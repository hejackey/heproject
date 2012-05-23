package com.apply.b2b.cms.pageportlet.common;

import java.util.List;

import com.apply.b2b.cms.data.base.PortletMixedData;
import com.apply.b2b.cms.pageportlet.base.LinkedPagePortlet;

public abstract class ListTextLinkedPagePortlet extends LinkedPagePortlet {
	abstract public List<PortletMixedData> getTextData();
}
