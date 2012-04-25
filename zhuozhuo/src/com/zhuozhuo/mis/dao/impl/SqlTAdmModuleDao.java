package com.zhuozhuo.mis.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.zhuozhuo.mis.dao.TAdmModuleDao;
import com.zhuozhuo.mis.po.TAdmModule;
import com.zhuozhuo.mis.util.page.PageManager;
import com.zhuozhuo.mis.util.page.PageWraper;

public class SqlTAdmModuleDao extends SqlMapClientDaoSupport implements
		TAdmModuleDao {

	@Override
	public List<String> getModuleParentName(String parentid) {
		
		return null;
	}

	public int saveModule(TAdmModule module)throws Exception{
		return this.getSqlMapClientTemplate().update("tadmmodule.saveModule",module);
	}
	
	public PageWraper listTAdmModule(TAdmModule module)throws Exception{
		Integer count = (Integer) this.getSqlMapClientTemplate()
				.queryForObject("tadmmodule.listTAdmModuleCount", module);
		List pageResult = this.getSqlMapClientTemplate().queryForList(
				"tadmmodule.listTAdmModule", module);
		PageWraper pw = PageManager.getPageWraper(module.getPageInfo(),
				pageResult, count);
		
		return pw;
	}
	
	public List<TAdmModule> getModuleTreeList()throws Exception{
		return this.getSqlMapClientTemplate().queryForList("tadmmodule.getModuleTreeList");
	}
	
	public int editModule(TAdmModule module)throws Exception{
		return this.getSqlMapClientTemplate().update("tadmmodule.editModule",module);
	}
	
	public void updateStatusModule(TAdmModule module)throws Exception{
		this.getSqlMapClientTemplate().update("tadmmodule.updateStatusModule",module);
	}
	
	public List<String> getModuleSonId(String id)throws Exception{
		return this.getSqlMapClientTemplate().queryForList("tadmmodule.getModuleSonId",id);
	}
	
	public TAdmModule getModule(String id)throws Exception{
		return (TAdmModule)this.getSqlMapClientTemplate().queryForObject("tadmmodule.getModule",id);
	}
}
