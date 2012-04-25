package com.zhuozhuo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateSheetIdUtil {
	/**
	 * 指定key加日期生成单据号
	 * @param key
	 * @return
	 */
	public static String genSheetIdDate(String key){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");		
		return key+sdf.format(new Date());
	}
	
	/**
	 *指定key加日期时间生成单据号
	 * @param key
	 * @return
	 */
	public static String genSheetIdDateTime(String key){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH24miss");		
		return key+sdf.format(new Date());
	}
}
