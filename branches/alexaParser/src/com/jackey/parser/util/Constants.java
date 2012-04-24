/*
 * Copyright(c) 2005,־j��ͨ
 *
 *��Ŀ��
 *�ѷ�WAP
 *
 *����
 *  ����              �汾
 *   2005/08/11   1.0
 */
package com.jackey.parser.util;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * ����.
 * <ul>
 * �����������ֳ���
 * </ul>
 * <br>
 * 
 * @author �ֻ���
 * @version 1.0 2005/08/11
 */

public class Constants {

	public static String DB_CONNECTION_NAME = "";

	public static String DB_USER_NAME = "";

	public static String DB_PASSWORD = "";
	
	public static String DB_DRIVER = "";
	
	public static final String DEF_COMMON_PARA = "config.properties";

	private static Properties ps = new Properties();
	protected static final Log log = LogFactory.getLog(Constants.class);
	
	public static void init() {
		try {
			Des des = new Des();
			Constants c = new Constants();
			InputStream is =c.getClass().getResourceAsStream("/" + DEF_COMMON_PARA);
            
			try {
				ps.load(is);
			} catch (Exception e) {
				log.error("---------------config.properties = " + e.toString());
				e.printStackTrace();

			}
			if (ps.getProperty("DB_CONNECTION_NAME") != null) {
				DB_CONNECTION_NAME = ps.getProperty("DB_CONNECTION_NAME");
			}

			if (ps.getProperty("DB_USER_NAME") != null) {
				DB_USER_NAME = ps.getProperty("DB_USER_NAME");
			}

			if (ps.getProperty("DB_PASSWORD") != null) {
				DB_PASSWORD = ps.getProperty("DB_PASSWORD");
			}
			
			if (ps.getProperty("DB_DRIVER") != null) {
				DB_DRIVER = ps.getProperty("DB_DRIVER");
			}
			log.info("------------- db = " + DB_CONNECTION_NAME + "         = " + DB_USER_NAME +  "         = " + DB_PASSWORD);
			DB_PASSWORD = des.decrypt(DB_PASSWORD);
		} catch (Exception e) {
			log.error("---------------config.properties = " + e.toString());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void init_message() {
		try {
			Des des = new Des();
			Constants c = new Constants();
			InputStream is =c.getClass().getResourceAsStream("/" + DEF_COMMON_PARA);

			try {
				ps.load(is);
			} catch (Exception e) {
				log.error("---------------config.properties = " + e.toString());
				e.printStackTrace();

			}
			if (ps.getProperty("DB_CONNECTION_NAME_MESS") != null) {
				DB_CONNECTION_NAME = ps.getProperty("DB_CONNECTION_NAME_MESS");
			}

			if (ps.getProperty("DB_USER_NAME_MESS") != null) {
				DB_USER_NAME = ps.getProperty("DB_USER_NAME_MESS");
			}

			if (ps.getProperty("DB_PASSWORD_MESS") != null) {
				DB_PASSWORD = ps.getProperty("DB_PASSWORD_MESS");
			}
			log.info("------------- db = " + DB_CONNECTION_NAME + "         = " + DB_USER_NAME +  "         = " + DB_PASSWORD);
			DB_PASSWORD = des.decrypt(DB_PASSWORD);
		} catch (Exception e) {
			log.error("---------------config.properties = " + e.toString());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
