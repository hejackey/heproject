package com.apply.b2b.cms.util;

import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

public class DateUtil {
	static java.text.SimpleDateFormat sdfShort = new java.text.SimpleDateFormat(
			"yyyyMMdd");
	static java.text.SimpleDateFormat sdfLong = new java.text.SimpleDateFormat(
			"yyyy-MM-dd");
	static java.text.SimpleDateFormat sdfLongTime = new java.text.SimpleDateFormat(
			"yyyyMMddHHmmss");
	static java.text.SimpleDateFormat sdfLongTimePlus = new java.text.SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	static java.text.SimpleDateFormat sdfLongTimePlusMill = new java.text.SimpleDateFormat(
			"yyyyMMddHHmmssSSSS");

	public DateUtil() {
	}

	public static String getHourMsFromDate(Date date) {
		try {
			if (date == null)
				return "11:00";
			String dateTime = sdfLongTimePlus.format(date);

			return dateTime.substring(11, 16);

		} catch (Exception e) {
			e.printStackTrace();
			return (date == null) ? new Date().toString() : date.toString();
		}
	}

	public static String getFomartDate(Date date, String format) {
		try {
			return new SimpleDateFormat(format, Locale.UK).format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return (date == null) ? new Date().toString() : date.toString();
		}
	}

	public static String getNowLongTime() throws Exception {
		String nowTime = "";
		try {
			java.sql.Date date = null;
			date = new java.sql.Date(new java.util.Date().getTime());
			nowTime = sdfLongTime.format(date);
			return nowTime;
		} catch (Exception e) {
			System.out.println("Error at DateUtil.java getNowLongTime():"
					+ e.getMessage());
			throw e;
		}
	}

	public static String getLongTime(Date date) {
		String time = null;
		try {
			time = sdfLongTime.format(date);
		} catch (Exception e) {
			System.out.println("Error at DateUtil.java getNowLongTime():"
					+ e.getMessage());
		}
		return time;
	}

	public static String getNowShortDate() throws Exception {
		String nowDate = "";
		try {
			java.sql.Date date = null;
			date = new java.sql.Date(new java.util.Date().getTime());
			nowDate = sdfShort.format(date);
			return nowDate;
		} catch (Exception e) {
			System.out.println("Error at DateUtil.java getNowShortDate():"
					+ e.getMessage());
			throw e;
		}
	}

	public static String getNowFormateDate() throws Exception {
		String nowDate = "";
		try {
			java.sql.Date date = null;
			date = new java.sql.Date(new java.util.Date().getTime());
			nowDate = sdfLong.format(date);
			return nowDate;
		} catch (Exception e) {
			System.out.println("Error at DateUtil.java getNowFormateDate():"
					+ e.getMessage());
			throw e;
		}
	}

	public static String getNowPlusTime() throws Exception {
		String nowDate = "";
		try {
			java.sql.Date date = null;
			date = new java.sql.Date(new java.util.Date().getTime());
			nowDate = sdfLongTimePlus.format(date);
			return nowDate;
		} catch (Exception e) {
			System.out.println("Error at DateUtil.java getNowPlusTime():"
					+ e.getMessage());
			throw e;
		}
	}

	/**
	 * Descrption:ȡ�õ�ǰ���ڵ����뼫,��ʽΪ:yyyyMMddHHmmssSSSS
	 * 
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getNowPlusTimeMill() throws Exception {
		String nowDate = "";
		try {
			java.sql.Date date = null;
			date = new java.sql.Date(new java.util.Date().getTime());
			nowDate = sdfLongTimePlusMill.format(date);
			return nowDate;
		} catch (Exception e) {
			System.out.println("Error at DateUtil.java getNowPlusTimeMill():"
					+ e.getMessage());
			throw e;
		}
	}

	/**
	 * �õ���ǰ���ֵ:1900
	 * 
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getNowYear() throws Exception {
		String nowYear = "";
		try {
			String strTemp = getNowLongTime();
			nowYear = strTemp.substring(0, 4);
			return nowYear;
		} catch (Exception e) {
			System.out.println("Error at DateUtil.java getNowYear():"
					+ e.getMessage());
			throw e;
		}
	}

	/**
	 * �õ���ǰ�·�ֵ:12
	 * 
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getNowMonth() throws Exception {
		String nowMonth = "";
		try {
			String strTemp = getNowLongTime();
			nowMonth = strTemp.substring(4, 6);
			return nowMonth;
		} catch (Exception e) {
			System.out.println("Error at DateUtil.java getNowMonth():"
					+ e.getMessage());
			throw e;
		}
	}

	/**
	 * �õ���ǰ����ֵ:30
	 * 
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getNowDay() throws Exception {
		String nowDay = "";
		try {
			String strTemp = getNowLongTime();
			nowDay = strTemp.substring(6, 8);
			return nowDay;
		} catch (Exception e) {
			System.out.println("Error at DateUtil.java getNowDay():"
					+ e.getMessage());
			throw e;
		}
	}

	/**
	 * �õ���ǰСʱֵ:23
	 * 
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getNowHour() throws Exception {
		String nowHour = "";
		try {
			String strTemp = getNowPlusTimeMill();
			nowHour = strTemp.substring(8, 10);
			return nowHour;
		} catch (Exception e) {
			System.out.println("Error at DateUtil.java getNowHour():"
					+ e.getMessage());
			throw e;
		}
	}

	/**
	 * ��������ʱ����
	 * 
	 * @param _second
	 *            ����
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getTimeBySecond(String _second) throws Exception {
		String returnTime = "";
		long longHour = 0;
		long longMinu = 0;
		long longSec = 0;
		try {
			longSec = Long.parseLong(_second);
			if (longSec == 0) {
				returnTime = "0ʱ0��0��";
				return returnTime;
			}
			longHour = longSec / 3600; // ȡ��Сʱ��
			longSec = longSec % 3600; // ȡ�����µ���
			longMinu = longSec / 60; // ȡ�÷���
			longSec = longSec % 60; // ȡ�����µ���
			// System.out.println(longHour+"/"+longMinu+"/"+longSec);
			returnTime = longHour + "ʱ" + longMinu + "��" + longSec + "��";
			return returnTime;
		} catch (Exception e) {
			System.out.println("Error at DateUtil.java getTimeBySecond():"
					+ e.getMessage());
			throw e;
		}
	}

	/**
	 * �õ������е����
	 * 
	 * @param date
	 *            ����
	 * @return yyyy��ʽ�����
	 */
	public static int convertDateToYear(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy",
				new DateFormatSymbols());
		return Integer.parseInt(df.format(date));
	}

	/**
	 * �õ�������������ɵ��ַ�
	 * 
	 * @param d
	 *            ����
	 * @return yyyyMM��ʽ�������ַ�
	 */
	public static String convertDateToYearMonth(Date d) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM",
				new DateFormatSymbols());
		return df.format(d);
	}

	/**
	 * �õ���������������ɵ��ַ�
	 * 
	 * @param d
	 *            ����
	 * @return yyyyMMdd��ʽ���������ַ�
	 */
	public static String convertDateToYearMonthDay(Date d) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd",
				new DateFormatSymbols());
		return df.format(d);
	}

	/**
	 * �õ������е��·�
	 * 
	 * @param date
	 *            ����
	 * @return yyyy��ʽ�����
	 */
	public static String convertDateToMonth(Date d) {
		SimpleDateFormat df = new SimpleDateFormat("MM",
				new DateFormatSymbols());
		return df.format(d);
	}

	public static String convertDateToDay(Date d) {
		SimpleDateFormat df = new SimpleDateFormat("dd",
				new DateFormatSymbols());
		return df.format(d);
	}

	public static Date getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		Date d = cal.getTime();
		return d;
	}

	public static String getCurrentYearMonth() {
		Calendar cal = Calendar.getInstance();
		String currentYear = (new Integer(cal.get(Calendar.YEAR))).toString();
		String currentMonth = null;
		if (cal.get(Calendar.MONTH) < 9)
			currentMonth = "0"
					+ (new Integer(cal.get(Calendar.MONTH) + 1)).toString();
		else
			currentMonth = (new Integer(cal.get(Calendar.MONTH) + 1))
					.toString();
		return (currentYear + currentMonth);
	}

	public static int getCurrentYear() {
		Calendar cal = Calendar.getInstance();
		int currentYear = cal.get(Calendar.YEAR);
		return currentYear;
	}

	public static String formatDateTime(String _dateTime, String _format)
			throws Exception {
		String returnValue = "";
		String formatString = _format.toUpperCase();
		String strYear = "";
		String strMonth = "";
		String strDay = "";
		String strHour = "";
		String strMinu = "";
		String strSec = "";
		int hourType = 12;
		int yearType = 1;
		try {
			if (formatString.indexOf("YYYY") >= 0) {
				int tempBeginPlace = formatString.indexOf("YYYY");
				int temEndPlace = tempBeginPlace + 4;
				strYear = _dateTime.substring(tempBeginPlace, temEndPlace);
			}
			if (formatString.indexOf("MM") >= 0) {
				int tempBeginPlace = formatString.indexOf("MM");
				int temEndPlace = tempBeginPlace + 2;
				strMonth = _dateTime.substring(tempBeginPlace, temEndPlace);
			}
			if (formatString.indexOf("DD") >= 0) {
				int tempBeginPlace = formatString.indexOf("DD");
				int temEndPlace = tempBeginPlace + 2;
				strDay = _dateTime.substring(tempBeginPlace, temEndPlace);
			}
			if (formatString.indexOf("HH24") >= 0) {
				int tempBeginPlace = formatString.indexOf("HH24");
				int temEndPlace = tempBeginPlace + 2;
				strHour = _dateTime.substring(tempBeginPlace, temEndPlace);
				formatString = formatString.replaceAll("24", "");
				// Ϊ�˱���λ��һ��,ȥ��24
				hourType = 24;
			} else if (formatString.indexOf("HH12") >= 0) {
				int tempBeginPlace = formatString.indexOf("HH12");
				int temEndPlace = tempBeginPlace + 2;
				strHour = _dateTime.substring(tempBeginPlace, temEndPlace);
				formatString = formatString.replaceAll("12", "");
				// Ϊ�˱���λ��һ��,ȥ��12
				hourType = 12;
			} else if (formatString.indexOf("HH") >= 0) {
				int tempBeginPlace = formatString.indexOf("HH");
				int temEndPlace = tempBeginPlace + 2;
				strHour = _dateTime.substring(tempBeginPlace, temEndPlace);
				hourType = 12; // ���δָ��Сʱ��,��Ĭ��Ϊ12Сʱ��;
			}
			if (formatString.indexOf("MI") >= 0) {
				int tempBeginPlace = formatString.indexOf("MI");
				int temEndPlace = tempBeginPlace + 2;
				strMinu = _dateTime.substring(tempBeginPlace, temEndPlace);
			}
			if (formatString.indexOf("SS") >= 0) {
				int tempBeginPlace = formatString.indexOf("SS");
				int temEndPlace = tempBeginPlace + 2;
				strSec = _dateTime.substring(tempBeginPlace, temEndPlace);
			}

			if (!strYear.equals("")) {
				int intYear = Integer.parseInt(strYear);
				if (intYear % 4 == 0) {
					if (intYear % 100 != 0) {
						yearType = 2;
					}
				}
				if (intYear % 4 == 0) {
					if (intYear % 400 == 0) {
						yearType = 2;
					}
				}
			}
			if (!strMonth.equals("")) {
				int intMonth = Integer.parseInt(strMonth);
				if (intMonth == 0) {
					strMonth = "01";
					intMonth = 1;
				}
				if (intMonth > 12) {
					strMonth = "12";
					intMonth = 12;
				}
			}

			if (!strDay.equals("")) {
				int intDay = Integer.parseInt(strDay);
				if (intDay == 0) {
					strDay = "01";
					intDay = 1;
				}
				if (intDay > 31) {
					strDay = "31";
					intDay = 31;
				}
				if ((strMonth.equals("01")) || (strMonth.equals("03"))
						|| (strMonth.equals("05")) || (strMonth.equals("07"))
						|| (strMonth.equals("08")) || (strMonth.equals("10"))
						|| (strMonth.equals("12"))) {
					if (intDay > 31) {
						strDay = "31";
						intDay = 31;
					}
				}
				if ((strMonth.equals("02")) || (strMonth.equals("04"))
						|| (strMonth.equals("06")) || (strMonth.equals("09"))
						|| (strMonth.equals("11"))) {
					if (intDay > 30) {
						strDay = "30";
						intDay = 30;
					}
					if (strMonth.equals("02")) {
						if (yearType == 2) {
							if (intDay > 29) {
								strDay = "29";
								intDay = 29;
							}
						} else {
							if (intDay > 28) {
								strDay = "28";
								intDay = 28;
							}
						}
					}
				}

				if (!strHour.equals("")) {
					int intHour = Integer.parseInt(strHour);
					if (intHour > 24) {
						strHour = "24";
						intHour = 24;
					}
					if (hourType == 12) {
						if (intHour == 0) {
							intHour = 1;
							strHour = "01";
						}
						if (intHour > 12) {
							intHour = intHour - 12;
							strHour = "0" + intHour;
						}
					} else {
						if (intHour > 23) {
							intHour = 23;
							strHour = "23";
						}
					}
				}
				if (!strMinu.equals("")) {
					int intMinu = Integer.parseInt(strMinu);
					if (intMinu > 59) {
						strMinu = "59";
						intMinu = 59;
					}
				}
				if (!strSec.equals("")) {
					int intSec = Integer.parseInt(strSec);
					if (intSec > 59) {
						strSec = "59";
						intSec = 59;
					}
				}
			}
			returnValue = strYear + strMonth + strDay + strHour + strMinu
					+ strSec;
			return returnValue;
		} catch (Exception e) {
			System.out.println("Error at DateUtil.java formatDateTime():"
					+ e.getMessage());
			throw e;
		}
	}

	public static java.sql.Date getDateByAge(int age) {
		Calendar calendar = Calendar.getInstance(Locale.CHINESE);
		calendar.set(calendar.get(Calendar.YEAR) - age, calendar
				.get(Calendar.MONTH), calendar.get(Calendar.DATE));
		return new java.sql.Date((calendar.getTimeInMillis()));
	}

	public static int calBetweenTwoMonth(String dealMonth, String alterMonth) {
		int length = 0;
		if ((dealMonth.length() != 6) || (alterMonth.length() != 6)) {
			length = -1;

		} else {
			int dealInt = Integer.parseInt(dealMonth);
			int alterInt = Integer.parseInt(alterMonth);
			if (dealInt < alterInt) {
				length = -2;
			} else {
				int dealYearInt = Integer.parseInt(dealMonth.substring(0, 4));
				int dealMonthInt = Integer.parseInt(dealMonth.substring(4, 6));
				int alterYearInt = Integer.parseInt(alterMonth.substring(0, 4));
				int alterMonthInt = Integer
						.parseInt(alterMonth.substring(4, 6));
				length = (dealYearInt - alterYearInt) * 12
						+ (dealMonthInt - alterMonthInt);
			}
		}

		return length;
	}

	public static int daysBetweenDates(Date newDate, Date oldDate) {
		int days = 0;
		Calendar calo = Calendar.getInstance();
		Calendar caln = Calendar.getInstance();
		calo.setTime(oldDate);
		caln.setTime(newDate);
		int oday = calo.get(Calendar.DAY_OF_YEAR);
		int nyear = caln.get(Calendar.YEAR);
		int oyear = calo.get(Calendar.YEAR);
		while (nyear > oyear) {
			calo.set(Calendar.MONTH, 11);
			calo.set(Calendar.DATE, 31);
			days = days + calo.get(Calendar.DAY_OF_YEAR);
			oyear = oyear + 1;
			calo.set(Calendar.YEAR, oyear);
		}
		int nday = caln.get(Calendar.DAY_OF_YEAR);
		days = days + nday - oday;

		return days;
	}

	public static Date getDateBetween(Date date, int intBetween) {
		Calendar calo = Calendar.getInstance();
		calo.setTime(date);
		calo.add(Calendar.DATE, intBetween);
		return calo.getTime();
	}

	public static String increaseYearMonth(String yearMonth) {
		int year = (new Integer(yearMonth.substring(0, 4))).intValue();
		int month = (new Integer(yearMonth.substring(4, 6))).intValue();
		month = month + 1;
		if (month <= 12 && month >= 10)
			return yearMonth.substring(0, 4) + (new Integer(month)).toString();
		else if (month < 10)
			return yearMonth.substring(0, 4) + "0"
					+ (new Integer(month)).toString();
		else
			// if(month>12)
			return (new Integer(year + 1)).toString() + "0"
					+ (new Integer(month - 12)).toString();

	}

	/**
	 * �õ����������ַ����ָ�������������ַ�
	 * 
	 * @param yearMonth
	 *            yyyyMM��ʽ����
	 * @param addMonth
	 *            ���ָ������
	 * @return yearMonth ���addMonth���º�����ڣ�yyyyMM��ʽ
	 */
	public static String increaseYearMonth(String yearMonth, int addMonth) {
		int year = (new Integer(yearMonth.substring(0, 4))).intValue();
		int month = (new Integer(yearMonth.substring(4, 6))).intValue();
		month = month + addMonth;
		year = year + month / 12;
		month = month % 12;
		if (month <= 12 && month >= 10)
			return year + (new Integer(month)).toString();
		else
			return year + "0" + (new Integer(month)).toString();

	}

	/**
	 * �õ����������ַ��ȥ1�º�������ַ�
	 * 
	 * @param yearMonth -
	 *            yyyyMM��ʽ
	 * @return - yearMonth����һ���µ����ڣ�yyyyMM��ʽ
	 */
	public static String descreaseYearMonth(String yearMonth) {
		int year = (new Integer(yearMonth.substring(0, 4))).intValue();
		int month = (new Integer(yearMonth.substring(4, 6))).intValue();
		month = month - 1;
		if (month >= 10)
			return yearMonth.substring(0, 4) + (new Integer(month)).toString();
		else if (month > 0 && month < 10)
			return yearMonth.substring(0, 4) + "0"
					+ (new Integer(month)).toString();
		else
			// if(month>12)
			return (new Integer(year - 1)).toString()
					+ (new Integer(month + 12)).toString();

	}

	/**
	 * �Ƚ�}�����������ڵĴ�С�����ڸ�ʽΪyyyyMM }���ִ�6λ��ǰ4����꣬��2����£� <br>
	 * IF ��һ�����ʱ�� > �ڶ������ʱ�䣬�����棬ELSE ���ؼ� <br>
	 * 
	 * @param s1
	 *            ����1
	 * @param s2
	 *            ����2
	 * @return boolean ���s1���ڵ���s2�򷵻��棬���򷵻ؼ�
	 */
	public static boolean yearMonthGreatEqual(String s1, String s2) {
		String temp1 = s1.substring(0, 4);
		String temp2 = s2.substring(0, 4);
		String temp3 = s1.substring(4, 6);
		String temp4 = s2.substring(4, 6);

		if (Integer.parseInt(temp1) > Integer.parseInt(temp2))
			return true;
		else if (Integer.parseInt(temp1) == Integer.parseInt(temp2)) {
			if (Integer.parseInt(temp3) >= Integer.parseInt(temp4))
				return true;
			else
				return false;
		} else
			return false;
	}

	/**
	 * �Ƚ�}�����������ڵĴ�С�����ڸ�ʽΪyyyyMM }���ִ�6λ��ǰ4����꣬��2����£� <br>
	 * IF ��һ�����ʱ�� > �ڶ������ʱ�䣬�����棬ELSE ���ؼ� <br>
	 * 
	 * @param s1
	 *            ����1
	 * @param s2
	 *            ����2
	 * @return boolean ���s1����s2�򷵻��棬���򷵻ؼ�
	 */
	public static boolean yearMonthGreater(String s1, String s2) {
		String temp1 = s1.substring(0, 4);
		String temp2 = s2.substring(0, 4);
		String temp3 = s1.substring(4, 6);
		String temp4 = s2.substring(4, 6);

		if (Integer.parseInt(temp1) > Integer.parseInt(temp2))
			return true;
		else if (Integer.parseInt(temp1) == Integer.parseInt(temp2)) {
			if (Integer.parseInt(temp3) > Integer.parseInt(temp4))
				return true;
			else
				return false;
		} else
			return false;
	}

	public static String getLastDay(String term) {

		int getYear = Integer.parseInt(term.substring(0, 4));
		int getMonth = Integer.parseInt(term.substring(4, 6));

		String getLastDay = "";

		if (getMonth == 2) {
			if (getYear % 4 == 0 && getYear % 100 != 0 || getYear % 400 == 0) {
				getLastDay = "29";
			} else {
				getLastDay = "28";
			}
		} else if (getMonth == 4 || getMonth == 6 || getMonth == 9
				|| getMonth == 11) {
			getLastDay = "30";
		} else {
			getLastDay = "31";
		}
		return String.valueOf(getYear) + "年" + String.valueOf(getMonth) + "月"
				+ getLastDay + "日";
	}

	public static String getMonthBetween(String strDateBegin, String strDateEnd) {
		try {
			int intMonthBegin;
			int intMonthEnd;
			String strOut;
			if (strDateBegin.equals("") || strDateEnd.equals("")
					|| strDateBegin.length() != 6 || strDateEnd.length() != 6)
				strOut = "";
			else {
				intMonthBegin = Integer.parseInt(strDateBegin.substring(0, 4))
						* 12 + Integer.parseInt(strDateBegin.substring(4, 6));
				intMonthEnd = Integer.parseInt(strDateEnd.substring(0, 4)) * 12
						+ Integer.parseInt(strDateEnd.substring(4, 6));
				strOut = String.valueOf(intMonthBegin - intMonthEnd);
			}
			return strOut;
		} catch (Exception e) {
			return "0";
		}
	}

	public static String getStrHaveAcross(String strDate) {
		try {
			return strDate.substring(0, 4) + "-" + strDate.substring(4, 6)
					+ "-" + strDate.substring(6, 8);
		} catch (Exception e) {
			return strDate;
		}
	}

	public static String getFirstDayOfNextMonth() {
		try {
			return increaseYearMonth(getNowShortDate().substring(0, 6)) + "01";
		} catch (Exception e) {
			System.out
					.println("Error at DateUtil.java getFirstDayOfNextMonth():"
							+ e.getMessage());
			return "";
		}
	}

	public static String getFirstDayOfThisMonth() {
		try {
			return getNowShortDate().substring(0, 6) + "01";
		} catch (Exception e) {
			System.out
					.println("Error at DateUtil.java getFirstDayOfThisMonth():"
							+ e.getMessage());
			return "";
		}
	}

	public static String getYearAndMonth(String yearMonth) {
		if (null == yearMonth)
			return "";
		String ym = yearMonth.trim();
		if (6 != ym.length())
			return ym;
		String year = ym.substring(0, 4);
		String month = ym.substring(4);
		return new StringBuffer(year).append("年").append(month).append("月")
				.toString();
	}

	public static String getYearMonthByMonth(String month) {
		if (null == month)
			return null;
		String ym = month.trim();
		if (6 != ym.length())
			return ym;
		String year = ym.substring(0, 4);
		return new StringBuffer(year).append("年").append(month).append("月")
				.toString();
	}

	public static Date increaseMonth(Date date, int intBetween) {
		Calendar calo = Calendar.getInstance();
		calo.setTime(date);
		calo.add(Calendar.MONTH, intBetween);
		return calo.getTime();
	}

	public static Date increaseDay(Date date, int intBetween) {
		Calendar calo = Calendar.getInstance();
		calo.setTime(date);
		calo.add(Calendar.DATE, intBetween);
		return calo.getTime();
	}

	public static Date increaseYear(Date date, int intBetween) {
		Calendar calo = Calendar.getInstance();
		calo.setTime(date);
		calo.add(Calendar.YEAR, intBetween);
		return calo.getTime();
	}

	public static int compareDate(String str1, String str2) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date1 = null, date2 = null;
		try {
			date1 = formatter.parse(str1);
			date2 = formatter.parse(str2);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return date1.compareTo(date2);
	}

	public static int compareDate(String str1, Date date2) {
		Date date1 = getDateByString(str1);
		return date1.compareTo(date2);
	}

	public static java.sql.Timestamp getDateByString(String strDate) {
		if (strDate.trim().equals("")) {
			return new java.sql.Timestamp(System.currentTimeMillis());
		}
		try {
			strDate = getFormattedDate(strDate, "yyyy-MM-dd HH:mm:ss")
					+ ".000000000";
			return java.sql.Timestamp.valueOf(strDate);
		} catch (Exception ex) {
			System.out.println("getDateByString exception: " + ex.getMessage());
			return new java.sql.Timestamp(System.currentTimeMillis());
		}
	}

	public static java.sql.Timestamp getNextMonyDate(String strDate) {
		try {
			int iYear = StringUtil
					.getStrToInt(getFormattedDate(strDate, "yyyy"));
			int iMonth = StringUtil
					.getStrToInt(getFormattedDate(strDate, "MM"));
			if (iMonth == 12) {
				iYear = iYear + 1;
			} else {
				iMonth = iMonth + 1;
			}
			String strMonth = Integer.toString(iMonth);
			if (strMonth.length() == 1) {
				strMonth = "0" + strMonth;
			}
			strDate = Integer.toString(iYear) + "/" + strMonth + "/01";
			return getDateByString(strDate);
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return getDateByString(strDate);
		}
	}

	public static java.sql.Timestamp getDateFromReqParam(
			HttpServletRequest request, String strParamName) {
		String strStr = StringUtil.getNotNullStr(request
				.getParameter(strParamName));
		return getDateByString(strStr);
	}

	public static String getCurrDate() {
		return getFormattedDate(getDateByString(""));
	}

	public static String getToday() {
		Date cDate = new Date();
		SimpleDateFormat cSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return cSimpleDateFormat.format(cDate);
	}

	public static String getYesterday() {
		Date cDate = new Date();
		cDate.setTime(cDate.getTime() - 24 * 3600 * 1000);
		SimpleDateFormat cSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return cSimpleDateFormat.format(cDate);
	}

	public static String getTomorrow() {
		Date cDate = new Date();
		cDate.setTime(cDate.getTime() + 24 * 3600 * 1000);
		SimpleDateFormat cSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return cSimpleDateFormat.format(cDate);
	}

	public static String getDefaultValidDate() {
		return "1900-01-01";
	}

	public static String getDefaultExpireDate() {
		return "2099-12-31";
	}

	public static String getCurrDateTime() {
		java.sql.Timestamp date = new java.sql.Timestamp(System
				.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return formatter.format(date);
	}

	public static String getSpecDate(String strFormat, int iYear, int iMonth,
			int iDate) {
		Calendar rightNow = Calendar.getInstance();
		rightNow.set(Calendar.YEAR, rightNow.get(Calendar.YEAR) + iYear);
		rightNow.set(Calendar.MONTH, rightNow.get(Calendar.MONTH) + iMonth);
		rightNow.set(Calendar.DATE, rightNow.get(Calendar.DATE) + iDate);
		SimpleDateFormat df = new SimpleDateFormat(strFormat);
		return df.format(rightNow.getTime());
	}

	public static String getDefaultFormattedDate(String strDate) {
		return getFormattedDate(strDate, "yyyy-MM-dd HH:mm:ss");
	}

	public static String getDefaultFormattedDate(java.sql.Timestamp dtDate) {
		return getFormattedDate(dtDate, "yyyy-MM-dd HH:mm:ss");
	}

	public static java.sql.Timestamp getNullBirthDay() {
		return new java.sql.Timestamp(0);
	}

	public static String getFormattedDate(String strDate) {
		return getFormattedDate(strDate, "yyyy-MM-dd");
	}

	public static String getFormattedDate(String strDate, String strFormatTo) {
		if (strDate == null || strDate.trim().equals("")) {
			return "";
		}
		strDate = strDate.replace('/', '-');
		strFormatTo = strFormatTo.replace('/', '-');
		if (strDate.equals("0000-00-00 00:00:00")
				|| strDate.equals("1800-01-01 00:00:00")) {
			return "";
		}
		String formatStr = strFormatTo; // "yyyyMMdd";
		if (strDate == null || strDate.trim().equals("")) {
			return "";
		}
		switch (strDate.trim().length()) {
		case 6:
			if (strDate.substring(0, 1).equals("0")) {
				formatStr = "yyMMdd";
			} else {
				formatStr = "yyyyMM";
			}
			break;
		case 8:
			formatStr = "yyyyMMdd";
			break;
		case 10:
			if (strDate.indexOf("-") == -1) {
				formatStr = "yyyy/MM/dd";
			} else {
				formatStr = "yyyy-MM-dd";
			}
			break;
		case 11:
			if (strDate.getBytes().length == 14) {
				formatStr = "yyyy��MM��dd��";
			} else {
				return "";
			}
		case 14:
			formatStr = "yyyyMMddHHmmss";
			break;
		case 19:
			if (strDate.indexOf("-") == -1) {
				formatStr = "yyyy/MM/dd HH:mm:ss";
			} else {
				formatStr = "yyyy-MM-dd HH:mm:ss";
			}
			break;
		case 21:
			if (strDate.indexOf("-") == -1) {
				formatStr = "yyyy/MM/dd HH:mm:ss.S";
			} else {
				formatStr = "yyyy-MM-dd HH:mm:ss.S";
			}
			break;
		default:
			return strDate.trim();
		}
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(formatter.parse(strDate));
			formatter = new SimpleDateFormat(strFormatTo);
			return formatter.format(calendar.getTime());
		} catch (Exception e) {
			return "";
		}
	}

	public static String getFormattedDate(java.sql.Timestamp dtDate) {
		return getFormattedDate(dtDate, "yyyy-MM-dd");
	}

	public static String getFormattedDate(java.sql.Timestamp dtDate,
			String strFormatTo) {
		if (dtDate == null) {
			return "";
		}
		if (dtDate.equals(new java.sql.Timestamp(0))) {
			return "";
		}
		strFormatTo = strFormatTo.replace('/', '-');
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
			if (Integer.parseInt(formatter.format(dtDate)) < 1900) {
				return "";
			} else {
				formatter = new SimpleDateFormat(strFormatTo);
				return formatter.format(dtDate);
			}
		} catch (Exception e) {
			return "";
		}
	}

	public static String getTimeFormat(long lSecond) {
		String szTime = new String();

		if (lSecond <= 0) {
			szTime = "00" + ":" + "00" + ":" + "00";
		} else {
			long hour = lSecond / 3600;
			long minute = (lSecond - hour * 3600) / 60;
			long second = (lSecond - hour * 3600 - minute * 60);

			if (hour <= 0) {
				szTime = "00";
			} else if (hour < 10) {
				szTime = "0" + String.valueOf(hour);
			} else {
				szTime = String.valueOf(hour);
			}
			szTime = szTime + ":";

			if (minute <= 0) {
				szTime = szTime + "00";
			} else if (minute < 10) {
				szTime = szTime + "0" + String.valueOf(minute);
			} else {
				szTime = szTime + String.valueOf(minute);
			}
			szTime = szTime + ":";

			if (second <= 0) {
				szTime = szTime + "00";
			} else if (second < 10) {
				szTime = szTime + "0" + String.valueOf(second);
			} else {
				szTime = szTime + String.valueOf(second);
			}
		}

		return szTime;
	}

	public static String getFormattedDateUtil(java.util.Date dtDate,
			String strFormatTo) {
		if (dtDate == null) {
			return "";
		}
		strFormatTo = strFormatTo.replace('/', '-');
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(strFormatTo);
			return formatter.format(dtDate);
		} catch (Exception e) {
			return "";
		}
	}

	public static int getBetweenDays(String strFromDate, String strToDate) {
		try {
			Calendar clFrom = new GregorianCalendar();
			int iYear = Integer.parseInt(strFromDate.substring(0, 4));
			int iMonth = Integer.parseInt(strFromDate.substring(4, 6));
			int iDay = Integer.parseInt(strFromDate.substring(6, 8));
			clFrom.set(iYear, iMonth, iDay, 0, 0, 0);
			Calendar clTo = new GregorianCalendar();
			iYear = Integer.parseInt(strToDate.substring(0, 4));
			iMonth = Integer.parseInt(strToDate.substring(4, 6));
			iDay = Integer.parseInt(strToDate.substring(6, 8));
			clTo.set(iYear, iMonth, iDay, 0, 0, 0);
			long llTmp = clTo.getTime().getTime() - clFrom.getTime().getTime();
			return new Long(llTmp / 1000 / 3600 / 24).intValue();
		} catch (Exception e) {
			return Integer.MIN_VALUE;
		}
	}

	private static DateUtil instance = null;

	private static final Locale local = Locale.ENGLISH;

	public static synchronized DateUtil getInstance() {
		if (instance == null) {
			instance = new DateUtil();
		}
		return instance;
	}

	public static final long millisInDay = 86400000;

	// some static date formats
	private static SimpleDateFormat[] mDateFormats = loadDateFormats();

	private static final SimpleDateFormat mFormat8chars = new SimpleDateFormat(
			"yyyyMMdd");

	private static final SimpleDateFormat mFormatIso8601Day = new SimpleDateFormat(
			"yyyy-MM-dd");

	private static final SimpleDateFormat mFormatIso8601 = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssZ");

	// Date format pattern used to parse HTTP date headers in RFC 1123 format.
	private static final SimpleDateFormat mFormatRfc1123 = new SimpleDateFormat(
			"EEE, dd MMM yyyy HH:mm:ss zzz");

	public static String formatRfc1123(Date date) {
		return DateUtil.format(date, mFormatRfc1123);
	}

	// Date format pattern used to parse HTTP date headers in RFC 1036 format.
	private static final SimpleDateFormat mFormatRfc1036 = new SimpleDateFormat(
			"EEEE, dd-MMM-yy HH:mm:ss zzz");

	public static String formatRfc1036(Date date) {
		return DateUtil.format(date, mFormatRfc1036);
	}

	// Date format pattern used to parse HTTP date headers in ANSI C
	public static final String PATTERN_ASCTIME = "EEE MMM d HH:mm:ss yyyy";

	// http://www.w3.org/Protocols/rfc822/Overview.html#z28
	private static final SimpleDateFormat mFormatRfc822 = new SimpleDateFormat(
			"EEE, d MMM yyyy HH:mm:ss z");

	private static final SimpleDateFormat mFormatTradeEasy = new SimpleDateFormat(
			"MM/dd/yyyy HH:mm");

	private static final SimpleDateFormat mFormatTradeEasyMMddyyyy = new SimpleDateFormat(
			"MM/dd/yyyy");

	// add by huyanzhi
	private static final SimpleDateFormat mFormatTradeEasyProduct = new SimpleDateFormat(
			"dd/MM/yyyy");
	// end

	private static final SimpleDateFormat mFormatExpire = new SimpleDateFormat(
			"MMMM dd, yyyy", local);

	private static SimpleDateFormat[] loadDateFormats() {
		SimpleDateFormat[] temp = {
				// new SimpleDateFormat("MM/dd/yyyy hh:mm:ss.SSS a"),
				new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy"),
				// standard Date.toString() results
				new SimpleDateFormat("M/d/yy hh:mm:ss"),
				new SimpleDateFormat("M/d/yyyy hh:mm:ss"),
				new SimpleDateFormat("M/d/yy hh:mm a"),
				new SimpleDateFormat("M/d/yyyy hh:mm a"),
				new SimpleDateFormat("M/d/yy HH:mm"),
				new SimpleDateFormat("M/d/yyyy HH:mm"),
				new SimpleDateFormat("dd.MM.yyyy HH:mm:ss"),
				new SimpleDateFormat("yy-MM-dd HH:mm:ss.SSS"),
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"),
				// standard Timestamp.toString() results
				new SimpleDateFormat("M-d-yy HH:mm"),
				new SimpleDateFormat("M-d-yyyy HH:mm"),
				new SimpleDateFormat("MM/dd/yyyy HH:mm:ss.SSS"),
				new SimpleDateFormat("M/d/yy"),
				new SimpleDateFormat("M/d/yyyy"),
				new SimpleDateFormat("M-d-yy"),
				new SimpleDateFormat("M-d-yyyy"),
				new SimpleDateFormat("MMMM d, yyyyy"),
				new SimpleDateFormat("MMM d, yyyyy") };

		return temp;
	}

	// -----------------------------------------------------------------------
	/**
	 * Gets the array of SimpleDateFormats that DateUtil knows about.
	 */
	private static SimpleDateFormat[] getFormats() {
		return mDateFormats;
	}

	// -----------------------------------------------------------------------
	/**
	 * Returns a Date set to the last possible millisecond of the day, just
	 * before midnight. If a null day is passed in, a new Date is created.
	 * midnight (00m 00h 00s)
	 */
	public static Date getEndOfDay(Date day) {
		return getEndOfDay(day, Calendar.getInstance());
	}

	public static Date getEndOfDay(Date day, Calendar cal) {
		if (day == null)
			day = new Date();
		cal.setTime(day);
		cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
		return cal.getTime();
	}

	// -----------------------------------------------------------------------
	/**
	 * Returns a Date set to the first possible millisecond of the day, just
	 * after midnight. If a null day is passed in, a new Date is created.
	 * midnight (00m 00h 00s)
	 */
	public static Date getStartOfDay(Date day) {
		return getStartOfDay(day, Calendar.getInstance());
	}

	/**
	 * Returns a Date set to the first possible millisecond of the day, just
	 * after midnight. If a null day is passed in, a new Date is created.
	 * midnight (00m 00h 00s)
	 */
	public static Date getStartOfDay(Date day, Calendar cal) {
		if (day == null)
			day = new Date();
		cal.setTime(day);
		cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
		return cal.getTime();
	}

	/**
	 * Returns a Date set just to Noon, to the closest possible millisecond of
	 * the day. If a null day is passed in, a new Date is created. nnoon (00m
	 * 12h 00s)
	 */
	public static Date getNoonOfDay(Date day, Calendar cal) {
		if (day == null)
			day = new Date();
		cal.setTime(day);
		cal.set(Calendar.HOUR_OF_DAY, 12);
		cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
		return cal.getTime();
	}

	// -----------------------------------------------------------------------
	public static Date parseFromFormats(String aValue) {
		if (StringUtil.isEmpty(aValue))
			return null;

		// get DateUtil's formats
		SimpleDateFormat formats[] = DateUtil.getFormats();
		if (formats == null)
			return null;

		// iterate over the array and parse
		Date myDate = null;
		for (int i = 0; i < formats.length; i++) {
			try {
				myDate = DateUtil.parse(aValue, formats[i]);
				// if (myDate instanceof Date)
				return myDate;
			} catch (Exception e) {
				// do nothing because we want to try the next
				// format if current one fails
			}
		}
		// haven't returned so couldn't parse
		return null;
	}

	// -----------------------------------------------------------------------
	public static java.sql.Timestamp parseTimestampFromFormats(String aValue) {
		if (StringUtil.isEmpty(aValue))
			return null;

		// call the regular Date formatter
		Date myDate = DateUtil.parseFromFormats(aValue);
		if (myDate != null)
			return new java.sql.Timestamp(myDate.getTime());
		return null;
	}

	// -----------------------------------------------------------------------
	/**
	 * Returns a java.sql.Timestamp equal to the current time
	 */
	public static java.sql.Timestamp now() {
		return new java.sql.Timestamp(new java.util.Date().getTime());
	}

	// -----------------------------------------------------------------------
	/**
	 * Returns a string the represents the passed-in date parsed according to
	 * the passed-in format. Returns an empty string if the date or the format
	 * is null.
	 */
	public static String format(Date aDate, SimpleDateFormat aFormat) {
		if (aDate == null || aFormat == null) {
			return "";
		}
		synchronized (aFormat) {
			return aFormat.format(aDate);
		}
	}

	// -----------------------------------------------------------------------
	/**
	 * Tries to take the passed-in String and format it as a date string in the
	 * the passed-in format.
	 */
	public static String formatDateString(String aString,
			SimpleDateFormat aFormat) {
		if (StringUtil.isEmpty(aString) || aFormat == null)
			return "";
		try {
			java.sql.Timestamp aDate = parseTimestampFromFormats(aString);
			if (aDate != null) {
				return DateUtil.format(aDate, aFormat);
			}
		} catch (Exception e) {
			// Could not parse aString.
		}
		return "";
	}

	// -----------------------------------------------------------------------
	/**
	 * Returns a Date using the passed-in string and format. Returns null if the
	 * string is null or empty or if the format is null. The string must match
	 * the format.
	 */
	public static Date parse(String aValue, SimpleDateFormat aFormat)
			throws ParseException {
		if (StringUtil.isEmpty(aValue) || aFormat == null) {
			return null;
		}

		return aFormat.parse(aValue);
	}

	// -----------------------------------------------------------------------
	/**
	 * Returns true if endDate is after startDate or if startDate equals endDate
	 * or if they are the same date. Returns false if either value is null.
	 */
	public static boolean isValidDateRange(Date startDate, Date endDate) {
		return isValidDateRange(startDate, endDate, true);
	}

	// -----------------------------------------------------------------------
	/**
	 * Returns true if endDate is after startDate or if startDate equals
	 * endDate. Returns false if either value is null. If equalOK, returns true
	 * if the dates are equal.
	 */
	public static boolean isValidDateRange(Date startDate, Date endDate,
			boolean equalOK) {
		// false if either value is null
		if (startDate == null || endDate == null) {
			return false;
		}

		if (equalOK) {
			// true if they are equal
			if (startDate.equals(endDate)) {
				return true;
			}
		}

		// true if endDate after startDate
		if (endDate.after(startDate)) {
			return true;
		}

		return false;
	}

	// -----------------------------------------------------------------------
	// returns full timestamp format
	public static java.text.SimpleDateFormat defaultTimestampFormat() {
		return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	}

	// -----------------------------------------------------------------------
	// convenience method returns minimal date format
	public static java.text.SimpleDateFormat get8charDateFormat() {
		return DateUtil.mFormat8chars;
	}

	// -----------------------------------------------------------------------
	// convenience method returns minimal date format
	public static java.text.SimpleDateFormat defaultDateFormat() {
		return DateUtil.friendlyDateFormat(true);
	}

	// -----------------------------------------------------------------------
	// convenience method
	public static String defaultTimestamp(Date date) {
		return DateUtil.format(date, DateUtil.defaultTimestampFormat());
	}

	// -----------------------------------------------------------------------
	// convenience method
	public static String defaultDate(Date date) {
		return DateUtil.format(date, DateUtil.defaultDateFormat());
	}

	// -----------------------------------------------------------------------
	// convenience method returns long friendly timestamp format
	public static java.text.SimpleDateFormat friendlyTimestampFormat() {
		return new java.text.SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	}

	// -----------------------------------------------------------------------
	// convenience method returns long friendly formatted timestamp
	public static String friendlyTimestamp(Date date) {
		return DateUtil.format(date, DateUtil.friendlyTimestampFormat());
	}

	// -----------------------------------------------------------------------
	// convenience method returns long friendly formatted timestamp
	public static String format8chars(Date date) {
		return DateUtil.format(date, mFormat8chars);
	}

	// -----------------------------------------------------------------------
	// convenience method returns long friendly formatted timestamp
	public static String formatIso8601Day(Date date) {
		return DateUtil.format(date, mFormatIso8601Day);
	}

	public static String formatIso8601Day(Timestamp timestamp) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timestamp.getTime());
		return DateUtil.format(calendar.getTime(), mFormatIso8601Day);
	}

	public static String formatTradeEasy(Date date) {
		return DateUtil.format(date, mFormatTradeEasy);
	}

	// add by huyanzhi
	public static String formatTradeEasyProduct(Date date) {
		return DateUtil.format(date, mFormatTradeEasyProduct);
	}

	//

	public static String formatFormatTradeEasyMMddyyyy(Date date) {
		return DateUtil.format(date, mFormatTradeEasyMMddyyyy);
	}

	public static String formatTradeEasy(Timestamp timestamp) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timestamp.getTime());
		return DateUtil.format(calendar.getTime(), mFormatTradeEasy);
	}

	// -----------------------------------------------------------------------
	public static String formatRfc822(Date date) {
		return DateUtil.format(date, mFormatRfc822);
	}

	public static String formatExpire(Date date) {
		return DateUtil.format(date, mFormatExpire);
	}

	// -----------------------------------------------------------------------
	// This is a hack, but it seems to work
	public static String formatIso8601(Date date) {
		if (date == null)
			return "";
		String str = DateUtil.format(date, mFormatIso8601);
		StringBuffer sb = new StringBuffer();
		sb.append(str.substring(0, str.length() - 2));
		sb.append(":");
		sb.append(str.substring(str.length() - 2));
		return sb.toString();
	}

	// -----------------------------------------------------------------------
	// convenience method returns minimal date format
	public static java.text.SimpleDateFormat minimalDateFormat() {
		return DateUtil.friendlyDateFormat(true);
	}

	// -----------------------------------------------------------------------
	// convenience method using minimal date format
	public static String minimalDate(Date date) {
		return DateUtil.format(date, DateUtil.minimalDateFormat());
	}

	// -----------------------------------------------------------------------
	// convenience method that returns friendly data format
	// using full month, day, year digits.
	public static java.text.SimpleDateFormat fullDateFormat() {
		return DateUtil.friendlyDateFormat(false);
	}

	// -----------------------------------------------------------------------
	public static String fullDate(Date date) {
		return DateUtil.format(date, DateUtil.fullDateFormat());
	}

	// -----------------------------------------------------------------------
	/**
	 * Returns a "friendly" date format.
	 * 
	 * @param mimimalFormat
	 *            Should the date format allow single digits.
	 */
	public static java.text.SimpleDateFormat friendlyDateFormat(
			boolean minimalFormat) {
		if (minimalFormat) {
			return new java.text.SimpleDateFormat("d.M.yy");
		}

		return new java.text.SimpleDateFormat("dd.MM.yyyy");
	}

	// -----------------------------------------------------------------------
	/**
	 * Format the date using the "friendly" date format.
	 */
	public static String friendlyDate(Date date, boolean minimalFormat) {
		return DateUtil
				.format(date, DateUtil.friendlyDateFormat(minimalFormat));
	}

	// -----------------------------------------------------------------------
	// convenience method
	public static String friendlyDate(Date date) {
		return DateUtil.format(date, DateUtil.friendlyDateFormat(true));
	}

	public static Date parseFormatIso8601Date(String date) throws Exception {
		Date returnDate = null;
		try {
			returnDate = mFormatIso8601Day.parse(date);
		} catch (Exception e) {
			throw e;
		}
		return returnDate;
	}

	// add by huyanzhi
	public static String addDate(String date, String type, int into)
			throws Exception {
		String Sdate = "";
		try {
			GregorianCalendar grc = new GregorianCalendar();
			grc.setTime(new Date(date));
			if (type.equals("D")) {
				grc.add(GregorianCalendar.DATE, into);
			} else if (type.equals("M")) {
				grc.add(GregorianCalendar.MONTH, into);
			} else if (type.equals("Y")) {
				grc.add(GregorianCalendar.YEAR, into);
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Sdate = new String(formatter.format(grc.getTime()));
		} catch (Exception e) {
			throw e;
		}
		return Sdate;
	}

	public static String addDate(String date, String into) throws Exception {
		String Sdate = "";
		try {
			date = date.replaceAll("-", "/");
			date = date.substring(0, date.length() - 2);
			GregorianCalendar grc = new GregorianCalendar();
			grc.setTime(new Date(date));
			grc.add(GregorianCalendar.DATE, Integer.parseInt(into));
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Sdate = new String(formatter.format(grc.getTime()));
		} catch (Exception e) {
			throw e;
		}
		return Sdate;
	}

	public static String formatDate(Date date, String pattern) {
		if (pattern == null || pattern.equals("") || pattern.equals("null")) {
			pattern = "yyyy-MM-dd";
		}
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	public static String addValidateDate(String date, String into)
			throws Exception {
		String Sdate = "";
		try {
			date = date.replaceAll("-", "/");
			date = date.substring(0, date.length() - 2);
			GregorianCalendar grc = new GregorianCalendar();
			grc.setTime(new Date(date));
			grc.add(GregorianCalendar.DATE, Integer.parseInt(into));
			Sdate = new String(mFormatExpire.format(grc.getTime()));
		} catch (Exception e) {
			throw e;
		}
		return Sdate;
	}
}
