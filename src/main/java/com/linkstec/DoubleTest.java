package com.linkstec;

import org.apache.log4j.Logger;

public class DoubleTest {

	static Logger log = Logger.getLogger(DoubleTest.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String ss = "1234";
		double dd = Double.parseDouble(ss);
		System.out.println(dd);
	}

}
