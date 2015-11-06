package com.zhangyingwei.filter;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;

public class H4Filter implements NodeFilter{
	public H4Filter(){}
	public boolean accept(Node node) {
		if(node.getText().startsWith("h4")){
			return true;
		}
		return false;
	}
}
