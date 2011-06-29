package com.bfb.portal.base;

import java.io.Serializable;

import com.bfb.portal.base.page.PageInfo;

/**
 * model基础类
 * @author Administrator
 *
 */
public class BaseModel implements Serializable {
	private static final long serialVersionUID = -5337484404562836810L;
	PageInfo pageInfo = new PageInfo(1,20,5);
	String context;
	String result;
	
	private int rows;	//每页记录数，适用easyui分页控件
	private int page;	//当前页码，适用easyui分页控件
	private String pageLimit;	//分页表达式，适用mysql数据库
	
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
	public String getResult(){
		return result;
	}

	public void setResult(String result){
		this.result = result;
	}

	public int getRows() {
		if(rows == 0)
			rows = 10;
		
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		if(page == 0)
			page = 1;
		
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getPageLimit() {
		return rows*(page-1)+","+rows*page;
	}

	public void setPageLimit(String pageLimit) {
		this.pageLimit = pageLimit;
	}
}
