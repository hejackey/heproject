package com.zhuozhuo.mis.po;

import com.zhuozhuo.mis.util.BaseModel;

public class ScmDiaoBoDetail extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4485779373897017030L;
	private String id;
	private int did;
	private String sheetid;
	private String[] aproductid;
	private String productid;
	private String productname;
	private String productcode;
	private String producttype;
	private String productTypeName;
	private String productunit;	
	private ProductInfo product;
	private String[] aqty;
	private String qty;
	private String[] afactinprice;
	private String factinprice;
	private String[] ataxrate;
	private String taxrate;
	private String[] aamt;
	private String amt;
	private String sheetidoriginal;
	private String[] aremark;
	private String remark;
	private String productspecs;
	private String masterid;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getSheetid() {
		return sheetid;
	}
	public void setSheetid(String sheetid) {
		this.sheetid = sheetid;
	}
	
	public ProductInfo getProduct() {
		return product;
	}
	public void setProduct(ProductInfo product) {
		this.product = product;
	}
	
	public String getSheetidoriginal() {
		return sheetidoriginal;
	}
	public void setSheetidoriginal(String sheetidoriginal) {
		this.sheetidoriginal = sheetidoriginal;
	}
	
	public String[] getAproductid() {
		return aproductid;
	}
	public void setAproductid(String[] aproductid) {
		this.aproductid = aproductid;
	}
	public String[] getAqty() {
		return aqty;
	}
	public void setAqty(String[] aqty) {
		this.aqty = aqty;
	}
	public String[] getAfactinprice() {
		return afactinprice;
	}
	public void setAfactinprice(String[] afactinprice) {
		this.afactinprice = afactinprice;
	}
	public String[] getAtaxrate() {
		return ataxrate;
	}
	public void setAtaxrate(String[] ataxrate) {
		this.ataxrate = ataxrate;
	}
	public String[] getAamt() {
		return aamt;
	}
	public void setAamt(String[] aamt) {
		this.aamt = aamt;
	}
	public String[] getAremark() {
		return aremark;
	}
	public void setAremark(String[] aremark) {
		this.aremark = aremark;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public void setFactinprice(String factinprice) {
		this.factinprice = factinprice;
	}
	public void setTaxrate(String taxrate) {
		this.taxrate = taxrate;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getProductid() {
		return productid;
	}
	public String getQty() {
		return qty;
	}
	public String getFactinprice() {
		return factinprice;
	}
	public String getTaxrate() {
		return taxrate;
	}
	public String getAmt() {
		return amt;
	}
	public String getRemark() {
		return remark;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getProducttype() {
		return producttype;
	}
	public void setProducttype(String producttype) {
		this.producttype = producttype;
	}
	public String getProductunit() {
		return productunit;
	}
	public void setProductunit(String productunit) {
		this.productunit = productunit;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	public String getProductcode() {
		return productcode;
	}
	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}
	public String getProductspecs() {
		return productspecs;
	}
	public void setProductspecs(String productspecs) {
		this.productspecs = productspecs;
	}
	public String getMasterid() {
		return masterid;
	}
	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}	
}
