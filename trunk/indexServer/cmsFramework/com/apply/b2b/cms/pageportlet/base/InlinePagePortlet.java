package com.apply.b2b.cms.pageportlet.base;

import org.apache.velocity.VelocityContext;

/**
 * 
 * inLine 类型的PagePortlet
 * @author luoweifeng
 *
 */

public abstract class InlinePagePortlet extends VelocityPagePortlet implements IInline {
	
	@Override
	protected void afterPutTemplateParseValue(VelocityContext context) {
	}
	
	@Override
	protected void beforePutTemplateParseValue(VelocityContext context) {
	}
	
	public void delete() {
	}
	
	public void init() {
	}
	
	public String getInlineContent(){
		return this.getParserContent();
	}
}