package com.bfb.commons.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	private static SimpleDateFormat sdf =null;
	private static Calendar calendar = Calendar.getInstance();
	
	/**
	 * 日期格式化string
	 * @param date	待格式化的日期
	 * @param format	string的日期格式
	 * @return	格式化后的字符串
	 */
	public static String formatDate2Str(Date date ,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		
		return sdf.format(date);
	}
	
	/**
	 * 字符串转成date
	 * @param val	字符串日期
	 * @param format	日期格式
	 * @return	返回转换后的日期
	 * @throws ParseException
	 */
	public static Date formatStr2Date(String val,String format) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		
		return sdf.parse(val);
	}
	
	/**
	 * 日期格式化成默认格式的字符串，yyyy-MM-dd HH:mm:ss
	 * @param date 待格式化日期
	 * @return	格式化字符串
	 */
	public static String formatDateTime2Str(Date date){
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		return sdf.format(date);
	}
	
	/**
	 * 日期格式化成默认格式的字符串，yyyy-MM-dd
	 * @param date 待格式化日期
	 * @return	格式化字符串
	 */
	public static String formatDate2Str(Date date){
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		return sdf.format(date);
	}
	
	/**
	 * 获取日期中的年
	 * @param val	字符串日期
	 * @return	返回年
	 * @throws ParseException
	 */
	public static int getYearByStrDate(String val,String format) throws ParseException{
		sdf = new SimpleDateFormat(format);
		calendar.setTime(sdf.parse(val));
		
		return calendar.get(Calendar.YEAR);
	}
	
	/**
	 * 获取日期中的月
	 * @param val	字符串日期
	 * @return	返回月
	 * @throws ParseException
	 */
	public static int getMonthByStrDate(String val,String format) throws ParseException{
		sdf = new SimpleDateFormat(format);
		calendar.setTime(sdf.parse(val));
		
		return calendar.get(Calendar.MONTH)+1;
	}
	
	/**
	 * 获取日期中的日
	 * @param val	字符串日期
	 * @return	返回日
	 * @throws ParseException
	 */
	public static int getDateByStrDate(String val,String format) throws ParseException{
		sdf = new SimpleDateFormat(format);
		calendar.setTime(sdf.parse(val));
		
		return calendar.get(Calendar.DATE);
	}
	
	/**
	 * calendar转date
	 * @param calendar
	 * @return
	 */
	public static Date calend2Date(Calendar calendar){
		return calendar.getTime();
	}
	
	/**
	 * date转calendar
	 * @param date
	 * @return
	 */
	public static Calendar DateTCalendar(Date date){
		calendar.setTime(date);

		return calendar;
	}
	
	/**
	 * 日期年份加减
	 * @param date	日期
	 * @param val	加减年数
	 * @return	返回加减年份后的日期
	 */
	public static Date addMinYear(Date date,int val){
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, val);
		
		return calendar.getTime();
	}
	
	/**
	 * 日期月份加减
	 * @param date	日期
	 * @param val	加减月数
	 * @return	返回加减月数后的日期
	 */
	public static Date addMinMonth(Date date,int val){
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, val);
		
		return calendar.getTime();
	}
	
	/**
	 * 日期天数加减
	 * @param date 日期
	 * @param val	加减天数
	 * @return	返回加减天数后的日期
	 */
	public static Date addMinDay(Date date,int val){
		calendar.setTime(date);
		calendar.add(Calendar.DATE, val);
		
		return calendar.getTime();
	}
	
	/**
	 * 获取当前月的第一天
	 * @param date	date型日期
	 * @return	返回当前月第一天的日期
	 */
	public static Date getFirstDayOfMonth(Date date){
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE,calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        
        return calendar.getTime();
	}
	
	/**
	 * 返回当前月的最后一天
	 * @param date	date型日期
	 * @return	返回当前月最后一天的日期
	 */
	public static Date getLastDayOfMonth(Date date)   {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        
        return calendar.getTime();
	} 
	
	/**
	 * 日期比较
	 * @param startTime	日期,格式yyyy-mm-dd
	 * @param endTime	日期,格式yyyy-mm-dd
	 * @return endTime>startTime返回true，否则返回false
	 */
	public static boolean compDate(String startTime,String endTime){		
		if (Integer.parseInt(endTime.substring(0, 4)) > Integer.parseInt(startTime.substring(0, 4)))
			return true;
		else if (Integer.parseInt(endTime.substring(0, 4)) == Integer.parseInt(startTime.substring(0, 4)))
		{
			if (Integer.parseInt(endTime.substring(5, 7)) > Integer.parseInt(startTime.substring(5, 7)))
				return true;
			else if(Integer.parseInt(endTime.substring(5, 7)) == Integer.parseInt(startTime.substring(5, 7))){
				if(Integer.parseInt(endTime.substring(8,10)) >= Integer.parseInt(startTime.substring(8,10)))
					return true;
				else
					return false;
			}
			else
				return false;
		}
		else
			return false;
	}
	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		System.out.println(compDate("2011-08-29","2012-08-26"));
		System.out.println(formatDate2Str(new Date(),"yyyy-MM-dd HH:mm:ss"));
		System.out.println(formatDateTime2Str(new Date()));
		System.out.println(formatDate2Str(new Date()));
		System.out.println(formatStr2Date("2011-08-31","yyyy-MM-dd"));
		
		System.out.println("year="+getYearByStrDate("2011-08-31 13","yyyy-MM-dd HH"));
		
		System.out.println(getMonthByStrDate("2011-08-31","yyyy-MM-dd"));
		
		System.out.println(getDateByStrDate("2011-08-31","yyyy-MM-dd"));
		
		System.out.println(formatDateTime2Str(addMinYear(new Date(),-5)));
		
		System.out.println(formatDateTime2Str(addMinMonth(new Date(),-18)));
		
		System.out.println(formatDateTime2Str(addMinDay(new Date(),-31)));
		System.out.println(formatDateTime2Str(getLastDayOfMonth(formatStr2Date("2011-02-01","yyyy-MM-dd"))));
		System.out.println(formatDateTime2Str(getFirstDayOfMonth(formatStr2Date("2011-08-26","yyyy-MM-dd"))));
		
	}

}
