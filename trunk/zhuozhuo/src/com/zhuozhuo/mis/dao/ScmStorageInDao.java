package com.zhuozhuo.mis.dao;

import java.util.List;

import com.zhuozhuo.mis.po.ScmStorageIn;
import com.zhuozhuo.mis.po.ScmStorageInQ;

public interface ScmStorageInDao {
	public List auditTHD(String[]ids);
	public List<ScmStorageIn> listScmStorageIns(String type);
	public List<ScmStorageIn> listScmStorageIns(String type,ScmStorageInQ scmStorageInQ);
	public int saveScmStorageIn(ScmStorageIn scmStorageIn);
	public void deleteScmScmStorageIns(String[] ids);
	public List auditScmScmStorageIns(String[] ids);
	public ScmStorageIn getScmStorageInById(String id,String type);
	public void updateScmStorageIn(ScmStorageIn scmStorageIn);
}
