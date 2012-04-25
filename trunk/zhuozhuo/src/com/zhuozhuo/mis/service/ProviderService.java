package com.zhuozhuo.mis.service;

import java.util.List;

import com.zhuozhuo.mis.model.Provider;
import com.zhuozhuo.mis.model.QueryProvider;
import com.zhuozhuo.mis.po.PProvider;
import com.zhuozhuo.mis.util.page.PageWraper;
public interface ProviderService {
	public List<PProvider> listProviders();
	public List<PProvider> listProviders(QueryProvider queryProvider);	
	public Provider getProvider(String id);
	public void saveProvider(Provider provider);
	public void updateProvider(Provider provider);
	public void deleteProviders(String[] ids);
	public List<PProvider> queryProvider(QueryProvider queryProvider);
	public PageWraper qProviderByName(QueryProvider queryProvider)throws Exception;
}
