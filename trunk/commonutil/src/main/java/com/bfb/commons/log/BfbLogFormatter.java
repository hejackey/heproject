package com.bfb.commons.log;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

import com.bfb.commons.date.DateUtil;

public class BfbLogFormatter extends Formatter{

	@Override
	public String format(LogRecord record) {
		StringBuffer sb = new StringBuffer();
		sb.append(DateUtil.formatDate2Str(new Date(), "yyyy-MM-dd hh:mm:ss"));
		sb.append(" ");
		sb.append(record.getLevel());
		sb.append(" ");
		sb.append(record.getSourceClassName());
		sb.append(" ");
		sb.append(record.getMessage());
		sb.append("\n");
		
		return sb.toString();
	}
}
