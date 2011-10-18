package com.bfb.commons.spring;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * spring 工具类
 * @author Administrator
 *
 */
public class SpringUtil {
	/**
	 * 获取spring配置文件中的bean
	 * @param context	应用请求的上下文
	 * @param beanName	配置文件中bean名
	 * @return	返回bean对象
	 */
	public static Object getSpringBean(ServletContext context,String beanName){
		WebApplicationContext wac = WebApplicationContextUtils.
			getRequiredWebApplicationContext(context);
		
		return wac.getBean(beanName);
	}
}
