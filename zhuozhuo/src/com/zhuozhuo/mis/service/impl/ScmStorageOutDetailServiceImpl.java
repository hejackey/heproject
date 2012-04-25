package com.zhuozhuo.mis.service.impl;

import java.util.List;

import com.zhuozhuo.mis.common.BaseService;
import com.zhuozhuo.mis.dao.ScmStorageOutDetailDao;
import com.zhuozhuo.mis.po.ScmStorageOutDetail;
import com.zhuozhuo.mis.service.ScmStorageOutDetailService;

public class ScmStorageOutDetailServiceImpl extends BaseService implements
		ScmStorageOutDetailService {
	
	private ScmStorageOutDetailDao scmStorageOutDetailDao;

	@Override
	public List<ScmStorageOutDetail> listScmStorageOutDetails() {		
		return scmStorageOutDetailDao.listScmStorageOutDetails();
	}

	public ScmStorageOutDetailDao getScmStorageOutDetailDao() {
		return scmStorageOutDetailDao;
	}

	public void setScmStorageOutDetailDao(
			ScmStorageOutDetailDao scmStorageOutDetailDao) {
		this.scmStorageOutDetailDao = scmStorageOutDetailDao;
	}

	public void deleteScmStorageOutDetail(String masterId)throws Exception{
		this.scmStorageOutDetailDao.deleteScmStorageOutDetail(masterId);
	}
}
