package com.apply.b2b.cms.data.impl;

import com.apply.b2b.cms.data.base.PortletImageData;

/**
 * 
 * PortletImageData 的一般实现
 * @author luoweifeng
 * 
 */

public class PagePortletImageData extends PortletImageData {
	private String id;
	private Object content;
	private String summary;
	private String src;
	private String httpURL;
	private String xSize;
	private String ySize;
	
	public PagePortletImageData(String id, String name, Object content,
			String summary, String src, String httpURL, String size,
			String size2) {
		super();
		this.id = id;
		this.content = content;
		this.summary = summary;
		this.src = src;
		this.httpURL = httpURL;
		xSize = size;
		ySize = size2;
	}
	
	public PagePortletImageData (){
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
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setSrc(String src) {
		this.src = src;
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
}