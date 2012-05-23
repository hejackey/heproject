package com.apply.b2b.cms.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author wind
 * 
 */

public class RowSetTypeConverter {
	
	public static String getString(Object obj) {
		if (obj != null) {
			if (obj instanceof String) {
				return (String) obj;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	public static String getString(Object obj, String defaultV) {
		if (obj != null) {
			if (obj instanceof String) {
				return (String) obj;
			} else {
				return defaultV;
			}
		} else {
			return defaultV;
		}
	}
	
	public static Date getDate(Object obj) {
		if (obj != null) {
			if (obj instanceof Date) {
				return (Date) obj;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	public static Date getDate(Object obj, Date defaultV) {
		if (obj != null) {
			if (obj instanceof Date) {
				return (Date) obj;
			} else {
				return defaultV;
			}
		} else {
			return defaultV;
		}
	}
	
	public static BigDecimal getBigDecimal(Object obj) {
		return getBigDecimal(obj, 0);
	}

	public static BigDecimal getBigDecimal(Object obj, double defaultV) {
		if (obj != null) {
			if (obj instanceof BigDecimal) {
				BigDecimal bdObj = (BigDecimal) obj;
				if (bdObj != null) {
					return bdObj;
				} else {
					return new BigDecimal(defaultV);
				}
			} else {
				return new BigDecimal(defaultV);
			}
		} else {
			return new BigDecimal(defaultV);
		}
	}

	public static Integer getInteger(Object obj) {
		return getInteger(obj, 0);
	}

	public static Integer getInteger(Object obj, int defaultV) {
		if (obj != null) {
			if (obj instanceof BigDecimal) {
				BigDecimal dbObj = (BigDecimal) obj;
				if (dbObj != null) {
					return new Integer(dbObj.intValue());
				} else {
					return new Integer(defaultV);
				}
			} else if (obj instanceof Integer) {
				Integer dbObj = (Integer) obj;
				if (dbObj != null) {
					return new Integer(dbObj.intValue());
				} else {
					return new Integer(defaultV);
				}
			} else {
				return new Integer(defaultV);
			}
		} else {
			return new Integer(defaultV);
		}
	}

	public static Long getLong(Object obj) {
		return getLong(obj, 0);
	}

	public static Long getLong(Object obj, long defaultV) {
		if (obj != null) {
			if (obj instanceof BigDecimal) {
				BigDecimal dbObj = (BigDecimal) obj;
				if (dbObj != null) {
					return new Long(dbObj.longValue());
				} else {
					return new Long(defaultV);
				}
			} else if (obj instanceof Long) {
				Long dbObj = (Long) obj;
				if (dbObj != null) {
					return new Long(dbObj.longValue());
				} else {
					return new Long(defaultV);
				}
			} else {
				return new Long(defaultV);
			}
		} else {
			return new Long(defaultV);
		}
	}

	public static Double getDouble(Object obj) {
		return getDouble(obj, 0);
	}

	public static Double getDouble(Object obj, double defaultV) {
		if (obj != null) {
			if (obj instanceof BigDecimal) {
				BigDecimal dbObj = (BigDecimal) obj;
				if (dbObj != null) {
					return new Double(dbObj.doubleValue());
				} else {
					return new Double(defaultV);
				}
			} else if (obj instanceof Double) {
				Double dbObj = (Double) obj;
				if (dbObj != null) {
					return new Double(dbObj.doubleValue());
				} else {
					return new Double(defaultV);
				}
			} else {
				return new Double(defaultV);
			}
		} else {
			return new Double(defaultV);
		}
	}
	
	public static Float getFloat(Object obj) {
		return getFloat(obj, 0);
	}
	
	public static Float getFloat(Object obj, float defaultV) {
		if (obj != null) {
			if (obj instanceof BigDecimal) {
				BigDecimal dbObj = (BigDecimal) obj;
				if (dbObj != null) {
					return new Float(dbObj.floatValue());
				} else {
					return new Float(defaultV);
				}
			} else if (obj instanceof Float) {
				Float dbObj = (Float) obj;
				if (dbObj != null) {
					return new Float(dbObj.floatValue());
				} else {
					return new Float(defaultV);
				}
			} else {
				return new Float(defaultV);
			}
		} else {
			return new Float(defaultV);
		}
	}

	public static String getCLOBToString(Object robj) {
		if (robj != null) {

			if (robj instanceof String) {

				return (String) robj;
				// StringBuffer strBuf = new StringBuffer("");
				// CLOB clob = (oracle.sql.CLOB) robj;
				// /* 以字符形式输出 */
				//				
				// BufferedReader bfReader = new BufferedReader(clob
				// .getCharacterStream());
				//				
				// String str = bfReader.readLine();
				// while (str != null) {
				// strBuf.append(str);
				// str = bfReader.readLine();
				// }
				// return strBuf.toString();
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public static Reader getReader(Object obj) {
		if (obj != null) {
			if (obj instanceof Reader) {
				return (Reader) obj;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	public static String getReaderToString(Reader robj) throws IOException {
		StringBuffer strBuf = new StringBuffer("");
		
		if (robj == null)
			return null;
		BufferedReader bfReader = new BufferedReader(robj);
		String str = bfReader.readLine();
		while (str != null) {
			strBuf.append(str);
			str = bfReader.readLine();
		}
		return strBuf.toString();
	}

	public static long getBigDecimalToLong(BigDecimal bdl) {
		return bdl.longValue();
	}

	public static int getBigDecimalToInt(BigDecimal bdl) {
		return bdl.intValue();
	}

	public static double getBigDecimalToDouble(BigDecimal bdl) {
		return bdl.doubleValue();
	}

	public static float getBigDecimalToFloat(BigDecimal bdl) {
		return bdl.floatValue();
	}
}