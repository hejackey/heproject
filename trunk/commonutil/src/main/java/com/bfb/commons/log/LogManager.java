package com.bfb.commons.log;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * jdk自带写日志类
 * @author Administrator
 * @version 1.0
 * @date 2011-12-26
 */
public class LogManager {
	private static final int LIMIT=100*1024;//100k
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
	
	/**
	 * 获取日志对象
	 * @param clazz	记录日志的类
	 * @param FileName	日志文件名
	 * @return
	 */
	public  Logger getLogger(Class<?> clazz,String FileName){
		Logger log = Logger.getLogger(clazz.getName());
		log.addHandler(getFileHandler(FileName));
		
		return log;
	}
	
	private static LogManager logManager=null;
	private static FileHandler fh = null;
	
	private LogManager(){
		
	}
	
	public static synchronized LogManager getInstantce(){
		if(logManager == null){
			logManager = new LogManager();
		}
		
		return logManager;
	}
	/**
	 * 获取日志对象
	 * @param clazz	记录日志的类
	 * @return
	 */
	public Logger getLogger(Class<?> clazz) {  
       Logger logger = Logger.getLogger(clazz.getName());
       try {
    	   fh = new FileHandler("d:\\syn_msg.log",LIMIT,COUNT,APPEND);
    	   fh.setFormatter(new BfbLogFormatter());
    	   logger.addHandler(fh);
       } catch (SecurityException e) {
    	   e.printStackTrace();
       } catch (IOException e) {
    	   e.printStackTrace();
       }
       
       return logger;
    }  

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LogManager lm = new LogManager();
			lm.getLogger(LogManager.class);
			/*Logger log = getLogger(LogManager.class,"d:\\mer_msg%u.%g.log");

			log.info("java自带日志");
			log.info("日志格式不爽");*/
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
}
