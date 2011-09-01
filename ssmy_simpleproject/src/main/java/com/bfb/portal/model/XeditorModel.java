package com.bfb.portal.model;

import java.util.List;

import com.bfb.portal.base.model.BaseModel;

public class XeditorModel extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7588737220926432589L;
	private String conValue;
	private String id;
	private HelloWorld hellWorld;
	private List<XeditorModel> list;
	
	public String getConValue() {
		return conValue;
	}

	public void setConValue(String conValue) {
		this.conValue = conValue;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public HelloWorld getHellWorld() {
		return hellWorld;
	}

	public void setHellWorld(HelloWorld hellWorld) {
		this.hellWorld = hellWorld;
	}

	public List<XeditorModel> getList() {
		return list;
	}

	public void setList(List<XeditorModel> list) {
		this.list = list;
	}
	
}
