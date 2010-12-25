package com.jackey.android.diary.model;

import java.io.Serializable;

public class DiaryModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8204254222720487712L;
	private String title;
	private String content;
	private String createtime;
	private Integer id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
}
