
package com.moobao.indexser.document;

import java.text.DecimalFormat;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

import com.moobao.indexser.document.number.NumberJudge;
import com.moobao.indexser.phone.field.CameraPixelField;
import com.moobao.indexser.phone.field.NetWorkTypeField;
import com.moobao.indexser.phone.field.PhoneAppearance;
import com.moobao.indexser.phone.field.PhoneAskCountField;
import com.moobao.indexser.phone.field.PhoneBaseParaField;
import com.moobao.indexser.phone.field.PhoneBrandCodeField;
import com.moobao.indexser.phone.field.PhoneBrandField;
import com.moobao.indexser.phone.field.PhoneBrandVField;
import com.moobao.indexser.phone.field.PhoneClassCodeField;
import com.moobao.indexser.phone.field.PhoneColorField;
import com.moobao.indexser.phone.field.PhoneColorNoField;
import com.moobao.indexser.phone.field.PhoneContentField;
import com.moobao.indexser.phone.field.PhoneCrowdNameField;
import com.moobao.indexser.phone.field.PhoneDescribeField;
import com.moobao.indexser.phone.field.PhoneFunctionIntField;
import com.moobao.indexser.phone.field.PhoneIdField;
import com.moobao.indexser.phone.field.PhoneIfRecommendField;
import com.moobao.indexser.phone.field.PhoneIfUseField;
import com.moobao.indexser.phone.field.PhoneIfdirectField;
import com.moobao.indexser.phone.field.PhoneKeywordsField;
import com.moobao.indexser.phone.field.PhoneMarketTimeField;
import com.moobao.indexser.phone.field.PhoneMeritField;
import com.moobao.indexser.phone.field.PhoneModelField;
import com.moobao.indexser.phone.field.PhoneNameField;
import com.moobao.indexser.phone.field.PhoneNetHighPriceSortField;
import com.moobao.indexser.phone.field.PhoneNetLowPriceSortField;
import com.moobao.indexser.phone.field.PhoneNetPriceRangField;
import com.moobao.indexser.phone.field.PhonePicListField;
import com.moobao.indexser.phone.field.PhonePriceMarketField;
import com.moobao.indexser.phone.field.PhonePrimaryId;
import com.moobao.indexser.phone.field.PhoneProductClassField;
import com.moobao.indexser.phone.field.PhonePromotionField;
import com.moobao.indexser.phone.field.PhoneRebateField;
import com.moobao.indexser.phone.field.PhoneRecommendCountField;
import com.moobao.indexser.phone.field.PhoneSaleCountField;
import com.moobao.indexser.phone.field.PhoneWeightIndexField;
import com.moobao.indexser.phone.field.ScreenSizeField;
import com.moobao.service.common.UrlParameterEncoder;

/**
 * 根据参数，取出所有的手机Field的信息，加入document.
 * @author liuxueyong
 */
public class PhoneDocument {

	/**
	 * @param productid
	 * @param productName
	 * @param describe
	 * @param promotion
	 * @param marketPrice
	 * @param netPriceSort
	 * @param netPriceRang
	 * @param saleCount
	 * @param upTime
	 * @param picList
	 * @param phoneBrand
	 * @param netWorkType
	 * @param OS
	 * @param appearance
	 * @param screenSize
	 * @param cameraPixel
	 * @param marketTime
	 * @param searchContent   (名称+简单描述+推荐语+手机型号)
	 * @param function
	 * @param model 手机型号
	 * @throws no thrown Exception
	 * @return Document
	 */
//	public static Document getDocument(String productid, String productName, String describe, String promotion, 
//			                           String marketPrice, double netPrice, String saleCount,
//			                           Date upTime, Date downTime, String picList, String phoneBrand, String netWorkType,
//			                           String OS, String appearance, double screenSize, int cameraPixel,
//			                           String marketTime, String function, String model, String keywords, String color , int brandV
//			                           , int ifuse) {
//		//定义一个Document
//		Document doc = new Document();
//		//名称+描述+推荐语
//		StringBuffer searchContent = new StringBuffer();
//		//摩宝价格(整数部分统一为12位, 不足补0)，范围搜索
//		String netPriceRang = "";
//		//要保存入Field的日期格式.
//		String upDate = "", downDate = "";
//		
//		searchContent.append( productName + " " );
//		searchContent.append( describe + " " );
//		searchContent.append( promotion );
//		String modeList = "";
//		if( model != null && !model.trim().equals("") ) {
//			modeList = NumberJudge.splitProductModel( model );
//		}
//		searchContent.append( modeList );
//		
//		//将摩宝价netPrice转化为字符串型,可排序.
//		String moobaoPrice = netPrice + "";
//		
//		
//		//将"摩宝价"整数部分扩为统一的12位(高级搜索)
//		DecimalFormat df = new DecimalFormat( "000000000000.000" );
//		netPriceRang = df.format( netPrice );
//		
//		//将"摄像头像素"扩为统一的8位(高级搜索)
//		df = new DecimalFormat( "00000000" );
//		String cameraRang = df.format( (double)cameraPixel );
//		
//		//将日期"上架时间"和"下架时间"转为字符串
//		SimpleDateFormat sf = new SimpleDateFormat( "yyyyMMdd" );
//		if( upTime != null )
//			upDate = sf.format( upTime );
//		if( downTime != null )
//			downDate = sf.format( downTime );
//		
//		//将productModel的各种形式加入到keywords中.
//		StringBuffer keybuff = new StringBuffer();
//		keybuff.append( keywords );
//		if( model != null && !model.trim().equals("") ) {
//			keybuff.append( modeList );
//		}
//		
//		StringBuffer marketTimeFormat = new StringBuffer();
//		if( !marketTime.equals("") && !marketTime.equals(null) && marketTime.contains("-") ) {
//			int first = marketTime.indexOf("-");
//			int last = marketTime.lastIndexOf("-");
//			String one = marketTime.substring( first + 1, last ).trim();
//			String two = marketTime.substring( last + 1 ).trim();
//			if( one.length() < 2 )
//				one = "0" + one ;
//			if( two.length() < 2 )
//				two = "0" + two;
//			marketTimeFormat.append( marketTime.substring(0, first) );
//			marketTimeFormat.append( one );
//			marketTimeFormat.append( two );
//		}
//			
//		
//		Field id  = PhoneIdField.getField(productid);
//		Field name = PhoneNameField.getField( productName );
//		Field description = PhoneBaseParaField.getField( describe );
//		Field promotions = PhonePromotionField.getField( promotion );
//		
//		Field market = PhonePriceMarketField.getField( marketPrice );
//		Field sortPrice = PhoneNetLowPriceSortField.getField( moobaoPrice );
//		Field rangPrice = PhoneNetPriceRangField.getField( netPriceRang );
//		Field saleCountt = PhoneSaleCountField.getField( saleCount );
//		
//		Field uptime = PhoneUpTimeField.getField( upDate );
//		Field downtime = PhoneDownTimeField.getField( downDate );
//		Field pics = PhonePicListField.getField( picList );
//		Field brand = PhoneBrandField.getField( phoneBrand );
//		
//		Field netType = NetWorkTypeField.getField( netWorkType );
//		Field os = PhoneOSField.getField( OS );
//		Field appear = PhoneAppearance.getField( appearance );
//		Field screen = ScreenSizeField.getField( screenSize + "" );
//		
//		Field camera = CameraPixelField.getField( cameraRang + "" );
//		Field marketDate = PhoneMarketTimeField.getField( marketTimeFormat.toString() );
//		Field content = PhoneContentField.getField( searchContent.toString() );
//		Field func = phoneFunctionField.getField( function );
//		
//		Field pModel = PhoneModelField.getField( model );
//		Field key = PhoneKeywordsField.getField( keybuff.toString() );
//		Field pcolor = PhoneColorField.getField(color);
//		Field brandVF = PhoneBrandVField.getField(brandV+"");
//		
//		Field IfUse = PhoneIfUseField.getField(ifuse+"");
//		
//		doc.add( id );
//		doc.add( name );
//		doc.add( description );
//		doc.add( promotions );
//		
//		doc.add( market );
//		doc.add( sortPrice );
//		doc.add( rangPrice );
//		doc.add( saleCountt );
//		
//		doc.add( uptime );
//		doc.add( downtime );
//		doc.add( pics );
//		doc.add( brand );
//		
//		doc.add( netType );
//		doc.add( os );
//		doc.add( appear );
//		doc.add( screen );
//		
//		doc.add( camera );
//		doc.add( marketDate );
//		doc.add( content );
//		doc.add( func );
//		
//		doc.add( pModel );
//		doc.add( key );
//		doc.add( pcolor );
//		doc.add(brandVF);
//		
//		doc.add(IfUse);
//		return doc;
//    }
	
	/**
	 * @param productid
	 * @param productName
	 * @param describe
	 * @param promotion
	 * @param marketPrice
	 * @param netPriceSort
	 * @param netPriceRang
	 * @param saleCount
	 * @param upTime
	 * @param picList
	 * @param phoneBrand
	 * @param netWorkType
	 * @param OS
	 * @param appearance
	 * @param screenSize
	 * @param cameraPixel
	 * @param marketTime
	 * @param searchContent   (名称+简单描述+推荐语+手机型号)
	 * @param function
	 * @param model 手机型号
	 * @throws no thrown Exception
	 * @return Document
	 */
	public static Document getDocument_new(String pri_productid,String classcode, String productclass, int brandv, String brand_code, String brand_name, String productModel, String product_para,
			String productname, String describe, String promotion, int saleCount, String market_time, String rebate, String keywords, String net_name, 
			int camera_pixel, double screen_size, String productid, double price_market, double priceNetMin, double priceNetMax, 
			String color, String colorNo, int ifuse, int recommendCount, int askCount, String picList, String shape, String defect,
			String merit, String crowd_name, String ifdirect, String weightIndex, String ifrecommend, int func) {
		//定义一个Document
		Document doc = new Document();
		//名称+描述+推荐语
		StringBuffer searchContent = new StringBuffer();
		
		StringBuffer productPara = new StringBuffer();
		productPara.append( product_para + "" );
		productPara.append( UrlParameterEncoder.getFunc(func) );
		//咪啦价格(整数部分统一为12位, 不足补0)，范围搜索
		String netPriceRang = "";
		//要保存入Field的日期格式.
//		String upDate = "", downDate = "";
		
		searchContent.append( productname + " " );
		searchContent.append( keywords + " " );
		searchContent.append( promotion + " ");
		searchContent.append( product_para + " ");
		searchContent.append( defect + " ");
		searchContent.append( merit + " ");
		searchContent.append( crowd_name + " ");
		
		String modeList = "";
		if( productModel != null && !productModel.trim().equals("") ) {
			modeList = NumberJudge.splitProductModel( productModel );
		}
		searchContent.append( modeList );
		
		//将咪啦价priceNetMin和priceNetMax转化为字符串型,可排序.
		String netMin = priceNetMin + "";    //同型号的最低价
		String netMax = priceNetMax + "";    //同型号的最高价
		
		
		//将"咪啦价"整数部分扩为统一的12位(高级搜索)
		DecimalFormat df = new DecimalFormat( "000000000000.000" );
		netPriceRang = df.format( priceNetMin );
		
		//将"摄像头像素"扩为统一的8位(高级搜索)
		df = new DecimalFormat( "00000000" );
		String cameraRang = df.format( (double)camera_pixel );
		
		//将日期"上架时间"和"下架时间"转为字符串
//		SimpleDateFormat sf = new SimpleDateFormat( "yyyyMMdd" );
//		if( upTime != null )
//			upDate = sf.format( upTime );
//		if( downTime != null )
//			downDate = sf.format( downTime );
		
		//将productModel的各种形式加入到keywords中.
		StringBuffer keybuff = new StringBuffer();
		keybuff.append( keywords );
		if( productModel != null && !productModel.trim().equals("") ) {
			keybuff.append( modeList );
		}
		
		StringBuffer marketTimeFormat = new StringBuffer();
		if( !market_time.equals("") && !market_time.equals(null) && market_time.contains("-") ) {
			int first = market_time.indexOf("-");
			int last = market_time.lastIndexOf("-");
			String one = market_time.substring( first + 1, last ).trim();
			String two = market_time.substring( last + 1 ).trim();
			if( one.length() < 2 )
				one = "0" + one ;
			if( two.length() < 2 )
				two = "0" + two;
			marketTimeFormat.append( market_time.substring(0, first) );
			marketTimeFormat.append( one );
			marketTimeFormat.append( two );
		}
			
		Field primaryId = PhonePrimaryId.getField(pri_productid);
		Field classCode = PhoneClassCodeField.getField(classcode);
		Field prodClass = PhoneProductClassField.getField(productclass);
		Field brandVF = PhoneBrandVField.getField(brandv+"");
		Field brandcode = PhoneBrandCodeField.getField( brand_code );
		Field brandname = PhoneBrandField.getField( brand_name );
		
		Field pModel = PhoneModelField.getField( productModel );
		Field basePara = PhoneBaseParaField.getField( productPara.toString() );
		Field name = PhoneNameField.getField( productname );
		Field describeP = PhoneDescribeField.getField(describe);
		
		Field promotions = PhonePromotionField.getField( promotion );
		Field saleCountt = PhoneSaleCountField.getField(saleCount+"");
		Field marketDate = PhoneMarketTimeField.getField( marketTimeFormat.toString() );
		Field prebate = PhoneRebateField.getField(rebate);
		
		Field key = PhoneKeywordsField.getField( keybuff.toString() );
		Field netType = NetWorkTypeField.getField( net_name );
		Field camera = CameraPixelField.getField( cameraRang + "" );
		Field screen = ScreenSizeField.getField( screen_size + "" );
		
		Field id  = PhoneIdField.getField(productid);
		Field market = PhonePriceMarketField.getField( price_market+"" );
		Field sortPrice = PhoneNetLowPriceSortField.getField( netMin );
		Field maxPrice = PhoneNetHighPriceSortField.getField( netMax );
		
		Field rangPrice = PhoneNetPriceRangField.getField( netPriceRang );
		Field pcolor = PhoneColorField.getField(color);         //有货颜色的组合字串
		Field pcolorNo = PhoneColorNoField.getField(colorNo);   //无货颜色的组合字串
		Field IfUse = PhoneIfUseField.getField(ifuse+"");
//		Field uptime = PhoneUpTimeField.getField( upDate );
//		Field downtime = PhoneDownTimeField.getField( downDate );
		Field pics = PhonePicListField.getField( picList );
		

		Field recommendcount = PhoneRecommendCountField.getField(recommendCount+"");
		Field askcount = PhoneAskCountField.getField(askCount+"");
		Field content = PhoneContentField.getField( searchContent.toString() );
		Field style = PhoneAppearance.getField(shape);       //外观
		
		Field crowd = PhoneCrowdNameField.getField(crowd_name);                //人群
		Field meritt = PhoneMeritField.getField(merit);
		Field ifdir = PhoneIfdirectField.getField(ifdirect);                   //是否主推
		Field weightindex = PhoneWeightIndexField.getField(weightIndex);       //权重
		
		Field ifRecommend = PhoneIfRecommendField.getField(ifrecommend);       //是否编辑推荐
		Field fun = PhoneFunctionIntField.getField(func+"");             //功能(如:32)
	
		doc.add( primaryId );
		doc.add( classCode );
		doc.add( prodClass );
		doc.add( brandVF );
		doc.add( brandcode );
		doc.add( brandname );
		
		doc.add( pModel );
		doc.add( basePara );
		doc.add( name );
		doc.add( describeP );
		
		doc.add( promotions );
		doc.add( saleCountt );
		doc.add( marketDate );
		doc.add( prebate );
		
		doc.add( key );
		doc.add( netType );
		doc.add( camera );
		doc.add( screen );
		
		doc.add( id );
		doc.add( market );
		doc.add( sortPrice );
		doc.add( maxPrice );
		
		doc.add( rangPrice );
		doc.add( pcolor );
		doc.add( pcolorNo );
		doc.add( IfUse );
		doc.add( pics );
		
		doc.add(recommendcount);
		doc.add(askcount);
		doc.add(content);
		doc.add(style);
		
		doc.add(crowd);
		doc.add(meritt);
		doc.add(ifdir);
		doc.add(weightindex);
		
		doc.add(ifRecommend);
		doc.add(fun);
		return doc;
    }
}
