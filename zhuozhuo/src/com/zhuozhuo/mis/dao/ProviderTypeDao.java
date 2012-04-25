package com.zhuozhuo.mis.dao;

import java.util.List;

import com.zhuozhuo.mis.po.PProviderType;

public interface ProviderTypeDao {
	public List<PProviderType> getProviderTypeTreeList();
	public void insertProviderType(PProviderType pProviderType);
	public List<PProviderType> getAllProviderType();
	public List<PProviderType> getChildProviderType(String recordId);
	public PProviderType getProviderType(String recordId);
	public void updateProviderType(PProviderType pProviderType);
	public void deleteProviderType(String recordId);
	public void deleteProviderTypes(String[] recordIds);
}
