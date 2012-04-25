package com.zhuozhuo.mis.service.impl;

import java.util.List;

import com.zhuozhuo.mis.common.BaseService;
import com.zhuozhuo.mis.dao.ProductInfoJxcDao;
import com.zhuozhuo.mis.dao.ProductTypeDao;
import com.zhuozhuo.mis.model.ProductInfoJxc;
import com.zhuozhuo.mis.model.QueryProductInfoJxc;
import com.zhuozhuo.mis.po.PProductInfoJxc;
import com.zhuozhuo.mis.service.ProductInfoJxcService;
import com.zhuozhuo.mis.util.page.PageWraper;

public class ProductInfoJxcServiceImpl extends BaseService implements
		ProductInfoJxcService {

	private ProductInfoJxcDao productInfoJxcDao;
	private ProductTypeDao productTypeDao;
	
	/**
	 * 保存商品
	 */
	public void saveProductInfoJxc(ProductInfoJxc productInfoJxc) {
		PProductInfoJxc pProductInfoJxc=new PProductInfoJxc();
		copyProperties(productInfoJxc, pProductInfoJxc);
		productInfoJxcDao.saveProductInfoJxc(pProductInfoJxc);
	}

	public ProductInfoJxcDao getProductInfoJxcDao() {
		return productInfoJxcDao;
	}

	public void setProductInfoJxcDao(ProductInfoJxcDao productInfoJxcDao) {
		this.productInfoJxcDao = productInfoJxcDao;
	}
	
	public List<PProductInfoJxc> getAllProductInfoJxc(){
		return productInfoJxcDao.getAllProductInfoJxc();
	}

	@Override
	public ProductInfoJxc getProductInfoJxc(String id) {
		PProductInfoJxc pProductInfoJxc= productInfoJxcDao.getProductInfoJxc(id);
		ProductInfoJxc productInfoJxc=new ProductInfoJxc();
		copyProperties(pProductInfoJxc,productInfoJxc);
		String productTypeName=productTypeDao.getProductTypeNameById(pProductInfoJxc.getProductType());
		productInfoJxc.setProductTypeName(productTypeName);
		return productInfoJxc;
	}

	@Override
	public void updateProductInfoJxc(ProductInfoJxc productInfoJxc) {
		PProductInfoJxc pProductInfoJxc=new PProductInfoJxc();
		copyProperties(productInfoJxc,pProductInfoJxc);
		productInfoJxcDao.updateProductInfoJxc(pProductInfoJxc);
	}

	@Override
	public void deleteProductInfoJxcs(String[] ids) {
		productInfoJxcDao.deleteProductInfoJxcs(ids);
	}

	@Override
	public List<PProductInfoJxc> queryProductInfoJxcs(QueryProductInfoJxc queryProductInfoJxc) {		
		return productInfoJxcDao.queryProductInfoJxcs(queryProductInfoJxc);
	}
	
	public PageWraper qByProductCodeProdInfoJxcs(QueryProductInfoJxc queryProductInfoJxc){
		return this.productInfoJxcDao.qByProductCodeProdInfoJxcs(queryProductInfoJxc);
	}

	public ProductTypeDao getProductTypeDao() {
		return productTypeDao;
	}

	public void setProductTypeDao(ProductTypeDao productTypeDao) {
		this.productTypeDao = productTypeDao;
	}

	@Override
	public List<PProductInfoJxc> getAllProductInfoJxc(QueryProductInfoJxc queryProductInfoJxc) {		
		return productInfoJxcDao.getAllProductInfoJxc(queryProductInfoJxc);
	}
}
