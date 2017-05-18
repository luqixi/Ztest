package com.linkstec;

import java.util.HashSet;
import java.util.Set;

public class SetToArrayTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Set<String> set = new HashSet<String>();
		 set.add("stockHolder_900940");
		 set.add("stockHolder_300229");

		 System.out.println(set.toString());
		 String[] temp = new String[set.size()];
		 String[]  sss = (String[]) set.toArray(temp);
//		 String[]  ssss = (String[]) set.toArray(new String[set.size()]);

		 System.out.println(sss);
		 for (String ssssss :sss){
			 System.out.println(ssssss);
		 }

	}

}
