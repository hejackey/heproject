package com.zhuozhuo.mis.dao;

import java.util.List;

import com.zhuozhuo.mis.po.ScmStorageOutDetail;

public interface ScmStorageOutDetailDao {
	public List<ScmStorageOutDetail>  listScmStorageOutDetails();
	public void saveScmStorageOutDetail(ScmStorageOutDetail scmStorageOutDetail);
	public void deleteScmStorageOutDetails(String[] ids);
	public List<ScmStorageOutDetail> getDetailScmStorageOut(String id);
	public void auditScmStorageOut(String[] ids,List list);
	public void deleteScmStorageOutDetail(String masterId)throws Exception;
//	addScmStorageOutDetail
//	editScmStorageOutDetail
//	saveScmStorageOutDetail
//	updateScmStorageOutDetail
//	deleteScmStorageOutDetails
}
