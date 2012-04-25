package com.zhuozhuo.mis.model;
/**
 * 用于查询商品分类的vo
 * @author Administrator
 *
 */
public class QueryProductType {
	private String parentName;
	private String goodsTypeCode;
	private String goodsTypeName;
	private String  classLevel;
	
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getGoodsTypeCode() {
		return goodsTypeCode;
	}
	public void setGoodsTypeCode(String goodsTypeCode) {
		this.goodsTypeCode = goodsTypeCode;
	}
	public String getGoodsTypeName() {
		return goodsTypeName;
	}
	public void setGoodsTypeName(String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}
	public String getClassLevel() {
		return classLevel;
	}
	public void setClassLevel(String classLevel) {
		this.classLevel = classLevel;
	}
}
