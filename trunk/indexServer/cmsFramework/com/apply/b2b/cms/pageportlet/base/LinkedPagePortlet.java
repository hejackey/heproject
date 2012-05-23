package com.apply.b2b.cms.pageportlet.base;

import com.apply.b2b.cms.base.IProject;
import com.apply.b2b.cms.util.FileManager;

/**
 * 
 * Linked 类型的Pageportlet
 * @author luoweifeng
 * 
 */

public abstract class LinkedPagePortlet extends FileVelocityPagePortlet
		implements Ilinked {
	
	public final static String LINEDTYPE_APACHE_SSI = "APACHE_SSI";
	
	@Override
	protected String doShow() {
			if (getLinkedType().equals(LINEDTYPE_APACHE_SSI)) {
					if (this.getLinkedFileName() != null
							&& !this.getLinkedFileName().trim().equals("")) {
						return " <!--#include virtual=\""
								+ this.getLinkedFileName() + "\" -->";
					} else {
						return "";
					}
			} else {
				return "";
			}
	}
	
	public void delete() {
	}
	
	public void init() {
		FileManager fileM = new FileManager();
		if (!fileM.fileExists(this.getFileName())) {
			this.doLinkedSourceFile();	
		}
	}
	
	public boolean doLinkedSourceFile() {
		return this.writeFile();
	}
	
	protected String getLinkedPagePortletSavePath() {
		return "pageportlet/linked/" + this.getName() + ".html";
	}
	
	public String getLinkedFileName() {
		IProject myProject = this.getOwnerProject();
		if (myProject != null) {
			FileManager fileM = new FileManager();
			if (fileM.fileExists(this.getFileName())) {
				String baseUrl = myProject.getProjectBaseUrl();
				if (baseUrl == null) {
					baseUrl = "";
				}
				
				if (baseUrl.trim().endsWith("/")) {
					return baseUrl.trim() + getLinkedPagePortletSavePath();
				} else {
					return baseUrl.trim() + "/"
							+ getLinkedPagePortletSavePath();
				}
			} else {
				return "";
			}
		} else {
			return "";
		}
	}
	
	public String getFileName() {
		IProject myProject = this.getOwnerProject();
		if (myProject != null) {
			String basePath = myProject.getProjectBasePath();
			if (basePath == null) {
				basePath = "";
			}
			
			if (basePath.trim().endsWith("/")) {
				return basePath.trim() + getLinkedPagePortletSavePath();
			} else {
				return basePath.trim() + "/"
						+ getLinkedPagePortletSavePath();
			}
		} else {
			return "";
		}
	}
	
	public String getLinkedType() {
		return "APACHE_SSI";
	}
}