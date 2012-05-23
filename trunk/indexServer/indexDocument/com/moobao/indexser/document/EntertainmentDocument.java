package com.moobao.indexser.document;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

import com.moobao.indexser.entertainment.field.EntAuthorField;
import com.moobao.indexser.entertainment.field.EntBackTagField;
import com.moobao.indexser.entertainment.field.EntBrandField;
import com.moobao.indexser.entertainment.field.EntCPIdField;
import com.moobao.indexser.entertainment.field.EntCatIdField;
import com.moobao.indexser.entertainment.field.EntCreateTimeField;
import com.moobao.indexser.entertainment.field.EntDescribeField;
import com.moobao.indexser.entertainment.field.EntDownCountField;
import com.moobao.indexser.entertainment.field.EntIdField;
import com.moobao.indexser.entertainment.field.EntLastOperTimeField;
import com.moobao.indexser.entertainment.field.EntMainPicField;
import com.moobao.indexser.entertainment.field.EntMinPicField;
import com.moobao.indexser.entertainment.field.EntModelField;
import com.moobao.indexser.entertainment.field.EntModelIDField;
import com.moobao.indexser.entertainment.field.EntPicPathField;
import com.moobao.indexser.entertainment.field.EntResNameField;
import com.moobao.indexser.entertainment.field.EntResNoField;
import com.moobao.indexser.entertainment.field.EntResPathField;
import com.moobao.indexser.entertainment.field.EntSearchContentField;
import com.moobao.indexser.entertainment.field.EntSlaveIdField;
import com.moobao.indexser.entertainment.field.EntSlaveNameField;
import com.moobao.indexser.entertainment.field.EntStatusField;
import com.moobao.indexser.entertainment.field.EntTagField;

/**
 * 根据参数，取出所有的娱乐中心Field的信息，加入document.
 * 
 * @author liuxueyong
 */
public class EntertainmentDocument {
	/**
	 * @param entID
	 * @param entResNo
	 * @param entResName
	 * @param entCpID
	 * @param entDescribe
	 * @param entCatID
	 * @param entSlaveID
	 * @param entTag
	 * @param entBackTag
	 * @param entDownCount
	 * @param entAuthor
	 * @param entResPath
	 * @param entPicPath
	 * @param entMainPic
	 * @param entMinPic
	 * @param entStatus
	 * @param entLastOperTime
	 * @param modelData       娱乐资讯所对应的手机型号列表
	 * @param BrandID         对应的手机品牌ID   
	 * @param brand           手机品牌的中文名称
	 * @return Document
	 */
	public static Document getDocument(int entID, String entResNo,
			String entResName, int entCpID, String entDescribe, int entCatID, int entSlaveID,
			String entSlaveName, String entTag, String entBackTag, int entDownCount, String entAuthor, 
			String entResPath, String entPicPath, String entMainPic, 
			String entMinPic, int entStatus, Date entCreateTime, Date entLastOperTime,
			String modelData, String BrandID, String brand) {

		// 定义一个Document
		Document doc = new Document();
		
		//组合搜索content(资源名称、简介)
		StringBuffer entSearchContent = new StringBuffer();
		entSearchContent.append(entResName + " ");
		entSearchContent.append(entDescribe);

		// 将"创建日期"和"最后更新日期"转为字符串
		String createTime = "", lastOperTime = "";
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		if( entLastOperTime != null )
			lastOperTime = sf.format(entLastOperTime);
		if( entCreateTime != null )
			createTime = sf.format(entCreateTime);

		Field id = EntIdField.getField( entID + "" );
		Field resNo = EntResNoField.getField( entResNo );
		Field resName = EntResNameField.getField( entResName );
		Field cpID = EntCPIdField.getField( entCpID + "" );

		Field describe = EntDescribeField.getField( entDescribe );
		Field catID = EntCatIdField.getField( entCatID + "" );
		Field slaveID = EntSlaveIdField.getField( entSlaveID + "" );
		Field slaveName = EntSlaveNameField.getField( entSlaveName );
		
		Field tag = EntTagField.getField( entTag );
		Field backTag = EntBackTagField.getField( entBackTag );
		Field downCount = EntDownCountField.getField( entDownCount + "" );
		Field author = EntAuthorField.getField( entAuthor );
		
        Field resPath = EntResPathField.getField( entResPath );  
        Field picPath = EntPicPathField.getField( entPicPath );
        Field mainPic = EntMainPicField.getField( entMainPic );
        Field minPic = EntMinPicField.getField( entMinPic );
        
        Field status = EntStatusField.getField( entStatus + "" );
        Field createDt = EntCreateTimeField.getField( createTime );
        Field endOperTime = EntLastOperTimeField.getField( lastOperTime );
		Field searchContent = EntSearchContentField.getField( entSearchContent.toString() );
		
		Field entModel = EntModelField.getField( modelData );  
        Field entBrandId = EntModelIDField.getField( BrandID );
        Field brandData = EntBrandField.getField( brand );
        
		doc.add( id );
		doc.add( resNo );
		doc.add( resName );
		doc.add( cpID );

		doc.add( describe );
		doc.add( catID );
		doc.add( slaveID );
		doc.add( slaveName );
		
		doc.add( tag );
		doc.add( backTag );
		doc.add( downCount );
		doc.add( author );
		
		doc.add( resPath );
		doc.add( picPath );
		doc.add( mainPic );
		doc.add( minPic );
		
		doc.add( status );
	    doc.add( createDt );
		doc.add( endOperTime );
		doc.add( searchContent );
		
		doc.add( entModel );
		doc.add( entBrandId );
		doc.add( brandData );

		return doc;
	}
}
