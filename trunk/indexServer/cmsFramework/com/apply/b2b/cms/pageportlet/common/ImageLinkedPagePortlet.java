package com.apply.b2b.cms.pageportlet.common;

import com.apply.b2b.cms.data.base.PortletImageData;
import com.apply.b2b.cms.pageportlet.base.LinkedPagePortlet;

public abstract class ImageLinkedPagePortlet extends LinkedPagePortlet {
	abstract public PortletImageData getImageData();
}