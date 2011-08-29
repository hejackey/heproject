package com.bfb.commons.random;

import java.util.Random;

/**
 * 产生随机数公共类
 * @author Administrator
 *
 */
public class RandomUtil
{
	/**
	 * 产生指定长度的随机数字串
	 * @param length	指定长度
	 * @return	随机的数字串
	 */
	public static String generateNumString(int length) { 
    	String numberChar="0123456789";
    	
        StringBuffer sb = new StringBuffer(); 
        Random random = new Random(); 
        for (int i = 0; i < length; i++) { 
                sb.append(numberChar.charAt(random.nextInt(numberChar.length()))); 
        } 
        return sb.toString(); 
    }
    
	/**
	 * 产生指定长度的随机字符数字串
	 * @param length	指定长度
	 * @return	返回随机产生的串
	 */
    public static String generateNumCharString(int length) { 
    	String numberChar="23456789";
    	String charChar="abcdefghijkmnpqrstuvwxyz";
    	
        StringBuffer sb = new StringBuffer(); 
        Random random = new Random(); 
        for (int i = 0; i < length; i++) { 
        		if(i%2==0)
        			sb.append(numberChar.charAt(random.nextInt(numberChar.length())));
        		else
        			sb.append(charChar.charAt(random.nextInt(charChar.length())));
        } 
        return sb.toString(); 
    }
}
