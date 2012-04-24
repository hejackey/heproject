package com.jackey.lucene.util;

import java.util.ResourceBundle;

public class ConfigUtil {
	public static String confFile="indexConfig";
	public static String INDEX_FILE_PATH="INDEX_FILE_PATH";
	
	public static String getConfProperty(String name,String key){
		ResourceBundle rb = ResourceBundle.getBundle(name);
		return rb.getString(key);
	}
}
