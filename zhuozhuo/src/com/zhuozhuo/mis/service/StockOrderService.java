package com.zhuozhuo.mis.service;

import java.util.List;

import com.zhuozhuo.mis.po.ScmStockOrder;
import com.zhuozhuo.mis.util.page.PageWraper;

public interface StockOrderService {
	public String saveStockOrder(ScmStockOrder model)throws Exception;
	
	public PageWraper listStockOrder(ScmStockOrder model)throws Exception;
	public ScmStockOrder getStockOrder(String sheetid)throws Exception;
	public int editStockOrder(ScmStockOrder model)throws Exception;
	public int updateStatusStockOrder(String[] asheetid,int flag)throws Exception;
	public List<String> exportStockOrderToStorageIn(List<String> sheetidList)throws Exception;
	public void exportStockOrderDetailToStorageInDetail(List<String> idList,List<String> storageIdList)throws Exception;
	public List<ScmStockOrder> exportStockOrderToExcel(ScmStockOrder stockOrder)throws Exception;
	public int deleteStockOrder(String[] idArray)throws Exception;
}
