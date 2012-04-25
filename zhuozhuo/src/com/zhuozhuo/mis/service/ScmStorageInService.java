package com.zhuozhuo.mis.service;

import java.util.List;

import com.zhuozhuo.mis.po.ScmStorageIn;
import com.zhuozhuo.mis.po.ScmStorageInAndDetail;
import com.zhuozhuo.mis.po.ScmStorageInDetail;
import com.zhuozhuo.mis.po.ScmStorageInQ;

public interface ScmStorageInService {
	/**
	 * 审核退货单
	 * @param ids
	 */
	public void auditTHD(String[] ids);
	
	public List<ScmStorageIn> listScmStorageIns(String type);
	public List<ScmStorageIn> listScmStorageIns(String type,ScmStorageInQ scmStorageInQ);
	/**
	 * 保存一个入库单和明细表内容
	 * @param scmStorageInAndDetail
	 */
	public void saveScmStorageInAndDetail(ScmStorageInAndDetail scmStorageInAndDetail,String type);
	
	/**
	 * 修改一个入库单和明细表内容
	 * @param scmStorageInAndDetail
	 * @param type
	 */
	public void updateScmScmStorageIn(ScmStorageInAndDetail scmStorageInAndDetail,String type);
	
	/**
	 * 删除入库单和其对应的明细表的内容
	 * @param ids
	 */
	public void deleteScmScmStorageIns(String[] ids);
	
	/**
	 * 审核并流转
	 */
	public void auditScmScmStorageIns(String[] ids);
	/**
	 * 列出对应id的明细表的内容
	 */
	public List<ScmStorageInDetail> showDetailScmStorageIn(String id);
	
	/**
	 * 根据id得到ScmStorageIn记录
	 * @param id
	 * @return
	 */
	public ScmStorageIn getScmStorageInById(String id,String type);
	
	/**
	 * 根据masterId得到ScmStorageInDetail记录数据
	 */
	public List<ScmStorageInDetail> getScmStorageInDetailBymasterId(String masterId);
	
	public List<ScmStorageInDetail> getEditScmStorageInDetailByMasterId(String id,String type);
}
