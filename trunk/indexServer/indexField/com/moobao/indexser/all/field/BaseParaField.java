package com.moobao.indexser.all.field;

import org.apache.lucene.document.Field;

/**
 * 手机基本参数
 * @author liuxueyong
 */
public class BaseParaField {
	
	public static String fieldName = "basePara";
	/**
	 * @param describe
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String para ) {
		Field field = new Field( fieldName, para, Field.Store.YES, Field.Index.TOKENIZED );
		return field;
	}
}
