package com.zhuozhuo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegx {
	public static void main(String[] args) {
//		Pattern p = Pattern.compile("f(.+?)k");
//		Matcher m = p.matcher("fckfkkfkf");
//		while (m.find()) {
//			String s0 = m.group();
//			String s1 = m.group(1);
//			System.out.println(s0 + "||" + s1);
//		}
//		System.out.println("---------");
//		m.reset("fucking!");
//		while (m.find()) {
//			System.out.println(m.group());
//		}
//
//		Pattern p1 = Pattern.compile("f(.+?)i(.+?)h");
//		Matcher m1 = p1.matcher("finishabigfishfrish");
//		while (m1.find()) {
//
//			String s0 = m1.group();
//			String s1 = m1.group(1);
//			String s2 = m1.group(2);
//			System.out.println(s0 + "||" + s1 + "||" + s2);
//		}
//
//		System.out.println("---------");
//		Pattern p3 = Pattern.compile("(19|20)\\d\\d([- /.])(0[1-9]|1[012])\\2(0[1-9]|[12][0-9]|3[01])");
//		Matcher m3 = p3.matcher("1900-01-01 2007/08/13 1900.01.01 1900 01 01 1900-01.01 1900 13 01 1900 02 31");
//		while (m3.find()) {
//			System.out.println(m3.group());
//		}
		
		//String str="eponline@yahoo.com.cn";
//		String str="";
//		Pattern pattern = Pattern.compile("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+",Pattern.CASE_INSENSITIVE);
//		Matcher matcher = pattern.matcher(str);
//		System.out.println(matcher.matches());
		
//		//截取url
//		Pattern pattern = Pattern.compile("(http://|https://){1}[\\w\\.\\-/:]+");
//		Matcher matcher = pattern.matcher("dsdsds<http://dsds//gfgffdfd>fdf");
//		StringBuffer buffer = new StringBuffer();
//		while(matcher.find()){              
//		    buffer.append(matcher.group());        
//		    buffer.append("\r\n");              
//		System.out.println(buffer.toString());
//		}
		
//		//去除html标记
//		Pattern pattern = Pattern.compile("<.+?>", Pattern.DOTALL);
//		Matcher matcher = pattern.matcher("<a href=\"index.html\">主页</a>");
//		String string = matcher.replaceAll("");
//		System.out.println(string);
		
		
		//查找html中对应条件字符串
		Pattern pattern = Pattern.compile("href=\"(.+?)\"");
		Matcher matcher = pattern.matcher("<a href=\"index.html\">主页</a>");
		if(matcher.find())
		  System.out.println(matcher.group(1));
		
		
//		String str = "Java目前的发展史是由{0}年-{1}年";
//		String[][] object={new String[]{"\\{0\\}","1995"},new String[]{"\\{1\\}","2007"}};
//		System.out.println(replace(str,object));		
	}
	
	public static String replace(final String sourceString,Object[] object) {
        String temp=sourceString;    
        for(int i=0;i<object.length;i++){
                  String[] result=(String[])object[i];
           Pattern    pattern = Pattern.compile(result[0]);
           Matcher matcher = pattern.matcher(temp);
           temp=matcher.replaceAll(result[1]);
        }
        return temp;
	}
}

