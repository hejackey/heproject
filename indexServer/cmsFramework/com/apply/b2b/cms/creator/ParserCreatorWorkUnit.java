package com.apply.b2b.cms.creator;

import java.util.List;

import com.apply.b2b.cms.base.IParser;

/**
 * 
 * @author luoweifeng
 * 
 */

class ParserCreatorWorkUnit implements Runnable {
	private List<IParser> workUnitList;
	
	public ParserCreatorWorkUnit() {
	}
	
	public ParserCreatorWorkUnit(List<IParser> parserList) {
		workUnitList = parserList;
	}
	
	public void run() {
		if (workUnitList != null && workUnitList.size() > 0) {
			for (IParser aParser : workUnitList) {
				try {
					aParser.perform();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	public List<IParser> getWorkUnitList() {
		return workUnitList;
	}
	
	public void setWorkUnitList(List<IParser> workUnitList) {
		this.workUnitList = workUnitList;
	}
}