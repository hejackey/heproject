package com.jackey.security;

import java.security.MessageDigest;

public class SHADemo {
	 private static final String KEY_SHA = "SHA";

	/**  
     * SHAº”√‹  
     *   
     * @param data  
     * @return  
     * @throws Exception  
     */  
    public static byte[] encryptSHA(byte[] data) throws Exception {   
  
        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);   
        sha.update(data);   
  
        return sha.digest();   
  
    }  
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			System.out.println(encryptSHA("abcde".getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
