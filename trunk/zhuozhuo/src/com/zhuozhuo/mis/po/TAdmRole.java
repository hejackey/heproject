package com.zhuozhuo.mis.po;

import java.util.List;

import com.zhuozhuo.mis.util.BaseModel;

public class TAdmRole extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1051931397250774821L;
	private String id;
	private String roleName;
	private String roleDesc;
	private int status;
	private String createBy;
	private String lastUpdateBy;
	private List<TAdmRole> roleList;
	private String[] roleid;
	private List<TAdmModule> moduleList;
	private List<String> rolePrivList;
	private String[] moduleid;
	private String rolePril;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getLastUpdateBy() {
		return lastUpdateBy;
	}
	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}
	public List<TAdmRole> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<TAdmRole> roleList) {
		this.roleList = roleList;
	}
	public String[] getRoleid() {
		return roleid;
	}
	public void setRoleid(String[] roleid) {
		this.roleid = roleid;
	}
	
	public String[] getModuleid() {
		return moduleid;
	}
	public void setModuleid(String[] moduleid) {
		this.moduleid = moduleid;
	}
	public String getRolePril() {
		return rolePril;
	}
	public void setRolePril(String rolePril) {
		this.rolePril = rolePril;
	}
	
	public List<String> getRolePrivList() {
		return rolePrivList;
	}
	public void setRolePrivList(List<String> rolePrivList) {
		this.rolePrivList = rolePrivList;
	}
	public List<TAdmModule> getModuleList() {
		return moduleList;
	}
	public void setModuleList(List<TAdmModule> moduleList) {
		this.moduleList = moduleList;
	}
	
}
