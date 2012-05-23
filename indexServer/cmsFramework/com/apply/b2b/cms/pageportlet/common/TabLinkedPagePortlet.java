package com.apply.b2b.cms.pageportlet.common;

import java.util.List;

import com.apply.b2b.cms.pageportlet.base.ITitleed;
import com.apply.b2b.cms.pageportlet.base.LinkedPagePortlet;

public abstract class TabLinkedPagePortlet extends LinkedPagePortlet{
	abstract public List<ITitleed> getTabs();
}
