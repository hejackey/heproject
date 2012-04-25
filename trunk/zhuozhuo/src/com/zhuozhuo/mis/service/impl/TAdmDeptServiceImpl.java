package com.zhuozhuo.mis.service.impl;

import java.util.List;

import com.zhuozhuo.b2c.memcached.service.CacheService;
import com.zhuozhuo.mis.dao.TAdmDeptDao;
import com.zhuozhuo.mis.po.TAdmDept;
import com.zhuozhuo.mis.po.TAdmUser;
import com.zhuozhuo.mis.service.TAdmDeptService;
import com.zhuozhuo.mis.service.TAdmUserService;
import com.zhuozhuo.mis.util.page.PageWraper;
import com.zhuozhuo.utils.GsonUtil;
import com.zhuozhuo.utils.StringUtils;


public class TAdmDeptServiceImpl implements TAdmDeptService {
	private TAdmDeptDao tadmDeptDao;
	private CacheService cacheService ;
	private TAdmUserService tadmUserService;
	
	@Override
	public PageWraper listTAdmDept(TAdmDept model)throws Exception {
		return tadmDeptDao.listTAdmDept(model);
	}

	public List<TAdmDept> getDeptTreeList()throws Exception{
		return this.tadmDeptDao.getDeptTreeList();
	}
	
	public String getDeptParentName(String parentid) throws Exception{
		List<String> parentNameList = this.tadmDeptDao.getDeptParentName(parentid);
		StringBuffer sb = new StringBuffer();
		int i=0;
		for(String pname : parentNameList){
			if(!"null".equals(pname)&&!StringUtils.isEmpty(pname)){
				sb.append(pname);
				
				if(i!=parentNameList.size()-1)
					sb.append("-");
				
				i++;
			}
		}
		
		return sb.toString();
	}
	
	public TAdmDept getDept(String id)throws Exception{
		Object cacheDept = cacheService.get("T_ADM_DET_"+id);
		TAdmDept dept = null;
		if(cacheDept == null){
			dept = this.tadmDeptDao.getDept(id);
			cacheService.put("T_ADM_DET_"+id, GsonUtil.toGson(dept));
		}
		else{
			dept = GsonUtil.fromGson(cacheDept.toString(),TAdmDept.class);
		}
		
		return dept;
	}
	
	public CacheService getCacheService() {
		return cacheService;
	}

	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	public int editDept(TAdmDept model)throws Exception{
		int result = this.tadmDeptDao.editDept(model);
		
		if(!model.getIfuse().equals(model.getHifuse())){//调用统一的状态更新接口
			String[] idArray = {model.getId()};
			this.updateStatusDept(idArray, model.getIfuse());
		}
		else
			cacheService.put("T_ADM_DET_"+model.getId(), GsonUtil.toGson(model));
		
		return result;
	}
	public int saveDept(TAdmDept model)throws Exception{
		return this.tadmDeptDao.saveDept(model);
	}
	
	public void updateStatusDept(String[] idArray,int ifuse)throws Exception{
		StringBuffer sbId = new StringBuffer();
		int i=0;
		for(String id : idArray){//组合修改状态的部门id
			sbId.append(id);
			if(i!=idArray.length-1)
				sbId.append(",");
			i++;
			
			//清缓存部门
			cacheService.delete("T_ADM_DET_"+id);			
		}
		
		if(sbId!=null && sbId.length()>0){
			//更新所有选中的部门状态及其下用户状态
			TAdmDept dept = new TAdmDept();
			dept.setId(sbId.toString());
			dept.setIfuse(ifuse);
			this.tadmDeptDao.updateStatusDept(dept);			
			this.updateUserStatusByDeptId(dept);
			
			//更新所有选中部门的子部门状态
			List<String > sonIdList = this.getDeptSonId(sbId.toString());//列出修改状态的部门所有子部门
			StringBuffer sbSonId = new StringBuffer();
			int j=0;
			for(String sonId : sonIdList){
				sbSonId.append(sonId);
				if(j!=sonIdList.size()-1)
					sbSonId.append(",");
				j++;
				
				//清缓存部门
				cacheService.delete("T_ADM_DET_"+sonId);
			}
			
			if(sbSonId!=null && sbSonId.length()>0){
				dept.setId(sbSonId.toString());
				dept.setIfuse(ifuse);
				this.tadmDeptDao.updateStatusDept(dept);
			}
			
			//更新部门下的会员状态
			this.updateUserStatusByDeptId(dept);
		}
		
		
	}
	
	public List<String> getDeptSonId(String id)throws Exception{
		return this.tadmDeptDao.getDeptSonId(id);
	}
	
	public int updateUserStatusByDeptId(TAdmDept dept)throws Exception{
		TAdmUser user = new TAdmUser();
		user.setDept(dept);
		if(dept.getIfuse()==2)
			user.setStatus(2);
		if(dept.getIfuse()==1)
			user.setStatus(1);
		
		return tadmUserService.updateUserStatus(user);
	}
	
	public List<TAdmDept> getDeptList()throws Exception{
		return this.tadmDeptDao.getDeptList();
	}
	
	public TAdmDeptDao getTadmDeptDao() {
		return tadmDeptDao;
	}

	public void setTadmDeptDao(TAdmDeptDao tadmDeptDao) {
		this.tadmDeptDao = tadmDeptDao;
	}

	@Override
	public void updateStatusDeptPatch(List<TAdmDept> listDept) throws Exception {
		
	}

	public TAdmUserService getTadmUserService() {
		return tadmUserService;
	}

	public void setTadmUserService(TAdmUserService tadmUserService) {
		this.tadmUserService = tadmUserService;
	}

	@Override
	public TAdmDept getDeptById(String id) throws Exception {
		return this.tadmDeptDao.getDeptById(id);
	}

}
