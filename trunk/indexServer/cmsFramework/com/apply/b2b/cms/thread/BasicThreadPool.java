package com.apply.b2b.cms.thread;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author luoweifeng
 * 
 */

public class BasicThreadPool {
	private static ThreadPool threadPool = null;
	
	private BasicThreadPool() {
	}
	
	private static synchronized ThreadPool getThreadPool() {
		if (threadPool == null) {
			threadPool = ThreadPoolFactory.createThreadPool();
		}
		return threadPool;
	}
	
	public static Future<?> submit(Runnable task) {
		if (task != null) {
			return getThreadPool().submit(task);
		} else {
			return null;
		}
	}
	
	public static boolean remove(Runnable task) {
		if (task != null) {
			return getThreadPool().remove(task);
		}else{
			return false;
		}
	}
	
	public static boolean isShutdown() {
		return getThreadPool().isShutdown();
	}
	
	public static int getActiveCount() {
		return getThreadPool().getActiveCount();
	}
	
	public static long getCompletedTaskCount() {
		return getThreadPool().getCompletedTaskCount();
	}
	
	public static long getTaskCount() {
		return getThreadPool().getTaskCount();
	}
	
	public static void shutdown() {
		getThreadPool().shutdown();
	}
	
	public static List<Runnable> shutdownNow() {
		return getThreadPool().shutdownNow();
	}
	
	public static void pause() {
		getThreadPool().pause();
	}
	
	public static void resume() {
		getThreadPool().resume();
	}
	
	public static int getPoolSize() {
		return getThreadPool().getPoolSize();
	}
	
	public static int getMaximumPoolSize() {
		return getThreadPool().getMaximumPoolSize();
	}
	
	public static long getKeepAliveTime() {
		return getThreadPool().getKeepAliveTime(TimeUnit.SECONDS);
	}
	
	public static int getLargestPoolSize() {
		return getThreadPool().getLargestPoolSize();
	}
	
	public static void setCorePoolSize(int val) {
		getThreadPool().setCorePoolSize(val);
	}
	
	public static void setMaximumPoolSize(int val) {
		getThreadPool().setMaximumPoolSize(val);
	}
	
	public static void setKeepAliveTime(long val) {
		getThreadPool().setKeepAliveTime(val, TimeUnit.SECONDS);
	}
}