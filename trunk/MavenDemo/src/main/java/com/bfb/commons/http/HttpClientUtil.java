package com.bfb.commons.http;

import java.io.IOException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;


public class HttpClientUtil {
	/**
	 * 发起http post请求
	 * @param url	请求的url
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			postMethod.releaseConnection();
		}
		return "";
	}
}
