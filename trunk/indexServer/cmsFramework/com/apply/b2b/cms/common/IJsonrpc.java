package com.apply.b2b.cms.common;

import javax.servlet.http.HttpServletRequest;

public interface IJsonrpc {
	public String getUserLoginResult( String userName, HttpServletRequest request);
}