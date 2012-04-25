package com.zhuozhuo.mis.service;

import java.util.List;

import com.zhuozhuo.mis.po.TAdmRole;
import com.zhuozhuo.mis.util.page.PageWraper;

public interface TAdmRoleService {
	public List<TAdmRole> getRoleList()throws Exception;
	public TAdmRole getRole(String id)throws Exception;
	public int saveRole(TAdmRole role)throws Exception;
	public PageWraper listTAdmRole(TAdmRole role)throws Exception;
	public int editRole(TAdmRole role)throws Exception;
	public TAdmRole getRoleById(String id)throws Exception;
	public int updateStatusRole(String[] idArray,int flag)throws Exception;
	public int deleteRole(String id)throws Exception;
	public int saveRolePriv(TAdmRole role)throws Exception;
	public int editRolePriv(TAdmRole role)throws Exception;
	public List<String> getRolePrilByRoleId(String roleid)throws Exception;
}
