
package com.moobao.indexser.netComputer.field;

import org.apache.lucene.document.Field;

/**
 * 获取上网本咨询的条数.
 * @author liuxueyong
 */
public class NetComputerAskCountField {
	
	public static String fieldName = "netComputerAskCount";
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
