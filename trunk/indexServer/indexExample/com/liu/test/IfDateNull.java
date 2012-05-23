package com.liu.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IfDateNull {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date d = new Date();
		d = null;
		String date = "";
		if( d != null ) {
			//将日期"上架时间"转为字符串
			SimpleDateFormat sf = new SimpleDateFormat( "yyyyMMdd" );
			//if( upTime != null )
			date = sf.format( d );
			System.out.println( date );
		}
		System.out.println( date );
	}

}
