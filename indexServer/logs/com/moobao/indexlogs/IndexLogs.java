package com.moobao.indexlogs;

import java.io.UnsupportedEncodingException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class IndexLogs {

	// static Logger logger = Logger.getLogger(SearchLogs.class.getName());
	
	static{
		URL url = IndexLogs.class.getResource("/indexTast.properties");
		PropertyConfigurator.configure(url);
	}

	public IndexLogs() {
	}
	/**
	 * 将索引定时任务写入日志中. 
	 * @param keywords
	 * @param searchClass
	 */
//	public static void setLogs(String keywords, String searchClass) {
//
//		URL url = SearchLogs.class.getResource("/logsSearch.properties");
//		// String path = "conf_system/app.properties";
//		PropertyConfigurator.configure(url);
//		// Logger logger = Logger.getLogger( SearchLogs.class );
//		Logger logger = Logger.getLogger(searchClass);
//		logger.info(keywords);
//	}

	public static void setLogs(String info, String searchClass) {
		
		Logger logger = Logger.getLogger(searchClass);
		logger.info(info);
	}
}
