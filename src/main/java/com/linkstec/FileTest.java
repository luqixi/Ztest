package com.linkstec;

import java.io.File;

public class FileTest {

	public static void main(String[] args) {
		File file = new File("test.txt");
		System.out.println(file.getAbsolutePath());
		System.out.println(FileTest.class.getClassLoader().getResource("").getPath()); 

	}

}
