package com.bfb.commons.log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogHandler implements InvocationHandler {
	Object obj;
	public LogHandler(Object o){
		obj = o;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		logBeforeCallMethod();
		obj = method.invoke(obj, args);
		logAfterCallMethod();
		
		return obj;
	}

	public void logBeforeCallMethod(){
		System.out.println("log before call method");
	}
	
	public void logAfterCallMethod(){
		System.out.println("log after call method");
	}
	
}
