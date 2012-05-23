package com.apply.b2b.cms.base;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

/**
 * 
 * @author luoweifeng
 * 
 */

public abstract class VelocityParser extends BaseParser implements IVelocityParser {
	private Template getTemplate(){
		return this.getTemplate(this.getTemplateName());
	}
	
	protected VelocityContext getVelocityContext(VelocityContext context) {
		return context;
	}
	
	abstract protected void beforePutTemplateParseValue(VelocityContext context);
	
	abstract protected void putVelocityTemplateParseValue(VelocityContext context);
	
	abstract protected void afterPutTemplateParseValue(VelocityContext context);
	
	public String getParserContent() {
		try {
			return parserContent();
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		} catch (ParseErrorException e) {
			e.printStackTrace();
		} catch (MethodInvocationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String parserContent() throws ResourceNotFoundException, ParseErrorException, MethodInvocationException, Exception {
		StringBuffer sf = new StringBuffer();
		StringWriter writer = new StringWriter();
		Template t = getTemplate();
		VelocityContext context = new VelocityContext();
		
		beforePutTemplateParseValue(context);
		
		context = this.getVelocityContext(context);
		
		putVelocityTemplateParseValue(context);
		
		afterPutTemplateParseValue(context);
		t.merge(context, writer);
		sf.append(writer);
		return sf.toString();
	}
}