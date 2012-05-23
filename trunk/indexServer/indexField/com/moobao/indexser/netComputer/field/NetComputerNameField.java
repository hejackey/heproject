
package com.moobao.indexser.netComputer.field;

import org.apache.lucene.document.Field;

/**
 * 获取上网本名称
 * @author liuxueyong
 */
public class NetComputerNameField {
	
	public static String fieldName = "netComputerName";
	public static float weight = 1.5f;
	/**
	 * @param pname
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String pname ){
		Field field = new Field( fieldName, pname, Field.Store.YES, Field.Index.TOKENIZED );
		field.setBoost(weight);
		return field;
	}	
}
