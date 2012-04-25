package com.zhuozhuo.mis.dao;

import java.util.List;

import com.zhuozhuo.mis.po.ScmGather;
import com.zhuozhuo.mis.util.page.PageWraper;

public interface ScmGatherDao {
	public String saveScmGather(ScmGather model);
	public PageWraper listScmGather(ScmGather model)throws Exception;
	public ScmGather getScmGather(String sheetid)throws Exception;
	public int editScmGather(ScmGather model)throws Exception;
	public int updateStatusScmGather(ScmGather stockOrder)throws Exception;
	public List<String> exportScmGatherToStorageIn(List<String> idList)throws Exception;
	public void exportScmGatherDetailToStorageInDetail(List<String> idList,List<String> storageIdList)throws Exception;
}
