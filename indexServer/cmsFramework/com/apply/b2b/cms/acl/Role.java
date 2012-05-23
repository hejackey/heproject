package com.apply.b2b.cms.acl;

public class Role implements IRole {
	private final String roleID;
	private final IRole  parentRole;
	
	public Role(String roleIDv, IRole pRole) {
		this.roleID = roleIDv;
		this.parentRole = pRole;
	}
	
	public String getRoleID() {
		return this.roleID;
	}
	
	public IRole getParent() {
		return this.parentRole;
	}
}