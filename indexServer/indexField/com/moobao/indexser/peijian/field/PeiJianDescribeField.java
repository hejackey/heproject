package com.moobao.indexser.peijian.field;

import org.apache.lucene.document.Field;

/**
 * 配件简要描述
 * @author liuxueyong
 */
public class PeiJianDescribeField {
	
	public static String fieldName = "peiJianDescribe";
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
