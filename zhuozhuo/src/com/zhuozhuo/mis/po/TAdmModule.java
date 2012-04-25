package com.zhuozhuo.mis.po;

import java.util.List;

import com.zhuozhuo.mis.util.BaseModel;

public class TAdmModule extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1833562548575848836L;
	private String id;
	private int moduleType;
	private int forLog;
	private String url;
	private String moduleCode;
	private int dispnum;
	private String moduleName;
	private String remark;
	private String parentid;
	private String parentname;
	private List<TAdmModule> moduleList;
	private List<TAdmModule> moduleTreeList;
	private int ifuse;
	private String q_id;
	private String q_parentid;
	private String q_modulecode;
	private String q_modulename;
	private int q_moduletype;
	private int q_forlog;
	private int q_ifuse;
	private int hifuse;
	private String[] moduleid;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getModuleType() {
		return moduleType;
	}
	public void setModuleType(int moduleType) {
		this.moduleType = moduleType;
	}
	public int getForLog() {
		return forLog;
	}
	public void setForLog(int forLog) {
		this.forLog = forLog;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getModuleCode() {
		return moduleCode;
	}
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
	public int getDispnum() {
		return dispnum;
	}
	public void setDispnum(int dispnum) {
		this.dispnum = dispnum;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public List<TAdmModule> getModuleList() {
		return moduleList;
	}
	public void setModuleList(List<TAdmModule> moduleList) {
		this.moduleList = moduleList;
	}
	public List<TAdmModule> getModuleTreeList() {
		return moduleTreeList;
	}
	public void setModuleTreeList(List<TAdmModule> moduleTreeList) {
		this.moduleTreeList = moduleTreeList;
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
	public String getQ_modulecode() {
		return q_modulecode;
	}
	public void setQ_modulecode(String q_modulecode) {
		this.q_modulecode = q_modulecode;
	}
	public String getQ_modulename() {
		return q_modulename;
	}
	public void setQ_modulename(String q_modulename) {
		this.q_modulename = q_modulename;
	}
	public int getQ_moduletype() {
		return q_moduletype;
	}
	public void setQ_moduletype(int q_moduletype) {
		this.q_moduletype = q_moduletype;
	}
	public int getQ_forlog() {
		return q_forlog;
	}
	public void setQ_forlog(int q_forlog) {
		this.q_forlog = q_forlog;
	}
	public int getQ_ifuse() {
		return q_ifuse;
	}
	public void setQ_ifuse(int q_ifuse) {
		this.q_ifuse = q_ifuse;
	}
	public int getIfuse() {
		return ifuse;
	}
	public void setIfuse(int ifuse) {
		this.ifuse = ifuse;
	}
	public int getHifuse() {
		return hifuse;
	}
	public void setHifuse(int hifuse) {
		this.hifuse = hifuse;
	}
	public String[] getModuleid() {
		return moduleid;
	}
	public void setModuleid(String[] moduleid) {
		this.moduleid = moduleid;
	}
	
	
}
