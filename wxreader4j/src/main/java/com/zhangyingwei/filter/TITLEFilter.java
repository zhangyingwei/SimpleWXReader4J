package com.zhangyingwei.filter;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;

public class TITLEFilter implements NodeFilter{
	public TITLEFilter(){}
	public boolean accept(Node node) {
		if(node.getText().startsWith("title")){
			return true;
		}
		return false;
	}
}
