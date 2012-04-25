package com.zhuozhuo.mis.service.impl;

import java.util.List;

import com.zhuozhuo.mis.common.BaseService;
import com.zhuozhuo.mis.dao.ProviderTypeDao;
import com.zhuozhuo.mis.model.ProviderType;
import com.zhuozhuo.mis.po.PProviderType;
import com.zhuozhuo.mis.service.ProviderTypeService;

public class ProviderTypeServiceImpl  extends BaseService  implements ProviderTypeService {

	private ProviderTypeDao providerTypeDao;

	public ProviderTypeDao getProviderTypeDao() {
		return providerTypeDao;
	}

	public void setProviderTypeDao(ProviderTypeDao providerTypeDao) {
		this.providerTypeDao = providerTypeDao;
	}

	@Override
	public void deleteProviderType(String recordId) {
		// providerTypeDao
		
	}

	@Override
	public void deleteProviderTypes(String[] recordIds) {
		providerTypeDao.deleteProviderTypes(recordIds);	
	}

	@Override
	public List<PProviderType> getAllProviderType() {
		return providerTypeDao.getAllProviderType();	
	}

	@Override
	public List<PProviderType> getChildProviderType(String recordId) {
		return providerTypeDao.getChildProviderType(recordId);		
	}

	@Override
	public ProviderType getProviderType(String recordId) {
		PProviderType pProviderType = providerTypeDao.getProviderType(recordId);
		ProviderType providerType =new ProviderType();
		copyProperties(pProviderType, providerType);
		return providerType;
	}

	@Override
	public void insertProviderType(ProviderType providerType) {
		PProviderType pProviderType=new PProviderType();
		copyProperties(providerType,pProviderType);
		providerTypeDao.insertProviderType(pProviderType);
	}

	@Override
	public void updateProviderType(ProviderType providerType) {
		PProviderType pProviderType=new PProviderType();
		copyProperties(providerType,pProviderType);
		providerTypeDao.updateProviderType(pProviderType);	
	}

	@Override
	public List<PProviderType> getProviderTypeTreeList() {		
		return providerTypeDao.getProviderTypeTreeList();
	}
}
