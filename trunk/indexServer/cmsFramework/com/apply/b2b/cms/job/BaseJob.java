package com.apply.b2b.cms.job;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 * @author luoweifeng
 */

public abstract class BaseJob {
	protected final Logger log = Logger.getLogger(this.getClass());
	
	protected void beforeRun() {
	}
	
	protected void afterRun() {
	}
	
	public final void run() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			long sysTime1 = System.currentTimeMillis();
			Date bDate = new Date();
			this.beforeRun();
			this.doRun();
			this.afterRun();
			long sysTime2 = System.currentTimeMillis();
			Date eDate = new Date();
			log
					.info("Begin time:" + sdf.format(bDate)
							+ " ------ End time:" + sdf.format(eDate)
							+ " Run time: " + (sysTime2 - sysTime1));
		} catch (Exception e) {
			log.error(" Error Message:" + e.getMessage());
		}
	}
	
	protected abstract void doRun();
}