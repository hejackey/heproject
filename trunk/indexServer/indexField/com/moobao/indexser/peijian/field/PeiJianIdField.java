
package com.moobao.indexser.peijian.field;

import org.apache.lucene.document.Field;

/**
 * 获取配件ID
 * @author liuxueyong
 */
public class PeiJianIdField {
	
	public static String fieldName = "peiJianId";
	/**
	 * @param pid
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String pid ){
		Field field = new Field( fieldName, pid, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field ;
	}	
}
