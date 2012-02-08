package com.bfb.commons.security;

/**
 * 一般servlet请求过滤
 * @author Administrator
 * @version 1.0
 * @date 2012-2-8
 */
public class HTMLFilterUtil {
	/**
	 * 过滤非法字符
	 * @param input	输入字符
	 * @return	过滤后字符串
	 */
	public static String htmlEscape(String input){
		return new HTMLFilter().filter(input);
	}
}
