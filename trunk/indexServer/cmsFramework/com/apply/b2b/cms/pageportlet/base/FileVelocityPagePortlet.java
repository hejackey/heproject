package com.apply.b2b.cms.pageportlet.base;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.velocity.VelocityContext;

/**
 * 
 * @author luoweifeng
 * 
 */

public abstract class FileVelocityPagePortlet extends VelocityPagePortlet
		implements IFileVelocityPagePortlet {
	
	public boolean writeFile() {
		return writeFileAllDir(this.getFileName(), this.getFileContent());
	}
	
	/**
	 * @param fileName
	 * @param fileContent
	 */
	private boolean writeFileAllDir(String fileName, String fileContent) {
		if (fileName != null && !fileName.trim().equals("")
				&& fileContent != null) {
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
				}else{
					return false;
				}
			} catch (Exception e) {
				log.error(e.getMessage());
				return false;
			}
		} else {
			return false;
		}
	}
	
	protected String getFileContent() {
			return getParserContent();
	}
	
	@Override
	protected void afterPutTemplateParseValue(VelocityContext context) {
	}
	
	@Override
	protected void beforePutTemplateParseValue(VelocityContext context) {
	}
}