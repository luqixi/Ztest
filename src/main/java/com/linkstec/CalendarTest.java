package com.linkstec;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CalendarTest {

	public static void main(String[] args) {
		Map<Integer, String> map = new HashMap<Integer, String>();
//		int str = Calendar.getInstance().get(Calendar.YEAR);
		map.put(2017, "fff");
		
		String str2 = map.get(Calendar.getInstance().get(Calendar.YEAR));

		System.out.println(str2);
	}

}
