package com.bfb.commons.security;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class BfbSecurityFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		
		Enumeration<String> params = req.getParameterNames();
		
		while(params.hasMoreElements()){
			String paramName = params.nextElement();
			String paramValue = req.getParameter(paramName);
			
			System.out.println("paramName==="+paramName);
			System.out.println("paramValue==="+paramValue);
			
			if(paramName.equals("param3")){
				req.setAttribute(paramName, "param3value3");
			}
		}
		
		System.out.println(req.getParameter("param3"));
		System.out.println(req.getAttribute("param3"));
		
		
		chain.doFilter(req, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("filter init");
		Enumeration em = config.getInitParameterNames();
		while(em.hasMoreElements()){
			System.out.println(em.nextElement());
		}
	}

}
