package com.zhangyingwei.filter;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;

public class DIVFilter implements NodeFilter{
	public DIVFilter(){}
	public boolean accept(Node node) {
		if(node.getText().startsWith("div")){
			return true;
		}
		return false;
	}
}
