package com.bfb.commons.log;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.bfb.commons.properties.PropertiesUtil;

/**
 * jdk自带写日志类
 * @author Administrator
 * @version 1.0
 * @date 2011-12-26
 */
public class LogManager {
	private static final int LIMIT=10000;
	private static final int COUNT=5;
	private static final boolean APPEND=true;
	
	/**
	 * 获取写日志文件类型handler
	 * @param fileName	日志文件名
	 * @param limit		日志切分大小，字节单位
	 * @param count		日志文件集数量
	 * @param append	是否追加到原日志文件
	 * @param format	日志格式，不传默认simpleformatter格式
	 * @return FileHandler
	 */
	public static FileHandler getFileHandler(String fileName,int limit,int count,
			boolean append,Formatter format){
		FileHandler fh;
		try {
			fh = new FileHandler(fileName,limit,count,append);
			
			if(format == null)
				fh.setFormatter(new SimpleFormatter());
			else
				fh.setFormatter(format);
			
			return fh;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取写日志文件类型handler
	 * @param fileName	文件名
	 * @return	FileHandler
	 */
	public static FileHandler getFileHandler(String fileName){
		FileHandler fh;
		try {
			fh = new FileHandler(fileName,LIMIT,COUNT,APPEND);
			fh.setFormatter(new BfbLogFormatter());
			
			return fh;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Logger getLogger(String logName,String FileName){
		Logger log = Logger.getLogger(logName);
		log.addHandler(getFileHandler(FileName));
		
		return log;
	}
	
	/**
	 * 获取处理商户的log
	 * @param log
	 * @return
	 */
	public static Logger getMerchantLogger(Logger log){
		try{
			log.addHandler(getFileHandler(PropertiesUtil.getValueFromPropertyByKey("custom_log.properties", "MERCHANT_FILE_NAME")));
			
			return log;
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("请配置日志文件名custom_log.properties");
			return null;
		}
	}
	
	/**
	 * 获取处理银行的log
	 * @param log
	 * @return
	 */
	public static Logger getBankLogger(Logger log){
		try{
			log.addHandler(getFileHandler(PropertiesUtil.getValueFromPropertyByKey("custom_log.properties", "BANK_FILE_NAME")));
			
			return log;
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("请配置日志文件名custom_log.properties");
			return null;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Logger log = getLogger("merchant_msg_log","d:\\mer_msg%u.%g.log");

			log.info("java自带日志");
			log.info("日志格式不爽");
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
}
