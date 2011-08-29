package com.bfb.commons.http;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;


import com.bfb.commons.cache.JavaMemcacheClient;
import com.bfb.commons.cookie.CookieUtils;
import com.bfb.commons.random.RandomGUID;
import com.danga.MemCached.MemCachedClient;

public class HttpSessionUtil{
	private static MemCachedClient memClient = JavaMemcacheClient.getmcc();

	public static void setAttribute(String key,String value){
		memClient.set(key, value);
	}
	
	public static Object getAttribute(String key){
		return memClient.get(key);
	}
	
	public static boolean deleteMemContent(String key){
		return memClient.delete(key);
	}
	
	/**
	 * 以guid作为key，缓存value，同时guid作为cookie存到客户端
	 * @param response	httpservletresponse
	 * @param key	cookie中的key
	 * @param value	缓存的value
	 * @param times	缓存的时间，以分钟为单位
	 * @return
	 */
	public static String setMemCache(HttpServletResponse response,String key,Object value,int times){	
		RandomGUID random = new RandomGUID();
		String guid="";
		if(random!=null)
			guid = random.toString();
		
		if(times==0)
			memClient.set(guid, value);
		else
			memClient.set(guid, value,new Date(times*60*1000));//指定缓存时间
				
		CookieUtils.setCookie(response, key, guid, 60*times);
		return guid;
	}
	
	/**
	 * 按指定时间缓存key-value
	 * @param key	缓存的key
	 * @param value	缓存的值
	 * @param times	缓存的时间，单位分钟
	 */
	public static void setMemCache(String key,Object value,int times){	
		if(times==0)
			memClient.set(key, value);
		else
			memClient.set(key, value,new Date(times*60*1000));//指定缓存时间
	}
	
	public static void main(String[] args){
		//System.out.println(setAttribute("123"));
	}
}
