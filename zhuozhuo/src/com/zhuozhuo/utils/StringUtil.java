package com.zhuozhuo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;

/**
 * *
 * <p>
 * Title: 工具类
 * </p>
 * <p>
 * Description: 用来处理各种字符串操作
 * </p>
 * @version 1.０
 */
public final class StringUtil {
	private StringUtil() {
	}

	/**
	 * 杀空函数，将"null"和null对象转换为""
	 *
	 * @param str
	 *            输入字符串
	 * @return 输出字符串
	 */
	public static String killNull(String str) {
		String returnStr = null;
		if (str == null || "null".equals(str.toLowerCase())) {
			returnStr = "";
		} else {
			returnStr = str;
		}
		return returnStr;
	}

	/**
	 * 去除字符串两边的空格并处理空字符串
	 *
	 * @param str
	 *            输入字符串
	 * @return 输出字符串
	 */
	public static String trim(String str) {
		String returnStr = null;
		returnStr = killNull(str);
		returnStr = returnStr.trim();
		return returnStr;
	}

	/**
	 * 将String类型变量转换为ASCII码 1、如果ASCII为73<i>、79[O]，跳过
	 * 2、只转换'0-9'和'A-Z'(除'I'、'O')的ASCII码，对应的ASCII码为'48-57'和'65-90'(除'73'、'79')
	 *
	 * @param transParam
	 *            待转换的变
	 * @return 转换后的ASCII码
	 */
	public static String stringToASCII(String transParam) {
		if (transParam == null || transParam.length() == 0) {
			return null;
		}
		char[] transChars = transParam.toCharArray();
		String ascii = "";
		// 字符转换为数字，并拼接为ASCII码
		int charASCII = -1;
		for (int i = 0; i < transChars.length; i++) {
			charASCII = (int) transChars[i];
			// 如果为73(I)、79(O)，自增
			if (charASCII == 73 || charASCII == 79) {
				charASCII++;
			}
			ascii += charASCII;
		}
		return ascii;
	}

	/**
	 * 检查字符串是否是options数组中的一个
	 *
	 * @param str
	 * @param options
	 * @return 检查字符串是否是options数组中的一个
	 */
	public static boolean isStrLegal(String str, String[] options) {
		for (int i = 0; i < options.length; i++) {
			if (options[i].equals(str))
				return true;
		}
		return false;
	}

	/**
	 * 检查字符串长度是否小于等于maxLength(可以为空串)，若是为null，也返回true。
	 *
	 * @param str
	 * @return boolean
	 */
	public static boolean isStrLegalOrEmpty(String str, int maxLength) {
		if (str == null)
			return true;
		else if (str.trim().equals(""))
			return true;
		return isStrLegal(str, 0, maxLength);
	}

	/**
	 * 检查字符串长度是否小于等于maxLength(可以为空串)。
	 *
	 * @param str
	 * @return boolean
	 */
	public static boolean isStrLegal(String str, int maxLength) {
		return isStrLegal(str, 0, maxLength);
	}

	/**
	 * 检查字符串长度是否大于等于minLength，小于等于maxLength。
	 *
	 * @param str
	 * @return boolean
	 */
	public static boolean isStrLegal(String str, int minLength, int maxLength) {
		boolean result = true;
		if (str == null)
			result = false;
		else if (str.trim().equals(""))
			result = false;
		else if (str.getBytes().length > maxLength
				|| str.getBytes().length < minLength)
			result = false;
		return result;
	}

	/**
	 * 判断字符串中是否全是数字：0到9， 并检查字符串长度是否小于等于maxLength(可以为空串)。
	 *
	 * @param str
	 * @return boolean
	 */
	public static boolean isNumLegal(String str, int maxLength) {
		return isNumLegal(str, 0, maxLength);
	}

	/**
	 * 判断字符串中是否全是数字：0到9， 并检查字符串长度是否大于等于minLength，小于等于maxLength。
	 *
	 * @param str
	 * @return boolean
	 */
	public static boolean isNumLegal(String str, int minLength, int maxLength) {
		if (!isStrLegal(str, minLength, maxLength))
			return false;
		return isNumLegal(str);
	}

	/**
	 * 判断字符串中是否全是数字：0到9
	 *
	 * @param str
	 * @return boolean
	 */
	public static boolean isNumLegal(String str) {
		if (str == null)
			return false;
		char ch;
		for (int i = 0; i < str.getBytes().length; i++) {
			ch = str.charAt(i);
			if (ch < '0' || ch > '9')
				return false;
		}
		return true;
	}

	/**
	 * 这里定义的时间格式是:yyyy-mm-dd,如2006-10-11 该函数只检查输入的时间字符串是否满足上面的格式要求。
	 *
	 * @param dateStr
	 * @return boolean
	 */
	public static boolean isDateLegal(String dateStr) {
		if (dateStr == null)
			return false;
		if (dateStr.length() != 10)
			return false;
		else {
			String[] strArr = dateStr.split("-");
			if (strArr.length != 3)
				return false;
			for (int i = 0; i < strArr.length; i++)
				if (!isNumLegal(strArr[i]))
					return false;
			return true;
		}
	}

	// ------------------------------------ 字符串处理方法
	/**
	 * 将字符串 source 中的 oldStr 替换为 newStr, 并以大小写敏感方式进行查找
	 *
	 * @param source
	 * @param oldStr
	 * @param newStr
	 * @return String
	 */
	public static String replace(String source, String oldStr, String newStr) {
		return replace(source, oldStr, newStr, true);
	}

	/**
	 * 将字符串 source 中的 oldStr 替换为 newStr, matchCase 为是否设置大小写敏感查找
	 *
	 * @param source
	 * @param oldStr
	 * @param newStr
	 * @param matchCase
	 * @return String
	 */
	public static String replace(String source, String oldStr, String newStr,
			boolean matchCase) {
		if (source == null)
			return null;
		// 首先检查旧字符串是否存在, 不存在就不进行替换
		if (source.toLowerCase().indexOf(oldStr.toLowerCase()) == -1) {
			return source;
		}
		int findStartPos = 0;
		int a = 0;
		while (a > -1) {
			int b = 0;
			String str1, str2, str3, str4, strA, strB;
			str1 = source;
			str2 = str1.toLowerCase();
			str3 = oldStr;
			str4 = str3.toLowerCase();
			if (matchCase) {
				strA = str1;
				strB = str3;
			} else {
				strA = str2;
				strB = str4;
			}
			a = strA.indexOf(strB, findStartPos);
			if (a > -1) {
				b = oldStr.length();
				findStartPos = a + b;
				StringBuffer bbuf = new StringBuffer(source);
				source = bbuf.replace(a, a + b, newStr) + "";
				// 新的查找开始点位于替换后的字符串的结尾
				findStartPos = findStartPos + newStr.length() - b;
			}
		}
		return source;
	}

	/**
	 * 滤除帖子中的危险 HTML 代码, 主要是脚本代码, 滚动字幕代码以及脚本事件处理代码
	 *
	 * @param content
	 * @return String
	 */
	public static String replaceHtmlCode(String content) {
		if (isEmpty(content))
			return "";
		// 需要滤除的脚本事件关键字
		String[] eventKeywords = { "onmouseover", "onmouseout", "onmousedown",
				"onmouseup", "onmousemove", "onclick", "ondblclick",
				"onkeypress", "onkeydown", "onkeyup", "ondragstart",
				"onerrorupdate", "onhelp", "onreadystatechange", "onrowenter",
				"onrowexit", "onselectstart", "onload", "onunload",
				"onbeforeunload", "onblur", "onerror", "onfocus", "onresize",
				"onscroll", "oncontextmenu" };
		content = replace(content, "<script", "&ltscript", false);
		content = replace(content, "</script", "&lt/script", false);
		content = replace(content, "<marquee", "&ltmarquee", false);
		content = replace(content, "</marquee", "&lt/marquee", false);
		content = replace(content, "\r\n", "<BR>");
		// 滤除脚本事件代码
		for (int i = 0; i < eventKeywords.length; i++) {
			content = replace(content, eventKeywords[i],
					"_" + eventKeywords[i], false); // 添加一个"_",
			// 使事件代码无效
		}
		return content;
	}

	// ******************************** 滤除 HTML 代码 为文本代码
	/**
	 *
	 * @param input
	 * @return String
	 */
	public static String replaceHtmlToText(String input) {
		if (isEmpty(input)) {
			return "";
		}
		return setBr(setTag(input));
	}

	/**
	 * 滤除 HTML 标记
	 *
	 * @param s
	 * @return String
	 */
	public static String setTag(String s) {
		int j = s.length();
		StringBuffer stringbuffer = new StringBuffer(j + 500);
		for (int i = 0; i < j; i++)
			if (s.charAt(i) == '<')
				stringbuffer.append("&lt");
			else if (s.charAt(i) == '>')
				stringbuffer.append("&gt");
			else if (s.charAt(i) == '&')
				stringbuffer.append("&amp");
			else
				stringbuffer.append(s.charAt(i));
		return stringbuffer.toString();
	}

	/**
	 * 滤除 BR 代码
	 *
	 * @param s
	 * @return String
	 */
	public static String setBr(String s) {
		int j = s.length();
		StringBuffer stringbuffer = new StringBuffer(j + 500);
		for (int i = 0; i < j; i++)
			if (s.charAt(i) == '\n')
				stringbuffer.append("");
			else if (s.charAt(i) == '\r')
				stringbuffer.append("");
			else
				stringbuffer.append(s.charAt(i));
		return stringbuffer.toString();
	}

	/**
	 * 滤除空格
	 *
	 * @param s
	 * @return String
	 */
	public static String setNbsp(String s) {
		int j = s.length();
		StringBuffer stringbuffer = new StringBuffer(j + 500);
		for (int i = 0; i < j; i++)
			if (s.charAt(i) == ' ')
				stringbuffer.append("&nbsp;");
			else
				stringbuffer.append(s.charAt(i));
		return stringbuffer.toString();
	}

	/**
	 * 转换由表单读取的数据的内码
	 *
	 * @param input
	 * @return String
	 */
	public static String toChi(String input) {
		try {
			byte[] bytes = input.getBytes("ISO8859-1");
			return new String(bytes);
		} catch (Exception ex) {
		}
		return null;
	}

	/**
	 * 将单个的 ' 换成 ''; SQL 规则:如果单引号中的字符串包含一个嵌入的引号，可以使用两个单引号表示嵌入的单引号。
	 *
	 * @param input
	 * @return String
	 */
	public static String replaceSql(String input) {
		return replace(input, "'", "''");
	}

	/**
	 * 对给定字符进行 URL 编码
	 *
	 * @param value
	 * @return String
	 */
	public static String encode(String value) {
		if (isEmpty(value))
			return "";
		return java.net.URLEncoder.encode(value);
	}

	/**
	 * 对给定字符进行 URL 解码
	 *
	 * @param value
	 * @return String
	 */
	public static String decode(String value) {
		if (isEmpty(value))
			return "";
		return java.net.URLDecoder.decode(value);
	}

	/**
	 * 判断字符串是否未空
	 *
	 * @param input
	 * @return boolean
	 */
	public static boolean isEmpty(String input) {
		if (input == null || input.length() <= 0)
			return true;
		return false;
	}

	/**
	 * 将心情符号修改为对应的图片 ------------- 请修改页面中相关代码!
	 *
	 * @param temp
	 * @return String
	 */
	public static String smilies(String temp) {
		if (isEmpty(temp))
			return "";
		// 判断是否有禁止表情字符的表单值
		// if( isEmpty(request("smilies")) ) {
		temp = replace(temp, "/:)",
				"<IMG border=0 SRC=images/brow/regular_smile.gif>");
		temp = replace(temp, "/:d",
				"<IMG border=0 SRC=images/brow/teeth_smile.gif>");
		temp = replace(temp, "/:o",
				"<IMG border=0 SRC=images/brow/omg_smile.gif>");
		temp = replace(temp, "/:p",
				"<IMG border=0 SRC=images/brow/tounge_smile.gif>");
		temp = replace(temp, "/;)",
				"<IMG border=0 SRC=images/brow/wink_smile.gif>");
		temp = replace(temp, "/:(",
				"<IMG border=0 SRC=images/brow/sad_smile.gif>");
		temp = replace(temp, "/:s",
				"<IMG border=0 SRC=images/brow/confused_smile.gif>");
		temp = replace(temp, "/:|",
				"<IMG border=0 SRC=images/brow/whatchutalkingabout_smile.gif>");
		temp = replace(temp, "/:$",
				"<IMG border=0 SRC=images/brow/embaressed_smile.gif>");
		// }
		return temp;
	}

	/**
	 * 得到文件的扩展名.
	 *
	 * @param fileName
	 *            需要处理的文件的名字.
	 * @return the extension portion of the file's name.
	 */
	public static String getExtension(String fileName) {
		if (fileName != null) {
			int i = fileName.lastIndexOf('.');
			if (i > 0 && i < fileName.length() - 1) {
				return fileName.substring(i + 1).toLowerCase();
			}
		}
		return "";
	}

	/**
	 * 得到文件的前缀名.
	 *
	 * @param fileName
	 *            需要处理的文件的名字.
	 * @return the prefix portion of the file's name.
	 */
	public static String getPrefix(String fileName) {
		if (fileName != null) {
			int i = fileName.lastIndexOf('.');
			if (i > 0 && i < fileName.length() - 1) {
				return fileName.substring(0, i);
			}
		}
		return "";
	}

	/**
	 * 删除指定的 Web 应用程序目录下所上传的文件
	 *
	 * @param application
	 * @param filePath
	 */
	public static void deleteFile(ServletContext application, String filePath) {
		if (!isEmpty(filePath)) {
			String physicalFilePath = application.getRealPath(filePath);
			if (!isEmpty(physicalFilePath)) {
				java.io.File file = new java.io.File(physicalFilePath);
				file.delete();
			}
		}
	}
	
	/**
	 * 判断是否是合法的email地址
	 * @param mail
	 * @return 是否是合法email的地址
	 */
	public static boolean verifyMail(String email){
		if(email==null)
			return false;
		Pattern pattern = Pattern.compile("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+",Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	/**
	 * 截取url
	 */
	public static String truncateUrl(String url){
		if(url==null)
			return null;
		Pattern pattern = Pattern.compile("(http://|https://){1}[\\w\\.\\-/:]+");
		Matcher matcher = pattern.matcher(url);
		StringBuffer buffer = new StringBuffer();
		while(matcher.find()){              
		    buffer.append(matcher.group());        
		    buffer.append("\r\n");
		}
		return buffer.toString();
	}
	/**
	 * example:
	 * Pattern pattern = Pattern.compile("(http://|https://){1}[\\w\\.\\-/:]+");
	 * Matcher matcher = pattern.matcher("dsdsds<http://dsds//gfgffdfd>fdf");
	 * 	StringBuffer buffer = new StringBuffer();
	 * 	while(matcher.find()){              
	 * 	    buffer.append(matcher.group());        
	 * 	    buffer.append("\r\n");              
	 * 	System.out.println(buffer.toString());
	 * 	}
	 */
	
	/**
	 * 去除html标记
	 */
	public static String removeHtml(String html){
		if(html==null)
			return null;
		Pattern pattern = Pattern.compile("<.+?>", Pattern.DOTALL);
		Matcher matcher = pattern.matcher(html);
		String result = matcher.replaceAll("");
		return result;
	}
	/**example:
	 * Pattern pattern = Pattern.compile("<.+?>", Pattern.DOTALL);
	 * Matcher matcher = pattern.matcher("<a href=\"index.html\">主页</a>");
	 * String string = matcher.replaceAll("");
	 * System.out.println(string);
	 */
}
