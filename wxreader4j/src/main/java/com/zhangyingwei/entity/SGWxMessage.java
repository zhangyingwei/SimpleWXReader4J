package com.zhangyingwei.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dom4j.Document;

import com.zhangyingwei.handler.XMLHandler;

public class SGWxMessage {
	private String totalItems;
	private String totalPages;
	private String page;
	private List items;
	private List<WXMessage> wxMessages;
	public String getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(String totalItems) {
		this.totalItems = totalItems;
	}
	public String getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(String totalPages) {
		this.totalPages = totalPages;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public List getItems() {
		return items;
	}
	public void setItems(List items) {
		this.items = items;
	}
	public List<WXMessage> getWxMessages() {
		this.wxMessages = new ArrayList<WXMessage>();
		for(Object obj:this.getItems()){
			Document msgDoc = XMLHandler.buildXml(obj.toString());
			WXMessage message = XMLHandler.docToWxMessage(msgDoc);
			wxMessages.add(message);
		}
		return wxMessages;
	}
	@Override
	public String toString() {
		return "SGWxMessage [totalItems=" + totalItems + ", totalPages="
				+ totalPages + ", page=" + page + ", items=" + items + "]";
	}
}
