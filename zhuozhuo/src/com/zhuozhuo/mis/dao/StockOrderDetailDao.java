package com.zhuozhuo.mis.dao;

import java.util.List;

import com.zhuozhuo.mis.po.ScmStockOrderDetail;

public interface StockOrderDetailDao {
	public void saveStockOrderDetail(List<ScmStockOrderDetail> detailList)throws Exception;
	public List<ScmStockOrderDetail> getStockOrderDetailBySheetId(String sheetid)throws Exception;
	public int deleteStockOrderDetailBySheetid(String sheetid)throws Exception;
}
