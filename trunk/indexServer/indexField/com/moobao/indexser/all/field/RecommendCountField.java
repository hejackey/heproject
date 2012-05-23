
package com.moobao.indexser.all.field;

import org.apache.lucene.document.Field;

/**
 * 获取手机评论的条数.
 * @author liuxueyong
 */
public class RecommendCountField {
	
	public static String fieldName = "recommendCount";
	/**
	 * @param recommendCount
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String recommendCount ){
		Field field = new Field( fieldName, recommendCount, Field.Store.YES, Field.Index.NO );
		return field ;
	}	
}
