package com.zhuozhuo.mis.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.zhuozhuo.mis.common.BaseService;
import com.zhuozhuo.mis.dao.ScmStorageInDao;
import com.zhuozhuo.mis.dao.ScmStorageInDetailDao;
import com.zhuozhuo.mis.po.ScmStorageIn;
import com.zhuozhuo.mis.po.ScmStorageInAndDetail;
import com.zhuozhuo.mis.po.ScmStorageInDetail;
import com.zhuozhuo.mis.po.ScmStorageInQ;
import com.zhuozhuo.mis.service.ScmStorageInService;
import com.zhuozhuo.utils.StringUtils;

public class ScmStorageInServiceImpl extends BaseService implements ScmStorageInService {
	private static final String ENTERWAREHOUSE = "enterWarehouse";//入库单
	//private static final String RETURNEDPURCHASE = "returnedPurchase";//退货单

	private ScmStorageInDao scmStorageInDao;
	private ScmStorageInDetailDao scmStorageInDetailDao;
	
	/**
	 * 审核退货单
	 * @param ids
	 */
	public void auditTHD(String[] ids) {
		List list=scmStorageInDao.auditTHD(ids);
		scmStorageInDetailDao.auditTHD(ids,list);
	}	

	@Override
	public List<ScmStorageIn> listScmStorageIns(String type) {
		return scmStorageInDao.listScmStorageIns(type);
	}

	public ScmStorageInDao getScmStorageInDao() {
		return scmStorageInDao;
	}

	public void setScmStorageInDao(ScmStorageInDao scmStorageInDao) {
		this.scmStorageInDao = scmStorageInDao;
	}

	public ScmStorageInDetailDao getScmStorageInDetailDao() {
		return scmStorageInDetailDao;
	}

	public void setScmStorageInDetailDao(
			ScmStorageInDetailDao scmStorageInDetailDao) {
		this.scmStorageInDetailDao = scmStorageInDetailDao;
	}

	/**
	 * 保存入库单和退货单主表及明细
	 */
	@Override
	public void saveScmStorageInAndDetail(ScmStorageInAndDetail scmStorageInAndDetail,String type) {
		ScmStorageIn scmStorageIn = new ScmStorageIn();
		copyProperties(scmStorageInAndDetail, scmStorageIn);
		// 保存主表
		int masterId;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		if(type.equals(ENTERWAREHOUSE)){//入库单			
			scmStorageIn.setSheetId("RKD"+sdf.format(new Date()));
			masterId=scmStorageInDao.saveScmStorageIn(scmStorageIn);
		}else{//退货单			
			if((scmStorageIn.getSumQty()!=null)&&(!(StringUtils.trim(scmStorageIn.getSumQty()).equals(""))))
				scmStorageIn.setSumQty("-"+scmStorageIn.getSumQty());
			if((scmStorageIn.getSumAmt()!=null)&&(!(StringUtils.trim(scmStorageIn.getSumAmt()).equals(""))))
				scmStorageIn.setSumAmt("-"+scmStorageIn.getSumAmt());
			scmStorageIn.setSheetId("THD"+sdf.format(new Date()));
			masterId=scmStorageInDao.saveScmStorageIn(scmStorageIn);
		}
		
		//String[] dids = scmStorageInAndDetail.getDid();// 序号
		String[] productIds = scmStorageInAndDetail.getProductId();// 产品ID
		String[] qtys = scmStorageInAndDetail.getQty();// 数量
		String[] factInprrices = scmStorageInAndDetail.getFactSellPrice();// 实际价格		
		String[] amts = scmStorageInAndDetail.getAmt();// 金额
		String[] taxrate= scmStorageInAndDetail.getTaxrate();//税率
		String[] sheetIdOriginals = scmStorageInAndDetail.getSheetIdOriginal();// 来源单号		

		// 保存明细表
		if ((productIds != null) && (productIds.length > 0)) {
			for (int i = 0; i < productIds.length; i++) {
				ScmStorageInDetail scmStorageInDetail = new ScmStorageInDetail();
				scmStorageInDetail.setDid(new Integer(i).toString());
				scmStorageInDetail.setMasterId(new Integer(masterId).toString());
				scmStorageInDetail.setProductId(productIds[i]);
				scmStorageInDetail.setQty(qtys[i]);
				scmStorageInDetail.setFactInprrice(factInprrices[i]);				
				scmStorageInDetail.setAmt(amts[i]);
				scmStorageInDetail.setTaxrate(taxrate[i]);
				scmStorageInDetail.setSheetIdOriginal(sheetIdOriginals[i]);				
				if(type.equals(ENTERWAREHOUSE)){//入库单
					scmStorageInDetailDao.saveScmStorageInDetail(scmStorageInDetail);
				}else{//退货单
					if((scmStorageInDetail.getQty()!=null)&&(!(StringUtils.trim(scmStorageInDetail.getQty()).equals(""))))
						scmStorageInDetail.setQty("-"+scmStorageInDetail.getQty());
					if((scmStorageInDetail.getAmt()!=null)&&(!(StringUtils.trim(scmStorageInDetail.getAmt()).equals(""))))
						scmStorageInDetail.setAmt("-"+scmStorageInDetail.getAmt());
					scmStorageInDetailDao.saveScmStorageInDetail(scmStorageInDetail);
				}
			}
		}
	}
	
	/**
	 * 修改一个入库单和明细表内容
	 * @param scmStorageInAndDetail
	 * @param type
	 */
	public void updateScmScmStorageIn(ScmStorageInAndDetail scmStorageInAndDetail, String type) {
		ScmStorageIn scmStorageIn = new ScmStorageIn();
		copyProperties(scmStorageInAndDetail, scmStorageIn);		
		int masterId=scmStorageIn.getId();		
		if(type.equals(ENTERWAREHOUSE)){//入库单
			scmStorageInDao.updateScmStorageIn(scmStorageIn);
		}else{//退货单
			if((scmStorageIn.getSumQty()!=null)&&(!(StringUtils.trim(scmStorageIn.getSumQty()).equals(""))))
				scmStorageIn.setSumQty("-"+scmStorageIn.getSumQty());
			if((scmStorageIn.getSumAmt()!=null)&&(!(StringUtils.trim(scmStorageIn.getSumAmt()).equals(""))))
				scmStorageIn.setSumAmt("-"+scmStorageIn.getSumAmt());
			scmStorageInDao.updateScmStorageIn(scmStorageIn);
		}
		
		//保存明细表的数据
		String[] productIds = scmStorageInAndDetail.getProductId();// 产品ID
		String[] qtys = scmStorageInAndDetail.getQty();// 数量
		String[] factInprrices = scmStorageInAndDetail.getFactSellPrice();// 实际价格		
		String[] amts = scmStorageInAndDetail.getAmt();// 金额
		String[] taxrate= scmStorageInAndDetail.getTaxrate();//税率
		String[] sheetIdOriginals = scmStorageInAndDetail.getSheetIdOriginal();// 来源单号
		//根据masterId删除明细表的内容
		scmStorageInDetailDao.deleteScmStorageInDetailByMasterId(masterId);
		if ((productIds != null) && (productIds.length > 0)) {
			for (int i = 0; i < productIds.length; i++) {
				ScmStorageInDetail scmStorageInDetail = new ScmStorageInDetail();
				scmStorageInDetail.setDid(new Integer(i).toString());
				scmStorageInDetail.setMasterId(new Integer(masterId).toString());
				scmStorageInDetail.setProductId(productIds[i]);
				scmStorageInDetail.setQty(qtys[i]);
				scmStorageInDetail.setFactInprrice(factInprrices[i]);				
				scmStorageInDetail.setAmt(amts[i]);
				scmStorageInDetail.setTaxrate(taxrate[i]);
				scmStorageInDetail.setSheetIdOriginal(sheetIdOriginals[i]);				
				if(type.equals(ENTERWAREHOUSE)){//入库单
					scmStorageInDetailDao.saveScmStorageInDetail(scmStorageInDetail);
				}else{//退货单
					if((scmStorageInDetail.getQty()!=null)&&(!(StringUtils.trim(scmStorageInDetail.getQty()).equals(""))))
						scmStorageInDetail.setQty("-"+scmStorageInDetail.getQty());
					if((scmStorageInDetail.getAmt()!=null)&&(!(StringUtils.trim(scmStorageInDetail.getAmt()).equals(""))))
						scmStorageInDetail.setAmt("-"+scmStorageInDetail.getAmt());
					scmStorageInDetailDao.saveScmStorageInDetail(scmStorageInDetail);
				}
			}
		}
			
	}

	@Override
	public void deleteScmScmStorageIns(String[] ids) {
		scmStorageInDao.deleteScmScmStorageIns(ids);
		scmStorageInDetailDao.deleteScmStorageInDetails(ids);
	}

	/**
	 * 审核并流转
	 */
	@Override
	public void auditScmScmStorageIns(String[] ids) {
		List list=scmStorageInDao.auditScmScmStorageIns(ids);//流转主表
		scmStorageInDetailDao.auditScmScmStorageIns(ids,list);//流转子表
	}

	@Override
	public List<ScmStorageInDetail> showDetailScmStorageIn(String id) {		
		return scmStorageInDetailDao.getScmStorageInDetailsByMasterId(id);
	}

	@Override
	public ScmStorageIn getScmStorageInById(String id,String type) {		
		return scmStorageInDao.getScmStorageInById(id,type);
	}

	@Override
	public List<ScmStorageInDetail> getScmStorageInDetailBymasterId(String masterId) {		
		return scmStorageInDetailDao.getScmStorageInDetailsByMasterId(masterId);		
	}

	@Override
	public List<ScmStorageInDetail> getEditScmStorageInDetailByMasterId(String id, String type) {		
		return scmStorageInDetailDao.getEditScmStorageInDetailByMasterId(id,type);
	}

	@Override
	public List<ScmStorageIn> listScmStorageIns(String type,
			ScmStorageInQ scmStorageInQ) {
		return scmStorageInDao.listScmStorageIns(type,scmStorageInQ);
	}
}
