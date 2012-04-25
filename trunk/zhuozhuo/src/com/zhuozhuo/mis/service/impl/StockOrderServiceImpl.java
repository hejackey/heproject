package com.zhuozhuo.mis.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zhuozhuo.b2c.memcached.service.CacheService;
import com.zhuozhuo.mis.dao.StockOrderDao;
import com.zhuozhuo.mis.dao.StockOrderDetailDao;
import com.zhuozhuo.mis.model.QueryProvider;
import com.zhuozhuo.mis.po.ScmStockOrder;
import com.zhuozhuo.mis.po.ScmStockOrderDetail;
import com.zhuozhuo.mis.service.StockOrderService;
import com.zhuozhuo.mis.util.page.PageWraper;
import com.zhuozhuo.utils.GenerateSheetIdUtil;
import com.zhuozhuo.utils.GsonUtil;
import com.zhuozhuo.utils.ServiceFacotryUtil;
import com.zhuozhuo.utils.StringUtils;

public class StockOrderServiceImpl implements StockOrderService {
	private StockOrderDao stockOrderDao;
	private StockOrderDetailDao stockOrderDetailDao;
	private CacheService cacheService;
	private ServiceFacotryUtil serviceFactoryUtil;
	
	@Override
	public String saveStockOrder(ScmStockOrder model) throws Exception{
		if(model.getStockopentype()==2){//退货单存负数
			model.setSumamt(model.getSumamt().negate());
			model.setSumqty(-model.getSumqty());
		}
		
		model.setSheetid(GenerateSheetIdUtil.genSheetIdDate("CGD"));
		
		String id = this.stockOrderDao.saveStockOrder(model);
		
		List<ScmStockOrderDetail> detailList= new ArrayList<ScmStockOrderDetail>();		
		int i=0;
		
		for(String productid : model.getStockOrderDetail().getAproductid()){
			if(!StringUtils.isEmpty(productid)){
				ScmStockOrderDetail detail = new ScmStockOrderDetail();
				i+=1;
				detail.setDid(i);
				detail.setMasterid(id);
				detail.setProductid(productid);
				if(model.getStockopentype()==2){
					detail.setQty("-"+model.getStockOrderDetail().getAqty()[i-1]);
					detail.setFactinprice("-"+model.getStockOrderDetail().getAfactinprice()[i-1]);
					detail.setTaxrate("-"+model.getStockOrderDetail().getAtaxrate()[i-1]);
					detail.setAmt("-"+model.getStockOrderDetail().getAamt()[i-1]);	
				}
				else{
					detail.setQty(model.getStockOrderDetail().getAqty()[i-1]);
					detail.setFactinprice(model.getStockOrderDetail().getAfactinprice()[i-1]);
					detail.setTaxrate(model.getStockOrderDetail().getAtaxrate()[i-1]);
					detail.setAmt(model.getStockOrderDetail().getAamt()[i-1]);
				}
				detail.setRemark(model.getStockOrderDetail().getAremark()[i-1]);
				
				detailList.add(detail);
			}
		}
		
		this.stockOrderDetailDao.saveStockOrderDetail(detailList);
		
		return id;
	}
	
	public PageWraper listStockOrder(ScmStockOrder model)throws Exception{
		return this.stockOrderDao.listStockOrder(model);
	}
	
	public ScmStockOrder getStockOrder(String id)throws Exception{
		Object stockOrder_cache = cacheService.get("SCM_STOCK_ORDER_"+id);
		ScmStockOrder stockOrder = null;
		if(stockOrder_cache==null){
			stockOrder = this.stockOrderDao.getStockOrder(id);
			stockOrder.setStockOrderDetailList(this.stockOrderDetailDao.getStockOrderDetailBySheetId(id));
			stockOrder.setDeptList(this.serviceFactoryUtil.getTadmDeptService().getDeptList());
			QueryProvider qp = new QueryProvider();
			stockOrder.setProviderList(this.serviceFactoryUtil.getProviderService().queryProvider(qp));
			stockOrder.setBarnTypeList(this.serviceFactoryUtil.getScmBarnTypeService().getBarnTypeList());
			stockOrder.setUserList(this.serviceFactoryUtil.getTadmUserService().getUserList());
			
			cacheService.put("SCM_STOCK_ORDER_"+id, GsonUtil.toGson(stockOrder));
		}
		else{
			stockOrder = GsonUtil.fromGson(stockOrder_cache.toString(),ScmStockOrder.class);
		}
		
		return stockOrder;
	}

	public int editStockOrder(ScmStockOrder model)throws Exception{
		int result = this.stockOrderDao.editStockOrder(model);//更新主表
		this.stockOrderDetailDao.deleteStockOrderDetailBySheetid(model.getId());//删除明细表
		
		//重新插入明细表
		List<ScmStockOrderDetail> detailList= new ArrayList<ScmStockOrderDetail>();		
		int i=0;
		
		for(String productid : model.getStockOrderDetail().getAproductid()){
			if(!StringUtils.isEmpty(productid)){
				ScmStockOrderDetail detail = new ScmStockOrderDetail();
				i+=1;
				detail.setDid(i);
				detail.setSheetid(model.getSheetid());
				detail.setProductid(productid);
				detail.setQty(model.getStockOrderDetail().getAqty()[i-1]);
				detail.setFactinprice(model.getStockOrderDetail().getAfactinprice()[i-1]);
				detail.setTaxrate(model.getStockOrderDetail().getAtaxrate()[i-1]);
				detail.setAmt(model.getStockOrderDetail().getAamt()[i-1]);				
				detail.setRemark(model.getStockOrderDetail().getAremark()[i-1]);
				detail.setMasterid(model.getId());
				
				detailList.add(detail);
			}
		}
		
		//更新缓存
		model.setStockOrderDetailList(detailList);
		cacheService.delete("SCM_STOCK_ORDER_"+model.getId());
		
		this.stockOrderDetailDao.saveStockOrderDetail(detailList);
		
		return result;
	}
	
	/**
	 * 采购单审核
	 */
	public int updateStatusStockOrder(String[] asheetid,int flag)throws Exception{
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
		
		ScmStockOrder stockOrder= new ScmStockOrder();
		stockOrder.setId(sbId.toString());
		stockOrder.setSheetstate(flag);
		stockOrder.setAuditid("5");
		
		int result = this.stockOrderDao.updateStatusStockOrder(stockOrder);
		
		//采购单导入总库存及明细表
		List<String> storageIdList = this.stockOrderDao.exportStockOrderToStorageIn(idList);		
		this.stockOrderDao.exportStockOrderDetailToStorageInDetail(idList,storageIdList);
		
		//采购单流入付款明细表
		this.stockOrderDao.exportStockOrderToScmPayDetail(stockOrder.getId());
		
		return result;
	}
	
	public List<String> exportStockOrderToStorageIn(List<String> sheetidList)throws Exception{
		return this.stockOrderDao.exportStockOrderToStorageIn(sheetidList);
	}
	
	public void exportStockOrderDetailToStorageInDetail(List<String> idList,List<String> storageIdList)throws Exception{
		this.stockOrderDao.exportStockOrderDetailToStorageInDetail(idList,storageIdList);
	}
	
	public List<ScmStockOrder> exportStockOrderToExcel(ScmStockOrder stockOrder)throws Exception{
		return this.stockOrderDao.exportStockOrderToExcel(stockOrder);
	}
	
	public int deleteStockOrder(String[] idArray)throws Exception{
		StringBuffer sbId = new StringBuffer();
		
		int i=0;
		
		for(String id : idArray){//组合修改状态的部门id
			sbId.append(id);
			if(i!=idArray.length-1)
				sbId.append(",");
			i++;
			
			//清缓存部门
			cacheService.delete("SCM_STOCK_ORDER_"+id);				
		}
		return this.stockOrderDao.deleteStockOrder(sbId.toString());
	}
	public StockOrderDao getStockOrderDao() {
		return stockOrderDao;
	}

	public void setStockOrderDao(StockOrderDao stockOrderDao) {
		this.stockOrderDao = stockOrderDao;
	}

	public StockOrderDetailDao getStockOrderDetailDao() {
		return stockOrderDetailDao;
	}

	public void setStockOrderDetailDao(StockOrderDetailDao stockOrderDetailDao) {
		this.stockOrderDetailDao = stockOrderDetailDao;
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
