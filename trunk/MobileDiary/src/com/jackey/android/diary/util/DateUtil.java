package com.jackey.android.diary.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public  static String formatDate(Date date,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	public static void  main(String[] arg){
		System.out.println(formatDate(new Date(),"yyyy-MM-dd"));
	}
}	
