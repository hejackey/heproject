package com.zhuozhuo.mis.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhuozhuo.mis.common.BaseDao;
import com.zhuozhuo.mis.dao.ScmPayDao;
import com.zhuozhuo.mis.po.ScmPay;
import com.zhuozhuo.mis.po.ScmPayAndDetail;
import com.zhuozhuo.mis.po.ScmPayQ;
import com.zhuozhuo.mis.po.ScmStorageOutDetail;

public class ScmPayDaoImpl extends BaseDao implements ScmPayDao {

	@Override
	public List<ScmPay> listScmPays() {		
		List<ScmPay> list = getSqlMapClientTemplate().queryForList("scmPay.getAllScmPays");
		return list;
	}

	@Override
	public int saveScmPay(ScmPay scmPay) {		
		int id=(Integer)getSqlMapClientTemplate().insert("scmPay.insertscmPay",scmPay);		
		return id;
	}

	@Override
	public void deleteScmPays(String[] ids) {
		Map map=new HashMap();
		map.put("ids",ids);
		getSqlMapClientTemplate().delete("scmPay.deleteScmPays",map);	
	}
	public ScmPay getScmPay(String id)throws Exception{
		return (ScmPay)this.getSqlMapClientTemplate().queryForObject("scmPay.getScmPay",id);
	}
	public int editScmPayAndDetail(ScmPay scmPay)throws Exception{
		return this.getSqlMapClientTemplate().update("scmPay.editScmPayAndDetail",scmPay);
	}

	@Override
	public List<ScmPay> listScmPays(ScmPayQ scmPayQ) {
		System.out.println();
		List<ScmPay> list = getSqlMapClientTemplate().queryForList("scmPay.queryScmPays",scmPayQ);
		return list;
	}
}
