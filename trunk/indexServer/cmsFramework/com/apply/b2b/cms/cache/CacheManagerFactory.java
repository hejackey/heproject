package com.apply.b2b.cms.cache;

import org.objectweb.perseus.cache.lib.BasicCacheEntryFactory;
import org.objectweb.perseus.cache.lib.BasicCacheManager;
import org.objectweb.perseus.cache.replacement.lib.AbstractReplacementManager;
import org.objectweb.perseus.cache.replacement.lib.FIFOReplacementManager;
import org.objectweb.perseus.cache.replacement.lib.LRUReplacementManager;
import org.objectweb.perseus.cache.replacement.lib.MRUReplacementManager;
import org.objectweb.util.monolog.Monolog;
import org.objectweb.util.monolog.api.LoggerFactory;

/**
 * 
 * @author luoweifeng
 * 
 */

public class CacheManagerFactory {
	public final static String LRU_POLICY = "LRU";
	public final static String MRU_POLICY = "MRU";
	public final static String FIFO_POLICY = "FIFO";
	public final static String CM_LOGGER_NAME = "com.apply.b2b.cms.util.CacheManagerFactory";
	public final static String RM_LOGGER_NAME = "com.apply.b2b.cms.util.CacheManagerFactory";
	
	public static BasicCacheManager newCacheManager(int size, String policy)
			throws Exception {
		LoggerFactory lf = Monolog.initialize();
		
		BasicCacheManager cm = new BasicCacheManager();
		cm.bindFc("logger", lf.getLogger(CM_LOGGER_NAME));
		cm.bindFc("monolog-factory", lf);
		cm.setMaxObjects(size);
		
		BasicCacheEntryFactory cef = new BasicCacheEntryFactory();
		
		AbstractReplacementManager rm = getRM(policy);
		rm.bindFc("logger", lf.getLogger(RM_LOGGER_NAME));
		rm.bindFc("monolog-factory", lf);
		
		// Binds primitive components together
		cm.bindFc(BasicCacheManager.CACHE_ENTRY_FACTORY_BINDING, cef);
		cm.bindFc(BasicCacheManager.REPLACEMENT_MANAGER_BINDING, rm);
		rm.bindFc(AbstractReplacementManager.UNBIND_MANAGER_BINDING, cm);
		
		cm.startFc();
		return cm;
	}
	
	private static AbstractReplacementManager getRM(String policy) {
		if (LRU_POLICY.equals(policy)) {
			return new LRUReplacementManager();
		} else if (MRU_POLICY.equals(policy)) {
			return new MRUReplacementManager();
		} else if (FIFO_POLICY.equals(policy)) {
			return new FIFOReplacementManager();
		} else {
			return new LRUReplacementManager();
		}
	}
}