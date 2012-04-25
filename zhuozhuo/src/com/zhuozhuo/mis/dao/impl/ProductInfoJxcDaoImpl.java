package com.zhuozhuo.mis.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhuozhuo.mis.common.BaseDao;
import com.zhuozhuo.mis.dao.ProductInfoJxcDao;
import com.zhuozhuo.mis.model.QueryProductInfoJxc;
import com.zhuozhuo.mis.po.PProductInfoJxc;
import com.zhuozhuo.mis.po.PProductType;
import com.zhuozhuo.mis.util.page.PageManager;
import com.zhuozhuo.mis.util.page.PageWraper;

public class ProductInfoJxcDaoImpl extends BaseDao implements ProductInfoJxcDao {

	/**
	 * 保存商品信息
	 */
	public void saveProductInfoJxc(PProductInfoJxc pProductInfoJxc){
		pProductInfoJxc.setCreateBy("55555");
		getSqlMapClientTemplate().insert("productInfoJxc.insertProductInfoJxc",pProductInfoJxc);
	}
	/**
	 * 查询商品信息
	 */
	public List<PProductInfoJxc> getAllProductInfoJxc(){		
		List<PProductInfoJxc> list = getSqlMapClientTemplate().queryForList("productInfoJxc.getAllProductInfoJxc");		
		return list;
	}
	@Override
	public PProductInfoJxc getProductInfoJxc(String id) {
		PProductInfoJxc pProductInfoJxc = (PProductInfoJxc)getSqlMapClientTemplate().queryForObject("productInfoJxc.getProductInfoJxc",id);		
		return pProductInfoJxc;
	}
	@Override
	public void updateProductInfoJxc(PProductInfoJxc productInfoJxc) {
		getSqlMapClientTemplate().update("productInfoJxc.updateProductInfoJxc", productInfoJxc);	
	}
	@Override
	public void deleteProductInfoJxcs(String[] ids) {
		Map map=new HashMap();
		map.put("ids",ids);
		getSqlMapClientTemplate().delete("productInfoJxc.deleteProductInfoJxcs",map);		
	}
	@Override
	public List<PProductInfoJxc> queryProductInfoJxcs(QueryProductInfoJxc queryProductInfoJxc) {		
		List<PProductInfoJxc> list = getSqlMapClientTemplate().queryForList("productInfoJxc.queryProductInfoJxc",queryProductInfoJxc);
		return list;
	}
	
	public PageWraper qByProductCodeProdInfoJxcs(QueryProductInfoJxc queryProductInfoJxc){
		Integer count = (Integer) this.getSqlMapClientTemplate()
				.queryForObject("productInfoJxc.qByProductCodeProdInfoJxcsCount", queryProductInfoJxc);
		List pageResult = this.getSqlMapClientTemplate().queryForList(
				"productInfoJxc.qByProductCodeProdInfoJxcs", queryProductInfoJxc);
		PageWraper pw = PageManager.getPageWraper(queryProductInfoJxc.getPageInfo(),
				pageResult, count);

		return pw;
	}
	@Override
	public List<PProductInfoJxc> getAllProductInfoJxc(QueryProductInfoJxc queryProductInfoJxc) {
		
		List<PProductInfoJxc> list = getSqlMapClientTemplate().queryForList("productInfoJxc.queryProductInfoJxcs",queryProductInfoJxc);
		return list;
	}
}
