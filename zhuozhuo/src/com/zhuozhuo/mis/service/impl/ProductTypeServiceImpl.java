package com.zhuozhuo.mis.service.impl;

import java.util.List;

import com.zhuozhuo.mis.common.BaseService;
import com.zhuozhuo.mis.dao.ProductTypeDao;
import com.zhuozhuo.mis.model.ProductType;
import com.zhuozhuo.mis.model.QueryProductType;
import com.zhuozhuo.mis.po.PProductType;
import com.zhuozhuo.mis.service.ProductTypeService;
import com.zhuozhuo.mis.util.page.PageWraper;
import com.zhuozhuo.utils.StringUtils;

public class ProductTypeServiceImpl extends BaseService implements ProductTypeService {

	private ProductTypeDao productTypeDao;

	public ProductTypeDao getProductTypeDao() {
		return productTypeDao;
	}

	public void setProductTypeDao(ProductTypeDao productTypeDao) {
		this.productTypeDao = productTypeDao;
	}
	
	/**
	 * 向数据库保存一个对象ProductType
	 */
	@Override
	public void saveProductType(ProductType productType) {
		PProductType pProductType=new PProductType();
		copyProperties(productType,pProductType);//po和vo的转换		
		productTypeDao.saveProductType(pProductType);
		
	}

	/**
	 * 得到所有的产品类型
	 */
	@Override
	public List<PProductType> getAllProductTypes() {		
		return productTypeDao.getAllProductTypes();
	}

	/**
	 * 根据recordId得到其所有的直接子类
	 */
	@Override
	public List<PProductType> getChildProductTypes(long recordId) {		
		return productTypeDao.getChildProductTypes(recordId);
	}

	/**
	 * 根据recordId删除数据库一条记录
	 */
	@Override
	public void deleteProductType(String recordId) {
		productTypeDao.deleteProductType(recordId);
	}

	/**
	 * 根据对象ProductType更新数据库
	 */
	@Override
	public void updateProductType(ProductType productType) {
		PProductType pProductType=new PProductType();
		copyProperties(productType,pProductType);
		productTypeDao.updateProductType(pProductType);
	}

	/**
	 * 根据recordId得到一个ProductType
	 */
	@Override
	public ProductType getProductType(String recordId) {
		PProductType pProductType=productTypeDao.getProductType(recordId);
		ProductType productType=new ProductType();
		copyProperties(pProductType, productType);
		return productType;
	}

	@Override
	public void deleteProductTypes(String[] recordIds) {
		productTypeDao.deleteProductTypes(recordIds);
		
	}

	/**
	 * 综合查询页面
	 */
	@Override
	public List<PProductType> queryProductType(QueryProductType queryProductType) {
		return productTypeDao.queryProductType(queryProductType);
	}

	/**
	 * 获取商品分类子id
	 */
	public String getProdcutTypeSonId(String id)throws Exception{
		if(StringUtils.isEmpty(id)){
			return "";
		}
		List<String> sonIdList = this.productTypeDao.getProdcutTypeSonId(id);
		StringBuffer sb = new StringBuffer();
		for(String sonId : sonIdList){
			sb.append(sonId);
			sb.append(",");
		}
		
		if(sb !=null && sb.length()>0){
			sb.append(id);
			return sb.toString();
		}
		
		return id;
	}
	
	@Override
	public List<PProductType> productTypeTreeList() {
		return productTypeDao.productTypeTreeList();
	}	
	
	public PageWraper productTypeList(PProductType model){
		return this.productTypeDao.productTypeList(model);
	}		
}
