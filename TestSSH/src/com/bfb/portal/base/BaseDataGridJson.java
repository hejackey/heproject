package com.bfb.portal.base;

import java.util.List;

/**
 * easyui datagrid定制json对象
 * @author Administrator
 *
 * @param <T>
 */
public class BaseDataGridJson<T> {
	private int total;
	private List<T> rows;
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
