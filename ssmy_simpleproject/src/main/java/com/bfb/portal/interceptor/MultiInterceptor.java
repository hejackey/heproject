package com.bfb.portal.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class MultiInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1007201670613555900L;

	public void destroy() {

	}

	public void init() {

	}

	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("全局拦截器");
		
		return invocation.invoke();
	}

}
