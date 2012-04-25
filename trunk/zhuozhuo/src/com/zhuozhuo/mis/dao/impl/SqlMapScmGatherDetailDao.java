package com.zhuozhuo.mis.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.zhuozhuo.mis.dao.ScmGatherDetailDao;
import com.zhuozhuo.mis.po.ScmGatherDetail;

public class SqlMapScmGatherDetailDao extends SqlMapClientDaoSupport implements
		ScmGatherDetailDao {

	@Override
	public void saveScmGatherDetail(final List<ScmGatherDetail> detailList)
			throws Exception {
		if (detailList != null && detailList.size() > 0) {
			this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				@Override
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
				
					for (ScmGatherDetail detail : detailList) {
						executor.update("stockorderdetail.saveScmGatherDetail", detail);
					}
					
					executor.executeBatch();		
					
					return null;
				}
			});			
		}		

	}

	public List<ScmGatherDetail> getScmGatherDetailBySheetId(String sheetid)throws Exception{
		return this.getSqlMapClientTemplate().queryForList("stockorderdetail.getScmGatherDetailBySheetId",sheetid);
	}
	
	public int deleteScmGatherDetailBySheetid(String sheetid)throws Exception{
		return this.getSqlMapClientTemplate().delete("stockorderdetail.deleteScmGatherDetailBySheetid",sheetid);
	}
}
