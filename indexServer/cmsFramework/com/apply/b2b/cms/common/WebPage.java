package com.apply.b2b.cms.common;

import java.util.Date;
/**
 * 
 * @author luoweifeng
 *
 */
public class WebPage {
	private String contentType = "text/html; charset=utf-8";
	private String title = "5366.com";
	private String keywords = "";
	private String description = "";
	private String abstractDec = "";
	private String owner = "www.5366.com";
	private String language = "en-US";
	private Date   date = new Date();
	
	private String robots = "index,follow";
	private String copryRight = "Copyright (c) 2008 by 5366.com Corporation";
	private String security = "Public";
	private String shortCuticon = "";
	
	public String getContentType() {
		return contentType;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getKeywords() {
		return keywords;
	}
	
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getAbstractDec() {
		return abstractDec;
	}
	
	public void setAbstractDec(String abstractDec) {
		this.abstractDec = abstractDec;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getRobots() {
		return robots;
	}
	
	public void setRobots(String robots) {
		this.robots = robots;
	}
	
	public String getCopryRight() {
		return copryRight;
	}
	
	public void setCopryRight(String copryRight) {
		this.copryRight = copryRight;
	}
	
	public String getSecurity() {
		return security;
	}
	
	public void setSecurity(String security) {
		this.security = security;
	}
	
	public String getShortCuticon() {
		return shortCuticon;
	}
	
	public void setShortCuticon(String shortCuticon) {
		this.shortCuticon = shortCuticon;
	}
}