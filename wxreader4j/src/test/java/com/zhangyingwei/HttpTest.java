package com.zhangyingwei;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.zhangyingwei.entity.SGWxMessage;
import com.zhangyingwei.entity.WXInfo;
import com.zhangyingwei.entity.WXMessage;
import com.zhangyingwei.handler.WXHandler;
import com.zhangyingwei.util.HttpUtil;
import com.zhangyingwei.util.IpUtil;

public class HttpTest {
	public static void main(String[] args) throws Exception {
//		String url = "http://weixin.sogou.com/gzhjs";
//		String param = "cb=sogou.weixin.gzhcb&openid=oIWsFt-4WMODzcLHc0DQO_D8ZLKM&ext=_L45N5QlA_UxdiattZeDU7zVYkyiaZiVLYkqsSLW_xp7mSzloF186bx2zRYzFZdu&gzhArtKeyWord=&page=1&t=1444802554940";
////		String result = HttpUtil.doGet(url, param);
//		Map map = new HashMap();
//		map.put("cb", "sogou.weixin.gzhcb");
//		map.put("ext", "_L45N5QlA_UxdiattZeDU7zVYkyiaZiVLYkqsSLW_xp7mSzloF186bx2zRYzFZdu");
//		map.put("gzhArtKeyWord", "");
//		map.put("page", "1");
//		map.put("t", "1444802554940");
		
		
		WXInfo wxInfo = WXHandler.getWxInfo("考研专有助手").get(0);
		SGWxMessage  sgWxMessage = WXHandler.getSgWxMessage(wxInfo);
		WXMessage msg = sgWxMessage.getWxMessages().get(0);
		System.out.println(msg.getTitle()+":\n"+msg.getUrl());
		
		
//		String post = HttpUtil.doGet(wxInfo.getUrl(), wxInfo.buildGetParams());
//		System.out.println(post);
//		JSONObject json = JSONObject.fromObject(post.substring(wxInfo.getCb().length()+1, post.length()));
		
//		System.out.println(HttpUtil.getKeyString("http://sogou.com/web?query=111"));
	}
}
