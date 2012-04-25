package com.zhuozhuo.mis.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.zhuozhuo.mis.common.BaseService;
import com.zhuozhuo.mis.dao.ScmPayDao;
import com.zhuozhuo.mis.dao.ScmPayDetailDao;
import com.zhuozhuo.mis.po.ScmPay;
import com.zhuozhuo.mis.po.ScmPayAndDetail;
import com.zhuozhuo.mis.po.ScmPayDetail;
import com.zhuozhuo.mis.po.ScmPayQ;
import com.zhuozhuo.mis.service.ScmPayService;
import com.zhuozhuo.utils.GenerateSheetIdUtil;

public class ScmPayServiceImpl extends BaseService implements ScmPayService {

	private ScmPayDao scmPayDao;
	private ScmPayDetailDao scmPayDetailDao;
	
	@Override
	public List<ScmPayDetail> showScmPayDetail(String id) {		
		return scmPayDetailDao.showScmPayDetail(id);
	}
	
	@Override
	public List<ScmPay> listScmPays() {		
		return scmPayDao.listScmPays();
	}
	
	@Override
	public void saveScmPayAndDetail(ScmPayAndDetail scmPayAndDetail) throws Exception {	
		ScmPay scmPay=new ScmPay();
		copyProperties(scmPayAndDetail,scmPay);
		//保存主表
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhh24mmss");
		scmPay.setSheetId("FKD"+sdf.format(new Date()));*/
		scmPay.setSheetId(GenerateSheetIdUtil.genSheetIdDate("FKD"));
		
		scmPay.setProviderId(scmPay.getProviderid());
		
		int masterId=scmPayDao.saveScmPay(scmPay);
		String[] dids=scmPayAndDetail.getDetailid();   
		String[] arrids = scmPayAndDetail.getArrid();
		
		//保存明细表
		if((dids!=null)&&(dids.length>0)){
			this.scmPayDetailDao.updateScmPayDetailAuditSign(dids, String.valueOf(masterId),arrids);
		}		
	}
	
	public int editScmPayAndDetail(ScmPayAndDetail scmPayAndDetail)throws Exception{
		ScmPay scmPay=new ScmPay();
		copyProperties(scmPayAndDetail,scmPay);
		scmPayDao.editScmPayAndDetail(scmPay);
		
		String[] dids=scmPayAndDetail.getDetailid();   
		String[] arrids = scmPayAndDetail.getArrid();
		//保存明细表
		if((dids!=null)&&(dids.length>0)){
			this.scmPayDetailDao.updateScmPayDetailAuditSign(dids, String.valueOf(scmPay.getId()),arrids);
		}
		
		return 1;
	}
	
	@Override
	public void deleteScmPays(String[] ids) {
		scmPayDao.deleteScmPays(ids);
		scmPayDetailDao.deleteScmPayDetails(ids);
	}
	
	public ScmPay getScmPay(String id)throws Exception{
		return this.scmPayDao.getScmPay(id);
	}
	public ScmPayDao getScmPayDao() {
		return scmPayDao;
	}
	public void setScmPayDao(ScmPayDao scmPayDao) {
		this.scmPayDao = scmPayDao;
	}
	
	public ScmPayDetailDao getScmPayDetailDao() {
		return scmPayDetailDao;
	}
	public void setScmPayDetailDao(ScmPayDetailDao scmPayDetailDao) {
		this.scmPayDetailDao = scmPayDetailDao;
	}

	@Override
	public List<ScmPay> listScmPays(ScmPayQ scmPayQ) {
		return scmPayDao.listScmPays(scmPayQ);
	}
}
