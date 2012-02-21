package com.bfb.commons.security;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class DsaKeyPaireGenerator {
	private static final String DSA_ARITHMETIC="DSA";
	private static final int KEYSIZE=512; 
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		genKeyPair("d:\\keypair\\public.key","d:\\keypair\\private.key");
	}

	public static void genKeyPair(String publicKey,String privateKey){
		try {
			KeyPairGenerator pairgen = KeyPairGenerator.getInstance(DSA_ARITHMETIC);
			SecureRandom random=new SecureRandom();  
            pairgen.initialize(KEYSIZE, random);  
            KeyPair keyPair=pairgen.generateKeyPair();  
              
            ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(publicKey));  
            out.writeObject(keyPair.getPublic());  
            out.close();  
              
            out=new ObjectOutputStream(new FileOutputStream(privateKey));  
            out.writeObject(keyPair.getPrivate());  
            out.close();  
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
