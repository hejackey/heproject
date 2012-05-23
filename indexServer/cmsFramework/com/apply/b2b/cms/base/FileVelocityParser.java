package com.apply.b2b.cms.base;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

/**
 * 
 * @author luoweifeng
 *
 */

public abstract class FileVelocityParser extends VelocityParser implements IFileVelocityParser {
	public final boolean perform() {
		return writeFile();
	}
	
	public boolean writeFile() {
		try {
			return writeFileAllDir(this.getFileName(), this.getFileContent());
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		} catch (ParseErrorException e) {
			e.printStackTrace();
		} catch (MethodInvocationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	protected String getFileContent() throws ResourceNotFoundException,
			ParseErrorException, MethodInvocationException, Exception {
		return getParserContent();
	}
	
	@Override
	protected void afterPutTemplateParseValue(VelocityContext context) {
	}
	
	@Override
	protected void beforePutTemplateParseValue(VelocityContext context) {
	}
}