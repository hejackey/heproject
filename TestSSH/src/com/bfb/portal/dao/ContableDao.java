package com.bfb.portal.dao;

import java.util.List;

import com.bfb.portal.model.XeditorModel;

public interface ContableDao {
	public XeditorModel saveContable(XeditorModel model);
	public List<XeditorModel> getContableList();
}
