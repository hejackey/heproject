package com.apply.b2b.cms.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import com.apply.b2b.cms.config.ConfigBuilder;
import com.apply.b2b.cms.config.ConfigBuilderFactory;

/**
 * 
 * @author luoweifeng
 * 
 */

public class ThreadPoolFactory {
	
	private ThreadPoolFactory() {
	}
	
	public static ThreadPool createThreadPool() {
		LinkedBlockingQueue<Runnable> queue = null;
		
		ConfigBuilder propertyBuilder = ConfigBuilderFactory.getConfigBuilder();
		
		// Constants.THREADPOOL_MAXIMUMTASKQUEUESIZE
		int maxTaskQueueSize = propertyBuilder.getIntProperty(
				"cms.threadpool.maximumTaskQueueSize", 100);
		
		// Constants.THREADPOOL_COREPOOLSIZE
		int corePoolSize = propertyBuilder.getIntProperty(
				"cms.threadpool.corePoolSize", 20);
		
		// Constants.THREADPOOL_MAXIMUMPOOLSIZE
		int maxPoolSize = propertyBuilder.getIntProperty(
				"cms.threadpool.maximumPoolSize", 100);
		
		// Constants.THREADPOOL_KEEPALIVETIME
		int keepAliveTime = propertyBuilder.getIntProperty(
				"cms.threadpool.keepAliveTime", 5);
		
		if (maxTaskQueueSize <= 0) {
			queue = new LinkedBlockingQueue<Runnable>();
		} else {
			queue = new LinkedBlockingQueue<Runnable>(maxTaskQueueSize);
		}
		return new ThreadPool(corePoolSize, maxPoolSize, keepAliveTime,
				TimeUnit.SECONDS, queue);
	}
	
	public static ThreadPool createThreadPool(int corePoolSize,
			int maxPoolSize, int keepAliveTime, int maxTaskQueueSize) {
		LinkedBlockingQueue<Runnable> queue = null;
		if (maxTaskQueueSize <= 0) {
			queue = new LinkedBlockingQueue<Runnable>();
		} else {
			queue = new LinkedBlockingQueue<Runnable>(maxTaskQueueSize);
		}
		return new ThreadPool(corePoolSize, maxPoolSize, keepAliveTime,
				TimeUnit.SECONDS, queue);
	}
}