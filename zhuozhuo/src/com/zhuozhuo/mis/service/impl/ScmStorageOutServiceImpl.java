package com.zhuozhuo.mis.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.zhuozhuo.mis.common.BaseService;
import com.zhuozhuo.mis.dao.ScmStorageOutDao;
import com.zhuozhuo.mis.dao.ScmStorageOutDetailDao;
import com.zhuozhuo.mis.po.ScmStorageOut;
import com.zhuozhuo.mis.po.ScmStorageOutAndDetail;
import com.zhuozhuo.mis.po.ScmStorageOutDetail;
import com.zhuozhuo.mis.po.ScmStorageOutQO;
import com.zhuozhuo.mis.po.TAdmDept;
import com.zhuozhuo.mis.service.ScmStorageOutService;
import com.zhuozhuo.mis.service.TAdmDeptService;
import com.zhuozhuo.utils.GenerateSheetIdUtil;

public class ScmStorageOutServiceImpl  extends BaseService  implements ScmStorageOutService {
	private ScmStorageOutDao scmStorageOutDao;
	private ScmStorageOutDetailDao scmStorageOutDetailDao;
	

	@Override
	public void saveScmStorageOutAndDetail(ScmStorageOutAndDetail scmStorageOutAndDetail) {
		ScmStorageOut scmStorageOut=new ScmStorageOut();
		ScmStorageOutDetail ScmStorageOutDetail=new ScmStorageOutDetail();
		copyProperties(scmStorageOutAndDetail, scmStorageOut);
		//保存主表数据
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhh24mmss");
		scmStorageOut.setSheetId("FHD"+sdf.format(new Date()));*/
		scmStorageOut.setSheetId(GenerateSheetIdUtil.genSheetIdDate("FHD"));
		
		int masterId=scmStorageOutDao.saveScmStorageOut(scmStorageOut);
		
		//保存明细表数据		
		//String[] dids=scmStorageOutAndDetail.getDid();
		String[] productIds=scmStorageOutAndDetail.getProductId();
		String[] qtys=scmStorageOutAndDetail.getQty();
		String[] factSellPrices=scmStorageOutAndDetail.getFactSellPrice();
		String[] sheetIdOriginals=scmStorageOutAndDetail.getSheetIdOriginal();
		String[] amts = scmStorageOutAndDetail.getAmt();
		String[] remarks = scmStorageOutAndDetail.getAremark();
		
		if((productIds!=null)&&(productIds.length>0)){
			for(int i=0;i<productIds.length;i++){
				ScmStorageOutDetail scmStorageOutDetail=new ScmStorageOutDetail();
				scmStorageOutDetail.setDid(String.valueOf(i+1));
				scmStorageOutDetail.setMasterId(masterId);
				scmStorageOutDetail.setProductId(productIds[i]);
				scmStorageOutDetail.setQty(qtys[i]);
				scmStorageOutDetail.setFactSellPrice(factSellPrices[i]);
				scmStorageOutDetail.setSheetIdOriginal(sheetIdOriginals[i]);
				scmStorageOutDetail.setAmt(amts[i]);
				scmStorageOutDetail.setRemark(remarks[i]);
				
				scmStorageOutDetailDao.saveScmStorageOutDetail(scmStorageOutDetail);//向明细表里插入数据
			}
		}
		//copyProperties(scmStorageOutAndDetail, ScmStorageOutDetail);
	}
	
	@Override
	public List<ScmStorageOut> listScmStorageOuts() {		
		return scmStorageOutDao.listScmStorageOuts();		
	}
	
	@Override
	public void deleteScmStorageOuts(String[] ids) {
		scmStorageOutDao.deleteScmStorageOuts(ids);
		scmStorageOutDetailDao.deleteScmStorageOutDetails(ids);
		
	}	
	
	/**
	 * 审核并流转
	 */
	@Override
	public void auditScmStorageOut(String[] ids) {
		List list=scmStorageOutDao.auditScmStorageOut(ids);
		scmStorageOutDetailDao.auditScmStorageOut(ids,list);
	}
	
	@Override
	public List<ScmStorageOutDetail> getDetailScmStorageOut(String id) {
		return scmStorageOutDetailDao.getDetailScmStorageOut(id);	
	}	
	
	public ScmStorageOut getScmStorageOut(String id)throws Exception{
		return this.scmStorageOutDao.getScmStorageOut(id);
	}
	
	public int editScmStorageOut(ScmStorageOutAndDetail scmStorageOutAndDetail)throws Exception{
		ScmStorageOut scmStorageOut=new ScmStorageOut();
		ScmStorageOutDetail ScmStorageOutDetail=new ScmStorageOutDetail();
		copyProperties(scmStorageOutAndDetail, scmStorageOut);
		//更新主表数据		
		scmStorageOutDao.editScmStorageOut(scmStorageOut);
		
		//删除明细表数据		
		this.scmStorageOutDetailDao.deleteScmStorageOutDetail(String.valueOf(scmStorageOut.getId()));
		
		//重新插入明细表数据
		String[] productIds=scmStorageOutAndDetail.getProductId();
		String[] qtys=scmStorageOutAndDetail.getQty();
		String[] factSellPrices=scmStorageOutAndDetail.getFactSellPrice();
		String[] sheetIdOriginals=scmStorageOutAndDetail.getSheetIdOriginal();
		String[] amts = scmStorageOutAndDetail.getAmt();
		String[] remarks = scmStorageOutAndDetail.getAremark();
		
		if((productIds!=null)&&(productIds.length>0)){
			for(int i=0;i<productIds.length;i++){
				ScmStorageOutDetail scmStorageOutDetail=new ScmStorageOutDetail();
				scmStorageOutDetail.setDid(String.valueOf(i+1));
				scmStorageOutDetail.setMasterId(scmStorageOut.getId());
				scmStorageOutDetail.setProductId(productIds[i]);
				scmStorageOutDetail.setQty(qtys[i]);
				scmStorageOutDetail.setFactSellPrice(factSellPrices[i]);
				scmStorageOutDetail.setSheetIdOriginal(sheetIdOriginals[i]);
				scmStorageOutDetail.setAmt(amts[i]);
				scmStorageOutDetail.setRemark(remarks[i]);
				
				scmStorageOutDetailDao.saveScmStorageOutDetail(scmStorageOutDetail);//向明细表里插入数据
			}
		}
		
		return 1;
	}
	
	public ScmStorageOutDao getScmStorageOutDao() {
		return scmStorageOutDao;
	}

	public void setScmStorageOutDao(ScmStorageOutDao scmStorageOutDao) {
		this.scmStorageOutDao = scmStorageOutDao;
	}

	public ScmStorageOutDetailDao getScmStorageOutDetailDao() {
		return scmStorageOutDetailDao;
	}

	public void setScmStorageOutDetailDao(
			ScmStorageOutDetailDao scmStorageOutDetailDao) {
		this.scmStorageOutDetailDao = scmStorageOutDetailDao;
	}

	@Override
	public List<ScmStorageOut> listScmStorageOuts(ScmStorageOutQO scmStorageOutQO) {		
		return scmStorageOutDao.listScmStorageOuts(scmStorageOutQO);
	}	
}
