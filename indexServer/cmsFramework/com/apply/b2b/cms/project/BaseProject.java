package com.apply.b2b.cms.project;

import com.apply.b2b.cms.base.IProject;

/**
 * 
 * @author luoweifeng
 *
 */

public class BaseProject implements IProject {
	private final String projectBasePath;
	private final String projectBaseUrl;
	private final String projectDesc;
	private final String projectDomain;
	private final String ProjectName;
	
	public BaseProject(String projectBasePath, String projectBaseUrl,
			String projectDesc, String projectDomain, String projectName) {
		super();
		this.projectBasePath = projectBasePath;
		this.projectBaseUrl = projectBaseUrl;
		this.projectDesc = projectDesc;
		this.projectDomain = projectDomain;
		ProjectName = projectName;
	}
	
	public String getProjectBasePath() {
		return projectBasePath;
	}
	
	public String getProjectBaseUrl() {
		return projectBaseUrl;
	}
	
	public String getProjectDesc() {
		return projectDesc;
	}
	
	public String getProjectDomain() {
		return projectDomain;
	}
	
	public String getProjectName() {
		return ProjectName;
	}
}