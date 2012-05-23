package com.apply.b2b.cms.sitemap;

import java.io.File;

import com.apply.b2b.cms.util.FileManager;

/**
 * 
 * @author luoweifeng
 *
 */

public class ExampleSiteMap {
	private String filePaht = "/server/cms_b2b/hot-search";
	
	public ExampleSiteMap() {
	}
	
	public ExampleSiteMap(String filePaht) {
		super();
		this.filePaht = filePaht;
	}
	
	public boolean perform() {
		try {
			FileManager fileManager = new FileManager();
			SiteMapFileManagerProcess siteMapFileManagerProcess = new SiteMapFileManagerProcess();

			File file = new File(filePaht);

			fileManager.visitAllFiles(file, siteMapFileManagerProcess);
			siteMapFileManagerProcess.genSiteMapFile();

			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] arg) {
		ExampleSiteMap hotSearchSiteMapNew = new ExampleSiteMap();
		hotSearchSiteMapNew.perform();
	}
}
