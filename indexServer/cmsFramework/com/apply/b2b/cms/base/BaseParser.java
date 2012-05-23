package com.apply.b2b.cms.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.objectweb.perseus.cache.api.CacheEntry;
import org.objectweb.perseus.cache.api.CacheException;
import org.objectweb.perseus.cache.lib.BasicCacheManager;

import com.apply.b2b.cms.cache.BasicModifyTimeCacheEntry;
import com.apply.b2b.cms.cache.CacheManagerUtil;
import com.apply.b2b.cms.config.ConfigBuilderFactory;
import com.apply.b2b.cms.pageportlet.base.BasePagePortlet;
import com.apply.b2b.cms.project.ProjectManager;
import com.apply.b2b.cms.registry.PagePortletRegistry;
import com.apply.b2b.cms.util.VelocityEngineUtil;

/**
 * 
 * @author luoweifeng
 * 
 */

public abstract class BaseParser implements IParser, IOwner {
	protected final Logger log = Logger.getLogger(this.getClass());
	
	protected final String fileOutputPath = ConfigBuilderFactory
			.getConfigBuilder().getProperty("cms.system.base.path");
	
	protected VelocityEngine ve = VelocityEngineUtil.getVelocityEngine();

	private static final BasicCacheManager cm = new CacheManagerUtil()
			.creatCacheManager();

	private IProject ownerProject = null;

	protected String fileExt = ".shtml";

	public BaseParser() {
	}
	
	public IProject getOwnerProject() {
		if (ownerProject == null) {
			ownerProject = ProjectManager.defaultProject();
		}
		return ownerProject;
	}
	
	public void setOwnerProject(IProject ownerProject) {
		this.ownerProject = ownerProject;
	}
	
	/**
	 * 根据prjName和portletName 获得一个PagePortlet
	 * @param prjName
	 * @param portletName
	 * @return
	 */
	
	public BasePagePortlet getPagePortlet(String prjName, String portletName) {
		if (portletName != null && portletName.trim().length() > 0) {
			BasePagePortlet aObj = null;
			if (prjName != null && prjName.trim().length() > 0) {
				aObj = PagePortletRegistry.getPagePortlet(prjName, portletName);
				if (aObj != null) {
					aObj.init();
				}
			} else {
				aObj = PagePortletRegistry.getPagePortlet(portletName);
				if (aObj != null) {
					aObj.init();
				}
			}
			return aObj;
		} else {
			return null;
		}
	}

	/**
	 * @param fileName
	 * @param fileContent
	 */
	public boolean writeFileAllDir(String fileName, String fileContent) {
		try {
			if (fileName.lastIndexOf("/") > 0) {
				File dfile = new File(fileName.substring(0, fileName
						.lastIndexOf("/")));
				if (!dfile.exists())
					dfile.mkdirs();

				File file = new File(fileName);
				Writer writer = new OutputStreamWriter(new FileOutputStream(
						file), "UTF-8");
				writer.write(fileContent);
				writer.flush();
				writer.close();
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
	}

	public long getFileLastModified(String dir_path)
			throws FileNotFoundException {

		File file = new File(dir_path);
		if (!file.exists()) {
			throw new FileNotFoundException();
		}
		if (file.isDirectory()) {
			throw new FileNotFoundException();
		}
		return file.lastModified();
	}

	protected Template getTemplate(String templateName) {
		Template t = null;
		if (templateName != null && !templateName.trim().equals("")) {
			BasicCacheManager cM = getCm();
			if (cM != null) {
				CacheEntry cEntry = cM.lookup(templateName);

				String templateFile = this.getTemplatePath() + "/"
						+ templateName;

				long tFLmf = 0;
				try {
					tFLmf = this.getFileLastModified(templateFile);
				} catch (FileNotFoundException e1) {
					log.error("模板文件不存在:" + templateName, e1);
					return null;
				}

				if (cEntry != null) {
					BasicModifyTimeCacheEntry aTcEntry = (BasicModifyTimeCacheEntry) cEntry
							.getCeObject();
					if (aTcEntry != null) {
						if (aTcEntry.getCeObjectModifyTime() < tFLmf) {
							try {
								cM.unbind(templateName, true);
							} catch (CacheException e) {
								e.printStackTrace();
							}

							try {
								t = ve.getTemplate(templateName, "UTF-8");
							} catch (Exception e) {
								e.printStackTrace();
								log.error(
										"初始velocity 模板" + templateName + "失败",
										e);
							}

							if (t != null) {
								BasicModifyTimeCacheEntry aNewTcEntry = new BasicModifyTimeCacheEntry(
										templateName, t, tFLmf);

								try {
									cM.bind(templateName, aNewTcEntry);
								} catch (CacheException e) {
									e.printStackTrace();
								}
							}
						} else {
							t = (Template) aTcEntry.getCeObject();
						}

					} else {
						try {
							cM.unbind(templateName, true);
						} catch (CacheException e) {
							e.printStackTrace();
						}

						try {
							t = ve.getTemplate(templateName, "UTF-8");
						} catch (Exception e) {
							e.printStackTrace();
							log.error("初始velocity 模板" + templateName + "失败", e);
						}

						if (t != null) {
							BasicModifyTimeCacheEntry aNewTcEntry = new BasicModifyTimeCacheEntry(
									templateName, t, tFLmf);

							try {
								cM.bind(templateName, aNewTcEntry);
							} catch (CacheException e) {
								e.printStackTrace();
							}
						}
					}
				} else {
					try {
						t = ve.getTemplate(templateName, "UTF-8");
					} catch (Exception e) {
						e.printStackTrace();
						log.error("初始velocity 模板" + templateName + "失败", e);
					}

					if (t != null) {
						BasicModifyTimeCacheEntry aNewTcEntry = new BasicModifyTimeCacheEntry(
								templateName, t, tFLmf);

						try {
							cM.bind(templateName, aNewTcEntry);
						} catch (CacheException e) {
							e.printStackTrace();
						}
					}
				}
			} else {
				try {
					t = ve.getTemplate(templateName, "UTF-8");
				} catch (Exception e) {
					e.printStackTrace();
					log.error("初始velocity 模板" + templateName + "失败", e);
				}
			}
		}
		return t;
	}

	protected String getTemplatePath() {
		return ConfigBuilderFactory.getConfigBuilder().getProperty(
				"cms.system.template.location");
	}

	protected String getFileExt() {
		return fileExt;
	}

	private static BasicCacheManager getCm() {
		return cm;
	}
}