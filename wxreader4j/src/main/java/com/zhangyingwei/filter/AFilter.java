package com.zhangyingwei.filter;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;

public class AFilter implements NodeFilter{
	public AFilter(){}
	public boolean accept(Node node) {
		if(node.getText().startsWith("a")){
			return true;
		}
		return false;
	}
}
