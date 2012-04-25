package com.zhuozhuo.mis.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhuozhuo.mis.common.BaseDao;
import com.zhuozhuo.mis.dao.ScmStorageInDetailDao;
import com.zhuozhuo.mis.po.AuditScmStorageOut;
import com.zhuozhuo.mis.po.ScmStorageIn;
import com.zhuozhuo.mis.po.ScmStorageInDetail;

public class ScmStorageInDetailDaoImpl  extends BaseDao  implements ScmStorageInDetailDao {
	
	/**
	 * 审核退货单
	 */
	public void auditTHD(String[] ids, List list) {
		for(int i=0;i<ids.length;i++){
			String masterId="";
			try{
				masterId=((Integer)list.get(i)).toString();
			}catch(Exception e){
				//
			}
			
			List<ScmStorageInDetail> scmStorageInDetailList = getScmStorageInDetailsByMasterId(ids[i]);
			for(int j=0;j<scmStorageInDetailList.size();j++){
				int id=((ScmStorageInDetail)scmStorageInDetailList.get(j)).getId();
				AuditScmStorageOut auditScmStorageOut=new AuditScmStorageOut();
				auditScmStorageOut.setId(new Integer(id).toString());
				auditScmStorageOut.setMasterId(masterId);
				Object o=getSqlMapClientTemplate().insert("scmStorageInDetail.auditTHD",auditScmStorageOut);				
			}
		}
	}

	/**
	 * 审核并流转
	 */
	@Override
	public void auditScmScmStorageIns(String[] ids,List list) {		
		for(int i=0;i<ids.length;i++){
			String masterId="";
			try{
				masterId=((Integer)list.get(i)).toString();
			}catch(Exception e){
				//
			}
			
			List<ScmStorageInDetail> scmStorageInDetailList = getScmStorageInDetailsByMasterId(ids[i]);
			for(int j=0;j<scmStorageInDetailList.size();j++){
				int id=((ScmStorageInDetail)scmStorageInDetailList.get(j)).getId();
				AuditScmStorageOut auditScmStorageOut=new AuditScmStorageOut();
				auditScmStorageOut.setId(new Integer(id).toString());
				auditScmStorageOut.setMasterId(masterId);
				Object o=getSqlMapClientTemplate().insert("scmStorageInDetail.auditScmStorageInDetail",auditScmStorageOut);				
			}
		}
	}


	@Override
	public void saveScmStorageInDetail(ScmStorageInDetail scmStorageInDetail) {
		getSqlMapClientTemplate().insert("scmStorageInDetail.insertScmStorageInDetail",scmStorageInDetail);		
	}

	@Override
	public void deleteScmStorageInDetails(String[] ids) {
		Map map=new HashMap();
		map.put("ids",ids);
		getSqlMapClientTemplate().delete("scmStorageInDetail.deleteScmStorageInDetails",map);		
	}

	
	
	/**
	 * 得到masterId得到每个记录的id
	 */
	
	public List<ScmStorageInDetail> getScmStorageInDetailsByMasterId(String id){
		List<ScmStorageInDetail> list = getSqlMapClientTemplate().queryForList("scmStorageInDetail.getScmStorageInDetailsByMasterId",id);		
		return list;
	}

	@Override
	public void deleteScmStorageInDetailByMasterId(int masterId) {
		getSqlMapClientTemplate().delete("scmStorageInDetail.deleteScmStorageInDetailByMasterId",masterId);
	}

	@Override
	public List<ScmStorageInDetail> getEditScmStorageInDetailByMasterId(String id, String type) {
		if(type.equals("enterWarehouse")){//入库单
			List<ScmStorageInDetail> list = getSqlMapClientTemplate().queryForList("scmStorageInDetail.getScmStorageInDetailsByMasterId",id);
			return list;
		}else{//退货单
			List<ScmStorageInDetail> list = getSqlMapClientTemplate().queryForList("scmStorageInDetail.getScmStorageInDetailsByMasterId1",id);
			return list;
		}	
	}	
}
