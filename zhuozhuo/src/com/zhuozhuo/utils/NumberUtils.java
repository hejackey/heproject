package com.zhuozhuo.utils;

import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * 数字工具类，主要提供浮点型、整型、货币、百分比和通用Number类型的格式化和解析功能。
 * 
 * @since 1.0
 */
public final class NumberUtils {
	private NumberUtils() {
	}
	/** 默认浮点型数字（float、double）格式 */
	public static final String DEFAULT_DOUBLE_PATTERN = "#,##0.00";
	/** 默认整型数字（short、int、long）格式 */
	public static final String DEFAULT_LONG_PATTERN = "#,##0";
	/** 默认货币格式 */
	public static final String DEFAULT_CURRENCY_PATTERN = "#,##0.00";
	/** 默认百分比格式 */
	public static final String DEFAULT_PERCENT_PATTERN = "#,##0.00%";

	/**
	 * 格式化双精度数字为“#,##0.00”标准格式
	 *
	 * @param d
	 *            需要格式化的双精度数字
	 * @return 格式化过的字符串
	 * @since 1.0
	 *
	 *
	 * <pre>
	 *   NumberUtil.format(0.0049d)输出结果：0.00
	 *   NumberUtil.format(0.0050d)输出结果：0.00
	 *   NumberUtil.format(0.0051d)输出结果：0.01
	 *   NumberUtil.format(9999990.9d)输出结果：9,999,990.90
	 * </pre>
	 */
	public static String format(double d) {
		return format(d, DEFAULT_DOUBLE_PATTERN);
	}

	/**
	 * 按照指定的<code>pattern</code>格式化双精度数字； 如果<code>pattern</code>为<code>null</code>或<code>""</code>则其行为同
	 * <code>format(double d)</code>。
	 *
	 * @param d
	 *            需要格式化的双精度数字
	 * @param pattern
	 *            数字格式
	 * @return 格式化过的字符串
	 * @since 1.0
	 *
	 *
	 * <pre>
	 *   NumberUtil.format(9999990.9d, null)输出结果：9,999,990.90
	 *   NumberUtil.format(9999990.9d, &quot;&quot;)输出结果：9,999,990.90
	 *   NumberUtil.format(9999990.9d, &quot;###0.00&quot;)输出结果：9999990.90
	 * </pre>
	 *
	 */
	public static String format(double d, String pattern) {
		if (pattern == null || "".equals(pattern))
			return format(d);
		else {
			DecimalFormat df = new DecimalFormat(pattern);
			return df.format(d);
		}
	}

	/**
	 * 格式化长整型数字为“#,##0”格式
	 *
	 * @param l
	 *            需要格式化的长整型数字
	 * @return 格式化过的字符串
	 * @since 1.0
	 *
	 *
	 * <pre>
	 *   NumberUtil.format(9999990L)输出结果：9,999,990
	 * </pre>
	 */
	public static String format(long l) {
		return format(l, DEFAULT_LONG_PATTERN);
	}

	/**
	 * 按照指定的<code>pattern</code>格式化长整型数字； 如果<code>pattern</code>为<code>null</code>或<code>""</code>则其行为同
	 * <code>format(long l)</code>。
	 *
	 * @param l
	 *            需要格式化的长整型数字
	 * @param pattern
	 *            数字格式
	 * @return 格式化过的字符串
	 * @since 1.0
	 *
	 *
	 * <pre>
	 *   NumberUtil.format(9999990L, null)输出结果：9,999,990
	 *   NumberUtil.format(9999990L, &quot;&quot;);输出结果：9,999,990
	 *   NumberUtil.format(9999990L, &quot;###0&quot;);输出结果：9999990
	 * </pre>
	 */
	public static String format(long l, String pattern) {
		if (pattern == null || "".equals(pattern))
			return format(l);
		else {
			DecimalFormat df = new DecimalFormat(pattern);
			return df.format(l);
		}
	}

	/**
	 * 格式化双精度数字类为“#,##0.00”标准格式
	 *
	 * @param d
	 *            双精度数字类
	 * @return 格式化过的字符串，否则null。
	 * @since 1.0
	 *
	 *
	 * <pre>
	 *   NumberUtil.format((Double) null)输出结果：null
	 *   NumberUtil.format(Double.valueOf(9999990.9d))输出结果：9,999,990.90
	 * </pre>
	 */
	public static String format(Double d) {
		if (d == null) {
			return null;
		} else {
			return format(d.doubleValue());
		}
	}

	/**
	 * 按照指定的<code>pattern</code>格式化双精度数字类。
	 *
	 * @param d
	 *            双精度数字类
	 * @return 返回格式化过的字符串，否则null。
	 * @see #format(double, String)
	 * @since 1.0
	 *
	 *
	 * <pre>
	 *   NumberUtil.format((Double) null, &quot;###0.00&quot;)输出结果：null
	 *   NumberUtil.format(Double.valueOf(9999990.9d), &quot;###0.00&quot;)输出结果：9999990.90
	 * </pre>
	 */
	public static String format(Double d, String pattern) {
		if (d == null) {
			return null;
		} else {
			return format(d.doubleValue(), pattern);
		}
	}

	/**
	 * 格式化单精度数字类为“#,##0.00”标准格式
	 *
	 * @param f
	 *            单精度数字类
	 * @return 格式化过的字符串，否则null。
	 * @since 1.0
	 *
	 *
	 * <pre>
	 *   NumberUtil.format((Float) null)输出结果：null
	 *   NumberUtil.format(Float.valueOf(9990.9f))输出结果：9,990.90
	 * </pre>
	 */
	public static String format(Float f) {
		if (f == null) {
			return null;
		} else {
			return format(f.floatValue());
		}
	}

	/**
	 * 按照指定的<code>pattern</code>格式化单精度数字类。
	 *
	 * @param f
	 *            单精度数字类
	 * @return 返回格式化过的字符串，否则null。
	 * @see #format(double, String)
	 * @since 1.0
	 *
	 *
	 * <pre>
	 *   NumberUtil.format((Float) null, &quot;###0.00&quot;)输出结果：null
	 *   NumberUtil.format(Float.valueOf(9990.9f), &quot;###0.00&quot;)输出结果：9990.90
	 * </pre>
	 */
	public static String format(Float f, String pattern) {
		if (f == null) {
			return null;
		} else {
			return format(f.floatValue(), pattern);
		}
	}

	/**
	 * 格式化长整型数字类为“#,##0”格式
	 *
	 * @param l
	 *            长整形数字类
	 * @return 格式化过的字符串，否则null。
	 * @since 1.0
	 *
	 *
	 * <pre>
	 *   NumberUtil.format((Long) null);输出结果：null
	 *   NumberUtil.format(Long.valueOf(9999990L));输出结果：9,999,990
	 *   &lt;pre&gt;
	 *
	 */
	public static String format(Long l) {
		if (l == null) {
			return null;
		} else {
			return format(l.longValue());
		}
	}

	/**
	 * 按照指定的<code>pattern</code>格式化长整型数字类。
	 *
	 * @param l
	 *            长整型数字类
	 * @return 返回格式化过的字符串，否则null。
	 * @see #format(long, String)
	 * @since 1.0
	 *
	 *
	 * <pre>
	 *   NumberUtil.format((Long) null, &quot;###0&quot;);输出结果：null
	 *   NumberUtil.format(Long.valueOf(9999990L), &quot;###0&quot;);输出结果：9999990
	 * </pre>
	 */
	public static String format(Long l, String pattern) {
		if (l == null) {
			return null;
		} else {
			return format(l.longValue(), pattern);
		}
	}

	/**
	 * 格式化整型数字类为“#,##0”格式
	 *
	 * @param i
	 *            整形数字类
	 * @return 格式化过的字符串，否则null。
	 * @since 1.0
	 *
	 *
	 * <pre>
	 *   NumberUtil.format((Integer) null)输出结果：null
	 *   NumberUtil.format(Integer.valueOf(9999990));输出结果：9,999,990
	 * </pre>
	 */
	public static String format(Integer i) {
		if (i == null) {
			return null;
		} else {
			return format(i.intValue());
		}
	}

	/**
	 * 按照指定的<code>pattern</code>格式化整型数字类。
	 *
	 * @param i
	 *            整型数字类
	 * @return 返回格式化过的字符串，否则null。
	 * @see #format(long, String)
	 * @since 1.0
	 *
	 *
	 * <pre>
	 *   NumberUtil.format((Integer) null, &quot;###0&quot;);输出结果：null
	 *   NumberUtil.format(Integer.valueOf(9999990), &quot;###0&quot;);输出结果：9999990
	 * </pre>
	 */
	public static String format(Integer i, String pattern) {
		if (i == null) {
			return null;
		} else {
			return format(i.intValue(), pattern);
		}
	}

	/**
	 * 格式化短整型数字类为“#,##0”格式
	 *
	 * @param s
	 *            短整形数字类
	 * @return 格式化过的字符串，否则null。
	 * @since 1.0
	 *
	 *
	 * <pre>
	 *   NumberUtil.format((Short) null)输出结果：null
	 *   NumberUtil.format(new Short(&quot;9990&quot;))输出结果：9,990
	 * </pre>
	 */
	public static String format(Short s) {
		if (s == null) {
			return null;
		} else {
			return format(s.shortValue());
		}
	}

	/**
	 * 按照指定的<code>pattern</code>格式化短整型数字类。
	 *
	 * @param s
	 *            短整型数字类
	 * @return 返回格式化过的字符串，否则null。
	 * @see #format(long, String)
	 * @since 1.0
	 *
	 *
	 * <pre>
	 *   NumberUtil.format((Short) null, &quot;###0&quot;)输出结果：null
	 *   NumberUtil.format(new Short(&quot;9990&quot;), &quot;###0&quot;)输出结果：9990
	 * </pre>
	 */
	public static String format(Short s, String pattern) {
		if (s == null) {
			return null;
		} else {
			return format(s.shortValue(), pattern);
		}
	}

	/**
	 * 格式化Number数字类为“#,##0.00”标准格式
	 *
	 * @param number
	 *            需要格式化的Number数字类
	 * @return 格式化过的字符串
	 * @since 1.0
	 *
	 *
	 * <pre>
	 *   //以BigDecimal为例
	 *   NumberUtil.format(new BigDecimal(0.0049d))输出结果：0.00
	 *   NumberUtil.format(new BigDecimal(0.0050d)输出结果：0.01
	 *   NumberUtil.format(new BigDecimal(0.0051d)输出结果：0.01
	 *   NumberUtil.format(new BigDecimal(9999990.9d)输出结果：9,999,990.90
	 * </pre>
	 */
	public static String format(Number number) {
		return format(number, "#,##0.00");
	}

	/**
	 * 按照指定的<code>pattern</code>格式化Number数字类； 如果<code>pattern</code>为<code>null</code>或<code>""</code>则其行为同
	 * <code>format(Number number)</code>。
	 *
	 * @param number
	 *            需要格式化的Number数字类
	 * @param pattern
	 *            数字格式
	 * @return 格式化过的字符串
	 * @since 1.0
	 *
	 *
	 * <pre>
	 *   //以BigDecimal为例
	 *   NumberUtil.format((BigDecimal)null, &quot;###0&quot;)输出结果：null
	 *   NumberUtil.format(new BigDecimal(9999990.9d), null)输出结果：9,999,990.90
	 *   NumberUtil.format(new BigDecimal(9999990.9d), &quot;&quot;)输出结果：9,999,990.90
	 *   NumberUtil.format(new BigDecimal(9999990.9d), &quot;###0.00&quot;)输出结果：9999990.90
	 * </pre>
	 */
	public static String format(Number number, String pattern) {
		if (number == null)
			return null;
		if (pattern == null || "".equals(pattern))
			return format(number);
		else {
			DecimalFormat df = new DecimalFormat(pattern);
			return df.format(number);
		}
	}

	/**
	 * 格式化数字num为“#,##0.00%”标准百分比格式
	 *
	 * @param num
	 *            需要格式化的数字
	 * @return 格式化过的字符串
	 * @since 1.0
	 *
	 *
	 * <pre>
	 *   NumberUtil.formatPercent(0.99d)输出结果：99.00%
	 *   NumberUtil.formatPercent(9999990.9d)输出结果：999,999,090.00%
	 * </pre>
	 */
	public static String formatPercent(double num) {
		return format(num, DEFAULT_PERCENT_PATTERN);
	}

	/**
	 * 格式化数字类number为“#,##0.00%”标准百分比格式
	 *
	 * @param number
	 *            需要格式化的Number数字类
	 * @return 格式化过的字符串，否则null
	 * @since 1.0
	 *
	 *
	 * <pre>
	 *   NumberUtil.formatPercent((Number) null)输出结果：null
	 *   NumberUtil.formatPercent(Double.valueOf(9999990.9d))输出结果：999,999,090.00%
	 * </pre>
	 */
	public static String formatPercent(Number number) {
		return format(number, DEFAULT_PERCENT_PATTERN);
	}

	/**
	 * 格式化数字num为“#,##0.00”标准货币格式
	 *
	 * @param num
	 *            需要格式化的数字
	 * @return 格式化过的字符串
	 * @since 1.0
	 *
	 *
	 * <pre>
	 *   NumberUtil.formatCurrency(9999990.9d)输出结果：9,999,990.90
	 * </pre>
	 */
	public static String formatCurrency(double num) {
		return format(num, DEFAULT_CURRENCY_PATTERN);
	}

	/**
	 * 格式化数字num为“#,##0.00”标准货币格式
	 *
	 * @param number
	 *            需要格式化的Number数字类
	 * @return 格式化过的字符串，否则null
	 * @since 1.0
	 *
	 *
	 * <pre>
	 *   NumberUtil.formatCurrency((Number) null)输出结果：null
	 *   NumberUtil.formatCurrency(Double.valueOf(9999990.9d))输出结果：9,999,990.90
	 * </pre>
	 */
	public static String formatCurrency(Number number) {
		return format(number, DEFAULT_CURRENCY_PATTERN);
	}

	/**
	 * 解析数字字符串为<code>Number</code>类型
	 *
	 * @param source
	 *            待解析数字字符串
	 * @param pattern
	 *            数字字符串的格式
	 * @return 成功解析后的Number；当source或pattern为null或空时，返回null。
	 * @throws ParseException
	 * @since 1.0
	 *
	 *
	 * <pre>
	 *   NumberUtil.parse((String) null, (String) null)输出结果：null
	 *   NumberUtil.parse((String) null, &quot;&quot;)输出结果：null
	 *   NumberUtil.parse(&quot;&quot;, (String) null)输出结果：null
	 *   NumberUtil.parse(&quot;&quot;, &quot;&quot;)输出结果：null
	 *   NumberUtil.parse(&quot;9999990.90&quot;, &quot;###0.00&quot;)输出结果：值为9999990.9的Number
	 * </pre>
	 */
	public static Number parse(String source, String pattern) throws ParseException {
		if (source == null || "".equals(source) || pattern == null || "".equals(pattern))
			return null;
		DecimalFormat df = new DecimalFormat(pattern);
		return df.parse(source);
	}

	/**
	 * 解析格式为“#,##0.00”的数字字符串为<code>Float</code>类型
	 *
	 * @param source
	 *            待解析数字字符串，格式为“#,##0.00”
	 * @return 成功解析后的Float；当source为null或空时，返回null。
	 * @throws ParseException
	 * @since 1.0
	 *
	 *
	 * <pre>
	 *   NumberUtil.parseFloat((String) null)输出结果：null
	 *   NumberUtil.parseFloat(&quot;9,999,990.00&quot;)输出结果：值为9999990.0的Float
	 * </pre>
	 */
	public static Float parseFloat(String source) throws ParseException {
		Number number = parse(source, DEFAULT_DOUBLE_PATTERN);
		if (number == null)
			return null;
		else
			return new Float(number.floatValue());
	}

	/**
	 * 解析格式为“#,##0.00”数字字符串为<code>Double</code>类型
	 *
	 * @param source
	 *            待解析数字字符串，格式为“#,##0.00”
	 * @return 成功解析后的Double；当source为null或空时，返回null。
	 * @throws ParseException
	 * @since 1.0
	 *
	 *
	 * <pre>
	 *   NumberUtil.parseDouble((String) null)输出结果：null
	 *   NumberUtil.parseDouble(&quot;9,999,990.90&quot;)输出结果：值为9999990.9的Double
	 * </pre>
	 */
	public static Double parseDouble(String source) throws ParseException {
		Number number = parse(source, DEFAULT_DOUBLE_PATTERN);
		if (number == null)
			return null;
		else
			return new Double(number.doubleValue());
	}

	/**
	 * 解析格式为“#,##0”数字字符串为<code>Short</code>类型
	 *
	 * @param source
	 *            待解析数字字符串，格式为“#,##0”
	 * @return 成功解析后的Short；当source为null或空时，返回null。
	 * @throws ParseException
	 * @since 1.0
	 *
	 *
	 * <pre>
	 *   NumberUtil.parseShort((String) null)输出结果：null
	 *   NumberUtil.parseShort(&quot;9,990&quot;)输出结果：值为9990的Short
	 * </pre>
	 */
	public static Short parseShort(String source) throws ParseException {
		Number number = parse(source, DEFAULT_LONG_PATTERN);
		if (number == null)
			return null;
		else
			return new Short(number.shortValue());
	}

	/**
	 * 解析格式为“#,##0”数字字符串为<code>Integer</code>类型
	 *
	 * @param source
	 *            待解析数字字符串，格式为“#,##0”
	 * @return 成功解析后的Integer；当source为null或空时，返回null。
	 * @throws ParseException
	 * @since 1.0
	 *
	 *
	 * <pre>
	 *   NumberUtil.parseInteger((String) null)输出结果：null
	 *   NumberUtil.parseInteger(&quot;9,999,990&quot;)输出结果：值为9999990的Integer
	 * </pre>
	 */
	public static Integer parseInteger(String source) throws ParseException {
		Number number = parse(source, DEFAULT_LONG_PATTERN);
		if (number == null)
			return null;
		else
			return new Integer(number.intValue());
	}

	/**
	 * 解析格式为“#,##0”数字字符串为<code>Long</code>类型
	 *
	 * @param source
	 *            待解析数字字符串，格式为“#,##0”
	 * @return 成功解析后的Long；当source为null或空时，返回null。
	 * @throws ParseException
	 * @since 1.0
	 *
	 *
	 * <pre>
	 *   NumberUtil.parseLong((String) null)输出结果：null
	 *   NumberUtil.parseLong(&quot;9,999,990&quot;)输出结果：值为9999990的Long
	 * </pre>
	 */
	public static Long parseLong(String source) throws ParseException {
		Number number = parse(source, DEFAULT_LONG_PATTERN);
		if (number == null)
			return null;
		else
			return new Long(number.longValue());
	}

	/**
	 * 解析格式为“#,##0.00”数字字符串为<code>Number</code>类型
	 *
	 * @param source
	 *            待解析数字字符串，格式为“#,##0.00”
	 * @return 成功解析后的Number；当source为null或空时，返回null。
	 * @throws ParseException
	 * @since 1.0
	 *
	 *
	 * <pre>
	 *   NumberUtil.parseCurrency((String) null)输出结果：null
	 *   NumberUtil.parseCurrency(&quot;9,999,990.90&quot;)输出结果：值为9999990.9的Number
	 * </pre>
	 */
	public static Number parseCurrency(String source) throws ParseException {
		return parse(source, DEFAULT_CURRENCY_PATTERN);
	}

	/**
	 * 解析格式为“#,##0.00%”数字字符串为<code>Number</code>类型
	 *
	 * @param source
	 *            待解析数字字符串，格式为“#,##0.00%”
	 * @return 成功解析后的Number；当source为null或空时，返回null。
	 * @throws ParseException
	 * @since 1.0
	 *
	 *
	 * <pre>
	 *   NumberUtil.parsePercent((String) null)输出结果：null
	 *   NumberUtil.parsePercent(&quot;99.00%&quot;)输出结果：值为0.99的Number
	 *   NumberUtil.parsePercent(&quot;999,999,090.00%&quot;)输出结果：值为9999990.9的Number
	 * </pre>
	 */
	public static Number parsePercent(String source) throws ParseException {
		return parse(source, DEFAULT_PERCENT_PATTERN);
	}
}
