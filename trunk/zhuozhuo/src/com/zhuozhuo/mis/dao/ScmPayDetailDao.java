package com.zhuozhuo.mis.dao;

import java.util.List;

import com.zhuozhuo.mis.po.ScmPayDetail;

public interface ScmPayDetailDao {
	public List<ScmPayDetail>  listScmPayDetails();
	public void saveScmPayDetail(ScmPayDetail scmPayDetail);
	public void deleteScmPayDetails(String[] ids);
	public List<ScmPayDetail> showScmPayDetail(String id);
	public List<ScmPayDetail> getScmPayDetailByProviderId(String pid)throws Exception;
	
	public List<ScmPayDetail> getScmPayDetailByMasterId(String mid)throws Exception;
	public void updateScmPayDetailAuditSign(String[] id,String masterId,String[] arrids)throws Exception;
//	addScmPayDetail
//	editScmPayDetail
//	saveScmPayDetail
//	updateScmPayDetail
//	deleteScmPayDetails
}
