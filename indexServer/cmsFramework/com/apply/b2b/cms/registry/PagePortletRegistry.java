package com.apply.b2b.cms.registry;

import com.apply.b2b.cms.pageportlet.base.BasePagePortlet;

/**
 * 
 * @author luoweifeng
 * 
 */

public class PagePortletRegistry {
	
	private static ThreadLocal<PagePortletServiceFactory> threadLocalVal = new ThreadLocal<PagePortletServiceFactory>() {
		protected synchronized PagePortletServiceFactory initialValue() {
			return new PagePortletServiceFactory();
		}
	};
	
	private PagePortletRegistry(){	
	}
	
	/**
	 * 
	 * @param projectName    the owner project
	 * @param portletName    the portlet name
	 * @return
	 */
	
	public static BasePagePortlet getPagePortlet(String projectName, String portletName) {
		if (projectName != null && projectName.trim().length() > 0 && portletName != null && portletName.trim().length() > 0) {
			PagePortletServiceFactory pagePortletFactory = threadLocalVal.get();
			
			if (pagePortletFactory != null) {
				return pagePortletFactory.getServiceObject(projectName, portletName);
			} else {
				return null;
			}
			
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 * @param portletName  the pagePortlet name
	 * @return
	 */
	public static BasePagePortlet getPagePortlet(String portletName) {
		if (portletName != null && portletName.trim().length() > 0) {
			PagePortletServiceFactory pagePortletFactory = threadLocalVal.get();
			if (pagePortletFactory != null) {
				return pagePortletFactory.getServiceObject(portletName);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
}