package com.apply.b2b.cms.registry;

import com.apply.b2b.cms.base.IProject;
import com.apply.b2b.cms.config.ConfigBuilder;
import com.apply.b2b.cms.config.ConfigBuilderFactory;
import com.apply.b2b.cms.pageportlet.base.BasePagePortlet;
import com.apply.b2b.cms.project.ProjectManager;

/**
 * 
 * @author luoweifeng
 * 
 */

public class PagePortletServiceFactory extends
		AbstractServiceFactory<BasePagePortlet> {
	
	@Override
	protected BasePagePortlet newServiceInstance(String name) {
		if (name != null && name.trim().length() > 0) {
			
			String clazzName = getConfigPagePortletClassName(name);
			if (clazzName != null && clazzName.trim().length() > 0) {
				Object aInstance = null;
				
				try {
					aInstance = Thread.currentThread().getContextClassLoader()
							.loadClass(clazzName).newInstance();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				if (aInstance != null) {
					return (BasePagePortlet) aInstance;
				} else {
					return null;
				}
				
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	private String getConfigPagePortletClassName(String name) {
		if (name != null && name.trim().length() > 0) {
			ConfigBuilder propertyBuilder = ConfigBuilderFactory
					.getConfigBuilder();
			
			String pagePortletClazzName = propertyBuilder
					.getProperty("cms.pageportlet." + name + ".clazz");
			
			if (pagePortletClazzName != null
					&& pagePortletClazzName.trim().length() > 0) {
				return pagePortletClazzName;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	public BasePagePortlet getServiceObject(String portletName) {
		IProject ownerP = ProjectManager.defaultProject();
		if (ownerP != null) {
			BasePagePortlet aPortlet = super.getServiceObject(portletName);
			if (aPortlet != null) {
				aPortlet.setOwnerProject(ownerP);
			}
			return aPortlet;
		} else {
			return null;
		}
	}
	
	public BasePagePortlet getServiceObject(String ownerName, String portletName) {
		if (ownerName != null && ownerName.trim().length() > 0) {
			IProject ownerP = ProjectManager.getProject(ownerName);
			
			if (ownerP != null) {
				BasePagePortlet aPortlet = super.getServiceObject(ownerName
						+ "." + portletName);
				if (aPortlet != null) {
					aPortlet.setOwnerProject(ownerP);
				}
				return aPortlet;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
}