package com.bfb.commons.validator;

import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bfb.commons.string.StringUtil;

public class ValidatorUtil {
	/**
	 * 校验手机号格式
	 * @param str	手机号
	 * @return	符合格式返回true，否则返回false
	 */
	public static boolean checkMobile(String str) {
		String regEx = "^\\d{11}+$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(regEx);
		java.util.regex.Matcher m = p.matcher(str.trim());
		boolean result = m.find();
		return result;
	}
	
	/**
	 * 按指定的格式校验手机号
	 * @param str	手机号
	 * @param format	格式
	 * @return	符合返回true,否则返回false
	 */
	public static boolean checkMobile(String str,String format) {
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(format);
		java.util.regex.Matcher m = p.matcher(str.trim());
		boolean result = m.find();
		
		return result;
	}
	
	/**
	 * 验证固定电话格式
	 * @param str	固定电话
	 * @return	符合返回true，否则返回false
	 */
	public static boolean checkPhone(String str) {
		String regEx = "^\\d{2,4}\\-\\d{7,8}+$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(regEx);
		java.util.regex.Matcher m = p.matcher(str.trim());
		boolean result = m.find();
		return result;
	}
	
	/**
	 * 按指定格式验证固定电话
	 * @param str	固定电话
	 * @param format	格式
	 * @return	符合返回true，否则返回false
	 */
	public static boolean checkPhone(String str,String format) {
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(format);
		java.util.regex.Matcher m = p.matcher(str.trim());
		boolean result = m.find();
		
		return result;
	}
	
	/**
	 * 验证邮编的格式
	 * @param str	邮编
	 * @return	符合返回true，否则返回false
	 */
	public static boolean checkCode(String str) {
		String regEx = "^\\d{6,7}+$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(regEx);
		java.util.regex.Matcher m = p.matcher(str.trim());
		boolean result = m.find();
		return result;
	}
	
	/**
	 * 校验email格式
	 * @param str	email
	 * @return	符合格式返回true，否则返回false
	 */
	public static boolean checkEmail(String str) {
		String regEx = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(regEx);
		java.util.regex.Matcher m = p.matcher(str.trim());
		boolean result = m.find();
		return result;
	}
	
	/**
	 * 按指定格式校验email
	 * @param str	email
	 * @return	符合格式返回true，否则返回false
	 * @return
	 */
	public static boolean checkEmail(String str,String format) {
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(format);
		java.util.regex.Matcher m = p.matcher(str.trim());
		boolean result = m.find();
		return result;
	}
	
	/**
	 * 判断字符串是否大于指定的长度，中文算2个字符长度
	 * @param val	字符串
	 * @param len	指定的长度
	 * @return	大于返回true，否则false
	 * @throws UnsupportedEncodingException
	 */
	public static boolean isGreaterLen(String val,int len) throws UnsupportedEncodingException{
		if(StringUtil.isEmpty(val))
			return false;
		
		if(val.getBytes("gbk").length>len)
			return true;
		else
			return false;
	}
	
	/**
	 * 判断字符串是否小于指定的长度，中文算2个字符长度
	 * @param val	字符串
	 * @param len	指定的长度
	 * @return	小于返回true，否则false
	 * @throws UnsupportedEncodingException
	 */
	public static boolean isLessLen(String val,int len) throws UnsupportedEncodingException{
		if(StringUtil.isEmpty(val))
			return false;
		
		if(val.getBytes("gbk").length<len)
			return true;
		else
			return false;
	}

	/**
	 * 校验是否数字字母组合
	 * @param val	字符串
	 * @return	数字字母组合返回true，否则false
	 */
	public static boolean isNumAndChar(String val){
		String dreg = "[0-9]+$";
        String creg="[a-zA-Z]+$";
        if(StringUtil.isEmpty(val))
        	return false;
        
        if(val.matches(dreg)|| val.matches(creg))
        	return false;
        
        return true;
	}
	
	/**
	 * 
	*    
	* 方法描述： 验证是否是数字，包含小数 
	* 创建人：hexiaoliang  
	* 创建时间：2010-3-25 下午05:29:39    
	* @param str	数字
	* @return	小于0的或非数字型返回false，否则true
	*
	 */
	public static boolean isNumeric(String str){
		String pattern = "[0-9]+(.[0-9]+)?";  
		Pattern p = Pattern.compile(pattern);  
		Matcher m = p.matcher(str);  
		
		boolean flag =true;	
		if(str.indexOf(".")==-1&&"0".equals(String.valueOf(str.charAt(0))))
			flag=false;
		
		return  m.matches()&&flag; 
	} 
	
	/**
	 * 
	*    
	* 方法描述： 验证是否是大于0的正整数 
	* 创建人：hexiaoliang  
	* 创建时间：2010-3-26 下午03:51:53    
	* @param str 正整数
	* @return	是返回true，否则false
	*
	 */
	public static boolean isInteger(String str){
		String pattern = "[0-9]+?";  
		Pattern p = Pattern.compile(pattern);  
		Matcher m = p.matcher(str);  
		
		boolean flag =true;	
		if(str.indexOf(".")==-1&&"0".equals(String.valueOf(str.charAt(0)))
				)
			flag=false;
		
		return  m.matches()&&flag; 
	}
	
	/**
	 * 
	 *    
	 * 方法描述：验证ip地址合法性  
	 * 创建人：hexiaoliang  
	 * 创建时间：2010-4-9 下午03:47:04    
	 * @param str ip地址
	 * @return 合法返回true，否则false
	 *
	 */
	public static boolean validateIp(String str){
		if(!str.matches("\\d+\\.\\d+\\.\\d+\\.\\d+")){
			return false;
		}
		else{
			StringTokenizer st=new StringTokenizer(str,".");
			int ip=0;
			while( st.hasMoreElements() ){
				ip = Integer.valueOf(st.nextToken());
				if(ip<0||ip>255){
					
					return false;
				}
			}

			return true;
		}
	}
	
	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(checkMobile("32213123123"));
		System.out.println(checkMobile("18322112","^1[3,4,5,8]\\d{6}+$"));//13,14,15,18开头都符合
		System.out.println(checkPhone("321-1313123"));
		System.out.println(checkCode("32131"));
		
		System.out.println(checkEmail("3_.2-13asdasdasd1@lkjasdfsfadsfs.coaasdfm"));
		System.out.println(isGreaterLen("asd",4));
		System.out.println(isLessLen("assd",4));
		
		System.out.println(isNumAndChar("asdsf"));
		System.out.println(ValidateIDCard.validate("321231298209011214"));
		System.out.println(isInteger("0"));
	}

}
