package com.zhuozhuo.mis.dao;

import java.util.List;

import com.zhuozhuo.mis.po.report.ScmStorage;
import com.zhuozhuo.mis.util.page.PageWraper;

public interface ScmStorageDao {
	/**
	 * 进销存汇总报表
	 * @param storage
	 * @return
	 * @throws Exception
	 */
	public PageWraper listScmStorage(ScmStorage storage)throws Exception;
	public List<ScmStorage> listScmStorageToExcel(ScmStorage storage)throws Exception;
	public ScmStorage listScmStorageHj(ScmStorage storage)throws Exception;
	
	/**
	 * 分仓汇总报表
	 * @param storage
	 * @return
	 * @throws Exception
	 */
	public PageWraper listBarnTypeStorage(ScmStorage storage)throws Exception;
	public List<ScmStorage> listBarnTypeStorageToExcel(ScmStorage storage)throws Exception;
	
	/**
	 * 商品类别汇总报表
	 * @param storage
	 * @return
	 * @throws Exception
	 */
	public PageWraper listProductTypeStorage(ScmStorage storage)throws Exception;
	public List<ScmStorage> listProductTypeStorageToExcel(ScmStorage storage)throws Exception;
	public ScmStorage listProductTypeStorageHj(ScmStorage storage)throws Exception;
	
}
