package com.bfb.commons.cache;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class JavaMemcacheClient {
	protected static final MemCachedClient mcc = new MemCachedClient();

	static {
		String serversV = "10.10.1.206:11211";	//使用magent的代理缓存服务器地址
		String weightsV = "1";

		String[] servers = serversV.split(",");

		String[] weightsi = weightsV.split(",");
		Integer[] weights = new Integer[weightsi.length];

		for (int i = 0; i < weightsi.length; i++) {
			weights[i] = Integer.parseInt(weightsi[i]);
		}

		// 创建一个实例对象SockIOPool
		SockIOPool pool = SockIOPool.getInstance();
		// 设置Memcached Server
		pool.setServers(servers);
		pool.setWeights(weights);
		// set some basic pool settings
		// 5 initial, 5 min, and 250 max conns
		// and set the max idle time for a conn to 3 hours
		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(250);
		pool.setMaxIdle(1000 * 60 * 60 * 3);
		// set the sleep for the maint thread
		// it will wake up every x seconds and
		// maintain the pool size
		pool.setMaintSleep(2000);
		// Tcp的规则就是在发送一个包之前，本地机器会等待远程主机
		// 对上一次发送的包的确认信息到来；这个方法就可以关闭套接字的缓存
		// 以至这个包准备好了就发
		pool.setNagle(false);
		// 连接建立后对超时的控制
		pool.setSocketTO(3000);
		// 连接建立时对超时的控制
		pool.setSocketConnectTO(0);
		// initialize the connection pool
		// 初始化一些值并与MemcachedServer段建立连接
		pool.initialize();
		// lets set some compression on for the client
		// compress anything larger than 64k
		mcc.setCompressEnable(true);
		mcc.setCompressThreshold(64 * 1024);
	}

	public static MemCachedClient getmcc() {
		return mcc;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*MemCachedClient mcc = JavaMemcacheClient.getmcc();
		
		int i;
		long time1 = System.currentTimeMillis();
		for(i=500;i<1500;i++){
			mcc.set("javamemcache_"+i, "Hello,xmemcached_"+i);
			//mcc.delete("javamemcache_"+i);
		}
		long time2 = System.currentTimeMillis();
		System.out.println("总缓存数500，耗时："+(time2-time1));*/
		
		
		MemCachedClient memcachedClient = JavaMemcacheClient.getmcc();
		for(int i=0;i<100;i++){
			memcachedClient.set("mobile_"+i,"12345");
		}
		
		/*for(int j=0;j<100;j++){
			System.out.println(memcachedClient.get("mobile_"+j));
		}
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		/*for(int i=0;i<50;i++){
			new JavaMemcacheThread(memcachedClient).start();
		}
		long time2= System.currentTimeMillis();
		System.out.println("线程数10，耗时："+(time2-time1));*/
	}
	
	static class JavaMemcacheThread extends Thread{
		private MemCachedClient memcachedClient;
		JavaMemcacheThread(MemCachedClient memcachedClient){
			this.memcachedClient = memcachedClient;
		}
		public void run(){
			int i;
			long time1 = System.currentTimeMillis();
			
			for(i=0;i<100;i++){
					memcachedClient.set("thread_javamemcache_"+i,  "Hello,javamemcached_"+i);
			}
			
			long time2 = System.currentTimeMillis();
			System.out.println("服务器总缓存数50，耗时："+(time2-time1));
			
			
		}
	}

}
