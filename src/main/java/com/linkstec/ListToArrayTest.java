package com.linkstec;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class ListToArrayTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		 list.add("123");
		 list.add("234");
		 String listStr = list.toString();
		 System.out.println(listStr.substring(1,listStr.length()-1));
		 System.out.println("+++++++++++++++++++++++++++++++");
		 String utilStr = StringUtils.join(list, ",");
//		 String utilStr = StringUtils.join(list, ",");
		 System.out.println(utilStr);
		 System.out.println("+++++++++++++++++++++++++++++++");
		 String[] s = new String[list.size()];
		 String[]  sss = (String[]) list.toArray(s);
		 System.out.println(sss);
		 for (String ssssss :sss){
			 System.out.println(ssssss);
		 }

	}

}
