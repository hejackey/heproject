package com.apply.b2b.cms.data.base;

import java.util.Date;

/**
 * 
 * @author luoweifeng
 *
 */

public interface IPortletTextData extends ITextData  {
	Date getCreateDate();
	Date getUpdateDate();
	String getSummary();
	String getOtherSummary();
}