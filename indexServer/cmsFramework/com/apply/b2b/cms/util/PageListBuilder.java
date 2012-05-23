package com.apply.b2b.cms.util;

/**
 * 分页列表生成工具
 * 
 * @author luoweifeng
 */
public class PageListBuilder {
	private int totalRecords = 0; // 总记录数
	
	private int pageSize = 20; // 每页数量
	
	private int pageListSize = 10;
	
	private int curPage = 1; // 当前页
	
	private int totalPages = 0;
	
	private int limitedMaxPages = 0;
	
	private String pageUrl = "";
	
	public PageListBuilder() {
		
	}
	
	public PageListBuilder(int totalRecords, int pageSize, int curPage,
			String pageUrl) {
		this.totalRecords = totalRecords;
		this.pageSize = pageSize;
		this.curPage = curPage;
		this.pageUrl = pageUrl;
	}
	
	public long getCurPage() {
		return curPage;
	}
	
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	
	public int getPageListSize() {
		return pageListSize;
	}
	
	public void setPageListSize(int pageListSize) {
		this.pageListSize = pageListSize;
	}
	
	public int getTotalRecords() {
		return totalRecords;
	}
	
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public String getPageUrl() {
		return pageUrl;
	}
	
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	
	// 获得所有的页数
	public int getTotalPages() {
		int tPages = totalRecords / pageSize;
		if (totalRecords % pageSize > 0) {
			totalPages = tPages + 1;
		} else {
			totalPages = tPages;
		}
		
		if (limitedMaxPages > 0) {
			if (totalPages > limitedMaxPages) {
				totalPages = limitedMaxPages;
			}
		}
		
		return totalPages;
	}
	
	// 获得下一页
	public int getNextPage() {
		if (curPage + 1 > getTotalPages()) {
			return getTotalPages();
		} else {
			return curPage + 1;
		}
	}
	
	// 获得上一页
	public int getPrePage() {
		if (curPage - 1 < 1) {
			return 1;
		} else {
			return curPage - 1;
		}
	}
	
	public String getPageNumList() {
		StringBuffer sbf = new StringBuffer();

		sbf.append("<div align=\"right\">");
		sbf
				.append("<table width=\"500\" border=\"0\" cellspacing=\"1\" cellpadding=\"0\">");
		sbf.append(" <tr> ");
		sbf
				.append("<td width=\"80\"><div align=\"right\"><a href=\"")
				.append(getRealUrl(this.getPrePage()))
				.append(
						"\"><img src=\"http://www.madeinchina.com/static/web/images/ico-previous.gif\" width=\"8\" height=\"7\" hspace=\"5\" border=\"0\" />Previous </a></div></td>");

		sbf.append("<td>");
		sbf.append("<div align=\"center\">Page");

		int sk = 0; // 构建末页的位置
		if (this.getTotalPages() > this.pageListSize
				&& this.curPage > this.pageListSize / 2) {
			sk = this.curPage + this.pageListSize / 2;
		} else {
			sk = this.pageListSize;
		}

		for (int i = Math.min((sk - this.pageListSize) + 1, this
				.getTotalPages()); i <= Math.min(sk, this.getTotalPages()); i++) {
			if (i == this.curPage) {
				sbf.append(" <span class=\"pageSel_current\">").append(i)
						.append("</span> ");
			} else {
				sbf.append(" <a href=\"").append(getRealUrl(i)).append("\">")
						.append(i).append("</a> ");
			}
		}
		
		sbf.append(" of ").append(this.getTotalPages()).append("</div>");
		sbf.append("</td>");
		
		sbf.append("<td width=\"55\"> <div align=\"left\"> <a href=\"");
		sbf.append(getRealUrl(this.getNextPage()));
		sbf
				.append("\"> Next <img src=\"http://www.madeinchina.com/static/web/images/ico-next.gif\" width=\"8\" height=\"7\" hspace=\"5\" border=\"0\" /></a></div></td>");
		sbf.append(" </tr>");
		sbf.append(" </table>");
		sbf.append(" </div>");
		
		return sbf.toString();
	}
	
	private String getRealUrl(int pageNum) {
		String url = this.pageUrl
				.replaceAll("#PAGENO", String.valueOf(pageNum));
		return url;
	}
	
	public int getLimitedMaxPages() {
		return limitedMaxPages;
	}
	
	public void setLimitedMaxPages(int limitedMaxPages) {
		this.limitedMaxPages = limitedMaxPages;
	}
}