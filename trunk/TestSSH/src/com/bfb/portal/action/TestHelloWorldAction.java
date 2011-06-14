package com.bfb.portal.action;

import com.bfb.portal.base.BaseAction;
import com.bfb.portal.manager.TestHelloWorldManager;
import com.bfb.portal.model.HelloWorld;

public class TestHelloWorldAction<T> extends BaseAction<T> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5783052855929964194L;
	public HelloWorld model = new HelloWorld();
	private TestHelloWorldManager testHelloWorldManager;
	
	public String helloWorld(){
		model.setStr("test struts,hello world!!"); 
		model.setParam(testHelloWorldManager.sayHello("start call service layer method"));
		
		return SUCCESS;
	}
	public T getModel() {
		// TODO Auto-generated method stub
		return (T)model;
	}
	
	public TestHelloWorldManager getTestHelloWorldManager() {
		return testHelloWorldManager;
	}
	
	public void setTestHelloWorldManager(TestHelloWorldManager testHelloWorldManager) {
		this.testHelloWorldManager = testHelloWorldManager;
	}

}
