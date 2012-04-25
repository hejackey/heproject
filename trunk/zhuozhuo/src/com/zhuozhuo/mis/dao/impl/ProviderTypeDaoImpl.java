package com.zhuozhuo.mis.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhuozhuo.mis.common.BaseDao;
import com.zhuozhuo.mis.dao.ProviderTypeDao;
import com.zhuozhuo.mis.po.PProductType;
import com.zhuozhuo.mis.po.PProviderType;

public class ProviderTypeDaoImpl extends BaseDao implements ProviderTypeDao {

	@Override
	public void deleteProviderType(String recordId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProviderTypes(String[] recordIds) {
		Map map=new HashMap();
		map.put("recordIds",recordIds);
		getSqlMapClientTemplate().delete("providerType.deleteProviderTypes",map);	
	}

	@Override
	public List<PProviderType> getAllProviderType() {
		List<PProviderType> list = getSqlMapClientTemplate().queryForList("providerType.getAllProviderType");//后面必须加一个参数，否则得不到List
		return list;
	}

	@Override
	public List<PProviderType> getChildProviderType(String recordId) {
		List<PProviderType> list = getSqlMapClientTemplate().queryForList("providerType.getChildProviderType",recordId);
		return list;
	}

	@Override
	public PProviderType getProviderType(String recordId) {
		PProviderType pProviderType=(PProviderType)getSqlMapClientTemplate().queryForObject("providerType.getProviderType",recordId);
		return pProviderType;
	}

	@Override
	public void insertProviderType(PProviderType providerType) {
		getSqlMapClientTemplate().insert("providerType.insertProviderType",providerType);		
	}

	@Override
	public List<PProviderType> getProviderTypeTreeList() {
		List<PProviderType> list = getSqlMapClientTemplate().queryForList("providerType.providerTypeTreeList");//后面必须加一个参数，否则得不到List
		return list;
	}

	@Override
	public void updateProviderType(PProviderType providerType) {
		getSqlMapClientTemplate().update("providerType.updateProviderType",providerType);			
	}
}
