package com.zhuozhuo.mis.po;

import java.io.Serializable;

public class ScmStorageInAndDetail implements Serializable{
	
	private static final long serialVersionUID = -8106832584951622041L;
	private int  id;
	private String  sheetId;//单据序号
	private String  makerId;//制单人ID
	private String  createDate;//建立日期
	private String  sheetState;//单据状态
	private String  providerid;//供应商ID
	private String  departmentId;//部门ID
	private String  userid;//用户ID
	private String  sumQty;//总数量
	private String  sumAmt;//总金额
	private String  storageInType;//入库类别
	private String  bargainCode;//合同代码
	private String  payDate;//付款日期
	private String  barnId;//仓库代码
	private String  transModeCode;//运输模式代码
	private String  toDate;//交贷日期
	private String  toAddress;//交货地址
	private String  auditId;//
	private String  auditDate;//审核日期
	private String  srcSheetid;//来源单号
	private String  remark;//备注
	
	private String[]  did;//序号	
	private String[] productId;//产品ID
	private String[] qty;//数量
	private String[] factInprrice;//实际价格
	private String[] taxrate;//税率,后加的
	private String[] amt;//金额
	private String[] sheetIdOriginal;//来源单号
	private String[] factSellPrice;//后加的
	
	
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
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getSheetState() {
		return sheetState;
	}
	public void setSheetState(String sheetState) {
		this.sheetState = sheetState;
	}
	
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	
	public String getSumQty() {
		return sumQty;
	}
	public void setSumQty(String sumQty) {
		this.sumQty = sumQty;
	}
	public String getSumAmt() {
		return sumAmt;
	}
	public void setSumAmt(String sumAmt) {
		this.sumAmt = sumAmt;
	}
	public String getStorageInType() {
		return storageInType;
	}
	public void setStorageInType(String storageInType) {
		this.storageInType = storageInType;
	}
	public String getBargainCode() {
		return bargainCode;
	}
	public void setBargainCode(String bargainCode) {
		this.bargainCode = bargainCode;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getBarnId() {
		return barnId;
	}
	public void setBarnId(String barnId) {
		this.barnId = barnId;
	}
	public String getTransModeCode() {
		return transModeCode;
	}
	public void setTransModeCode(String transModeCode) {
		this.transModeCode = transModeCode;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public String getAuditId() {
		return auditId;
	}
	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}
	public String getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}
	public String getSrcSheetid() {
		return srcSheetid;
	}
	public void setSrcSheetid(String srcSheetid) {
		this.srcSheetid = srcSheetid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String[] getDid() {
		return did;
	}
	public void setDid(String[] did) {
		this.did = did;
	}
	public String[] getProductId() {
		return productId;
	}
	public void setProductId(String[] productId) {
		this.productId = productId;
	}
	public String[] getQty() {
		return qty;
	}
	public void setQty(String[] qty) {
		this.qty = qty;
	}
	public String[] getFactInprrice() {
		return factInprrice;
	}
	public void setFactInprrice(String[] factInprrice) {
		this.factInprrice = factInprrice;
	}
	
	public String[] getAmt() {
		return amt;
	}
	public void setAmt(String[] amt) {
		this.amt = amt;
	}
	public String[] getSheetIdOriginal() {
		return sheetIdOriginal;
	}
	public void setSheetIdOriginal(String[] sheetIdOriginal) {
		this.sheetIdOriginal = sheetIdOriginal;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String[] getTaxrate() {
		return taxrate;
	}
	public void setTaxrate(String[] taxrate) {
		this.taxrate = taxrate;
	}
	public String[] getFactSellPrice() {
		return factSellPrice;
	}
	public void setFactSellPrice(String[] factSellPrice) {
		this.factSellPrice = factSellPrice;
	}
	public String getProviderid() {
		return providerid;
	}
	public void setProviderid(String providerid) {
		this.providerid = providerid;
	}
}
