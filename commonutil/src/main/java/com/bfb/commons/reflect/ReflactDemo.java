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
		bean.setBrandId("123");
		bean.setCono("001");
		Method[] methodAry = bean.getClass().getMethods();
		
		
		
		for(int i=0; i<methodAry.length; i++){
			Method method = methodAry[i];
			String mName = method.getName();
			if(mName.indexOf("get")!=-1)
				System.out.println(method.invoke(bean,null));
		}
	}

}
