package com.bfb.commonservice.model;

import java.io.Serializable;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3270884259889633335L;
	private String pwd;
	private String paypwd;
	
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPaypwd() {
		return paypwd;
	}
	public void setPaypwd(String paypwd) {
		this.paypwd = paypwd;
	}
	
	
}
