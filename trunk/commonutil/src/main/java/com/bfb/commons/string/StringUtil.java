package com.bfb.commons.string;

public class StringUtil {
	/**
	 * 判断字符串是否为空
	 * @param val	待判断的字符串
	 * @return	空返回true，不空返回false
	 */
	public static boolean isEmpty(String val){
		return val == null || val.trim().length()==0?true:false;
	}
	
	/**
	 * 判断2个字符串是否相等
	 * @param str1	字符串1
	 * @param str2	字符串2
	 * @return	相等返回true，不等返回false
	 */
	public static boolean equals(String str1, String str2){
		if(!isEmpty(str1) && !isEmpty(str2))
			return str1.equals(str2);
		else if(isEmpty(str1) && isEmpty(str2))
			return true;
		else
			return false;
	}
	
	/**
	 * 转换html的标签
	 * @param s	待转换的字符串
	 * @return	转换后的字符串
	 */
	public static String toHtmlBack(String s)
	{
		s = s.replace( "&", "&amp;");
		s = s.replace( "\\", "&#92;");
		s = s.replace( "\"", "&quot;");
		s = s.replace( "<", "&lt;");
		s = s.replace( ">", "&gt;");
		s = s.replace( "\t", "    ");
		s = s.replace( "\r\n", "\n");
		s = s.replace( "\n", "<br>");
		s = s.replace( " ", "&nbsp;");
		s = s.replace( "'", "&#39;");
		s = s.replace( "%", "%");

		return s;
	}

	/**
	 * html标签逆向转换	
	 * @param s	待转换的标签
	 * @return	转换后的字符串
	 */
	public static String unHtmlBack(String s)
	{
		s = s.replace( "&lt;", "<");
		s = s.replace( "&gt;", ">");
		s = s.replace( "    ", "\t");
		s = s.replace( "\n", "\r\n");
		s = s.replace( "<br>", "\n");
		s = s.replace( "&nbsp;", " ");
		s = s.replace( "&amp;", "&");
		s = s.replace( "&#39;", "'");
		s = s.replace( "&#92;", "\\");
		s = s.replace( "％", "%");
		return s;
	}
	
	/**
	 * 截取指定长度字符串，并结尾加指定字符
	 * @param str	待截取的字符串
	 * @param beginIndex	截取开始的下标
	 * @param endIndex		截取截至的下标
	 * @param specChar		追加的字符
	 * @return	返回截取后的字符串
	 */
	public static String subStrAddSpecChar(String str,int beginIndex,int endIndex,String specChar){
		if(isEmpty(str) || beginIndex < 0 || endIndex < 0)
			return "";
		
		if(beginIndex>str.length())
			beginIndex = 0;
		
		if(endIndex>str.length())
			endIndex = str.length();
		StringBuilder sb = new StringBuilder();
		sb.append(str.substring(beginIndex,endIndex));
		sb.append(specChar);
		
		return sb.toString();
	}
	
	/**
	 * 转换字符串第一个字符为小写
	 * @param str 待转换的字符
	 * @return String	转换后的结果
	 */
	public static String getStrByLowerFirstChar(String str)
	{
		if(isEmpty(str))
			return "";
		
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}
	
	/**
	 * 转换字符串第一个字符为大写
	 * @param str 待转换的字符
	 * @return String	转换后的结果
	 */
	public static String getStrByUpperFirstChar(String str)
	{
		if(isEmpty(str))
			return "";
	
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
	/**
	 * 字符串拼接函数
	 * @param splitStr	字符拼接分隔符
	 * @param strAry	待拼接的变长字符串
	 * @return 返回拼接后的字符串
	 */
	public static String concatStr(String splitStr,String... strAry){
		StringBuffer sb = new StringBuffer();
		int len = strAry.length;
		for(int i=0;i<len;i++){
			
			sb.append(strAry[i]);
			if(i<len-1)
				sb.append(splitStr);
		}
		return sb.toString();
	}
	
	public static void main(String[] args){
		System.out.println(getStrByUpperFirstChar("ab速度cdef"));
		System.out.println(concatStr("#",Thread.currentThread().getName(),"test thread pool"));
	}
}
