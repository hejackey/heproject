package com.bfb.commons.ip;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;

public class IPUtil {
	/**
	 * 获得客户端IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (StringUtils.isNotEmpty(ip)) {
			if (ip.length() > 45) {
				return ip.substring(0, 45);
			} else {
				return ip;
			}
		}
		return ip;
	}
}
