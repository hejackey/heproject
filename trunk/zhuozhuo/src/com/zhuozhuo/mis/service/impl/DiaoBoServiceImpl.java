package com.zhuozhuo.mis.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.zhuozhuo.b2c.memcached.service.CacheService;
import com.zhuozhuo.mis.dao.DiaoBoDao;
import com.zhuozhuo.mis.dao.DiaoBoDetailDao;
import com.zhuozhuo.mis.dao.StockOrderDao;
import com.zhuozhuo.mis.dao.StockOrderDetailDao;
import com.zhuozhuo.mis.model.QueryProvider;
import com.zhuozhuo.mis.po.ScmDiaoBo;
import com.zhuozhuo.mis.po.ScmDiaoBoDetail;
import com.zhuozhuo.mis.po.ScmStockOrder;
import com.zhuozhuo.mis.po.ScmStockOrderDetail;
import com.zhuozhuo.mis.service.DiaoBoService;
import com.zhuozhuo.mis.service.StockOrderService;
import com.zhuozhuo.mis.util.page.PageWraper;
import com.zhuozhuo.utils.GsonUtil;
import com.zhuozhuo.utils.ServiceFacotryUtil;
import com.zhuozhuo.utils.StringUtils;

public class DiaoBoServiceImpl implements DiaoBoService {
	private DiaoBoDao diaoBoDao;
	private DiaoBoDetailDao diaoBoDetailDao;
	private CacheService cacheService;
	public DiaoBoDao getDiaoBoDao() {
		return diaoBoDao;
	}

	public void setDiaoBoDao(DiaoBoDao diaoBoDao) {
		this.diaoBoDao = diaoBoDao;
	}

	private ServiceFacotryUtil serviceFactoryUtil;
	
	@Override
	public String saveDiaoBo(ScmDiaoBo model) throws Exception{
		if(model.getStockopentype()==2){//退货单存负数
			model.setSumamt(model.getSumamt().negate());
			model.setSumqty(-model.getSumqty());
		}
		String id = this.diaoBoDao.saveDiaoBo(model);
		
		List<ScmDiaoBoDetail> detailList= new ArrayList<ScmDiaoBoDetail>();		
		int i=0;
		
		for(String productid : model.getDiaoBoDetail().getAproductid()){
			if(!StringUtils.isEmpty(productid)){
				ScmDiaoBoDetail detail = new ScmDiaoBoDetail();
				i+=1;
				detail.setDid(i);
				detail.setMasterid(id);
				detail.setProductid(productid);
				if(model.getStockopentype()==2){
					detail.setQty("-"+model.getDiaoBoDetail().getAqty()[i-1]);
					detail.setFactinprice("-"+model.getDiaoBoDetail().getAfactinprice()[i-1]);
					detail.setTaxrate("-"+model.getDiaoBoDetail().getAtaxrate()[i-1]);
					detail.setAmt("-"+model.getDiaoBoDetail().getAamt()[i-1]);	
				}
				else{
					detail.setQty(model.getDiaoBoDetail().getAqty()[i-1]);
					detail.setFactinprice(model.getDiaoBoDetail().getAfactinprice()[i-1]);
					detail.setTaxrate(model.getDiaoBoDetail().getAtaxrate()[i-1]);
					detail.setAmt(model.getDiaoBoDetail().getAamt()[i-1]);
				}
				detail.setRemark(model.getDiaoBoDetail().getAremark()[i-1]);
				
				detailList.add(detail);
			}
		}
		
		this.diaoBoDetailDao.saveDiaoBoDetail(detailList);
		
		return id;
	}
	
	public PageWraper listDiaoBo(ScmDiaoBo model)throws Exception{
		return this.diaoBoDao.listDiaoBo(model);
	}
	
	public ScmDiaoBo getDiaoBo(String id)throws Exception{
		Object DiaoBo_cache = cacheService.get("SCM_DIAO_BO_"+id);
		ScmDiaoBo DiaoBo = null;
		if(DiaoBo_cache==null){
			DiaoBo = this.diaoBoDao.getDiaoBo(id);
			DiaoBo.setDiaoBoDetailList(this.diaoBoDetailDao.getDiaoBoDetailBySheetId(id));
			DiaoBo.setDeptList(this.serviceFactoryUtil.getTadmDeptService().getDeptList());
			QueryProvider qp = new QueryProvider();
			DiaoBo.setProviderList(this.serviceFactoryUtil.getProviderService().queryProvider(qp));
			DiaoBo.setBarnTypeList(this.serviceFactoryUtil.getScmBarnTypeService().getBarnTypeList());
			DiaoBo.setUserList(this.serviceFactoryUtil.getTadmUserService().getUserList());
			
			cacheService.put("SCM_DIAO_BO_"+id, GsonUtil.toGson(DiaoBo));			
		}
		else{
			DiaoBo = GsonUtil.fromGson(DiaoBo_cache.toString(),ScmDiaoBo.class);			
		}
		
		return DiaoBo;
	}

	public int editDiaoBo(ScmDiaoBo model)throws Exception{
		int result = this.diaoBoDao.editDiaoBo(model);//更新主表
		this.diaoBoDetailDao.deleteDiaoBoDetailBySheetid(model.getId());//删除明细表
		
		//重新插入明细表
		List<ScmDiaoBoDetail> detailList= new ArrayList<ScmDiaoBoDetail>();		
		int i=0;
		
		for(String productid : model.getDiaoBoDetail().getAproductid()){
			if(!StringUtils.isEmpty(productid)){
				ScmDiaoBoDetail detail = new ScmDiaoBoDetail();
				i+=1;
				detail.setDid(i);
				detail.setSheetid(model.getSheetid());
				detail.setProductid(productid);
				detail.setQty(model.getDiaoBoDetail().getAqty()[i-1]);
				detail.setFactinprice(model.getDiaoBoDetail().getAfactinprice()[i-1]);
				detail.setTaxrate(model.getDiaoBoDetail().getAtaxrate()[i-1]);
				detail.setAmt(model.getDiaoBoDetail().getAamt()[i-1]);				
				detail.setRemark(model.getDiaoBoDetail().getAremark()[i-1]);
				detail.setMasterid(model.getId());
				
				detailList.add(detail);
			}
		}
		
		//更新缓存
		model.setDiaoBoDetailList(detailList);
		cacheService.delete("SCM_STOCK_ORDER_"+model.getId());
		
		this.diaoBoDetailDao.saveDiaoBoDetail(detailList);
		
		return result;
	}
	
	/**
	 * 采购单审核
	 */
	public int updateStatusDiaoBo(String[] asheetid,int flag)throws Exception{
		StringBuffer sbId = new StringBuffer();
		List<String> idList = new ArrayList<String>();
		int i=0;
		
		for(String id : asheetid){//组合修改状态的部门id
			sbId.append(id);
			if(i!=asheetid.length-1)
				sbId.append(",");
			i++;
			
			//清缓存部门
			cacheService.delete("SCM_STOCK_ORDER_"+id);	
			
			idList.add(id);
		}
		
		ScmDiaoBo DiaoBo= new ScmDiaoBo();
		DiaoBo.setId(sbId.toString());
		DiaoBo.setSheetstate(flag);
		DiaoBo.setAuditid("5");
		
		int result = this.diaoBoDao.updateStatusDiaoBo(DiaoBo);
		
		//采购单导入总库存及明细表
		List<String> storageIdList = this.diaoBoDao.exportDiaoBoToStorageIn(idList);		
		this.diaoBoDao.exportDiaoBoDetailToStorageInDetail(idList,storageIdList);
		
		//采购单流入付款明细表
		this.diaoBoDao.exportDiaoBoToScmPayDetail(DiaoBo.getId());
		
		return result;
	}
	
	public List<String> exportDiaoBoToStorageIn(List<String> sheetidList)throws Exception{
		return this.diaoBoDao.exportDiaoBoToStorageIn(sheetidList);
	}
	
	public void exportDiaoBoDetailToStorageInDetail(List<String> idList,List<String> storageIdList)throws Exception{
		this.diaoBoDao.exportDiaoBoDetailToStorageInDetail(idList,storageIdList);
	}
	
	public DiaoBoDao getdiaoBoDao() {
		return diaoBoDao;
	}

	public void setdiaoBoDao(DiaoBoDao diaoBoDao) {
		this.diaoBoDao = diaoBoDao;
	}

	

	public DiaoBoDetailDao getDiaoBoDetailDao() {
		return diaoBoDetailDao;
	}

	public void setDiaoBoDetailDao(DiaoBoDetailDao diaoBoDetailDao) {
		this.diaoBoDetailDao = diaoBoDetailDao;
	}

	public CacheService getCacheService() {
		return cacheService;
	}

	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	public ServiceFacotryUtil getServiceFactoryUtil() {
		return serviceFactoryUtil;
	}

	public void setServiceFactoryUtil(ServiceFacotryUtil serviceFactoryUtil) {
		this.serviceFactoryUtil = serviceFactoryUtil;
	}

}
