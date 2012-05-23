package com.apply.b2b.cms.registry;

import org.objectweb.perseus.cache.api.CacheEntry;
import org.objectweb.perseus.cache.api.CacheException;
import org.objectweb.perseus.cache.lib.BasicCacheManager;

import com.apply.b2b.cms.cache.CacheManagerUtil;

/**
 * 
 * @author luoweifeng
 * 
 * @param <T>
 */

public abstract class AbstractServiceFactory<T> {
	private final BasicCacheManager cm = new CacheManagerUtil()
			.creatCacheManager(100);
	
	private String getCacheEntryName(String name){
		return "cms.pageportlet."+ name +".clazz";
	}
	
	public T getServiceObject(String name) {
		 T aService = this.get(getCacheEntryName(name));
		 if(aService != null){
			 return aService;
		 }else{
			T aNewService = this.newServiceInstance(name);
			if(aNewService != null){
				this.put(getCacheEntryName(name), aNewService);
				return aNewService;
			}else{
				return null;
			}
		 }
	}
	
	private boolean  remove(Object idV) throws CacheException{
		return getCm().unbind(idV, true);
	}
	
	private T put(Object idV, T vObject) {
		if (idV != null && vObject != null) {
			BasicCacheManager cM = getCm();
			if (cM != null) {
				CacheEntry cEntry = cM.lookup(idV);
				if (cEntry != null) {
					try {
						remove(idV);
					} catch (CacheException e) {
						e.printStackTrace();
					}
					
					try {
						cM.bind(idV, vObject);
						return vObject;
					} catch (CacheException e) {
						e.printStackTrace();
						return null;
					}
				} else {
					try {
						cM.bind(idV, vObject);
						return vObject;
					} catch (CacheException e) {
						e.printStackTrace();
						return null;
					}
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	private T get(Object idV) {
		if (idV != null) {
			BasicCacheManager cM = getCm();
			if (cM != null) {
				CacheEntry cEntry = cM.lookup(idV);
				if (cEntry != null) {
					Object aTcEntry = cEntry.getCeObject();
					if (aTcEntry != null) {
						T tcEntry = (T) aTcEntry;
						return tcEntry;
					}
				}
			}
		}
		return null;
	}
	
	private BasicCacheManager getCm() {
		return cm;
	}
	
	abstract protected T newServiceInstance(String name);
}