package com.bfb.commons.security;


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
	 *//*
	public static String encrypt(String src) throws Exception {
		DesUtil des = new DesUtil();
		return byteArr2HexStr(des.encrypt(src.getBytes()));
	}

	*//**
	 * DES解密方法,与encrypt互逆
	 * 
	 * @param target
	 *            待解密串
	 * @return 返回解密的串
	 * @throws Exception
	 *//*
	public static String decrypt(String target) throws Exception {
		DesUtil des = new DesUtil();
		return des.decrypt(target);
	}*/

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
	public static String byteArr2HexStr(byte[] arrB) throws Exception {
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
	public static byte[] hexStr2ByteArr(String strIn) throws Exception {
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

	public static void main(String[] args) throws Exception {
		
	}
}
