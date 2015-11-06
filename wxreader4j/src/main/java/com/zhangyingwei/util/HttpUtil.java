package com.zhangyingwei.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.zhangyingwei.entity.WXInfo;

public class HttpUtil {

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String doGet(String url, String param) {
		StringBuffer result = new StringBuffer();
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			System.err.println(urlNameString);
			IpUtil.doIp();//设置代理IP
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("Cookie",  getCookie());
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// // 遍历所有的响应头字段
			// for (String key : map.keySet()) {
			// System.out.println(key + "--->" + map.get(key));
			// }
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(),"utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				if(line.startsWith("<!--STATUS")){
					break;
				}
				result.append(line);
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result.toString();
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String doPost(String urlstr, String param) {
		StringBuffer sb = new StringBuffer();
		try {
			// 建立连接
			URL url = new URL(urlstr);
			HttpURLConnection httpConn = (HttpURLConnection) url
					.openConnection();

			// //设置连接属性
			httpConn.setDoOutput(true);// 使用 URL 连接进行输出
			httpConn.setDoInput(true);// 使用 URL 连接进行输入
			httpConn.setUseCaches(false);// 忽略缓存
			httpConn.setRequestMethod("POST");// 设置URL请求方法

			// 设置请求属性
			// 获得数据字节数据，请求数据流的编码，必须和下面服务器端处理请求流的编码一致
			byte[] requestStringBytes = param.getBytes("utf-8");
			httpConn.setRequestProperty("Content-length", ""
					+ requestStringBytes.length);
			httpConn.setRequestProperty("Content-Type",
					"application/octet-stream");
			httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			httpConn.setRequestProperty("Charset", "UTF-8");

			// 建立输出流，并写入数据
			OutputStream outputStream = httpConn.getOutputStream();
			outputStream.write(requestStringBytes);
			outputStream.close();
			// 获得响应状态
			int responseCode = httpConn.getResponseCode();

			if (HttpURLConnection.HTTP_OK == responseCode) {// 连接成功
				// 当正确响应时处理数据
				String readLine;
				BufferedReader responseReader;
				// 处理响应流，必须与服务器响应流输出的编码一致
				responseReader = new BufferedReader(new InputStreamReader(
						httpConn.getInputStream(), "UTF-8"));
				while ((readLine = responseReader.readLine()) != null) {
					System.out.println(readLine);
					sb.append(readLine);
					Thread.currentThread().sleep(600);
				}
				responseReader.close();
				// tv.setText(sb.toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 初始化参数
	 * 
	 * @param params
	 * @return
	 */
	public static String initParam(Map params) {
		StringBuffer result = new StringBuffer();
		for (Object key : params.keySet()) {
			String keyString = key.toString();
			result.append(keyString);
			result.append("=");
			result.append(params.get(keyString));
			result.append("&");
		}
		if (result.length() != 0) {
			result = result.deleteCharAt(result.length() - 1);
		}
		return result.toString();
	}
	
	public static String getCookie(){
//		"black_passportid=1; domain=.sogou.com; path=/; expires=Thu, 01-Dec-1994 16:00:00 GMT"
		String cookie = "ABTEST=5|"+new Date().getTime()+"|v1; "+getKeyString("http://weixin.sogou.com/weixin?query=111")+"; IPLOC=CN;weixinIndexVisited=1; wapsogou_qq_nickname=; sct=4";
		System.out.println("cookie:"+cookie);
		return cookie;
	}
	
	public static String getKeyString(String url){
		StringBuffer keyStr = new StringBuffer();
		String suid = "";
		String sunid = "";
		try {
			URL conUrl = new URL(url);
			IpUtil.doIp();
			URLConnection connection = conUrl.openConnection();
			List<String> list = connection.getHeaderFields().get("Set-Cookie");
			if(list!=null&&list.size()!=0){
				for(String params:list){
					int indexsuid = params.indexOf("SUID");
					int indexsunid = params.indexOf("SNUID");
					if(suid.length()==0){
						suid = (indexsuid==-1)?"":new String(params).substring(indexsuid+5,indexsuid+37);
					}
					if(sunid.length()==0){
						System.out.println(params);
						sunid = (indexsunid==-1)?"":new String(params).substring(indexsunid+6,indexsunid+38);
					}
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return keyStr.append("SUID=").append(suid).append(";").append("SUNID=").append(sunid).toString();
	}
	
}