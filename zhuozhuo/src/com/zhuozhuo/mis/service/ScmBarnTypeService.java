package com.zhuozhuo.mis.service;

import java.util.List;

import com.zhuozhuo.mis.po.ScmBarnType;

public interface ScmBarnTypeService {
	/**
	 * 得到所有的仓库类别
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ScmBarnType> getBarnTypeList() throws Exception;

	/**
	 * 根据id得到仓库类别
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ScmBarnType getBarnTypeById(String id) throws Exception;

	/**
	 * 得到所有的仓库类别
	 * 
	 * @return
	 */
	public List<ScmBarnType> getAllScmBarnTypes();

	/**
	 * 保存仓库类别
	 * 
	 * @param scmBarnType
	 */
	public void saveScmBarnType(ScmBarnType scmBarnType);

	/**
	 * 更新仓库类别
	 * 
	 * @param scmBarnType
	 */
	public void updateScmBarnType(ScmBarnType scmBarnType);

	/**
	 * 
	 * @param ids
	 */
	public void deleteScmBarnType(String[] ids);

	/**
	 * 得到某个仓库的所有的子孩子
	 * 
	 * @param id
	 * @return
	 */
	public List<ScmBarnType> getChildScmBarnTypes(String id);

	/**
	 * 根据id得到仓库类别
	 * 
	 * @param id
	 * @return
	 */
	public ScmBarnType getScmBarnType(String id);
	
	public String getBarnTypeSonId(String id)throws Exception;
}
