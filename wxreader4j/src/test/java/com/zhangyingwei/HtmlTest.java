package com.zhangyingwei;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.zhangyingwei.entity.HtmlNode;
import com.zhangyingwei.util.HtmlUtil;
import com.zhangyingwei.util.URLUtil;

public class HtmlTest {
	public static void main(String[] args) throws Exception {
//		try {
//			Parser parser = new Parser();
//			parser.setURL("http://weixin.sogou.com/weixin?type=1&query=%E7%BD%97%E6%B0%B8%E6%B5%A9&ie=utf8&");
//			parser.setEncoding("utf-8");
//			
//			NodeFilter afilter = new NodeClassFilter(Div.class);
//			
//			NodeList alist = parser.extractAllNodesThatMatch(afilter);
//			for(int i = 0;i<alist.size();i++){
//				Node node = alist.elementAt(i);
//				if(node instanceof Div){
//					Div div = (Div) node;
//					System.out.println(div.getAttribute("class"));
//				}
//			}
//		} catch (ParserException e) {
//			e.printStackTrace();
//		}
		
		String url = URLUtil.getInfoUrl("开源中国");
		HtmlNode htmlNode = HtmlNode.getInstance(url);
		NodeList list = htmlNode.getNodesByTagName("div");
//		NodeList list = htmlNode.getDivsByClassName("txt-box");
//		System.out.println(list);
		NodeList listUrl = htmlNode.getDivsByClassName(list,"wx-rb bg-blue wx-rb_v1 _item");
		NodeList listName = HtmlNode.getDivsByClassName(list,"txt-box");
		System.out.println("********************************************************");
//		System.out.println(listUrl);
		for(int i = 0;i<listUrl.size();i++){
			Div node = (Div) listUrl.elementAt(i);
			Node name = listName.elementAt(i);
			System.out.print(HtmlNode.find(name, "h3").elementAt(0).toPlainTextString());
			System.out.println(HtmlNode.find(name, "h4").elementAt(0).toPlainTextString());
//			System.out.print(HtmlUtil.find(name, ""));
			System.out.println(node.getAttribute("href"));
//			System.out.println(node.toPlainTextString());
		}
//		System.out.println(htmlNode.getDivsByClassName("txt-box"));
	}
}
