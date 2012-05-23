package com.apply.b2b.cms.pageportlet.common;

import com.apply.b2b.cms.data.base.PortletImageData;
import com.apply.b2b.cms.pageportlet.base.InlinePagePortlet;

public abstract class ImageInlinePagePortlet extends InlinePagePortlet {
	abstract public PortletImageData getImageData();
}