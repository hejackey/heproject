package com.zhuozhuo.mis.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.zhuozhuo.mis.dao.TAdmRoleDao;
import com.zhuozhuo.mis.po.TAdmRole;
import com.zhuozhuo.mis.util.page.PageManager;
import com.zhuozhuo.mis.util.page.PageWraper;

public class SqlMapTAdmRoleDao extends SqlMapClientDaoSupport implements
		TAdmRoleDao {
	public PageWraper listTAdmRole(TAdmRole role)throws Exception{
		Integer count = (Integer) this.getSqlMapClientTemplate()
				.queryForObject("tadmrole.listTAdmRoleCount", role);
		List pageResult = this.getSqlMapClientTemplate().queryForList(
				"tadmrole.listTAdmRole", role);
		PageWraper pw = PageManager.getPageWraper(role.getPageInfo(),
				pageResult, count);
		
		return pw;
	}
	
	public int editRole(TAdmRole role)throws Exception{
		return this.getSqlMapClientTemplate().update("tadmrole.editRole",role);
	}
	
	@Override
	public List<TAdmRole> getRoleList() throws Exception {
		return this.getSqlMapClientTemplate().queryForList("tadmrole.getRoleList");
	}

	public TAdmRole getRole(String id)throws Exception{
		return (TAdmRole)this.getSqlMapClientTemplate().queryForObject("tadmrole.getRole",id);
	}
	
	public TAdmRole getRoleById(String id)throws Exception{
		return (TAdmRole)this.getSqlMapClientTemplate().queryForObject("tadmrole.getRoleById",id);
	}
	public int saveRole(TAdmRole role)throws Exception{
		return this.getSqlMapClientTemplate().update("tadmrole.saveRole",role);
	}

	@Override
	public int updateStatusRole(TAdmRole role) throws Exception {
		return this.getSqlMapClientTemplate().update("tadmrole.updateStatusRole",role);
	}
	
	public int deleteRole(String id)throws Exception{
		return this.getSqlMapClientTemplate().delete("tadmrole.deleteRole",id);
	}
	
	public int saveRolePriv(TAdmRole role)throws Exception{
		return this.getSqlMapClientTemplate().update("tadmrole.saveRolePriv",role);		
	}
	
	public String getRoleSeq()throws Exception{
		return (String)this.getSqlMapClientTemplate().queryForObject("tadmrole.getRoleSeq");
	}
	
	public int editRolePriv(TAdmRole role)throws Exception{
		return this.getSqlMapClientTemplate().update("tadmrole.editRolePriv",role);
	}
	
	public String getRolePrilByRoleId(String roleid)throws Exception{
		return (String)this.getSqlMapClientTemplate().queryForObject("tadmrole.getRolePrilByRoleId",roleid);
	}
	
	public int deleteRolePriv(String id)throws Exception{
		return this.getSqlMapClientTemplate().delete("tadmrole.deleteRolePriv",id);
	}
}
