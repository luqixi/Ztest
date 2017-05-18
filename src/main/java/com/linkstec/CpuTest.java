package com.linkstec;

import org.apache.log4j.Logger;

public class CpuTest {

	static Logger log = Logger.getLogger(CpuTest.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("cpu数：" + Runtime.getRuntime().availableProcessors());
		System.out.println("现在线程的名字：" + Thread.currentThread().getName());

		log.info("cpu数：" + Runtime.getRuntime().availableProcessors());
		log.info("fuck");
	}

}
