package com.zhuozhuo.mis.po;

import java.util.List;

import com.zhuozhuo.mis.util.BaseModel;

public class TAdmUser extends BaseModel{	
	private static final long serialVersionUID = 5957939577958193933L;
	private String id;
	private String name;
	private String email;
	private String telephone;
	private TAdmRole role = new TAdmRole();
	private String roleName;
	private int status;
	private TAdmDept dept = new TAdmDept();
	private String deptName;
	private String loginName;
	private String password;
	private String hpasswd;
	private String expireDate;
	private int allowDel;
	private int isAdminUser;
	private String bname;
	private String createBy;
	private String lastUpdateBy;
	private int sex;
	private String address;
	private String handtel;
	private String remark;
	private List<TAdmDept> deptList;
	private List<TAdmRole> roleList;
	private List<TAdmUser> userList;
	private int pwdIsChange;
	private String[] userid;
	private String checkCode;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public TAdmDept getDept() {
		return dept;
	}
	public void setDept(TAdmDept dept) {
		this.dept = dept;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public TAdmRole getRole() {
		return role;
	}
	public void setRole(TAdmRole role) {
		this.role = role;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public int getAllowDel() {
		return allowDel;
	}
	public void setAllowDel(int allowDel) {
		this.allowDel = allowDel;
	}
	public int getIsAdminUser() {
		return isAdminUser;
	}
	public void setIsAdminUser(int isAdminUser) {
		this.isAdminUser = isAdminUser;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
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
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHandtel() {
		return handtel;
	}
	public void setHandtel(String handtel) {
		this.handtel = handtel;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<TAdmDept> getDeptList() {
		return deptList;
	}
	public void setDeptList(List<TAdmDept> deptList) {
		this.deptList = deptList;
	}
	public List<TAdmRole> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<TAdmRole> roleList) {
		this.roleList = roleList;
	}
	public List<TAdmUser> getUserList() {
		return userList;
	}
	public void setUserList(List<TAdmUser> userList) {
		this.userList = userList;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getHpasswd() {
		return hpasswd;
	}
	public void setHpasswd(String hpasswd) {
		this.hpasswd = hpasswd;
	}
	public int getPwdIsChange() {
		return pwdIsChange;
	}
	public void setPwdIsChange(int pwdIsChange) {
		this.pwdIsChange = pwdIsChange;
	}
	public String[] getUserid() {
		return userid;
	}
	public void setUserid(String[] userid) {
		this.userid = userid;
	}
	public String getCheckCode() {
		return checkCode;
	}
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
		
}
