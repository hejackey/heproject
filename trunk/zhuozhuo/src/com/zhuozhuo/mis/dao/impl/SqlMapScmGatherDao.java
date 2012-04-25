package com.zhuozhuo.mis.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.zhuozhuo.mis.dao.ScmGatherDao;
import com.zhuozhuo.mis.dao.StockOrderDao;
import com.zhuozhuo.mis.po.ScmGather;
import com.zhuozhuo.mis.util.page.PageManager;
import com.zhuozhuo.mis.util.page.PageWraper;

public class SqlMapScmGatherDao extends SqlMapClientDaoSupport implements
		ScmGatherDao {

	@Override
	public String saveScmGather(ScmGather model) {
		
		return (String)this.getSqlMapClientTemplate().insert("scmgather.saveScmGather",model);
	}
	
	public PageWraper listScmGather(ScmGather model)throws Exception{
		Integer count = (Integer) this.getSqlMapClientTemplate()
				.queryForObject("scmgather.listScmGatherCount", model);
		List pageResult = this.getSqlMapClientTemplate().queryForList(
				"scmgather.listScmGather", model);
		PageWraper pw = PageManager.getPageWraper(model.getPageInfo(),
				pageResult, count);
		
		return pw;
	}
	
	public ScmGather getScmGather(String sheetid)throws Exception{
		return (ScmGather)this.getSqlMapClientTemplate().queryForObject("scmgather.getScmGather",sheetid);
	}
	
	public int editScmGather(ScmGather model)throws Exception{
		return this.getSqlMapClientTemplate().update("scmgather.editScmGather",model);
	}
	
	public int updateStatusScmGather(ScmGather stockOrder)throws Exception{
		return this.getSqlMapClientTemplate().update("scmgather.updateStatusScmGather",stockOrder);
	}
	
	public List<String> exportScmGatherToStorageIn(final List<String> idList)throws Exception{
		if (idList != null && idList.size() > 0) {
			return (List<String>)this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				@Override
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
				
					List<String> storageIdList = new ArrayList<String>();
					for (String id : idList) {
						ScmGather stockOrder = new ScmGather();
						stockOrder.setId(id);
						storageIdList.add(executor.insert("scmgather.exportScmGatherToStorageIn", stockOrder).toString());											
					}
					
					executor.executeBatch();		
					
					return storageIdList;
				}
			});			
		}
		
		return null;
	}
	
	public void exportScmGatherDetailToStorageInDetail(final List<String> idList,final List<String> storageIdList)throws Exception{
		if (idList != null && idList.size() > 0) {
			this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				@Override
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();		
					int i=0;
					for (String id : idList) {
						ScmGather stockOrder = new ScmGather();
						stockOrder.setId(id);
						stockOrder.setStorageInId(storageIdList.get(i));
						executor.update("scmgather.exportScmGatherDetailToStorageInDetail", stockOrder);
						
						i++;
					}
					
					executor.executeBatch();		
					
					return null;
				}
			});			
		}		
	}
}
