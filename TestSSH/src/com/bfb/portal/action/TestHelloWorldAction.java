package com.bfb.portal.action;

import com.bfb.portal.base.BaseAction;
import com.bfb.portal.model.HelloWorld;

public class TestHelloWorldAction<T> extends BaseAction<T> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5783052855929964194L;
	public HelloWorld model = new HelloWorld();
	
	public String helloWorld(){
		model.setStr("test struts,hello world!!"); 
		
		return SUCCESS;
	}
	public T getModel() {
		// TODO Auto-generated method stub
		return (T)model;
	}

}
