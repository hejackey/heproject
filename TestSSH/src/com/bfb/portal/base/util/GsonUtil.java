package com.bfb.portal.base.util;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;

/**
* 
*    
* 类描述：Gson工具类  
* 创建人：hexiaoliang  
* 创建时间：2010-4-17 上午11:11:45  
* 修改人：  
* 修改时间：
* 修改备注：  
* @version   1.0
**/

public class GsonUtil {
	private static Gson gson = new Gson();
	/**
	 * 
	*    
	* 方法描述：gson串转成对象  
	* 创建人：hexiaoliang  
	* 创建时间：2010-4-17 上午11:12:14    
	* @param <T>
	* @param sourceGson   gson串
	* @param targetClass  目标对象类
	* @return 目标对象
	* @version   1.0
	*
	 */
	public static <T> T fromGson(String sourceGson,Class<T> targetClass){
		try{
			return gson.fromJson(sourceGson, targetClass);
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
			return null;
		}
	}
		
	/**
	 * 
	*    
	* 方法描述：对象转成gson串
	* 创建人：hexiaoliang  
	* 创建时间：2010-4-17 上午11:12:38    
	* @param o  对象
	* @return   gson串
	* @version   1.0
	*
	 */
	public static String toGson(Object o){
		return gson.toJson(o);
	}
	
	public static <T> T[] fromGsonArray(String sourceGson, Class<T[]> targetClass){
		return gson.fromJson(sourceGson, targetClass);
	}
	
	public static String toGson(Object o, Type targetClass){
		return gson.toJson(o, targetClass);
	}
	
	public static <T> List<T> fromGsonList(String sourceGson, Type type){
		return gson.fromJson(sourceGson, type);
	}
	
	public static void main(String[] args){
//		Type type = new TypeToken<List<HelloWorld>>() { }.getType();
	}
}
