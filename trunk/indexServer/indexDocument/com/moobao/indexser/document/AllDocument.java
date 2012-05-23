
package com.moobao.indexser.document;

import java.text.DecimalFormat;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

import com.moobao.indexser.all.field.Appearance;
import com.moobao.indexser.all.field.AskCountField;
import com.moobao.indexser.all.field.BaseParaField;
import com.moobao.indexser.all.field.BrandField;
import com.moobao.indexser.all.field.BrandVField;
import com.moobao.indexser.all.field.CameraPixelField;
import com.moobao.indexser.all.field.ClassCodeField;
import com.moobao.indexser.all.field.ColorField;
import com.moobao.indexser.all.field.ContentField;
import com.moobao.indexser.all.field.DescribeField;
import com.moobao.indexser.all.field.IdField;
import com.moobao.indexser.all.field.IfUseField;
import com.moobao.indexser.all.field.KeywordsField;
import com.moobao.indexser.all.field.MarketTimeField;
import com.moobao.indexser.all.field.ModelField;
import com.moobao.indexser.all.field.NameField;
import com.moobao.indexser.all.field.NetHighPriceSortField;
import com.moobao.indexser.all.field.NetLowPriceSortField;
import com.moobao.indexser.all.field.NetPriceRangField;
import com.moobao.indexser.all.field.NetWorkTypeField;
import com.moobao.indexser.all.field.PicListField;
import com.moobao.indexser.all.field.PriceMarketField;
import com.moobao.indexser.all.field.PrimaryId;
import com.moobao.indexser.all.field.PromotionField;
import com.moobao.indexser.all.field.RebateField;
import com.moobao.indexser.all.field.RecommendCountField;
import com.moobao.indexser.all.field.SaleCountField;
import com.moobao.indexser.all.field.ScreenSizeField;
import com.moobao.indexser.document.number.NumberJudge;

/**
 * 根据参数，取出所有的产品Field的信息，加入document.
 * @author liuxueyong
 */
public class AllDocument {
	
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
	public static Document getDocument_new(String pri_productid,String classcode, int brandv, String brand_name, String productModel, String product_para,
			String productname, String describe, String promotion, int saleCount, String market_time, String rebate, String keywords, String net_name, 
			int camera_pixel, double screen_size, String productid, double price_market, double priceNetMin, double priceNetMax, 
			String color, int ifuse, int recommendCount, int askCount, String picList, String shape) {
		//定义一个Document
		Document doc = new Document();
		//名称+描述+推荐语
		StringBuffer searchContent = new StringBuffer();
		//咪啦价格(整数部分统一为12位, 不足补0)，范围搜索
		String netPriceRang = "";
		//要保存入Field的日期格式.
//		String upDate = "", downDate = "";
		
		searchContent.append( productname + " " );
		searchContent.append( keywords + " " );
		searchContent.append( promotion + " ");
		searchContent.append( product_para + " ");
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
			
		Field primaryId = PrimaryId.getField(pri_productid);
		Field classCode = ClassCodeField.getField(classcode);
		Field brandVF = BrandVField.getField(brandv+"");
		Field brand = BrandField.getField( brand_name );
		
		Field pModel = ModelField.getField( productModel );
		Field basePara = BaseParaField.getField( product_para );
		Field name = NameField.getField( productname );
		Field describeP = DescribeField.getField(describe);
		
		Field promotions = PromotionField.getField( promotion );
		Field saleCountt = SaleCountField.getField(saleCount+"");
		Field marketDate = MarketTimeField.getField( marketTimeFormat.toString() );
		Field prebate = RebateField.getField(rebate);
		
		Field key = KeywordsField.getField( keybuff.toString() );
		Field netType = NetWorkTypeField.getField( net_name );
		Field camera = CameraPixelField.getField( cameraRang + "" );
		Field screen = ScreenSizeField.getField( screen_size + "" );
		
		Field id  = IdField.getField(productid);
		Field market = PriceMarketField.getField( price_market+"" );
		Field sortPrice = NetLowPriceSortField.getField( netMin );
		Field maxPrice = NetHighPriceSortField.getField( netMax );
		
		Field rangPrice = NetPriceRangField.getField( netPriceRang );
		Field pcolor = ColorField.getField(color);         //有货颜色的组合字串
		Field IfUse = IfUseField.getField(ifuse+"");
//		Field uptime = UpTimeField.getField( upDate );
//		Field downtime = DownTimeField.getField( downDate );
		Field pics = PicListField.getField( picList );
		

		Field recommendcount = RecommendCountField.getField(recommendCount+"");
		Field askcount = AskCountField.getField(askCount+"");
		Field content = ContentField.getField( searchContent.toString() );
		Field style = Appearance.getField(shape);       //外观
	
		doc.add( primaryId );
		doc.add( classCode );
		doc.add( brandVF );
		doc.add( brand );
		
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
		doc.add( IfUse );
		doc.add( pics );
		
		doc.add(recommendcount);
		doc.add(askcount);
		doc.add(content);
		doc.add(style);
		return doc;
    }
}
