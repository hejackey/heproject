package com.moobao.indexser.netComputer.field;

import org.apache.lucene.document.Field;

/**
 * 上网本简要描述
 * @author liuxueyong
 */
public class NetComputerDescribeField {
	
	public static String fieldName = "netComputerDescribe";
	/**
	 * @param describe
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String describe ) {
		Field field = new Field( fieldName, describe, Field.Store.YES, Field.Index.NO );
		return field;
	}
}
