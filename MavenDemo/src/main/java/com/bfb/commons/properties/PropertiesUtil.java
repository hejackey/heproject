package com.bfb.commons.properties;

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
}
