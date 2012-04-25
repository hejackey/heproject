package com.zhuozhuo.mis.dao;

import java.util.List;

import com.zhuozhuo.mis.po.ScmStorageOut;
import com.zhuozhuo.mis.po.ScmStorageOutQO;

public interface ScmStorageOutDao {
	public List<ScmStorageOut> listScmStorageOuts();
	public List<ScmStorageOut> listScmStorageOuts(ScmStorageOutQO scmStorageOutQO);
	public ScmStorageOut getScmStorageOut(String id)throws Exception;
	public int editScmStorageOut(ScmStorageOut scmStorageOut)throws Exception;
	public int saveScmStorageOut(ScmStorageOut scmStorageOut);
	public void deleteScmStorageOuts(String[] ids);
	public List auditScmStorageOut(String[] ids);	
//	addScmStorageOut
//	editScmStorageOut
//	saveScmStorageOut
//	updateScmStorageOut
//	deleteScmStorageOuts

}
