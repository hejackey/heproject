package com.bfb.commons.properties;

import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * properties文件工具类
 * @author Administrator
 *
 */
public class PropertiesUtil {
	/**
	 * 读取配置文件中指定key对应的值
	 * @param proName	读取的properties文件名	
	 * @param key		取值的key
	 * @return	返回key对应的value
	 */
	public static String getValByProNameAndKey(String proName,String key){
		ResourceBundle rb = ResourceBundle.getBundle(proName);
		return rb.getString(key);
	}
	
	/**
	 * 读取配置文件中指定key对应的值
	 * @param fileName	读取的properties文件名	
	 * @param keyName	取值的key
	 * @return	返回key对应的value
	 */
	@SuppressWarnings("finally")
	public static String getValueFromPropertyByKey(
			String fileName,
			String keyName) {
			Properties initProps = new Properties();
			String theValue = null;
			InputStream in = null;

			try {
				in = PropertiesUtil.class.getResourceAsStream(fileName);
				initProps.load(in);
				theValue = initProps.getProperty(keyName);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				return theValue;
			}
		}
}
