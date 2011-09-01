package com.bfb.commons.velocity;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class VelocityStringUtil {
	private static java.text.SimpleDateFormat sdfLong = new java.text.SimpleDateFormat(
			"yyyy-MM-dd");
	private static java.text.SimpleDateFormat timeLong = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	private static java.text.SimpleDateFormat sdfLongCn = new java.text.SimpleDateFormat(
			"yyyy年MM月dd日");
	private static String domainName="https://static.8f8.com";
	
	private VelocityStringUtil() {
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
		spaces = VelocityStringUtil.getStrSpace(spaceLength);
		if (isRightAlign) {
			strOut = spaces + strIn;
		} else {
			strOut = strIn + spaces;
		}
		return strOut;
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

	/**
	 * 英文字符截长度，后面补 ..
	 * 
	 * @param str
	 * @param length
	 * @return
	 */
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

	/**
	 * 转义hmtl标签
	 * 
	 * @param str
	 * @return
	 */
	public static String toHtml(String str) {
		if (str != null && !str.trim().equals("")) {
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
		}
		return str;
	}

	// 逆 转义hmtl标签
	public static String unHtmlBack(String s) {
		if (s != null && !s.trim().equals("")) {
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
		}
		return s;
	}

	/**
	 * 删除hmtl标签
	 * 
	 * @param s
	 * @return
	 */
	public static String removeHtml(String s) {
		if (s != null && !s.trim().equals("")) {
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
		}
		return s;
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null)
			return true;
		return "".equals(str.trim());
	}

	/**
	 * 分割字符串
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
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
		if (s != null) {
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
								b.append((char) (x < 0xA ? x + '0'
										: x - 0xA + 'A'));
								x = c & 0xF;
								b.append((char) (x < 0xA ? x + '0'
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
				}
			}
			return s;
		} else {
			return "";
		}
	}
	
	/**
	 * 随机产生字符串
	 * 
	 * @param randStr
	 * @param count
	 * @return
	 */
	public static final String GenerateRandomStr(String randStr, int count) {
		StringBuffer generateRandStr = new StringBuffer();
		Random rand = new Random();
		int randStrLength = count;
		for (int i = 0; i < randStrLength; i++) {
			int randNum = rand.nextInt(randStr.length());
			generateRandStr.append(randStr.substring(randNum, randNum + 1));
		}
		return generateRandStr.toString(); // 打印你的结果
	}
	
	/**
	 * 获得比例
	 * @param num1Int
	 * @param num2Int
	 * @return
	 */
	public static String getPercentValue(double num1Int, double num2Int) {
		if (num2Int > 0 && num1Int > 0) {
			String returnVal = null;
			DecimalFormat digits = new DecimalFormat("0.00");
			try {
				returnVal = String
						.valueOf(digits
								.format((new Double(num1Int) / new Double(
										num2Int)) ));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return returnVal;
		} else {
			return "0";
		}
	}
	
	/**
	 * 取百分比
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static String getPercentValue(int num1Int, int num2Int) {
		if (num2Int > 0 && num1Int > 0) {
			String returnVal = null;
			DecimalFormat digits = new DecimalFormat("0.00");
			try {
				returnVal = String
						.valueOf(digits
								.format((new Double(num1Int) / new Double(
										num2Int)) * 100));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return returnVal + "%";
		} else {
			return "0%";
		}
	}
	
	/**
	 * 取百分比
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static String getPercentValue(String num1, String num2) {
		int num1Int = getStrToInt(num1);
		int num2Int = getStrToInt(num2);
		return getPercentValue(num1Int, num2Int);
	}
	
	private static VelocityStringUtil instance = null;
	
	public static synchronized VelocityStringUtil getInstance() {
		if (instance == null) {
			instance = new VelocityStringUtil();
		}
		return instance;
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
	
	/*
	 * 转义null字符串
	 */
	public static String escapeNull(String str) {
		if (str == null || str.equals("")) {
			return "";
		} else {
			return str;
		}
	}
	
	/**
	 * 将长词加上空格字符
	 * 
	 * @param str
	 * @param length
	 * @return
	 */
	public static String sliceTooLongWord(String str, int length) {
		String reg = "(\\S){" + length + "}?";
		return str.replaceAll(reg, "$0 ");
	}
	
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
	 * 得到一个字符串的显示长度,一个汉字或日韩文长度为2,英文字符长度为1
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
	
	/**
	 * 截取字符串子串，末尾补more字符串
	 * 
	 * @param origin
	 * @param len
	 * @param more
	 * @return
	 */
	public static String substring(String origin, int len, String more) {
		if (origin == null || origin.equals("") || len < 1)
			return "";
		if (len >= origin.length()) {
			return origin;
		}
		
		if (more != null) {
			String retS = "";
			retS = origin.substring(0, len - more.length()) + more;
			return retS;
		} else {
			String retS = "";
			retS = origin.substring(0, len);
			return retS;
		}
	}
	
	/**
	 * 截取一段字符的长度,区分中英文,如果数字不正好，则少取一个字符位
	 * 
	 * @param String
	 *            origin, 原始字符串
	 * @param int
	 *            len, 截取长度(一个汉字长度按2算的)
	 * @return String, 返回的字符串
	 */
	public static String cnsubstring(String origin, int len, String more) {
		if (origin == null || origin.equals("") || len < 1)
			return "";

		if (len > length(origin)) {
			return origin;
		}

		int moreLength = 0;

		if (more != null) {
			moreLength = length(more);
		}

		int count = 0;
		int j = 0;
		for (int i = 0; i < origin.length() && j < len - moreLength; i++) {
			char value = origin.charAt(i);
			if (!isLetter(value)) {
				j = j + 2;
			} else {
				j = j + 1;
			}
			count++;
		}

		if (more != null) {
			return origin.substring(0, count) + more;
		} else {
			return origin.substring(0, count);
		}
	}

	/**
	 * html 按text输出
	 * 
	 * @param inputString
	 * @return
	 */
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

	public static String getUrlParameterEncodeStr(String para) {
		if (StringUtils.isNotEmpty("para")) {
			try {
				String encodePara = java.net.URLEncoder.encode(para,
						"unicode-16");
				// encodePara = StringUtil.Replace(encodePara, "%2F", "%252F");
				return encodePara;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return "";
			}
		} else {
			return "";
		}
	}

	private final static byte[] val = { 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x00, 0x01,
			0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F };

	private final static String[] hex = { "00", "01", "02", "03", "04", "05",
			"06", "07", "08", "09", "0A", "0B", "0C", "0D", "0E", "0F", "10",
			"11", "12", "13", "14", "15", "16", "17", "18", "19", "1A", "1B",
			"1C", "1D", "1E", "1F", "20", "21", "22", "23", "24", "25", "26",
			"27", "28", "29", "2A", "2B", "2C", "2D", "2E", "2F", "30", "31",
			"32", "33", "34", "35", "36", "37", "38", "39", "3A", "3B", "3C",
			"3D", "3E", "3F", "40", "41", "42", "43", "44", "45", "46", "47",
			"48", "49", "4A", "4B", "4C", "4D", "4E", "4F", "50", "51", "52",
			"53", "54", "55", "56", "57", "58", "59", "5A", "5B", "5C", "5D",
			"5E", "5F", "60", "61", "62", "63", "64", "65", "66", "67", "68",
			"69", "6A", "6B", "6C", "6D", "6E", "6F", "70", "71", "72", "73",
			"74", "75", "76", "77", "78", "79", "7A", "7B", "7C", "7D", "7E",
			"7F", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89",
			"8A", "8B", "8C", "8D", "8E", "8F", "90", "91", "92", "93", "94",
			"95", "96", "97", "98", "99", "9A", "9B", "9C", "9D", "9E", "9F",
			"A0", "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "AA",
			"AB", "AC", "AD", "AE", "AF", "B0", "B1", "B2", "B3", "B4", "B5",
			"B6", "B7", "B8", "B9", "BA", "BB", "BC", "BD", "BE", "BF", "C0",
			"C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "CA", "CB",
			"CC", "CD", "CE", "CF", "D0", "D1", "D2", "D3", "D4", "D5", "D6",
			"D7", "D8", "D9", "DA", "DB", "DC", "DD", "DE", "DF", "E0", "E1",
			"E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "EA", "EB", "EC",
			"ED", "EE", "EF", "F0", "F1", "F2", "F3", "F4", "F5", "F6", "F7",
			"F8", "F9", "FA", "FB", "FC", "FD", "FE", "FF" };

	/**
	 * js escape 实现
	 * 
	 * @param s
	 * @return
	 */
	public static String javascriptEscape(String s) {
		StringBuffer sbuf = new StringBuffer();
		int len = s.length();
		for (int i = 0; i < len; i++) {
			int ch = s.charAt(i);
			if ('A' <= ch && ch <= 'Z') {
				sbuf.append((char) ch);
			} else if ('a' <= ch && ch <= 'z') {
				sbuf.append((char) ch);
			} else if ('0' <= ch && ch <= '9') {
				sbuf.append((char) ch);
			} else if (ch == '-' || ch == '_' || ch == '.' || ch == '!'
					|| ch == '~' || ch == '*' || ch == '\'' || ch == '('
					|| ch == ')') {
				sbuf.append((char) ch);
			} else if (ch <= 0x007F) {
				sbuf.append('%');
				sbuf.append(hex[ch]);
			} else {
				sbuf.append('%');
				sbuf.append('u');
				sbuf.append(hex[(ch >>> 8)]);
				sbuf.append(hex[(0x00FF & ch)]);
			}
		}
		return sbuf.toString();
	}

	public static String getDateLong(Date date) {
		String nowDate = "";
		try {
			if (date != null)
				nowDate = sdfLong.format(date);
			return nowDate;
		} catch (Exception e) {
			System.out.println("Error at getDate:" + e.getMessage());
			return "";
		}
	}

	public static String getDateLongCn(Date date) {
		String nowDate = "";
		try {
			if (date != null)
				nowDate = sdfLongCn.format(date);
			return nowDate;
		} catch (Exception e) {
			System.out.println("Error at getDate:" + e.getMessage());
			return "";
		}
	}

	/**
	 * js unescape 实现
	 * 
	 * @param s
	 * @return
	 */
	public static String javascriptUnescape(String s) {
		StringBuffer sbuf = new StringBuffer();
		int i = 0;
		int len = s.length();
		while (i < len) {
			int ch = s.charAt(i);
			if ('A' <= ch && ch <= 'Z') {
				sbuf.append((char) ch);
			} else if ('a' <= ch && ch <= 'z') {
				sbuf.append((char) ch);
			} else if ('0' <= ch && ch <= '9') {
				sbuf.append((char) ch);
			} else if (ch == '-' || ch == '_' || ch == '.' || ch == '!'
					|| ch == '~' || ch == '*' || ch == '\'' || ch == '('
					|| ch == ')') {
				sbuf.append((char) ch);
			} else if (ch == '%') {
				int cint = 0;
				if ('u' != s.charAt(i + 1)) {
					cint = (cint << 4) | val[s.charAt(i + 1)];
					cint = (cint << 4) | val[s.charAt(i + 2)];
					i += 2;
				} else {
					cint = (cint << 4) | val[s.charAt(i + 2)];
					cint = (cint << 4) | val[s.charAt(i + 3)];
					cint = (cint << 4) | val[s.charAt(i + 4)];
					cint = (cint << 4) | val[s.charAt(i + 5)];
					i += 5;
				}
				sbuf.append((char) cint);
			} else {
				sbuf.append((char) ch);
			}
			i++;
		}
		return sbuf.toString();
	}
	
	public static String getlastUrl(String articleUrl){
		if (null!=articleUrl && !"".equals(articleUrl))
		{
			if (articleUrl.lastIndexOf("/")>-1)
			{ 
				return articleUrl.substring(0,articleUrl.lastIndexOf("/")+1);
			}
			return "";
		}else{
			return "";
		}
	}
	
	public static String getStoryJinPingPingJie(String dataSummary,int index){
		String splitStr ="!@@!";
		if (dataSummary==null || dataSummary.equals(""))
			return "";
		if (index<1 )
			return "";
		
		String[] arry = dataSummary.split(splitStr);
		
		if (arry.length>=index)
		   return arry[index-1];
		return "";
	}
	
	/**
	 * 
	*    
	* 方法描述：比较double类型数据  
	* 创建人：hexiaoliang  
	* 创建时间：2010-4-19 下午02:48:00    
	* @param firstValue
	* @param twoValue
	* @return
	* @version   1.0
	*
	 */
	public static boolean compareDouble(double firstValue,double twoValue){
		if(firstValue>=twoValue)
			return true;
		else 
			return false;
	}
	
	public static boolean compDoubleString(double firstValue,String twoValue){
		if(firstValue>Double.valueOf(twoValue))
			return true;
		else 
			return false;
	}
	
	public static Double minusDoubleString(double firstValue,String twoValue){
		return firstValue-Double.valueOf(twoValue);
	}
	
	/**
	 * 
	*    
	* 方法描述：两个数相乘  
	* 创建人：hexiaoliang  
	* 创建时间：2010-4-20 上午11:00:47    
	* @param firstValue
	* @param twoValue
	* @return
	* @version   1.0
	*
	 */
	public static double calMulti(int firstValue,double twoValue){
		return firstValue*twoValue;
	}
	
	/**
	 * 
	*    
	* 方法描述：int型相减  
	* 创建人：hexiaoliang  
	* 创建时间：2010-4-20 下午10:24:52    
	* @param firstValue
	* @param twoValue
	* @return
	* @version   1.0
	*
	 */
	public static int calMunis(int firstValue,int twoValue){
		return (firstValue-twoValue);
	}
	
	public static String dateToStr(Date date){
		return timeLong.format(date);
	}
	
	/**
	 * 
	*    
	* 方法描述：胜负彩比较选择的号  
	* 创建人：hexiaoliang  
	* 创建时间：2010-5-9 下午09:49:06    
	* @param choseNum
	* @param baseNum
	* @return
	* @version   1.0
	*
	 */
	public static boolean compareSfcNum(String choseNum,String baseNum){
		if(choseNum.indexOf(baseNum)>=0){
			return true;
		}
		return false;
	}

	/*public static boolean compareGameType(String source,String target){
		return source.equals(target);
	}*/
	public static void main(String[] argv) {
		// %E4%B8%AD%E5%9B%BD%E9%98%B2%E5%A4%A7%E5%AD%A6
		/*System.out.println(javascriptEscape("手机游戏"));
		System.out.println(compareDouble(140,140));*/
		System.out.println(dateToStr(new Date()));
		System.out.println("aa");
	}

	public String getBankImgUrl(String bankId){
		if("ICBC-NET".equals(bankId))
			return "bank_icon ICBC";
		if("CMBCHINA-NET".equals(bankId))
			return "bank_icon CMB";
		if("CCB-NET".equals(bankId))
			return "bank_icon CCB";
		if("BOC-NET".equals(bankId))
			return "bank_icon BOC";
		if("ABC-NET".equals(bankId))
			return "bank_icon ABC";
		if("BOCO-NET".equals(bankId))
			return "bank_icon COMM";
		if("POST-NET".equals(bankId))
			return "bank_icon PSBC";
		if("GDB-NET".equals(bankId))
			return "bank_icon GDB";
		if("SDB-NET".equals(bankId))
			return "bank_icon SDB";
		
		if("BCCB-NET".equals(bankId))
			return "bank_icon BJBANK";
		if("CMBC-NET".equals(bankId))
			return "bank_icon CMBC";
		if("SHB-NET".equals(bankId))
			return "bank_icon SHBANK";
		if("CIB-NET".equals(bankId))
			return "bank_icon CIB";
		if("ECITIC-NET".equals(bankId))
			return "bank_icon CITIC";
		if("SPDB-NET".equals(bankId))
			return "bank_icon SPDB";
		if("CEB-NET".equals(bankId))
			return "bank_icon CEB";
						
		return "帮付宝余额支付";
	}
	
	public static String getBankName(String bankId){
		if("ICBC-NET".equals(bankId))
			return "中国工商银行";
		if("CMBCHINA-NET".equals(bankId))
			return "招商银行";
		if("CCB-NET".equals(bankId))
			return "中国建设银行";
		if("BOC-NET".equals(bankId))
			return "中国银行";
		if("ABC-NET".equals(bankId))
			return "中国农业银行";
		if("BOCO-NET".equals(bankId))
			return "交通银行";
		if("POST-NET".equals(bankId))
			return "中国邮政储蓄银行";
		if("GDB-NET".equals(bankId))
			return "广东发展银行";
		if("SDB-NET".equals(bankId))
			return "深圳发展银行";
		
		if("BCCB-NET".equals(bankId))
			return "北京银行";
		if("CMBC-NET".equals(bankId))
			return "中国民生银行";
		if("SHB-NET".equals(bankId))
			return "上海银行";
		if("CIB-NET".equals(bankId))
			return "兴业银行";
		if("ECITIC-NET".equals(bankId))
			return "中信银行";
		if("SPDB-NET".equals(bankId))
			return "上海浦东发展银行";
		if("CEB-NET".equals(bankId))
			return "光大银行";
						
		return "";
	}
	
	
	public static int fenToYuan(int amt){
		return amt/100;
	}
	
	public static String getErrCodeText(String code){
		int errCode = Integer.valueOf(code);
		if(errCode == 203)
			return "订单重复提交";
		
		if(errCode == 402)
			return "支付登录名不能为空";
		if(errCode == 403)
			return "支付登录名格式不正确";
		if(errCode == 407)
			return "支付密码不能空";
		if(errCode == 409 || errCode==410 || errCode==411)
			return "用户名错误";
		if(errCode == 412)
			return "用户本地账户空";
		
		if(errCode == 413)
			return "用户本地账户余额不足";
		if(errCode == 408)
			return "支付密码不正确";
		
		return "商家发起的请求错误，请回到来源站点，重新发起请求";
	}
}