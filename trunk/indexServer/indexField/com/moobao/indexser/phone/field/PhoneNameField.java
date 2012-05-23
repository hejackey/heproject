
package com.moobao.indexser.phone.field;

import org.apache.lucene.document.Field;

/**
 * 获取主商品表中的手机名称
 * @author liuxueyong
 */
public class PhoneNameField {
	
	public static String fieldName = "phoneName";
	public static float weight = 1.5f;
	
	/**
	 * @param pname
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String pname ){
		//字段名,值,是否存储,是否分词(Field.Index.TOKENIZED建索引分词,Field.Index.UN_TOKENIZED建索引不分词,Field.Index.NO不建索引)
		Field field = new Field( fieldName, pname, Field.Store.YES, Field.Index.TOKENIZED );
		field.setBoost(weight);
		return field;
	}
}
