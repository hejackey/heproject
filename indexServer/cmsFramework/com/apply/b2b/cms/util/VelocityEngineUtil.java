package com.apply.b2b.cms.util;

import java.util.Properties;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import com.apply.b2b.cms.config.ConfigBuilderFactory;

public class VelocityEngineUtil {
	private static VelocityEngine ve;
	private static String templatePath = ConfigBuilderFactory.getConfigBuilder().getProperty("cms.system.template.location");
	
	public static void init(){
		Properties properties = new Properties();
		properties.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH,templatePath);
		properties.setProperty("runtime.log.logsystem.log4j.category","velocity");
		properties.setProperty(Velocity.RUNTIME_LOG_LOGSYSTEM_CLASS,"org.apache.velocity.runtime.log.SimpleLog4JLogSystem");
		properties.setProperty("velocimacro.library", "");
		properties.setProperty( Velocity.RUNTIME_LOG, "velocity.log" );
		VelocityEngine velocityEngine = new VelocityEngine();
		
		try {
			velocityEngine.init(properties);
		} catch (Exception e) {
			e.printStackTrace();
			ve = null;
			return ;
		}
		ve = velocityEngine;
	}
	
	public static synchronized VelocityEngine getVelocityEngine() {
		try {
			if (ve == null) {
				Properties properties = new Properties();
				properties.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH,templatePath);
				//properties.setProperty("runtime.log.logsystem.log4j.category","velocity");
				//properties.setProperty(Velocity.RUNTIME_LOG_LOGSYSTEM_CLASS,"org.apache.velocity.runtime.log.SimpleLog4JLogSystem");
				//properties.setProperty("velocimacro.library", "");
				//properties.setProperty( Velocity.RUNTIME_LOG, "velocity.log" ); 
				VelocityEngine velocityEngine = new VelocityEngine();
				velocityEngine.init(properties);
				
				ve = velocityEngine;
			}
		} catch (Exception e) {
			return null;
		}
		return ve;
	}
}