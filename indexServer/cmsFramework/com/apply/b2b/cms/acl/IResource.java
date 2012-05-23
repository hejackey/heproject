package com.apply.b2b.cms.acl;

public interface IResource {
	public String getResourceID();
	public IResource getParent();
}