package com.zhuozhuo.mis.dao;

import java.util.List;

import com.zhuozhuo.mis.model.QueryProductType;
import com.zhuozhuo.mis.po.PProductType;
import com.zhuozhuo.mis.util.page.PageWraper;

public interface ProductTypeDao {
	public String getProductTypeNameById(String id);
	public void saveProductType(PProductType pProductType);
	public List<PProductType> getAllProductTypes();
	public List<PProductType> getChildProductTypes(long recordId);	
	public void updateProductType(PProductType pProductType);
	public void deleteProductType(String recordId);	
	public PProductType getProductType(String recordId);
	public void deleteProductTypes(String[] recordIds);
	public List<PProductType> queryProductType(QueryProductType queryProductType);
	public List<PProductType> productTypeTreeList();
	public PageWraper productTypeList(PProductType model);
	
	public List<String> getProdcutTypeSonId(String id)throws Exception;
}
