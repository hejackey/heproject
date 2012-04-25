package com.zhuozhuo.mis.service.impl;

import java.util.List;

import com.zhuozhuo.mis.common.BaseService;
import com.zhuozhuo.mis.dao.ProviderDao;
import com.zhuozhuo.mis.model.Provider;
import com.zhuozhuo.mis.model.QueryProvider;
import com.zhuozhuo.mis.po.PProvider;
import com.zhuozhuo.mis.service.ProviderService;
import com.zhuozhuo.mis.util.page.PageWraper;

public class ProviderServiceImpl extends BaseService implements ProviderService {
	private ProviderDao providerDao;

	public ProviderDao getProviderDao() {
		return providerDao;
	}

	public void setProviderDao(ProviderDao providerDao) {
		this.providerDao = providerDao;
	}

	@Override
	public List<PProvider> listProviders() {		
		return providerDao.listProviders();		
	}

	@Override
	public Provider getProvider(String id) {
		PProvider pProvider=providerDao.getProvider(id);
		Provider provider=new Provider();
		copyProperties(pProvider,provider);
		return provider;
	}

	@Override
	public void saveProvider(Provider provider) {
		PProvider pProvider=new PProvider();
		copyProperties(provider,pProvider);
		providerDao.saveProvider(pProvider);
	}

	@Override
	public void updateProvider(Provider provider) {		
		PProvider pProvider=new PProvider();
		copyProperties(provider,pProvider);
		providerDao.updateProvider(pProvider);
	}

	@Override
	public void deleteProviders(String[] ids) {
		providerDao.deleteProviders(ids);
	}

	@Override
	public List<PProvider> queryProvider(QueryProvider queryProvider) {		
		return providerDao.queryProvider(queryProvider);
	}
	
	public PageWraper qProviderByName(QueryProvider queryProvider)throws Exception{
		return this.providerDao.qProviderByName(queryProvider);
	}

	@Override
	public List<PProvider> listProviders(QueryProvider queryProvider) {		
		return providerDao.queryProvider(queryProvider);
	}
}
