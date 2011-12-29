package com.bfb.commons.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 * @author Administrator
 *
 */
public class ThreadPoolExecutorFactory {
	private static ThreadPoolExecutorFactory factory=null;
	private static final int CORE_POOL_NUM = 20;	
	private static final int MAX_POOL_NUM = 50;
	private static final int KEEP_ALIVE_TIME = 3;
	private static final int QUEUE_NUM = 3;
	public ThreadPoolExecutor excutor;
	
	private ThreadPoolExecutorFactory(){
		excutor = new ThreadPoolExecutor(CORE_POOL_NUM,MAX_POOL_NUM,KEEP_ALIVE_TIME,
											TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(QUEUE_NUM),
											new ThreadPoolExecutor.CallerRunsPolicy());
	}
	public static synchronized ThreadPoolExecutorFactory getInstance(){
		if(factory==null){
			factory = new ThreadPoolExecutorFactory();
		}
		
		return factory;
	}	
}
