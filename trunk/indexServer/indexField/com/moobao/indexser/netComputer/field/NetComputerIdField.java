
package com.moobao.indexser.netComputer.field;

import org.apache.lucene.document.Field;

/**
 * 获取上网本ID
 * @author liuxueyong
 */
public class NetComputerIdField {
	
	public static String fieldName = "netComputerId";
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
