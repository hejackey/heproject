package com.zhuozhuo.mis.util.page;


public class PageInfo 
{
	private int page;
	private int pageSize;
	private int count;
	private int pages;
	private int prePage;
	private int nextPage;
	private int start; //当页开始记录
	private int end; //当页结束记录
	private int startPage; //当前开始页码
	private int endPage; //当前结束页码
	private int groupSize; //显示分组大小
  
	public PageInfo(int page, int pageSize, int groupSize){
		this.page = page;
		this.pageSize = pageSize;
		this.groupSize = groupSize;
	}
	public int getGroupSize() {
		if(groupSize <= 0)
			return PageConst.DEF_Group_SIZE;
		return groupSize;
	}

	public void setGroupSize(int groupSize) {
		this.groupSize = groupSize;
	}

	public int getCount() {
		return count;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getPages() {
		if( pages <= 0 )
			return 1;
		return pages;
	}

	public int getNextPage() {
		return nextPage;
	}

	public int getPrePage() {
		return prePage;
	}

	public int getPage() {
		if(page <= 0)
			return PageConst.DEF_CURRENT_PAGE;
		return page;
	}

	public int getPageSize() {
		if( pageSize <= 0 )
			return PageConst.DEF_PAGE_SIZE;
		return pageSize;
	}

	public int getEnd() {
		return end;
	}

	public int getStart() {
		return start;
	}

	public PageInfo() {
	}
	/**
	 * @return
	 */
	public int getEndPage() {
		return endPage;
	}

	/**
	 * @return
	 */
	public int getStartPage() {
		return startPage;
	}

	/**
	 * @param i
	 */
	public void setEndPage(int i) {
		endPage = i;
	}

	/**
	 * @param i
	 */
	public void setStartPage(int i) {
		startPage = i;
	}


	// 重置页面信息，以符合分页标准 前提 是有当前页 和总记录数 分组值
	public  static PageInfo resetPageInfo(PageInfo pageInfo) {
		int pageGroupNumber = 0;
		int pagesGroups = 0;
		int	groupSize = pageInfo.getGroupSize();
		
		int	pages = 0;
		
		if (pageInfo.getCount() / pageInfo.getPageSize() == 0) {
			pages = 1;
		} else if (
			pageInfo.getCount() / pageInfo.getPageSize() >= 1 && pageInfo.getCount() % pageInfo.getPageSize() == 0) {
			pages = pageInfo.getCount() / pageInfo.getPageSize();
		} else if (
			pageInfo.getCount() / pageInfo.getPageSize() >= 1 && pageInfo.getCount() % pageInfo.getPageSize() != 0) {
			pages = pageInfo.getCount() / pageInfo.getPageSize() + 1;
		}
		pageInfo.setPages(pages);


		if (pageInfo.getPages() / groupSize == 0) {
			pagesGroups = 1;
		} else if (
			pageInfo.getPages() / groupSize >= 1 && pageInfo.getPages() % groupSize == 0) {
			pagesGroups = pageInfo.getPages() / groupSize;
		} else if (
			pageInfo.getPages() / groupSize >= 1 && pageInfo.getPages() % groupSize != 0) {
			pagesGroups = pageInfo.getPages() / groupSize + 1;
		}

		if (pageInfo.getPage() / groupSize == 0) {
			pageGroupNumber = 1;
		} else if (
			pageInfo.getPage() / groupSize >= 1 && pageInfo.getPage() % groupSize == 0) {
			pageGroupNumber = pageInfo.getPage() / groupSize;
		} else if (
			pageInfo.getPage() / groupSize >= 1 && pageInfo.getPage() % groupSize != 0) {
			pageGroupNumber = pageInfo.getPage() / groupSize + 1;
		}

		//首组页 且小于一页
		if (pageGroupNumber == 1 && pageInfo.getPages() < groupSize) {
			pageInfo.setStartPage(1);
			pageInfo.setEndPage(pageInfo.getPages());
		}
		// 其它组页
		else if (pageGroupNumber >= 1 && pageGroupNumber < pagesGroups) {
			pageInfo.setStartPage((pageGroupNumber - 1) * groupSize + 1);
			pageInfo.setEndPage(pageInfo.getStartPage() + 9);
		}
		//末组页
		else if (pageGroupNumber == pagesGroups) {
			// 最后一组页为满页groupSize个
			pageInfo.setStartPage((pageGroupNumber - 1) * groupSize + 1);
			pageInfo.setEndPage(pageInfo.getPages());
		}

		if (pageInfo.getPage() <= 0
			|| pageInfo.getPages() <= 0
			|| pageInfo.getCount() <= 0) {
			pageInfo.setPage(0);
			pageInfo.setPrePage(0);
			pageInfo.setNextPage(0);
			pageInfo.setCount(0);
			pageInfo.setStart(0);
			pageInfo.setEnd(0);
			pageInfo.setStartPage(0);
			pageInfo.setEndPage(0);
		} else if (pageInfo.getPage() == 1 && pageInfo.getPages() == 1) {
			pageInfo.setPage(1);
			pageInfo.setPrePage(1);
			pageInfo.setNextPage(1);
			//pageInfo.setCount(1);
			pageInfo.setStart(1);
			pageInfo.setEnd(pageInfo.getCount());
			pageInfo.setStartPage(1);
			pageInfo.setEndPage(1);

		}
		//第一页 且总计为 一组页 
		else if (pageInfo.getPage() == 1 && pageInfo.getPages() <= groupSize) {
			pageInfo.setPrePage(1);
			pageInfo.setNextPage(pageInfo.getPage() + 1);
			pageInfo.setStart(
				(pageInfo.getPage() - 1) * pageInfo.getPageSize() + 1);
			pageInfo.setEnd(pageInfo.getPage() * pageInfo.getPageSize());
		}
		// 总计为 一组页 且不是第一页
		else if (
			pageInfo.getPage() >= 1
				&& pageInfo.getPage() < pageInfo.getPages()
				&& pageInfo.getPages() <= groupSize) {
			pageInfo.setPrePage(pageInfo.getPage() - 1);
			pageInfo.setNextPage(pageInfo.getPage() + 1);
			pageInfo.setStart(
				(pageInfo.getPage() - 1) * pageInfo.getPageSize() + 1);
			pageInfo.setEnd(pageInfo.getPage() * pageInfo.getPageSize());
		}
		// 
		else if (
			pageInfo.getPage() >= 1
				&& pageInfo.getPage() == pageInfo.getPages()
				&& pageInfo.getPages() > groupSize) {
			pageInfo.setPrePage(pageInfo.getPage() - 1);
			pageInfo.setNextPage(pageInfo.getPage() + 1);
			pageInfo.setStart(
				(pageInfo.getPage() - 1) * pageInfo.getPageSize() + 1);
			pageInfo.setEnd(pageInfo.getCount());
		}
		// 最后一页 
		else if (pageInfo.getPage() == pageInfo.getPages()) {
			pageInfo.setPage(pageInfo.getPages());
			pageInfo.setPrePage(pageInfo.getPages() - 1);
			pageInfo.setNextPage(pageInfo.getPages());
			pageInfo.setStart(
				(pageInfo.getPage() - 1) * pageInfo.getPageSize() + 1);
			pageInfo.setEnd(pageInfo.getCount());
		}
		// 中间页
		else {
			pageInfo.setPrePage(pageInfo.getPage() - 1);
			pageInfo.setNextPage(pageInfo.getPage() + 1);
			pageInfo.setStart(
				(pageInfo.getPage() - 1) * pageInfo.getPageSize() + 1);
			pageInfo.setEnd(pageInfo.getPage() * pageInfo.getPageSize());
		}

		return pageInfo;
	}
}
