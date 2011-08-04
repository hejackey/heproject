package com.bf.portal.test;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class TestJodaTime {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DateTime dt = new DateTime();
		
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		System.out.println(dt.plusDays(-1).toString(fmt));
		
		String str = fmt.print(dt);
		System.out.println(str);
		
		fmt = DateTimeFormat.forPattern("MMMM, yyyy");
		System.out.println(fmt.print(dt));
		dt.plusDays(1);
		fmt = DateTimeFormat.forPattern("C yyyyMMdd");
		System.out.println(fmt.print(dt));
		
		DateTime dateTime_temp = dt.plusDays(1);
		System.out.println("当前日期+1天：" + dateTime_temp.toString(ISODateTimeFormat.date()));
		dateTime_temp = dt.plusDays(-1);
		System.out.println("当前日期-1天：" + dateTime_temp.toString(ISODateTimeFormat.date()));
		
		/**
		 * 月份加减
		 */
		dateTime_temp = dt.plusMonths(1);
		System.out.println("当前日期+1月：" + dateTime_temp.toString(fmt));
		dateTime_temp = dt.plusMonths(-1);
		System.out.println("当前日期-1月：" + dateTime_temp.toString(ISODateTimeFormat.date()));
	}

}
