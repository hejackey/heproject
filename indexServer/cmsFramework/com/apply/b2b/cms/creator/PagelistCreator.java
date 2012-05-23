package com.apply.b2b.cms.creator;

import java.util.List;

import com.apply.b2b.cms.base.BaseCreator;
import com.apply.b2b.cms.data.base.PortletMixedData;
import com.apply.b2b.cms.parser.base.PageListParser;

/**
 * 
 * @author luoweifeng
 *
 */

public abstract class PagelistCreator extends BaseCreator {
	
	@Override
	public boolean create() {
		int totalRecords = getTotalRecords();
		int pageSize = getPageSize();
		if (totalRecords > 0 && pageSize > 0) {
			if (totalRecords > pageSize) {
				int pages = totalRecords / pageSize;
				if (!(totalRecords % pageSize == 0)) {
					pages = pages + 1;
				}
				
				for (int i = 1; i <= pages; i++) {
					try {
						PageListParser parser = getPageListParser(i);
						if (parser != null) {
							parser.perform();
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				
				return true;
			} else {
				PageListParser parser = getPageListParser(1);
				return parser.perform();
			}
		} else {
			return false;
		}
	}
	
	abstract int getTotalRecords();
	
	protected int getPageSize() {
		return 10;
	}
	
	protected int getPageListSize() {
		return 10;
	}
	
	abstract List<PortletMixedData> getPageListDate(int pageNum);
	
	abstract PageListParser getPageListParser(int pageNum);
}