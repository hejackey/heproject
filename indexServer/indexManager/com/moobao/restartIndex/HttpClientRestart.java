package com.moobao.restartIndex;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class HttpClientRestart {
	public static void main(String[] args) throws IOException {
//		HttpClient client = new HttpClient();
//		client.getHostConfiguration()
//				.setHost("search.5366.com", 8080, "http");
//		HttpMethod method = getPostMethod();   // 使用 POST 方式提交数据
//		//HttpMethod method = getGetMethod();  // 使用 POST 方式提交数据
//		client.executeMethod(method);          // 打印服务器返回的状态
//		//System.out.println(method.getStatusLine()); // 打印结果页面
//		//String response = new String(method.getResponseBodyAsString().getBytes("gb2312"));
//		// 打印返回的信息
//		//System.out.println(response);
//		method.releaseConnection();
		getPostMethod( "RESTART_PHONE_INDEX" );
	}

	/**
	 * 使用 GET 方式提交数据(根据type的不同重新引用各类索引)
	 */
	public static HttpMethod getGetMethod() {
		return new GetMethod("/searchWeb/reStartSearcherServlet?infotype=RESTART_PHONE_INDEX");
	}

	/**
	 * 使用 POST 方式提交数据(根据type的不同重新引用各类索引)
	 */
	public static void getPostMethod( String type ) {
		
		HttpClient client = new HttpClient();
		client.getHostConfiguration()
				//.setHost("localhost", 9000, "http");
				.setHost("search.5366.com", 8080, "http");
		
		PostMethod post = new PostMethod("/searchWeb/reStartSearcherServlet");
		NameValuePair simcard = new NameValuePair("infotype",
				type);
		post.setRequestBody(new NameValuePair[] { simcard });

		try {
			client.executeMethod(post);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}          
		// 打印服务器返回的状态
		//System.out.println(post.getStatusLine()); 
//		String response = null;
//		try {
//			response = new String(post.getResponseBodyAsString().getBytes("gb2312"));
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		// 打印返回的信息
		//System.out.println(response);
		post.releaseConnection();
	}
	
	/**
	 * 更新数据字典--手机和配件
	 */
	public static void updateDic( String type ) {
		
		HttpClient client = new HttpClient();
		client.getHostConfiguration()
				//.setHost("localhost", 9000, "http");
		        .setHost("search.5366.com", 8080, "http");
		
		PostMethod post = new PostMethod("/searchWeb/UpdateDataDicServlet");
		NameValuePair simcard = new NameValuePair("infotype",
				type);
		post.setRequestBody(new NameValuePair[] { simcard });

		try {
			client.executeMethod(post);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}          
		post.releaseConnection();
	}
	
	/**
	 * 使用 POST 方式提交数据(根据type的不同重新引用各类的数据字典--手机或配件)
	 */
	public static void reloadDic( String type ) {
		
		HttpClient client = new HttpClient();
		client.getHostConfiguration()
				//.setHost("localhost", 9000, "http");
		        .setHost("search.5366.com", 8080, "http");
		
		PostMethod post = new PostMethod("/searchWeb/reStartDataDicServlet");
		NameValuePair simcard = new NameValuePair("infotype",
				type);
		post.setRequestBody(new NameValuePair[] { simcard });

		try {
			client.executeMethod(post);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}          
		post.releaseConnection();
	}
	
	/**
	 * 使用 POST 方式提交数据(清空指定的Cache)
	 */
	public static void removeCache( String type ) {
		
		HttpClient client = new HttpClient();
		client.getHostConfiguration()
				//.setHost("localhost", 9000, "http");
		        .setHost("search.5366.com", 8080, "http");
		
		PostMethod post = new PostMethod("/searchWeb/RemoveCacheServlet");
		NameValuePair simcard = new NameValuePair("infotype",
				type);
		post.setRequestBody(new NameValuePair[] { simcard });

		try {
			client.executeMethod(post);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}          
		post.releaseConnection();
	}
}
