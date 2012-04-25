package com.zhuozhuo.mis.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.zhuozhuo.mis.dao.TAdmDeptDao;
import com.zhuozhuo.mis.po.TAdmDept;
import com.zhuozhuo.mis.util.page.PageManager;
import com.zhuozhuo.mis.util.page.PageWraper;

public class SqlMapTAdmDeptDao extends SqlMapClientDaoSupport implements
		TAdmDeptDao {

	public PageWraper listTAdmDept(TAdmDept model) throws Exception {
		Integer count = (Integer) this.getSqlMapClientTemplate()
				.queryForObject("tadmdept.listTAdmDeptCount", model);
		List pageResult = this.getSqlMapClientTemplate().queryForList(
				"tadmdept.listTAdmDept", model);
		PageWraper pw = PageManager.getPageWraper(model.getPageInfo(),
				pageResult, count);

		return pw;

	}

	public List<TAdmDept> getDeptTreeList()throws Exception{
		return this.getSqlMapClientTemplate().queryForList("tadmdept.getDeptTreeList");
	}
	
	public List<String> getDeptParentName(String parentid) throws Exception {
		return this.getSqlMapClientTemplate().queryForList("tadmdept.getDeptParentName",parentid);
	}
	
	public TAdmDept getDept(String id)throws Exception{
		return (TAdmDept)this.getSqlMapClientTemplate().queryForObject("tadmdept.getDept",id);
	}
	
	public int editDept(TAdmDept model)throws Exception{
		return this.getSqlMapClientTemplate().update("tadmdept.editDept",model);
	}
	public int saveDept(TAdmDept model)throws Exception{
		return this.getSqlMapClientTemplate().update("tadmdept.saveDept",model);
	}
	
	public void updateStatusDeptPatch(final List<TAdmDept> listDept)throws Exception{
		if (listDept != null && listDept.size() > 0) {
			this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				@Override
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
				
					for (TAdmDept dept : listDept) {
						executor.update("tadmdept.updateStatusDept", dept);
					}
					
					executor.executeBatch();		
					
					return null;
				}
			});			
		}		
	}
	
	public TAdmDept getDeptById(String id)throws Exception{
		return (TAdmDept)this.getDeptById(id);
	}
	
	public void updateStatusDept(TAdmDept dept)throws Exception{
		this.getSqlMapClientTemplate().update("tadmdept.updateStatusDept",dept);
	}
	
	public List<String> getDeptSonId(String id)throws Exception{
		return this.getSqlMapClientTemplate().queryForList("tadmdept.getDeptSonId",id);
	}
	
	public List<TAdmDept> getDeptList()throws Exception{
		return this.getSqlMapClientTemplate().queryForList("tadmdept.getDeptList");
	}
}
