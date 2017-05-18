package com.linkstec;

public class WaitNotifyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// java.lang.IllegalMonitorStateException
			"sss".wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
