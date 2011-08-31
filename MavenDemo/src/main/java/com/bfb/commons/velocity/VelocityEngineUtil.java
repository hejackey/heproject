package com.bfb.commons.velocity;

import java.io.StringWriter;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import com.bfb.commons.properties.PropertiesUtil;


public class VelocityEngineUtil {
	private static VelocityEngine ve;
	private static String templatePath = PropertiesUtil.getValueFromPropertyByKey("config.properties","TMEPLATE_PATH");
	private static Logger log = Logger.getLogger(VelocityEngineUtil.class);
	
	public static synchronized VelocityEngine getVelocityEngine() {
		try {
			if (ve == null) {
				Properties properties = new Properties();
				properties.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH,templatePath);
				VelocityEngine velocityEngine = new VelocityEngine();
				velocityEngine.init(properties);
				ve = velocityEngine;
			}
		} catch (Exception e) {
			return null;
		}
		return ve;
	}
	
	protected static Template getTemplate(String templateName) {
		Template t = null;
		if (StringUtils.isNotBlank(templateName)) {
			try {
				if(ve==null)
					ve = getVelocityEngine();
				t = ve.getTemplate(templateName, "UTF-8");//"template/" + 
				log.info(t.getResourceLoader().getClassName());
				
			} catch (Exception e) {
				e.printStackTrace();
				log.error("初始velocity 模板" + templateName + "失败",e);
			}				
		}
		return t;
	}
	
	protected String getTemplatePath() {
		return PropertiesUtil.getValueFromPropertyByKey("config.properties","TMEPLATE_PATH");
	}

	public static String parserContent(VelocityContext context,String templateName) throws ResourceNotFoundException, ParseErrorException, MethodInvocationException, Exception {
		StringBuffer sf = new StringBuffer();
		StringWriter writer = new StringWriter();
		Template t = getTemplate(templateName);
		
		context.put("VelocityStringUtil", VelocityStringUtil.getInstance());
		t.merge(context, writer);
		sf.append(writer);
		return sf.toString();
	}
}