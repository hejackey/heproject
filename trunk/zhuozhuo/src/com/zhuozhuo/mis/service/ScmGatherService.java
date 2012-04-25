package com.zhuozhuo.mis.service;

import java.util.List;

import com.zhuozhuo.mis.po.ScmGather;
import com.zhuozhuo.mis.util.page.PageWraper;

public interface ScmGatherService {
	public String saveScmGather(ScmGather model)throws Exception;
	
	public PageWraper listScmGather(ScmGather model)throws Exception;
	public ScmGather getScmGather(String sheetid)throws Exception;
	public int editScmGather(ScmGather model)throws Exception;
	public int updateStatusScmGather(String[] asheetid,int flag)throws Exception;
	public List<String> exportScmGatherToStorageIn(List<String> sheetidList)throws Exception;
	public void exportScmGatherDetailToStorageInDetail(List<String> idList,List<String> storageIdList)throws Exception;
}
