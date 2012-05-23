
package com.moobao.indexser.netComputer.field;

import org.apache.lucene.document.Field;

/**
 * 获取分类(区分上网本和上网卡)
 * @author liuxueyong
 */
public class NetComputerProductClassField {
	
	public static String fieldName = "productClass";
	/**
	 * @param pid
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String productClass ){
		Field field = new Field( fieldName, productClass, Field.Store.YES, Field.Index.UN_TOKENIZED );
		return field ;
	}	
}
