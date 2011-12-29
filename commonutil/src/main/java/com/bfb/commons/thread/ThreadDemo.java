package com.bfb.commons.thread;


import java.util.logging.Logger;

import com.bfb.commons.log.LogManager;
import com.bfb.commons.string.StringUtil;
import com.bfb.commons.synchronize.SynchronizeDemo;

public class ThreadDemo implements ThreadTask {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2035542097552376181L;
	public SynchronizeDemo demo;
	private static Logger log = LogManager.getInstantce().getLogger(ThreadDemo.class);
	
	public ThreadDemo(SynchronizeDemo idemo){
		demo = idemo;
	}
	@Override
	public void run() {
		for(int i=0;i<50;i++){
			demo.testSecondSynMethod(Thread.currentThread().getName());
			log.info(StringUtil.concatStr(" ",Thread.currentThread().getName(),"多线程写文件"));
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//演示相同对象在不同线程中调用同步的方法
		SynchronizeDemo sd= new SynchronizeDemo();
		
		for(int i=0;i<20;i++){
			new Thread(new ThreadDemo(sd)).start();
			new Thread(new SecondThreadDemo(sd)).start();
		}
		/*ThreadPoolExecutorFactory factory = ThreadPoolExecutorFactory.getInstance();
		factory.excutor.execute(new ThreadDemo());*/
		//System.exit(0);
	}

	public static String concatStr(String splitStr,String... strAry){
		StringBuffer sb = new StringBuffer();
		int len = strAry.length;
		for(int i=0;i<len;i++){
			
			sb.append(strAry[i]);
			if(i<len-1)
				sb.append(splitStr);
		}
		return sb.toString();
	}
}
