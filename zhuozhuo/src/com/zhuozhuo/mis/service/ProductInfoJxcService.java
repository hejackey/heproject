package com.zhuozhuo.mis.service;

import java.util.List;

import com.zhuozhuo.mis.model.ProductInfoJxc;
import com.zhuozhuo.mis.model.QueryProductInfoJxc;
import com.zhuozhuo.mis.po.PProductInfoJxc;
import com.zhuozhuo.mis.util.page.PageWraper;

public interface ProductInfoJxcService {
	public void saveProductInfoJxc(ProductInfoJxc productInfoJxc);
	public List<PProductInfoJxc> getAllProductInfoJxc();
	public List<PProductInfoJxc> getAllProductInfoJxc(QueryProductInfoJxc queryProductInfoJxc);
	public ProductInfoJxc getProductInfoJxc(String id);
	public void updateProductInfoJxc(ProductInfoJxc productInfoJxc);
	public void deleteProductInfoJxcs(String[] ids);
	public List<PProductInfoJxc> queryProductInfoJxcs(QueryProductInfoJxc queryProductInfoJxc);
	
	public PageWraper qByProductCodeProdInfoJxcs(QueryProductInfoJxc queryProductInfoJxc);
}
