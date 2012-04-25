package com.zhuozhuo.mis.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.zhuozhuo.mis.dao.StockOrderDetailDao;
import com.zhuozhuo.mis.po.ScmStockOrderDetail;
import com.zhuozhuo.mis.po.TAdmDept;

public class SqlMapScmOrderDetailDao extends SqlMapClientDaoSupport implements
		StockOrderDetailDao {

	@Override
	public void saveStockOrderDetail(final List<ScmStockOrderDetail> detailList)
			throws Exception {
		if (detailList != null && detailList.size() > 0) {
			this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				@Override
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
				
					for (ScmStockOrderDetail detail : detailList) {
						executor.update("stockorderdetail.saveStockOrderDetail", detail);
					}
					
					executor.executeBatch();		
					
					return null;
				}
			});			
		}		

	}

	public List<ScmStockOrderDetail> getStockOrderDetailBySheetId(String sheetid)throws Exception{
		return this.getSqlMapClientTemplate().queryForList("stockorderdetail.getStockOrderDetailBySheetId",sheetid);
	}
	
	public int deleteStockOrderDetailBySheetid(String sheetid)throws Exception{
		return this.getSqlMapClientTemplate().delete("stockorderdetail.deleteStockOrderDetailBySheetid",sheetid);
	}
}
