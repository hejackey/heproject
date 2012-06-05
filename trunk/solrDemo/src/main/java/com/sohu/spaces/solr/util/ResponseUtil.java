package com.sohu.spaces.solr.util;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {
	/**
	 * 设置请求不缓存
	 * @param response
	 */
    public static void setNoCache(HttpServletResponse response) {
        if (response != null) {
            response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        }
    }
    
    /**
     * 输出相应请求信息
     * @param response HttpServletResponse
     * @param msg	输出信息
     * @throws IOException
     */
    public static void responseWrite(HttpServletResponse response,String msg) throws IOException{
    	response.setContentType("text/html; charset=UTF-8");
    	response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        PrintWriter pw = response.getWriter();
    	pw.write(msg);
    	pw.flush();
    	pw.close();
    }
}
