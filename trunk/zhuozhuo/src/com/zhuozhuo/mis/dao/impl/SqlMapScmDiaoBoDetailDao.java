package com.zhuozhuo.mis.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.zhuozhuo.mis.dao.DiaoBoDetailDao;
import com.zhuozhuo.mis.po.ScmDiaoBoDetail;
import com.zhuozhuo.mis.po.TAdmDept;

public class SqlMapScmDiaoBoDetailDao extends SqlMapClientDaoSupport implements
DiaoBoDetailDao {

	@Override
	public void saveDiaoBoDetail(final List<ScmDiaoBoDetail> detailList)
			throws Exception {
		if (detailList != null && detailList.size() > 0) {
			this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				@Override
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
				
					for (ScmDiaoBoDetail detail : detailList) {
						executor.update("diaoBodetail.saveDiaoBoDetail", detail);
					}
					
					executor.executeBatch();		
					
					return null;
				}
			});			
		}		

	}

	public List<ScmDiaoBoDetail> getDiaoBoDetailBySheetId(String sheetid)throws Exception{
		return this.getSqlMapClientTemplate().queryForList("diaoBodetail.getDiaoBoDetailBySheetId",sheetid);
	}
	
	public int deleteDiaoBoDetailBySheetid(String sheetid)throws Exception{
		return this.getSqlMapClientTemplate().delete("diaoBodetail.deleteDiaoBoDetailBySheetid",sheetid);
	}
}
