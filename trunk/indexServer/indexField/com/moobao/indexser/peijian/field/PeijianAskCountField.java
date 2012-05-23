
package com.moobao.indexser.peijian.field;

import org.apache.lucene.document.Field;

/**
 * 获取配件咨询的条数.
 * @author liuxueyong
 */
public class PeijianAskCountField {
	
	public static String fieldName = "peijianAskCount";
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
