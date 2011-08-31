package com.bfb.portal.base.page;

import java.io.Serializable;

/**
 * 自定义分页
 * @author Administrator
 *
 */
public class PageInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8071198501406117801L;
	private int page;
	private int pageSize;
	private int count;
	private int pages;
	private int prePage;
	private int nextPage;
	private int start; 			//当页记录
	private int end; 			//当页结束记录
	private int startPage;	 	//当前页码
	private int endPage; 		//当前结束页码
	private int groupSize; 		//显示分组大小
	
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

	// 重置页面信息，以符合分页标准 前提 是有当前�?和�?记录�?分组�?
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

		//首组�?且小于一�?
		if (pageGroupNumber == 1 && pageInfo.getPages() < groupSize) {
			pageInfo.setStartPage(1);
			pageInfo.setEndPage(pageInfo.getPages());
		}
		// 其它组页
		else if (pageGroupNumber >= 1 && pageGroupNumber < pagesGroups) {
			pageInfo.setStartPage((pageGroupNumber - 1) * groupSize + 1);
			pageInfo.setEndPage(pageInfo.getStartPage() + 9);
		}
		//末组�?
		else if (pageGroupNumber == pagesGroups) {
			// �?���?��页为满页groupSize�?
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
		//第一�?且�?计为 �?���?
		else if (pageInfo.getPage() == 1 && pageInfo.getPages() <= groupSize) {
			pageInfo.setPrePage(1);
			pageInfo.setNextPage(pageInfo.getPage() + 1);
			pageInfo.setStart(
				(pageInfo.getPage() - 1) * pageInfo.getPageSize() + 1);
			pageInfo.setEnd(pageInfo.getPage() * pageInfo.getPageSize());
		}
		// 总计�?�?���?且不是第�?��
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
		// �?���?�� 
		else if (pageInfo.getPage() == pageInfo.getPages()) {
			pageInfo.setPage(pageInfo.getPages());
			pageInfo.setPrePage(pageInfo.getPages() - 1);
			pageInfo.setNextPage(pageInfo.getPages());
			pageInfo.setStart(
				(pageInfo.getPage() - 1) * pageInfo.getPageSize() + 1);
			pageInfo.setEnd(pageInfo.getCount());
		}
		// 中间�?
		else {
			pageInfo.setPrePage(pageInfo.getPage() - 1);
			pageInfo.setNextPage(pageInfo.getPage() + 1);
			pageInfo.setStart(
				(pageInfo.getPage() - 1) * pageInfo.getPageSize() + 1);
			pageInfo.setEnd(pageInfo.getPage() * pageInfo.getPageSize());
		}
		
		return pageInfo;
	}

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("PageInfo");
        sb.append("{page=").append(page);
        sb.append(", pageSize=").append(pageSize);
        sb.append(", count=").append(count);
        sb.append(", pages=").append(pages);
        sb.append(", prePage=").append(prePage);
        sb.append(", nextPage=").append(nextPage);
        sb.append(", start=").append(start);
        sb.append(", end=").append(end);
        sb.append(", startPage=").append(startPage);
        sb.append(", endPage=").append(endPage);
        sb.append(", groupSize=").append(groupSize);
        sb.append('}');
        return sb.toString();
    }

}