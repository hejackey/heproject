/**
 * created on 2006-9-28
 * 
 * 
 */
package com.zhuozhuo.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * communicate with the config file extern
 * @author Demy
 * 
 */
public class PropertyUtil {

	private static Properties props;
	static {
		if (props == null) {
			try {
				props = new Properties();
				props.load(PropertyUtil.class.getClassLoader().getResourceAsStream("config.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * get a certain property
	 * 
	 * @param key
	 * @return
	 */
	public static final String getProperty(String key) {
		return props.getProperty(key);
	}
	
	/**
	 * set the property
	 * @param key
	 * @param value
	 */
	public static void setProperty(String key,String value) 
	{
		try
		{
		props.setProperty(key,value);
		props.store(new FileOutputStream("upload.properties"),"test save");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * get a certain task file
	 * @param taskname
	 * @return
	 */
	public static String getTaskFile(String taskname) {
		return taskname;
	}

}
