package com.zhangyingwei.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTest {
	public static void main(String[] args) {
		Date date = new Date(1444802554940l);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
	}
}
