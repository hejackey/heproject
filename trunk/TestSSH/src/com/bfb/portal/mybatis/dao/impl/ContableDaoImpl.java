package com.bfb.portal.mybatis.dao.impl;



import java.util.List;

import com.bfb.portal.base.dao.BaseMybatisDao;
import com.bfb.portal.dao.ContableDao;
import com.bfb.portal.model.XeditorModel;

public class ContableDaoImpl extends BaseMybatisDao implements
		ContableDao {

	public XeditorModel saveContable(XeditorModel model) {
		try{
			this.getSqlSession().insert("contable.saveContable",model);
			return model;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}

	public List<XeditorModel> getContableList() {
		return this.getSqlSession().selectList("contable.getContableList");
	}

}
