package com.liu.test;

import java.text.DecimalFormat;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//将整数部分扩为统一的12位
		DecimalFormat df = new DecimalFormat( "00000000" );
		String cameraRang = df.format( (double)30 );
		
		System.out.println( cameraRang );
	}
}
