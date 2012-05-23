package com.apply.b2b.cms.pageportlet.base;

import com.apply.b2b.cms.data.base.PortletMixedData;
/**
 * 
 * @author luoweifeng
 *
 */

public interface IPagePortlet {
	public void init();
	public void delete();
	public String show();
	public String getName();
	public boolean isShow();
	public String getDesc();
	public String getId();
	
	public PortletMixedData getTitle();
	public String getData();
}