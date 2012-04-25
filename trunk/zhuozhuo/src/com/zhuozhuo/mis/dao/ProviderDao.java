package com.zhuozhuo.mis.dao;

import java.util.List;

import com.zhuozhuo.mis.model.QueryProvider;
import com.zhuozhuo.mis.po.PProvider;
import com.zhuozhuo.mis.util.page.PageWraper;

public interface ProviderDao {
	public List<PProvider> listProviders();
	public PProvider getProvider(String id);
	public void saveProvider(PProvider pProvider);
	public void  updateProvider(PProvider pProvider);
	public void deleteProviders(String[] ids);
	public List<PProvider> queryProvider(QueryProvider queryProvider);
	public PageWraper qProviderByName(QueryProvider queryProvider)throws Exception;
}
