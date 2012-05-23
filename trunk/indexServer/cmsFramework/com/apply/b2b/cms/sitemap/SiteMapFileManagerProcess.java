package com.apply.b2b.cms.sitemap;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.apply.b2b.cms.config.ConfigBuilderFactory;
import com.apply.b2b.cms.util.FileManagerProcess;
import com.apply.b2b.cms.util.StringUtil;

/**
 * 
 * @author luoweifeng
 * 
 */

public class SiteMapFileManagerProcess extends FileManagerProcess {
	private List<String> listUrls = new ArrayList<String>();
	private long accessNum = 0;
	private int MAX_NUM = 40000;
	private int genFileIndex = 0;
	
	private boolean accept(File dir) {
		if (dir != null && dir.isFile()) {
			String name = dir.getName();
			if (name != null && name.trim().length() > 2) {
				return name.endsWith(".shtml");
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public synchronized void process(File dir) {
		if (dir != null && dir.isFile()) {
			if (accept(dir)) {
				listUrls.add(getFileWWWUrl(dir));
				if (listUrls.size() == this.MAX_NUM) {
					genFileIndex++;
					accessNum++;
					genSMfile(listUrls, genFileIndex);
					listUrls.clear();
				} else {
					accessNum++;
				}
			}
		}
	}
	
	private String getFileWWWUrl(File dir) {
		if (dir != null) {
			return StringUtil.Replace(dir.toString(), "/server/cms_b2b",
					"http://www.madeinchina.com");
		} else {
			return "";
		}
	}
	
	public synchronized void genSiteMapFile() {
		if (accessNum > 0 && listUrls.size() > 0) {
			genFileIndex++;
			genSMfile(listUrls, genFileIndex);
			listUrls.clear();
		}
	}
	
	private boolean genSMfile(List<String> list, int index) {
		boolean ret = false;
		SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
		try {
			FileWriter fr = null;
			BufferedWriter br = null;
			StringBuffer sb = new StringBuffer();
			
			String path = ConfigBuilderFactory.getConfigBuilder().getProperty("cms.system.base.path");
			
			StringBuffer nameBuffer = new StringBuffer();
			nameBuffer.append(path).append("/hot-search/").append(
					"20080201hotsearchsitemap-").append(index).append(".xml");
			
			String sdate = dformat.format(new Date());
			fr = new FileWriter(nameBuffer.toString());
			
			br = new BufferedWriter(fr);
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
			sb
					.append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\r\n");
			sb.append("<url>\r\n");
			sb.append("<loc>http://www.madeinchina.com</loc>\r\n");
			sb.append("<priority>1</priority>\r\n");
			sb.append("<lastmod>" + sdate + "</lastmod>\r\n");
			sb.append("<changefreq>daily</changefreq>\r\n");
			sb.append("</url>\r\n");
			
			for (int i = 0; i < list.size(); i++) {
				String aUrl = list.get(i);
				sb.append("<url>\r\n");
				sb.append("<loc>" + aUrl + "</loc>\r\n");
				sb.append("<priority>0.7</priority>\r\n");
				sb.append("<lastmod>" + sdate + "</lastmod>\r\n");
				sb.append("<changefreq>daily</changefreq>\r\n");
				sb.append("</url>\r\n");
			}
			
			sb.append("</urlset>\r\n");
			br.write(sb.toString());
			br.close();
			fr.close();
			ret = true;
		} catch (Exception e) {
			e.printStackTrace();
			ret = false;
		}
		return ret;
	}
	
	public long getAccessNum() {
		return accessNum;
	}
}