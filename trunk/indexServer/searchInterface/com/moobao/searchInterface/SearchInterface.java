package com.moobao.searchInterface;

import java.io.IOException;

import org.apache.lucene.search.IndexSearcher;

import com.moobao.config.PropertyConfiguration;

/**
 * 获取各个搜索模块的IndexSearch对象.
 * 
 * @author liuxueyong
 */
public class SearchInterface {

	private static IndexSearcher searcher_phone = null;
	private static IndexSearcher searcher_resource = null;
	private static IndexSearcher searcher_peijian = null;
	private static IndexSearcher searcher_entertainment = null;
	private static IndexSearcher searcher_all = null;

	/**
	 * 获取手机的IndexSearcher
	 * 
	 * @exception IOException
	 * @return null
	 */
	public static synchronized IndexSearcher getPhoneSearcher() {
		if (searcher_phone == null) {
			try {
				searcher_phone = new IndexSearcher(PropertyConfiguration
						.getPhoneIndexPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return searcher_phone;
	}
	
	/**
	 * 重新打开手机的IndexSearcher
	 * 
	 * @exception IOException
	 * @return null
	 */
	public static synchronized IndexSearcher getReOpenPhoneSearcher() {
		if (searcher_phone != null) {
			try {
				searcher_phone.close();
				searcher_phone = new IndexSearcher(PropertyConfiguration
						.getPhoneIndexPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				searcher_phone = new IndexSearcher(PropertyConfiguration
						.getPhoneIndexPath());
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
	 * @return null
	 */
	public static synchronized IndexSearcher getPeiJianSearcher() {

		if (searcher_peijian == null) {
			try {
				searcher_peijian = new IndexSearcher(PropertyConfiguration
						.getPeiJianIndexPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return searcher_peijian;
	}
	
	/**
	 * 重新打开配件的IndexSearcher
	 * 
	 * @exception IOException
	 * @return null
	 */
	public static synchronized IndexSearcher getReOpenPeiJianSearcher() {
		if (searcher_peijian != null) {
			try {
				searcher_peijian.close();
				searcher_peijian = new IndexSearcher(PropertyConfiguration
						.getPeiJianIndexPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				searcher_peijian = new IndexSearcher(PropertyConfiguration
						.getPeiJianIndexPath());
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
	 * @return null
	 */
	public static synchronized IndexSearcher getResourceSearcher() {
		if (searcher_resource == null) {
			try {
				searcher_resource = new IndexSearcher(PropertyConfiguration
						.getResourceIndexPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return searcher_resource;
	}
	
	/**
	 * 重新打开资讯的IndexSearcher
	 * 
	 * @exception IOException
	 * @return null
	 */
	public static synchronized IndexSearcher getReOpenResourceSearcher() {
		if (searcher_resource != null) {
			try {
				searcher_resource.close();
				searcher_resource = new IndexSearcher(PropertyConfiguration
						.getResourceIndexPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				searcher_resource = new IndexSearcher(PropertyConfiguration
						.getResourceIndexPath());
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
	 * @return null
	 */
	public static synchronized IndexSearcher getEntertainmentSearcher() {

		if (searcher_entertainment == null) {
			try {
				searcher_entertainment = new IndexSearcher(PropertyConfiguration
						.getEntertainmentIndexPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return searcher_entertainment;
	}
	
	/**
	 * 重新打开娱乐中心的IndexSearcher
	 * 
	 * @exception IOException
	 * @return null
	 */
	public static synchronized IndexSearcher getReOpenEntertainmentSearcher() {
		if (searcher_entertainment != null) {
			try {
				searcher_entertainment.close();
				searcher_entertainment = new IndexSearcher(PropertyConfiguration
						.getEntertainmentIndexPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				searcher_entertainment = new IndexSearcher(PropertyConfiguration
						.getEntertainmentIndexPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return searcher_entertainment;
	}
	
	/**
	 * 获取所有产品的IndexSearcher
	 * 
	 * @exception IOException
	 * @return null
	 */
	public static synchronized IndexSearcher getAllProductSearcher() {
		if (searcher_all == null) {
			try {
				searcher_all = new IndexSearcher(PropertyConfiguration
						.getAllIndexPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return searcher_all;
	}
	
	/**
	 * 重新打开所有产品的IndexSearcher
	 * 
	 * @exception IOException
	 * @return null
	 */
	public static synchronized IndexSearcher getReOpenAllProductSearcher() {
		if (searcher_all != null) {
			try {
				searcher_all.close();
				searcher_all = new IndexSearcher(PropertyConfiguration
						.getAllIndexPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				searcher_all = new IndexSearcher(PropertyConfiguration
						.getAllIndexPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return searcher_all;
	}
}