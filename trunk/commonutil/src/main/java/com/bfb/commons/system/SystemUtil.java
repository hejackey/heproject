package com.bfb.commons.system;

import org.apache.commons.lang.StringUtils;

/**
 * system工具类
 * @author xiaolianghe
 *
 */
public class SystemUtil {
	/**
	 * 取操作系统类型
	 * @return	操作系统类型字符串
	 */
	public static String getSystemType(){
		return System.getProperty("os.name");
	}
	
	/**
	 * 判断操作系统类型是否是window
	 * @return 是返回true，否则返回false
	 */
	public static boolean isWinSystem(){
		String osType = getSystemType();
		if(StringUtils.isEmpty(osType))
			return false;
		
		if(osType.toLowerCase().contains("window"))
			return true;
		else
			return false;
	}
}
