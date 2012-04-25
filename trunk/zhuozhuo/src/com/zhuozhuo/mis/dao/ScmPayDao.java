package com.zhuozhuo.mis.dao;

import java.util.List;

import com.zhuozhuo.mis.po.ScmPay;
import com.zhuozhuo.mis.po.ScmPayAndDetail;
import com.zhuozhuo.mis.po.ScmPayQ;

public interface ScmPayDao {
	public List<ScmPay>  listScmPays();
	public List<ScmPay>  listScmPays(ScmPayQ scmPayQ);
	
	public int  saveScmPay(ScmPay scmPay);
	public void deleteScmPays(String[] ids);
	public ScmPay getScmPay(String id)throws Exception;
	public int editScmPayAndDetail(ScmPay scmPay)throws Exception;
//	addScmPay
//	editScmPay
//	saveScmPay
//	updateScmPay
//	deleteScmPays
}
