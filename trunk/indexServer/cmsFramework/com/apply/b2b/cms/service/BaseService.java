package com.apply.b2b.cms.service;

import org.apache.log4j.Logger;

import com.apply.b2b.cms.base.BaseDAO;
import com.apply.b2b.cms.base.IDAO;

/**
 * 
 * @author luoweifeng
 *
 */
public class BaseService {
	protected final Logger log = Logger.getLogger(this.getClass());
	private IDAO dao = new BaseDAO();
	
	public IDAO getDao() {
		return dao;
	}
	
	public void setDao(IDAO dao) {
		this.dao = dao;
	}
}