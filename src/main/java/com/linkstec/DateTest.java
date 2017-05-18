package com.linkstec;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateTest {

	static Logger log = Logger.getLogger(DateTest.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()));
	}

}
