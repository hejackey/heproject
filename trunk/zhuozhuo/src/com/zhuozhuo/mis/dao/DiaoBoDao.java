package com.zhuozhuo.mis.dao;

import java.util.List;

import com.zhuozhuo.mis.po.ScmDiaoBo;
import com.zhuozhuo.mis.util.page.PageWraper;

public interface DiaoBoDao {
	public String saveDiaoBo(ScmDiaoBo model);
	public PageWraper listDiaoBo(ScmDiaoBo model)throws Exception;
	public ScmDiaoBo getDiaoBo(String sheetid)throws Exception;
	public int editDiaoBo(ScmDiaoBo model)throws Exception;
	public int updateStatusDiaoBo(ScmDiaoBo DiaoBo)throws Exception;
	public List<String> exportDiaoBoToStorageIn(List<String> idList)throws Exception;
	public void exportDiaoBoDetailToStorageInDetail(List<String> idList,List<String> storageIdList)throws Exception;
	public void exportDiaoBoToScmPayDetail(String sheetids)throws Exception;
}
