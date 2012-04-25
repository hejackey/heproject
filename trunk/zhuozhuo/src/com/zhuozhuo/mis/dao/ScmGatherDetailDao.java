package com.zhuozhuo.mis.dao;

import java.util.List;

import com.zhuozhuo.mis.po.ScmGatherDetail;

public interface ScmGatherDetailDao {
	public void saveScmGatherDetail(List<ScmGatherDetail> detailList)throws Exception;
	public List<ScmGatherDetail> getScmGatherDetailBySheetId(String sheetid)throws Exception;
	public int deleteScmGatherDetailBySheetid(String sheetid)throws Exception;
}
