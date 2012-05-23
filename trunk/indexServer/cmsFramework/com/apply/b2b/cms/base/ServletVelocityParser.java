package com.apply.b2b.cms.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author luoweifeng
 *
 */

public abstract class ServletVelocityParser extends VelocityParser implements IServletVelocityParser {
	 
	public HttpServletResponse getResponse(){
		 return null;
	}
	 
	public HttpServletRequest getRequest(){
		 return null;
	}
}