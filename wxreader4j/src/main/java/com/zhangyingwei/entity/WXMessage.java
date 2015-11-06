package com.zhangyingwei.entity;

public class WXMessage {
	private String key;
	private String title;
	private String decription;
	private String publishTime;
	private String docid;
	private String url;
	public String getUrl() {
		url = WXInfo.BASEURL+url;
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDecription() {
		return decription;
	}
	public void setDecription(String decription) {
		this.decription = decription;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getDocid() {
		return docid;
	}
	public void setDocid(String docid) {
		this.docid = docid;
	}
	@Override
	public String toString() {
		return "WXMessage [key=" + key + ", title=" + title + ", decription="
				+ decription + ", publishTime=" + publishTime + ", docid="
				+ docid + ", url=" + this.getUrl() + "]";
	}
}
