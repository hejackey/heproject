package com.moobao.searchInterface;

import java.io.IOException;

import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.RAMDirectory;

import com.moobao.config.PropertyConfiguration;

/**
 * 获取各个搜索模块的IndexSearch对象.
 * 
 * @author liuxueyong
 */
public class SearchRAMInterface {

	private static IndexSearcher searcher_phone = null;
	private static IndexSearcher searcher_resource = null;
	private static IndexSearcher searcher_peijian = null;
	private static IndexSearcher searcher_entertainment = null;
	private static IndexSearcher searcher_all = null;
	/**
	 * 获取手机的IndexSearcher
	 * 
	 * @exception IOException
	 * @return IndexSearcher
	 */
	public static synchronized IndexSearcher getPhoneSearcher() {
		if (searcher_phone == null) {
			try {
				//将索引加载入内存.
				RAMDirectory ramDir = new RAMDirectory( PropertyConfiguration
						.getPhoneIndexPath() );
				searcher_phone = new IndexSearcher( ramDir );
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return searcher_phone;
	}

	/**
	 * 获取配件的IndexSearcher
	 * 
	 * @exception IOException
	 * @return IndexSearcher
	 */
	public static synchronized IndexSearcher getPeiJianSearcher() {

		if (searcher_peijian == null) {			
			try {
				//将索引加载入内存.
				RAMDirectory ramDir = new RAMDirectory( PropertyConfiguration
						.getPeiJianIndexPath() );
				searcher_peijian = new IndexSearcher( ramDir );
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return searcher_peijian;
	}

	/**
	 * 获取资讯的IndexSearcher
	 * 
	 * @exception IOException
	 * @return IndexSearcher
	 */
	public static synchronized IndexSearcher getResourceSearcher() {

		if (searcher_resource == null) {
			try {
				//将索引加载入内存.
				RAMDirectory ramDir = new RAMDirectory( PropertyConfiguration
						.getResourceIndexPath() );
				searcher_resource = new IndexSearcher( ramDir );
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return searcher_resource;
	}
	
	/**
	 * 获取娱乐中心的IndexSearcher
	 * 
	 * @exception IOException
	 * @return IndexSearcher
	 */
	public static synchronized IndexSearcher getEntertainmentSearcher() {

		if (searcher_entertainment == null) {
			try {
				//将索引加载入内存.
				RAMDirectory ramDir = new RAMDirectory( PropertyConfiguration
						.getEntertainmentIndexPath() );
				searcher_entertainment = new IndexSearcher( ramDir );
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return searcher_entertainment;
	}
	
	/**
	 * 获取所有商品的IndexSearcher
	 * 
	 * @exception IOException
	 * @return IndexSearcher
	 */
	public static synchronized IndexSearcher getAllProductSearcher() {
		if (searcher_all == null) {
			try {
				//将索引加载入内存.
				RAMDirectory ramDir = new RAMDirectory( PropertyConfiguration
						.getAllIndexPath() );
				searcher_all = new IndexSearcher( ramDir );
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return searcher_all;
	}
}