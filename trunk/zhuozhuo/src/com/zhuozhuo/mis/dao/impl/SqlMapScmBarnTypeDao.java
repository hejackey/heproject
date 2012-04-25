package com.zhuozhuo.mis.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.zhuozhuo.mis.dao.ScmBarnTypeDao;
import com.zhuozhuo.mis.po.PProductType;
import com.zhuozhuo.mis.po.ScmBarnType;

public class SqlMapScmBarnTypeDao extends SqlMapClientDaoSupport implements
		ScmBarnTypeDao {

	@Override
	public List<ScmBarnType> getBarnTypeList() throws Exception {
		return this.getSqlMapClientTemplate().queryForList("scmbarntype.getBarnTypeList");
	}

	public ScmBarnType getBarnTypeById(String id)throws Exception{
		return (ScmBarnType)this.getSqlMapClientTemplate().queryForObject("scmbarntype.getBarnTypeById",id);
	}

	/**
	 * 删除一条或多条仓库记录
	 */
	@Override
	public void deleteScmBarnType(String[] ids) {
		Map map=new HashMap();
		map.put("ids",ids);
		getSqlMapClientTemplate().delete("scmbarntype.deleteScmBarnTypes",map);	
	}

	@Override
	public List<ScmBarnType> getAllScmBarnTypes() {
		List<ScmBarnType> list = getSqlMapClientTemplate().queryForList("scmbarntype.getAllScmBarnTypes");		
		return list;
	}

	@Override
	public void saveScmBarnType(ScmBarnType scmBarnType) {
		getSqlMapClientTemplate().insert("scmbarntype.insertScmBarnType",scmBarnType);			
	}

	@Override
	public void updateScmBarnType(ScmBarnType scmBarnType) {
		getSqlMapClientTemplate().update("scmbarntype.updateScmBarnType",scmBarnType);		
	}

	@Override
	public List<ScmBarnType> getChildScmBarnTypes(String id) {
		List<ScmBarnType> list = getSqlMapClientTemplate().queryForList("scmbarntype.getChildScmBarnTypes",id);		
		return list;
	}

	@Override
	public ScmBarnType getScmBarnType(String id) {
		ScmBarnType scmBarnType = (ScmBarnType)getSqlMapClientTemplate().queryForObject("scmbarntype.getScmBarnType",id);		
		return scmBarnType;
	}
	
	public List<String> getBarnTypeSonId(String id)throws Exception{
		return this.getSqlMapClientTemplate().queryForList("scmbarntype.getBarnTypeSonId",id);
	}
}
