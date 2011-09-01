package com.bfb.portal.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LocalInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4111660337467072139L;

	public void destroy() {

	}

	public void init() {

	}

	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("局部拦截器，需要在拦截的地方配置interceptor");
		
		return invocation.invoke();
	}

}
