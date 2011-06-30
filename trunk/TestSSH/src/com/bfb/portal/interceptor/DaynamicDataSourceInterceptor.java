package com.bfb.portal.interceptor;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import weblogic.jdbc.common.internal.RmiDataSource;

import com.bfb.portal.base.util.EncypDataSource;
import com.bfb.portal.base.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class DaynamicDataSourceInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2355843001322874514L;
	private static Logger log = Logger.getLogger(DaynamicDataSourceInterceptor.class);
	private DriverManagerDataSource ds;
	
	public void destroy() {

	}

	public void init() {

	}

	/**
	 * 动态加载数据源
	 * 适用spring的DriverManagerDataSource和weblogic的jndi方式
	 */
	public String intercept(ActionInvocation invocation) throws Exception {
		String actionName = ActionContext.getContext().getName();
		log.info("actionName="+actionName);
		
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(
				ServletActionContext.getServletContext());
		
		if(StringUtil.isEmpty(actionName)){
			log.error("请求方法名为空");
			return "";
		}
		
		actionName = actionName.toLowerCase();
		Object o = null;
		if(actionName.startsWith("add") || actionName.startsWith("save")
				|| actionName.startsWith("insert") || actionName.startsWith("create")
				|| actionName.startsWith("update") || actionName.startsWith("edit")
				|| actionName.startsWith("del") || actionName.startsWith("remove")
				){
			o = wac.getBean("master");
			if( o instanceof RmiDataSource){
				System.out.println(((RmiDataSource) o).getJNDINames()[0]);
			}
			else{
				ds = (DriverManagerDataSource)o;
			}
		}
		else{
			o = wac.getBean("slave");
			if( o instanceof RmiDataSource){
				System.out.println(((RmiDataSource) o).getJNDINames()[0]);
			}
			else{
				ds = (DriverManagerDataSource)o;
			}
		}
		
		Object obj = wac.getBean("dataSource");
		if( obj instanceof RmiDataSource){
			obj = o;
		}
		else{
			EncypDataSource dataSource = (EncypDataSource)obj;
			dataSource.setUrl(ds.getUrl());
			dataSource.setUsername(ds.getUsername());
			dataSource.setPassword(ds.getPassword());
		}
		
		return invocation.invoke();
	}

}
