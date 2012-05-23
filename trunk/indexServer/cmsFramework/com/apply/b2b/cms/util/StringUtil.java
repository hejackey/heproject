package com.apply.b2b.cms.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Pattern;



public class StringUtil {
	public StringUtil() {
	}

	public static final String escapeForIntro(String string) {
		// String str = escapeHTMLTags(string);
		String str = string;
		str = replace(str, "\r\n", "<br>");
		str = replace(str, "\n", "<br>");
		str = replace(str, "'", "\\'");
		return replace(str, "\r", "");

	}

	/**
	 * 得到非空的字符串，若字符串对象为null，则返回""。
	 * 
	 * @param objValue
	 *            Object待转换的原字符串对象
	 * @return String 转换后的字符串
	 */
	public static String getNotNullStr(Object objValue) {
		return (objValue == null ? "" : objValue.toString());
	}

	/**
	 * 得到非空的字符串，若字符串为null，则返回""。
	 * 
	 * @param strValue
	 *            String待转换的原字符串
	 * @return String 转换后的字符串
	 */
	public static String getNotNullStr(String strValue) {
		return (strValue == null ? "" : strValue.trim());
	}
	
	/**
	 * 用"0"补足一个字符串到指定长度
	 * 
	 * @param str -
	 *            源字符串
	 * @param size -
	 *            补足后应达到的长度
	 * @return - 补零后的结果
	 */
	public static String fillZero(String str, int size) {
		String result;
		if (str.length() < size) {
			char[] s = new char[size - str.length()];
			for (int i = 0; i < (size - str.length()); i++) {
				s[i] = '0';
			}
			result = new String(s) + str;
		} else {
			result = str;
		}
		
		return result;
	}

	/**
	 * 根据字符串（文件类型或者多行输入类型）获取字符串数组
	 * 
	 * @param str1
	 *            String 输入字符串
	 * @return String[] 返回结果
	 */
	public static String[] getStrArryByString(String str1) {
		if (str1.indexOf("\t") > 0) {
			for (int i = 0; i < str1.length(); i++) {
				if (str1.substring(i, i + 1).equals("\t")) {
					str1 = str1.substring(0, i) + " "
							+ str1.substring(i + 1, str1.length());
				}
			}
		}
		StringTokenizer stringTokenizer = new StringTokenizer(str1, "\r\n");
		String[] strId = new String[stringTokenizer.countTokens()];
		int i = 0;
		while (stringTokenizer.hasMoreTokens()) {
			strId[i] = stringTokenizer.nextToken();
			i++;
		}
		return strId;
	}

	/**
	 * 判断一个字符串是否为 NUll 或为空
	 * 
	 * @param inStr
	 *            inStr
	 * @return boolean
	 */
	public static boolean isValid(String inStr) {
		if (inStr == null) {
			return false;
		} else if (inStr.equals("")) {
			return false;
		} else if (inStr.equals("null")) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 获得指定长度的空格
	 * 
	 * @param spaceNum
	 *            spaceNum
	 * @return String
	 */
	public static String getStrSpace(int spaceNum) {
		return getStrWithSameElement(spaceNum, " ");
	}

	/**
	 * 获得指定长度的字符串
	 * 
	 * @param num
	 *            int
	 * @param element
	 *            String
	 * @return String
	 */
	public static String getStrWithSameElement(int num, String element) {
		if (num <= 0) {
			return "";
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < num; i++) {
			sb.append(element);
		}
		return sb.toString();
	}

	/**
	 * 从右或左加空格
	 * 
	 * @param strIn
	 *            String
	 * @param totalLength
	 *            int
	 * @param isRightAlign
	 *            boolean
	 * @return String
	 */
	public static String getFillString(String strIn, int totalLength,
			boolean isRightAlign) {
		int spaceLength = 0;
		String spaces = null;
		String strOut = null;

		if (strIn == null) {
			strIn = "";
		}

		spaceLength = totalLength - strIn.length();

		if (spaceLength < 0) {
			spaceLength = 0;
		}
		spaces = StringUtil.getStrSpace(spaceLength);

		if (isRightAlign) {
			strOut = spaces + strIn;
		} else {
			strOut = strIn + spaces;

		}
		return strOut;
	}

	/**
	 * 以String类型返回错误抛出的堆栈信息
	 * 
	 * @param t
	 *            Throwable
	 * @return String
	 */
	public static String getStackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		t.printStackTrace(pw);
		return sw.toString();
	}

	/**
	 * 转换字符串第一个字符为大写
	 * 
	 * @param str
	 *            String
	 * @return String
	 */
	public static String getStrByUpperFirstChar(String str) {
		try {
			return str.substring(0, 1).toUpperCase() + str.substring(1);
		} catch (Exception e) {
			return "";
		}

	}

	/**
	 * 转换字符串第一个字符为小写
	 * 
	 * @param str
	 *            String
	 * @return String
	 */
	public static String getStrByLowerFirstChar(String str) {
		try {
			return str.substring(0, 1).toLowerCase() + str.substring(1);
		} catch (Exception e) {
			return "";
		}

	}

	/**
	 * 通过字符串转换成相应的整型，并返回。
	 * 
	 * @param strValue
	 *            String 待转换的字符串
	 * @return int 转换完成的整型
	 */
	public static int getStrToInt(String strValue) {
		if (null == strValue) {
			return 0;
		}
		int iValue = 0;
		try {
			iValue = new java.lang.Integer(strValue.trim()).intValue();
		} catch (Exception ex) {
			iValue = 0;
		}
		return iValue;
	}

	/**
	 * 通过字符串转换成相应的DOUBLE，并返回。
	 * 
	 * @param strValue
	 *            String 待转换的字符串
	 * @return double 转换完成的DOUBLE
	 */
	public static double getStrToDouble(String strValue) {
		if (null == strValue) {
			return 0;
		}
		double dValue = 0;
		try {
			dValue = Double.parseDouble(strValue.trim());
		} catch (Exception ex) {
			dValue = 0;
		}
		return dValue;
	}

	/**
	 * 通过字符串转换成相应的短整型，并返回。
	 * 
	 * @param strValue
	 *            String 待转换的字符串
	 * @return short 转换完成的短整型
	 */
	public static short getStrToShort(String strValue) {
		if (null == strValue) {
			return 0;
		}
		short iValue = 0;
		try {
			iValue = new java.lang.Short(strValue.trim()).shortValue();
		} catch (Exception ex) {
			iValue = 0;
		}
		return iValue;
	}

	/**
	 * 通过字符串转换成相应的长整型，并返回。
	 * 
	 * @param strValue
	 *            String 待转换的字符串
	 * @return long 转换完成的长整型
	 */
	public static long getStrToLong(String strValue) {
		if (null == strValue) {
			return 0;
		}
		long lValue = 0;
		try {
			lValue = new java.lang.Long(strValue.trim()).longValue();
		} catch (Exception ex) {
			lValue = 0;
		}
		return lValue;
	}

	public static String toLengthForEn(String str, int length) {
		if (null != str) {
			if (str.length() <= length) {
				return str;
			} else {
				str = str.substring(0, length - 2);
				str = str + "..";
				return str;
			}
		} else {
			return "";
		}
	}

	/**
	 * 降字符串转换成给定长度的字符串，如超出的话截断，并在最后以两个点结尾
	 * 
	 * @param str
	 *            String
	 * @param length
	 *            int
	 * @return String
	 */
	public static String toLengthForIntroduce(String str, int length) {
		str = delTag(str);

		byte[] strByte = str.getBytes();
		int byteLength = strByte.length;
		char[] charArray;
		StringBuffer buff = new StringBuffer();
		if (byteLength > (length * 2)) {
			charArray = str.toCharArray();
			int resultLength = 0;
			for (int i = 0; i < charArray.length; i++) {
				resultLength += String.valueOf(charArray[i]).getBytes().length;
				if (resultLength > (length * 2)) {
					break;
				}
				buff.append(charArray[i]);

			}
			buff.append("..");
			str = buff.toString();
		}

		// str = replace(str, "'", "\\'");
		str = replace(str, "\"", "\\\"");
		str = replace(str, "，", ",");
		return str;

	}

	public static String delTag(String str) {
		str = str + "<>";
		StringBuffer buff = new StringBuffer();
		int start = 0;
		int end = 0;

		while (str.length() > 0) {
			start = str.indexOf("<");
			end = str.indexOf(">");
			if (start > 0) {
				buff.append(str.substring(0, start));
			}
			if (end > 0 && end <= str.length()) {
				str = str.substring(end + 1, str.length());
			} else {
				str = "";
			}

		}
		String result = buff.toString();

		while (result.startsWith(" ")) {

			result = result.substring(result.indexOf(" ") + 1, result.length());

		}
		return result;

	}

	public static final String replace(String line, String oldString,
			String newString) {
		if (line == null) {
			return null;
		}
		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0) {
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = line.indexOf(oldString, i)) > 0) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		}
		return line;

	}

	// Replace
	public static String Replace(String source, String oldString,
			String newString) {
		if (source == null) {
			return null;
		}
		StringBuffer output = new StringBuffer();
		int lengOfsource = source.length();
		int lengOfold = oldString.length();
		int posStart = 0;
		int pos;
		while ((pos = source.indexOf(oldString, posStart)) >= 0) {
			output.append(source.substring(posStart, pos));
			output.append(newString);
			posStart = pos + lengOfold;
		}
		if (posStart < lengOfsource) {
			output.append(source.substring(posStart));
		}
		return output.toString();
	}
	
	// 此函数前台使用中，请勿随便修改，不然会造成显示混乱(以前修改版本在下方注释中)
	public static String toHtml(String str) {
		str = Replace(str, "&", "&amp;");
		str = Replace(str, "<", "&lt;");
		str = Replace(str, ">", "&gt;");
		str = Replace(str, "\t", "    ");
		str = Replace(str, "\r\n", " ");
		str = Replace(str, "\n", " ");
		str = Replace(str, "'", "&#39;");
		str = Replace(str, "\"", "&quot;");
		str = Replace(str, "\\", "&#92;");
		str = Replace(str, "%", "％");
		return str;
	}
	
	// 逆
	public static String unHtml(String s) {
		// s = Replace(s, "&lt;", "<");
		// s = Replace(s, "&gt;", ">");
		// s = Replace(s, " ", "\t");
		// s = Replace(s, "\n", "\r\n");
		s = Replace(s, "<br>", "\n");
		// s = Replace(s, "&nbsp;", " ");
		// s = Replace(s, "&amp;", "&");
		// s = Replace(s, "&#39;", "'");
		// s = Replace(s, "&#92;", "\\");
		// s = Replace(s, "％", "%");
		return s;
	}
	
	// 逆
	public static String unHtmlCms(String s) {
		s = Replace(s, "&#39;", "'");
		s = Replace(s, "&quot;", "\"");
		s = Replace(s, "&#92;", "\\");
		s = Replace(s, "&nbsp;", " ");
		s = Replace(s, "&#160;", " ");
		s = Replace(s, "    ", "\t");
		s = Replace(s, "&lt;", "<");
		s = Replace(s, "&gt;", ">");
		s = Replace(s, "&amp;", "&");
		return s;
	}
	
	// 此函数后台使用中，请勿随便修改，不然会造成显示混乱(以前修改版本在下方注释中)
	public static String toHtmlBack(String s) {
		// 显示
		s = Replace(s, "&", "&amp;");
		s = Replace(s, "\\", "&#92;");
		s = Replace(s, "\"", "&quot;");
		s = Replace(s, "<", "&lt;");
		s = Replace(s, ">", "&gt;");
		s = Replace(s, "\t", "    ");
		s = Replace(s, "\r\n", "\n");
		// s = Replace(s, "\n", "<br>");
		// s = Replace(s, " ", "&nbsp;");
		// s = Replace(s, "'", "&#39;");
		// s = Replace(s, "%", "%");

		return s;
	}
	
	// 逆
	public static String unHtmlBack(String s) {
		s = Replace(s, "&lt;", "<");
		s = Replace(s, "&gt;", ">");
		s = Replace(s, "    ", "\t");
		s = Replace(s, "\n", "\r\n");
		s = Replace(s, "<br>", "\n");
		s = Replace(s, "&nbsp;", " ");
		s = Replace(s, "&amp;", "&");
		s = Replace(s, "&#39;", "'");
		s = Replace(s, "&#92;", "\\");
		s = Replace(s, "％", "%");
		return s;
	}
	
	public static String removeHtml(String s) {
		s = Replace(s, "<br>", "");
		s = Replace(s, "<", "");
		s = Replace(s, ">", "");
		s = Replace(s, "\t", "");
		s = Replace(s, "\r\n", "");
		s = Replace(s, "\n", "");
		s = Replace(s, "'", "");
		s = Replace(s, "\"", "");
		s = Replace(s, "\\", "");
		s = Replace(s, "%", "％");
		s = Replace(s, "&", "");
		s = Replace(s, "<input>", "");
		return s;
	}

	/*
	 * public static String toHtml(String s) { //显示 s = Replace(s, "&",
	 * "&amp;"); s = Replace(s, "\\", "&#92;"); s = Replace(s, "\"", "&quot;");
	 * s = Replace(s, "<", "&lt;"); s = Replace(s, ">", "&gt;"); s = Replace(s,
	 * "\t", " "); s = Replace(s, "\r\n", "\n"); // s = Replace(s, "\n", "<br>");
	 * s = Replace(s, " ", "&nbsp;"); // s = Replace(s, "'", "&#39;"); // s =
	 * Replace(s, "%", "%");
	 * 
	 * return s; }
	 * 
	 * public static String unHtml(String s) { s = Replace(s, "&lt;", "<"); s =
	 * Replace(s, "&gt;", ">"); s = Replace(s, " ", "\t"); s = Replace(s, "\n",
	 * "\r\n"); s = Replace(s, "<br>", "\n"); s = Replace(s, "&nbsp;", " "); s =
	 * Replace(s, "&amp;", "&"); s = Replace(s, "&#39;", "'"); s = Replace(s,
	 * "&#92;", "\\"); s = Replace(s, "％", "%"); return s; }
	 */
	// 判断是否含有中文，如果含有中文返回ture
	public static boolean containsChinese(String str)
			throws UnsupportedEncodingException {

		if (str.length() < (str.getBytes()).length)
			return true;

		return false;

		// for (int i = 0; i < username.length(); i++) {
		// String unit=Character.toString(username.charAt(i));
		// byte[] unitByte=unit.getBytes("GBK");
		// // ((unitByte[0]+256)*256 + (unitByte[1]+256)) <= 0xFEFE)
		// if (unitByte.length == 2)
		// {
		// return true;
		// }
		// }
		// return false;

	}

	public static boolean isEmpty(String str) {
		if (str == null)
			return true;
		return "".equals(str.trim());
	}

	public static String[] split(String str1, String str2) {
		return org.apache.commons.lang.StringUtils.split(str1, str2);
	}

	public static String left(String str, int length) {
		return org.apache.commons.lang.StringUtils.left(str, length);
	}

	/**
	 * 根据输入的长度截取字符串，单个单词超过输入长度的强制加<BR>
	 * 换行
	 * 
	 * @param str
	 *            输入的字符串
	 * @param len
	 *            输入的长度
	 * @return 处理过后的字符串
	 */
	public static String truncateStr(String str, int len) {
		if (str != null && !("").equalsIgnoreCase(str)) {

			String strs[] = str.split(" ");
			StringBuffer buff = new StringBuffer();
			if (strs.length > 0) {
				for (int i = 0; i < strs.length; i++) {
					StringBuffer temp = new StringBuffer();
					while (strs[i].length() > len) {
						temp.append(strs[i].substring(0, len) + "<BR>");
						strs[i] = strs[i].substring(len);
					}
					temp.append(strs[i]);
					buff.append(temp.toString() + " ");
				}

			}
			return buff.toString();
		} else {
			return "";
		}
	}

	public static String truncateStr2(String str, int len) {
		if (str != null && !("").equalsIgnoreCase(str) && len != 0) {
			String strs[] = str.split(" ");

			StringBuffer buff = new StringBuffer();
			for (int i = 0; i < strs.length; i++) {
				StringBuffer temp = new StringBuffer();
				String tempstr = "";
				while (strs[i].length() > len) {
					tempstr = strs[i].substring(0, len);
					tempstr = tempstr.replaceAll(" ", "&nbsp; ");
					tempstr = tempstr.replaceAll("<", "&lt; ");
					tempstr = tempstr.replaceAll("\n", "<br> ").replaceAll(
							"\"", "&quot; ").replaceAll("'", "&#39; ");
					tempstr = tempstr + "<br>";
					temp.append(tempstr);

					strs[i] = strs[i].substring(len);
				}
				tempstr = strs[i];
				tempstr = tempstr.replaceAll(" ", "&nbsp; ");
				tempstr = tempstr.replaceAll("<", "&lt; ");
				tempstr = tempstr.replaceAll("\n", "<br> ").replaceAll("\"",
						"&quot; ").replaceAll("'", "&#39; ");

				temp.append(tempstr);
				buff.append(temp.toString() + " ");
			}

			if (buff.length() > 0)
				return buff.toString().substring(0, buff.length() - 1);
			else
				return str;
		} else {
			return "";
		}
	}

	/**
	 * 编码转换，从unicode转换为GBK
	 * 
	 * @param str
	 *            输入字符串
	 * @return str编码转换后的字符串
	 * @throws UnsupportedEncodingException
	 */
	public static String unicodeToGB(String l_S_Source)
			throws UnsupportedEncodingException {
		String l_S_Desc = "";
		if (l_S_Source != null && !l_S_Source.trim().equals("")) {
			byte l_b_Proc[] = l_S_Source.getBytes("GBK");
			l_S_Desc = new String(l_b_Proc, "ISO8859_1");
		}
		return l_S_Desc;
	}

	/**
	 * 编码转换，从GBK转换为unicode
	 * 
	 * @param str
	 *            输入字符串
	 * @return str 编码转换后的字符串
	 * @throws UnsupportedEncodingException
	 */
	public static String GBToUnicode(String l_S_Source)
			throws UnsupportedEncodingException {
		String l_S_Desc = "";
		if (l_S_Source != null && !l_S_Source.trim().equals("")) {
			byte l_b_Proc[] = l_S_Source.getBytes("ISO8859_1");
			l_S_Desc = new String(l_b_Proc, "GBK");
		}
		return l_S_Desc;
	}

	/**
	 * Escapes a <code>String</code> according the JavaScript string literal
	 * escaping rules. The resulting string will not be quoted.
	 * 
	 * <p>
	 * It escapes both <tt>'</tt> and <tt>"</tt>. In additional it escapes
	 * <tt>></tt> as <tt>\></tt> (to avoid <tt>&lt;/script></tt>).
	 * Furthermore, all characters under UCS code point 0x20, that has no
	 * dedicated escape sequence in JavaScript language, will be replaced with
	 * hexadecimal escape (<tt>\x<i>XX</i></tt>).
	 */
	public static String javaScriptStringEnc(String s) {
		int ln = s.length();
		for (int i = 0; i < ln; i++) {
			char c = s.charAt(i);
			if (c == '"' || c == '\'' || c == '\\' || c == '>' || c < 0x20) {
				StringBuffer b = new StringBuffer(ln + 4);
				b.append(s.substring(0, i));
				while (true) {
					if (c == '"') {
						b.append("\\\"");
					} else if (c == '\'') {
						b.append("\\'");
					} else if (c == '\\') {
						b.append("\\\\");
					} else if (c == '>') {
						b.append("\\>");
					} else if (c < 0x20) {
						if (c == '\n') {
							b.append("\\n");
						} else if (c == '\r') {
							b.append("\\r");
						} else if (c == '\f') {
							b.append("\\f");
						} else if (c == '\b') {
							b.append("\\b");
						} else if (c == '\t') {
							b.append("\\t");
						} else {
							b.append("\\x");
							int x = c / 0x10;
							b
									.append((char) (x < 0xA ? x + '0'
											: x - 0xA + 'A'));
							x = c & 0xF;
							b
									.append((char) (x < 0xA ? x + '0'
											: x - 0xA + 'A'));
						}
					} else {
						b.append(c);
					}
					i++;
					if (i >= ln) {
						return b.toString();
					}
					c = s.charAt(i);
				}
			} // if has to be escaped
		} // for each characters
		return s;
	}

	public static final String GenerateRandomStr(String randStr, int count) {

		StringBuffer generateRandStr = new StringBuffer();
		Random rand = new Random();
		int randStrLength = count; // 你想生成的随机数的长度
		for (int i = 0; i < randStrLength; i++) {
			int randNum = rand.nextInt(randStr.length());
			generateRandStr.append(randStr.substring(randNum, randNum + 1));
		}
		return generateRandStr.toString(); // 打印你的结果
	}

	private static StringUtil instance = null;

	public static synchronized StringUtil getInstance() {
		if (instance == null) {
			instance = new StringUtil();
		}
		return instance;
	}

	public static String replace(String src, String target, String rWith,
			int maxCount) {
		return org.apache.commons.lang.StringUtils.replace(src, target, rWith,
				maxCount);
	}

	public static boolean equals(String str1, String str2) {
		return org.apache.commons.lang.StringUtils.equals(str1, str2);
	}

	public static boolean isAlphanumeric(String str) {
		return org.apache.commons.lang.StringUtils.isAlphanumeric(str);
	}

	public static boolean isNumeric(String str) {
		return org.apache.commons.lang.StringUtils.isNumeric(str);
	}

	public static String[] stripAll(String[] strs) {
		return org.apache.commons.lang.StringUtils.stripAll(strs);
	}

	public static String truncation(String src) {
		StringBuffer result = new StringBuffer("");

		boolean last = false; // 标记上一个字符是否是英文字符或数字
		for (int i = 0; i < src.length(); i++) {
			char c = src.charAt(i);
			if ((c >= 65 && c <= 90 /* A-Z */)
					|| (c >= 97 && c <= 122/* a-z */)
					|| (c >= 48 && c <= 57 /* 数字 */)) { // 英文字母
				result.append(c);
				last = true;
			} else if (last) {
				last = false;
				result.append("-");
			}
		}
		return result.toString();
	}
	
	/**
	 * 页面展示是如果为空,返回"N/A"
	 */
	public static String escapeNull(String str) {
		if (str == null || str.equals("")) {
			return "N/A";
		} else {
			return str;
		}
	}
	
	/**
	 * 页面展示是如果为空,返回"N/A"
	 */
	public static String escapeNullObject(String str) {
		if (str == null) {
			return "";
		} else {
			return str;
		}
	}
	
	/**
	 * 
	 * @author luoweifeng
	 * @param str
	 * @return
	 */
	
	public static String escapeHtml(String s) {
		s = Replace(s, "<br>", "\n");
		s = Replace(s, "<br/>", "\n");
		s = Replace(s, "<", "&lt;");
		s = Replace(s, ">", "&gt;");
		s = Replace(s, "\t", "    ");
		s = Replace(s, "\r\n", "\n");
		s = Replace(s, "\n", "<br/>");
		s = Replace(s, "'", "&#39;");
		s = Replace(s, "\"", "&quot;");
		s = Replace(s, "\\", "&#92;");
		s = Replace(s, "%", "％");
		return s;
	}
	
	public static String sliceTooLongWord(String str, int length) {
		String reg = "(\\S){" + length + "}?";
		return str.replaceAll(reg, "$0 ");
	}
	
	/**
	 * 按照 字节 截取字符串
	 * 
	 * @param str
	 * @param istart
	 * @param toCount
	 * @param more
	 * @return
	 */
	/**
	 * 判断一个字符是Ascill字符还是其它字符（如汉，日，韩文字符）
	 * 
	 * @param char
	 *            c, 需要判断的字符
	 * @return boolean, 返回true,Ascill字符
	 */
	private static boolean isLetter(char c) {
		int k = 0x80;
		return c / k == 0 ? true : false;
	}
	
	/**
	 * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
	 * 
	 * @param String
	 *            s ,需要得到长度的字符串
	 * @return int, 得到的字符串长度
	 */
	public static int length(String s) {
		if (s == null)
			return 0;
		char[] c = s.toCharArray();
		int len = 0;
		for (int i = 0; i < c.length; i++) {
			len++;
			if (!isLetter(c[i])) {
				len++;
			}
		}
		return len;
	}
	
	public static int lengthSub(int titleLen, String s) {
		int len = 0;
		char[] c = s.toCharArray();
		boolean flag = true;
		for (int i = 0; i <= s.length(); i++) {

			if (titleLen > 0) {
				titleLen--;
				if (!isLetter(c[i])) {
					titleLen--;
				}
			} else {
				if (flag) {
					len = i;
					flag = false;
				}
			}
		}
		return len;

	}
	
	/**
	 * 截取一段字符的长度,不区分中英文,如果数字不正好，则少取一个字符位
	 * 
	 * @author patriotlml
	 * @param String
	 *            origin, 原始字符串
	 * @param int
	 *            len, 截取长度(一个汉字长度按2算的)
	 * @return String, 返回的字符串
	 */
	public static String substring(String origin, int len, String more) {
		if (origin == null || origin.equals("") || len < 1)
			return "";
		byte[] strByte = new byte[len];
		if (len > StringUtil.length(origin)) {
			return origin;
		}
		System.arraycopy(origin.getBytes(), 0, strByte, 0, len);
		int count = 0;
		for (int i = 0; i < len; i++) {
			int value = (int) strByte[i];
			if (value < 0) {
				count++;
			}
		}
		if (count % 2 != 0) {
			len = (len == 1) ? ++len : --len;
		}
		int n = count % 2;
		String retS = "";
		retS = origin.substring(0, len - n) + more;
		return retS;
	}
	
	public static String html2Text(String inputString) {
		String htmlStr = inputString;
		String textStr = "";
		java.util.regex.Pattern p_script = null;
		java.util.regex.Matcher m_script = null;
		java.util.regex.Pattern p_style = null;
		java.util.regex.Matcher m_style = null;
		java.util.regex.Pattern p_html = null;
		java.util.regex.Matcher m_html = null;
		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
			String regEx_html = "<[^>]+>";
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll("");
			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll("");
			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll("");
			textStr = htmlStr;
		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}
		return textStr;
	}
}
