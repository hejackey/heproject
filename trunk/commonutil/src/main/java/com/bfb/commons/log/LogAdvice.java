package com.bfb.commons.log;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

/**
 * 基于spring aop的方法调用拦截日志类
 * 需要在spring配置文件中加入一下配置
 * <!-- 传统aop代理  -->
    <bean id="logAdvice" class="com.bfb.portal.log.LogAdvice"/>
    <bean id="aspectjPointcut" class="org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor">  
        <property name="advice" ref="logAdvice" /> 
        <property name="expression" value="execution(* *..manager..*.*(..))" /> 
    </bean>  
 * @author Administrator
 * @version 1.0
 * @date 2011-10-19
 */
public class LogAdvice implements MethodBeforeAdvice, AfterReturningAdvice {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Logger log = Logger.getLogger(LogAdvice.class);
	
	/**
	 * 方法调用前拦截生成操作日志
	 */
	@Override
	public void before(Method method, Object[] params, Object obj)
			throws Throwable {
		StringBuilder sb = new StringBuilder();
		sb.append(sdf.format(new Date())).append(" INFO");
		sb.append(" 开始调用");
		sb.append(method.getDeclaringClass().getName());
		sb.append("类的");
		sb.append(method.getName());
		sb.append("的方法，传入参数值:");
		sb.append(formatParams(params));
		
		log.info(sb.toString());
	}

	/**
	 * 方法调用后拦截生成操作日志
	 */
	@Override
	public void afterReturning(Object retVal, Method method, Object[] params,
			Object arg3) throws Throwable {
		StringBuilder sb = new StringBuilder();
		sb.append(sdf.format(new Date())).append(" INFO");
		sb.append(" 结束调用");
		sb.append(method.getDeclaringClass().getName());
		sb.append("类的");
		sb.append(method.getName());
		sb.append("的方法，返回值:");
		sb.append(retVal);
		
		log.info(sb.toString());
	}

	/**
	 * 格式化参数
	 * @param params
	 * @return
	 */
	public String formatParams(Object[] params){
		StringBuilder sb = new StringBuilder();
		for(Object o : params){
			sb.append(o).append("|");
		}
		
		return sb.toString().substring(0,sb.toString().length()-1);
	}
}
