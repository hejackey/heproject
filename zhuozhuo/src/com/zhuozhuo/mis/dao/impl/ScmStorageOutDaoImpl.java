package com.zhuozhuo.mis.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhuozhuo.mis.common.BaseDao;
import com.zhuozhuo.mis.dao.ScmStorageOutDao;
import com.zhuozhuo.mis.po.AuditScmStorageOut;
import com.zhuozhuo.mis.po.PProductType;
import com.zhuozhuo.mis.po.ScmStorageOut;
import com.zhuozhuo.mis.po.ScmStorageOutQO;

public class ScmStorageOutDaoImpl extends BaseDao implements ScmStorageOutDao {
	
	public List<ScmStorageOut> listScmStorageOuts(ScmStorageOutQO scmStorageOutQO) {		
		List<ScmStorageOut> list = getSqlMapClientTemplate().queryForList("scmStorageOut.queryScmStorageOuts",scmStorageOutQO);
		return list;
	}
	
	@Override
	public int  saveScmStorageOut(ScmStorageOut scmStorageOut) {		
		int id=(Integer)getSqlMapClientTemplate().insert("scmStorageOut.insertScmStorageOut",scmStorageOut);		
		return id;
	}

	@Override
	public List<ScmStorageOut> listScmStorageOuts() {		
		List<ScmStorageOut> list = getSqlMapClientTemplate().queryForList("scmStorageOut.getAllScmStorageOuts");
		return list;
	}

	@Override
	public void deleteScmStorageOuts(String[] ids) {
		Map map=new HashMap();
		map.put("ids",ids);
		getSqlMapClientTemplate().delete("scmStorageOut.deleteScmStorageOuts",map);	
	}

	/**
	 * 审核并流转
	 */
	@Override
	public List auditScmStorageOut(String[] ids) {
		List list=new ArrayList();
		for(int i=0;i<ids.length;i++){
			AuditScmStorageOut auditScmStorageOut=new AuditScmStorageOut();
			auditScmStorageOut.setId(ids[i]);
			int masterId=(Integer)getSqlMapClientTemplate().insert("scmStorageOut.auditScmStorageOut",auditScmStorageOut);
			list.add(masterId);
			getSqlMapClientTemplate().update("scmStorageOut.updateScmStorageOut",ids[i]);
		}
		return list;
	}
	
	public ScmStorageOut getScmStorageOut(String id)throws Exception{
		return (ScmStorageOut)this.getSqlMapClientTemplate().queryForObject("scmStorageOut.getScmStorageOut",id);
	}
	
	public int editScmStorageOut(ScmStorageOut scmStorageOut)throws Exception{
		return this.getSqlMapClientTemplate().update("scmStorageOut.editScmStorageOut",scmStorageOut);
	}
}
