package com.zhuozhuo.mis.dao;

import java.util.List;


import com.zhuozhuo.mis.po.ScmDiaoBoDetail;

public interface DiaoBoDetailDao {
	public void saveDiaoBoDetail(List<ScmDiaoBoDetail> detailList)throws Exception;
	public List<ScmDiaoBoDetail> getDiaoBoDetailBySheetId(String sheetid)throws Exception;
	public int deleteDiaoBoDetailBySheetid(String sheetid)throws Exception;
}
