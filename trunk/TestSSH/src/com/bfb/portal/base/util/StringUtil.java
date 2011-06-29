package com.bfb.portal.base.util;

import java.io.UnsupportedEncodingException;

/**
 * 字符串工具类
 * @author Administrator
 *
 */
public class StringUtil {
	/**
	 * 校验字符串是否为空
	 * @param param 待校验的字符串
	 * @return
	 */
	public static boolean isEmpty(String param){
		if(param==null || "".equals(param.trim()))
			return true;
		
		return false;
	}
	
	/**
	 * 校验字符串超过指定长度
	 * @param param	待校验的字符串
	 * @param len	长度
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static boolean validStrExceedLen(String param,int len) throws UnsupportedEncodingException{
		if(!isEmpty(param) && param.getBytes("gbk").length>len)
			return true;
		
		return false;
	}
}
