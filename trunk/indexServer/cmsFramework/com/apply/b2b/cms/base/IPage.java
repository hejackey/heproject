package com.apply.b2b.cms.base;

import java.util.List;

import com.apply.b2b.cms.common.WebPage;
import com.apply.b2b.cms.pageportlet.base.PagePortletMetaData;

/**
 *
 * @author luoweifeng
 *
 */

public interface IPage {
	public  WebPage getWebPage();
	public Object getPageBean();
	public List<PagePortletMetaData> getPageContaitPortlets();
}