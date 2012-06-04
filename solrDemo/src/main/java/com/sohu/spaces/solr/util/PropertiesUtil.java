package com.sohu.spaces.solr.util;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * properties文件工具类
 * @author Administrator
 *
 */
public class PropertiesUtil {
	private static final String PROPER_KEY_VALUE_SEPARETOR="=";
	private static final String PROPER_VALUE_SEPARETOR=",";
	private static final String URL_PARAM_SEPARETOR="&";
	private static final String BEAN_SETMETHOD_START_LAB="set";
	private static final String BEAN_GETMETHOD_START_LAB="get";
	
	/**
	 * 读取配置文件中指定key对应的值
	 * @param proName	读取的properties文件名	
	 * @param key		取值的key
	 * @return	返回key对应的value
	 */
	public static String getValByProNameAndKey(String proName,String key){
		ResourceBundle rb = ResourceBundle.getBundle(proName);
		return rb.getString(key);
	}
	
	/**
	 * 读取配置文件中指定key对应的值
	 * @param fileName	读取的properties文件名	
	 * @param keyName	取值的key
	 * @return	返回key对应的value
	 */
	@SuppressWarnings("finally")
	public static String getValueFromPropertyByKey(
		String fileName,
		String keyName) {
		Properties initProps = new Properties();
		String theValue = null;
		InputStream in = null;	

		try {
			in = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
			initProps.load(in);
			theValue = initProps.getProperty(keyName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return theValue;
		}
	}
	
	/**
	 * 把配置文件中固定值，根据属性名设置到bean对应的set方法中
	 * @param o	bean对象
	 * @param proFileName	配置文件名
	 * @param constParamKey	固定的配置信息在配置文件中的key
	 * @return	返回设置值后的bean对象
	 */
	public static Object setObjectValue(Object o,String proFileName,String constParamKey){
		String constParamValue = getValueFromPropertyByKey(proFileName,constParamKey);
		String[] cParamAry = constParamValue.split(PROPER_VALUE_SEPARETOR);
		Class<? extends Object> c = o.getClass();
		Method[] methodAry = c.getMethods();
		
		for(String param : cParamAry){
			String paramName = param.substring(0,param.indexOf(PROPER_KEY_VALUE_SEPARETOR));
			String paramValue = param.substring(param.indexOf(PROPER_KEY_VALUE_SEPARETOR)+1);
			for(Method method : methodAry){
				String methodName = method.getName();
				if(methodName.indexOf(BEAN_SETMETHOD_START_LAB) != -1
						&& methodName.toLowerCase().indexOf(paramName.toLowerCase()) != -1){
					try {
						method.invoke(o,paramValue);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
						return null;
					} catch (IllegalAccessException e) {
						e.printStackTrace();
						return null;
					} catch (InvocationTargetException e) {
						e.printStackTrace();
						return null;
					}
				}
			}
			
		}
		
		return o;
	}
	
	/**
	 * 组装o对象中get方法的值
	 * 把bean中含有paramAry数组里属性的get方法值组合，格式如下：
	 * paramName=paramValue&paramName=paramValue
	 * @param o
	 * @param paramAry
	 * @param sb
	 * @return
	 */
	public static String concatParam(Object o,String[] paramAry,StringBuffer sb){
		Class<? extends Object> c = o.getClass();
		Method[] methodAry = c.getMethods();
		for(String param : paramAry){
			sb.append(param);
			sb.append(PROPER_KEY_VALUE_SEPARETOR);
			for(Method method : methodAry){
				if(method.getName().indexOf(BEAN_GETMETHOD_START_LAB) != -1
						&& method.getName().toLowerCase().indexOf(param.toLowerCase()) != -1){
					try {
						sb.append(method.invoke(o,null));
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
						return null;
					} catch (IllegalAccessException e) {
						e.printStackTrace();
						return null;
					} catch (InvocationTargetException e) {
						e.printStackTrace();
						return null;
					}
				}
			}
			sb.append(URL_PARAM_SEPARETOR);
		}
		
		return sb.toString().substring(0, sb.toString().lastIndexOf(URL_PARAM_SEPARETOR));
	}
}
