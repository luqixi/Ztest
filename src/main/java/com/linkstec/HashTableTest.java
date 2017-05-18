package com.linkstec;

import java.util.Hashtable;

public class HashTableTest {

	public static void main(String[] args) {
		Hashtable<Object, Object> hashtable = new Hashtable<>();
		hashtable.put("hash", "table1");
//		hashtable.put("hash", null);
//		hashtable.put(null, "table1");
		System.out.println(hashtable.get("hash"));

	}

}
