package com.zhuozhuo.mis.po;

import java.util.List;

import com.zhuozhuo.mis.util.BaseModel;

public class TAdmDept extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2610527947668921034L;
	private String id;
	private String parentid;
	private String parentname;
	private String departmentcode;
	private String departmentname;
	private Integer levels;
	private Integer ifuse;
	private Integer isleaf;
	private String remark;
	private List<TAdmDept> listDept;
	private List<TAdmDept> deptTreeList;
	private String[] deptid;
	private String q_id;
	private String q_parentid;
	private String q_depcode;
	private String q_depname;
	private Integer q_ifuse;
	private Integer hifuse;
	
	public String getQ_depcode() {
		return q_depcode;
	}
	public void setQ_depcode(String q_depcode) {
		this.q_depcode = q_depcode;
	}
	public String getQ_depname() {
		return q_depname;
	}
	public void setQ_depname(String q_depname) {
		this.q_depname = q_depname;
	}
	public Integer getQ_ifuse() {
		return q_ifuse;
	}
	public void setQ_ifuse(Integer q_ifuse) {
		this.q_ifuse = q_ifuse;
	}
	public String getQ_id() {
		return q_id;
	}
	public void setQ_id(String q_id) {
		this.q_id = q_id;
	}
	public String getQ_parentid() {
		return q_parentid;
	}
	public void setQ_parentid(String q_parentid) {
		this.q_parentid = q_parentid;
	}
	
	public String[] getDeptid() {
		return deptid;
	}
	public void setDeptid(String[] deptid) {
		this.deptid = deptid;
	}
	public List<TAdmDept> getDeptTreeList() {
		return deptTreeList;
	}
	public void setDeptTreeList(List<TAdmDept> deptTreeList) {
		this.deptTreeList = deptTreeList;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getParentname() {
		return parentname;
	}
	public void setParentname(String parentname) {
		this.parentname = parentname;
	}
	public String getDepartmentcode() {
		return departmentcode;
	}
	public void setDepartmentcode(String departmentcode) {
		this.departmentcode = departmentcode;
	}
	public String getDepartmentname() {
		return departmentname;
	}
	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}
	public Integer getLevels() {
		return levels;
	}
	public void setLevels(Integer levels) {
		this.levels = levels;
	}
	public Integer getIfuse() {
		return ifuse;
	}
	public void setIfuse(Integer ifuse) {
		this.ifuse = ifuse;
	}
	public Integer getIsleaf() {
		return isleaf;
	}
	public void setIsleaf(Integer isleaf) {
		this.isleaf = isleaf;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<TAdmDept> getListDept() {
		return listDept;
	}
	public void setListDept(List<TAdmDept> listDept) {
		this.listDept = listDept;
	}
	public Integer getHifuse() {
		return hifuse;
	}
	public void setHifuse(Integer hifuse) {
		this.hifuse = hifuse;
	}
	
}
