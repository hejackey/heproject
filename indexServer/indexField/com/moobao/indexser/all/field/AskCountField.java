
package com.moobao.indexser.all.field;

import org.apache.lucene.document.Field;

/**
 * 获取手机咨询的条数.
 * @author liuxueyong
 */
public class AskCountField {
	
	public static String fieldName = "askCount";
	/**
	 * @param askCount
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String askCount ){
		Field field = new Field( fieldName, askCount, Field.Store.YES, Field.Index.NO );
		return field ;
	}	
}
