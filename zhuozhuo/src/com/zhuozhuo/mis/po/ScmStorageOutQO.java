package com.zhuozhuo.mis.po;

import java.io.Serializable;

public class ScmStorageOutQO  implements Serializable{
	private static final long serialVersionUID = 8247729023471518958L;
	private String sheetId;
	private String clientId;
	private String barnId;
	private String srcSheetId;
	public String getSheetId() {
		return sheetId;
	}
	public void setSheetId(String sheetId) {
		this.sheetId = sheetId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getBarnId() {
		return barnId;
	}
	public void setBarnId(String barnId) {
		this.barnId = barnId;
	}
	public String getSrcSheetId() {
		return srcSheetId;
	}
	public void setSrcSheetId(String srcSheetId) {
		this.srcSheetId = srcSheetId;
	}
	

}
