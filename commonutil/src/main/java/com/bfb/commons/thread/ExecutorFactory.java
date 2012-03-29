package com.bfb.commons.thread;

import java.util.concurrent.ExecutorService;


import java.util.concurrent.Executors;



/**
 * 线程池工厂，返回线程池
 * @author daweiwang
 *
 */
public class ExecutorFactory {

	//youtube解析线程池
	private ExecutorService ytbParserExecutor;
	//youtube下载线程池
	private ExecutorService ytbDownExecutor;
	//播放页批处理线程池
	
	private static ExecutorFactory instance=null;
	
	private ExecutorFactory(){
		ytbParserExecutor=Executors.newFixedThreadPool(10);
		ytbDownExecutor=Executors.newFixedThreadPool(20);
	}
	
	public static ExecutorFactory getInstance(){
		if(instance==null){
			synchronized(ExecutorFactory.class){
				if(instance==null){
					instance=new ExecutorFactory();
				}
			}
		}
		return instance;
	}

	public ExecutorService getYtbParserExecutor() {
		return ytbParserExecutor;
	}

	public ExecutorService getYtbDownExecutor() {
		return ytbDownExecutor;
	}
}
