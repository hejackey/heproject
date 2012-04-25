package com.zhuozhuo.mis.dao;

import java.util.List;

import com.zhuozhuo.mis.po.TAdmModule;
import com.zhuozhuo.mis.util.page.PageWraper;

public interface TAdmModuleDao {
	public List<String> getModuleParentName(String parentid);
	public int saveModule(TAdmModule module)throws Exception;
	public PageWraper listTAdmModule(TAdmModule module)throws Exception;
	public List<TAdmModule> getModuleTreeList()throws Exception;
	public TAdmModule getModule(String id)throws Exception;
	public int editModule(TAdmModule module)throws Exception;
	public void updateStatusModule(TAdmModule module)throws Exception;
	public List<String> getModuleSonId(String id)throws Exception;
}

