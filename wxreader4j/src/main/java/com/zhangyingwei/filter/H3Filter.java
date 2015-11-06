package com.zhangyingwei.filter;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;

public class H3Filter implements NodeFilter{
	public H3Filter(){}
	public boolean accept(Node node) {
		if(node.getText().startsWith("h3")){
			return true;
		}
		return false;
	}
}
