package com.zhuozhuo.mis.service;

import java.util.List;

import com.zhuozhuo.mis.po.ScmStorageOut;
import com.zhuozhuo.mis.po.ScmStorageOutAndDetail;
import com.zhuozhuo.mis.po.ScmStorageOutDetail;
import com.zhuozhuo.mis.po.ScmStorageOutQO;

public interface ScmStorageOutService {

	public List<ScmStorageOut> listScmStorageOuts();
	public List<ScmStorageOut> listScmStorageOuts(ScmStorageOutQO scmStorageOutQO);
	public void saveScmStorageOutAndDetail(ScmStorageOutAndDetail scmStorageOutAndDetail);
	public void deleteScmStorageOuts(String[] ids);
	public List<ScmStorageOutDetail> getDetailScmStorageOut(String id);
	public void auditScmStorageOut(String[] ids);
	public ScmStorageOut getScmStorageOut(String id)throws Exception;
	public int editScmStorageOut(ScmStorageOutAndDetail scmStorageOutAndDetail)throws Exception;
//	addScmStorageOut
//	editScmStorageOut
//	saveScmStorageOut
//	updateScmStorageOut
//	deleteScmStorageOuts
}
