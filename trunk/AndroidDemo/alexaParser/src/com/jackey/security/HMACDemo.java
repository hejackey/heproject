package com.jackey.security;

import java.math.BigInteger;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class HMACDemo {
	private static final String KEY_MAC = "HmacMD5";

	/**  
     * ≥ı ºªØHMAC√‹‘ø  
     *   
     * @return  
     * @throws Exception  
     */  
    public static String initMacKey() throws Exception {   
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);   
  
        SecretKey secretKey = keyGenerator.generateKey(); 
        return Base64Demo.encryptBASE64(secretKey.getEncoded());   
    }   
  
    /**  
     * HMACº”√‹  
     *   
     * @param data  
     * @param key  
     * @return  
     * @throws Exception  
     */  
    public static byte[] encryptHMAC(byte[] data, String key) throws Exception {   
  
        SecretKey secretKey = new SecretKeySpec(Base64Demo.decryptBASE64(key), KEY_MAC);   
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());   
        mac.init(secretKey);   
  
        return mac.doFinal(data);   
  
    }   

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String  key = initMacKey();
			System.err.println("Mac√‹‘ø:\n" + key);   
			System.out.println(encryptHMAC("abcde".getBytes(),key));
			
			BigInteger mac = new BigInteger(encryptHMAC("abcde".getBytes(),key));   
	        System.err.println("HMAC:\n" + mac.toString(16)); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
