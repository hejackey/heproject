package com.bfb.commons.thread;

import java.io.Serializable;

/**
 * 线程任务接口
 * @author Administrator
 *
 */
public interface ThreadTask extends Runnable, Serializable {
	public void run();
}
