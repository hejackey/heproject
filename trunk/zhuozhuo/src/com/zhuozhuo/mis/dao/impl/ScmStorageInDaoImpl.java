package com.zhuozhuo.mis.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhuozhuo.mis.common.BaseDao;
import com.zhuozhuo.mis.dao.ScmStorageInDao;
import com.zhuozhuo.mis.po.AuditScmStorageOut;
import com.zhuozhuo.mis.po.PProvider;
import com.zhuozhuo.mis.po.ScmStorageIn;
import com.zhuozhuo.mis.po.ScmStorageInQ;
import com.zhuozhuo.utils.Constants;

public class ScmStorageInDaoImpl extends BaseDao implements ScmStorageInDao {
	
	@Override
	public List auditTHD(String[] ids) {		
		List list=new ArrayList();
		for(int i=0;i<ids.length;i++){
			AuditScmStorageOut auditScmStorageOut=new AuditScmStorageOut();
			auditScmStorageOut.setId(ids[i]);
			int id=(Integer)getSqlMapClientTemplate().insert("scmStorageIn.auditTHD",auditScmStorageOut);
			list.add(id);
			getSqlMapClientTemplate().update("scmStorageIn.updateScmStorageIn",ids[i]);			
		}
		return list;
	}

	@Override
	public List<ScmStorageIn> listScmStorageIns(String type) {
		if(type.equals(Constants.ENTERWAREHOUSE)){//入库单
			List<ScmStorageIn> list = getSqlMapClientTemplate().queryForList("scmStorageIn.getScmStorageIns");		
			return list;
		}else{//退货单
			List<ScmStorageIn> list = getSqlMapClientTemplate().queryForList("scmStorageIn.getScmStorageIns1");		
			return list;			
		}		
	}

	@Override
	public int saveScmStorageIn(ScmStorageIn scmStorageIn) {		
		int id=(Integer)getSqlMapClientTemplate().insert("scmStorageIn.insertScmStorageIn",scmStorageIn);		
		return id;
	}

	@Override
	public void deleteScmScmStorageIns(String[] ids) {
		Map map=new HashMap();
		map.put("ids",ids);
		getSqlMapClientTemplate().delete("scmStorageIn.deleteScmStorageIns",map);		
	}

	/**
	 * 审核并流转
	 */
	@Override
	public List auditScmScmStorageIns(String[] ids) {
		List list=new ArrayList();
		for(int i=0;i<ids.length;i++){
			AuditScmStorageOut auditScmStorageOut=new AuditScmStorageOut();
			auditScmStorageOut.setId(ids[i]);
			int id=(Integer)getSqlMapClientTemplate().insert("scmStorageIn.auditScmStorageIn",auditScmStorageOut);
			list.add(id);
			getSqlMapClientTemplate().update("scmStorageIn.updateScmStorageIn",ids[i]);			
		}
		return list;
	}

	@Override
	public ScmStorageIn getScmStorageInById(String id,String type) {
		if(type.equals("enterWarehouse")){//入库单
			return (ScmStorageIn)getSqlMapClientTemplate().queryForObject("scmStorageIn.getScmStorageIn",id);
		}else{//退货单
			return (ScmStorageIn)getSqlMapClientTemplate().queryForObject("scmStorageIn.getScmStorageIn1",id);
		}		
	}

	/**
	 * 更新表SCM_STORAGE_IN
	 */
	@Override
	public void updateScmStorageIn(ScmStorageIn scmStorageIn) {		
		getSqlMapClientTemplate().update("scmStorageIn.updateScmStorageIn1", scmStorageIn);
	}

	@Override
	public List<ScmStorageIn> listScmStorageIns(String type,ScmStorageInQ scmStorageInQ) {
		if(type.equals(Constants.ENTERWAREHOUSE)){//入库单
			List<ScmStorageIn> list = getSqlMapClientTemplate().queryForList("scmStorageIn.queryScmStorageIns",scmStorageInQ);		
			return list;
		}else{//退货单			
			List<ScmStorageIn> list = getSqlMapClientTemplate().queryForList("scmStorageIn.queryScmStorageIns1",scmStorageInQ);		
			return list;			
		}
	}

	
}
