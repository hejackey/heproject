package com.moobao.indexTime;

import java.math.BigDecimal;

/**
 * 将花费时间"毫秒"--"秒".
 * @author liuxueyong
 */
public class GetSeconds {
	
	/**
	 * 将'毫秒'转化为'秒'
	 * @param date
	 * @return
	 */
	public static double convertSeconds( long date ) {
		
		double cost = (double) date / 1000;
		BigDecimal bd = new BigDecimal(cost);
		BigDecimal bd1 = bd.setScale(3, bd.ROUND_HALF_UP); // 保留3位小数
		double costTime = bd1.doubleValue();
		return costTime;
	}
}
