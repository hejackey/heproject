package com.zhuozhuo.mis.model;

import java.util.Date;

public class ProductType {	
	
	private long recordId;
	private Long parentId;
	private String parentName;
	private String goodsTypeCode;
	private String goodsTypeName;
	private Date createTime;
	private int  numb;//显示顺序，直接添数字，越小排的越在前
	private int  classLevel;//第几级,直接添数值
	private int  ifUse=1;//是否可用（0代表不可用，1代表可用）
	private int  isLeaf=0;//是否为未级(0代表不是末级，1代表是末级)
	private String define1;
	private String remark;	
	
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
	public String getDefine1() {
		return define1;
	}
	public void setDefine1(String define1) {
		this.define1 = define1;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}	
	public long getRecordId() {
		return recordId;
	}
	public void setRecordId(long recordId) {
		this.recordId = recordId;
	}
	public int getNumb() {
		return numb;
	}
	public void setNumb(int numb) {
		this.numb = numb;
	}
	public int getClassLevel() {
		return classLevel;
	}
	public void setClassLevel(int classLevel) {
		this.classLevel = classLevel;
	}
	public int getIfUse() {
		return ifUse;
	}
	public void setIfUse(int ifUse) {
		this.ifUse = ifUse;
	}
	public int getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(int isLeaf) {
		this.isLeaf = isLeaf;
	}	
}
