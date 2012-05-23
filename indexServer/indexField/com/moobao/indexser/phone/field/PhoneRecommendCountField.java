
package com.moobao.indexser.phone.field;

import org.apache.lucene.document.Field;

/**
 * 获取手机评论的条数.
 * @author liuxueyong
 */
public class PhoneRecommendCountField {
	
	public static String fieldName = "phoneRecommendCount";
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
