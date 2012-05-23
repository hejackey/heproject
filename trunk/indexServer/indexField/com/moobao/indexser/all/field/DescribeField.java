package com.moobao.indexser.all.field;

import org.apache.lucene.document.Field;

/**
 * 手机简单描述
 * @author liuxueyong
 */
public class DescribeField {
	
	public static String fieldName = "describe";
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
