package com.bfb.transaction.engine.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class ExecProcessUtil {
	public static Object excuteProcess(Map<String,String> atrMap,Map<Object,Object> paraMap){
		String className = (String)atrMap.get("class");
		String methodName = (String)atrMap.get("method");
		Class<?> cls;
		try {
			cls = ClassLoader.getSystemClassLoader().loadClass(className);
			Method[] methods = cls.getMethods();
			for(Method method : methods){
				if(methodName.equals(method.getName())){
					try {
						System.out.println("������"+className+"�Ŀ���ִ�з�����"+methodName);
						
						return method.invoke(cls.newInstance(), paraMap);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
						return null;
					} catch (IllegalAccessException e) {
						e.printStackTrace();
						return null;
					} catch (InvocationTargetException e) {
						e.printStackTrace();
						return null;
					} catch (InstantiationException e) {
						e.printStackTrace();
						return null;
					}
				}
			}
			
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
