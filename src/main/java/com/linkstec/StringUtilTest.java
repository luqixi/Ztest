package com.linkstec;

import org.apache.commons.lang3.StringUtils;

public class StringUtilTest {

	public static String keyNameSpace;
	
	public static void main(String[] args) {
		System.out.println(StringUtils.EMPTY == "");
		System.out.println(StringUtils.EMPTY.equals(""));

		String test = "test;";
		System.out.println(test.substring(0, test.length() - 1));
		System.out.println("++++++++++++++++++分隔符++++++++++++++++");
		System.out.println(String.format("%1$,09d", 3123));
		System.out.println("++++++++++++++++++分隔符++++++++++++++++");
		System.out.println(keyNameSpace+"111");
	}

}
