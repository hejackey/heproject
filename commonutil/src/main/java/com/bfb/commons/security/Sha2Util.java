package com.bfb.commons.security;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.bfb.commons.random.RandomUtil;
import com.bfb.commons.string.StringUtil;

/**
 * SHA2 256加密工具类
 * @author Administrator
 * @version 1.0
 * @date 2012-1-12
 */
public class Sha2Util {
	private static final int SALT_LENGHT = 8;

	/**
	 * sha2 256加密算法
	 * 
	 * @param passwd
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String sha2(String passwd,String salt) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(StringUtil.concatStr("", salt, passwd, salt).getBytes());

		byte byteData[] = md.digest();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
					.substring(1));
		}

		return sb.toString();
	}

	/**
	 * 生成随机salt
	 * 
	 * @return
	 */
	public static String genSalt() {
		return RandomUtil.generateNumCharString(SALT_LENGHT);
	}

	/**
	 * @param args
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException,
			IOException {
		String password = "123456";
		String salt = genSalt();
		String pwd = sha2(password,salt);
		System.out.println(pwd);
		System.out
				.println(pwd.length());
	}
}
