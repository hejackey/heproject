package com.zhuozhuo.mis.po;

import java.io.Serializable;
import java.util.Date;

public class ScmStorageOut implements Serializable {	
	private static final long serialVersionUID = -4262595650677950658L;
	private Integer id;//记录序号
	//private Integer erpId;//没有用
	private String sheetId;//单据序号
	private String makerId;//制造商编号
	private Date createTime;//建立日期
	private Integer sheetState;//单据状态
	private String clientId;//客户代码
	private String departmentId;//部门代码
	private String userId;//用户代码
	private Integer sumQty;//总数量
	private Integer sumAmt;//总金额
	private String storageOutType;//出库类型
	private String bargainCode;//合同代码
	private String gatherDate;//收款日期
	private String barnId;//仓库代码
	private String barnAddress;//仓库地址
	private String barnPhone;//仓库电话
	private String transModeCode;//运输模式
	private String gatherName;//
	private String telephone;//联系电话
	private String toDate;//交货日期
	private String toAddress;//交货地址
	private String auditId;//审核ID
	private Date auditDate;//审核日期
	private String srcSheetId;//来源单号
	private String remark;//
	private String qualiterid;//质检人id
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
//	public Integer getErpId() {
//		return erpId;
//	}
//	public void setErpId(Integer erpId) {
//		this.erpId = erpId;
//	}
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getSheetState() {
		return sheetState;
	}
	public void setSheetState(Integer sheetState) {
		this.sheetState = sheetState;
	}
	
	public Integer getSumQty() {
		return sumQty;
	}
	public void setSumQty(Integer sumQty) {
		this.sumQty = sumQty;
	}
	public Integer getSumAmt() {
		return sumAmt;
	}
	public void setSumAmt(Integer sumAmt) {
		this.sumAmt = sumAmt;
	}
	public String getStorageOutType() {
		return storageOutType;
	}
	public void setStorageOutType(String storageOutType) {
		this.storageOutType = storageOutType;
	}
	public String getBargainCode() {
		return bargainCode;
	}
	public void setBargainCode(String bargainCode) {
		this.bargainCode = bargainCode;
	}
	public String getGatherDate() {
		return gatherDate;
	}
	public void setGatherDate(String gatherDate) {
		this.gatherDate = gatherDate;
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
	public String getGatherName() {
		return gatherName;
	}
	public void setGatherName(String gatherName) {
		this.gatherName = gatherName;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
	public Date getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	public String getSrcSheetId() {
		return srcSheetId;
	}
	public void setSrcSheetId(String srcSheetId) {
		this.srcSheetId = srcSheetId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getQualiterid() {
		return qualiterid;
	}
	public void setQualiterid(String qualiterid) {
		this.qualiterid = qualiterid;
	}
	
	public String getBarnPhone() {
		return barnPhone;
	}
	public void setBarnPhone(String barnPhone) {
		this.barnPhone = barnPhone;
	}
	public String getBarnAddress() {
		return barnAddress;
	}
	public void setBarnAddress(String barnAddress) {
		this.barnAddress = barnAddress;
	}
}
