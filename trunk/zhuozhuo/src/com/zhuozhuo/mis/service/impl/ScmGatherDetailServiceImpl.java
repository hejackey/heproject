package com.zhuozhuo.mis.service.impl;

import java.util.List;

import com.zhuozhuo.mis.common.BaseService;
import com.zhuozhuo.mis.dao.ScmPayDetailDao;
import com.zhuozhuo.mis.po.ScmPayDetail;
import com.zhuozhuo.mis.service.ScmPayDetailService;

public class ScmGatherDetailServiceImpl extends BaseService implements ScmPayDetailService {
	private ScmPayDetailDao scmPayDetailDao;	

	@Override
	public List<ScmPayDetail> listScmPayDetails() {		
		return scmPayDetailDao.listScmPayDetails();
	}
	
	public ScmPayDetailDao getScmPayDetailDao() {
		return scmPayDetailDao;
	}

	public void setScmPayDetailDao(ScmPayDetailDao scmPayDetailDao) {
		this.scmPayDetailDao = scmPayDetailDao;
	}

	@Override
	public List<ScmPayDetail> getScmPayDetailByProviderId(String pid)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<ScmPayDetail> getScmPayDetailByMasterId(String mid)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateScmPayDetailAuditSign(String[] id, String masterId,
			String[] arrids) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
