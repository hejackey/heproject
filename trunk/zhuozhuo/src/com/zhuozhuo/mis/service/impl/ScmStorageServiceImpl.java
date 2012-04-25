package com.zhuozhuo.mis.service.impl;

import java.util.Date;
import java.util.List;

import com.zhuozhuo.mis.dao.ScmStorageDao;
import com.zhuozhuo.mis.po.report.ScmStorage;
import com.zhuozhuo.mis.service.ScmStorageService;
import com.zhuozhuo.mis.util.page.PageWraper;
import com.zhuozhuo.utils.DateUtils;
import com.zhuozhuo.utils.StringUtils;

public class ScmStorageServiceImpl implements ScmStorageService {
	private ScmStorageDao scmStorageDao;
	
	@Override
	public PageWraper listScmStorage(ScmStorage storage) throws Exception {
		return scmStorageDao.listScmStorage(storage);
	}

	@Override
	public List<ScmStorage> listScmStorageToExcel(ScmStorage storage)
			throws Exception {
		return this.scmStorageDao.listScmStorageToExcel(storage);
	}

	public ScmStorage listScmStorageHj(ScmStorage storage)throws Exception{
		if(StringUtils.isEmpty(storage.getStartTime())){
			if(StringUtils.isEmpty(storage.getEndTime()))
				storage.setStartTime(DateUtils.getDateFormat(DateUtils.getBeforeMonth(new Date()),"yyyy-MM-dd"));
			else{
				storage.setStartTime(DateUtils.getDateFormat(DateUtils.getBeforeMonth(
						DateUtils.parseDateFormat(storage.getEndTime(),"yyyy-MM-dd")),"yyyy-MM-dd"));
			}
		}
		if(StringUtils.isEmpty(storage.getEndTime()))
			storage.setEndTime(DateUtils.getDateFormat(new Date(),"yyyy-MM-dd"));
		
		ScmStorage model = this.scmStorageDao.listScmStorageHj(storage);
		if(model != null){
			storage.setHjcgqty(model.getHjcgqty());
			storage.setHjcgamt(model.getHjcgamt());
			storage.setHjxsqty(model.getHjxsqty());
			storage.setHjxsamt(model.getHjxsamt());
			storage.setHjrkqty(model.getHjrkqty());
			storage.setHjrkamt(model.getHjrkamt());
			storage.setHjckqty(model.getHjckqty());
			storage.setHjckamt(model.getHjckamt());
			storage.setHjthqty(model.getHjthqty());
			storage.setHjthamt(model.getHjthamt());
			storage.setRkqty(model.getHjqty());
			storage.setRkamt(model.getRkamt());
			storage.setCkqty(model.getCkqty());
			storage.setCkamt(model.getCkamt());
			storage.setThqty(model.getThqty());
			storage.setThamt(model.getThamt());
			storage.setHjzqty(model.getHjzqty());
			storage.setHjzamt(model.getHjzamt());
		}
		
		return storage;
	}
	
	/**
	 * 分仓汇总报表
	 * @param storage
	 * @return
	 * @throws Exception
	 */
	public PageWraper listBarnTypeStorage(ScmStorage storage)throws Exception{
		return this.scmStorageDao.listBarnTypeStorage(storage);
	}
	
	public List<ScmStorage> listBarnTypeStorageToExcel(ScmStorage storage)throws Exception{
		return this.scmStorageDao.listBarnTypeStorageToExcel(storage);
	}
	
	/**
	 * 商品类别汇总报表
	 * @param storage
	 * @return
	 * @throws Exception
	 */
	public PageWraper listProductTypeStorage(ScmStorage storage)throws Exception{
		return this.scmStorageDao.listProductTypeStorage(storage);
	}
	
	public List<ScmStorage> listProductTypeStorageToExcel(ScmStorage storage)throws Exception{
		return this.scmStorageDao.listProductTypeStorageToExcel(storage);
	}
	
	public ScmStorage listProductTypeStorageHj(ScmStorage storage)throws Exception{
		if(StringUtils.isEmpty(storage.getStartTime())){
			if(StringUtils.isEmpty(storage.getEndTime()))
				storage.setStartTime(DateUtils.getDateFormat(DateUtils.getBeforeMonth(new Date()),"yyyy-MM-dd"));
			else{
				storage.setStartTime(DateUtils.getDateFormat(DateUtils.getBeforeMonth(
						DateUtils.parseDateFormat(storage.getEndTime(),"yyyy-MM-dd")),"yyyy-MM-dd"));
			}
		}
		if(StringUtils.isEmpty(storage.getEndTime()))
			storage.setEndTime(DateUtils.getDateFormat(new Date(),"yyyy-MM-dd"));
		
		ScmStorage s = this.scmStorageDao.listProductTypeStorageHj(storage);
		if(s!=null){
			storage.setHjLastAmt(s.getHjLastAmt());
			storage.setHjInAmt(s.getHjInAmt());
			storage.setHjOutAmt(s.getHjOutAmt());
		}
		
		return storage;
	}
	
	public ScmStorageDao getScmStorageDao() {
		return scmStorageDao;
	}

	public void setScmStorageDao(ScmStorageDao scmStorageDao) {
		this.scmStorageDao = scmStorageDao;
	}

}
