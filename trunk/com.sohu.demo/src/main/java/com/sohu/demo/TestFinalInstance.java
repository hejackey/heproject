package com.sohu.demo;

import java.lang.reflect.InvocationTargetException;

public class TestFinalInstance {
	public static void main(String[] args){
		Class<?> discoveryClass;
		try {
			discoveryClass = Class.forName("com.sohu.demo.FinalInstance");
			Object instance = discoveryClass.getMethod("getInstance").invoke(null);
			Object instance1 = discoveryClass.getMethod("getInstance").invoke(null);
			System.out.println(instance == instance1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
            
	}
}
