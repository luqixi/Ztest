package com.linkstec;

public class EquslsTest {
	static String str1 = "123";
	static String str2 = "123";

	public static void main(String[] args) {
		System.out.println(String1.str1.equals(String2.str1));
		System.out.println(String1.str1 == String2.str1);
	}
}
class String1 {
	static String str1 = "123";
}
class String2 {
	static String str1 = "123";
	
}