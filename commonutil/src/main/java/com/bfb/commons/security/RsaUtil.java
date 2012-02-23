package com.bfb.commons.security;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RsaUtil {

	/**
	 * 生成密钥对
	 * @return
	 */
	public static KeyPair genRsaKeyPair(){
		try {
			KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
			gen.initialize(1024);

			return gen.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			
			return null;
		}
	}
	
	/**
	 * rsa用公钥加密
	 * @param pubKey 公钥
	 * @param data	待加密数据
	 * @return 加密数据
	 */
	public static String rsaEncry(PublicKey pubKey,String data){
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, pubKey);//指定加密模式
			byte[] bytes = cipher.doFinal(data.getBytes());
			
			return SecurityUtil.byteArr2HexStr(bytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * rsa用私钥加密
	 * @param priKey 私钥
	 * @param data	待加密数据
	 * @return 加密数据
	 */
	public static String rsaEncry(PrivateKey priKey,String data){
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, priKey);//指定加密模式
			byte[] bytes = cipher.doFinal(data.getBytes());
			
			return SecurityUtil.byteArr2HexStr(bytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * rsa私钥解密
	 * @param priKey	私钥
	 * @param data	待解密数据
	 * @return	解密后数据
	 */
	public static String rsaDecry(PrivateKey priKey,String data){
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE,priKey);
			byte[] src = cipher.doFinal(SecurityUtil.hexStr2ByteArr(data));
			
			return new String(src);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * rsa公钥解密
	 * @param pubKey	公钥
	 * @param data	待解密数据
	 * @return	解密后数据
	 */
	public static String rsaDecry(PublicKey pubKey,String data){
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE,pubKey);
			byte[] src = cipher.doFinal(SecurityUtil.hexStr2ByteArr(data));
			
			return new String(src);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			
			KeyPair pair = genRsaKeyPair();
			PrivateKey priKey = pair.getPrivate();
			PublicKey pubkey = pair.getPublic();
			String pwd = rsaEncry(priKey,"123adfbc324123");
			
			System.out.println(pwd);
			
			System.out.println(rsaDecry(pubkey,pwd));
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

}
