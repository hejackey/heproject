package com.zhuozhuo.mis.service;

import java.util.List;
import com.zhuozhuo.mis.model.ProviderType;
import com.zhuozhuo.mis.po.PProviderType;
public interface ProviderTypeService {
	public List<PProviderType> getProviderTypeTreeList();
	public void insertProviderType(ProviderType providerType);
	public List<PProviderType> getAllProviderType();
	public List<PProviderType> getChildProviderType(String recordId);
	public ProviderType getProviderType(String recordId);
	public void updateProviderType(ProviderType providerType);
	public void deleteProviderType(String recordId);
	public void deleteProviderTypes(String[] recordIds);
}
