package com.bfb.commons.test.log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import com.bfb.commons.log.ILog;
import com.bfb.commons.log.LogHandler;
import com.bfb.commons.log.LogImpl;

public class TestLogHandler {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ILog log = new LogImpl();
		InvocationHandler handler = new LogHandler(log);
		
		ILog proxy = (ILog)Proxy.newProxyInstance(ILog.class.getClassLoader(),new Class[] { ILog.class },handler);
		proxy.writeLog();
	}
	
}
