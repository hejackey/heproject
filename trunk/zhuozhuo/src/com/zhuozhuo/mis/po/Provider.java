package com.zhuozhuo.mis.po;

import com.zhuozhuo.mis.util.BaseModel;

public class Provider extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2748971927316060785L;
	private String id;
	private String providername;
	private String providercode;
	private String phone;
	private String address;
	private String leadername;
	private String businessman;
	
	private String providerCredit;//供应商信用
	private String providerClass;//供应商等级
	private String agreement;//协议、合同
	private String content;//内容
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProvidername() {
		return providername;
	}
	public void setProvidername(String providername) {
		this.providername = providername;
	}
	public String getProvidercode() {
		return providercode;
	}
	public void setProvidercode(String providercode) {
		this.providercode = providercode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLeadername() {
		return leadername;
	}
	public void setLeadername(String leadername) {
		this.leadername = leadername;
	}
	public String getBusinessman() {
		return businessman;
	}
	public void setBusinessman(String businessman) {
		this.businessman = businessman;
	}
	public String getProviderCredit() {
		return providerCredit;
	}
	public void setProviderCredit(String providerCredit) {
		this.providerCredit = providerCredit;
	}
	public String getProviderClass() {
		return providerClass;
	}
	public void setProviderClass(String providerClass) {
		this.providerClass = providerClass;
	}
	public String getAgreement() {
		return agreement;
	}
	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
