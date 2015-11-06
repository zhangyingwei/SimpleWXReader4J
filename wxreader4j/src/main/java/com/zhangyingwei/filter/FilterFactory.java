package com.zhangyingwei.filter;

import org.htmlparser.NodeFilter;

public class FilterFactory {
	
	public static NodeFilter getNameFilter(String tagName){
		NodeFilter nodeFilter = null;
		try {
			Class classType = Class.forName(buildFilterName(tagName));
			nodeFilter = (NodeFilter) classType.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return nodeFilter;
	}
	
	private static String buildFilterName(String tagName){
		return "com.zhangyingwei.filter."+tagName.toUpperCase()+"Filter";
	}
}
