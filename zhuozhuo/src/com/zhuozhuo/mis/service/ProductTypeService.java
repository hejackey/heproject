package com.zhuozhuo.mis.service;

import java.util.List;

import com.zhuozhuo.mis.model.ProductType;
import com.zhuozhuo.mis.model.QueryProductType;
import com.zhuozhuo.mis.po.PProductType;
import com.zhuozhuo.mis.util.page.PageWraper;

public interface ProductTypeService {
	public void saveProductType(ProductType productType);
	public List<PProductType> getAllProductTypes();
	public List<PProductType> getChildProductTypes(long recordId);	
	public ProductType getProductType(String recordId);
	public void updateProductType(ProductType productType);
	public void deleteProductType(String recordId);
	public void deleteProductTypes(String[] recordIds);
	public List<PProductType> queryProductType(QueryProductType queryProductType);
	public List<PProductType> productTypeTreeList();
	public PageWraper productTypeList(PProductType model);
	public String getProdcutTypeSonId(String id)throws Exception;
}
