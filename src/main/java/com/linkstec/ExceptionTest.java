package com.linkstec;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExceptionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
		  getException();
		}catch (Exception e){
		System.out.println("cuo");	
		}
	}

	private static void getException() throws Exception{
		File file = new File("d://hehe.txt");
		System.out.println(file.exists());
		try {
			FileWriter writer = new FileWriter(file);
			writer.close();
		} catch (IOException e) {
			System.out.println("cuole");
			e.printStackTrace();
			throw e;
		}
	}
}
