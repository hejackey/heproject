package com.zhuozhuo.mis.model;

import java.util.List;

import com.zhuozhuo.mis.util.BaseModel;

public class QueryProvider extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String providerName;// 供应商名称
	private String providerCode;// 供应商编号
	private String address;// 地址
	private String account;// 帐户
	private List<QueryProvider> providerList;
	
	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getProviderCode() {
		return providerCode;
	}

	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public List<QueryProvider> getProviderList() {
		return providerList;
	}

	public void setProviderList(List<QueryProvider> providerList) {
		this.providerList = providerList;
	}
}
