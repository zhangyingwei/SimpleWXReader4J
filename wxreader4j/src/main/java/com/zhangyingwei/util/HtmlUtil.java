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
	 * ��ȡ������ͬ������Node
	 * @param divs
	 * @param classname
	 * @return ������ͬ��div����
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
	 * ����url��ȡ����������ͬ��div
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
	 * ����url��ȡ����node�ļ���
	 * @param url
	 * @return ����node�ļ���
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
