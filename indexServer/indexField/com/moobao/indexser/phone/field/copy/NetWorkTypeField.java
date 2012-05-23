package com.moobao.indexser.phone.field.copy;

import org.apache.lucene.document.Field;

/**
 * 获取手机网络类型
 * @author liuxueyong
 */
public class NetWorkTypeField {
	
	public static String fieldName = "netWorkType";
	/**
	 * @param type
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String type ) {
		Field field = new Field( fieldName, type, Field.Store.YES, Field.Index.TOKENIZED );
		return field;
	}
}
