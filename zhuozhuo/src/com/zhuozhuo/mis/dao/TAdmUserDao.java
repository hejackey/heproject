package com.zhuozhuo.mis.dao;

import java.util.List;

import com.zhuozhuo.mis.po.TAdmUser;
import com.zhuozhuo.mis.util.page.PageWraper;

public interface TAdmUserDao {
	public int updateUserStatus(TAdmUser user) throws Exception;
	public int saveUser(TAdmUser user) throws Exception;
	public PageWraper listTAdmUser(TAdmUser user)throws Exception;
	public TAdmUser getUser(String id)throws Exception;
	public int editUser(TAdmUser user)throws Exception;
	public int updateStatusUser(TAdmUser user)throws Exception;	
	public int deleteUser(String id)throws Exception;
	public List<String> getUserByDeptId(String deptid)throws Exception;
	public List<TAdmUser> getUserList()throws Exception;
	public TAdmUser isLogin(TAdmUser user)throws Exception;
}
