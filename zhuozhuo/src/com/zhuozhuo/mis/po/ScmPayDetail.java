package com.zhuozhuo.mis.po;

import java.io.Serializable;
import java.util.Date;

public class ScmPayDetail implements Serializable {
	
	private static final long serialVersionUID = -5889800134692124137L;
	private Integer id;
	private String did;//明细ID
	private String masterId;//单据ID
	private String srcSheetId;//来源单号
	private String amt;//本单金额
	private String payedAmt;//已付金额
	private String payAmt;//本次现付
	private String plusAmt;//余额
	private String payDate;//应付日期
	private String auditSign;//复核标志
	private String remark;//备注
	private String providercode;
	private String providername;	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public String getMasterId() {
		return masterId;
	}
	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
	public String getSrcSheetId() {
		return srcSheetId;
	}
	public void setSrcSheetId(String srcSheetId) {
		this.srcSheetId = srcSheetId;
	}
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
	public String getPayedAmt() {
		return payedAmt;
	}
	public void setPayedAmt(String payedAmt) {
		this.payedAmt = payedAmt;
	}
	public String getPayAmt() {
		return payAmt;
	}
	public void setPayAmt(String payAmt) {
		this.payAmt = payAmt;
	}
	public String getPlusAmt() {
		return plusAmt;
	}
	public void setPlusAmt(String plusAmt) {
		this.plusAmt = plusAmt;
	}	
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getAuditSign() {
		return auditSign;
	}
	public void setAuditSign(String auditSign) {
		this.auditSign = auditSign;
	}
	public String getProvidercode() {
		return providercode;
	}
	public void setProvidercode(String providercode) {
		this.providercode = providercode;
	}
	public String getProvidername() {
		return providername;
	}
	public void setProvidername(String providername) {
		this.providername = providername;
	}	
}
