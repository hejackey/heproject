package com.moobao.indexser.document.number;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 依据英文与数字来拆分productmodel(eg.w159i, w159, 159, 159i)
 * 加入keywords中.
 * @author liuxueyong
 *
 */
public class NumberJudge {

	public static String splitProductModel( String productModel ) {
		
		//去掉所有的空格.
		productModel = productModel.replaceAll("\\s", "");
        
		StringBuffer sb = new StringBuffer();
		int len = productModel.length();
		int left = NumberJudge.firstNumber(productModel);     //第一个数字的位置.
		int right = NumberJudge.endNumber(productModel);      //最后一个数字的位置.
		
		
	    sb.append( "," + productModel.substring(0,right+1) + "," );
	    sb.append( productModel.substring(left,right+1) + "," );
	    sb.append( productModel.substring(left,len) );
		
		return sb.toString();
	}
	
	public static int firstNumber( String productModel ) {
		Pattern pattern = Pattern.compile( "[0-9]*" );
        Matcher isNum = null;
        
        int position = 0;
        for ( int i = 0; i < productModel.length(); i ++ )
        {
	    	isNum = pattern.matcher( productModel.substring( i, i + 1 ) ) ;
            if( isNum.matches() ) {
            	position = i; //productModel.substring( i, i + 1 );
            	break;
            }
        }
        return position;
	}
	
	public static int endNumber( String productModel ) {
		Pattern pattern = Pattern.compile( "[0-9]*" );
        Matcher isNum = null;
        
        int position = 0;
        for ( int i = 0; i < productModel.length(); i ++ )
        {
	    	isNum = pattern.matcher( productModel.substring( i, i + 1 ) ) ;
            if( isNum.matches() ) {
            	position = i; //productModel.substring( i, i + 1 );
            }
        }
        return position;
	}
	
	public static void main( String[] args ) {

		String productModel = "w1 5 9c ";
		System.out.println( NumberJudge.splitProductModel(productModel) );
	}
}
