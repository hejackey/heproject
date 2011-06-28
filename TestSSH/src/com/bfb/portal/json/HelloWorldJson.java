package com.bfb.portal.json;

import java.util.List;

import com.bfb.portal.base.BaseJson;
import com.bfb.portal.model.HelloWorld;

public class HelloWorldJson extends BaseJson{
	private List<HelloWorld> rows;

	public List<HelloWorld> getRows() {
		return rows;
	}

	public void setRows(List<HelloWorld> rows) {
		this.rows = rows;
	}
}
