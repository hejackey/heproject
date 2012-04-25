package com.zhuozhuo.mis.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.zhuozhuo.b2c.memcached.service.CacheService;
import com.zhuozhuo.mis.dao.TAdmRoleDao;
import com.zhuozhuo.mis.po.TAdmRole;
import com.zhuozhuo.mis.service.TAdmRoleService;
import com.zhuozhuo.mis.util.page.PageWraper;
import com.zhuozhuo.utils.GsonUtil;
import com.zhuozhuo.utils.StringUtils;

public class TAdmRoleServiceImpl implements TAdmRoleService {
	private TAdmRoleDao tadmRoleDao;
	private CacheService cacheService;
	
	@Override
	public List<TAdmRole> getRoleList() throws Exception {
		return this.tadmRoleDao.getRoleList();
	}

	public TAdmRole getRole(String id)throws Exception{		
		return this.tadmRoleDao.getRole(id);
	}
	
	public int saveRole(TAdmRole role)throws Exception{
		String roleid = this.tadmRoleDao.getRoleSeq(); 
		role.setId(roleid);
		this.tadmRoleDao.saveRole(role);
		
		if(role.getModuleid()!=null && role.getModuleid().length>0)
			this.saveRolePriv(role);
		
		return	Integer.parseInt(roleid);		
	}
	
	public TAdmRoleDao getTadmRoleDao() {
		return tadmRoleDao;
	}

	public void setTadmRoleDao(TAdmRoleDao tadmRoleDao) {
		this.tadmRoleDao = tadmRoleDao;
	}

	public TAdmRole getRoleById(String id)throws Exception{
		Object cacheRole = cacheService.get("T_ADM_ROLE_"+id);
		TAdmRole role = null;
		if(cacheRole == null){
			role = this.tadmRoleDao.getRoleById(id);
		
			cacheService.put("T_ADM_ROLE_"+id, GsonUtil.toGson(role));
		}
		else{
			role = GsonUtil.fromGson(cacheRole.toString(),TAdmRole.class);
		}
		return role;
	}
	@Override
	public PageWraper listTAdmRole(TAdmRole role) throws Exception {
		return this.tadmRoleDao.listTAdmRole(role);
	}

	public int editRole(TAdmRole role)throws Exception{
		int result = this.tadmRoleDao.editRole(role);
		if(result>0)
			cacheService.put("T_ADM_ROLE_"+role.getId(), GsonUtil.toGson(role));
		
		if(role.getModuleid()!=null && role.getModuleid().length>0){
			role.setCreateBy("2");
			if(this.editRolePriv(role)<1){
				this.saveRolePriv(role);
			}
		}
		
		return result;		
	}

	public CacheService getCacheService() {
		return cacheService;
	}

	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	@Override
	public int updateStatusRole(String[] idArray,int flag) throws Exception {
		StringBuffer sbId = new StringBuffer();
		int i=0;
		for(String id : idArray){//组合修改状态的角色id
			sbId.append(id);
			if(i!=idArray.length-1)
				sbId.append(",");
			i++;
			
			/**
			 * 清空缓存
			 */
			cacheService.delete("T_ADM_ROLE_"+id);
		}
		
		if(sbId!=null && sbId.length()>0){
			//更新所有选中的模块状态
			TAdmRole role = new TAdmRole();
			role.setId(sbId.toString());
			role.setStatus(flag);
			
			if(flag==3){//删除
				this.tadmRoleDao.deleteRolePriv(role.getId());
				return this.tadmRoleDao.deleteRole(role.getId());
			}
			
			return this.tadmRoleDao.updateStatusRole(role);	
		}
		
		return 0;
	}

	@Override
	public int deleteRole(String id) throws Exception {
		return this.tadmRoleDao.deleteRole(id);
	}
	
	public int saveRolePriv(TAdmRole role)throws Exception{
		
		return this.tadmRoleDao.saveRolePriv(this.getRolePriv(role));		
	}
	
	public int editRolePriv(TAdmRole role)throws Exception{
		return this.tadmRoleDao.editRolePriv(this.getRolePriv(role));
	}
	
	public TAdmRole getRolePriv(TAdmRole role){
		StringBuffer sb = new StringBuffer();
		String[] rolePrivArray = role.getModuleid();
		int i=0;
		for(String rolePriv : rolePrivArray){
			sb.append(rolePriv);
			if(i!=rolePrivArray.length-1)
				sb.append("@");
			i++;
		}
		role.setRolePril(sb.toString());
		
		return role;
	}
	
	public List<String> getRolePrilByRoleId(String roleid)throws Exception{
		String rolePril = this.tadmRoleDao.getRolePrilByRoleId(roleid);
		List<String> rolePrilList = new ArrayList<String>();
		
		if(!StringUtils.isEmpty(rolePril)){
			String[] rolePrilArray = rolePril.split("@");
			for(String pril : rolePrilArray)
				rolePrilList.add(pril);
			
			return rolePrilList;
		}
		
		return null;
	}
}
