package com.zhuozhuo.mis.po;

public class AuditScmStorageOut {
	private int scmStorageId;
	private String id;	
	private int scmStorageDetailId;
	private String masterId;

	public int getScmStorageId() {
		return scmStorageId;
	}

	public void setScmStorageId(int scmStorageId) {
		this.scmStorageId = scmStorageId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getScmStorageDetailId() {
		return scmStorageDetailId;
	}

	public void setScmStorageDetailId(int scmStorageDetailId) {
		this.scmStorageDetailId = scmStorageDetailId;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
}
