
package com.moobao.indexser.netComputer.field;

import org.apache.lucene.document.Field;

/**
 * 获取上网本图片列表路径
 * @author liuxueyong
 */
public class NetComputerPicListField {
	
	public static String fieldName = "netComputerPicList" ;
	/**
	 * @param photoName
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String photoName ) {
		Field field = new Field( fieldName, photoName, Field.Store.YES, Field.Index.NO );
		return field ;
	}
}
