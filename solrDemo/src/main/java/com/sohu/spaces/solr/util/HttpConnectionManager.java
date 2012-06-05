package com.sohu.spaces.solr.util;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;


/**
 * httpclient对象管理类
 * @author xiaolianghe
 *
 */
public class HttpConnectionManager {
    /** 
     * 最大连接数 
     */  
    public final static int MAX_TOTAL_CONNECTIONS =  ConstantUtil.HTTP_MAX_TOTAL_CONNECTIONS;  
    /** 
     * 每个路由最大连接数 
     */  
    public final static int MAX_ROUTE_CONNECTIONS = ConstantUtil.HTTP_MAX_CONNECTION_PER_ROUTER;  
    /** 
     * 连接超时时间 
     */  
    public final static int CONNECT_TIMEOUT = ConstantUtil.HTTP_CONNECTION_TIMEOUT;
    /** 
     * 读取超时时间 
     */  
    public final static int READ_TIMEOUT = ConstantUtil.HTTP_SOCKET_TIME_OUT;  
    
    private static  ThreadSafeClientConnManager cm = null;
    
    static {  
        SchemeRegistry schemeRegistry = new SchemeRegistry();  
        schemeRegistry.register(  
                 new Scheme("http", Integer.valueOf(ConstantUtil.HTTP_GET_USER_PORT), PlainSocketFactory.getSocketFactory()));  
          
        cm = new ThreadSafeClientConnManager(schemeRegistry);  
        cm.setMaxTotal(MAX_TOTAL_CONNECTIONS);  
	    cm.setDefaultMaxPerRoute(MAX_ROUTE_CONNECTIONS);
    }  
  
    /**
     * 获取httpclient
     * @return
     */
    public static HttpClient getHttpClient() {  
    	HttpClient client = new DefaultHttpClient(cm);
    	HttpParams params = client.getParams();
    	params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, CONNECT_TIMEOUT);
    	params.setParameter(CoreConnectionPNames.SO_TIMEOUT, READ_TIMEOUT);
    	
    	return client;
    }  

    public static void main(String[] args){
    	HttpClient client = getHttpClient();
    	HttpParams params = client.getParams();
    	HttpConnectionParams.setConnectionTimeout(params, CONNECT_TIMEOUT);
    	HttpConnectionParams.setSoTimeout(params, READ_TIMEOUT);
    }
}
