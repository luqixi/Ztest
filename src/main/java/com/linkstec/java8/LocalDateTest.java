package com.linkstec.java8;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateTest {

	public static void main(String[] args) {
		LocalDate localDate = LocalDate.now().plusDays(-1);
		
		System.out.println(localDate);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String local = localDate.format(formatter);
		System.out.println(local);
		
		
		String local2 = LocalDate.now().plusDays(-1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		System.out.println(local2);

	}

}
