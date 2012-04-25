package com.zhuozhuo.mis.dao;

import java.util.List;

import com.zhuozhuo.mis.po.ScmStockOrder;
import com.zhuozhuo.mis.util.page.PageWraper;

public interface StockOrderDao {
	public String saveStockOrder(ScmStockOrder model);
	public PageWraper listStockOrder(ScmStockOrder model)throws Exception;
	public ScmStockOrder getStockOrder(String sheetid)throws Exception;
	public int editStockOrder(ScmStockOrder model)throws Exception;
	public int updateStatusStockOrder(ScmStockOrder stockOrder)throws Exception;
	public List<String> exportStockOrderToStorageIn(List<String> idList)throws Exception;
	public void exportStockOrderDetailToStorageInDetail(List<String> idList,List<String> storageIdList)throws Exception;
	public void exportStockOrderToScmPayDetail(String sheetids)throws Exception;
	
	public List<ScmStockOrder> exportStockOrderToExcel(ScmStockOrder stockOrder)throws Exception;
	public int deleteStockOrder(String id)throws Exception;
}
