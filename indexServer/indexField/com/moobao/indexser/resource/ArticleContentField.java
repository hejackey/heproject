package com.moobao.indexser.resource;

import org.apache.lucene.document.Field;

/**
 * 获取文章内容(需要搜索和分词的Field)
 * 组成:关建字、摘要、标签
 * @author liuxueyong
 */
public class ArticleContentField {
	
	public static String fieldName = "articleContent";
	/**
	 * @param content
	 * @exception no thrown Exception
	 * @return Field
	 */
	public static Field getField( String content ) {
		Field field = new Field( fieldName, content, Field.Store.YES, Field.Index.TOKENIZED );
		return field;
	}
}
