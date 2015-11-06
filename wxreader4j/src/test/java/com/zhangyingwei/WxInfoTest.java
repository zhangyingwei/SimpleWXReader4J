package com.zhangyingwei;

import com.zhangyingwei.handler.WXHandler;

public class WxInfoTest {
	public static void main(String[] args) {
		System.out.println(WXHandler.getWxInfo("考研专有助手").get(0));
//		String url = "http://weixin.sogou.com/gzhjs?cb=sogou.weixin.gzhcb&openid=oIWsFt-4WMODzcLHc0DQO_D8ZLKM&ext=_L45N5QlA_UxdiattZeDU7zVYkyiaZiVLYkqsSLW_xp7mSzloF186bx2zRYzFZdu&gzhArtKeyWord=&page=1&t=1444802554940";
//		WXHandler.getWxMessages(url);
	}
}
