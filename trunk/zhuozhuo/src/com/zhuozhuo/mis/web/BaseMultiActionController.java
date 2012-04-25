package com.zhuozhuo.mis.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class BaseMultiActionController extends MultiActionController {
	/**
	 * 对绑定的数据进行校验。
	 * 
	 * @param request HttpServlertRequest
	 * @param command FormBean Command
	 * @param modelAndView 如果校验失败，则将错误ExceptionModel绑定至该 ModelAndView 中
	 * @return 是否校验成功
	 */
	public boolean validation( HttpServletRequest request, Object command, ModelAndView modelAndView) {
		boolean isAllow = false;
		try {
			this.bind(request, command); //closeNoCatch() So if your Validator returns any Error, closeNoCatch will throw a ServletRequestBindingException
			isAllow = true;
		} catch (ServletRequestBindingException bindingException) {//校验失败将抛出该异常
			BindException bindException = (BindException) bindingException.getRootCause();
			modelAndView.addAllObjects(bindException.getModel());
		} catch(Exception ex){
			//LOG.error("Validation Be Throws Exception.");
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return isAllow;
	}
}
