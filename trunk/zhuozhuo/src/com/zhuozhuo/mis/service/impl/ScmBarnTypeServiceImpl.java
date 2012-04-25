package com.zhuozhuo.mis.service.impl;

import java.util.List;

import com.zhuozhuo.b2c.memcached.service.CacheService;
import com.zhuozhuo.mis.common.BaseService;
import com.zhuozhuo.mis.dao.ScmBarnTypeDao;
import com.zhuozhuo.mis.po.ScmBarnType;
import com.zhuozhuo.mis.service.ScmBarnTypeService;
import com.zhuozhuo.utils.GsonUtil;
import com.zhuozhuo.utils.StringUtils;

public class ScmBarnTypeServiceImpl  extends BaseService  implements ScmBarnTypeService {
	private ScmBarnTypeDao scmBarnTypeDao;
	private CacheService cacheService;
	
	public List<ScmBarnType> getBarnTypeList()throws Exception{
		return this.scmBarnTypeDao.getBarnTypeList();
	}

	public ScmBarnType getBarnTypeById(String id)throws Exception{
		Object cacheBarnType = cacheService.get("SCM_BARN_TYPE_"+id);
		ScmBarnType barnType = null;
		if(cacheBarnType == null){
			barnType = this.scmBarnTypeDao.getBarnTypeById(id);
			cacheService.put("SCM_BARN_TYPE_"+id, GsonUtil.toGson(barnType));
		}
		else{
			barnType = GsonUtil.fromGson(cacheBarnType.toString(),ScmBarnType.class);
		}
		
		return barnType;
	}
	
	public String getBarnTypeSonId(String id)throws Exception{
		if(StringUtils.isEmpty(id)){
			return "";
		}
		List<String> sonIdList = this.scmBarnTypeDao.getBarnTypeSonId(id);
		StringBuffer sb = new StringBuffer();
		for(String sonId : sonIdList){
			sb.append(sonId);
			sb.append(",");
		}
		
		if(sb !=null && sb.length()>0){
			sb.append(id);
			return sb.toString();
		}
		
		return id;
	}
	
	public ScmBarnTypeDao getScmBarnTypeDao() {
		return scmBarnTypeDao;
	}

	public void setScmBarnTypeDao(ScmBarnTypeDao scmBarnTypeDao) {
		this.scmBarnTypeDao = scmBarnTypeDao;
	}

	public CacheService getCacheService() {
		return cacheService;
	}

	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	@Override
	public void deleteScmBarnType(String[] ids) {
		scmBarnTypeDao.deleteScmBarnType(ids);
	}

	@Override
	public List<ScmBarnType> getAllScmBarnTypes() {		
		return scmBarnTypeDao.getAllScmBarnTypes();		
	}

	@Override
	public void saveScmBarnType(ScmBarnType scmBarnType) {
		scmBarnTypeDao.saveScmBarnType(scmBarnType);
	}

	@Override
	public void updateScmBarnType(ScmBarnType scmBarnType) {
		scmBarnTypeDao.updateScmBarnType(scmBarnType);
	}

	@Override
	public List<ScmBarnType> getChildScmBarnTypes(String id) {		
		return scmBarnTypeDao.getChildScmBarnTypes(id);
	}

	@Override
	public ScmBarnType getScmBarnType(String id) {
		return scmBarnTypeDao.getScmBarnType(id);
	}
}
