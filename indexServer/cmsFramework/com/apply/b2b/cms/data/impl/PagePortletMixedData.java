package com.apply.b2b.cms.data.impl;

import java.util.Date;

import com.apply.b2b.cms.data.base.PortletMixedData;

/**
 * 
 * MixedData 数据的一般实现
 * @author luoweifeng
 * 
 */

public class PagePortletMixedData extends PortletMixedData {
	private String title;
	private String id;
	private Object content;
	private String summary;
	private String otherSummary;
	private Date createDate;
	private Date updateDate;
	
	private String src;
	private String httpURL;
	private String xSize;
	private String ySize;
	
	public PagePortletMixedData(String title, String id, String name,
			Object content, String summary, String otherSummary,
			Date createDate, Date updateDate, String src, String httpURL,
			String size, String size2) {
		super();
		this.title = title;
		this.id = id;
		this.content = content;
		this.summary = summary;
		this.otherSummary = otherSummary;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.src = src;
		this.httpURL = httpURL;
		xSize = size;
		ySize = size2;
	}
	
	public PagePortletMixedData(){
		
	}
	

	public String getTitle() {
		return this.title;
	}
	
	public String getSrc() {
		return this.src;
	}
	
	public String getId() {
		return this.id;
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
	
	public String getHttpURL() {
		return httpURL;
	}
	
	public void setHttpURL(String httpURL) {
		this.httpURL = httpURL;
	}
	
	public String getXSize() {
		return xSize;
	}
	
	public void setXSize(String size) {
		xSize = size;
	}
	
	public String getYSize() {
		return ySize;
	}
	
	public void setYSize(String size) {
		ySize = size;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setSrc(String src) {
		this.src = src;
	}
}