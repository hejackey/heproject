package com.zhuozhuo.mis.po;

import java.io.Serializable;

public class ScmPayQ implements Serializable{

	private static final long serialVersionUID = 4194967947266823014L;
	private String sheetId;
	private String userId;
	private String checkCode;
	public String getSheetId() {
		return sheetId;
	}
	public void setSheetId(String sheetId) {
		this.sheetId = sheetId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCheckCode() {
		return checkCode;
	}
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
}
