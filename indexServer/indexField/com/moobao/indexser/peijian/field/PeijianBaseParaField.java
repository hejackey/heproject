package com.moobao.indexser.peijian.field;

import org.apache.lucene.document.Field;

/**
 * 配件基本参数
 * @author liuxueyong
 */
public class PeijianBaseParaField {
	
	public static String fieldName = "peijianBasePara";
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
