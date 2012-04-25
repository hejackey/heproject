package com.zhuozhuo.mis.dao;

import java.util.List;

import com.zhuozhuo.mis.model.QueryProductInfoJxc;
import com.zhuozhuo.mis.po.PProductInfoJxc;
import com.zhuozhuo.mis.util.page.PageWraper;

public interface ProductInfoJxcDao {	
	public void saveProductInfoJxc(PProductInfoJxc pProductInfoJxc);
	public List<PProductInfoJxc> getAllProductInfoJxc();
	public List<PProductInfoJxc> getAllProductInfoJxc(QueryProductInfoJxc queryProductInfoJxc);	
	public PProductInfoJxc getProductInfoJxc(String id);
	public void updateProductInfoJxc(PProductInfoJxc pProductInfoJxc);
	public void deleteProductInfoJxcs(String[] ids);
	public List<PProductInfoJxc> queryProductInfoJxcs(QueryProductInfoJxc queryProductInfoJxc);
	
	public PageWraper qByProductCodeProdInfoJxcs(QueryProductInfoJxc queryProductInfoJxc);
}
