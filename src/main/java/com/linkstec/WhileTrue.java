package com.linkstec;

public class WhileTrue {

	static int count;
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		while (true) {
			count++;
			long end = System.currentTimeMillis();
			if ((end - start) > 1000) {
				System.out.println("一秒累加了" + count + "多次！！！厉害了");
//				break;
				return;
			}
		}
	}
}
