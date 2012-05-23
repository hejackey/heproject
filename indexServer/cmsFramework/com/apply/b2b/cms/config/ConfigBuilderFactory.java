package com.apply.b2b.cms.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.AccessController;

import sun.security.action.GetPropertyAction;

import com.apply.b2b.cms.cache.BaseModifyTimeCachePool;
import com.apply.b2b.cms.cache.ICacheEntry;

/**
 * 
 * @author luoweifeng
 * 
 */

public class ConfigBuilderFactory {
	private static final String CONF_CACHE_PRENAME = "_cms_configfile";
	private static final String FSPROPERTY = "config.properties";

	public static String getConfigBuilderCacheName(String name) {
		return CONF_CACHE_PRENAME + "_" + name;
	}

	public static long getFileLastModified(String dir_path)
			throws FileNotFoundException {
		if (dir_path != null && dir_path.trim().length() > 0) {
			File file = new File(dir_path);

			if (!file.exists()) {
				throw new FileNotFoundException();
			}

			if (file.isDirectory()) {
				throw new FileNotFoundException();
			}

			return file.lastModified();
		} else {
			throw new FileNotFoundException();
		}
	}

	public static long getConfigFileLMTime(String name) {
		if (name != null && name.trim().length() > 0) {
			URL url = ConfigBuilderFactory.class.getResource(name);
			if (url != null) {
				String filePath = url.getPath();
				String ureDecodeFilePath = "";
				try {
					String urlEncodeName = (String) AccessController
							.doPrivileged(new GetPropertyAction("file.encoding"));
					
					ureDecodeFilePath = URLDecoder.decode(filePath,
							urlEncodeName);
				} catch (UnsupportedEncodingException e1) {
					ureDecodeFilePath = filePath;
					e1.printStackTrace();
				}
				
				try {
					return getFileLastModified(ureDecodeFilePath);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					System.out.println(filePath);
					return 0;
				}
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}
	
	public static long getConfigFileLMTime() {
		return getConfigFileLMTime("/" + FSPROPERTY);
	}
	
	public static ConfigBuilder getConfigBuilder() {
		return getConfigBuilder(FSPROPERTY);
	}

	public static ConfigBuilder getConfigBuilder(String fileName) {
		long lmTime = getConfigFileLMTime("/" + fileName);
		//long lmTime = 0;
		if (lmTime > 0) {
			String cacheName = getConfigBuilderCacheName(fileName);
			ICacheEntry chEntry = BaseModifyTimeCachePool
					.get(cacheName, lmTime);
			if (chEntry != null) {
				Object ceV = chEntry.getCeObject();
				if (ceV != null) {
					return (ConfigBuilder) ceV;
				} else {
					ConfigBuilder vObj = new ConfigBuilder(fileName);
					BaseModifyTimeCachePool.put(cacheName, vObj, lmTime);
					return vObj;
				}
			} else {
				ConfigBuilder vObj = new ConfigBuilder(fileName);
				BaseModifyTimeCachePool.put(cacheName, vObj, lmTime);
				return vObj;
			}
		} else {
			ConfigBuilder vObj = new ConfigBuilder(fileName);
			return vObj;
		}
	}

	public static void main(String[] argv) {
		// ConfigBuilder dd = ConfigBuilderFactory.getConfigBuilder();
		// System.out.println(dd.getProperty("cms.system.domain"));

		// getConfigFileLMTime();
		getConfigFileLMTime("/罗伟峰.confif");
		// try {
		// getFileLastModified(java.net.URLDecoder.decode("/C:/Program%20Files/Apache%20Software%20Foundation/Tomcat%206.0/webapps/cms/WEB-INF/classes/config.properties"));
		// } catch (FileNotFoundException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }

		// try {
		// dd.toStoreFile("D:/configtest.property");
		// dd.toStoreXMLFile("D:/configtest.xml");
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}
}