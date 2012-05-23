
package com.moobao.indexser.netComputer.field;

import org.apache.lucene.document.Field;

/**
 * 获取上网本的上架时间.
 * @author liuxueyong
 */
public class NetComputerUpTimeField {
	
	public static String fieldName = "netComputerUpTime";
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
