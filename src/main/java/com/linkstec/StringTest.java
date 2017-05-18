package com.linkstec;

public class StringTest {

	public static void main(String[] args) {
		String str1 = "helloWorld";
		String str2 = "hello" + "World";
		String str3 = "hello";
		String str4 = "World";
		String str5 = str3 + str4;
		System.out.println(str1.equals(str2));
		System.out.println(str1.equals(str5));
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++");

		String trdDate = "20170309";
		String trdTime = "133018";
		String time = trdDate.substring(0, 4) + "-" + trdDate.substring(4, 6) + "-" + trdDate.substring(6, 8)
		              + " " + trdTime.substring(0, 2) + ":" + trdTime.substring(2, 4);
		System.out.println(time);
		System.out.println("++++++++++++++++++++++++++++++++++++++++++");
		String fundId = "123455678";
		String secret = fundId.replaceAll("(?<=\\d{0})\\d(?=\\d{3})", "*");
		System.out.println(secret);
		System.out.println("++++++++++++++++++++++++++++++++++++++++++");
		int yourNumber = 82830;
		String mmm = String.format("%06d", yourNumber);
		System.out.println(mmm);
		System.out.println("++++++++++++++++++++++++++++++++++++++++++");
		String alarm = "12345644444";
		String aaa = alarm.length() > 5 ? alarm.substring(0,5) : alarm;
		System.out.println(aaa);
	}

}
