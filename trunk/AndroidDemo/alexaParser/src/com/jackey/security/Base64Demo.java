package com.jackey.security;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class Base64Demo {

	public static byte[] decryptBase64(String key) throws IOException{
		return (new BASE64Decoder()).decodeBuffer(key);
	}
	
	public static String encryptBase64(byte[] key){
		return (new BASE64Encoder()).encodeBuffer(key);
	}
	
	/**  
	 * BASE64����  
	 *   
	 * @param key  
	 * @return  
	 * @throws Exception  
	 */  
	public static byte[] decryptBASE64(String key) throws Exception {   
	    return (new BASE64Decoder()).decodeBuffer(key);   
	}   
	  
	/**  
	 * BASE64����  
	 *   
	 * @param key  
	 * @return  
	 * @throws Exception  
	 */  
	public static String encryptBASE64(byte[] key) throws Exception {   
	    return (new BASE64Encoder()).encodeBuffer(key);   
	}  

	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		try {
			System.out.println(encryptBASE64("abcde".getBytes()));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		try {
			System.out.println(new String(decryptBASE64("YWJjZGU=")));
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		String inputStr = "�򵥼���";
		System.err.println("ԭ��:\n" + inputStr);

		byte[] inputData = inputStr.getBytes();
		String code = encryptBASE64(inputData);

		System.err.println("BASE64���ܺ�:\n" + code);

		byte[] output = decryptBASE64(code);

		String outputStr = new String(output);

		System.err.println("BASE64���ܺ�:\n" + outputStr);

		// ��֤BASE64���ܽ���һ����
		//assertEquals(inputStr, outputStr);   

	}

}
