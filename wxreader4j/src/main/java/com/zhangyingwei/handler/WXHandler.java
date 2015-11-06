package com.zhangyingwei.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.htmlparser.Node;
import org.htmlparser.tags.Div;
import org.htmlparser.util.NodeList;

import com.zhangyingwei.entity.HtmlNode;
import com.zhangyingwei.entity.SGWxMessage;
import com.zhangyingwei.entity.WXInfo;
import com.zhangyingwei.entity.WXMessage;
import com.zhangyingwei.filter.FilterFactory;
import com.zhangyingwei.util.HtmlUtil;
import com.zhangyingwei.util.HttpUtil;
import com.zhangyingwei.util.IpUtil;
import com.zhangyingwei.util.URLUtil;

public class WXHandler {
	
	public static List<WXInfo> getWxInfo(String msg){
		List<WXInfo> wxInfos = new ArrayList<WXInfo>();
		String url = URLUtil.getInfoUrl(msg);
		System.out.println("url@:"+url);
		IpUtil.doIp();//设置代理IP
		HtmlNode htmlNode = HtmlNode.getInstance(url);
		NodeList list = htmlNode.getNodesByTagName("div");
		NodeList listUrl = htmlNode.getDivsByClassName(list,"wx-rb bg-blue wx-rb_v1 _item");
		NodeList listName = HtmlNode.getDivsByClassName(list,"txt-box");
		for(int i = 0;i<listUrl.size();i++){
			Div node = (Div) listUrl.elementAt(i);
			Node name = listName.elementAt(i);
			WXInfo wxInfo = new WXInfo();
			wxInfo.setName(HtmlNode.find(name, "h3").elementAt(0).toPlainTextString());
			wxInfo.setDescription(HtmlNode.find(name, "h4").elementAt(0).toPlainTextString());
			wxInfo.setUrl(URLUtil.getMsgUrl(node.getAttribute("href")));
			wxInfos.add(wxInfo);
		}
		return wxInfos;
	}
	
	public static SGWxMessage getSgWxMessage(WXInfo wxInfo){
		SGWxMessage sgWxMessage = new SGWxMessage();
		System.out.println("参数信息:"+wxInfo.buildGetParams());
		String post = HttpUtil.doGet(wxInfo.getUrl(), wxInfo.buildGetParams());
		JSONObject json = null;
		try {
			String jsonstr = post.substring(wxInfo.getCb().length()+1, post.length()-1);
			json = JSONObject.fromObject(jsonstr);
		} catch (Exception e) {
			System.out.println("有可能是get失败了....");
			System.out.println(e);
		}
		sgWxMessage.setPage(json.getString("page"));
		sgWxMessage.setTotalItems(json.getString("totalItems"));
		sgWxMessage.setTotalPages(json.getString("totalPages"));
		String items = json.getString("items");
		JSONArray jsonArray = JSONArray.fromObject(items);
		sgWxMessage.setItems(jsonArray);
		return sgWxMessage;
	}
}
