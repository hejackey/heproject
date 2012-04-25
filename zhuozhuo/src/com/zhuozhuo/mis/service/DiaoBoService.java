package com.zhuozhuo.mis.service;

import java.util.List;

import com.zhuozhuo.mis.po.ScmDiaoBo;
import com.zhuozhuo.mis.po.ScmStockOrder;
import com.zhuozhuo.mis.util.page.PageWraper;

public interface DiaoBoService {
	public String saveDiaoBo(ScmDiaoBo model)throws Exception;
	
	public PageWraper listDiaoBo(ScmDiaoBo model)throws Exception;
	public ScmDiaoBo getDiaoBo(String sheetid)throws Exception;
	public int editDiaoBo(ScmDiaoBo model)throws Exception;
	public int updateStatusDiaoBo(String[] asheetid,int flag)throws Exception;
	public List<String> exportDiaoBoToStorageIn(List<String> sheetidList)throws Exception;
	public void exportDiaoBoDetailToStorageInDetail(List<String> idList,List<String> storageIdList)throws Exception;
}
