package com.zhuozhuo.mis.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.zhuozhuo.mis.dao.TAdmUserDao;
import com.zhuozhuo.mis.po.TAdmUser;
import com.zhuozhuo.mis.util.page.PageManager;
import com.zhuozhuo.mis.util.page.PageWraper;

public class SqlMapTAdmUserDao extends SqlMapClientDaoSupport implements
		TAdmUserDao {
	public PageWraper listTAdmUser(TAdmUser user)throws Exception{
		Integer count = (Integer) this.getSqlMapClientTemplate()
				.queryForObject("tadmuser.listTAdmUserCount", user);
		List pageResult = this.getSqlMapClientTemplate().queryForList(
				"tadmuser.listTAdmUser", user);
		PageWraper pw = PageManager.getPageWraper(user.getPageInfo(),
				pageResult, count);
		
		return pw;
	}
	/**
	 * 根据部门id更新用户状态
	 */
	@Override
	public int updateUserStatus(TAdmUser user) throws Exception {		
		return this.getSqlMapClientTemplate().update("tadmuser.updateUserStatus",user);
	}

	public int saveUser(TAdmUser user) throws Exception{
		return this.getSqlMapClientTemplate().update("tadmuser.saveUser",user);
	}
	
	public TAdmUser getUser(String id)throws Exception{
		return (TAdmUser)this.getSqlMapClientTemplate().queryForObject("tadmuser.getUser",id);
	}
	
	public int editUser(TAdmUser user)throws Exception{
		return this.getSqlMapClientTemplate().update("tadmuser.editUser",user);
	}
	
	/**
	 * 根据用户id更新用户
	 */
	public int updateStatusUser(TAdmUser user)throws Exception{
		return this.getSqlMapClientTemplate().update("tadmuser.updateStatusUser",user);
	}
	
	public int deleteUser(String id)throws Exception{
		return this.getSqlMapClientTemplate().delete("tadmuser.deleteUser",id);
	}
	@Override
	public List<String> getUserByDeptId(String deptid) throws Exception {
		return this.getSqlMapClientTemplate().queryForList("tadmuser.getUserByDeptId",deptid);
	}
	
	public List<TAdmUser> getUserList()throws Exception{
		return this.getSqlMapClientTemplate().queryForList("tadmuser.getUserList");
	}
	
	public TAdmUser isLogin(TAdmUser user)throws Exception{
		return (TAdmUser)this.getSqlMapClientTemplate().queryForObject("tadmuser.isLogin",user);
	}
}
