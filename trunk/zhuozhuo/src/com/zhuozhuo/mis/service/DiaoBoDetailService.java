package com.zhuozhuo.mis.service;

import java.util.List;

import com.zhuozhuo.mis.po.ScmDiaoBoDetail;
import com.zhuozhuo.mis.po.ScmStockOrderDetail;

public interface DiaoBoDetailService {
	public void saveDiaoBoDetail(List<ScmDiaoBoDetail> detailList)throws Exception;
	public List<ScmDiaoBoDetail> getDiaoBoDetailBySheetId(String sheetid)throws Exception;
	public int deleteDiaoBoDetailBySheetid(String sheetid)throws Exception;
}
