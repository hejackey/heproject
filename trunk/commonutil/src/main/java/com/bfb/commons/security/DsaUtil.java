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
	@SuppressWarnings("unused")
	private static PrivateKey privateKey;
	@SuppressWarnings("unused")
	private static PublicKey publicKey;
	
	public static String sign(String content,PrivateKey privateKey){
		 Signature signalg;
		try {
			signalg = Signature.getInstance(DSA_ARITHMETIC);
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
	
	public static boolean vierify(String sign,String content,PublicKey publicKey){
		   Signature verifyflag;
		try {
			verifyflag = Signature.getInstance(DSA_ARITHMETIC);
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
	
	public static void setPrivateKey(String privateKeySrc) throws Exception {  
        try {  
            ObjectInputStream keyIn = new ObjectInputStream(  
                    new FileInputStream(new File(privateKeySrc)));  
            privateKey = (PrivateKey) keyIn.readObject();  
            keyIn.close();  
        } catch (Exception e) {  
            throw e;  
        }  
    }  
  
    public static void setPublicKey(String publicKeySrc) throws Exception {  
        try {  
            ObjectInputStream keyIn = new ObjectInputStream(  
                    new FileInputStream(new File(publicKeySrc)));  
            publicKey = (PublicKey) keyIn.readObject();  
            keyIn.close();  
        } catch (Exception e) {  
            throw e;  
        }  
    }  
	public static void main(String[] args) throws Exception {
		setPublicKey("D:\\keypair\\public.key");
		setPrivateKey("D:\\keypair\\private.key");
		String t = sign("123",privateKey);
		System.out.println(t);
		System.out.println(vierify(t,"1223",publicKey));
		
	}
}
