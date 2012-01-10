package com.bfb.commons.http;

import java.io.IOException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;


public class HttpClientUtil {
	/**
	 * 发起http post请求
	 * @param url	请求的url，含所有的请求参数
	 * @return	返回请求结果
	 */
	public static String httpClientPostMethod(String url){
		HttpClient client = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		
		try {
			client.executeMethod(postMethod);
			
			return postMethod.getResponseBodyAsString();
		} catch (HttpException e) {
			e.printStackTrace();
			return "";
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		finally{
			postMethod.releaseConnection();
		}
	}
	
	/**
	 * 发起http post请求
	 * @param url	请求的url包含请求参数
	 * @param data	请求的参数
	 * @return	返回请求结果
	 */
	public static String httpClientPostMethod(String url,NameValuePair[] data){
		HttpClient client = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		
		try {
			postMethod.setRequestBody(data);
			client.executeMethod(postMethod);
			
			return postMethod.getResponseBodyAsString();
		} catch (HttpException e) {
			e.printStackTrace();
			return "";
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		finally{
			postMethod.releaseConnection();
		}
	}
	
	public static void main(String[] args){
	    MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();  
	    HttpClient client = new HttpClient(connectionManager);// 在某个线程中。  
	    GetMethod get = new GetMethod("http://jakarta.apache.org/");  
	    try {  
	    	client.executeMethod(get);
	    	System.out.println(get.getResponseBodyAsStream());  
	    } catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {  
	    	get.releaseConnection();  
	    }  
		//System.out.println(httpClientPostMethod("https://www.8f8.com"));
	}
}
