package com.zhuozhuo.mis.service.impl;

import java.util.List;

import com.zhuozhuo.mis.dao.StockOrderDetailDao;
import com.zhuozhuo.mis.po.ScmStockOrderDetail;
import com.zhuozhuo.mis.service.StockOrderDetailService;

public class StockOrderDetailServiceImpl implements StockOrderDetailService {
	private StockOrderDetailDao stockOrderDetailDao;
	
	@Override
	public void saveStockOrderDetail(List<ScmStockOrderDetail> detailList)
			throws Exception {
		this.stockOrderDetailDao.saveStockOrderDetail(detailList);
	}

	public List<ScmStockOrderDetail> getStockOrderDetailBySheetId(String sheetid)throws Exception{
		return this.stockOrderDetailDao.getStockOrderDetailBySheetId(sheetid);
	}
	
	public int deleteStockOrderDetailBySheetid(String sheetid)throws Exception{
		return this.stockOrderDetailDao.deleteStockOrderDetailBySheetid(sheetid);
	}
	
	public StockOrderDetailDao getStockOrderDetailDao() {
		return stockOrderDetailDao;
	}

	public void setStockOrderDetailDao(StockOrderDetailDao stockOrderDetailDao) {
		this.stockOrderDetailDao = stockOrderDetailDao;
	}

}
