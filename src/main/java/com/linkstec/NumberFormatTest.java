package com.linkstec;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class NumberFormatTest {

	public static void main(String[] args) {
		NumberFormat nf = new DecimalFormat(",##0.00");
		System.out.println(nf.format(99999999));
	}
}
