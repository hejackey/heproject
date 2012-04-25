package com.zhuozhuo.mis.po;

import java.io.Serializable;

public class PProviderType implements Serializable{	
	private static final long serialVersionUID = 2041142179442206750L;
	private String recordId;//记录序号
	private String parentId;//父结点ID
	private String parentName;//父节点名称
	private String providerTypeCode;//供应商类别编号
	private String providerytpeName;//供应商类别名称
	private String remark;//备注
	private String define1;//自定义字段

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getProviderTypeCode() {
		return providerTypeCode;
	}

	public void setProviderTypeCode(String providerTypeCode) {
		this.providerTypeCode = providerTypeCode;
	}

	public String getProviderytpeName() {
		return providerytpeName;
	}

	public void setProviderytpeName(String providerytpeName) {
		this.providerytpeName = providerytpeName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDefine1() {
		return define1;
	}

	public void setDefine1(String define1) {
		this.define1 = define1;
	}
}
