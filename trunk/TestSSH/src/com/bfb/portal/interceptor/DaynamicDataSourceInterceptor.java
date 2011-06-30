package com.bfb.portal.interceptor;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

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
	
	public void destroy() {

	}

	public void init() {

	}

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
		DriverManagerDataSource ds = null;
		if(actionName.startsWith("add") || actionName.startsWith("save")
				|| actionName.startsWith("insert") || actionName.startsWith("create")
				|| actionName.startsWith("update") || actionName.startsWith("edit")
				|| actionName.startsWith("del") || actionName.startsWith("remove")
				){
			ds = (DriverManagerDataSource)wac.getBean("master");
		}
		else{
			ds = (DriverManagerDataSource)wac.getBean("slave");
		}
		
		EncypDataSource dataSource = (EncypDataSource)wac.getBean("dataSource");
		dataSource.setUrl(ds.getUrl());
		dataSource.setUsername(ds.getUsername());
		dataSource.setPassword(ds.getPassword());
		
		DataSourceTransactionManager manager = (DataSourceTransactionManager)wac.getBean("transactionManager");
		manager.setDataSource(dataSource);
		
		
		return invocation.invoke();
	}

}
