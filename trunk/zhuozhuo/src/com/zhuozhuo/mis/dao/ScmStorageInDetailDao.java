package com.zhuozhuo.mis.dao;

import java.util.List;

import com.zhuozhuo.mis.po.ScmStorageInDetail;

public interface ScmStorageInDetailDao {
	public void auditTHD(String[] ids, List list);
	public void saveScmStorageInDetail(ScmStorageInDetail scmStorageInDetail);
	public void deleteScmStorageInDetails(String[] ids);
	public void auditScmScmStorageIns(String[] ids,List list);	
	public List<ScmStorageInDetail> getScmStorageInDetailsByMasterId(String id);
	public void deleteScmStorageInDetailByMasterId(int masterId);
	public List<ScmStorageInDetail> getEditScmStorageInDetailByMasterId(String id, String type);
}
