package com.bfb.commons.security;

import java.security.NoSuchAlgorithmException;

import com.bfb.commons.random.RandomUtil;


/**
 * 安全工具类
 * 
 * @author Administrator
 * 
 */
public class SecurityUtil {
	private static final int SALT_LENGHT = 8;
	
	/**
	 * 生成随机salt
	 * 
	 * @return
	 */
	public static String genSalt() {
		return RandomUtil.generateNumCharString(SALT_LENGHT);
	}


	/**
	 * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[]
	 * hexStr2ByteArr(String strIn) 互为可逆的转换过程
	 * 
	 * @param arrB
	 *            需要转换的byte数组
	 * @return 转换后的字符串
	 * @throws Exception
	 *             本方法不处理任何异常，所有异常全部抛出
	 */
	protected static String byteArr2HexStr(byte[] arrB) throws Exception {
		String result="";
		for (int i = 0; i < arrB.length; i++) {
			result += Integer.toHexString((0x000000FF & arrB[i]) | 0xFFFFFF00)
					.substring(6);
		}
		return result;
	}

	/**
	 * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB)
	 * 互为可逆的转换过程
	 * 
	 * @param strIn
	 *            需要转换的字符串
	 * @return 转换后的byte数组
	 * @throws Exception
	 *             本方法不处理任何异常，所有异常全部抛出
	 * @author <a href="mailto:leo841001@163.com">LiGuoQing</a>
	 */
	protected static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;

		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}
	
	/**
	 * des对字符串加密
	 * @param strIn	待加密字符串
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String strIn) throws Exception {
		return byteArr2HexStr(DesUtil.encrypt(strIn.getBytes()));
	}
	
	/**
	 * des解密
	 * @param strIn	待解密字符串
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String strIn) throws Exception {
		return new String(DesUtil.decrypt(hexStr2ByteArr(strIn)));
	}
	
	/**
	 * 对指定的salt和password加密
	 * @param strIn	明文密码
	 * @param salt	随机salt
	 * @return	加密后的密码
	 * @throws Exception
	 */
	protected static String encrypt(String strIn,String salt) throws Exception {
		return byteArr2HexStr(DesUtil.encrypt(Sha2Util.sha2(strIn, salt).getBytes()));
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
	
	public static void main(String[] args){
		String salt = genSalt();
		System.out.println(salt);
		try {
			String pwd = encrypt("aaa111",salt);
			System.out.println(pwd);
			System.out.println(encrypt("wang_12li@143.com"));
			System.out.println(decrypt("0a0ccdd6bfb2521ebeb8897852ebf402bec6c5f6d089410b"));
			System.out.println(comparePass("aaa111",salt,pwd));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
