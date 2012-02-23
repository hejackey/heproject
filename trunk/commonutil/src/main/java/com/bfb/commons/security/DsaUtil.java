package com.bfb.commons.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

public class DsaUtil {
	private static final String DSA_ARITHMETIC="DSA";
	
	/**
	 * 私钥签名
	 * @param content	明文密码
	 * @param privateKeyFile	私钥文件路径
	 * @return 签名后字符串
	 */
	public static String sign(String content,String privateKeyFile){
		 Signature signalg;
		try {
			signalg = Signature.getInstance(DSA_ARITHMETIC);
			PrivateKey privateKey = setPrivateKey(privateKeyFile);
			signalg.initSign(privateKey);  
			signalg.update(content.getBytes());  
			byte[] signature = signalg.sign();  
	         
			return SecurityUtil.byteArr2HexStr(signature);  
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}  
        
		return null;
	}
	
	/**
	 * 公钥验证签名
	 * @param sign	签名串
	 * @param content	明文密码
	 * @param publicKeyFile	公钥文件路径
	 * @return
	 */
	public static boolean vierify(String sign,String content,String publicKeyFile){
		   Signature verifyflag;
		try {
			verifyflag = Signature.getInstance(DSA_ARITHMETIC);
			PublicKey publicKey= setPublicKey(publicKeyFile);
			verifyflag.initVerify(publicKey);  
			verifyflag.update(content.getBytes());  
			
			return verifyflag.verify(SecurityUtil.hexStr2ByteArr(sign));  
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}  
           
		return false;
	}
	
	/**
	 * 获取私钥
	 * @param privateKeySrc	私钥文件路径
	 * @return	私钥
	 * @throws Exception
	 */
	private static PrivateKey setPrivateKey(String privateKeySrc) throws Exception {  
        try {  
            ObjectInputStream keyIn = new ObjectInputStream(  
                    new FileInputStream(new File(privateKeySrc)));  
            PrivateKey privateKey = (PrivateKey) keyIn.readObject();  
            keyIn.close();  
            
            return privateKey;
        } catch (Exception e) {  
            throw e;  
        }  
    }  
  
	/**
	 * 获取公钥
	 * @param publicKeySrc	公钥文件路径
	 * @return	公钥
	 * @throws Exception
	 */
    private static PublicKey setPublicKey(String publicKeySrc) throws Exception {  
        try {  
            ObjectInputStream keyIn = new ObjectInputStream(  
                    new FileInputStream(new File(publicKeySrc)));  
            PublicKey publicKey = (PublicKey) keyIn.readObject();  
            keyIn.close();  
            
            return publicKey;
        } catch (Exception e) {  
            throw e;  
        }  
    }  
	public static void main(String[] args) throws Exception {
		String t = sign("123a斯蒂芬阿asdfasdf萨德ss","D:\\keypair\\private.key");
		System.out.println(t);
		System.out.println(vierify(t,"123a斯蒂芬阿asdfasdf萨德ss","D:\\keypair\\public.key"));
		System.out.println(t.length());
		
	}
}
