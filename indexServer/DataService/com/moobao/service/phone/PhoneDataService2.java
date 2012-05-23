package com.moobao.service.phone;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.document.Document;

import com.apply.b2b.cms.base.IDAO;
import com.apply.b2b.cms.util.RowSetTypeConverter;
import com.apply.b2b.cms.util.StringUtil;
import com.moobao.indexser.document.PhoneDocument;
import com.moobao.service.DataBaseService;

public class PhoneDataService2 extends DataBaseService {

	

	/**
	 * 重新编译手机视图
	 * 
	 * @return
	 */
//	public boolean reCompileView() {
//		Boolean f = false;
//		try {
//			StringBuilder sql = new StringBuilder("");
//			sql.append(" alter view V_MOBILE_INDEX compile ");
//			IDAO dao = this.getDao();
//			dao.executeUpdate( sql.toString() );
//			f = true;
//		}
//		catch( Exception e ) {
//			System.out.println( "重新编译手机视图异常!" );
//		}
//		return f;
//	}
	
	
	/**
	 * 获得已下架的手机数量
	 * 
	 * @return
	 */
	public int getUnSalePhoneNums() {
		StringBuilder sql = new StringBuilder("");
		sql.append(" select count(1) ");
		sql.append(" from v_mobile_index t ");
		sql.append(" where ");
		sql.append(" t.down_time < sysdate or t.ifuse = 0");
		IDAO dao = this.getDao();
		return dao.getQueryInt(sql.toString());
	}

	/**
	 * 获得已下架的手机信息
	 * 
	 * @param beginNum
	 * @param endNum
	 * @return
	 */
	public List<Document> getUnSalePhonePhoneDatas(int beginNum, int endNum) {
		List<Document> listData = new ArrayList<Document>();
		if (endNum >= beginNum && endNum > 0) {
			StringBuilder sql = new StringBuilder("");
			
			
			
		sql.append(" select t.productid, ");
		sql.append(" t.productno, ");
		sql.append(" t.productname, ");
	    sql.append(" t.promotion, ");
	    sql.append(" t.productclass, ");
	    sql.append(" t.brand,");
	    sql.append(" (select dict.valuename ");
	    sql.append(" from dict_basetype_value dict ");
	    sql.append(" where dict.valueid = t.brand) brand_name, ");
	    sql.append(" t.price_net, ");
	    sql.append(" t.price_market, ");
	    sql.append(" t.price_set, ");
	    sql.append(" t.score_add, ");
	    sql.append(" t.score_use, ");
	    sql.append(" t.ifset, ");
	    sql.append(" t.ifuse, ");
	    sql.append(" t.ifvalid, ");
	    sql.append(" t.ifentity, ");
	    sql.append(" t.ifscore, ");
	    sql.append(" t.up_time, ");
	    sql.append(" t.down_time, ");
	    sql.append(" t.keywords, ");
	    sql.append(" t.memo, ");
	    sql.append(" t.describe, ");
	    sql.append(" t.create_dt, ");
	    sql.append(" t.update_type, ");
	    sql.append(" t.ifindex, ");
	    sql.append(" t.price_standby, ");
	    sql.append(" t.valid_time, ");
	    sql.append(" t.ifdelete, ");
	    sql.append(" t.productmodel, ");
	    sql.append(" t.sale_count, ");
	    sql.append(" pma.ifspecial,");
	    sql.append(" pma.ifnew, ");
	    sql.append(" pma.ifdiscount, ");
	    sql.append(" pma.ifbestsell, ");
	    sql.append(" pma.ifhandwrite, ");
	    sql.append(" decode(pma.ifhandwrite, 1, '手写', '') handwrite_fun, ");
	    sql.append(" pma.ifthin,");
	    sql.append(" decode(pma.ifthin, 1, '超薄', '') thin, ");
	    sql.append(" pma.iffemale, ");
	    sql.append(" decode(pma.iffemale, 1, '女士', '') femail_fun, ");
	    sql.append(" pma.main_function, ");
	    sql.append(" pma.additional, ");
	    sql.append(" pma.net_standard, ");
	    sql.append(" (select dict.valuename ");
	    sql.append(" from dict_basetype_value dict ");
	    sql.append(" where dict.valueid = pma.net_standard) net_name, ");

	    sql.append(" pma.color, ");		
	    sql.append(" pma.material, ");				
	    sql.append(" (select dict.valuename ");
	    sql.append(" from dict_basetype_value dict ");
	    sql.append(" where dict.valueid = pma.material) material_name, ");
	    sql.append(" pma.shape, ");						
	    sql.append(" (select dict.valuename ");													
	    sql.append(" from dict_basetype_value dict ");
	    sql.append(" where dict.valueid = pma.shape) shap_name, ");
	    sql.append(" pma.memory, ");
	    sql.append(" pma.dimensions, ");
	    sql.append(" pma.weight, ");
	    sql.append(" pma.configuration, ");
	    sql.append(" pma.ring, ");
	    sql.append(" pma.call_time, ");
	    sql.append(" pma.standby_time, pma.bluetooth, decode(pma.BLUETOOTH, 1, '蓝牙', '') bluetooth_fun, ");
	    sql.append(" pma.infrared, decode(pma.infrared, 1, '红外', '') frared_fun, pma.EXPANSION_CARD, ");
	    sql.append(" decode(pma.EXPANSION_CARD, null, '', '扩展存储') expansion_card_fun, ");

	    sql.append(" pma.camera_pixel, pma.zoom, pma.mp3, decode(pma.mp3, 1, 'mp3', '') mp3_fun, ");
	    sql.append(" pma.video, pma.video_capture, decode(pma.video_capture, 1, '视频拍摄', '') video_capture_fun, ");

	    sql.append(" pma.video_resolution, pma.os, (select dict.valuename ");
	    sql.append(" from dict_basetype_value dict where dict.valueid = pma.os) os_name, ");

	    sql.append(" pma.gps, decode(pma.gps, null || 0, '', 'gps') gps_fun, pma.media, pma.screen_size, ");
	    sql.append(" pma.screen_parameter, pma.email, decode(pma.email, 1, 'email', '') email_fun, ");
	    sql.append(" pma.audio, decode(pma.audio, 1, '录音', '') audio_fun, pma.wap, ");
	    sql.append(" decode(pma.wap, 1, 'wap', '') wap_fun, pma.market_time, ");
	    sql.append(" f_get_product_pic(t.productid, 1) photo_name from product_list t ");
	    sql.append(" join product_mobile_attr pma on t.productid = pma.productid ");

	    sql.append(" where t.productclass = 1 and t.ifuse = 0 " );
	    sql.append(" or t.productclass = 1 and t.down_time < sysdate " );
	    
			
			
			
			
//			sql.append("select productid, ");
//			sql.append("productno, ");
//			sql.append("productname, ");
//			sql.append("promotion, ");
//			sql.append("productclass, ");
//			sql.append("brand, ");
//			sql.append("brand_name, ");
//			sql.append("price_net, ");
//			sql.append("price_market, ");
//			sql.append("price_set, ");
//			sql.append("score_add, ");
//			sql.append("score_use, ");
//			sql.append("ifset, ");
//			sql.append("ifuse, ");
//			sql.append("ifvalid, ");
//			sql.append("ifentity, ");
//			sql.append("ifscore, ");
//			sql.append("up_time, ");
//			sql.append("down_time, ");
//			sql.append("keywords, ");
//			//sql.append("memo, ");
//			sql.append("describe, ");
//			sql.append("create_dt, ");
//			sql.append("update_type, ");
//			sql.append("ifindex, ");
//			sql.append("price_standby, ");
//			sql.append("valid_time, ");
//			sql.append("ifdelete, ");
//			sql.append("productmodel, ");
//			sql.append("sale_count, ");
//			sql.append("ifspecial, ");
//			sql.append("ifnew, ");
//			sql.append("ifdiscount, ");
//			sql.append("ifbestsell, ");
//			sql.append("ifhandwrite, ");
//			sql.append("handwrite_fun, ");
//			sql.append("ifthin, ");
//			sql.append("thin, ");
//			sql.append("iffemale, ");
//			sql.append("femail_fun, ");
//			sql.append("main_function, ");
//			sql.append("additional, ");
//			sql.append("net_standard, ");
//			sql.append("net_name, ");
//			sql.append("color, ");
//			sql.append("material, ");
//			sql.append("material_name, ");
//			sql.append("shape, ");
//			sql.append("shap_name, ");
//			sql.append("memory, ");
//			sql.append("dimensions, ");
//			sql.append("weight, ");
//			sql.append("configuration, ");
//			sql.append("ring, ");
//			sql.append("call_time, ");
//			sql.append("standby_time, ");
//			sql.append("bluetooth, ");
//			sql.append("bluetooth_fun, ");
//			sql.append("infrared, ");
//			sql.append("frared_fun, ");
//			sql.append("expansion_card, ");
//			sql.append("expansion_card_fun, ");
//			sql.append("expansion_card_name, ");
//			sql.append("camera_pixel, ");
//			sql.append("zoom, ");
//			sql.append("mp3, ");
//			sql.append("mp3_fun, ");
//			sql.append("video, ");
//			sql.append("video_capture, ");
//			sql.append("video_capture_fun, ");
//			sql.append("video_resolution, ");
//			sql.append("os, ");
//			sql.append("os_name, ");
//			sql.append("gps, ");
//			sql.append("gps_fun, ");
//			sql.append("media, ");
//			sql.append("screen_size, ");
//			sql.append("screen_parameter, ");
//			sql.append("email, ");
//			sql.append("email_fun, ");
//			sql.append("audio, ");
//			sql.append("audio_fun, ");
//			sql.append("wap, ");
//			sql.append("wap_fun, ");
//			sql.append("market_time, ");
//			sql.append("photo_name ");
//			sql.append(" from v_mobile_index t ");
//			sql.append(" where t.down_time < sysdate ");
//			
//			sql.append(" UNION ");
//			
//			
//			sql.append("select productid, ");
//			sql.append("productno, ");
//			sql.append("productname, ");
//			sql.append("promotion, ");
//			sql.append("productclass, ");
//			sql.append("brand, ");
//			sql.append("brand_name, ");
//			sql.append("price_net, ");
//			sql.append("price_market, ");
//			sql.append("price_set, ");
//			sql.append("score_add, ");
//			sql.append("score_use, ");
//			sql.append("ifset, ");
//			sql.append("ifuse, ");
//			sql.append("ifvalid, ");
//			sql.append("ifentity, ");
//			sql.append("ifscore, ");
//			sql.append("up_time, ");
//			sql.append("down_time, ");
//			sql.append("keywords, ");
//			//sql.append("memo, ");
//			sql.append("describe, ");
//			sql.append("create_dt, ");
//			sql.append("update_type, ");
//			sql.append("ifindex, ");
//			sql.append("price_standby, ");
//			sql.append("valid_time, ");
//			sql.append("ifdelete, ");
//			sql.append("productmodel, ");
//			sql.append("sale_count, ");
//			sql.append("ifspecial, ");
//			sql.append("ifnew, ");
//			sql.append("ifdiscount, ");
//			sql.append("ifbestsell, ");
//			sql.append("ifhandwrite, ");
//			sql.append("handwrite_fun, ");
//			sql.append("ifthin, ");
//			sql.append("thin, ");
//			sql.append("iffemale, ");
//			sql.append("femail_fun, ");
//			sql.append("main_function, ");
//			sql.append("additional, ");
//			sql.append("net_standard, ");
//			sql.append("net_name, ");
//			sql.append("color, ");
//			sql.append("material, ");
//			sql.append("material_name, ");
//			sql.append("shape, ");
//			sql.append("shap_name, ");
//			sql.append("memory, ");
//			sql.append("dimensions, ");
//			sql.append("weight, ");
//			sql.append("configuration, ");
//			sql.append("ring, ");
//			sql.append("call_time, ");
//			sql.append("standby_time, ");
//			sql.append("bluetooth, ");
//			sql.append("bluetooth_fun, ");
//			sql.append("infrared, ");
//			sql.append("frared_fun, ");
//			sql.append("expansion_card, ");
//			sql.append("expansion_card_fun, ");
//			sql.append("expansion_card_name, ");
//			sql.append("camera_pixel, ");
//			sql.append("zoom, ");
//			sql.append("mp3, ");
//			sql.append("mp3_fun, ");
//			sql.append("video, ");
//			sql.append("video_capture, ");
//			sql.append("video_capture_fun, ");
//			sql.append("video_resolution, ");
//			sql.append("os, ");
//			sql.append("os_name, ");
//			sql.append("gps, ");
//			sql.append("gps_fun, ");
//			sql.append("media, ");
//			sql.append("screen_size, ");
//			sql.append("screen_parameter, ");
//			sql.append("email, ");
//			sql.append("email_fun, ");
//			sql.append("audio, ");
//			sql.append("audio_fun, ");
//			sql.append("wap, ");
//			sql.append("wap_fun, ");
//			sql.append("market_time, ");
//			sql.append("photo_name ");
//			sql.append(" from v_mobile_index tt ");
//			sql.append(" where tt.ifuse = 0 ");
			
			
			IDAO dao = this.getDao();
			
			List data = dao.getCountQuery(sql.toString(), endNum, beginNum);
			
			if (data != null && data.size() > 0) {
				for (Object obj : data) {
					Map entry = (Map) obj;
					if (entry != null) {
						Document doc = buildPhoneIndexDocument(entry);
						if (doc != null) {
							listData.add(doc);
						}
					}
				}
			}
		}
		return listData;
	}

	/**
	 * 获得上架销售的手机数量
	 * 
	 * @return
	 */
	public int getAllPhoneNums() {
		StringBuilder sql = new StringBuilder("");
		sql.append(" select count(1) ");
		sql.append(" from v_mobile_index t ");
		sql.append(" where t.up_time < sysdate ");
		sql.append(" and t.down_time > sysdate ");
		sql.append(" and t.ifuse = 1 and t.ifdelete = 0 ");
		IDAO dao = this.getDao();
		return dao.getQueryInt(sql.toString());
	}

	/**
	 * 获得上架销售的没有被索引的手机数量
	 * 
	 * @return
	 */
	public int getUnIndexedPhoneNums() {
		StringBuilder sql = new StringBuilder("");
		sql.append(" select count(1) ");
		sql.append(" from v_mobile_index t ");
		sql.append(" where t.up_time<sysdate ");
		sql.append(" and t.down_time>sysdate ");

		sql.append(" and t.ifuse=1 ");
		sql.append(" and t.ifindex = 0 ");
		sql.append(" and t.ifdelete = 0 ");

		
		IDAO dao = this.getDao();
		System.out.println(dao.getQueryInt(sql.toString()));
		return dao.getQueryInt(sql.toString());
	}
	
	/**
	 * 获得已删除了的手机数量
	 * 
	 * @return
	 */
	public int getDeletedPhoneNums() {
		StringBuilder sql = new StringBuilder("");
		
		sql.append(" select count(1) ");
		sql.append(" from v_mobile_index t ");
		sql.append(" where t.up_time < sysdate ");
		sql.append(" and t.down_time > sysdate ");
		sql.append(" and t.ifindex = 0 ");
		sql.append(" and t.ifdelete = 1 ");
		
		IDAO dao = this.getDao();
		return dao.getQueryInt(sql.toString());
	}
	
	/**
	 * 获得未被索引且已被删除了的手机信息
	 * 
	 * @param beginNum
	 * @param endNum
	 * @return
	 */
	public List<Document> getDeletedPhoneDatas(int beginNum, int endNum) {
		List<Document> listData = new ArrayList<Document>();
		if (endNum >= beginNum && endNum > 0) {
			StringBuilder sql = new StringBuilder("");
//			sql.append("select productid, ");
//			sql.append("productno, ");
//			sql.append("productname, ");
//			sql.append("promotion, ");
//			sql.append("productclass, ");
//			sql.append("brand, ");
//			sql.append("brand_name, ");
//			sql.append("price_net, ");
//			sql.append("price_market, ");
//			sql.append("price_set, ");
//			sql.append("score_add, ");
//			sql.append("score_use, ");
//			sql.append("ifset, ");
//			sql.append("ifuse, ");
//			sql.append("ifvalid, ");
//			sql.append("ifentity, ");
//			sql.append("ifscore, ");
//			sql.append("up_time, ");
//			sql.append("down_time, ");
//			sql.append("keywords, ");
//			sql.append("memo, ");
//			sql.append("describe, ");
//			sql.append("create_dt, ");
//			sql.append("update_type, ");
//			sql.append("ifindex, ");
//			sql.append("price_standby, ");
//			sql.append("valid_time, ");
//			sql.append("ifdelete, ");
//			sql.append("productmodel, ");
//			sql.append("sale_count, ");
//			sql.append("ifspecial, ");
//			sql.append("ifnew, ");
//			sql.append("ifdiscount, ");
//			sql.append("ifbestsell, ");
//			sql.append("ifhandwrite, ");
//			sql.append("handwrite_fun, ");
//			sql.append("ifthin, ");
//			sql.append("thin, ");
//			sql.append("iffemale, ");
//			sql.append("femail_fun, ");
//			sql.append("main_function, ");
//			sql.append("additional, ");
//			sql.append("net_standard, ");
//			sql.append("net_name, ");
//			sql.append("color, ");
//			sql.append("material, ");
//			sql.append("material_name, ");
//			sql.append("shape, ");
//			sql.append("shap_name, ");
//			sql.append("memory, ");
//			sql.append("dimensions, ");
//			sql.append("weight, ");
//			sql.append("configuration, ");
//			sql.append("ring, ");
//			sql.append("call_time, ");
//			sql.append("standby_time, ");
//			sql.append("bluetooth, ");
//			sql.append("bluetooth_fun, ");
//			sql.append("infrared, ");
//			sql.append("frared_fun, ");
//			sql.append("expansion_card, ");
//			sql.append("expansion_card_fun, ");
//			sql.append("expansion_card_name, ");
//			sql.append("camera_pixel, ");
//			sql.append("zoom, ");
//			sql.append("mp3, ");
//			sql.append("mp3_fun, ");
//			sql.append("video, ");
//			sql.append("video_capture, ");
//			sql.append("video_capture_fun, ");
//			sql.append("video_resolution, ");
//			sql.append("os, ");
//			sql.append("os_name, ");
//			sql.append("gps, ");
//			sql.append("gps_fun, ");
//			sql.append("media, ");
//			sql.append("screen_size, ");
//			sql.append("screen_parameter, ");
//			sql.append("email, ");
//			sql.append("email_fun, ");
//			sql.append("audio, ");
//			sql.append("audio_fun, ");
//			sql.append("wap, ");
//			sql.append("wap_fun, ");
//			sql.append("market_time, ");
//			sql.append("photo_name ");
//			sql.append(" from v_mobile_index t ");
//			sql.append(" where t.up_time<sysdate ");
//			sql.append(" and t.down_time>sysdate ");
//			sql.append(" and t.ifindex = 0 ");
//			sql.append(" and t.ifdelete = 1 ");
			
			
			sql.append(" select t.productid, ");
			sql.append(" t.productno, ");
			sql.append(" t.productname, ");
		    sql.append(" t.promotion, ");
		    sql.append(" t.productclass, ");
		    sql.append(" t.brand,");
		    sql.append(" (select dict.valuename ");
		    sql.append(" from dict_basetype_value dict ");
		    sql.append(" where dict.valueid = t.brand) brand_name, ");
		    sql.append(" t.price_net, ");
		    sql.append(" t.price_market, ");
		    sql.append(" t.price_set, ");
		    sql.append(" t.score_add, ");
		    sql.append(" t.score_use, ");
		    sql.append(" t.ifset, ");
		    sql.append(" t.ifuse, ");
		    sql.append(" t.ifvalid, ");
		    sql.append(" t.ifentity, ");
		    sql.append(" t.ifscore, ");
		    sql.append(" t.up_time, ");
		    sql.append(" t.down_time, ");
		    sql.append(" t.keywords, ");
		    sql.append(" t.memo, ");
		    sql.append(" t.describe, ");
		    sql.append(" t.create_dt, ");
		    sql.append(" t.update_type, ");
		    sql.append(" t.ifindex, ");
		    sql.append(" t.price_standby, ");
		    sql.append(" t.valid_time, ");
		    sql.append(" t.ifdelete, ");
		    sql.append(" t.productmodel, ");
		    sql.append(" t.sale_count, ");
		    sql.append(" pma.ifspecial,");
		    sql.append(" pma.ifnew, ");
		    sql.append(" pma.ifdiscount, ");
		    sql.append(" pma.ifbestsell, ");
		    sql.append(" pma.ifhandwrite, ");
		    sql.append(" decode(pma.ifhandwrite, 1, '手写', '') handwrite_fun, ");
		    sql.append(" pma.ifthin,");
		    sql.append(" decode(pma.ifthin, 1, '超薄', '') thin, ");
		    sql.append(" pma.iffemale, ");
		    sql.append(" decode(pma.iffemale, 1, '女士', '') femail_fun, ");
		    sql.append(" pma.main_function, ");
		    sql.append(" pma.additional, ");
		    sql.append(" pma.net_standard, ");
		    sql.append(" (select dict.valuename ");
		    sql.append(" from dict_basetype_value dict ");
		    sql.append(" where dict.valueid = pma.net_standard) net_name, ");

		    sql.append(" pma.color, ");		
		    sql.append(" pma.material, ");				
		    sql.append(" (select dict.valuename ");
		    sql.append(" from dict_basetype_value dict ");
		    sql.append(" where dict.valueid = pma.material) material_name, ");
		    sql.append(" pma.shape, ");						
		    sql.append(" (select dict.valuename ");													
		    sql.append(" from dict_basetype_value dict ");
		    sql.append(" where dict.valueid = pma.shape) shap_name, ");
		    sql.append(" pma.memory, ");
		    sql.append(" pma.dimensions, ");
		    sql.append(" pma.weight, ");
		    sql.append(" pma.configuration, ");
		    sql.append(" pma.ring, ");
		    sql.append(" pma.call_time, ");
		    sql.append(" pma.standby_time, pma.bluetooth, decode(pma.BLUETOOTH, 1, '蓝牙', '') bluetooth_fun, ");
		    sql.append(" pma.infrared, decode(pma.infrared, 1, '红外', '') frared_fun, pma.EXPANSION_CARD, ");
		    sql.append(" decode(pma.EXPANSION_CARD, null, '', '扩展存储') expansion_card_fun, ");

		    sql.append(" pma.camera_pixel, pma.zoom, pma.mp3, decode(pma.mp3, 1, 'mp3', '') mp3_fun, ");
		    sql.append(" pma.video, pma.video_capture, decode(pma.video_capture, 1, '视频拍摄', '') video_capture_fun, ");

		    sql.append(" pma.video_resolution, pma.os, (select dict.valuename ");
		    sql.append(" from dict_basetype_value dict where dict.valueid = pma.os) os_name, ");

		    sql.append(" pma.gps, decode(pma.gps, null || 0, '', 'gps') gps_fun, pma.media, pma.screen_size, ");
		    sql.append(" pma.screen_parameter, pma.email, decode(pma.email, 1, 'email', '') email_fun, ");
		    sql.append(" pma.audio, decode(pma.audio, 1, '录音', '') audio_fun, pma.wap, ");
		    sql.append(" decode(pma.wap, 1, 'wap', '') wap_fun, pma.market_time, ");
		    sql.append(" f_get_product_pic(t.productid, 1) photo_name from product_list t ");
		    sql.append(" join product_mobile_attr pma on t.productid = pma.productid ");

		    sql.append(" where t.up_time<sysdate ");
			sql.append(" and t.down_time>sysdate ");
			sql.append(" and t.ifindex = 0 ");
			sql.append(" and t.ifdelete = 1 ");
			sql.append(" and t.productclass = 1 ");
			
			IDAO dao = this.getDao();

			List data = dao.getCountQuery(sql.toString(), endNum, beginNum);

			if (data != null && data.size() > 0) {
				for (Object obj : data) {
					Map entry = (Map) obj;
					if (entry != null) {
						Document doc = buildPhoneIndexDocument(entry);
						if (doc != null) {
							listData.add(doc);
						}
					}
				}
			}
		}
		return listData;
	}

	/**
	 * 获得指定的手机信息
	 * 
	 * @return
	 */
	public Document getAPhoneData(int phoneid) {
		if (phoneid > 0) {
			StringBuilder sql = new StringBuilder("");
//			sql.append("select productid, ");
//			sql.append("productno, ");
//			sql.append("productname, ");
//			sql.append("promotion, ");
//			sql.append("productclass, ");
//			sql.append("brand, ");
//			sql.append("brand_name, ");
//			sql.append("price_net, ");
//			sql.append("price_market, ");
//			sql.append("price_set, ");
//			sql.append("score_add, ");
//			sql.append("score_use, ");
//			sql.append("ifset, ");
//			sql.append("ifuse, ");
//			sql.append("ifvalid, ");
//			sql.append("ifentity, ");
//			sql.append("ifscore, ");
//			sql.append("up_time, ");
//			sql.append("down_time, ");
//			sql.append("keywords, ");
//			sql.append("memo, ");
//			sql.append("describe, ");
//			sql.append("create_dt, ");
//			sql.append("update_type, ");
//			sql.append("ifindex, ");
//			sql.append("price_standby, ");
//			sql.append("valid_time, ");
//			sql.append("ifdelete, ");
//			sql.append("productmodel, ");
//			sql.append("sale_count, ");
//			sql.append("ifspecial, ");
//			sql.append("ifnew, ");
//			sql.append("ifdiscount, ");
//			sql.append("ifbestsell, ");
//			sql.append("ifhandwrite, ");
//			sql.append("handwrite_fun, ");
//			sql.append("ifthin, ");
//			sql.append("thin, ");
//			sql.append("iffemale, ");
//			sql.append("femail_fun, ");
//			sql.append("main_function, ");
//			sql.append("additional, ");
//			sql.append("net_standard, ");
//			sql.append("net_name, ");
//			sql.append("color, ");
//			sql.append("material, ");
//			sql.append("material_name, ");
//			sql.append("shape, ");
//			sql.append("shap_name, ");
//			sql.append("memory, ");
//			sql.append("dimensions, ");
//			sql.append("weight, ");
//			sql.append("configuration, ");
//			sql.append("ring, ");
//			sql.append("call_time, ");
//			sql.append("standby_time, ");
//			sql.append("bluetooth, ");
//			sql.append("bluetooth_fun, ");
//			sql.append("infrared, ");
//			sql.append("frared_fun, ");
//			sql.append("expansion_card, ");
//			sql.append("expansion_card_fun, ");
//			sql.append("expansion_card_name, ");
//			sql.append("camera_pixel, ");
//			sql.append("zoom, ");
//			sql.append("mp3, ");
//			sql.append("mp3_fun, ");
//			sql.append("video, ");
//			sql.append("video_capture, ");
//			sql.append("video_capture_fun, ");
//			sql.append("video_resolution, ");
//			sql.append("os, ");
//			sql.append("os_name, ");
//			sql.append("gps, ");
//			sql.append("gps_fun, ");
//			sql.append("media, ");
//			sql.append("screen_size, ");
//			sql.append("screen_parameter, ");
//			sql.append("email, ");
//			sql.append("email_fun, ");
//			sql.append("audio, ");
//			sql.append("audio_fun, ");
//			sql.append("wap, ");
//			sql.append("wap_fun, ");
//			sql.append("market_time, ");
//			sql.append("photo_name ");
//			sql.append(" from v_mobile_index t ");
//			sql.append(" where t.up_time<sysdate ");
//			sql.append(" and t.down_time>sysdate ");
//
//			sql.append(" and t.ifuse=1 and t.productid = ? ");
			
			
			sql.append(" select t.productid, ");
			sql.append(" t.productno, ");
			sql.append(" t.productname, ");
		    sql.append(" t.promotion, ");
		    sql.append(" t.productclass, ");
		    sql.append(" t.brand,");
		    sql.append(" (select dict.valuename ");
		    sql.append(" from dict_basetype_value dict ");
		    sql.append(" where dict.valueid = t.brand) brand_name, ");
		    sql.append(" t.price_net, ");
		    sql.append(" t.price_market, ");
		    sql.append(" t.price_set, ");
		    sql.append(" t.score_add, ");
		    sql.append(" t.score_use, ");
		    sql.append(" t.ifset, ");
		    sql.append(" t.ifuse, ");
		    sql.append(" t.ifvalid, ");
		    sql.append(" t.ifentity, ");
		    sql.append(" t.ifscore, ");
		    sql.append(" t.up_time, ");
		    sql.append(" t.down_time, ");
		    sql.append(" t.keywords, ");
		    sql.append(" t.memo, ");
		    sql.append(" t.describe, ");
		    sql.append(" t.create_dt, ");
		    sql.append(" t.update_type, ");
		    sql.append(" t.ifindex, ");
		    sql.append(" t.price_standby, ");
		    sql.append(" t.valid_time, ");
		    sql.append(" t.ifdelete, ");
		    sql.append(" t.productmodel, ");
		    sql.append(" t.sale_count, ");
		    sql.append(" pma.ifspecial,");
		    sql.append(" pma.ifnew, ");
		    sql.append(" pma.ifdiscount, ");
		    sql.append(" pma.ifbestsell, ");
		    sql.append(" pma.ifhandwrite, ");
		    sql.append(" decode(pma.ifhandwrite, 1, '手写', '') handwrite_fun, ");
		    sql.append(" pma.ifthin,");
		    sql.append(" decode(pma.ifthin, 1, '超薄', '') thin, ");
		    sql.append(" pma.iffemale, ");
		    sql.append(" decode(pma.iffemale, 1, '女士', '') femail_fun, ");
		    sql.append(" pma.main_function, ");
		    sql.append(" pma.additional, ");
		    sql.append(" pma.net_standard, ");
		    sql.append(" (select dict.valuename ");
		    sql.append(" from dict_basetype_value dict ");
		    sql.append(" where dict.valueid = pma.net_standard) net_name, ");

		    sql.append(" pma.color, ");		
		    sql.append(" pma.material, ");				
		    sql.append(" (select dict.valuename ");
		    sql.append(" from dict_basetype_value dict ");
		    sql.append(" where dict.valueid = pma.material) material_name, ");
		    sql.append(" pma.shape, ");						
		    sql.append(" (select dict.valuename ");													
		    sql.append(" from dict_basetype_value dict ");
		    sql.append(" where dict.valueid = pma.shape) shap_name, ");
		    sql.append(" pma.memory, ");
		    sql.append(" pma.dimensions, ");
		    sql.append(" pma.weight, ");
		    sql.append(" pma.configuration, ");
		    sql.append(" pma.ring, ");
		    sql.append(" pma.call_time, ");
		    sql.append(" pma.standby_time, pma.bluetooth, decode(pma.BLUETOOTH, 1, '蓝牙', '') bluetooth_fun, ");
		    sql.append(" pma.infrared, decode(pma.infrared, 1, '红外', '') frared_fun, pma.EXPANSION_CARD, ");
		    sql.append(" decode(pma.EXPANSION_CARD, null, '', '扩展存储') expansion_card_fun, ");

		    sql.append(" pma.camera_pixel, pma.zoom, pma.mp3, decode(pma.mp3, 1, 'mp3', '') mp3_fun, ");
		    sql.append(" pma.video, pma.video_capture, decode(pma.video_capture, 1, '视频拍摄', '') video_capture_fun, ");

		    sql.append(" pma.video_resolution, pma.os, (select dict.valuename ");
		    sql.append(" from dict_basetype_value dict where dict.valueid = pma.os) os_name, ");

		    sql.append(" pma.gps, decode(pma.gps, null || 0, '', 'gps') gps_fun, pma.media, pma.screen_size, ");
		    sql.append(" pma.screen_parameter, pma.email, decode(pma.email, 1, 'email', '') email_fun, ");
		    sql.append(" pma.audio, decode(pma.audio, 1, '录音', '') audio_fun, pma.wap, ");
		    sql.append(" decode(pma.wap, 1, 'wap', '') wap_fun, pma.market_time, ");
		    sql.append(" f_get_product_pic(t.productid, 1) photo_name from product_list t ");
		    sql.append(" join product_mobile_attr pma on t.productid = pma.productid ");

		    sql.append(" where t.up_time<sysdate ");
			sql.append(" and t.down_time>sysdate ");
			sql.append(" and t.ifvalid = 1          ");
			sql.append(" and t.productclass = 1 ");
			sql.append(" and t.ifuse=1 and ifdelete=0 and t.productid = ? ");

			IDAO dao = this.getDao();
			Object[] args = { phoneid };
			int[] argTypes = { java.sql.Types.INTEGER };

			List data = dao.getQuery(sql.toString(), args, argTypes);

			if (data != null && data.size() > 0) {
				Object obj = data.get(0);
				Map entry = (Map) obj;
				if (entry != null) {
					Document doc = buildPhoneIndexDocument(entry);
					return doc;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 获得上架销售的手机信息
	 * @param beginNum
	 * @param endNum
	 * @return
	 */
	public List<Document> getAllPhoneDatas(int beginNum, int endNum) {
		List<Document> listData = new ArrayList<Document>();
		if (endNum >= beginNum && endNum > 0) {
			StringBuilder sql = new StringBuilder("");
//			sql.append("select productid, ");
//			sql.append("productno, ");
//			sql.append("productname, ");
//			sql.append("promotion, ");
//			sql.append("productclass, ");
//			sql.append("brand, ");
//			sql.append("brand_name, ");
//			sql.append("price_net, ");
//			sql.append("price_market, ");
//			sql.append("price_set, ");
//			sql.append("score_add, ");
//			sql.append("score_use, ");
//			sql.append("ifset, ");
//			sql.append("ifuse, ");
//			sql.append("ifvalid, ");
//			sql.append("ifentity, ");
//			sql.append("ifscore, ");
//			sql.append("up_time, ");
//			sql.append("down_time, ");
//			sql.append("keywords, ");
//			sql.append("memo, ");
//			sql.append("describe, ");
//			sql.append("create_dt, ");
//			sql.append("update_type, ");
//			sql.append("ifindex, ");
//			sql.append("price_standby, ");
//			sql.append("valid_time, ");
//			sql.append("ifdelete, ");
//			sql.append("productmodel, ");
//			sql.append("sale_count, ");
//			sql.append("ifspecial, ");
//			sql.append("ifnew, ");
//			sql.append("ifdiscount, ");
//			sql.append("ifbestsell, ");
//			sql.append("ifhandwrite, ");
//			sql.append("handwrite_fun, ");
//			sql.append("ifthin, ");
//			sql.append("thin, ");
//			sql.append("iffemale, ");
//			sql.append("femail_fun, ");
//			sql.append("main_function, ");
//			sql.append("additional, ");
//			sql.append("net_standard, ");
//			sql.append("net_name, ");
//			sql.append("color, ");
//			sql.append("material, ");
//			sql.append("material_name, ");
//			sql.append("shape, ");
//			sql.append("shap_name, ");
//			sql.append("memory, ");
//			sql.append("dimensions, ");
//			sql.append("weight, ");
//			sql.append("configuration, ");
//			sql.append("ring, ");
//			sql.append("call_time, ");
//			sql.append("standby_time, ");
//			sql.append("bluetooth, ");
//			sql.append("bluetooth_fun, ");
//			sql.append("infrared, ");
//			sql.append("frared_fun, ");
//			sql.append("expansion_card, ");
//			sql.append("expansion_card_fun, ");
//			sql.append("expansion_card_name, ");
//			sql.append("camera_pixel, ");
//			sql.append("zoom, ");
//			sql.append("mp3, ");
//			sql.append("mp3_fun, ");
//			sql.append("video, ");
//			sql.append("video_capture, ");
//			sql.append("video_capture_fun, ");
//			sql.append("video_resolution, ");
//			sql.append("os, ");
//			sql.append("os_name, ");
//			sql.append("gps, ");
//			sql.append("gps_fun, ");
//			sql.append("media, ");
//			sql.append("screen_size, ");
//			sql.append("screen_parameter, ");
//			sql.append("email, ");
//			sql.append("email_fun, ");
//			sql.append("audio, ");
//			sql.append("audio_fun, ");
//			sql.append("wap, ");
//			sql.append("wap_fun, ");
//			sql.append("market_time, ");
//			sql.append("photo_name ");
//			sql.append(" from v_mobile_index t ");
//			sql.append(" where t.up_time<sysdate ");
//			sql.append(" and t.down_time>sysdate ");
//
//			sql.append(" and t.ifuse=1 ");
//			sql.append(" and t.ifdelete = 0 ");
			
			
			sql.append(" select t.productid, ");
			sql.append(" t.productno, ");
			sql.append(" t.productname, ");
		    sql.append(" t.promotion, ");
		    sql.append(" t.productclass, ");
		    sql.append(" t.brand,");
		    sql.append(" (select dict.valuename ");
		    sql.append(" from dict_basetype_value dict ");
		    sql.append(" where dict.valueid = t.brand) brand_name, ");
		    sql.append(" t.price_net, ");
		    sql.append(" t.price_market, ");
		    sql.append(" t.price_set, ");
		    sql.append(" t.score_add, ");
		    sql.append(" t.score_use, ");
		    sql.append(" t.ifset, ");
		    sql.append(" t.ifuse, ");
		    sql.append(" t.ifvalid, ");
		    sql.append(" t.ifentity, ");
		    sql.append(" t.ifscore, ");
		    sql.append(" t.up_time, ");
		    sql.append(" t.down_time, ");
		    sql.append(" t.keywords, ");
		    sql.append(" t.memo, ");
		    sql.append(" t.describe, ");
		    sql.append(" t.create_dt, ");
		    sql.append(" t.update_type, ");
		    sql.append(" t.ifindex, ");
		    sql.append(" t.price_standby, ");
		    sql.append(" t.valid_time, ");
		    sql.append(" t.ifdelete, ");
		    sql.append(" t.productmodel, ");
		    sql.append(" t.sale_count, ");
		    sql.append(" pma.ifspecial,");
		    sql.append(" pma.ifnew, ");
		    sql.append(" pma.ifdiscount, ");
		    sql.append(" pma.ifbestsell, ");
		    sql.append(" pma.ifhandwrite, ");
		    sql.append(" decode(pma.ifhandwrite, 1, '手写', '') handwrite_fun, ");
		    sql.append(" pma.ifthin,");
		    sql.append(" decode(pma.ifthin, 1, '超薄', '') thin, ");
		    sql.append(" pma.iffemale, ");
		    sql.append(" decode(pma.iffemale, 1, '女士', '') femail_fun, ");
		    sql.append(" pma.main_function, ");
		    sql.append(" pma.additional, ");
		    sql.append(" pma.net_standard, ");
		    sql.append(" (select dict.valuename ");
		    sql.append(" from dict_basetype_value dict ");
		    sql.append(" where dict.valueid = pma.net_standard) net_name, ");

		    sql.append(" pma.color, ");		
		    sql.append(" pma.material, ");				
		    sql.append(" (select dict.valuename ");
		    sql.append(" from dict_basetype_value dict ");
		    sql.append(" where dict.valueid = pma.material) material_name, ");
		    sql.append(" pma.shape, ");						
		    sql.append(" (select dict.valuename ");													
		    sql.append(" from dict_basetype_value dict ");
		    sql.append(" where dict.valueid = pma.shape) shap_name, ");
		    sql.append(" pma.memory, ");
		    sql.append(" pma.dimensions, ");
		    sql.append(" pma.weight, ");
		    sql.append(" pma.configuration, ");
		    sql.append(" pma.ring, ");
		    sql.append(" pma.call_time, ");
		    sql.append(" pma.standby_time, pma.bluetooth, decode(pma.BLUETOOTH, 1, '蓝牙', '') bluetooth_fun, ");
		    sql.append(" pma.infrared, decode(pma.infrared, 1, '红外', '') frared_fun, pma.EXPANSION_CARD, ");
		    sql.append(" decode(pma.EXPANSION_CARD, null, '', '扩展存储') expansion_card_fun, ");

		    sql.append(" pma.camera_pixel, pma.zoom, pma.mp3, decode(pma.mp3, 1, 'mp3', '') mp3_fun, ");
		    sql.append(" pma.video, pma.video_capture, decode(pma.video_capture, 1, '视频拍摄', '') video_capture_fun, ");

		    sql.append(" pma.video_resolution, pma.os, (select dict.valuename ");
		    sql.append(" from dict_basetype_value dict where dict.valueid = pma.os) os_name, ");

		    sql.append(" pma.gps, decode(pma.gps, null || 0, '', 'gps') gps_fun, pma.media, pma.screen_size, ");
		    sql.append(" pma.screen_parameter, pma.email, decode(pma.email, 1, 'email', '') email_fun, ");
		    sql.append(" pma.audio, decode(pma.audio, 1, '录音', '') audio_fun, pma.wap, ");
		    sql.append(" decode(pma.wap, 1, 'wap', '') wap_fun, pma.market_time, ");
		    sql.append(" f_get_product_pic(t.productid, 1) photo_name from product_list t ");
		    sql.append(" join product_mobile_attr pma on t.productid = pma.productid ");

		    sql.append(" where t.up_time<sysdate ");
			sql.append(" and t.down_time>sysdate ");

			sql.append(" and t.ifuse=1 ");
			sql.append(" and t.ifdelete = 0 ");
			sql.append(" and t.productclass = 1 ");
		    
		    
			IDAO dao = this.getDao();

			List data = dao.getCountQuery(sql.toString(), endNum, beginNum);

			if (data != null && data.size() > 0) {
				for (Object obj : data) {
					Map entry = (Map) obj;
					if (entry != null) {
						Document doc = buildPhoneIndexDocument(entry);
						if (doc != null) {
							listData.add(doc);
						}
					}
				}
			}
		}
		return listData;
	}

	/**
	 * 获得上架销售未被索引的手机信息
	 * 
	 * @param beginNum
	 * @param endNum
	 * @return
	 */
	public List<Document> getUnindexedPhoneDatas(int beginNum, int endNum) {
		List<Document> listData = new ArrayList<Document>();
		if (endNum >= beginNum && endNum > 0) {
			StringBuilder sql = new StringBuilder("");
//			sql.append("select productid, ");
//			sql.append("productno, ");
//			sql.append("productname, ");
//			sql.append("promotion, ");
//			sql.append("productclass, ");
//			sql.append("brand, ");
//			sql.append("brand_name, ");
//			sql.append("price_net, ");
//			sql.append("price_market, ");
//			sql.append("price_set, ");
//			sql.append("score_add, ");
//			sql.append("score_use, ");
//			sql.append("ifset, ");
//			sql.append("ifuse, ");
//			sql.append("ifvalid, ");
//			sql.append("ifentity, ");
//			sql.append("ifscore, ");
//			sql.append("up_time, ");
//			sql.append("down_time, ");
//			sql.append("keywords, ");
//			sql.append("memo, ");
//			sql.append("describe, ");
//			sql.append("create_dt, ");
//			sql.append("update_type, ");
//			sql.append("ifindex, ");
//			sql.append("price_standby, ");
//			sql.append("valid_time, ");
//			sql.append("ifdelete, ");
//			sql.append("productmodel, ");
//			sql.append("sale_count, ");
//			sql.append("ifspecial, ");
//			sql.append("ifnew, ");
//			sql.append("ifdiscount, ");
//			sql.append("ifbestsell, ");
//			sql.append("ifhandwrite, ");
//			sql.append("handwrite_fun, ");
//			sql.append("ifthin, ");
//			sql.append("thin, ");
//			sql.append("iffemale, ");
//			sql.append("femail_fun, ");
//			sql.append("main_function, ");
//			sql.append("additional, ");
//			sql.append("net_standard, ");
//			sql.append("net_name, ");
//			sql.append("color, ");
//			sql.append("material, ");
//			sql.append("material_name, ");
//			sql.append("shape, ");
//			sql.append("shap_name, ");
//			sql.append("memory, ");
//			sql.append("dimensions, ");
//			sql.append("weight, ");
//			sql.append("configuration, ");
//			sql.append("ring, ");
//			sql.append("call_time, ");
//			sql.append("standby_time, ");
//			sql.append("bluetooth, ");
//			sql.append("bluetooth_fun, ");
//			sql.append("infrared, ");
//			sql.append("frared_fun, ");
//			sql.append("expansion_card, ");
//			sql.append("expansion_card_fun, ");
//			sql.append("expansion_card_name, ");
//			sql.append("camera_pixel, ");
//			sql.append("zoom, ");
//			sql.append("mp3, ");
//			sql.append("mp3_fun, ");
//			sql.append("video, ");
//			sql.append("video_capture, ");
//			sql.append("video_capture_fun, ");
//			sql.append("video_resolution, ");
//			sql.append("os, ");
//			sql.append("os_name, ");
//			sql.append("gps, ");
//			sql.append("gps_fun, ");
//			sql.append("media, ");
//			sql.append("screen_size, ");
//			sql.append("screen_parameter, ");
//			sql.append("email, ");
//			sql.append("email_fun, ");
//			sql.append("audio, ");
//			sql.append("audio_fun, ");
//			sql.append("wap, ");
//			sql.append("wap_fun, ");
//			sql.append("market_time, ");
//			sql.append("photo_name ");
//			sql.append(" from v_mobile_index t ");
//			sql.append(" where t.up_time<sysdate ");
//			sql.append(" and t.down_time>sysdate ");
//
//			sql.append(" and t.ifuse=1 and t.ifdelete = 0 and  t.ifindex = 0");
			
			
			sql.append(" select t.productid, ");
			sql.append(" t.productno, ");
			sql.append(" t.productname, ");
		    sql.append(" t.promotion, ");
		    sql.append(" t.productclass, ");
		    sql.append(" t.brand,");
		    sql.append(" (select dict.valuename ");
		    sql.append(" from dict_basetype_value dict ");
		    sql.append(" where dict.valueid = t.brand) brand_name, ");
		    sql.append(" t.price_net, ");
		    sql.append(" t.price_market, ");
		    sql.append(" t.price_set, ");
		    sql.append(" t.score_add, ");
		    sql.append(" t.score_use, ");
		    sql.append(" t.ifset, ");
		    sql.append(" t.ifuse, ");
		    sql.append(" t.ifvalid, ");
		    sql.append(" t.ifentity, ");
		    sql.append(" t.ifscore, ");
		    sql.append(" t.up_time, ");
		    sql.append(" t.down_time, ");
		    sql.append(" t.keywords, ");
		    sql.append(" t.memo, ");
		    sql.append(" t.describe, ");
		    sql.append(" t.create_dt, ");
		    sql.append(" t.update_type, ");
		    sql.append(" t.ifindex, ");
		    sql.append(" t.price_standby, ");
		    sql.append(" t.valid_time, ");
		    sql.append(" t.ifdelete, ");
		    sql.append(" t.productmodel, ");
		    sql.append(" t.sale_count, ");
		    sql.append(" pma.ifspecial,");
		    sql.append(" pma.ifnew, ");
		    sql.append(" pma.ifdiscount, ");
		    sql.append(" pma.ifbestsell, ");
		    sql.append(" pma.ifhandwrite, ");
		    sql.append(" decode(pma.ifhandwrite, 1, '手写', '') handwrite_fun, ");
		    sql.append(" pma.ifthin,");
		    sql.append(" decode(pma.ifthin, 1, '超薄', '') thin, ");
		    sql.append(" pma.iffemale, ");
		    sql.append(" decode(pma.iffemale, 1, '女士', '') femail_fun, ");
		    sql.append(" pma.main_function, ");
		    sql.append(" pma.additional, ");
		    sql.append(" pma.net_standard, ");
		    sql.append(" (select dict.valuename ");
		    sql.append(" from dict_basetype_value dict ");
		    sql.append(" where dict.valueid = pma.net_standard) net_name, ");

		    sql.append(" pma.color, ");		
		    sql.append(" pma.material, ");				
		    sql.append(" (select dict.valuename ");
		    sql.append(" from dict_basetype_value dict ");
		    sql.append(" where dict.valueid = pma.material) material_name, ");
		    sql.append(" pma.shape, ");						
		    sql.append(" (select dict.valuename ");													
		    sql.append(" from dict_basetype_value dict ");
		    sql.append(" where dict.valueid = pma.shape) shap_name, ");
		    sql.append(" pma.memory, ");
		    sql.append(" pma.dimensions, ");
		    sql.append(" pma.weight, ");
		    sql.append(" pma.configuration, ");
		    sql.append(" pma.ring, ");
		    sql.append(" pma.call_time, ");
		    sql.append(" pma.standby_time, pma.bluetooth, decode(pma.BLUETOOTH, 1, '蓝牙', '') bluetooth_fun, ");
		    sql.append(" pma.infrared, decode(pma.infrared, 1, '红外', '') frared_fun, pma.EXPANSION_CARD, ");
		    sql.append(" decode(pma.EXPANSION_CARD, null, '', '扩展存储') expansion_card_fun, ");

		    sql.append(" pma.camera_pixel, pma.zoom, pma.mp3, decode(pma.mp3, 1, 'mp3', '') mp3_fun, ");
		    sql.append(" pma.video, pma.video_capture, decode(pma.video_capture, 1, '视频拍摄', '') video_capture_fun, ");

		    sql.append(" pma.video_resolution, pma.os, (select dict.valuename ");
		    sql.append(" from dict_basetype_value dict where dict.valueid = pma.os) os_name, ");

		    sql.append(" pma.gps, decode(pma.gps, null || 0, '', 'gps') gps_fun, pma.media, pma.screen_size, ");
		    sql.append(" pma.screen_parameter, pma.email, decode(pma.email, 1, 'email', '') email_fun, ");
		    sql.append(" pma.audio, decode(pma.audio, 1, '录音', '') audio_fun, pma.wap, ");
		    sql.append(" decode(pma.wap, 1, 'wap', '') wap_fun, pma.market_time, ");
		    sql.append(" f_get_product_pic(t.productid, 1) photo_name from product_list t ");
		    sql.append(" join product_mobile_attr pma on t.productid = pma.productid ");
			
		    sql.append(" where t.up_time<sysdate ");
			sql.append(" and t.down_time>sysdate ");
			sql.append(" and t.ifuse=1 and t.ifdelete = 0 and  t.ifindex = 0");
			sql.append(" and t.productclass = 1 ");

			IDAO dao = this.getDao();

			List data = dao.getCountQuery(sql.toString(), endNum, beginNum);

			if (data != null && data.size() > 0) {
				for (Object obj : data) {
					Map entry = (Map) obj;
					if (entry != null) {
						Document doc = buildPhoneIndexDocument(entry);
						if (doc != null) {
							listData.add(doc);
						}
					}
				}
			}
		}
		return listData;
	}

	/**
	 * 构建一个phone document
	 * 
	 * @param entry
	 * @return
	 */
	private Document buildPhoneIndexDocument(Map entry) {
		if (entry != null) {

			String productid = StringUtil
			.escapeNullObject(RowSetTypeConverter.getLong(
					entry.get("productid")).toString());
			String productname = StringUtil
			.escapeNullObject(RowSetTypeConverter.getString(entry
					.get("productname")));
			
			String describe = StringUtil.html2Text(StringUtil
			.escapeNullObject(RowSetTypeConverter.getString(entry
					.get("describe"))));
			
			String promotion = StringUtil
			.escapeNullObject(RowSetTypeConverter.getString(entry
					.get("promotion")));
			
			Double price_market = RowSetTypeConverter.getDouble(entry
					.get("price_market"));
			Double price_net = RowSetTypeConverter.getDouble(entry
					.get("price_net"));

			Integer sale_count = RowSetTypeConverter.getInteger(entry
					.get("sale_count"));
			
			Date up_time = RowSetTypeConverter.getDate(entry.get("up_time"));
			Date down_time = RowSetTypeConverter.getDate(entry.get("down_time"));
			
			String photo_name = StringUtil
			.escapeNullObject(RowSetTypeConverter.getString(entry
					.get("photo_name")));
			String brand_name = StringUtil
			.escapeNullObject(RowSetTypeConverter.getString(entry
					.get("brand_name"))).toLowerCase();
			
			
			String net_name = StringUtil
			.escapeNullObject(RowSetTypeConverter.getString(entry
					.get("net_name")));
			String os_name = StringUtil
			.escapeNullObject(RowSetTypeConverter
					.getString(entry.get("os_name")));

			String shap_name = StringUtil
			.escapeNullObject(RowSetTypeConverter.getString(entry
					.get("shap_name")));

			Double screen_size = RowSetTypeConverter.getDouble(entry
					.get("screen_size"));
			if(screen_size == null){
				screen_size = 0.0;
			}
			
			Integer camera_pixel = RowSetTypeConverter.getInteger(entry
					.get("camera_pixel"));
			
			if(camera_pixel == null){
				camera_pixel = 0;
			}
			
			String market_time = StringUtil
			.escapeNullObject(RowSetTypeConverter.getString(entry
					.get("market_time")));

			StringBuffer phoneFun = new StringBuffer("");

			String handwrite_fun = StringUtil
					.escapeNullObject(RowSetTypeConverter.getString(entry
							.get("handwrite_fun")));
			String thin = StringUtil.escapeNullObject(RowSetTypeConverter
					.getString(entry.get("thin")));
			String femail_fun = StringUtil.escapeNullObject(RowSetTypeConverter
					.getString(entry.get("femail_fun")));
			String bluetooth_fun = StringUtil
					.escapeNullObject(RowSetTypeConverter.getString(entry
							.get("bluetooth_fun")));
			String frared_fun = StringUtil.escapeNullObject(RowSetTypeConverter
					.getString(entry.get("frared_fun")));
			String expansion_card_fun = StringUtil
					.escapeNullObject(RowSetTypeConverter.getString(entry
							.get("expansion_card_fun")));
			String mp3_fun = StringUtil.escapeNullObject(RowSetTypeConverter
					.getString(entry.get("mp3_fun")));
			//视频播放
			String video = StringUtil
			        .escapeNullObject(RowSetTypeConverter.getString(entry
					        .get("video")));
			if( video.length() > 0 && !video.contains("不支持") ) {
				video = "视频拍摄";
			}
			String video_capture_fun = StringUtil
					.escapeNullObject(RowSetTypeConverter.getString(entry
							.get("video_capture_fun")));
			String gps_fun = StringUtil.escapeNullObject(RowSetTypeConverter
					.getString(entry.get("gps_fun")));
			String email_fun = StringUtil.escapeNullObject(RowSetTypeConverter
					.getString(entry.get("email_fun")));
			String audio_fun = StringUtil.escapeNullObject(RowSetTypeConverter
					.getString(entry.get("audio_fun")));
			String wap_fun = StringUtil.escapeNullObject(RowSetTypeConverter
					.getString(entry.get("wap_fun")));
			
			String productModel = StringUtil.escapeNullObject(RowSetTypeConverter
					.getString(entry.get("productmodel")));
			
			String keywords = StringUtil.escapeNullObject(RowSetTypeConverter
					.getString(entry.get("keywords")));
			
			String ring = StringUtil.escapeNullObject(RowSetTypeConverter
					.getString(entry.get("ring")));
			
			if( ring.contains("和弦铃声") ) {
				ring = ring.replaceAll("和弦铃声", "和弦");
			}

			phoneFun.append(handwrite_fun);
			phoneFun.append(" ");
			phoneFun.append(thin);
			phoneFun.append(" ");
			phoneFun.append(femail_fun);
			phoneFun.append(" ");
			phoneFun.append(bluetooth_fun);
			phoneFun.append(" ");
			phoneFun.append(frared_fun);
			phoneFun.append(" ");
			if(StringUtils.isNotEmpty(expansion_card_fun) && !StringUtils.equals(expansion_card_fun.trim(), "不支持扩展")) {
				phoneFun.append(expansion_card_fun);
			}
			phoneFun.append(" ");
			phoneFun.append(mp3_fun);
			phoneFun.append(" ");
			phoneFun.append(video);
			phoneFun.append(" ");
			phoneFun.append(video_capture_fun);
			phoneFun.append(" ");
			phoneFun.append(gps_fun);
			phoneFun.append(" ");
			phoneFun.append(email_fun);
			phoneFun.append(" ");
			phoneFun.append(audio_fun);
			phoneFun.append(" ");
			phoneFun.append(wap_fun);
			phoneFun.append(" ");
			if(StringUtils.isNotEmpty(os_name) && price_net.intValue() >= 1500 && !StringUtils.equals(os_name.trim(), "无操作系统")){
				phoneFun.append("智能系统");
			}
			phoneFun.append(" ");
			phoneFun.append(ring);
			
			String color = StringUtil.escapeNullObject(RowSetTypeConverter
					.getString(entry.get("color"))).trim();
			if( !color.equals("白色") && !color.equals("粉色") && !color.equals("红色") && !color.equals("蓝色") 
					&& !color.equals("金色") && !color.equals("银色") && !color.equals("黑色") && !color.equals("紫色") ) {
				color = "其它";
			}
			Integer brandv = RowSetTypeConverter.getInteger(entry.get("brand"));
			Integer ifuse = RowSetTypeConverter.getInteger(entry.get("ifuse"));
//			return PhoneDocument.getDocument(productid, productname, describe,
//					promotion, price_market.toString(), price_net, sale_count
//							.toString(), up_time, down_time, photo_name, brand_name,
//					net_name, os_name, shap_name, screen_size, camera_pixel,
//					market_time, phoneFun.toString().trim(), productModel, keywords, color,brandv.intValue(), ifuse);
			return null;
		} else {
			return null;
		}
	}
	
	/**
	 * 更新指定手机为已被索引状态
	 * 
	 * @param phoneid
	 * @return
	 */
	public int setAPhoneIndexed(int phoneid) {
		if (phoneid > 0) {
			StringBuilder sql = new StringBuilder();
			sql
					.append(" update product_list pl set pl.ifindex = 1 where pl.productid = ? ");
			IDAO dao = this.getDao();
			Object[] args = { phoneid };
			int[] argTypes = { java.sql.Types.INTEGER };
			
			dao.executeUpdate(sql.toString(), args, argTypes);
		}
		return 0;
	}

	
	/**
	 * 更新所有手机为已被索引状态
	 * 
	 * @param phoneid
	 * @return
	 */
	public int setAllPhoneIndexed() {
		StringBuilder sql = new StringBuilder();
		sql.append(" update  product_list t set t.ifindex = 1 ");
		sql.append(" where t.ifindex = 0 and t.productclass = 1 ");
		IDAO dao = this.getDao();
		return dao.executeUpdate(sql.toString());
	}
	
	/**
	 * 更新未索引的手机为已被索引状态
	 * 
	 * @param phoneid
	 * @return
	 */
	public int setUnIndexedPhoneIndexed() {
		StringBuilder sql = new StringBuilder();
		sql.append(" update  product_list t set t.ifindex = 1 ");
		sql.append(" where t.up_time < sysdate ");
		sql.append(" and t.down_time > sysdate ");

		sql.append(" and t.ifuse = 1 and t.ifdelete = 0 and  t.ifindex = 0");
		sql.append(" and t.productclass = 1 ");
		
		IDAO dao = this.getDao();
		return dao.executeUpdate(sql.toString());
	}
	
	/**
	 * 更新已删除的手机为已被索引状态
	 * 
	 * @param phoneid
	 * @return
	 */
	public int setDeletedPhoneIndexed() {
		StringBuilder sql = new StringBuilder();
		sql
				.append(" update  product_list t set t.ifindex = 1 ");
		sql.append(" where t.up_time < sysdate ");
		sql.append(" and t.down_time > sysdate ");

		sql.append(" and t.ifuse = 1 and t.ifdelete = 1 and  t.ifindex = 0");
		sql.append(" and t.productclass = 1 ");
		
		IDAO dao = this.getDao();
		
		return dao.executeUpdate(sql.toString());
	}
	
	public static void main(String arg[]) {
		PhoneDataService peijianDataService = new PhoneDataService();
		//System.out.println( peijianDataService.reCompileView() );
		int dd = peijianDataService.getAllPhoneNums();
		
		int dddw = peijianDataService.getDeletedPhoneNums();
		
		List<Document> ddd = peijianDataService.getAllPhoneDatas(1, 10);
	}
}