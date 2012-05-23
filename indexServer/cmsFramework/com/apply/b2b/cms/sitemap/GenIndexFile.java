package com.apply.b2b.cms.sitemap;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.zip.GZIPOutputStream;

import com.apply.b2b.cms.config.ConfigBuilderFactory;

/**
 * 
 * @author luoweifeng
 *
 */

public class GenIndexFile {
	private boolean deleteFile(String targetName){
		boolean rtn = false;
		try
		{

			String path = ConfigBuilderFactory.getConfigBuilder().getProperty("cms.system.base.path");
			File file = new File(path);
			if ((file.exists()) && (file.exists()))
			{
				String[] fileList = file.list();
				for (int i = 0; i < fileList.length; i++)
				{
					if (fileList[i].endsWith(targetName))
					{
						File subfile = new File(path + "/" + fileList[i]);
						subfile.delete();
					}
				}
			}
			rtn = true;
		}
		catch (Exception e)
		{
			rtn = false;
		}
		return rtn;
	}
	private boolean getIndexSMfile()
	{
		boolean rtn = false;
		String path = ConfigBuilderFactory.getConfigBuilder().getProperty("SYS_BASE_PATH");
		SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
		String sdate = "";
		sdate = dformat.format(new Date());
		try
		{

			FileWriter fr = null;
			BufferedWriter br = null;
			StringBuffer sb = new StringBuffer();
			StringBuffer nameBuffer = new StringBuffer();
			nameBuffer.append(path).append("/").append("madeinchina").append("Sitemap").append(".xml");
			fr = new FileWriter(nameBuffer.toString());
			br = new BufferedWriter(fr);
			File file = new File(path);
			sb.append("<?xml version='1.0' encoding='UTF-8'?>\r\n");
			sb.append("<sitemapindex xmlns='http://www.google.com/schemas/sitemap/0.84'>\r\n");
			if (file.exists() && (file.isDirectory()))
			{
				String[] mapIndex = file.list();
				for (int i = 0; i < mapIndex.length; i++)
				{
					String fileName = mapIndex[i];
					if (fileName.endsWith("sitemap.xml"))
					{
						sb.append("<sitemap>\r\n");
						sb.append("<loc>http://www.madeinchina.com/").append(fileName).append(".gz</loc>");
						sb.append("<lastmod>").append(sdate).append("</lastmod>");
						sb.append("</sitemap>\r\n");

					}
				}

			}
			sb.append("</sitemapindex>\r\n");
			br.write(sb.toString());
			br.close();
			fr.close();
			rtn = true;
		}
		catch (Exception e)
		{
			rtn = false;
		}
		return rtn;

	}
	private boolean CompressXmlFile()
	{
		boolean rtn = false;
		try
		{

			String path = ConfigBuilderFactory.getConfigBuilder().getProperty("SYS_BASE_PATH");
			File file = new File(path);
			if (file.exists()) 
			{
				String[] fileList = file.list();
				for (int i = 0; i < fileList.length; i++)
				{
					if (fileList[i] != null && fileList[i].endsWith("map.xml"))
					{
						CompressToGz(path + "/" + fileList[i]);
					}
				}
			}
			System.out.println("SiteMap GZ 压缩文件生成接收。");
			rtn = true;
		}
		catch (Exception e)
		{
			rtn = false;
			e.printStackTrace();
		}
		return rtn;
	}
	private boolean CompressToGz(String fileName)
	{

		boolean rtn = false;
		StringBuffer nameInBuffer = new StringBuffer();
		nameInBuffer.append(fileName);
		StringBuffer nameOutBuffer = new StringBuffer();
		nameOutBuffer.append(fileName).append(".gz");
		try
		{
			// Create the GZIP output stream
			GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream(nameOutBuffer.toString()));
			FileInputStream in = new FileInputStream(nameInBuffer.toString());
			// Transfer bytes from the input file to the GZIP output stream
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0)
			{
				out.write(buf, 0, len);
			}
			in.close();
			out.finish();
			out.close();
			rtn = true;
		}
		catch (IOException e)
		{
			rtn = false;
		}

		return rtn;
	}
	private void genCatFile(){
		String path = ConfigBuilderFactory.getConfigBuilder().getProperty("SYS_BASE_PATH");
		try
		{

			FileWriter fr = null;
			BufferedWriter br = null;
			StringBuffer sb = new StringBuffer();
			StringBuffer nameBuffer = new StringBuffer();
			nameBuffer.append(path).append("/").append("madeinchinacategorysitemap.xml");
			fr = new FileWriter(nameBuffer.toString());
			br = new BufferedWriter(fr);
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
			sb.append("<urlset xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.google.com/schemas/sitemap/0.84 http://www.google.com/schemas/sitemap/0.84/sitemap.xsd\" xmlns=\"http://www.google.com/schemas/sitemap/0.84\">\r\n");
			sb.append("\r\n");
			sb.append("<url>\r\n");
			sb.append("<loc>http://www.madeinchina.com/Agriculture.shtml</loc>\r\n");
			sb.append("<priority>0.8</priority>\r\n");
			sb.append("<lastmod>2007-10-12</lastmod>\r\n");
			sb.append("<changefreq>daily</changefreq>\r\n");
			sb.append("</url>\r\n");
			sb.append("\r\n");
			sb.append("<url>\r\n");
			sb.append("<loc>http://www.madeinchina.com/Food-Beverage.shtml</loc>\r\n");
			sb.append("<priority>0.8</priority>\r\n");
			sb.append("<lastmod>2007-10-12</lastmod>\r\n");
			sb.append("<changefreq>daily</changefreq>\r\n");
			sb.append("</url>\r\n");
			sb.append("\r\n");
			sb.append("<url>\r\n");
			sb.append("<loc>http://www.madeinchina.com/Automobile-Supplies-Accessories.shtml</loc>\r\n");
			sb.append("<priority>0.8</priority>\r\n");
			sb.append("<lastmod>2007-10-12</lastmod>\r\n");
			sb.append("<changefreq>daily</changefreq>\r\n");
			sb.append("</url>\r\n");
			sb.append("\r\n");
			sb.append("<url>\r\n");
			sb.append("<loc>http://www.madeinchina.com/Computer-PC-Peripherals.shtml</loc>\r\n");
			sb.append("<priority>0.8</priority>\r\n");
			sb.append("<lastmod>2007-10-12</lastmod>\r\n");
			sb.append("<changefreq>daily</changefreq>\r\n");
			sb.append("</url>\r\n");
			sb.append("\r\n");
			sb.append("<url>\r\n");
			sb.append("<loc>http://www.madeinchina.com/Electronics-Electrical.shtml</loc>\r\n");
			sb.append("<priority>0.8</priority>\r\n");
			sb.append("<lastmod>2007-10-12</lastmod>\r\n");
			sb.append("<changefreq>daily</changefreq>\r\n");
			sb.append("</url>\r\n");
			sb.append("\r\n");
			sb.append("<url>\r\n");
			sb.append("<loc>http://www.madeinchina.com/Fashion.shtml</loc>\r\n");
			sb.append("<priority>0.8</priority>\r\n");
			sb.append("<lastmod>2007-10-12</lastmod>\r\n");
			sb.append("<changefreq>daily</changefreq>\r\n");
			sb.append("</url>\r\n");
			sb.append("\r\n");
			sb.append("<url>\r\n");
			sb.append("<loc>http://www.madeinchina.com/Furniture-Furnishing.shtml</loc>\r\n");
			sb.append("<priority>0.8</priority>\r\n");
			sb.append("<lastmod>2007-10-12</lastmod>\r\n");
			sb.append("<changefreq>daily</changefreq>\r\n");
			sb.append("</url>\r\n");
			sb.append("\r\n");
			sb.append("<url>\r\n");
			sb.append("<loc>http://www.madeinchina.com/Gifts-Crafts.shtml</loc>\r\n");
			sb.append("<priority>0.8</priority>\r\n");
			sb.append("<lastmod>2007-10-12</lastmod>\r\n");
			sb.append("<changefreq>daily</changefreq>\r\n");
			sb.append("</url>\r\n");
			sb.append("\r\n");
			sb.append("<url>\r\n");
			sb.append("<loc>http://www.madeinchina.com/Health-Beauty.shtml</loc>\r\n");
			sb.append("<priority>0.8</priority>\r\n");
			sb.append("<lastmod>2007-10-12</lastmod>\r\n");
			sb.append("<changefreq>daily</changefreq>\r\n");
			sb.append("</url>\r\n");
			sb.append("\r\n");
			sb.append("<url>\r\n");
			sb.append("<loc>http://www.madeinchina.com/Home-Products.shtml</loc>\r\n");
			sb.append("<priority>0.8</priority>\r\n");
			sb.append("<lastmod>2007-10-12</lastmod>\r\n");
			sb.append("<changefreq>daily</changefreq>\r\n");
			sb.append("</url>\r\n");
			sb.append("\r\n");
			sb.append("<url>\r\n");
			sb.append("<loc>http://www.madeinchina.com/Industrial-Supplies.shtml</loc>\r\n");
			sb.append("<priority>0.8</priority>\r\n");
			sb.append("<lastmod>2007-10-12</lastmod>\r\n");
			sb.append("<changefreq>daily</changefreq>\r\n");
			sb.append("</url>\r\n");
			sb.append("\r\n");
			sb.append("<url>\r\n");
			sb.append("<loc>http://www.madeinchina.com/Printing-Publishing.shtml</loc>\r\n");
			sb.append("<priority>0.8</priority>\r\n");
			sb.append("<lastmod>2007-10-12</lastmod>\r\n");
			sb.append("<changefreq>daily</changefreq>\r\n");
			sb.append("</url>\r\n");
			sb.append("\r\n");
			sb.append("<url>\r\n");
			sb.append("<loc>http://www.madeinchina.com/Telecommunication-Products.shtml</loc>\r\n");
			sb.append("<priority>0.8</priority>\r\n");
			sb.append("<lastmod>2007-10-12</lastmod>\r\n");
			sb.append("<changefreq>daily</changefreq>\r\n");
			sb.append("</url>\r\n");
			sb.append("\r\n");
			sb.append("<url>\r\n");
			sb.append("<loc>http://www.madeinchina.com/Office-Stationery.shtml</loc>\r\n");
			sb.append("<priority>0.8</priority>\r\n");
			sb.append("<lastmod>2007-10-12</lastmod>\r\n");
			sb.append("<changefreq>daily</changefreq>\r\n");
			sb.append("</url>\r\n");
			sb.append("\r\n");
			sb.append("<url>\r\n");
			sb.append("<loc>http://www.madeinchina.com/Minerals-Metals-Materials.shtml</loc>\r\n");
			sb.append("<priority>0.8</priority>\r\n");
			sb.append("<lastmod>2007-10-12</lastmod>\r\n");
			sb.append("<changefreq>daily</changefreq>\r\n");
			sb.append("</url>\r\n");
			sb.append("\r\n");
			sb.append("<url>\r\n");
			sb.append("<loc>http://www.madeinchina.com/Security-Supplies.shtml</loc>\r\n");
			sb.append("<priority>0.8</priority>\r\n");
			sb.append("<lastmod>2007-10-12</lastmod>\r\n");
			sb.append("<changefreq>daily</changefreq>\r\n");
			sb.append("</url>\r\n");
			sb.append("\r\n");
			sb.append("<url>\r\n");
			sb.append("<loc>http://www.madeinchina.com/Service.shtml</loc>\r\n");
			sb.append("<priority>0.8</priority>\r\n");
			sb.append("<lastmod>2007-10-12</lastmod>\r\n");
			sb.append("<changefreq>daily</changefreq>\r\n");
			sb.append("</url>\r\n");
			sb.append("\r\n");
			sb.append("<url>\r\n");
			sb.append("<loc>http://www.madeinchina.com/Excess-Inventory.shtml</loc>\r\n");
			sb.append("<priority>0.8</priority>\r\n");
			sb.append("<lastmod>2007-10-12</lastmod>\r\n");
			sb.append("<changefreq>daily</changefreq>\r\n");
			sb.append("</url>\r\n");
			sb.append("\r\n");
			sb.append("<url>\r\n");
			sb.append("<loc>http://www.madeinchina.com/Environment.shtml</loc>\r\n");
			sb.append("<priority>0.8</priority>\r\n");
			sb.append("<lastmod>2007-10-12</lastmod>\r\n");
			sb.append("<changefreq>daily</changefreq>\r\n");
			sb.append("</url>\r\n");
			sb.append("\r\n");
			sb.append("<url>\r\n");
			sb.append("<loc>http://www.madeinchina.com/Energy.shtml</loc>\r\n");
			sb.append("<priority>0.8</priority>\r\n");
			sb.append("<lastmod>2007-10-12</lastmod>\r\n");
			sb.append("<changefreq>daily</changefreq>\r\n");
			sb.append("</url>\r\n");
			sb.append("\r\n");
			sb.append("<url>\r\n");
			sb.append("<loc>http://www.madeinchina.com/Construction-Real-Estate.shtml</loc>\r\n");
			sb.append("<priority>0.8</priority>\r\n");
			sb.append("<lastmod>2007-10-12</lastmod>\r\n");
			sb.append("<changefreq>daily</changefreq>\r\n");
			sb.append("</url>\r\n");
			sb.append("\r\n");
			sb.append("<url>\r\n");
			sb.append("<loc>http://www.madeinchina.com/Luggage-Bags-Cases.shtml</loc>\r\n");
			sb.append("<priority>0.8</priority>\r\n");
			sb.append("<lastmod>2007-10-12</lastmod>\r\n");
			sb.append("<changefreq>daily</changefreq>\r\n");
			sb.append("</url>\r\n");
			sb.append("\r\n");
			sb.append("<url>\r\n");
			sb.append("<loc>http://www.madeinchina.com/Toy-Game-Hobby.shtml</loc>\r\n");
			sb.append("<priority>0.8</priority>\r\n");
			sb.append("<lastmod>2007-10-12</lastmod>\r\n");
			sb.append("<changefreq>daily</changefreq>\r\n");
			sb.append("</url>\r\n");
			sb.append("\r\n");
			sb.append("<url>\r\n");
			sb.append("<loc>http://www.madeinchina.com/Sports-Recreation.shtml</loc>\r\n");
			sb.append("<priority>0.8</priority>\r\n");
			sb.append("<lastmod>2007-10-12</lastmod>\r\n");
			sb.append("<changefreq>daily</changefreq>\r\n");
			sb.append("</url>\r\n");
			sb.append("\r\n");
			sb.append("<url>\r\n");
			sb.append("<loc>http://www.madeinchina.com/Textiles-Leather-Products.shtml</loc>\r\n");
			sb.append("<priority>0.8</priority>\r\n");
			sb.append("<lastmod>2007-10-12</lastmod>\r\n");
			sb.append("<changefreq>daily</changefreq>\r\n");
			sb.append("</url>\r\n");
			sb.append("\r\n");
			sb.append("<url>\r\n");
			sb.append("<loc>http://www.madeinchina.com/Chemicals-Related-Products.shtml</loc>\r\n");
			sb.append("<priority>0.8</priority>\r\n");
			sb.append("<lastmod>2007-10-12</lastmod>\r\n");
			sb.append("<changefreq>daily</changefreq>\r\n");
			sb.append("</url>\r\n");
			sb.append("\r\n");
			sb.append("<url>\r\n");
			sb.append("<loc>http://www.madeinchina.com/Packaging-Materials-Equipment.shtml</loc>\r\n");
			sb.append("<priority>0.8</priority>\r\n");
			sb.append("<lastmod>2007-10-12</lastmod>\r\n");
			sb.append("<changefreq>daily</changefreq>\r\n");
			sb.append("</url>\r\n");
			sb.append("\r\n");
			sb.append("<url>\r\n");
			sb.append("<loc>http://www.madeinchina.com/Transportation.shtml</loc>\r\n");
			sb.append("<priority>0.8</priority>\r\n");
			sb.append("<lastmod>2007-10-12</lastmod>\r\n");
			sb.append("<changefreq>daily</changefreq>\r\n");
			sb.append("</url>\r\n");
			sb.append("</urlset>");
			br.write(sb.toString());
			br.close();
			fr.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
	
	public boolean perform(){
		boolean flag = true;
		deleteFile("map.xml.gz");
		genCatFile();
		getIndexSMfile();
		CompressXmlFile();
		deleteFile("map.xml");
		return flag;
	}
}
