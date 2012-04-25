package com.zhuozhuo.mis.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.zhuozhuo.mis.dao.ScmStorageDao;
import com.zhuozhuo.mis.po.report.ScmStorage;
import com.zhuozhuo.mis.util.page.PageManager;
import com.zhuozhuo.mis.util.page.PageWraper;

public class SqlMapScmStorageDao extends SqlMapClientDaoSupport implements
		ScmStorageDao {

	@Override
	public PageWraper listScmStorage(ScmStorage storage) throws Exception {
		Integer count = (Integer) this.getSqlMapClientTemplate()
				.queryForObject("scmstorage.listScmStorageCount", storage);
		if(count==null)
			count=0;
		
		List pageResult = this.getSqlMapClientTemplate().queryForList(
				"scmstorage.listScmStorage", storage);
		PageWraper pw = PageManager.getPageWraper(storage.getPageInfo(),
				pageResult, count);

		return pw; 
	}

	@Override
	public List<ScmStorage> listScmStorageToExcel(ScmStorage storage)
			throws Exception {		
		return this.getSqlMapClientTemplate().queryForList("scmstorage.listScmStorageToExcel",storage);
	}

	public ScmStorage listScmStorageHj(ScmStorage storage)throws Exception{
		return (ScmStorage)this.getSqlMapClientTemplate().queryForObject("scmstorage.listScmStorageHj",storage);
	}
	
	/**
	 * 分仓汇总报表
	 * @param storage
	 * @return
	 * @throws Exception
	 */
	public PageWraper listBarnTypeStorage(ScmStorage storage)throws Exception{
		Integer count = (Integer) this.getSqlMapClientTemplate()
				.queryForObject("scmstorage.listBarnTypeStorageCount", storage);
		if(count==null)
			count=0;
		
		List pageResult = this.getSqlMapClientTemplate().queryForList(
				"scmstorage.listBarnTypeStorage", storage);
		PageWraper pw = PageManager.getPageWraper(storage.getPageInfo(),
				pageResult, count);
		
		return pw;
	}
	
	public List<ScmStorage> listBarnTypeStorageToExcel(ScmStorage storage)throws Exception{
		return this.getSqlMapClientTemplate().queryForList("scmstorage.listBarnTypeStorageToExcel",storage);
	}
	
	/**
	 * 商品类别汇总报表
	 * @param storage
	 * @return
	 * @throws Exception
	 */
	public PageWraper listProductTypeStorage(ScmStorage storage)throws Exception{
		Integer count = (Integer) this.getSqlMapClientTemplate()
				.queryForObject("scmstorage.listProductTypeStorageCount", storage);
		if(count==null)
			count=0;
		
		List pageResult = this.getSqlMapClientTemplate().queryForList(
				"scmstorage.listProductTypeStorage", storage);
		PageWraper pw = PageManager.getPageWraper(storage.getPageInfo(),
				pageResult, count);
		
		return pw;
	}
	public List<ScmStorage> listProductTypeStorageToExcel(ScmStorage storage)throws Exception{
		return this.getSqlMapClientTemplate().queryForList("scmstorage.listProductTypeStorageToExcel",storage);
	}
	
	public ScmStorage listProductTypeStorageHj(ScmStorage storage)throws Exception{
		return (ScmStorage)this.getSqlMapClientTemplate().queryForObject("scmstorage.listProductTypeStorageHj",storage);
	}
}
