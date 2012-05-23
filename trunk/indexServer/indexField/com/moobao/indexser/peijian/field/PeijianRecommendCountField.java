
package com.moobao.indexser.peijian.field;

import org.apache.lucene.document.Field;

/**
 * 获取配件评论的条数.
 * @author liuxueyong
 */
public class PeijianRecommendCountField {
	
	public static String fieldName = "peijianRecommendCount";
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
