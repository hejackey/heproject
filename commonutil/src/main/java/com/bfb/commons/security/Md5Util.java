package com.bfb.commons.security;

import java.security.MessageDigest;

import org.apache.commons.lang.StringUtils;

public class Md5Util {
	private static final String DEFAULT_KEY="20zhuozhuo8f811"; 
	/**
	 * md5加密算法
	 * 
	 * @param value
	 *            欲使用md5算法加密的字符串
	 * @param key 附加的增强密码复杂度的key,可为空
	 * @return String 加密后的字符串
	 */
	public static String MD5(String value,String key) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			StringBuilder sb = new StringBuilder();
			sb.append(value);
			if(StringUtils.isEmpty(key)){
				key = DEFAULT_KEY;
			}
			sb.append(key);
			
			md.update(sb.toString().getBytes("UTF8"));
			byte s[] = md.digest();
			
			return SecurityUtil.byteArr2HexStr(s);
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		System.out.println(MD5("zhuozhuo","8f8apikey"));
	}

}
