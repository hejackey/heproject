package com.zhuozhuo.mis.service.impl;

import java.util.List;

import com.zhuozhuo.b2c.memcached.service.CacheService;

import com.zhuozhuo.mis.dao.TAdmUserDao;
import com.zhuozhuo.mis.po.TAdmDept;
import com.zhuozhuo.mis.po.TAdmRole;
import com.zhuozhuo.mis.po.TAdmUser;
import com.zhuozhuo.mis.service.TAdmUserService;
import com.zhuozhuo.mis.util.page.PageWraper;
import com.zhuozhuo.utils.GsonUtil;

public class TAdmUserServiceImpl implements TAdmUserService {
	private TAdmUserDao tadmUserDao;
	private CacheService cacheService;
	
	public PageWraper listTAdmUser(TAdmUser user)throws Exception{
		return this.tadmUserDao.listTAdmUser(user);
	}
	
	@Override
	public int updateUserStatus(TAdmUser user) throws Exception {
		/**
		 * 清缓存
		 */
		List<String> userIdList = this.tadmUserDao.getUserByDeptId(user.getDept().getId());
		for(String uid : userIdList){
			cacheService.delete("T_ADM_USER_"+uid);
		}
		
		return this.tadmUserDao.updateUserStatus(user);
	}
	
	public int saveUser(TAdmUser user) throws Exception{
		return this.tadmUserDao.saveUser(user);
	}

	public TAdmUser getUser(String id)throws Exception{
		Object cacheUser = cacheService.get("T_ADM_USER_"+id);
		TAdmUser user = null;
		if(cacheUser == null){
			user = this.tadmUserDao.getUser(id);
			TAdmDept dept = new TAdmDept();
			TAdmRole role = new TAdmRole();
			dept.setId(user.getDept().getId());
			dept.setParentname(user.getDept().getParentname());
			dept.setDepartmentname(user.getDept().getDepartmentname());
			role.setId(user.getRole().getId());
			role.setRoleName(user.getRole().getRoleName());
			
			user.setDept(dept);
			user.setRole(role);
			
			cacheService.put("T_ADM_USER_"+id, GsonUtil.toGson(user));
		}
		else{
			user = GsonUtil.fromGson(cacheUser.toString(),TAdmUser.class);
		}
		
		return user;		
	}
	
	public int editUser(TAdmUser user)throws Exception{
		int result = this.tadmUserDao.editUser(user);
		if(result>0)
			cacheService.put("T_ADM_USER_"+user.getId(), GsonUtil.toGson(user));
		
		return result;
	}
	
	public int updateStatusUser(String[] idArray,int flag)throws Exception{
		StringBuffer sbId = new StringBuffer();
		int i=0;
		for(String id : idArray){//组合修改状态的用户id
			sbId.append(id);
			if(i!=idArray.length-1)
				sbId.append(",");
			i++;
			
			/**
			 * 清空缓存
			 */
			cacheService.delete("T_ADM_USER_"+id);
		}
		
		if(sbId!=null && sbId.length()>0){
			//更新所有选中的模块状态
			TAdmUser user = new TAdmUser();
			user.setId(sbId.toString());
			user.setStatus(flag);
			
			if(flag==3)//删除
				return this.tadmUserDao.deleteUser(user.getId());
			
			return this.tadmUserDao.updateStatusUser(user);	
		}
		
		return 0;
	}
	
	public TAdmUser isLogin(TAdmUser user)throws Exception{
		return this.tadmUserDao.isLogin(user);
	}
	
	public TAdmUserDao getTadmUserDao() {
		return tadmUserDao;
	}

	public void setTadmUserDao(TAdmUserDao tadmUserDao) {
		this.tadmUserDao = tadmUserDao;
	}

	public CacheService getCacheService() {
		return cacheService;
	}

	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	@Override
	public List<String> getUserByDeptId(String deptid) throws Exception {
		return this.tadmUserDao.getUserByDeptId(deptid);
	}
	public List<TAdmUser> getUserList()throws Exception{
		return this.tadmUserDao.getUserList();
	}
}
