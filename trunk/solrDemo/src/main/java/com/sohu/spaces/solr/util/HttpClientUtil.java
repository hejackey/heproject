package com.sohu.spaces.solr.util;

import java.io.UnsupportedEncodingException;

import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import com.mysql.jdbc.StringUtils;


/**
 * httpclient包装类
 * @author xiaolianghe
 *
 */
public class HttpClientUtil {
	
	/**
	 * 取httppost对象
	 * @param uri	post请求的uri地址
	 * @param list	post参数列表
	 * @param charset	post请求字符编码
	 * @return httppost对象
	 * @throws UnsupportedEncodingException
	 */
	public static HttpPost getHttpPost(String uri,List<NameValuePair> list,String charset) throws UnsupportedEncodingException{
		HttpEntity entity = new UrlEncodedFormEntity(list, charset);
		HttpPost httpPost = new HttpPost(uri);
		httpPost.setEntity(entity);
		
		return httpPost;
	}
	
	/**
	 * 获取httpGet对象
	 * @param url	get请求的uri地址
	 * @param map	请求参数map集合
	 * @param charset	字符l编码(utf-8)
	 * @return	httpget对象
	 * @throws UnsupportedEncodingException
	 */
	public static HttpGet getHttpGet(String url,Map<String,String> map,String charset) throws UnsupportedEncodingException, URISyntaxException{
		StringBuffer sb = null;
		if(!StringUtils.isNullOrEmpty(url) ){
			if(map != null){
				Set<String> keySet = map.keySet();
				sb = new StringBuffer();
				sb.append(url);
				
				for(String key : keySet){
					sb.append(key);
					sb.append("=");
					sb.append(URLEncoder.encode(map.get(key),charset));
					sb.append("&");
				}
				
				int len = sb.toString().lastIndexOf("&");
				return new HttpGet(sb.toString().substring(0, len));
			}
			else{
				return new HttpGet(url);
			}
		}
		
		return null;
	}
	/**
	 * 组装post请求参数
	 * @param postParamList
	 * @return
	 */
	public static List<NameValuePair> getParamNameValuePair(Map<String,String> paramMap){
		List<NameValuePair> postParams = null;
		if(paramMap != null){
			postParams = new ArrayList<NameValuePair>();
			Set<String> keySet = paramMap.keySet();
		
			for(String key : keySet){
				postParams.add(new BasicNameValuePair(key,paramMap.get(key)));
			}
		}

		return postParams;
	}
}
