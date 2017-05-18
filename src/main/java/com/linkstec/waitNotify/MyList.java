package com.linkstec.waitNotify;

import java.util.ArrayList;
import java.util.List;

public class MyList {

	private static List<Object> list = new ArrayList<>();
	public static void add() {
		list.add("anyString");
	}
	public static int size() {
		return list.size();
	}

}
