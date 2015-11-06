package com.zhangyingwei.filter;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;

public class PFilter implements NodeFilter{
	public PFilter(){}
	public boolean accept(Node node) {
		if(node.getText().startsWith("p")){
			return true;
		}
		return false;
	}
}
