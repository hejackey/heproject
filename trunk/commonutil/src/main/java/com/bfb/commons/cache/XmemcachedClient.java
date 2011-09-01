package com.bfb.commons.cache;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;

public class XmemcachedClient {

	public static MemcachedClient getMemcachedClient() throws IOException{
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil
				.getAddresses("10.10.1.208:11211"));
		
		return builder.build();
	}
	
	public static MemcachedClient getClusterMemcachedClient() throws IOException{
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil
				.getAddresses("10.10.1.208:11211 10.10.1.212:11211"),new int[]{1,4});
		 
		return builder.build();
	}
	
	public static void TestXMemcache(MemcachedClient memcachedClient){
		try {
			int i;
			long time1 = System.currentTimeMillis();
			
			for(i=500;i<1500;i++){
				memcachedClient.set("xmemcache_"+i, 0, "Hello,xmemcached_"+i);
			}
			
			long time2 = System.currentTimeMillis();
			System.out.println("服务器总缓存数500，耗时："+(time2-time1));
			
			memcachedClient.shutdown();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void TestGetMemcache(MemcachedClient memcachedClient){
		try {
			int i;
			long time1 = System.currentTimeMillis();
			
			for(i=500;i<1500;i++){
				System.out.println(memcachedClient.get("xmemcache_"+i));
			}
			
			long time2 = System.currentTimeMillis();
			System.out.println("单服务器get1000，耗时："+(time2-time1));
			
			memcachedClient.shutdown();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void TestDelMemcache(MemcachedClient memcachedClient){
		try {
			int i;
			long time1 = System.currentTimeMillis();
			
			for(i=0;i<50;i++){
				System.out.println(memcachedClient.delete("xmemcache_"+i));
			}
			
			long time2 = System.currentTimeMillis();
			System.out.println("单服务器get50，耗时："+(time2-time1));
			
			memcachedClient.shutdown();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws InterruptedException, IOException{
		//TestClusterMemcache();
		
		//TestXMemcache(memcachedClient);
		//TestGetMemcache(memcachedClient);
		long time1= System.currentTimeMillis();
		for(int i=0;i<10;i++){
			MemcachedClient memcachedClient = XmemcachedClient.getMemcachedClient();
			new XMemcacheThread(memcachedClient).start();
		}
		long time2= System.currentTimeMillis();
		System.out.println("线程数10，耗时："+(time2-time1));
		//memcachedClient.shutdown();
		//TestDelMemcache(memcachedClient);
		/*Thread.sleep(3000);
		TestGetMemcache();*/
		/*MemcachedClient memcachedClient = XmemcachedClient.getMemcachedClient();
		
		try {
			int i;
			long time1 = System.currentTimeMillis();
			for(i=0;i<50;i++){
				memcachedClient.set("xmemcache_"+i, 0, "Hello,xmemcached");
				//memcachedClient.delete("xmemcache_"+i);
			}
			long time2 = System.currentTimeMillis();
			System.out.println("总缓存数1000，耗时："+(time2-time1));
			
			String value = memcachedClient.get("hello");
			System.out.println("hello=" + value);
			memcachedClient.delete("hello");
			value = memcachedClient.get("hello");
			System.out.println("hello=" + value);
		} catch (MemcachedException e) {
			System.err.println("MemcachedClient operation fail");
			e.printStackTrace();
		} catch (TimeoutException e) {
			System.err.println("MemcachedClient operation timeout");
			e.printStackTrace();
		} catch (InterruptedException e) {
			// ignore
		}
		try {
			// close memcached client
			memcachedClient.shutdown();
		} catch (IOException e) {
			System.err.println("Shutdown MemcachedClient fail");
			e.printStackTrace();
		}*/
	}
	
	static class XMemcacheThread extends Thread{
		private MemcachedClient memcachedClient;
		XMemcacheThread(MemcachedClient memcachedClient){
			this.memcachedClient = memcachedClient;
		}
		public void run(){
			int i;
			long time1 = System.currentTimeMillis();
			
			for(i=0;i<500;i++){
				try {
					memcachedClient.set("thread_xmemcache_"+i, 0, "Hello,xmemcached_"+i);
				} catch (TimeoutException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (MemcachedException e) {
					e.printStackTrace();
				}
			}
			
			long time2 = System.currentTimeMillis();
			System.out.println("服务器总缓存数50，耗时："+(time2-time1));
			
			try {
				memcachedClient.shutdown();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
