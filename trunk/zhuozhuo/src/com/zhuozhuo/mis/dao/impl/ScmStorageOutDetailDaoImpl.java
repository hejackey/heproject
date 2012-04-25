package com.zhuozhuo.mis.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhuozhuo.mis.common.BaseDao;
import com.zhuozhuo.mis.dao.ScmStorageOutDetailDao;
import com.zhuozhuo.mis.po.AuditScmStorageOut;
import com.zhuozhuo.mis.po.ScmStorageOut;
import com.zhuozhuo.mis.po.ScmStorageOutDetail;

public class ScmStorageOutDetailDaoImpl extends BaseDao implements ScmStorageOutDetailDao {
	
	@Override
	public List<ScmStorageOutDetail> getDetailScmStorageOut(String id) {
		List<ScmStorageOutDetail> list = getSqlMapClientTemplate().queryForList("scmStorageOutDetail.getDetailScmStorageOut",id);
		return list;
	}

	@Override
	public List<ScmStorageOutDetail> listScmStorageOutDetails() {		
		List<ScmStorageOutDetail> list = getSqlMapClientTemplate().queryForList("scmStorageOutDetail.getAllScmStorageOutDetails");
		return list;
	}

	@Override
	public void saveScmStorageOutDetail(ScmStorageOutDetail scmStorageOutDetail) {
		getSqlMapClientTemplate().insert("scmStorageOutDetail.insertScmStorageOutDetail",scmStorageOutDetail);		
	}

	@Override
	public void deleteScmStorageOutDetails(String[] ids) {
		Map map=new HashMap();
		map.put("ids",ids);
		getSqlMapClientTemplate().delete("scmStorageOutDetail.deleteScmStorageOutDetails",map);	
	}

	/**
	 * 审核并流转
	 */
	@Override
	public void auditScmStorageOut(String[] ids,List list) {
		for(int i=0;i<ids.length;i++){	
			String masterId="";
			try{				
				masterId=((Integer)list.get(i)).toString();				
			}catch(Exception e){
				//
			}
			
			List<ScmStorageOutDetail> scmStorageOutDetailList = getScmStorageDetailByMasterId(ids[i]);
			for(int j=0;j<scmStorageOutDetailList.size();j++){
				ScmStorageOutDetail scmStorageOutDetail=scmStorageOutDetailList.get(j);
				Integer id=scmStorageOutDetail.getId();
				AuditScmStorageOut auditScmStorageOut=new AuditScmStorageOut();
				auditScmStorageOut.setId(id.toString());
				auditScmStorageOut.setMasterId(masterId);
				getSqlMapClientTemplate().insert("scmStorageOutDetail.auditScmStorageDetail",auditScmStorageOut);	
			}						
		}
	}
	/**
	 * 根据masterId得到明细记录数据
	 */
	public List<ScmStorageOutDetail> getScmStorageDetailByMasterId(String id){
		List<ScmStorageOutDetail> list = getSqlMapClientTemplate().queryForList("scmStorageOutDetail.getScmStorageDetailByMasterId",id);
		return list;
	}
	
	public void deleteScmStorageOutDetail(String masterId)throws Exception{
		this.getSqlMapClientTemplate().delete("scmStorageOutDetail.deleteScmStorageOutDetail",masterId);
	}
}
