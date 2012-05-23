package com.apply.b2b.cms.project;

import com.apply.b2b.cms.base.IProject;
import com.apply.b2b.cms.config.ConfigBuilder;
import com.apply.b2b.cms.config.ConfigBuilderFactory;

/**
 * 
 * @author luoweifeng
 * 
 */

public class ProjectFactory {
	public static IProject defaultProject() {
		ConfigBuilder propertyBuilder = ConfigBuilderFactory
		.getConfigBuilder();
		
		String sysBasePath = propertyBuilder
				.getProperty("cms.system.base.path");
		
		String sysDomain = propertyBuilder.getProperty(
				"cms.system.domain");
		
		if (sysBasePath != null && sysBasePath.trim().length() > 0) {
			return new BaseProject(sysBasePath, "/", null, sysDomain, "default");
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	
	public static IProject newProject(String name) {
		if (name != null && name.trim().length() > 0) {
			ConfigBuilder propertyBuilder = ConfigBuilderFactory
					.getConfigBuilder();
			
			String projectBasePath = propertyBuilder.getProperty("cms.project."
					+ name + ".base.path");
			
			if (projectBasePath != null && projectBasePath.trim().length() > 0) {
				
				String projectBaseUrl = propertyBuilder
						.getProperty("cms.project." + name + ".base.url");
				
				if (projectBaseUrl != null
						&& projectBaseUrl.trim().length() > 0) {
					projectBaseUrl = projectBaseUrl.trim();
				} else {
					projectBaseUrl = "/";
				}
				
				String projectDomain = propertyBuilder
						.getProperty("cms.project." + name + ".domain");
				
				String projectDesc = propertyBuilder.getProperty("cms.project."
						+ name + ".desc");
				
				return new BaseProject(projectBasePath, projectBaseUrl,
						projectDesc, projectDomain, name);
				
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
}