package com.zhangyingwei.entity;

import java.util.Date;

import net.sf.json.JSONObject;

public class WXInfo {
	private String name;
	private String description;
	private String url;
	private String openid;
	private String ext;
	private String page;
	private String cb;
	private String gzhArtKeyWord = "";
	private String t;
	private String baseUrl = "http://weixin.sogou.com";
	public static final String BASEURL = "http://weixin.sogou.com";
	public String cookie;
	
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	public String getPage() {
		return page==null?"1":page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getCb() {
		//设置默认cb
		return cb==null?"sogou.weixin.gzhcb":cb;
	}
	public void setCb(String cb) {
		this.cb = cb;
	}
	public String getGzhArtKeyWord() {
		return gzhArtKeyWord;
	}
	public void setGzhArtKeyWord(String gzhArtKeyWord) {
		this.gzhArtKeyWord = gzhArtKeyWord;
	}
	public String getT() {
		//获取当前时间戳
		this.t = new Date().getTime()+"";
		return t;
	}
	private String getBaseUrl() {
		return baseUrl;
	}
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return baseUrl+="/gzhjs";
	}
	public void setUrl(String url) {
		if(url==null){
			return;
		}
		String[] strarr = url.split("\\?");
		String param = strarr.length==1?"":strarr[1];
		String[] params = param.split("&");
		JSONObject jsonObject = new JSONObject();
		
		String[] openids = params[0].split("=");
		jsonObject.put("openid", openids[1]==null?"":openids[1]);
		
		String[] exts = params[1].split("=");
		jsonObject.put("ext", exts[1]==null?"":exts[1]);

		this.setOpenid(jsonObject.getString("openid"));
		this.setExt(jsonObject.getString("ext"));
		this.url = url;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	
	public String buildPostParams(){
		JSONObject params = new  JSONObject();
		params.put("cb", this.getCb());
		params.put("openid", this.getOpenid());
		params.put("ext", this.getExt());
		params.put("gzhArtKeyWord", this.getGzhArtKeyWord());
		params.put("page", this.getPage());
		params.put("t", this.getT());
		return params.toString();
	}
	public String buildGetParams(){
		StringBuffer params = new StringBuffer();
		return params.append("cb=").append(this.getCb()).append("&")
		.append("openid=").append(this.getOpenid()).append("&")
		.append("ext=").append(this.getExt()).append("&")
		.append("gzhArtKeyWord=").append("").append("&")
		.append("page=").append(this.getPage()).append("&")
		.append("t=").append(this.getT())
		.toString();
	}
	
	@Override
	public String toString() {
		return "WXInfo [name=" + this.getName() + ", description=" + this.getDescription()
				+ ", url=" + this.getUrl() + ", openid=" + this.getOpenid() + ", ext=" + this.getExt()
				+ ", page=" + this.getPage() + ", cb=" + this.getCb() + ", gzhArtKeyWord="
				+ this.getGzhArtKeyWord() + ", t=" + this.getT() + "]";
	}
}
