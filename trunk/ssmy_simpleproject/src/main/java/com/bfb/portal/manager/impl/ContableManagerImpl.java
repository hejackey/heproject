package com.bfb.portal.manager.impl;

import java.util.List;

import com.bfb.portal.dao.ContableDao;
import com.bfb.portal.manager.ContableManager;
import com.bfb.portal.model.XeditorModel;

public class ContableManagerImpl implements ContableManager {
	private ContableDao contableDao;
	
	public XeditorModel saveContable(XeditorModel model) {
		return contableDao.saveContable(model);
	}

	public ContableDao getContableDao() {
		return contableDao;
	}

	public void setContableDao(ContableDao contableDao) {
		this.contableDao = contableDao;
	}

	public List<XeditorModel> getContableList() {
		return contableDao.getContableList();
	}

}
