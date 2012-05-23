package com.apply.b2b.cms.pageportlet.common;

import java.util.List;

import com.apply.b2b.cms.data.base.PortletMixedData;
import com.apply.b2b.cms.pageportlet.base.InlinePagePortlet;

public abstract class ListTextInlinePagePortlet extends InlinePagePortlet {
	abstract public List<PortletMixedData> getTextData();
}
