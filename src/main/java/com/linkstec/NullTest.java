package com.linkstec;

public class NullTest {

	public static void main(String[] args) {
//		long lll = (long) (int) null;//Cannot cast from null to int
		String str = (String) null;
		System.out.println(str);
		System.out.println("null");
		
		String notNull = "null";
		String nu = null;
		System.out.println(nu == null);
		System.out.println(notNull == null);
		System.out.println(nu instanceof String);
		System.out.println(notNull instanceof String);
		System.out.println("end");

	}

}
