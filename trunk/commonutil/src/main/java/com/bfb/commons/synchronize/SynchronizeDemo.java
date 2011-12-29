package com.bfb.commons.synchronize;

import com.bfb.commons.string.StringUtil;

public class SynchronizeDemo {
	//private static int i=0;
	public static synchronized void testSynMethod(String threadName){
		int i=0;
		System.out.println(StringUtil.concatStr("#", threadName,"testSynMethod"));
		i++;
		System.out.println(i);
	}
	
	public static synchronized void testSecondSynMethod(String threadName){
		System.out.println(StringUtil.concatStr("#", threadName,"testOtherSynMethod"));
	}
	
	public synchronized void testThirdSynMethod(String threadName){
		System.out.println(StringUtil.concatStr("#", threadName,"testThirdSynMethod"));
	}
}
