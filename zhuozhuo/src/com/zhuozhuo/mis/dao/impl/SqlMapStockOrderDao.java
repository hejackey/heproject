package com.zhuozhuo.mis.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.zhuozhuo.mis.dao.StockOrderDao;
import com.zhuozhuo.mis.po.ScmStockOrder;
import com.zhuozhuo.mis.util.page.PageManager;
import com.zhuozhuo.mis.util.page.PageWraper;
import com.zhuozhuo.utils.GenerateSheetIdUtil;

public class SqlMapStockOrderDao extends SqlMapClientDaoSupport implements
		StockOrderDao {

	@Override
	public String saveStockOrder(ScmStockOrder model) {
		
		return (String)this.getSqlMapClientTemplate().insert("stockorder.saveStockOrder",model);
	}
	
	public PageWraper listStockOrder(ScmStockOrder model)throws Exception{
		Integer count = (Integer) this.getSqlMapClientTemplate()
				.queryForObject("stockorder.listStockOrderCount", model);
		List pageResult = this.getSqlMapClientTemplate().queryForList(
				"stockorder.listStockOrder", model);
		PageWraper pw = PageManager.getPageWraper(model.getPageInfo(),
				pageResult, count);
		
		return pw;
	}
	
	public ScmStockOrder getStockOrder(String sheetid)throws Exception{
		return (ScmStockOrder)this.getSqlMapClientTemplate().queryForObject("stockorder.getStockOrder",sheetid);
	}
	
	public int editStockOrder(ScmStockOrder model)throws Exception{
		return this.getSqlMapClientTemplate().update("stockorder.editStockOrder",model);
	}
	
	public int updateStatusStockOrder(ScmStockOrder stockOrder)throws Exception{
		return this.getSqlMapClientTemplate().update("stockorder.updateStatusStockOrder",stockOrder);
	}
	
	public List<String> exportStockOrderToStorageIn(final List<String> idList)throws Exception{
		if (idList != null && idList.size() > 0) {
			return (List<String>)this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				@Override
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
				
					List<String> storageIdList = new ArrayList<String>();
					for (String id : idList) {
						ScmStockOrder stockOrder = new ScmStockOrder();
						stockOrder.setId(id);
						stockOrder.setSrcsheetid(stockOrder.getSheetid());
						stockOrder.setSheetid(GenerateSheetIdUtil.genSheetIdDate("RKD"));
						storageIdList.add(executor.insert("stockorder.exportStockOrderToStorageIn", stockOrder).toString());											
					}
					
					executor.executeBatch();		
					
					return storageIdList;
				}
			});			
		}
		
		return null;
	}
	
	public void exportStockOrderDetailToStorageInDetail(final List<String> idList,final List<String> storageIdList)throws Exception{
		if (idList != null && idList.size() > 0) {
			this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				@Override
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();		
					int i=0;
					for (String id : idList) {
						ScmStockOrder stockOrder = new ScmStockOrder();
						stockOrder.setId(id);
						stockOrder.setStorageInId(storageIdList.get(i));
						executor.update("stockorder.exportStockOrderDetailToStorageInDetail", stockOrder);
						
						i++;
					}
					
					executor.executeBatch();		
					
					return null;
				}
			});			
		}		
	}
	
	public void exportStockOrderToScmPayDetail(String sheetids)throws Exception{
		this.getSqlMapClientTemplate().insert("stockorder.exportStockOrderToScmPayDetail", sheetids);
	}
	
	public List<ScmStockOrder> exportStockOrderToExcel(ScmStockOrder stockOrder)throws Exception{
		return this.getSqlMapClientTemplate().queryForList("stockorder.exportStockOrderToExcel",stockOrder);
	}
	public int deleteStockOrder(String id)throws Exception{
		return this.getSqlMapClientTemplate().delete("stockorder.deleteStockOrder",id);
	}
}
