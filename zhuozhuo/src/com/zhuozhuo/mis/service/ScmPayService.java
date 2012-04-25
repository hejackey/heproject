package com.zhuozhuo.mis.service;

import java.util.List;

import com.zhuozhuo.mis.po.ScmPay;
import com.zhuozhuo.mis.po.ScmPayAndDetail;
import com.zhuozhuo.mis.po.ScmPayDetail;
import com.zhuozhuo.mis.po.ScmPayQ;
import com.zhuozhuo.mis.po.ScmStorageOutDetail;

public interface ScmPayService {
	public List<ScmPay>  listScmPays();
	public List<ScmPay>  listScmPays(ScmPayQ scmPayQ);
	
	public void saveScmPayAndDetail(ScmPayAndDetail scmPayAndDetail)throws Exception;
	public int editScmPayAndDetail(ScmPayAndDetail scmPayAndDetail)throws Exception;
	public void deleteScmPays(String[] ids);
	public List<ScmPayDetail> showScmPayDetail(String id);
	public ScmPay getScmPay(String id)throws Exception;
//	addScmPay
//	editScmPay
//	saveScmPay
//	updateScmPay
//	deleteScmPays
}
