package com.bfb.commons.security;

import java.security.MessageDigest;

/**
 * 安全工具类
 * 
 * @author Administrator
 * 
 */
public class SecurityUtil {
	/**
	 * DES加密方法，与decrypt互逆
	 * 
	 * @param src
	 *            待加密串
	 * @return 返回加密的串
	 * @throws Exception
	 */
	public static String encrypt(String src) throws Exception {
		DesReversible des = new DesReversible();
		return des.encrypt(src);
	}

	/**
	 * DES解密方法,与encrypt互逆
	 * 
	 * @param target
	 *            待解密串
	 * @return 返回解密的串
	 * @throws Exception
	 */
	public static String decrypt(String target) throws Exception {
		DesReversible des = new DesReversible();
		return des.decrypt(target);
	}

	/**
	 * md5加密算法
	 * 
	 * @param value
	 *            欲使用md5算法加密的字符串
	 * @param key 附加的增强密码复杂度的key     
	 * @return String 已经使用md5算法加密后的字符串
	 */
	public static String MD5(String value,String key) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			StringBuilder sb = new StringBuilder();
			sb.append(value);
			sb.append(key);
			
			md.update(sb.toString().getBytes("UTF8"));
			byte s[] = md.digest();
			String result = "";
			for (int i = 0; i < s.length; i++) {
				result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00)
						.substring(6);
			}
			return result;
		} catch (Exception e) {
			return null;
		}

	}

	public static void main(String[] args) throws Exception {
		System.out.println(SecurityUtil.encrypt("zhuozhuo"));
		System.out.println(SecurityUtil.decrypt(SecurityUtil
				.encrypt("zhuozhuo")));
		System.out.println(MD5("zhuozhuo","8f8apikey"));
	}
}
