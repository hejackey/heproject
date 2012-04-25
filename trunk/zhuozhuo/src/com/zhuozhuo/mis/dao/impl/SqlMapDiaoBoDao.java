package com.zhuozhuo.mis.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.zhuozhuo.mis.dao.DiaoBoDao;
import com.zhuozhuo.mis.dao.StockOrderDao;
import com.zhuozhuo.mis.po.ScmDiaoBo;
import com.zhuozhuo.mis.po.ScmStockOrder;
import com.zhuozhuo.mis.util.page.PageManager;
import com.zhuozhuo.mis.util.page.PageWraper;

public class SqlMapDiaoBoDao extends SqlMapClientDaoSupport implements
		DiaoBoDao {

	@Override
	public String saveDiaoBo(ScmDiaoBo model) {
		
		return (String)this.getSqlMapClientTemplate().insert("diaoBo.saveDiaoBo",model);
	}
	
	public PageWraper listDiaoBo(ScmDiaoBo model)throws Exception{
		Integer count = (Integer) this.getSqlMapClientTemplate()
				.queryForObject("diaoBo.listDiaoBoCount", model);
		List pageResult = this.getSqlMapClientTemplate().queryForList(
				"diaoBo.listDiaoBo", model);
		PageWraper pw = PageManager.getPageWraper(model.getPageInfo(),
				pageResult, count);
		
		return pw;
	}
	
	public ScmDiaoBo getDiaoBo(String sheetid)throws Exception{
		return (ScmDiaoBo)this.getSqlMapClientTemplate().queryForObject("diaoBo.getDiaoBo",sheetid);
	}
	
	public int editDiaoBo(ScmDiaoBo model)throws Exception{
		return this.getSqlMapClientTemplate().update("diaoBo.editDiaoBo",model);
	}
	
	public int updateStatusDiaoBo(ScmDiaoBo diaoBo)throws Exception{
		return this.getSqlMapClientTemplate().update("diaoBo.updateStatusDiaoBo",diaoBo);
	}
	
	public List<String> exportDiaoBoToStorageIn(final List<String> idList)throws Exception{
		if (idList != null && idList.size() > 0) {
			return (List<String>)this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				@Override
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
				
					List<String> storageIdList = new ArrayList<String>();
					for (String id : idList) {
						ScmDiaoBo diaoBo = new ScmDiaoBo();
						diaoBo.setId(id);
						storageIdList.add(executor.insert("diaoBo.exportDiaoBoToStorageIn", diaoBo).toString());											
					}
					
					executor.executeBatch();		
					
					return storageIdList;
				}
			});			
		}
		
		return null;
	}
	
	public void exportDiaoBoDetailToStorageInDetail(final List<String> idList,final List<String> storageIdList)throws Exception{
		if (idList != null && idList.size() > 0) {
			this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				@Override
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();		
					int i=0;
					for (String id : idList) {
						ScmDiaoBo diaoBo = new ScmDiaoBo();
						diaoBo.setId(id);
						diaoBo.setStorageInId(storageIdList.get(i));
						executor.update("diaoBo.exportDiaoBoDetailToStorageInDetail", diaoBo);
						
						i++;
					}
					
					executor.executeBatch();		
					
					return null;
				}
			});			
		}		
	}
	
	public void exportDiaoBoToScmPayDetail(String sheetids)throws Exception{
		this.getSqlMapClientTemplate().insert("diaoBo.exportDiaoBoToScmPayDetail", sheetids);
	}
}
