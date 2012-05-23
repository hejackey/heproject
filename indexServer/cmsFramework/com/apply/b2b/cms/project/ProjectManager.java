package com.apply.b2b.cms.project;

import com.apply.b2b.cms.base.IProject;
import com.apply.b2b.cms.cache.BaseModifyTimeCachePool;
import com.apply.b2b.cms.cache.ICacheEntry;
import com.apply.b2b.cms.config.ConfigBuilderFactory;

/**
 * 
 * @author luoweifeng
 * 
 */

public class ProjectManager {
	private static String BASE_POJECT_NAME = "_cms_project";
	
	private static String getCacheProjectName(String name) {
		return BASE_POJECT_NAME + "_" + name;
	}
	
	public static IProject getProject(String pName) {
		if (pName != null && pName.trim().length() > 0) {
			
			long lmTime = ConfigBuilderFactory.getConfigFileLMTime();
			String cacheProjectName = getCacheProjectName(pName);
			
			ICacheEntry cacheEntry = BaseModifyTimeCachePool.get(
					cacheProjectName, lmTime);
			
			if (cacheEntry != null) {
				Object aCeObject = cacheEntry.getCeObject();
				if (aCeObject != null) {
					return (IProject) aCeObject;
				} else {
					IProject defaultProject = ProjectFactory.newProject(pName);
					if (defaultProject != null) {
						BaseModifyTimeCachePool.put(cacheProjectName,
								defaultProject, lmTime);
						return defaultProject;
					} else {
						return null;
					}
				}
			} else {
				IProject defaultProject = ProjectFactory.newProject(pName);
				if (defaultProject != null) {
					BaseModifyTimeCachePool.put(cacheProjectName,
							defaultProject, lmTime);
					return defaultProject;
				} else {
					return null;
				}
			}
		} else {
			return null;
		}
	}
	
	public static IProject defaultProject() {

		long lmTime = ConfigBuilderFactory.getConfigFileLMTime();
		String cacheProjectName = getCacheProjectName("default");
		
		ICacheEntry cacheEntry = BaseModifyTimeCachePool.get(cacheProjectName,
				lmTime);
		
		if (cacheEntry != null) {
			Object aCeObject = cacheEntry.getCeObject();
			if (aCeObject != null) {
				return (IProject) aCeObject;
			} else {
				IProject defaultProject = ProjectFactory.defaultProject();
				if (defaultProject != null) {
					BaseModifyTimeCachePool.put(cacheProjectName,
							defaultProject, lmTime);
					return defaultProject;
				} else {
					return null;
				}
			}
		} else {
			IProject defaultProject = ProjectFactory.defaultProject();
			if (defaultProject != null) {
				BaseModifyTimeCachePool.put(cacheProjectName, defaultProject,
						lmTime);
				return defaultProject;
			} else {
				return null;
			}
		}	
	}
}