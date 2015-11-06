package com.zhangyingwei.filter;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;

public class H2Filter implements NodeFilter{
	public H2Filter(){}
	public boolean accept(Node node) {
		if(node.getText().startsWith("h2")){
			return true;
		}
		return false;
	}
}
