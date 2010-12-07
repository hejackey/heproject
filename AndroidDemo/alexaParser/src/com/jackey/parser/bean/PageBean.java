package com.jackey.parser.bean;

import java.io.Serializable;

public class PageBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4173315786854925606L;
	private int startRow=1;
	private int endRow;
	private int groupSize=10;
	
	public PageBean(){
		
	}
	
	public PageBean(int sRow,int gSize){		
		this.startRow = (sRow-1)*gSize+1;
		this.endRow = gSize;
		this.groupSize = gSize;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	public int getGroupSize() {
		return groupSize;
	}
	public void setGroupSize(int groupSize) {
		this.groupSize = groupSize;
	}
	
	
	
}
