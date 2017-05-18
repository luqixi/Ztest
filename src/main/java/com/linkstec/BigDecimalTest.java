package com.linkstec;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalTest {

	public static void main(String[] args) {
		BigDecimal bigdecimal = new BigDecimal("11");
		String sss = bigdecimal +"adfdf";
		System.out.println(sss);
		System.out.println(bigdecimal + "asas");
		System.out.println("++++++++++++++++++++++");
		BigDecimal bd = new BigDecimal("11.005000");
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		System.out.println(bd.toString());
		System.out.println("++++++++++++++++++++++");
		BigDecimal b1 = new BigDecimal("36510");
		BigDecimal b2 = new BigDecimal("300");
		BigDecimal result = b1.divide(b2,2,BigDecimal.ROUND_HALF_UP);
		System.out.println(result.toString());
		System.out.println(Double.parseDouble(result.toString()));
		System.out.println("++++++++++++++++++++++");
		// 打印0.899999999999999911182158029987476766109466552734375
		System.out.println(new BigDecimal(Double.toString(2.00))
				.subtract(new BigDecimal(1.10)));
        // double转BigDecimal建议做法 
        System.out.println(new BigDecimal(2.00).subtract(new BigDecimal(Double
        		.toString(1.10))));
        System.out.println("++++++++++++++++++++++");

        // 打印0.90
        System.out.println(new BigDecimal("2.00").subtract(new BigDecimal(
                "1.10")));

        // 异常java.lang.ArithmeticException: Non-terminating decimal expansion;
        // System.out.println(new BigDecimal("2.00")
        //        .divide(new BigDecimal("1.10")));
        // 打印1.81818
        System.out.println(new BigDecimal("2.00").divide(
                new BigDecimal("1.10"), 5, RoundingMode.HALF_EVEN));
        System.out.println("++++++++++++++++++++++");
        Double dddd = 2.00;
        System.out.println(dddd.toString());
	}
}