package com.bfb.commons.security;



public class Md5Util {
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
			return SecurityUtil.encrypt(value, key);
		} catch (Exception e) {
			return null;
		}

	}

}
