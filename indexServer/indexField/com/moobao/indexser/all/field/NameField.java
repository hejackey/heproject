
package com.moobao.indexser.all.field;

import org.apache.lucene.document.Field;

/**
 * 获取主商品表中的手机名称
 * @author liuxueyong
 */
public class NameField {
	
	public static String fieldName = "name";
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
