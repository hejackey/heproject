package com.bfb.commons.http;

import java.io.IOException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
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
}
