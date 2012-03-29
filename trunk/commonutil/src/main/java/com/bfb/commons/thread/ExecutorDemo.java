package com.bfb.commons.thread;

import java.util.concurrent.ExecutorService;


public class ExecutorDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorDemo tt = new ExecutorDemo();
		ExecutorService excutorService = ExecutorFactory.getInstance().getYtbParserExecutor();
		for(int i=0;i<10;i++){
			excutorService.execute(tt.new ThreadDemo());
		}		
		excutorService.shutdown();
		while (!excutorService.isTerminated()) { }{
			System.out.println("Finished all threads");
		}
         
		//new Thread(tt.new ThreadDemo()).start();
	}
	
	private class ThreadDemo implements Runnable{

		@Override
		public void run() {
			System.out.println("abc");
		}
		
	}
}
