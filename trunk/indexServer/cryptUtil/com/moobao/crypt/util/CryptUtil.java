package com.moobao.crypt.util;

import org.apache.commons.lang.StringUtils;

public class CryptUtil {
	public static String encypt(String sourceStr) {
		if (StringUtils.isNotEmpty(sourceStr)) {
			try {
				Des des = new Des();
				String enstr = des.encrypt(sourceStr);
				return enstr;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		} else {
			return null;
		}
	}
	
	public static String descypt(String encryptStr) {
		if (StringUtils.isNotEmpty(encryptStr)) {
			try {
				Des des = new Des();
				String destr = des.decrypt(encryptStr);
				return destr;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		} else {
			return null;
		}
	}
	
	
	public static void main(String[] argv){
		System.out.println(descypt("095c6843a59a5ef3"));
	}
}