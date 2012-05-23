package com.apply.b2b.cms.pageportlet.common;

import java.util.List;

import com.apply.b2b.cms.data.base.PortletImageData;
import com.apply.b2b.cms.data.base.PortletMixedData;
import com.apply.b2b.cms.pageportlet.base.InlinePagePortlet;

public abstract class ImageListTextInlinePagePortlet extends InlinePagePortlet {
	abstract public PortletImageData getImageData();
	abstract public List<PortletMixedData> getTextData();
}