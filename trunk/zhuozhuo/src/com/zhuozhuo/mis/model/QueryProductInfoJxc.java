package com.zhuozhuo.mis.model;

import java.util.List;

import com.zhuozhuo.mis.po.PProductInfoJxc;
import com.zhuozhuo.mis.util.BaseModel;

/**
 * 用于输入综合查询条件的，用于接收用户输入的条件
 * 
 * @author Administrator
 * 
 */
public class QueryProductInfoJxc extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8901516360247284441L;
	private String productName;// 商品名称
	private String productCode;// 商品编码
	private String createBy;// 添加人员
	private String productType;// 商品类型
	private String productTypeName;//商品类别名称
	private List<PProductInfoJxc> productList;
	private String seq;
	private String querytype;
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public List<PProductInfoJxc> getProductList() {
		return productList;
	}

	public void setProductList(List<PProductInfoJxc> productList) {
		this.productList = productList;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getQuerytype() {
		return querytype;
	}

	public void setQuerytype(String querytype) {
		this.querytype = querytype;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
}
