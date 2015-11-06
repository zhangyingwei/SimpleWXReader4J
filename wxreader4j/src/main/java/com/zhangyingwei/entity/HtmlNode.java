package com.zhangyingwei.entity;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.zhangyingwei.filter.FilterFactory;

public class HtmlNode {
	
	private static Parser parser = null;
	private static HtmlNode htmlNode = null;
	
	public HtmlNode(){
		
	}
	
	private static class HtmlHandler{
		private static Parser parser = new Parser();
		private static HtmlNode htmlNode = new HtmlNode();
	}
	public static HtmlNode getInstance(String url){
		try {
			parser = HtmlHandler.parser;
			parser.setEncoding("utf-8");
			parser.setURL(url);
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return HtmlHandler.htmlNode;
	}
	
	/**
	 * 获取相同classname的div
	 * @param classname
	 * @return
	 */
	public static NodeList getDivsByClassName(String classname){
		NodeList nodes = null;
		try {
			nodes = parser.extractAllNodesThatMatch(new NodeClassFilter(Div.class));
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return getDivsByClassName(nodes, classname);
	}
	
	/**
	 * 根据名称从list里边选择出classname相同的div
	 * @param divs
	 * @param classname
	 * @return
	 */
	public static NodeList getDivsByClassName(NodeList divs,String classname){
		NodeList resultList  = new NodeList();
		for(int i = 0;i<divs.size();i++){
			Div div = (Div) divs.elementAt(i);
			if(div.getAttribute("class")!=null&&div.getAttribute("class").equals(classname)){
				resultList.add(div);
			}
		}
		return resultList;
	}
	
	public static NodeList getNodesByTagName(String tagname){
		NodeList nodes = null;
		try {
			nodes = parser.extractAllNodesThatMatch(FilterFactory.getNameFilter(tagname));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nodes;
	}
	
	public static NodeList find(Node baseNode,String name){
		NodeList nodes = baseNode.getChildren();
		return nodes.extractAllNodesThatMatch(FilterFactory.getNameFilter(name));
	}
	
	public static NodeList getTagByName(NodeList nodes,String name){
		return nodes.extractAllNodesThatMatch(FilterFactory.getNameFilter(name));
	}
}
