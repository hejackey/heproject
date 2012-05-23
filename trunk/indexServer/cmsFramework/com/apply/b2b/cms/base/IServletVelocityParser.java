package com.apply.b2b.cms.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IServletVelocityParser extends IVelocityParser{
	public HttpServletResponse getResponse();
	public HttpServletRequest getRequest();
}