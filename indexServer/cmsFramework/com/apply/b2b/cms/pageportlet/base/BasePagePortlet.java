package com.apply.b2b.cms.pageportlet.base;

import org.apache.log4j.Logger;
import com.apply.b2b.cms.base.IOwner;
import com.apply.b2b.cms.base.IPage;
import com.apply.b2b.cms.base.IProject;
import com.apply.b2b.cms.project.ProjectManager;

/**
 * 
 * 基础PagePortlet 实现参考
 * @author luoweifeng
 * 
 */

public abstract class BasePagePortlet implements IPagePortlet, IOwner {
	protected final Logger log = Logger.getLogger(this.getClass());
	private IProject ownerProject = null;
    private IPage containerPage = null;
	
	public BasePagePortlet() {
		super();
	}
	
	public IPage getRunPage(){
		return this.containerPage;
	}
	
	public void setRunPage(IPage runPage){
		this.containerPage = runPage;
	}
	
	public IProject getOwnerProject() {
		if (ownerProject == null) {
			ownerProject = ProjectManager.defaultProject();
		}
		return ownerProject;
	}

	public void setOwnerProject(IProject ownerProject) {
		this.ownerProject = ownerProject;
	}

	public String getName() {
		return this.getClass().getName();
	}

	public String getId() {
		return this.getName();
	}

	public boolean isShow() {
		return true;
	}

	public String getDesc() {
		return this.getName();
	}

	public String getPortletHttpUrl() {
		return null;
	}
}