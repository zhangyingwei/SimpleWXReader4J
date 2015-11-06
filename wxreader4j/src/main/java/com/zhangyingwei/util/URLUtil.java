package com.zhangyingwei.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class URLUtil {
	private static final String BASEURL = "http://weixin.sogou.com";
	
	public static String doEncodeUtf8(String msg){
		String result = "";
		try {
			result = URLEncoder.encode(msg, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static String getInfoUrl(String msg){
		StringBuffer url = new StringBuffer();
		url.append(BASEURL).append("/weixin?type=1&query=").append(doEncodeUtf8(msg)).append("&ie=utf8");
		return url.toString();
	}
	
	public static String getMsgUrl(String url){
		StringBuffer realUrl = new StringBuffer();
		realUrl.append(BASEURL).append(url.replace("amp;", ""));
		return realUrl.toString();
	}
}
