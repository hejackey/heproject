package com.moobao.indexser.netComputer.field;

import org.apache.lucene.document.Field;

/**
 * 上网本基本参数
 * @author liuxueyong
 */
public class NetComputerBaseParaField {
	
	public static String fieldName = "netComputerBasePara";
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
