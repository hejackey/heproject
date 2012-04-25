package com.zhuozhuo.mis.service;

import java.util.List;

import com.zhuozhuo.mis.po.TAdmDept;
import com.zhuozhuo.mis.util.page.PageWraper;


public interface TAdmDeptService {
	public PageWraper listTAdmDept(TAdmDept model)throws Exception;
	public String getDeptParentName(String parentid) throws Exception;
	public int saveDept(TAdmDept model)throws Exception;
	public List<TAdmDept> getDeptTreeList()throws Exception;
	public TAdmDept getDept(String id)throws Exception;
	public int editDept(TAdmDept model)throws Exception;
	public void updateStatusDeptPatch(List<TAdmDept> listDept)throws Exception;
	public void updateStatusDept(String[] idArray,int ifuse)throws Exception;
	public List<String> getDeptSonId(String id)throws Exception;
	public List<TAdmDept> getDeptList()throws Exception;
	public TAdmDept getDeptById(String id)throws Exception;
}
