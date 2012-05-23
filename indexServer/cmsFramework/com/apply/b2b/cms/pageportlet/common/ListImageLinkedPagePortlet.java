package com.apply.b2b.cms.pageportlet.common;

import java.util.List;

import com.apply.b2b.cms.data.base.PortletImageData;
import com.apply.b2b.cms.pageportlet.base.LinkedPagePortlet;

public abstract class ListImageLinkedPagePortlet extends LinkedPagePortlet {
	abstract public List<PortletImageData> getImageData();
}