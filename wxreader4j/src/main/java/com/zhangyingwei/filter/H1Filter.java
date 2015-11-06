package com.zhangyingwei.filter;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;

public class H1Filter implements NodeFilter{
	
	public H1Filter(){}
	
	public boolean accept(Node node) {
		if(node.getText().startsWith("h1")){
			return true;
		}
		return false;
	}
}
