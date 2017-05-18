package com.linkstec;

import org.apache.commons.lang.math.NumberUtils;

public class NumberTest {

	public static void main(String[] args) {
		Number number = NumberUtils.createNumber("1213.22");
		System.out.println(number);
		System.out.println(number instanceof Float);
	}
}
