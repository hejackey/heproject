package com.jackey.security;

import java.security.MessageDigest;

public class MD5Demo {

	private static final String KEY_MD5 = "md5";

	public static byte[] encryptMD5(byte[] data) throws Exception {   
		  
	    MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);   
	    md5.update(data);   
	  
	    return md5.digest();   
	  
	}  
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			System.out.println(encryptMD5("abcdefd".getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
