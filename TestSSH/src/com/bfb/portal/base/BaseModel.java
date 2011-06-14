package com.bfb.portal.base;

import java.io.Serializable;

import com.bfb.portal.base.page.PageInfo;


public class BaseModel implements Serializable {
	private static final long serialVersionUID = -5337484404562836810L;
	PageInfo pageInfo = new PageInfo(1,20,5);
	String context;
	String page;
	String result;
	
	public PageInfo getPageInfo(){
		return pageInfo;
	}
	
	public void setPageInfo(PageInfo pageInfo){
		this.pageInfo = pageInfo;
	}
	
	public String getContext(){
		return context;
	}
	
	public void setContext(String context){
		this.context = context;
	}
	
	public String getPage(){
		return page;
	}
	
	public void setPage(String page){
		this.page = page;
	}
	
	public String getResult(){
		return result;
	}

	public void setResult(String result){
		this.result = result;
	}
}
