package com.apply.b2b.cms.data.impl;

import java.util.Date;

import com.apply.b2b.cms.data.base.PortletTextData;

/**
 * 
 * PortletTextData 的一般实现
 * @author luoweifeng
 *
 */

public class PagePortletTextData extends  PortletTextData {
	private String title;
	private String id;
	private Object content;
	private String summary;
	private String otherSummary;
	private Date createDate;
	private Date updateDate;
	private String httpURL;
	
	public PagePortletTextData(){
	}
	
	public PagePortletTextData(String title, String id, String name,
			Object content, String summary, String otherSummary,
			Date createDate, Date updateDate) {
		super();
		this.title = title;
		this.id = id;
		this.content = content;
		this.summary = summary;
		this.otherSummary = otherSummary;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}
	
	public String getTitle() {
          return this.title;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Object getContent() {
		return content;
	}
	
	public void setContent(Object content) {
		this.content = content;
	}
	
	public String getSummary() {
		return summary;
	}
	
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public String getOtherSummary() {
		return otherSummary;
	}
	
	public void setOtherSummary(String otherSummary) {
		this.otherSummary = otherSummary;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Date getUpdateDate() {
		return updateDate;
	}
	
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getHttpURL() {
		return httpURL;
	}
	
	public void setHttpURL(String httpURL) {
		this.httpURL = httpURL;
	}
}