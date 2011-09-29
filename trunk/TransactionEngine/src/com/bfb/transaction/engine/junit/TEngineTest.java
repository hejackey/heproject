package com.bfb.transaction.engine.junit;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.bfb.commons.string.StringUtil;

public class TEngineTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("please input transaction cmd:");

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			String cmd = reader.readLine();
			
			URL url = ClassLoader.getSystemResource(cmd+".xml");
			String filePath = url.getFile();
			
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(new File(filePath));
			Element root = document.getRootElement();
			
			for(Iterator<?> it=root.elementIterator();it.hasNext();){
				Element sub = (Element)it.next();
				
				Map<String,String> processsMap = new HashMap<String,String>();
				for(Iterator<?> at = sub.attributeIterator();at.hasNext();){
					Attribute attribute = (Attribute)at.next();
					String atrName = attribute.getName();
					String atrText = attribute.getText();
					processsMap.put(atrName, atrText);
				}
				
				String className = (String)processsMap.get("class");
				String methodName = (String)processsMap.get("method");
				if(!StringUtil.isEmpty(className) && !StringUtil.isEmpty((methodName))){
					Class<?> cls = ClassLoader.getSystemClassLoader().loadClass(className);
					Method[] methods = cls.getMethods();
					
					for(Method method : methods){
						if(!StringUtil.isEmpty(method.getName()) && method.getName().equals(methodName)){
							Map<Object,Object> para = new HashMap<Object,Object>();
							para.put("userid", "123");
							method.invoke(cls.newInstance(), para);
							System.out.println("调用类"+className+"的可以执行方法："+methodName);
						}
					}
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}

}
