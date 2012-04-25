package com.zhuozhuo.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Currency {
	public static String HanDigiStr[] =
		new String[] { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
	public static String HanDiviStr[] =
		new String[] {
			"",
			"拾",
			"佰",
			"仟",
			"万",
			"拾",
			"佰",
			"仟",
			"亿",
			"拾",
			"佰",
			"仟",
			"万",
			"拾",
			"佰",
			"仟",
			"亿",
			"拾",
			"佰",
			"仟",
			"万",
			"拾",
			"佰",
			"仟" };
	
	public static String formatCurrency(String amountString) {
        try {
            double amount = Double.parseDouble(amountString);
            return formatCurrency(amount);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    public static String formatCurrency(String amountString, Locale locale) {
        try {
            double amount = Double.parseDouble(amountString);
            return formatCurrency(amount, locale);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    public static String formatCurrency(double amount){
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        DecimalFormat df = (DecimalFormat)nf;
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(2);
        df.setDecimalSeparatorAlwaysShown(true);
        String pattern = "$###,###.00";
        df.applyPattern(pattern);
        return df.format(amount);
    }

    public static String formatPlainCurrency(double amount){
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        DecimalFormat df = (DecimalFormat)nf;
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(2);
        df.setDecimalSeparatorAlwaysShown(true);
        String pattern = "###,###";
        df.applyPattern(pattern);
        return df.format(amount);
    }

    public static String formatCurrency(double amount, Locale locale){
        NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
        return nf.format(amount);
    }
    
    public static String PositiveIntegerToHanStr(String NumStr) { // 输入字符串必须正整数，只允许前导空格(必须右对齐)，不宜有前导零
		String RMBStr = "";
		boolean lastzero = false;
		boolean hasvalue = false; // 亿、万进位前有数值标记
		int len, n;
		len = NumStr.length();
		if (len > 15)
			return "数值过大!";
		for (int i = len - 1; i >= 0; i--) {
			if (NumStr.charAt(len - i - 1) == ' ')
				continue;
			n = NumStr.charAt(len - i - 1) - '0';
			if (n < 0 || n > 9)
				return "输入含非数字字符!";

			if (n != 0) {
				if (lastzero)
					RMBStr += HanDigiStr[0]; // 若干零后若跟非零值，只显示一个零
				// 除了亿万前的零不带到后面
				//if( !( n==1 && (i%4)==1 && (lastzero || i==len-1) ) )    // 如十进位前有零也不发壹音用此行
				//if (!(n == 1 && (i % 4) == 1 && i == len - 1)) // 十进位处于第一位不发壹音
					RMBStr += HanDigiStr[n];
				RMBStr += HanDiviStr[i]; // 非零值后加进位，个位为空
				hasvalue = true; // 置万进位前有值标记

			} else {
				if ((i % 8) == 0
					|| ((i % 8) == 4 && hasvalue)) // 亿万之间必须有非零值方显示万
					RMBStr += HanDiviStr[i]; // “亿”或“万”
			}
			if (i % 8 == 0)
				hasvalue = false; // 万进位前有值标记逢亿复位
			lastzero = (n == 0) && (i % 4 != 0);
		}

		if (RMBStr.length() == 0)
			return HanDigiStr[0]; // 输入空字符或"0"，返回"零"
		return RMBStr;
	}

	public static String NumToRMBStr(double val) {
		String SignStr = "";
		String TailStr = "";
		long fraction, integer;
		int jiao, fen;

		if (val < 0) {
			val = -val;
			SignStr = "负";
		}
		if (val > 99999999999999.999 || val < -99999999999999.999)
			return "数值位数过大!";
		// 四舍五入到分  
		long temp = Math.round(val * 100);
		integer = temp / 100;
		fraction = temp % 100;
		jiao = (int) fraction / 10;
		fen = (int) fraction % 10;
		if (jiao == 0 && fen == 0) {
			TailStr = "整";
		} else {
			TailStr = HanDigiStr[jiao];
			if (jiao != 0)
				TailStr += "角";
			if (integer == 0 && jiao == 0) // 零元后不写零几分
				TailStr = "";
			if (fen != 0)
				TailStr += HanDigiStr[fen] + "分";
		}

		// 下一行可用于非正规金融场合，0.03只显示“叁分”而不是“零元叁分”
		//        if( !integer ) return  SignStr+TailStr;

		return "￥"
			+ SignStr
			+ PositiveIntegerToHanStr(String.valueOf(integer))
			+ "元"
			+ TailStr;
	}

	/**
		 * 
		 * @param val 要格式化的数值
		 * @param len 长度 就是 拾佰仟万拾佰仟 一共有多少位 不是返回字符串的长度
		 * @param space 中间的字符串
		 * @return 返回一个字符串，每个度量中间加 space字符串
		 * 
		 */

	public static String RMBToStrForPrint(double val, int len, String space) {

		String RMBStr = "";
		if (val < 0) {
			val = -val;
			RMBStr = "负";
		}
		if (val > 99999999999999.999 || val < -99999999999999.999)
			return "";

		long temp = Math.round(val * 100);
		String integer = String.valueOf(temp / 100); //整数部分
		long fraction = temp % 100;
		int jiao = (int) fraction / 10;
		int fen = (int) fraction % 10;
		//String fraction = String.valueOf(temp % 100); //小数部分

		//已进行到哪一位
		int index = 0;
		//整数部分大于要的长度 把多的长度全部显示
		if (integer.length() > len-2) {
			for (; index < integer.length() - len + 2; index++) {
				int n = integer.charAt(index) - '0';
				RMBStr += HanDigiStr[n];				
			}
		} else if (integer.length() < len - 2) {
			//整数部分小于长度 把前面加上 space
			for (int i = 0; i < len - integer.length() -2; i++) {
				RMBStr +=  "零" + space ;
			}
		}

		for (; index < integer.length(); index++) {
			int n = integer.charAt(index) - '0';
			RMBStr += "&nbsp;" + HanDigiStr[n] + space;
		}

		
		RMBStr += HanDigiStr[jiao] + space; // 加角
		RMBStr += HanDigiStr[fen] + "&nbsp;"; // 加分
		
		
		return RMBStr;
	}
	
	public static void main(String[] args){
		System.out.println(Currency.NumToRMBStr(3310.32));
		System.out.println(Currency.RMBToStrForPrint(10.00,6," "));
	}
}
