
package com.moobao.indexser.netComputer.field;

import org.apache.lucene.document.Field;

/**
 * 获取上网本评论的条数.
 * @author liuxueyong
 */
public class NetComputerRecommendCountField {
	
	public static String fieldName = "netComputerRecommendCount";
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
