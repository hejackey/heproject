package com.bfb.commons.cache;

import java.io.IOException;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;

public class SpyMemcacheClient {
	public static MemcachedClient getMemcachedClient() throws IOException{
		MemcachedClient c=new MemcachedClient(
		        AddrUtil.getAddresses("10.10.1.208:11211"));
		
		return c;
	}
	
	public static MemcachedClient getClusterMemcachedClient() throws IOException{
		MemcachedClient c=new MemcachedClient(
		        AddrUtil.getAddresses("10.10.1.208:11211 10.10.1.212:11211"));
		 
		return c;
	}

	public static void TestSpyMemcache(MemcachedClient memcachedClient){
		try {
			int i;
			long time1 = System.currentTimeMillis();
			
			for(i=1500;i<2500;i++){
				memcachedClient.set("SpyMemcache_"+i, 0, "Hello,xmemcached_"+i);
				//System.out.println("SpyMemcache_"+i+"_"+f.get());
				//memcachedClient.delete("SpyMemcache_"+i);
			}
			
			long time2 = System.currentTimeMillis();
			System.out.println("服务器总缓存数1000，耗时："+(time2-time1));
			
			memcachedClient.shutdown();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void TestGetSpyMemcache(MemcachedClient memcachedClient){
		try {
			int i;
			long time1 = System.currentTimeMillis();
			
			for(i=1500;i<2500;i++){
				System.out.println(memcachedClient.get("SpyMemcache_" + i));
			}
			
			long time2 = System.currentTimeMillis();
			System.out.println("单服务器get1000，耗时："+(time2-time1));
			
			memcachedClient.shutdown();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void TestDelSpyMemcache(MemcachedClient memcachedClient){
		try {
			int i;
			long time1 = System.currentTimeMillis();
			
			for(i=0;i<500;i++){
				System.out.println(memcachedClient.delete("SpyMemcache_"+i));
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
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		MemcachedClient memcachedClient = SpyMemcacheClient.getMemcachedClient();
		//TestSpyMemcache(memcachedClient);
		//Thread.sleep(3000);
		TestGetSpyMemcache(memcachedClient);
		//TestDelSpyMemcache(memcachedClient);
		// Get a memcached client connected to several servers
		// over the binary protocol
		/*MemcachedClient c = new MemcachedClient(
				AddrUtil.getAddresses("10.10.1.208:11211"));
		MemcachedClient c=new MemcachedClient(
			    new InetSocketAddress("10.10.1.208", 11211));
		
		MemcachedClient c=new MemcachedClient(
		        new BinaryConnectionFactory(),
		        AddrUtil.getAddresses("10.10.1.208:11212 10.10.1.212:11212"));



		int i;
		long time1 = System.currentTimeMillis();
		for (i = 500; i < 1500; i++) {
			c.set("spymemcache_" + i, 0, "spymemcache_" + i);
			//c.delete("spymemcache_" + i);
			
		}
		long time2 = System.currentTimeMillis();
		System.out.println("总缓存数1000，耗时：" + (time2 - time1));
		
		int j;
		for(j=500;j<1500;j++){
			Future<Object> f = c.asyncGet("spymemcache_" + j);
			try {
				System.out.println(f.get(5, TimeUnit.SECONDS));
				// throws expecting InterruptedException, ExecutionException
				// or TimeoutException
			} catch (Exception e) {
			}
		}
		
		c.shutdown();*/
		// Try to get a value, for up to 5 seconds, and cancel if it
		// doesn't return
		/*Object myObj = null;
		Future<Object> f = c.asyncGet("someKey");
		try {
			myObj = f.get(5, TimeUnit.SECONDS);
			// throws expecting InterruptedException, ExecutionException
			// or TimeoutException
		} catch (Exception e) {
		}*/

	}

}
