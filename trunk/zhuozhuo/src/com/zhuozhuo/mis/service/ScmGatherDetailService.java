package com.zhuozhuo.mis.service;

import java.util.List;

import com.zhuozhuo.mis.po.ScmGatherDetail;

public interface ScmGatherDetailService {
	public void saveScmGatherDetail(List<ScmGatherDetail> detailList)throws Exception;
	public List<ScmGatherDetail> getScmGatherDetailBySheetId(String sheetid)throws Exception;
	public int deleteScmGatherDetailBySheetid(String sheetid)throws Exception;
}
