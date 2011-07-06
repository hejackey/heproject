package com.bfb.portal.action;

import java.lang.reflect.Type;
import java.util.List;

import com.bfb.portal.base.action.BaseAction;
import com.bfb.portal.base.model.BaseDataGridJson;
import com.bfb.portal.base.util.ResponseUtil;
import com.bfb.portal.manager.TestHelloWorldManager;
import com.bfb.portal.model.HelloWorld;
import com.bfb.portal.validator.BaseValidator;
import com.bfb.portal.validator.HelloWorldValidator;
import com.google.gson.reflect.TypeToken;
import com.bfb.portal.base.util.StringUtil;

public class TestHelloWorldAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5783052855929964194L;
	public HelloWorld model = new HelloWorld();
	private TestHelloWorldManager testHelloWorldManager;
	private BaseValidator valid = new HelloWorldValidator();
	
	public String helloWorld(){
		model = testHelloWorldManager.getHelloWorld(3);
		if(model==null)
			model = new HelloWorld();
		
		return SUCCESS;
	}

	/**
	 * 获取table内容
	 */
	public void getListToJson(){
		try{
			int count = testHelloWorldManager.getHelloWorldCount(model);
			List<HelloWorld> list = testHelloWorldManager.getHelloWorldList(model);
			
			BaseDataGridJson<HelloWorld> json = new BaseDataGridJson<HelloWorld>();
			json.setTotal(count);
			json.setRows(list);
			
			Type type = new TypeToken<BaseDataGridJson<HelloWorld>>() { }.getType();
			ResponseUtil.printJson(json, this.getResponse(), type);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加记录
	 */
	public String saveHelloWorld(){
		try{
			String res = valid.validForm(model);
			if(!StringUtil.isEmpty(res)){
				this.addFieldError("formErr", res);
				return INPUT;
			}
			
			testHelloWorldManager.saveHelloWorld(model);
			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.addFieldError("formErr", "保存出错!");
			
			return INPUT;
		}
		
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
