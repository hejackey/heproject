package com.zhuozhuo.mis.service.impl;

import java.util.List;

import com.zhuozhuo.mis.dao.DiaoBoDetailDao;
import com.zhuozhuo.mis.dao.StockOrderDetailDao;
import com.zhuozhuo.mis.po.ScmDiaoBoDetail;
import com.zhuozhuo.mis.po.ScmStockOrderDetail;
import com.zhuozhuo.mis.service.DiaoBoDetailService;
import com.zhuozhuo.mis.service.StockOrderDetailService;

public class DiaoBoDetailServiceImpl implements DiaoBoDetailService {
	private DiaoBoDetailDao diaoBoDetailDao;
	
	public void saveDiaoBoDetail(List<ScmDiaoBoDetail> detailList)
			throws Exception {
		this.diaoBoDetailDao.saveDiaoBoDetail(detailList);
	}

	public List<ScmDiaoBoDetail> getDiaoBoDetailBySheetId(String sheetid)throws Exception{
		return this.diaoBoDetailDao.getDiaoBoDetailBySheetId(sheetid);
	}
	
	public int deleteDiaoBoDetailBySheetid(String sheetid)throws Exception{
		return this.diaoBoDetailDao.deleteDiaoBoDetailBySheetid(sheetid);
	}

	public DiaoBoDetailDao getDiaoBoDetailDao() {
		return diaoBoDetailDao;
	}

	public void setDiaoBoDetailDao(DiaoBoDetailDao diaoBoDetailDao) {
		this.diaoBoDetailDao = diaoBoDetailDao;
	}
	
	

}
