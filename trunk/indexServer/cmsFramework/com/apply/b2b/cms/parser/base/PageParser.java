package com.apply.b2b.cms.parser.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.VelocityContext;

import com.apply.b2b.cms.base.FileVelocityParser;
import com.apply.b2b.cms.base.IPage;
import com.apply.b2b.cms.pageportlet.base.BasePagePortlet;
import com.apply.b2b.cms.pageportlet.base.PagePortletMetaData;
import com.apply.b2b.cms.registry.PagePortletRegistry;

/**
 * 
 * @author luoweifeng
 * 
 */

public abstract class PageParser extends FileVelocityParser implements IPage {
	private Object pageBean;
	
	public PageParser(Object aPagebean){
		this.pageBean = aPagebean;
	}
	
	public Object getPageBean() {
		return this.pageBean;
	}
	
	@Override
	protected void beforePutTemplateParseValue(VelocityContext context) {
		context.put("pageBean", getPageBean());
		context.put("webpage", this.getWebPage());
		putPageContaintPagePortlet(context);
		doBeforePutTemplateParseValue(context);
	}
	
	abstract public void doBeforePutTemplateParseValue(VelocityContext context);
	
	public BasePagePortlet getPagePortlet(String prjName, String portletName) {
		if (portletName != null && portletName.trim().length() > 0) {
			BasePagePortlet aObj = null;
			if (prjName != null && prjName.trim().length() > 0) {
				aObj = PagePortletRegistry.getPagePortlet(prjName, portletName);
				if (aObj != null) {
					aObj.setRunPage(this);
					aObj.init();
				}
			} else {
				aObj = PagePortletRegistry.getPagePortlet(portletName);
				if (aObj != null) {
					aObj.setRunPage(this);
					aObj.init();
				}
			}
			return aObj;
		} else {
			return null;
		}
	}
	
	protected void putPageContaintPagePortlet(VelocityContext context) {
		List<PagePortletMetaData> portletMetas = this.getPageContaitPortlets();
		if (portletMetas != null && portletMetas.size() > 0) {
			for (PagePortletMetaData portletMeta : portletMetas) {
				BasePagePortlet contentedPagePortlet = this.getPagePortlet(
						portletMeta.getPrjName(), portletMeta
								.getPagePortletName());
				
				if (contentedPagePortlet != null) {
					context.put(portletMeta.getPagePortletName(),
							contentedPagePortlet.show());
				}
			}
		}
	}
	
	public List<PagePortletMetaData> getPageContaitPortlets() {
		List<PagePortletMetaData> listPagePortlet = new ArrayList<PagePortletMetaData>();
        return listPagePortlet;
	}
}