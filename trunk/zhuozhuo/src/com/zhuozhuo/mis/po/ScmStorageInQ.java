package com.zhuozhuo.mis.po;

import java.io.Serializable;

public class ScmStorageInQ implements Serializable{
	
	private static final long serialVersionUID = 8238044870137645929L;
	private String sheetId;
	private String departmentId;
	private String startTime;
	private String endTime;
	public String getSheetId() {
		return sheetId;
	}
	public void setSheetId(String sheetId) {
		this.sheetId = sheetId;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
