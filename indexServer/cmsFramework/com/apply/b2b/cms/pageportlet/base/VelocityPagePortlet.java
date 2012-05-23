package com.apply.b2b.cms.pageportlet.base;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import com.apply.b2b.cms.util.VelocityEngineUtil;

/**
 * @author luoweifeng
 */

public abstract class VelocityPagePortlet extends BasePagePortlet implements
		IVelocityPagePortlet {
	
	private final VelocityEngine ve = VelocityEngineUtil.getVelocityEngine();
	
	public String show() {
		if (this.isShow()) {
			return doShow();
		} else {
			return "";
		}
	}
	
	protected String doShow(){
		return getParserContent();
	}
	
	abstract protected void beforePutTemplateParseValue(VelocityContext context);
	
	abstract protected void putVelocityTemplateParseValue(
			VelocityContext context);
	
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
	
	private String parserContent() throws ResourceNotFoundException,
			ParseErrorException, MethodInvocationException, Exception {
		StringBuffer sf = new StringBuffer();
		StringWriter writer = new StringWriter();
		Template t = getTemplate();
		VelocityContext context = new VelocityContext();
		beforePutTemplateParseValue(context);
		putVelocityTemplateParseValue(context);
		afterPutTemplateParseValue(context);
		t.merge(context, writer);
		sf.append(writer);
		return sf.toString();
	}

	private VelocityEngine getVe() {
		return ve;
	}
	
	private Template getTemplate() {
		String templateFileName = this.getTemplateName();
		Template t = null;
		if (templateFileName != null && !templateFileName.trim().equals("")) {
			try {
				t = getVe().getTemplate(templateFileName, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
				log.error("初始velocity 模板" + templateFileName + "失败", e);
			}
		}else{
			log.error("模板名称为空");
		}
		return t;
	}
}