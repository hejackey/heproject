package com.apply.b2b.cms.pageportlet.common;

import com.apply.b2b.cms.data.base.PortletTextData;
import com.apply.b2b.cms.pageportlet.base.InlinePagePortlet;


public abstract class TextInlinePagePortlet extends InlinePagePortlet {
	abstract public PortletTextData getTextData();
}