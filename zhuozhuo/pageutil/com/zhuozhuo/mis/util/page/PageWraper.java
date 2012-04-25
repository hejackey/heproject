package com.zhuozhuo.mis.util.page;

import java.util.List;

/**
 * @author  
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class PageWraper {
	
	private PageInfo pageInfo=null;
	private List result=null;

	/**
	 * @return
	 */
	public PageInfo getPageInfo() {
		return pageInfo;
	}

	/**
	 * @return
	 */
	public List getResult() {
		return result;
	}

	/**
	 * @param info
	 */
	public void setPageInfo(PageInfo info) {
		pageInfo = info;
	}

	/**
	 * @param list
	 */
	public void setResult(List list) {
		result = list;
	}

}
