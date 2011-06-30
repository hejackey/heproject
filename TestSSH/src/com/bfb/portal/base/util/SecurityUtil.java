package com.bfb.portal.base.util;
/**
 * 安全工具类
 * @author Administrator
 *
 */
public class SecurityUtil {
	/**
	 * 加密方法，与decrypt互逆
	 * @param src 待加密串 
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String src) throws Exception{
		DesReversible des = new DesReversible();
		return des.encrypt(src);
	}
	
	/**
	 * 解密方法,与encrypt互逆
	 * @param target
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String target) throws Exception{
		DesReversible des = new DesReversible();
		return des.decrypt(target);
	}
	
	public static void main(String[] args) throws Exception{
		System.out.println(SecurityUtil.encrypt("zhuozhuo"));
		System.out.println(SecurityUtil.decrypt(SecurityUtil.encrypt("zhuozhuo")));
	}
}
