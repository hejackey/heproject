package com.bfb.commons.security;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.Cipher;

/**
 * des可逆加密算法
 * @author Administrator
 *
 */
public class DesUtil {
	private static String strDefaultKey = "abzhuozhuo8f8";

	private Cipher encryptCipher = null;

	private Cipher decryptCipher = null;

	
	/**
	 * 指定密钥构造方法
	 * 
	 * @param strKey
	 *            指定的密钥
	 * @throws Exception
	 */
	private DesUtil(String strKey) throws Exception {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		Key key = getKey(strKey.getBytes());

		encryptCipher = Cipher.getInstance("DES");
		encryptCipher.init(Cipher.ENCRYPT_MODE, key);

		decryptCipher = Cipher.getInstance("DES");
		decryptCipher.init(Cipher.DECRYPT_MODE, key);
	}

	/**
	 * 加密字节数组
	 * 
	 * @param arrB
	 *            需加密的字节数组
	 * @return 加密后的字节数组
	 * @throws Exception
	 */
	private static byte[] encrypt(byte[] arrB) throws Exception {
		Cipher encryptCipher = null;
		Key key = getKey(strDefaultKey.getBytes());

		encryptCipher = Cipher.getInstance("DES");
		encryptCipher.init(Cipher.ENCRYPT_MODE, key);

		return encryptCipher.doFinal(arrB);
	}

	/**
	 * 加密字符串
	 * @param strIn 待加密字符串
	 * @return	数组，下标0是加密后的密码、密码长度144位，下标1是随机salt
	 * @throws Exception
	 */
	public static String[] encrypt(String strIn) throws Exception {
		String salt = Sha2Util.genSalt();
		String passwd = SecurityUtil.byteArr2HexStr(encrypt(Sha2Util.sha2(strIn, salt).getBytes()));
		
		return new String[]{passwd,salt};
	}
	
	/**
	 * 解密字节数组
	 * 
	 * @param arrB
	 *            需解密的字节数组
	 * @return 解密后的字节数组
	 * @throws Exception
	 */
	private static byte[] decrypt(byte[] arrB) throws Exception {
		Cipher decryptCipher = null;
		Key key = getKey(strDefaultKey.getBytes());

		decryptCipher = Cipher.getInstance("DES");
		decryptCipher.init(Cipher.DECRYPT_MODE, key);
		
		return decryptCipher.doFinal(arrB);
	}

	/**
	 * 解密字符串
	 * 
	 * @param strIn
	 *            需解密的字符串
	 * @return 解密后的字符串
	 * @throws Exception
	 */
	private static String decrypt(String strIn) throws Exception {
		return new String(decrypt(SecurityUtil.hexStr2ByteArr(strIn)));
	}

	/**
	 * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
	 * 
	 * @param arrBTmp
	 *            构成该字符串的字节数组
	 * @return 生成的密钥
	 * @throws java.lang.Exception
	 */
	private static Key getKey(byte[] arrBTmp) throws Exception {
		// 创建一个空的8位字节数组（默认值为0）
		byte[] arrB = new byte[8];

		// 将原始字节数组转换为8位
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}

		// 生成密钥
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");

		return key;
	}
	
	/**
	 * 比较密码是否正确
	 * @param passwd	明文密码
	 * @param salt	随机salt
	 * @param encryptPwd	加密后的密码
	 * @return
	 */
	public static boolean comparePass(String passwd,String salt,String encryptPwd) {
		try {
			return DesUtil.decrypt(encryptPwd).equals(Sha2Util.sha2(passwd, salt));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			
			return false;
		}
	}
}
