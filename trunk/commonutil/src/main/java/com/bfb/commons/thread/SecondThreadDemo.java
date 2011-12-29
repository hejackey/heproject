package com.bfb.commons.thread;

import java.util.logging.Logger;

import com.bfb.commons.log.LogManager;
import com.bfb.commons.string.StringUtil;
import com.bfb.commons.synchronize.SynchronizeDemo;

public class SecondThreadDemo implements ThreadTask {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2331583579158173527L;
	public SynchronizeDemo demo;
	private static Logger log = LogManager.getInstantce().getLogger(SecondThreadDemo.class);
	
	public SecondThreadDemo(SynchronizeDemo idemo){
		demo = idemo;
	}
	
	@Override
	public void run() {
		for(int i=0;i<50;i++){
			demo.testSecondSynMethod(Thread.currentThread().getName());
			//demo.testOtherSynMethod(Thread.currentThread().getName());
			log.info(StringUtil.concatStr(" ",Thread.currentThread().getName(),"多线程写文件"));
		}
	}
	
	public static void main(String[] args) {
		//演示不同对象在不同线程中调用同步的方法
		SynchronizeDemo sd= new SynchronizeDemo();
		SynchronizeDemo sd1= new SynchronizeDemo();
		new Thread(new ThreadDemo(sd1)).start();
		new Thread(new SecondThreadDemo(sd)).start();
		/*ThreadPoolExecutorFactory factory = ThreadPoolExecutorFactory.getInstance();
		factory.excutor.execute(new ThreadDemo());*/
		System.exit(0);
	}

}
