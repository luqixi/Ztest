package com.linkstec;

import java.util.Properties;

public class SystemTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir") );
		Properties properties = System.getProperties();
		for (Object key : properties.keySet()) {
			
			System.out.println(key + "是" + properties.getProperty(key.toString()));
		}
		System.out.println(System.currentTimeMillis() - 60000);
		/*try {
			int resultInt = System.in.read();
			System.out.println(resultInt);
		} catch (IOException e) {
			e.printStackTrace();
		}*/

	}

}
