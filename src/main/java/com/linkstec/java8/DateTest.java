package com.linkstec.java8;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateTest {

	static Logger logger = Logger.getLogger(DateTest.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(new Date());
		System.out.println(new Date().getTime()/1000);
		LocalDateTime time = LocalDateTime.now();
		System.out.println(time.getNano());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Long lll = Long.valueOf(sdf.format( new Date()));
		Date date1 = null;
		// 不宽容
		sdf.setLenient(false);
		try {
			date1 = sdf.parse("20160839");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("++++++++++++++++++++++++++++++++++++++++++");
		System.out.println(date1);
		System.out.println("++++++++++++++++++++++++++++++++++++++++++");
		System.out.println(lll);
		logger.info("dddd");
//		System.out.println(isHOLIDAYdebug(new Date()));
		
		Date date = new Date();
		date = getDay(date, 2);
		System.out.println(new SimpleDateFormat("yyyyMMdd").format(date));

	}

//	static boolean isHOLIDAYdebug (Date date) {
//		/*date = null;
//		try {
//			date = new SimpleDateFormat("yyyyMMdd").parse("20160729");
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}*/
//		Calendar calendar = Calendar.getInstance();
//		// 取得下一天
//		calendar.setTime(date);
//		calendar.add(Calendar.DAY_OF_MONTH,1);
//		// 判断是否是周末
//		if (Calendar.SATURDAY == calendar.get(Calendar.DAY_OF_WEEK) ||
//		    Calendar.SUNDAY == calendar.get(Calendar.DAY_OF_WEEK)	)
//		{
//			return true;
//		}
//		
//		date = calendar.getTime();
//		System.out.println(new SimpleDateFormat("yyyyMMdd").format(date));
//		return false;
//	}
//	// 返回下一个开盘日
//	static Date getddddDay(Date date, int iii) {
//		if ( iii == 0) {
//			return date;
//		}
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(date);
//		calendar.add(Calendar.DAY_OF_MONTH,1);
//		Date nextdate = calendar.getTime();
//		if(!isHOLIDAYdebug(nextdate))
//		{
//		return	getddddDay(nextdate, iii-1);
//		}
//		else
//		{
//			return	getddddDay(nextdate, iii);
//		}
//		
//		/*if(!isHOLIDAY(date)){
//			Calendar calendar = Calendar.getInstance();
//			// 取得下一天
//			calendar.setTime(date);
//			calendar.add(Calendar.DAY_OF_MONTH,1);
//			date = calendar.getTime();
//			getNextDay(date);
//		}
//		return date;*/
//	}
	// 判断当日是不是节假日
	static boolean isHoliday (Date date) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);
		// 判断是否是周末
		if (Calendar.SATURDAY == calendar.get(Calendar.DAY_OF_WEEK) ||
			Calendar.SUNDAY == calendar.get(Calendar.DAY_OF_WEEK))
		{
			return true;
		}
		return false;
	}
	// 返回下i个开盘日
	static Date getDay(Date date, int i) {
		if ( i == 0) {
			return date;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH,1);
		Date nextdate = calendar.getTime();
		if(!isHoliday(nextdate))
		{
			return	getDay(nextdate, i-1);
		}
		else
		{
			return	getDay(nextdate, i);
		}
	}
}
