package com.zhangyingwei.util;

import java.util.Random;

public class IpUtil {
	public static void doIp(){
		System.setProperty("http.maxRedirects", "50");  
        System.getProperties().setProperty("proxySet", "true");  
        // 如果不设置，只要代理IP和代理端口正确,此项不设置也可以  
        String ips[] = {"93.91.200.146","221.130.18.5","221.130.23.135","221.130.18.78","221.130.23.134","221.130.18.49","111.1.32.36","221.130.18.49","221.130.18.49"};  
        String ip = ips[new Random().nextInt(9)];
        System.getProperties().setProperty("http.proxyHost", ip);  
        System.getProperties().setProperty("http.proxyPort", "80");
        System.out.println("代理IP设置成功@："+ip);
	}
}
