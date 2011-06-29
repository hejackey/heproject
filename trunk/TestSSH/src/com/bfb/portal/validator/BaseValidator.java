package com.bfb.portal.validator;

/**
 * 定义基础校验类
 * 默认校验action提交的form内容，可在子类中自定义方法做其他校验
 * @author Administrator
 *
 */
public abstract class BaseValidator {
	//校验返回结果
	protected String res;
	
	/**
	 * 校验form内容
	 * @param o form提交对应action的model对象
	 * @return
	 */
	public abstract String validForm(Object o);
}
