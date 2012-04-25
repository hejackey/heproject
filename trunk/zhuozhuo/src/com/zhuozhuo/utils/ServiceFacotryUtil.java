package com.zhuozhuo.utils;

import com.zhuozhuo.b2c.memcached.service.CacheService;
import com.zhuozhuo.mis.service.DiaoBoDetailService;
import com.zhuozhuo.mis.service.DiaoBoService;
import com.zhuozhuo.mis.service.ProductTypeService;
import com.zhuozhuo.mis.service.ProviderService;
import com.zhuozhuo.mis.service.ScmBarnTypeService;
import com.zhuozhuo.mis.service.ScmGatherDetailService;
import com.zhuozhuo.mis.service.ScmGatherService;
import com.zhuozhuo.mis.service.ScmStorageService;
import com.zhuozhuo.mis.service.StockOrderDetailService;
import com.zhuozhuo.mis.service.StockOrderService;
import com.zhuozhuo.mis.service.TAdmDeptService;
import com.zhuozhuo.mis.service.TAdmModuleService;
import com.zhuozhuo.mis.service.TAdmRoleService;
import com.zhuozhuo.mis.service.TAdmUserService;

public class ServiceFacotryUtil {
	private TAdmDeptService tadmDeptService;
	private TAdmModuleService tadmModuleService;
	private TAdmUserService tadmUserService;
	private CacheService cacheService;
	private TAdmRoleService tadmRoleService;
	private StockOrderService stockOrderService;
	private ProviderService providerService;
	private ScmBarnTypeService scmBarnTypeService;
	private StockOrderDetailService stockOrderDetailService;
	private DiaoBoService diaoBoService;
	private DiaoBoDetailService DiaoBoDetailService;
	private ScmStorageService scmStorageService;
	private ProductTypeService productTypeService; 
	private ScmGatherService scmGatherService;
	private ScmGatherDetailService scmGatherDetailService;
	
	public TAdmDeptService getTadmDeptService() {
		return tadmDeptService;
	}
	public void setTadmDeptService(TAdmDeptService tadmDeptService) {
		this.tadmDeptService = tadmDeptService;
	}
	public TAdmModuleService getTadmModuleService() {
		return tadmModuleService;
	}
	public void setTadmModuleService(TAdmModuleService tadmModuleService) {
		this.tadmModuleService = tadmModuleService;
	}
	public TAdmUserService getTadmUserService() {
		return tadmUserService;
	}
	public void setTadmUserService(TAdmUserService tadmUserService) {
		this.tadmUserService = tadmUserService;
	}
	public CacheService getCacheService() {
		return cacheService;
	}
	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}
	public TAdmRoleService getTadmRoleService() {
		return tadmRoleService;
	}
	public void setTadmRoleService(TAdmRoleService tadmRoleService) {
		this.tadmRoleService = tadmRoleService;
	}
	public StockOrderService getStockOrderService1() {
		return stockOrderService;
	}
	public void setStockOrderService(StockOrderService stockOrderService) {
		this.stockOrderService = stockOrderService;
	}
	public ProviderService getProviderService() {
		return providerService;
	}
	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}
	public ScmBarnTypeService getScmBarnTypeService() {
		return scmBarnTypeService;
	}
	public void setScmBarnTypeService(ScmBarnTypeService scmBarnTypeService) {
		this.scmBarnTypeService = scmBarnTypeService;
	}
	public StockOrderDetailService getStockOrderDetailService() {
		return stockOrderDetailService;
	}
	public void setStockOrderDetailService(
			StockOrderDetailService stockOrderDetailService) {
		this.stockOrderDetailService = stockOrderDetailService;
	}
	public ScmStorageService getScmStorageService() {
		return scmStorageService;
	}
	public void setScmStorageService(ScmStorageService scmStorageService) {
		this.scmStorageService = scmStorageService;
	}
	public ProductTypeService getProductTypeService() {
		return productTypeService;
	}
	public void setProductTypeService(ProductTypeService productTypeService) {
		this.productTypeService = productTypeService;
	}
	public ScmGatherService getScmGatherService() {
		return scmGatherService;
	}
	public void setScmGatherService(ScmGatherService scmGatherService) {
		this.scmGatherService = scmGatherService;
	}
	public ScmGatherDetailService getScmGatherDetailService() {
		return scmGatherDetailService;
	}
	public void setScmGatherDetailService(
			ScmGatherDetailService scmGatherDetailService) {
		this.scmGatherDetailService = scmGatherDetailService;
	}
	public StockOrderService getStockOrderService() {
		return stockOrderService;
	} 
	

	
	public DiaoBoService getDiaoBoService() {
		return diaoBoService;
	}
	public void setDiaoBoService(DiaoBoService diaoBoService) {
		this.diaoBoService = diaoBoService;
	}
	public DiaoBoDetailService getDiaoBoDetailService() {
		return DiaoBoDetailService;
	}
	public void setDiaoBoDetailService(
			DiaoBoDetailService DiaoBoDetailService) {
		this.DiaoBoDetailService = DiaoBoDetailService;
	}
	
	
}
