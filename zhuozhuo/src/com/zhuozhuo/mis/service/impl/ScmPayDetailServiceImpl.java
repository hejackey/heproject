package com.zhuozhuo.mis.service.impl;

import java.util.List;

import com.zhuozhuo.mis.common.BaseService;
import com.zhuozhuo.mis.dao.ScmPayDetailDao;
import com.zhuozhuo.mis.po.ScmPayDetail;
import com.zhuozhuo.mis.service.ScmPayDetailService;

public class ScmPayDetailServiceImpl extends BaseService implements ScmPayDetailService {
	private ScmPayDetailDao scmPayDetailDao;	

	@Override
	public List<ScmPayDetail> listScmPayDetails() {		
		return scmPayDetailDao.listScmPayDetails();
	}
	
	public List<ScmPayDetail> getScmPayDetailByProviderId(String pid)throws Exception{
		return this.scmPayDetailDao.getScmPayDetailByProviderId(pid);
	}
	
	public void updateScmPayDetailAuditSign(String[] id,String masterId,String[] arrids)throws Exception{
		this.scmPayDetailDao.updateScmPayDetailAuditSign(id, masterId,arrids);
	}
	
	public List<ScmPayDetail> getScmPayDetailByMasterId(String mid)throws Exception{
		return this.scmPayDetailDao.getScmPayDetailByMasterId(mid);
	}
	
	public ScmPayDetailDao getScmPayDetailDao() {
		return scmPayDetailDao;
	}

	public void setScmPayDetailDao(ScmPayDetailDao scmPayDetailDao) {
		this.scmPayDetailDao = scmPayDetailDao;
	}
}
