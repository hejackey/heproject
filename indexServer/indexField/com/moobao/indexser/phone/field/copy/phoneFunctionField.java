package com.moobao.indexser.phone.field.copy;

import org.apache.lucene.document.Field;

/**
 * 得到手机功能的Field(各个功能组合而成)
 * @author liuxueyong
 */
public class phoneFunctionField {
	
	public static String fieldName = "phoneFunction";
	/**
	 * @param function
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String function ) {
		Field field = new Field( fieldName, function, Field.Store.YES, Field.Index.TOKENIZED );
		return field;
	}
}
