package com.bfb.portal.action;

import java.lang.reflect.Type;
import java.util.List;

import com.bfb.commons.http.ResponseUtil;
import com.bfb.commons.json.gson.GsonUtil;
import com.bfb.commons.string.StringUtil;
import com.bfb.portal.base.action.BaseAction;
import com.bfb.portal.base.model.BaseDataGridJson;
import com.bfb.portal.manager.TestHelloWorldManager;
import com.bfb.portal.model.HelloWorld;
import com.bfb.portal.validator.BaseValidator;
import com.bfb.portal.validator.HelloWorldValidator;
import com.google.gson.reflect.TypeToken;

public class TestHelloWorldAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5783052855929964194L;
	public HelloWorld model = new HelloWorld();
	private TestHelloWorldManager testHelloWorldManager;
	private BaseValidator valid = new HelloWorldValidator();
	private int unsafeParam;	//类变量多线程下是安全的
	
	public String layout(){
		unsafeParam=2;
		System.out.println("layout==========="+unsafeParam);
		return SUCCESS;
	}
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
			model.getRows();
			model.getPage();
			model.getPageLimit();
			int count = testHelloWorldManager.getHelloWorldCount(model);
			List<HelloWorld> list = testHelloWorldManager.getHelloWorldList(model);
			
			BaseDataGridJson<HelloWorld> json = new BaseDataGridJson<HelloWorld>();
			json.setTotal(count);
			json.setRows(list);
			
			Type type = new TypeToken<BaseDataGridJson<HelloWorld>>() { }.getType();
			String res = GsonUtil.toGson(json, type);
			
			ResponseUtil.respAjaxResultCode(this.getResponse(),res);
			
			System.out.println("getListToJson==========="+unsafeParam);
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

	/**
	 * 保存记录
	 */
	public void saveHelloWorldAjax(){
		try{
			String res = valid.validForm(model);
			if(!StringUtil.isEmpty(res)){
				this.addFieldError("formErr", res);
				ResponseUtil.respAjaxResultCode(this.getResponse(),"");
				return;
			}
			
			testHelloWorldManager.saveHelloWorld(model);
			ResponseUtil.respAjaxResultCode( this.getResponse(),"{'success':'true'}");
			
		}catch(Exception e){
			e.printStackTrace();
			this.addFieldError("formErr", "异常!");
			ResponseUtil.respAjaxResultCode(this.getResponse(),"");
			
		}
	}
	
	/**
	 * 更新记录
	 */
	public void updateHelloWorldAjax(){
		try{
			String res = valid.validForm(model);
			if(!StringUtil.isEmpty(res)){
				this.addFieldError("formErr", res);
				ResponseUtil.respAjaxResultCode(this.getResponse(),"");
				return;
			}
			
			testHelloWorldManager.updateHellworld(model);
			ResponseUtil.respAjaxResultCode( this.getResponse(),"{\"success\":\"true\"}");
			
		}catch(Exception e){
			e.printStackTrace();
			this.addFieldError("formErr", "异常!");
			ResponseUtil.respAjaxResultCode( this.getResponse(),"");
			
		}
	}
	
	/**
	 * 删除记录
	 */
	public void delHelloWorldAjax(){
		try{
			testHelloWorldManager.delHelloWorld(model.getId());
			ResponseUtil.respAjaxResultCode( this.getResponse(),"{\"success\":\"true\"}");
			
		}catch(Exception e){
			e.printStackTrace();
			this.addFieldError("formErr", "异常!");
			ResponseUtil.respAjaxResultCode(this.getResponse(),"" );
			
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