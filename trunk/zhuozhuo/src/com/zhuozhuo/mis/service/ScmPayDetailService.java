package com.zhuozhuo.mis.service;

import java.util.List;
import com.zhuozhuo.mis.po.ScmPayDetail;

public interface ScmPayDetailService {
	public List<ScmPayDetail>  listScmPayDetails();
	public List<ScmPayDetail> getScmPayDetailByProviderId(String pid)throws Exception;
	public void updateScmPayDetailAuditSign(String[] id,String masterId,String[] arrids)throws Exception;
	public List<ScmPayDetail> getScmPayDetailByMasterId(String mid)throws Exception;
//	addScmPayDetail
//	editScmPayDetail
//	saveScmPayDetail
//	updateScmPayDetail
//	deleteScmPayDetails
}
