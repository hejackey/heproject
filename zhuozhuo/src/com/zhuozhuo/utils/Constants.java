package com.zhuozhuo.utils;

public class Constants {
//用于定义一些常量
	public static final String MD5_KEY="zhuozhuo_erp";
	
	public static final int getObjectLevels(String obj){
		if(StringUtils.isEmpty(obj))
			return 1;
		
		String[] aStr = obj.split("-");
		if(aStr==null)
			return 1;
		return aStr.length;
	}
	
	public static final String UPLOAD_PATH="d:/uploadtest/";
	public static final String ENTERWAREHOUSE = "enterWarehouse";//入库单
	public static final String RETURNEDPURCHASE = "returnedPurchase";//退货单
}
