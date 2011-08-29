package com.bfb.commons.http;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;



public class ResponseUtil {
	/**
	 * ajax请求输出字符串
	 * @param resp	httpservletresponse
	 * @param result	返回的字符串(string、xml、json格式串)
	 */
	public static void respAjaxResultCode(HttpServletResponse  resp,String result){
    	try {
    		resp.setContentType("text/html");
    		resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(result);
			resp.getWriter().flush();
	    	resp.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
