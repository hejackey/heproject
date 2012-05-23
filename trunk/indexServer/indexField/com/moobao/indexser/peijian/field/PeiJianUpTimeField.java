
package com.moobao.indexser.peijian.field;

import org.apache.lucene.document.Field;

/**
 * 获取配件的上架时间.
 * @author liuxueyong
 */
public class PeiJianUpTimeField {
	
	public static String fieldName = "peiJianUpTime";
	/**
	 * @param String
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String upTime ){
		Field field = new Field( fieldName, upTime, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field ;
	}	
}
