package com.zhuozhuo.mis.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhuozhuo.mis.common.BaseDao;
import com.zhuozhuo.mis.dao.ProductTypeDao;
import com.zhuozhuo.mis.model.QueryProductType;
import com.zhuozhuo.mis.po.PProductType;
import com.zhuozhuo.mis.util.page.PageManager;
import com.zhuozhuo.mis.util.page.PageWraper;

public class ProductTypeDaoImpl extends BaseDao implements ProductTypeDao {	
	@Override
	public String getProductTypeNameById(String id) {
		String productTypeName=(String)getSqlMapClientTemplate().queryForObject("product.getProductTypeNameById",id);
		return productTypeName;
	}
	
	/**
	 * 向数据库保存一个对象ProductType
	 */
	@Override
	public void saveProductType(PProductType productType) {				
		getSqlMapClientTemplate().insert("product.insertProductType",productType);		
	}

	/**
	 * 得到所有的产品类型
	 */
	@Override
	public List<PProductType> getAllProductTypes() {
		List<PProductType> list = getSqlMapClientTemplate().queryForList("product.getAllProductType");		
		return list;
	}

	/**
	 * 根据recordId得到其所有的直接子类
	 */
	@Override
	public List<PProductType> getChildProductTypes(long recordId) {
		List<PProductType> list = getSqlMapClientTemplate().queryForList("product.getChildProductType",recordId);//后面必须加一个参数，否则得不到List
		return list;
	}

	/**
	 * 根据recordId删除数据库一条记录
	 */
	@Override
	public void deleteProductType(String recordId) {
		getSqlMapClientTemplate().delete("product.deleteProductType",recordId);		
	}

	/**
	 * 根据对象ProductType更新数据库
	 */
	@Override
	public void updateProductType(PProductType pProductType) {
		getSqlMapClientTemplate().update("product.updateProductType",pProductType);	
	}

	/**
	 * 根据recordId得到一个ProductType
	 */
	@Override
	public PProductType getProductType(String recordId) {		
		return (PProductType)getSqlMapClientTemplate().queryForObject("product.getProductType",recordId);	
		 
	}

	@Override
	public void deleteProductTypes(String[] recordIds) {
		Map map=new HashMap();
		map.put("recordIds",recordIds);
		getSqlMapClientTemplate().delete("product.deleteProductTypes",map);				
	}

	/**
	 * 综合查询页面
	 */
	@Override
	public List<PProductType> queryProductType(QueryProductType queryProductType) {		
		List<PProductType> list = getSqlMapClientTemplate().queryForList("product.queryProductType",queryProductType);
		return list;
	}

	@Override
	public List<PProductType> productTypeTreeList() {
		List<PProductType> list = getSqlMapClientTemplate().queryForList("product.getAllProductType");		
		return list;
	}	
	
	public PageWraper productTypeList(PProductType model){
		Integer count = (Integer) this.getSqlMapClientTemplate()
				.queryForObject("product.listProductTypeCount", model);
		List pageResult = this.getSqlMapClientTemplate().queryForList(
					"product.listProductType", model);
		PageWraper pw = PageManager.getPageWraper(model.getPageInfo(),
					pageResult, count);
		
		return pw;
	}
	
	public List<String> getProdcutTypeSonId(String id)throws Exception{
		return this.getSqlMapClientTemplate().queryForList("product.getProdcutTypeSonId",id);
	}	
}
