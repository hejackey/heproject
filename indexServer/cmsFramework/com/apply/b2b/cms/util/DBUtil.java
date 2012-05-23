package com.apply.b2b.cms.util;

import java.io.InputStream;
import java.sql.DriverManager;
import java.util.Properties;

public class DBUtil {
	private static final String DB_PROPERTY = "dbconfig.properties";
	
	private static DBUtil instance = null;
	
	private static Properties paraProps = new Properties();
	
	private DBUtil() {
		InputStream is = getClass().getResourceAsStream("/" + DB_PROPERTY);
		try {
			paraProps.load(is);
		} catch (Exception e) {
			System.err.println("不能读取属性文件. " + "请确保" + DB_PROPERTY
					+ "在CLASSPATH指定的路径中");
		}
	}
	
	public static synchronized DBUtil getInstance() {
		if (instance == null) {
			instance = new DBUtil();
		}
		return instance;
	}
	
	private static String getProperty(String paraName) {
		return paraProps.getProperty(paraName);
	}
	
	public synchronized java.sql.Connection getConnection() {
		String dbDriver = getProperty("DataBase.Driver");
		String dbURL = getProperty("DataBase.URL");
		String dbUserName = getProperty("DataBase.UserName");
		String dbPassword = getProperty("DataBase.Password");
		
		try {
			Class.forName(dbDriver);
			java.sql.Connection conn = DriverManager.getConnection(dbURL,
					dbUserName, dbPassword);
			return conn;
		} catch (Exception e) {
			return null;
		}
	}
}
