package com.apply.b2b.cms.base;

import org.apache.log4j.Logger;

/**
 * 
 * @author luoweifeng
 *
 */

public abstract class BaseCreator {
	protected final Logger log = Logger.getLogger(this.getClass());
	public abstract boolean create();
}
