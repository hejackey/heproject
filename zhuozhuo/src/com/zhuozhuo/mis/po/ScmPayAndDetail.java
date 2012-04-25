package com.zhuozhuo.mis.po;

import java.io.Serializable;
import java.util.Date;

public class ScmPayAndDetail implements Serializable{
	private static final long serialVersionUID = -8596538696737801711L;
	private Integer id;//
	private String   sheetId;//单据ID
	private String  makerId;//制单人ID
	private Date  createDate;//
	private String  sheetState;//单据状态
	private String  providerId;//供应商编码
	private String  providerid;//供应商编码
	private String  departmentId;//部门编码
	private String  userId;//用户编码
	private String  sumAmt;//本单金额
	private String  sumOverAmt;//已付金额
	private String  sumPayAmt;//本次现付
	private String  sumPlusAmt;//付款余额
	private String  paymentType;//付款方式
	private String  checkCode;//支票号
	private String  auditId;//审计编号
	private Date  auditDate;//审计日期
	private String  remark;
	private String detailid[];
	private String arrid[];
	
	// 明细表的内容
	private String[] did;    
	private String[] amt;
	private String[] payedAmt;
	private String[] payAmt;
	private String[] plusAmt;                
	private String[] payDate;    
	private String[] remarks;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSheetId() {
		return sheetId;
	}
	public void setSheetId(String sheetId) {
		this.sheetId = sheetId;
	}
	public String getMakerId() {
		return makerId;
	}
	public void setMakerId(String makerId) {
		this.makerId = makerId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getSheetState() {
		return sheetState;
	}
	public void setSheetState(String sheetState) {
		this.sheetState = sheetState;
	}
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSumAmt() {
		return sumAmt;
	}
	public void setSumAmt(String sumAmt) {
		this.sumAmt = sumAmt;
	}
	public String getSumOverAmt() {
		return sumOverAmt;
	}
	public void setSumOverAmt(String sumOverAmt) {
		this.sumOverAmt = sumOverAmt;
	}
	public String getSumPayAmt() {
		return sumPayAmt;
	}
	public void setSumPayAmt(String sumPayAmt) {
		this.sumPayAmt = sumPayAmt;
	}
	public String getSumPlusAmt() {
		return sumPlusAmt;
	}
	public void setSumPlusAmt(String sumPlusAmt) {
		this.sumPlusAmt = sumPlusAmt;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getCheckCode() {
		return checkCode;
	}
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	public String getAuditId() {
		return auditId;
	}
	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}
	public Date getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	public String[] getDid() {
		return did;
	}
	public void setDid(String[] did) {
		this.did = did;
	}
	public String[] getAmt() {
		return amt;
	}
	public void setAmt(String[] amt) {
		this.amt = amt;
	}
	public String[] getPayedAmt() {
		return payedAmt;
	}
	public void setPayedAmt(String[] payedAmt) {
		this.payedAmt = payedAmt;
	}
	public String[] getPayAmt() {
		return payAmt;
	}
	public void setPayAmt(String[] payAmt) {
		this.payAmt = payAmt;
	}
	public String[] getPlusAmt() {
		return plusAmt;
	}
	public void setPlusAmt(String[] plusAmt) {
		this.plusAmt = plusAmt;
	}
	public String[] getPayDate() {
		return payDate;
	}
	public void setPayDate(String[] payDate) {
		this.payDate = payDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String[] getRemarks() {
		return remarks;
	}
	public void setRemarks(String[] remarks) {
		this.remarks = remarks;
	}
	public String getProviderid() {
		return providerid;
	}
	public void setProviderid(String providerid) {
		this.providerid = providerid;
	}
	public String[] getDetailid() {
		return detailid;
	}
	public void setDetailid(String[] detailid) {
		this.detailid = detailid;
	}
	public String[] getArrid() {
		return arrid;
	}
	public void setArrid(String[] arrid) {
		this.arrid = arrid;
	}
	
}
