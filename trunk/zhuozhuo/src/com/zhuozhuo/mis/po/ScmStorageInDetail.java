package com.zhuozhuo.mis.po;

import java.io.Serializable;

public class ScmStorageInDetail implements Serializable{
	
	private static final long serialVersionUID = 1878745031730609102L;
	private int id;//
	private String  did;//序号
	private String masterId;//主表ID
	private String productId;//产品ID
	private String qty;//数量
	private String factInprrice;//实际价格
	private String taxrate;//税率
	private String amt;//金额
	private String sheetIdOriginal;//来源单号
	private String remark;//备注
	private String barnid;//仓库ID
	private String productname;
	private String productspecs;
	private String productentry;
	private String productcode;
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getFactInprrice() {
		return factInprrice;
	}
	public void setFactInprrice(String factInprrice) {
		this.factInprrice = factInprrice;
	}
	
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
	public String getSheetIdOriginal() {
		return sheetIdOriginal;
	}
	public void setSheetIdOriginal(String sheetIdOriginal) {
		this.sheetIdOriginal = sheetIdOriginal;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getBarnid() {
		return barnid;
	}
	public void setBarnid(String barnid) {
		this.barnid = barnid;
	}
	public String getTaxrate() {
		return taxrate;
	}
	public void setTaxrate(String taxrate) {
		this.taxrate = taxrate;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getProductspecs() {
		return productspecs;
	}
	public void setProductspecs(String productspecs) {
		this.productspecs = productspecs;
	}
	public String getProductentry() {
		return productentry;
	}
	public void setProductentry(String productentry) {
		this.productentry = productentry;
	}
	public String getProductcode() {
		return productcode;
	}
	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}
}
