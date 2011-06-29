package com.bfb.portal.action;

import java.lang.reflect.Type;
import java.util.List;

import com.bfb.portal.base.BaseAction;
import com.bfb.portal.base.BaseDataGridJson;
import com.bfb.portal.base.util.ResponseUtil;
import com.bfb.portal.manager.TestHelloWorldManager;
import com.bfb.portal.model.HelloWorld;
import com.google.gson.reflect.TypeToken;

public class TestHelloWorldAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5783052855929964194L;
	public HelloWorld model = new HelloWorld();
	private TestHelloWorldManager testHelloWorldManager;
	
	
	public String helloWorld(){
		//model.setStr("test struts,hello world!!"); 
		//model.setParam(testHelloWorldManager.sayHello("start call service layer method"));
		/*HelloWorld hello = new HelloWorld();
		hello.setId(3);
		hello.setStr("狗日的");
		hello.setParam("window控制台下还是乱码");
		testHelloWorldManager.saveHelloWorld(hello);*/
		
		model = testHelloWorldManager.getHelloWorld(3);
		if(model==null)
			model = new HelloWorld();
		
		return SUCCESS;
	}

	/**
	 * 获取table内容
	 */
	public void getListToJson(){
		int count = testHelloWorldManager.getHelloWorldCount(model);
		List<HelloWorld> list = testHelloWorldManager.getHelloWorldList(model);
		
		BaseDataGridJson<HelloWorld> json = new BaseDataGridJson<HelloWorld>();
		json.setTotal(count);
		json.setRows(list);
		
		Type type = new TypeToken<BaseDataGridJson<HelloWorld>>() { }.getType();
		ResponseUtil.printJson(json, this.getResponse(), type);
	}
	
	public HelloWorld getModel() {
		return model;
	}
	
	public TestHelloWorldManager getTestHelloWorldManager() {
		return testHelloWorldManager;
	}
	
	public void setTestHelloWorldManager(TestHelloWorldManager testHelloWorldManager) {
		this.testHelloWorldManager = testHelloWorldManager;
	}
}
