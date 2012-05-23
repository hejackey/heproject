
/**
 * 读取配置文件
 * @author liuxueyong
 */
package com.moobao.config;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class PropertyConfiguration {

	/* property file identifier */
	private static String CONFIG_FILE = "app";
	//定义一个ResourceBundle对象.
	private static ResourceBundle bundle;

	//实例化ResourceBundle对象
	static {
		try {
			bundle = ResourceBundle.getBundle(CONFIG_FILE);
		} catch (MissingResourceException e) {
			e.printStackTrace();
		}
	}

	private static String getValue(String key) {
		return bundle.getString(key);
	}

	/**
	 * 返回手机索引文件存放的目录
	 * @param null
	 * @return String
	 * @exception no thrown Exception
	 */
	public static String getPhoneIndexPath() {
		String val = getValue("product.phoneIndex.directory");
		return val;
	}
	
	/**
	 * 返回手机索引备份存放的目录
	 * @param null
	 * @return String
	 * @exception no thrown Exception
	 */
	public static String getPhoneBackUpIndexPath() {
		String val = getValue("product.phoneBackUpIndex.directory");
		return val;
	}
	
	/**
	 * 返回配件索引文件存放的目录
	 * @param null
	 * @return String
	 * @exception no thrown Exception
	 */
	public static String getPeiJianIndexPath() {
		String val = getValue("product.peiJianIndex.directory");
		return val;
	}
	
	/**
	 * 返回配件索引备份存放的目录
	 * @param null
	 * @return String
	 * @exception no thrown Exception
	 */
	public static String getPeiJianBackUpIndexPath() {
		String val = getValue("product.peiJianBackUpIndex.directory");
		return val;
	}
	
	/**
	 * 返回配件基本索引文件存放的目录
	 * @param null
	 * @return String
	 * @exception no thrown Exception
	 */
	public static String getPeiJianBaseIndexPath() {
		String val = getValue("product.peiJianBaseIndex.directory");
		return val;
	}
	
	/**
	 * 返回配件基本索引备份存放的目录
	 * @param null
	 * @return String
	 * @exception no thrown Exception
	 */
	public static String getPeiJianBaseBackUpIndexPath() {
		String val = getValue("product.peiJianBaseBackUpIndex.directory");
		return val;
	}
	
	/**
	 * 返回资讯索引文件存放的目录
	 * @param null
	 * @return String
	 * @exception no thrown Exception
	 */
	public static String getResourceIndexPath() {
		String val = getValue("product.resourceIndex.directory");
		return val;
	}
	
	/**
	 * 返回资讯索引备份存放的目录
	 * @param null
	 * @return String
	 * @exception no thrown Exception
	 */
	public static String getResourceBackUpIndexPath() {
		String val = getValue("product.resourceBackUpIndex.directory");
		return val;
	}
	
	/**
	 * 返回娱乐中心索引文件存放的目录
	 * @param null
	 * @return String
	 * @exception no thrown Exception
	 */
	public static String getEntertainmentIndexPath() {
		String val = getValue("product.entertainmentIndex.directory");
		return val;
	}
	
	/**
	 * 返回娱乐中心索引备份存放的目录
	 * @param null
	 * @return String
	 * @exception no thrown Exception
	 */
	public static String getEntertainmentBackUpIndexPath() {
		String val = getValue("product.entertainmentBackUpIndex.directory");
		return val;
	}

	/**
	 * 返回上网本索引文件存放的目录
	 * @param null
	 * @return String
	 * @exception no thrown Exception
	 */
	public static String getNetComputerIndexPath() {
		String val = getValue("product.netComputerIndex.directory");
		return val;
	}
	
	/**
	 * 返回上网本索引备份存放的目录
	 * @param null
	 * @return String
	 * @exception no thrown Exception
	 */
	public static String getNetComputerBackUpIndexPath() {
		String val = getValue("product.netComputerBackUpIndex.directory");
		return val;
	}
	
	/**
	 * 返回所有产品索引文件存放的目录
	 * @param null
	 * @return String
	 * @exception no thrown Exception
	 */
	public static String getAllIndexPath() {
		String val = getValue("product.AllIndex.directory");
		return val;
	}
	
	/**
	 * 返回所有产品索引备份存放的目录
	 * @param null
	 * @return String
	 * @exception no thrown Exception
	 */
	public static String getAllBackUpIndexPath() {
		String val = getValue("product.AllBackUpIndex.directory");
		return val;
	}
	
	
	/**
	 * 返回词库文件存放的目录
	 * @param null
	 * @return String
	 * @exception no thrown Exception
	 */
	public static String getWordDictionary() {
		String val = getValue("word.dictionary.file");
		return val;
	}
	
	/**
	 * 返回索引过程优化参数(合并document和segment数量)
	 * @param null
	 * @return String
	 * @exception no thrown Exception
	 */
	public static String setMergeFactor() {
		String val = getValue("writer.setMergeFactor");
		return val;
	}
	
	/**
	 * 返回索引过程优化参数(一个segment包括的最大document数量)
	 * @param null
	 * @return String
	 * @exception no thrown Exception
	 */
	public static String setMaxMergeDocs() {
		String val = getValue("writer.setMaxMergeDocs");
		return val;
	}

	/**
	 * 返回索引过程优化参数(内存包括的最大document数量)
	 * @param null
	 * @return String
	 * @exception no thrown Exception
	 */
	public static String setMaxBufferedDocs() {
		String val = getValue("writer.setMaxBufferedDocs");
		return val;
	}
	
	/**
	 * 返回高级搜索中关键字存放文件
	 * @param null
	 * @return String
	 * @exception no thrown Exception
	 */
	public static String getHighPropertyPath() {
		String val = getValue("word.HighProperty.file");
		return val;
	}
}
