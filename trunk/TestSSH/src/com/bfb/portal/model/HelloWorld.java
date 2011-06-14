package com.bfb.portal.model;

import java.io.Serializable;

import com.bfb.portal.base.BaseModel;

public class HelloWorld extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8501562069162474242L;
	private String str;
	private String param;
	
	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	
}
