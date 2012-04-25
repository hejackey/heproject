package com.zhuozhuo.mis.po;

import com.zhuozhuo.mis.util.BaseModel;

public class ProductInfo extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8107475214266366762L;
	private String productid;
	private String productCode;
	private String productname;
	private String productentry;
	private String productspecs;
	
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getProductentry() {
		return productentry;
	}
	public void setProductentry(String productentry) {
		this.productentry = productentry;
	}
	public String getProductspecs() {
		return productspecs;
	}
	public void setProductspecs(String productspecs) {
		this.productspecs = productspecs;
	}
	
	
}
