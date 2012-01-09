package com.bfb.commons.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflactDemo {

	/**
	 * @param args
	 * @throws NoSuchFieldException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static void main(String[] args) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		// TODO Auto-generated method stub
		TestBean bean = new TestBean();
		Method[] methodAry = bean.getClass().getMethods();
		Field[] dfieldAry = bean.getClass().getDeclaredFields();
		
		System.out.println(methodAry.length);
		System.out.println(dfieldAry.length);
		
		bean.getClass().getDeclaredField("brandId");
		
		
		for(int i=0; i<methodAry.length; i++){
			System.out.println(methodAry[i].getName());
			Method method = methodAry[i];
			String mName = method.getName();
			if(mName.indexOf("get")!=-1 && mName.toLowerCase().indexOf("brandId".toLowerCase())!=-1)
				System.out.println(method.invoke(null, null));
		}
	}

}
