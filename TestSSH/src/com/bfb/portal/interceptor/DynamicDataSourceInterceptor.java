package com.bfb.portal.interceptor;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bfb.portal.base.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 *拦截器方式动态更新数据源 
 * @author Administrator
 *
 */
public class DynamicDataSourceInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2355843001322874514L;
	private static Logger log = Logger.getLogger(DynamicDataSourceInterceptor.class);
	
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
		
		DriverManagerDataSource dataSource = (DriverManagerDataSource)wac.getBean("dataSource");
		dataSource.setUrl(ds.getUrl());
		dataSource.setUsername(ds.getUsername());
		dataSource.setPassword(ds.getPassword());
		
		return invocation.invoke();
	}

}
