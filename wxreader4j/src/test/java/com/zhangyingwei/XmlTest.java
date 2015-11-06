package com.zhangyingwei;

import com.zhangyingwei.handler.XMLHandler;

public class XmlTest {
	public static void main(String[] args) {
		String xmlstr = "<?xml version=\"1.0\" encoding=\"gbk\"?><DOCUMENT><docid></docid><item> <key><![CDATA[http://mp.weixin.qq.com/]]></key><tplid><![CDATA[555]]></tplid><classid>11002601</classid> <display> </display></item></DOCUMENT>";
		System.out.println(XMLHandler.buildXml(xmlstr).getRootElement());
	}
}
