package com.zhuozhuo.mis.util.page;

import java.util.List;

/**
 * 
 * 
 */
public class PageManager
{

	public PageManager()
	{
	}

	/**
	 * @author Pablo
	 * @desc  针对分页查询的 分页信息的包装
	 * @param pageInfo		修改设置 当前页/页面大小/页面分组大小  默认为PageConst中设定
	 * @param pageResult	当前页的查询结果
	 * @param count			满足查询条件 的总记录数
	 * @return
	 */
	public static PageWraper getPageWraper(PageInfo pageInfo, List pageResult, int count){
	  PageWraper pageWraper = new PageWraper();
	  pageWraper.setPageInfo(new PageInfo());
	  
	  if(pageResult == null || pageResult.size() < 1){
		  return pageWraper;
	  }
	  else{
		  pageInfo.setCount(count);
		  pageInfo = PageInfo.resetPageInfo(pageInfo);
		  pageWraper.setResult(pageResult);
		  pageWraper.setPageInfo(pageInfo);
	  }
	  return pageWraper;
	}

	public static void main(String[] args)
	{

		String str = " select  fromEmail from email ";

		System.out.println(str.substring(str.toUpperCase().indexOf(" FROM ") + 1));

	}

}
