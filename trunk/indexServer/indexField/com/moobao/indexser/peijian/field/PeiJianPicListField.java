
package com.moobao.indexser.peijian.field;

import org.apache.lucene.document.Field;

/**
 * 获取配件图片列表路径
 * @author liuxueyong
 */
public class PeiJianPicListField {
	
	public static String fieldName = "peiJianPicList" ;
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
