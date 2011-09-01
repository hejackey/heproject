package com.bfb.portal.action;

import java.util.List;

import com.bfb.portal.base.action.BaseAction;
import com.bfb.portal.manager.ContableManager;
import com.bfb.portal.model.HelloWorld;
import com.bfb.portal.model.XeditorModel;

public class XheditorAction extends BaseAction {
	private XeditorModel model = new XeditorModel();
	private ContableManager contableManager;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8428556584594302455L;

	public String saveXheditor(){
		System.out.println(model.getConValue());
		HelloWorld hworld = new HelloWorld();
		hworld.setId("118");
		model.setHellWorld(hworld);
		
		
		contableManager.saveContable(model);
		
		return SUCCESS;
	}
	
	public String list(){
		List<XeditorModel> list = contableManager.getContableList();
		System.out.println(list.size());
		
		for(XeditorModel dbModel : list){
			System.out.println(dbModel.getConValue());
			System.out.println(dbModel.getHellWorld().getId());
			System.out.println(dbModel.getHellWorld().getStr());
		}
		
		model.setList(list);
		
		return SUCCESS;
	}
	public Object getModel() {
		return model;
	}
	public void setModel(XeditorModel model) {
		this.model = model;
	}

	public ContableManager getContableManager() {
		return contableManager;
	}

	public void setContableManager(ContableManager contableManager) {
		this.contableManager = contableManager;
	}

}
