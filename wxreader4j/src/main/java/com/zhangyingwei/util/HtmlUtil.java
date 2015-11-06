package com.zhangyingwei.util;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;

import com.zhangyingwei.filter.FilterFactory;

public class HtmlUtil {
	
	
	/**
	 * 获取类名相同的所有Node
	 * @param divs
	 * @param classname
	 * @return 类名相同的div集合
	 */
	public static NodeList getDivsByClassName(NodeList divs,String classname){
		NodeList resultList  = new NodeList();
		for(int i = 0;i<divs.size();i++){
			Div div = (Div) divs.elementAt(i);
			if(div.getAttribute("class")!=null&&div.getAttribute("class").equals(classname)){
				System.out.println(div);
				resultList.add(div);
			}
		}
		return resultList;
	}
	
	/**
	 * 根据url获取所有类名相同的div
	 * @param url
	 * @param classname
	 * @return
	 */
	public static NodeList getDivsByClassName(String url,String classname){
		Parser parser = new Parser();
		NodeList nodes = null;
		try {
			parser.setEncoding("utf-8");
			parser.setURL(url);
			nodes = parser.extractAllNodesThatMatch(new NodeClassFilter(Div.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getDivsByClassName(nodes, classname);
	}
	
	/**
	 * 根据url获取所有node的集合
	 * @param url
	 * @return 所有node的集合
	 */
	public static NodeList getAllNodelist(String url){
		Parser parser = new Parser();
		NodeList nodes = new NodeList();
		try {
			parser.setEncoding("utf-8");
			parser.setURL(url);
			NodeIterator it = parser.elements();
			while(it.hasMoreNodes()){
				nodes.add(it.nextNode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nodes;
	}
}
