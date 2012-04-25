package com.zhuozhuo.mis.po;

import java.io.Serializable;

public class PProvider implements Serializable{
	 private static final long serialVersionUID = 1L;
	 private int id;
	 private String providerName;//供应商名称
	 private String providerCode;//供应商编号
	 private String department;//部门
	 private String businessMan;//经办人
	 private String providerType;//供应商类别
	 private String providerClass;//供应商等级
	 private String providerCredit;//供应商信用
	 private String province;//省\市
	 private String county;//县
	 private String leaderName;//负责人
	 private String address;//地址
	 private String phone;//电话
	 private String account;//帐户
	 private String content;//内容
	 private String createDate;//建立日期
	 private String agreement;//协议、合同
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getBusinessMan() {
		return businessMan;
	}
	public void setBusinessMan(String businessMan) {
		this.businessMan = businessMan;
	}
	public String getProviderType() {
		return providerType;
	}
	public void setProviderType(String providerType) {
		this.providerType = providerType;
	}
	public String getProviderClass() {
		return providerClass;
	}
	public void setProviderClass(String providerClass) {
		this.providerClass = providerClass;
	}
	public String getProviderCredit() {
		return providerCredit;
	}
	public void setProviderCredit(String providerCredit) {
		this.providerCredit = providerCredit;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getLeaderName() {
		return leaderName;
	}
	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
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
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getAgreement() {
		return agreement;
	}
	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}
}
