package com.zhuozhuo.mis.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.zhuozhuo.b2c.memcached.service.CacheService;
import com.zhuozhuo.mis.dao.ScmGatherDao;
import com.zhuozhuo.mis.dao.ScmGatherDetailDao;
import com.zhuozhuo.mis.model.QueryProvider;
import com.zhuozhuo.mis.po.ScmGather;
import com.zhuozhuo.mis.po.ScmGatherDetail;
import com.zhuozhuo.mis.service.ScmGatherService;
import com.zhuozhuo.mis.util.page.PageWraper;
import com.zhuozhuo.utils.GsonUtil;
import com.zhuozhuo.utils.ServiceFacotryUtil;
import com.zhuozhuo.utils.StringUtils;

public class ScmGatherServiceImpl implements ScmGatherService {
	private ScmGatherDao scmGatherDao;
	private ScmGatherDetailDao scmGatherDetailDao;
	private CacheService cacheService;
	private ServiceFacotryUtil serviceFactoryUtil;
	
	@Override
	public String saveScmGather(ScmGather model) throws Exception{
		if(model.getStockopentype()==2){//退货单存负数
			model.setSumamt(model.getSumamt().negate());
			model.setSumqty(-model.getSumqty());
		}
		String id = this.scmGatherDao.saveScmGather(model);
		
		List<ScmGatherDetail> detailList= new ArrayList<ScmGatherDetail>();		
		int i=0;
		
		for(String productid : model.getScmGatherDetail().getAproductid()){
			if(!StringUtils.isEmpty(productid)){
				ScmGatherDetail detail = new ScmGatherDetail();
				i+=1;
				detail.setDid(i);
				detail.setMasterid(id);
				detail.setProductid(productid);
				if(model.getStockopentype()==2){
					detail.setQty("-"+model.getScmGatherDetail().getAqty()[i-1]);
					detail.setFactinprice("-"+model.getScmGatherDetail().getAfactinprice()[i-1]);
					detail.setTaxrate("-"+model.getScmGatherDetail().getAtaxrate()[i-1]);
					detail.setAmt("-"+model.getScmGatherDetail().getAamt()[i-1]);	
				}
				else{
					detail.setQty(model.getScmGatherDetail().getAqty()[i-1]);
					detail.setFactinprice(model.getScmGatherDetail().getAfactinprice()[i-1]);
					detail.setTaxrate(model.getScmGatherDetail().getAtaxrate()[i-1]);
					detail.setAmt(model.getScmGatherDetail().getAamt()[i-1]);
				}
				detail.setRemark(model.getScmGatherDetail().getAremark()[i-1]);
				
				detailList.add(detail);
			}
		}
		
		this.scmGatherDetailDao.saveScmGatherDetail(detailList);
		
		return id;
	}
	
	public PageWraper listScmGather(ScmGather model)throws Exception{
		return this.scmGatherDao.listScmGather(model);
	}
	
	public ScmGather getScmGather(String id)throws Exception{
		Object scmGather_cache = cacheService.get("SCM_STOCK_ORDER_"+id);
		ScmGather scmGather = null;
		if(scmGather_cache==null){
			scmGather = this.scmGatherDao.getScmGather(id);
			scmGather.setScmGatherDetailList(this.scmGatherDetailDao.getScmGatherDetailBySheetId(id));
			scmGather.setDeptList(this.serviceFactoryUtil.getTadmDeptService().getDeptList());
			QueryProvider qp = new QueryProvider();
			scmGather.setProviderList(this.serviceFactoryUtil.getProviderService().queryProvider(qp));
			scmGather.setBarnTypeList(this.serviceFactoryUtil.getScmBarnTypeService().getBarnTypeList());
			scmGather.setUserList(this.serviceFactoryUtil.getTadmUserService().getUserList());
			
			cacheService.put("SCM_STOCK_ORDER_"+id, GsonUtil.toGson(scmGather));
		}
		else{
			scmGather = GsonUtil.fromGson(scmGather_cache.toString(),ScmGather.class);
		}
		
		return scmGather;
	}

	public int editScmGather(ScmGather model)throws Exception{
		int result = this.scmGatherDao.editScmGather(model);//更新主表
		this.scmGatherDetailDao.deleteScmGatherDetailBySheetid(model.getId());//删除明细表
		
		//重新插入明细表
		List<ScmGatherDetail> detailList= new ArrayList<ScmGatherDetail>();		
		int i=0;
		
		for(String productid : model.getScmGatherDetail().getAproductid()){
			if(!StringUtils.isEmpty(productid)){
				ScmGatherDetail detail = new ScmGatherDetail();
				i+=1;
				detail.setDid(i);
				detail.setSheetid(model.getSheetid());
				detail.setProductid(productid);
				detail.setQty(model.getScmGatherDetail().getAqty()[i-1]);
				detail.setFactinprice(model.getScmGatherDetail().getAfactinprice()[i-1]);
				detail.setTaxrate(model.getScmGatherDetail().getAtaxrate()[i-1]);
				detail.setAmt(model.getScmGatherDetail().getAamt()[i-1]);				
				detail.setRemark(model.getScmGatherDetail().getAremark()[i-1]);
				detail.setMasterid(model.getId());
				
				detailList.add(detail);
			}
		}
		
		//更新缓存
		model.setScmGatherDetailList(detailList);
		cacheService.delete("SCM_STOCK_ORDER_"+model.getId());
		
		this.scmGatherDetailDao.saveScmGatherDetail(detailList);
		
		return result;
	}
	
	public int updateStatusScmGather(String[] asheetid,int flag)throws Exception{
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
		
		ScmGather scmGather= new ScmGather();
		scmGather.setId(sbId.toString());
		scmGather.setSheetstate(flag);
		scmGather.setAuditid("5");
		
		int result = this.scmGatherDao.updateStatusScmGather(scmGather);
		
		List<String> storageIdList = this.scmGatherDao.exportScmGatherToStorageIn(idList);
		this.scmGatherDao.exportScmGatherDetailToStorageInDetail(idList,storageIdList);
		
		return result;
	}
	
	public List<String> exportScmGatherToStorageIn(List<String> sheetidList)throws Exception{
		return this.scmGatherDao.exportScmGatherToStorageIn(sheetidList);
	}
	
	public void exportScmGatherDetailToStorageInDetail(List<String> idList,List<String> storageIdList)throws Exception{
		this.scmGatherDao.exportScmGatherDetailToStorageInDetail(idList,storageIdList);
	}
	
	public ScmGatherDao getScmGatherDao() {
		return scmGatherDao;
	}

	public void setScmGatherDao(ScmGatherDao scmGatherDao) {
		this.scmGatherDao = scmGatherDao;
	}

	public ScmGatherDetailDao getScmGatherDetailDao() {
		return scmGatherDetailDao;
	}

	public void setScmGatherDetailDao(ScmGatherDetailDao scmGatherDetailDao) {
		this.scmGatherDetailDao = scmGatherDetailDao;
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
