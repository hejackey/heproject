package com.zhuozhuo.mis.service.impl;

import java.util.List;

import com.zhuozhuo.b2c.memcached.service.CacheService;
import com.zhuozhuo.mis.dao.TAdmModuleDao;
import com.zhuozhuo.mis.po.TAdmModule;
import com.zhuozhuo.mis.service.TAdmModuleService;
import com.zhuozhuo.mis.util.page.PageWraper;
import com.zhuozhuo.utils.GsonUtil;

public class TAdmModuleServiceImpl implements TAdmModuleService {
	private TAdmModuleDao tadmModuleDao;
	private CacheService cacheService;
	
	@Override
	public List<String> getModuleParentName(String parentid) {
		return tadmModuleDao.getModuleParentName(parentid);
	}

	public int saveModule(TAdmModule module)throws Exception{
		return this.tadmModuleDao.saveModule(module);
	}
	
	public PageWraper listTAdmModule(TAdmModule module)throws Exception{
		return this.tadmModuleDao.listTAdmModule(module);
	}
	
	public List<TAdmModule> getModuleTreeList()throws Exception{
		return this.tadmModuleDao.getModuleTreeList();
	}
	
	public TAdmModule getModule(String id)throws Exception{
		Object cacheModule = cacheService.get("T_ADM_MODULE_"+id);
		TAdmModule module = null;
		if(cacheModule == null){
			module = this.tadmModuleDao.getModule(id);
			cacheService.put("T_ADM_MODULE_"+id, GsonUtil.toGson(module));
		}
		else{
			module = GsonUtil.fromGson(cacheModule.toString(),TAdmModule.class);
		}
		
		return module;		
	}
	
	public int editModule(TAdmModule module)throws Exception{
		int result = this.tadmModuleDao.editModule(module);
		
		if(module.getIfuse()!= module.getHifuse()){//调用统一的状态更新接口
			String[] idArray = {module.getId()};
			this.updateStatusModule(idArray, module.getIfuse());
		}
		else
			cacheService.put("T_ADM_MODULE_"+module.getId(), GsonUtil.toGson(module));
		
		return result;
	}
	
	public void updateStatusModule(String[] idArray,int ifuse)throws Exception{
		StringBuffer sbId = new StringBuffer();
		int i=0;
		for(String id : idArray){//组合修改状态的模块id
			sbId.append(id);
			if(i!=idArray.length-1)
				sbId.append(",");
			i++;
			
			//清缓存
			cacheService.delete("T_ADM_MODULE_"+id);
		}
		
		if(sbId!=null && sbId.length()>0){
			//更新所有选中的模块状态
			TAdmModule module = new TAdmModule();
			module.setId(sbId.toString());
			module.setIfuse(ifuse);
			this.tadmModuleDao.updateStatusModule(module);			
			
			//更新所有选中部门的子模块状态
			List<String > sonIdList = this.getModuleSonId(sbId.toString());//列出修改状态的模块所有子模块
			StringBuffer sbSonId = new StringBuffer();
			int j=0;
			for(String sonId : sonIdList){
				sbSonId.append(sonId);
				if(j!=sonIdList.size()-1)
					sbSonId.append(",");
				j++;
				
				//清缓存
				cacheService.delete("T_ADM_MODULE_"+sonId);
			}
			
			if(sbSonId!=null && sbSonId.length()>0){
				module.setId(sbSonId.toString());
				module.setIfuse(ifuse);
				this.tadmModuleDao.updateStatusModule(module);
			}			
		}		
	}
	public List<String> getModuleSonId(String id)throws Exception{
		return this.tadmModuleDao.getModuleSonId(id);
	}
	
	public TAdmModuleDao getTadmModuleDao() {
		return tadmModuleDao;
	}

	public void setTadmModuleDao(TAdmModuleDao tadmModuleDao) {
		this.tadmModuleDao = tadmModuleDao;
	}

	public CacheService getCacheService() {
		return cacheService;
	}

	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}

}
