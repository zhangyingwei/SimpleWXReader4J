package com.zhangyingwei.handler;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.zhangyingwei.entity.WXMessage;

public class XMLHandler {
	
	public static Document buildXml(String xmlStr){
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(new StringReader(xmlStr));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;
	}
	
	public static WXMessage docToWxMessage(Document document){
		WXMessage message = new WXMessage();
		Element root = document.getRootElement();
		Element item = root.element("item");
		Element display = item.element("display");
		String key = item.elementText("key");
		String docid = display.elementText("docid");
		String title = display.elementText("title");
		String url = display.elementText("url");
		String content = display.elementText("content");
		String lastModified = display.elementText("lastModified");
		
		message.setKey(key);
		message.setDocid(docid);
		message.setTitle(title);
		message.setUrl(url);
		message.setDecription(content);
		message.setPublishTime(lastModified);
		return message;
		
	}
	
	
}
