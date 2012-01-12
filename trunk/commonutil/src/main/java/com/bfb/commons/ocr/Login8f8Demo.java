package com.bfb.commons.ocr;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.apache.commons.io.FileUtils;


/**
 * 控制台模拟form表单登录，需要手工输入验证码
 * @author Administrator
 * @version 1.0
 * @date 2012-1-11
 */
public class Login8f8Demo {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//
		File file = HttpUtils.doGetFile("http://10.10.223.218/8f8portal/vcode/?veriCodeKey=userLoginCode", null);
		File codeFile = new File("d:\\8f8login.jpg");
		if(!codeFile.exists())
			codeFile.createNewFile();
		FileUtils.copyFile(file, codeFile);
		
		String code = readString("输入验证码");
		String username="hexiaoliang@izhuozhuo.com";
		String pwd="aaa111";
		
		String postParams = "userNo="+username+"&loginPass="+pwd+"&veriCode="+code;
		String body = HttpUtils.doPostBody("http://10.10.223.218/8f8portal/userLogin/userLogin.action", postParams, null, null, true);
		System.out.println(body);
	}

	private static String readString(String msg) throws Exception{
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		try{
			System.out.print(msg+": ");
			return bufferedReader.readLine();
		}catch(Exception e){
		}
		return "1245";
	}
}
