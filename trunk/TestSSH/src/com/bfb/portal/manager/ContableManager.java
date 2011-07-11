package com.bfb.portal.manager;

import java.util.List;

import com.bfb.portal.model.XeditorModel;

public interface ContableManager {
	public XeditorModel saveContable(XeditorModel model);
	public List<XeditorModel> getContableList();
}
