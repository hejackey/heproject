package com.jackey.parser.bean;

import java.io.Serializable;

public class AlexBean extends PageBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7429313419976329767L;
	private int id;
	
	private int count;
	private String domain;
	private String desc;
	private String title;
	private int sorttype;
	private String webtype;
	
	public AlexBean(){
		super();
	}
	
	public AlexBean(int sRow,int gSize){
		super(sRow, gSize);
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getSorttype() {
		return sorttype;
	}
	public void setSorttype(int sorttype) {
		this.sorttype = sorttype;
	}
	public String getWebtype() {
		return webtype;
	}
	public void setWebtype(String webtype) {
		this.webtype = webtype;
	}
}
