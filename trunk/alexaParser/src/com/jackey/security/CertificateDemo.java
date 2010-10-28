package com.jackey.security;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import java.util.Date;


import javax.crypto.Cipher;

/**
 * 
1.����keyStroe�ļ� 
����������ִ��������� 
Shell���� 
keytool -genkey -validity 36000 -alias www.zlex.org -keyalg RSA -keystore d:\zlex.keystore  

���� 
-genkey��ʾ������Կ 
-validityָ��֤����Ч�ڣ�������36000�� 
-aliasָ��������������www.zlex.org 
-keyalgָ���㷨��������RSA 
-keystoreָ���洢λ�ã�������d:\zlex.keystore 

��������ʹ�õ�����Ϊ 123456


2.������ǩ��֤�� 
����keyStore�ļ��ǲ����ģ�����Ҫ֤���ļ���֤�����ֱ���ṩ�����ʹ�õĹ�Կƾ֤�� 
����֤�飺 
Shell���� 
keytool -export -keystore d:\zlex.keystore -alias www.zlex.org -file d:\zlex.cer -rfc  

keytool -export -keystore d:\zlex.keystore -alias www.zlex.org -file d:\zlex.cer -rfc

���� 
-exportָ��Ϊ�������� 
-keystoreָ��keystore�ļ� 
-aliasָ������keystore�ļ��еı��� 
-fileָ�򵼳�·�� 
-rfc���ı���ʽ�����Ҳ������BASE64������� 
����������� 123456 


 * @author Administrator
 *
 */
public class CertificateDemo {
	/**  
     * Java��Կ��(Java Key Store��JKS)KEY_STORE  
     */  
    public static final String KEY_STORE = "JKS";   
  
    public static final String X509 = "X.509";   
  
    /**  
     * ��KeyStore���˽Կ  
     *   
     * @param keyStorePath  
     * @param alias  
     * @param password  
     * @return  
     * @throws Exception  
     */  
    private static PrivateKey getPrivateKey(String keyStorePath, String alias,   
            String password) throws Exception {   
        KeyStore ks = getKeyStore(keyStorePath, password);   
        PrivateKey key = (PrivateKey) ks.getKey(alias, password.toCharArray());   
        return key;   
    }   
  
    /**  
     * ��Certificate��ù�Կ  
     *   
     * @param certificatePath  
     * @return  
     * @throws Exception  
     */  
    private static PublicKey getPublicKey(String certificatePath)   
            throws Exception {   
        Certificate certificate = getCertificate(certificatePath);   
        PublicKey key = certificate.getPublicKey();   
        return key;   
    }   
  
    /**  
     * ���Certificate  
     *   
     * @param certificatePath  
     * @return  
     * @throws Exception  
     */  
    private static Certificate getCertificate(String certificatePath)   
            throws Exception {   
        CertificateFactory certificateFactory = CertificateFactory   
                .getInstance(X509);   
        System.out.println(certificateFactory.getProvider());
        FileInputStream in = new FileInputStream(certificatePath);   
  
        Certificate certificate = certificateFactory.generateCertificate(in);   
        System.out.println(certificate.getType());
        in.close();   
  
        return certificate;   
    }   
  
    /**  
     * ���Certificate  
     *   
     * @param keyStorePath  
     * @param alias  
     * @param password  
     * @return  
     * @throws Exception  
     */  
    private static Certificate getCertificate(String keyStorePath,   
            String alias, String password) throws Exception {   
        KeyStore ks = getKeyStore(keyStorePath, password);   
        Certificate certificate = ks.getCertificate(alias);   
  
        return certificate;   
    }   
  
    /**  
     * ���KeyStore  
     *   
     * @param keyStorePath  
     * @param password  
     * @return  
     * @throws Exception  
     */  
    private static KeyStore getKeyStore(String keyStorePath, String password)   
            throws Exception {   
        FileInputStream is = new FileInputStream(keyStorePath);   
        KeyStore ks = KeyStore.getInstance(KEY_STORE);   
        ks.load(is, password.toCharArray());   
        is.close();   
        return ks;   
    }   
  
    /**  
     * ˽Կ����  
     *   
     * @param data  
     * @param keyStorePath  
     * @param alias  
     * @param password  
     * @return  
     * @throws Exception  
     */  
    public static byte[] encryptByPrivateKey(byte[] data, String keyStorePath,   
            String alias, String password) throws Exception {   
        // ȡ��˽Կ   
        PrivateKey privateKey = getPrivateKey(keyStorePath, alias, password);   
  
        // �����ݼ���   
        Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());   
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);   
  
        return cipher.doFinal(data);   
  
    }   
  
    /**  
     * ˽Կ����  
     *   
     * @param data  
     * @param keyStorePath  
     * @param alias  
     * @param password  
     * @return  
     * @throws Exception  
     */  
    public static byte[] decryptByPrivateKey(byte[] data, String keyStorePath,   
            String alias, String password) throws Exception {   
        // ȡ��˽Կ   
        PrivateKey privateKey = getPrivateKey(keyStorePath, alias, password);   
  
        // �����ݼ���   
        Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());   
        cipher.init(Cipher.DECRYPT_MODE, privateKey);   
  
        return cipher.doFinal(data);   
  
    }   
  
    /**  
     * ��Կ����  
     *   
     * @param data  
     * @param certificatePath  
     * @return  
     * @throws Exception  
     */  
    public static byte[] encryptByPublicKey(byte[] data, String certificatePath)   
            throws Exception {   
  
        // ȡ�ù�Կ   
        PublicKey publicKey = getPublicKey(certificatePath);   
        // �����ݼ���   
        Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());   
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);   
  
        return cipher.doFinal(data);   
  
    }   
  
    /**  
     * ��Կ����  
     *   
     * @param data  
     * @param certificatePath  
     * @return  
     * @throws Exception  
     */  
    public static byte[] decryptByPublicKey(byte[] data, String certificatePath)   
            throws Exception {   
        // ȡ�ù�Կ   
        PublicKey publicKey = getPublicKey(certificatePath);   
  
        // �����ݼ���   
        Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());   
        cipher.init(Cipher.DECRYPT_MODE, publicKey);   
  
        return cipher.doFinal(data);   
  
    }   
  
    /**  
     * ��֤Certificate  
     *   
     * @param certificatePath  
     * @return  
     */  
    public static boolean verifyCertificate(String certificatePath) {   
        return verifyCertificate(new Date(), certificatePath);   
    }   
  
    /**  
     * ��֤Certificate�Ƿ���ڻ���Ч  
     *   
     * @param date  
     * @param certificatePath  
     * @return  
     */  
    public static boolean verifyCertificate(Date date, String certificatePath) {   
        boolean status = true;   
        try {   
            // ȡ��֤��   
            Certificate certificate = getCertificate(certificatePath);   
            // ��֤֤���Ƿ���ڻ���Ч   
            status = verifyCertificate(date, certificate);   
        } catch (Exception e) {   
            status = false;   
        }   
        return status;   
    }   
  
    /**  
     * ��֤֤���Ƿ���ڻ���Ч  
     *   
     * @param date  
     * @param certificate  
     * @return  
     */  
    private static boolean verifyCertificate(Date date, Certificate certificate) {   
        boolean status = true;   
        try {   
            X509Certificate x509Certificate = (X509Certificate) certificate;   
            x509Certificate.checkValidity(date);   
        } catch (Exception e) {   
            status = false;   
        }   
        return status;   
    }   
  
    /**  
     * ǩ��  
     *   
     * @param keyStorePath  
     * @param alias  
     * @param password  
     *   
     * @return  
     * @throws Exception  
     */  
    public static String sign(byte[] sign, String keyStorePath, String alias,   
            String password) throws Exception {   
        // ���֤��   
        X509Certificate x509Certificate = (X509Certificate) getCertificate(   
                keyStorePath, alias, password);   
        // ��ȡ˽Կ   
        KeyStore ks = getKeyStore(keyStorePath, password);   
        // ȡ��˽Կ   
        PrivateKey privateKey = (PrivateKey) ks.getKey(alias, password   
                .toCharArray());   
  
        // ����ǩ��   
        Signature signature = Signature.getInstance(x509Certificate   
                .getSigAlgName());   
        signature.initSign(privateKey);   
        signature.update(sign);   
        return Base64Demo.encryptBASE64(signature.sign());   
    }   
  
    /**  
     * ��֤ǩ��  
     *   
     * @param data  
     * @param sign  
     * @param certificatePath  
     * @return  
     * @throws Exception  
     */  
    public static boolean verify(byte[] data, String sign,   
            String certificatePath) throws Exception {   
        // ���֤��   
        X509Certificate x509Certificate = (X509Certificate) getCertificate(certificatePath);   
        // ��ù�Կ   
        PublicKey publicKey = x509Certificate.getPublicKey();   
        // ����ǩ��   
        Signature signature = Signature.getInstance(x509Certificate   
                .getSigAlgName());   
        signature.initVerify(publicKey);   
        signature.update(data);   
  
        return signature.verify(Base64Demo.decryptBASE64(sign));   
  
    }   
  
    /**  
     * ��֤Certificate  
     *   
     * @param keyStorePath  
     * @param alias  
     * @param password  
     * @return  
     */  
    public static boolean verifyCertificate(Date date, String keyStorePath,   
            String alias, String password) {   
        boolean status = true;   
        try {   
            Certificate certificate = getCertificate(keyStorePath, alias,   
                    password);   
            status = verifyCertificate(date, certificate);   
        } catch (Exception e) {   
            status = false;   
        }   
        return status;   
    }   
  
    /**  
     * ��֤Certificate  
     *   
     * @param keyStorePath  
     * @param alias  
     * @param password  
     * @return  
     */  
    public static boolean verifyCertificate(String keyStorePath, String alias,   
            String password) {   
        return verifyCertificate(new Date(), keyStorePath, alias, password);   
    }  
    
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String password = "123456";   
	    String alias = "www.jackeyhe.com";   
	    String certificatePath = "f:/jackey_he.cer";   
	    String keyStorePath = "f:/jackey_he.keystore";   
	    
	    System.err.println("��Կ���ܡ���˽Կ����");   
        String inputStr = "Ceritifcate";   
        byte[] data = inputStr.getBytes();   
  
        byte[] encrypt = CertificateDemo.encryptByPublicKey(data,   
                certificatePath);   
  
        byte[] decrypt = CertificateDemo.decryptByPrivateKey(encrypt,   
                keyStorePath, alias, password);   
        String outputStr = new String(decrypt);   
  
        System.err.println("����ǰ: " + inputStr + "\n\r" + "���ܺ�: " + outputStr);  
        
        /*System.err.println("˽Կ���ܡ�����Կ����");   
        
        inputStr = "sign";   
        data = inputStr.getBytes();   
  
        byte[] encodedData = CertificateDemo.encryptByPrivateKey(data,   
                keyStorePath, alias, password);   
  
        byte[] decodedData = CertificateDemo.decryptByPublicKey(encodedData,   
                certificatePath);   
  
        outputStr = new String(decodedData);   
        System.err.println("����ǰ: " + inputStr + "\n\r" + "���ܺ�: " + outputStr);   
        
        System.err.println("˽Կǩ��������Կ��֤ǩ��");   
        // ����ǩ��   
        encodedData="abc".getBytes();
        String sign = CertificateDemo.sign(encodedData, keyStorePath, alias,   
                password);   
        System.err.println("ǩ��:\r" + sign);   
  
        // ��֤ǩ��   
       
        boolean status = CertificateDemo.verify(encodedData, sign,   
                certificatePath);   
        System.err.println("״̬:\r" + status);   
*/
	}

}
