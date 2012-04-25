package com.zhuozhuo.mis.vo;

public class TDept {
	private String q_id;
	private String q_parentid;
	private String q_depcode;
	private String q_depname;
	private Integer q_ifuse;
	private int page;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getQ_depcode() {
		return q_depcode;
	}
	public void setQ_depcode(String q_depcode) {
		this.q_depcode = q_depcode;
	}
	public String getQ_depname() {
		return q_depname;
	}
	public void setQ_depname(String q_depname) {
		this.q_depname = q_depname;
	}
	public Integer getQ_ifuse() {
		return q_ifuse;
	}
	public void setQ_ifuse(Integer q_ifuse) {
		this.q_ifuse = q_ifuse;
	}
	public String getQ_id() {
		return q_id;
	}
	public void setQ_id(String q_id) {
		this.q_id = q_id;
	}
	public String getQ_parentid() {
		return q_parentid;
	}
	public void setQ_parentid(String q_parentid) {
		this.q_parentid = q_parentid;
	}
	
}
