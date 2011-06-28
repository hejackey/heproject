package com.bfb.portal.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.bfb.portal.base.BaseAction;
import com.bfb.portal.base.util.GsonUtil;
import com.bfb.portal.json.HelloWorldJson;
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
	
	public String list(){
		return SUCCESS;
	}
	
	public void getListToJson(){
		
		System.out.println(model.getRows());
		System.out.println(model.getPage());
		if(model.getRows()==0)
			model.setRows(10);
		if(model.getPage()==0)
			model.setPage(1);
		int count = testHelloWorldManager.getHelloWorldCount(model);
		List<HelloWorld> list = testHelloWorldManager.getHelloWorldList(model);
		
		HelloWorldJson json = new HelloWorldJson();
		json.setTotal(count);
		json.setRows(list);
		
		
		PrintWriter writer;
		try {
			String res = GsonUtil.toGson(json, HelloWorldJson.class);
			
			this.getResponse().setCharacterEncoding("utf-8");
			writer = this.getResponse().getWriter();
			writer.write(res);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			writer = null;
		}
		
	}
	public T getModel() {
		return (T)model;
	}
	
	public TestHelloWorldManager getTestHelloWorldManager() {
		return testHelloWorldManager;
	}
	
	public void setTestHelloWorldManager(TestHelloWorldManager testHelloWorldManager) {
		this.testHelloWorldManager = testHelloWorldManager;
	}
}
