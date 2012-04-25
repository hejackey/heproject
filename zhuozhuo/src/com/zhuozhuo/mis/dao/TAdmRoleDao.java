package com.zhuozhuo.mis.dao;

import java.util.List;

import com.zhuozhuo.mis.po.TAdmRole;
import com.zhuozhuo.mis.util.page.PageWraper;

public interface TAdmRoleDao {
	public List<TAdmRole> getRoleList()throws Exception;
	public TAdmRole getRole(String id)throws Exception;
	public int saveRole(TAdmRole role)throws Exception;
	public PageWraper listTAdmRole(TAdmRole role)throws Exception;
	public int editRole(TAdmRole role)throws Exception;
	public TAdmRole getRoleById(String id)throws Exception;
	public int updateStatusRole(TAdmRole role)throws Exception;
	public int deleteRole(String id)throws Exception;
	public int deleteRolePriv(String id)throws Exception;
	public int saveRolePriv(TAdmRole role)throws Exception;
	public int editRolePriv(TAdmRole role)throws Exception;
	public String getRoleSeq()throws Exception;
	public String getRolePrilByRoleId(String roleid)throws Exception;
}
