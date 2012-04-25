package com.zhuozhuo.mis.service;

import java.util.List;

import com.zhuozhuo.mis.po.ScmStorageOutDetail;


public interface ScmStorageOutDetailService {
	public List<ScmStorageOutDetail>  listScmStorageOutDetails();
	public void deleteScmStorageOutDetail(String masterId)throws Exception;
//	addScmStorageOutDetail
//	editScmStorageOutDetail
//	saveScmStorageOutDetail
//	updateScmStorageOutDetail
//	deleteScmStorageOutDetails
}
