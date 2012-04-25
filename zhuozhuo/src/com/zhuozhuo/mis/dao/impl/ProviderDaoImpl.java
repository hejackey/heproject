package com.zhuozhuo.mis.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhuozhuo.mis.common.BaseDao;
import com.zhuozhuo.mis.dao.ProviderDao;
import com.zhuozhuo.mis.model.QueryProvider;
import com.zhuozhuo.mis.po.PProductInfoJxc;
import com.zhuozhuo.mis.po.PProductType;
import com.zhuozhuo.mis.po.PProvider;
import com.zhuozhuo.mis.util.page.PageManager;
import com.zhuozhuo.mis.util.page.PageWraper;

public class ProviderDaoImpl extends BaseDao implements ProviderDao {
	@Override
	public List<PProvider> listProviders() {		
		List<PProvider> list = getSqlMapClientTemplate().queryForList("provider.listProviders");		
		return list;
	}

	@Override
	public PProvider getProvider(String id) {
		return (PProvider)getSqlMapClientTemplate().queryForObject("provider.getProvider",id);
	}

	@Override
	public void saveProvider(PProvider provider) {
		getSqlMapClientTemplate().insert("provider.insertProvider",provider);	
	}

	@Override
	public void updateProvider(PProvider provider) {
		getSqlMapClientTemplate().update("provider.updateProvider",provider);
	}

	@Override
	public void deleteProviders(String[] ids) {
		Map map=new HashMap();
		map.put("ids",ids);
		getSqlMapClientTemplate().delete("provider.deleteProviders",map);		
	}

	@Override
	public List<PProvider> queryProvider(QueryProvider queryProvider) {
		List<PProvider> list = getSqlMapClientTemplate().queryForList("provider.queryProvider",queryProvider);		
		return list;
	}
	
	public PageWraper qProviderByName(QueryProvider queryProvider)throws Exception{
		Integer count = (Integer)this.getSqlMapClientTemplate().queryForObject("provider.qProviderByNameCount",queryProvider);
		List pageResult = this.getSqlMapClientTemplate().queryForList(
				"provider.qProviderByName", queryProvider);
		PageWraper pw = PageManager.getPageWraper(queryProvider.getPageInfo(),
				pageResult, count);
		
		return pw;
	}
}
