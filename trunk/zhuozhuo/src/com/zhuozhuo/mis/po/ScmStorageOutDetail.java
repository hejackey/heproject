package com.zhuozhuo.mis.po;

import java.io.Serializable;

public class ScmStorageOutDetail implements Serializable {

	private static final long serialVersionUID = 8405733697306352644L;
	private Integer id;
	private String did;//明细表ID
	private Integer masterId;//主表ID
	private String productId;//产品ID
	private String productcode;
	private String productname;
	private String productspecs;
	private String productentry;
	private String qty;//数量
	private String factSellPrice;//实际价
	private String taxRat;//税率
	private String amt;//金额
	private String sheetIdOriginal;//单据来源
	private String remark;//
	private String barnid;
	
	
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
	public String getFactSellPrice() {
		return factSellPrice;
	}
	public void setFactSellPrice(String factSellPrice) {
		this.factSellPrice = factSellPrice;
	}
	public String getTaxRat() {
		return taxRat;
	}
	public void setTaxRat(String taxRat) {
		this.taxRat = taxRat;
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
	public Integer getMasterId() {
		return masterId;
	}
	public void setMasterId(Integer masterId) {
		this.masterId = masterId;
	}
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
	public String getBarnid() {
		return barnid;
	}
	public void setBarnid(String barnid) {
		this.barnid = barnid;
	}
	public String getProductcode() {
		return productcode;
	}
	public void setProductcode(String productcode) {
		this.productcode = productcode;
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
}
