package com.bfb.portal.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class DaynamicDataSourceInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2355843001322874514L;

	public void destroy() {

	}

	public void init() {

	}

	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println(ActionContext.getContext().getName());
		
		return invocation.invoke();
	}

}
