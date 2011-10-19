package com.bfb.commons.test.log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import com.bfb.commons.log.ILog;
import com.bfb.commons.log.LogHandler;
import com.bfb.commons.log.LogImpl;

/**
 * 动态代理机制，返回一个代理类做特殊处理
 * @author Administrator
 * @version 1.0
 * @date 2011-10-19
 */
public class TestLogHandler {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ILog log = new LogImpl();
		
		ILog proxy = (ILog)getProxyClass(log);
		proxy.writeLog();
	}
	
	/**
	 * 获取代理类
	 * @param obj
	 * @return
	 */
	public static Object getProxyClass(Object obj){
		InvocationHandler handler = new LogHandler(obj);
		
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),handler);
	}
}
