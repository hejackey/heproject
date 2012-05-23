package com.apply.b2b.cms.acl;

/**
 * 
 * @author luoweifeng
 *
 */

public class Resource implements IResource {
	private final String resourceID;
	private final IResource resourceParent;
	
	public Resource(String resourceIDV, IResource parentR){
		this.resourceID = resourceIDV;
		this.resourceParent = parentR;
	}
	
	public String getResourceID(){
		return this.resourceID;
	}
	
	public com.apply.b2b.cms.acl.IResource getParent() {
		return this.resourceParent;
	}
}
