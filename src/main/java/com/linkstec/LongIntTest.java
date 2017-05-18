package com.linkstec;

public class LongIntTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Long.MAX_VALUE,Long的最大值：" + Long.MAX_VALUE);
		long l1 = 1L;
		long l2 = l1 + 3;
		int i = (int) 3.14;
		System.out.println(l2);
		System.out.println(i);
		System.out.println("Integer.MAX_VALUE,Integer的最大值：" + Integer.MAX_VALUE);
		System.out.println("Integer.MIN_VALUE,Integer的最小值：" + Integer.MIN_VALUE);

		long l3 = 4294967295l;
		System.out.println(l3);
		long l4 = 2147483647;
		System.out.println(l4 * 2);
		System.out.println("++++++++++++++++++++++++++++");
		System.out.println(Long.valueOf("1"));
		System.out.println(Long.parseLong("1"));
		// java.lang.NumberFormatException: null
//		System.out.println(Integer.parseInt(null));
	}

}
