package com.moobao.service;
import org.apache.log4j.Logger;

import com.apply.b2b.cms.base.BaseDAO;
import com.apply.b2b.cms.base.IDAO;

/**
 * 
 * @author wind
 *
 */
public class DataBaseService {
	protected  final Logger log = Logger.getLogger(this.getClass());
	private IDAO dao = new BaseDAO();
	public IDAO getDao(){
		return this.dao;
	}
}
