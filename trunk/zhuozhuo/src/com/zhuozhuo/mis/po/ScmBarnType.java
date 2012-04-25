package com.zhuozhuo.mis.po;

import com.zhuozhuo.mis.util.BaseModel;

public class ScmBarnType extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3999852358508214850L;
	private String id;
	private String parentid;
	private String parentname;
	private String barntypecode;
	private String barntypename;
	private String address;
	private String phone;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getParentname() {
		return parentname;
	}
	public void setParentname(String parentname) {
		this.parentname = parentname;
	}
	public String getBarntypecode() {
		return barntypecode;
	}
	public void setBarntypecode(String barntypecode) {
		this.barntypecode = barntypecode;
	}
	public String getBarntypename() {
		return barntypename;
	}
	public void setBarntypename(String barntypename) {
		this.barntypename = barntypename;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
