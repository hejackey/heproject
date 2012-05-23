package com.apply.b2b.cms.pageportlet.common;

import com.apply.b2b.cms.data.base.PortletTextData;
import com.apply.b2b.cms.pageportlet.base.LinkedPagePortlet;

public abstract class TextLinkedPagePortlet extends LinkedPagePortlet {
	abstract public PortletTextData getTextData();
}