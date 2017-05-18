package com.linkstec;

public class IntegerTest {

	public static int ii;
	public static String str;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int a = 0 ;
		int b = 1 ;
		// return (x < y) ? -1 : ((x == y) ? 0 : 1);
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.compare(a, b));
		System.out.println("----------------------华丽的分割线-----------------");
		int expiredSencods = 60 * 60 * 24 * 2;
		System.out.println(expiredSencods);
		System.out.println(ii);
		System.out.println(str);
		Integer int1 = 1000,int2 = 1000;
		System.out.println(int1 == int2);
		Integer int3 = 127,int4 = 127;
		System.out.println(int3 == int4);
		System.out.println("----------------------华丽的分割线-----------------");
//		System.out.println(b.toString());
		System.out.println(int3.toString());

	}

}