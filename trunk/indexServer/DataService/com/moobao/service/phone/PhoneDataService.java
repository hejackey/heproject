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

public class PhoneDataService extends DataBaseService {

	/**
	 * 重新编译手机视图
	 * 
	 * @return
	 */
	// public boolean reCompileView() {
	// Boolean f = false;
	// try {
	// StringBuilder sql = new StringBuilder("");
	// sql.append(" alter view V_MOBILE_INDEX compile ");
	// IDAO dao = this.getDao();
	// dao.executeUpdate( sql.toString() );
	// f = true;
	// }
	// catch( Exception e ) {
	// System.out.println( "重新编译手机视图异常!" );
	// }
	// return f;
	// }

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
		sql.append(" (t.down_time < sysdate and t.comp_id = 1) or ");
		sql.append(" (t.ifuse = 0 and t.comp_id = 1) ");
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
			sql
					.append(" pma.standby_time, pma.bluetooth, decode(pma.BLUETOOTH, 1, '蓝牙', '') bluetooth_fun, ");
			sql
					.append(" pma.infrared, decode(pma.infrared, 1, '红外', '') frared_fun, pma.EXPANSION_CARD, ");
			sql
					.append(" decode(pma.EXPANSION_CARD, null, '', '0', '', '104014', '', '扩展存储') expansion_card_fun, ");

			sql
					.append(" pma.camera_pixel, pma.zoom, pma.mp3, decode(pma.mp3, 1, 'mp3', '') mp3_fun, ");
			sql
					.append(" pma.video, pma.video_capture, decode(pma.video_capture, 1, '视频拍摄', '') video_capture_fun, ");

			sql
					.append(" pma.video_resolution, pma.os, (select dict.valuename ");
			sql
					.append(" from dict_basetype_value dict where dict.valueid = pma.os) os_name, ");

			sql
					.append(" pma.gps, decode(pma.gps, null, '', 0, '', 1, '', 2, '', 122101, 'gaps', 'gps') gps_fun, pma.media, pma.screen_size, ");
			sql
					.append(" pma.screen_parameter, pma.email, decode(pma.email, 1, 'email', '') email_fun, ");
			sql
					.append(" pma.audio, decode(pma.audio, 1, '录音', '') audio_fun, pma.wap, ");
			sql
					.append(" decode(pma.wap, 1, 'wap', '') wap_fun, pma.market_time, ");
			sql
					.append(" f_get_product_pic(t.productid, 102) photo_name from product_list t ");
			sql
					.append(" join product_mobile_attr pma on t.productid = pma.productid ");

			sql
					.append(" where t.productclass = 102 and t.ifuse = 0  and t.comp_id = 1 ");
			sql
					.append(" or t.productclass = 102 and t.down_time < sysdate and t.comp_id = 1 ");
			sql
					.append(" or t.productclass = 102 and t.ifvalid = 0 and t.comp_id = 1 ");

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
		sql.append(" from product_master pmaster ");
		sql.append(" join (select m.* from( select t.*,row_number() over(partition by t.pri_productid order by t.ifgoogle desc) as rn from product_list t ) m where m.rn=1) n on pmaster.pri_productid = n.pri_productid ");
		sql.append(" where pmaster.classcode  like '102%' ");
		sql.append(" and  n.ifvalid = 1 and n.ifgift = 0 ");
		sql.append(" and  pmaster.ifdelete = 0  ");
		sql.append(" and pmaster.comp_id = 1 ");
		IDAO dao = this.getDao();
		return dao.getQueryInt(sql.toString());
	}

	/**
	 * 获得没有被索引的手机数量
	 * 
	 * @return
	 */
	public int getUnIndexedPhoneNums() {
		StringBuilder sql = new StringBuilder("");	
		sql.append(" select count(1) ");                                                                        
		sql.append(" from product_master pmaster ");
		sql.append(" join (select m.* from( select t.*,row_number() over(partition by t.pri_productid order by t.ifgoogle desc) as rn from product_list t ) m where m.rn=1) n on pmaster.pri_productid = n.pri_productid ");
		sql.append(" where pmaster.classcode like '102%' ");
		sql.append(" and n.ifvalid = 1 and n.ifgift = 0 ");
		sql.append(" and pmaster.ifdelete = 0  ");
		sql.append(" and pmaster.ifindex = 0 ");
		sql.append(" and pmaster.comp_id = 1 ");

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
		sql.append(" from product_master pmaster ");
		sql.append(" where pmaster.classcode like '102%' ");
		sql.append(" and pmaster.ifindex = 0 ");
		sql.append(" and pmaster.ifdelete = 1  ");
		sql.append(" and pmaster.comp_id = 1 ");

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
			sql.append(" select pmaster.pri_productid,  ");
			sql.append(" pmaster.classcode, ");
			sql.append(" pmaster.productclass, ");
			sql.append(" pmaster.brand, ");
//			sql.append(" (select dict.valuename  ");
//			sql.append(" from dict_basetype_value dict ");
//			sql.append(" where dict.valueid = pmaster.brand) brand_name, ");
			
			sql.append(" (select dicts.parentid from dict_physicsclass dicts where dicts.classcode=pmaster.classcode) brand_code, ");//产品分类code
			
			sql.append("   (    ");
			sql.append(" select dict.classname          ");
			sql.append(" from dict_physicsclass dict    ");
			sql.append(" where dict.classcode in        ");
			sql.append(" (select dicts.parentid from dict_physicsclass dicts where dicts.classcode=pmaster.classcode)   ");//产品分类名称
			sql.append("   )   brand_name,              ");
			
			sql.append(" pmaster.productmodel, ");
			sql.append(" pmaster.product_para, ");
			sql.append(" pmaster.productname, ");
			sql.append(" pmaster.describe,");
			sql.append(" pmaster.promotion, ");
			sql.append(" pmaster.sale_count, ");
			sql.append(" pmaster.market_time, ");
			sql.append(" pmaster.rebate, ");
			sql.append(" pmaster.keywords, ");
			sql.append(" pmaster.net_standard, ");
			sql.append(" n.rela_color, ");
			sql.append(" pmaster.ifuse, ");
			sql.append(" pmaster.defect, ");
			sql.append(" pmaster.merit,  ");
			sql.append(" pmaster.net_standard net_name,  ");			
			sql.append(" pmaster.camera_pixel, ");
			sql.append(" pmaster.screen_size, ");
			sql.append(" pmaster.shape, ");
			sql.append(" pmaster.crowd, ");
			sql.append(" pmaster.ifdirect, ");
			sql.append(" pmaster.weightindex, ");
			sql.append(" pmaster.ifrecommend, ");
			sql.append(" pmaster.func, ");
			sql.append(" (select dict.valuename ");
			sql.append(" from dict_basetype_value dict ");
			sql.append(" where dict.valueid = pmaster.shape) shape_name, " );
			sql.append(" n.productid, ");
			sql.append(" n.price_market, ");
//			sql
//					.append(" (select t.productid from product_list t where t.ifgoogle = 1 and t.pri_productid = pmaster.pri_productid) productid, ");
//			sql
//					.append(" (select tt.price_market from product_list tt where tt.ifgoogle = 1 and tt.pri_productid = pmaster.pri_productid) price_market, ");
			sql
					.append(" (select min(t.price_net) from product_list t where pmaster.pri_productid=t.pri_productid and t.ifdelete = 0 and t.ifvalid = 1 ) minNetPrice, ");
			sql
					.append(" (select max(t.price_net) from product_list t where pmaster.pri_productid=t.pri_productid and t.ifdelete = 0 and t.ifvalid = 1 ) maxNetPrice, ");
			sql
					.append(" (select count(*) from user_message m where m.productid = pmaster.pri_productid and m.message_type = 1 and m.ifdisp = 1) recommendCount, ");
			sql
					.append(" (select count(*) from user_message m where m.productid = pmaster.pri_productid and m.message_type = 2 and m.ifdisp = 1) askCount,  ");
			sql.append(" f_get_product_pic(productid, 102) photo_name ");
			sql.append(" from product_master pmaster ");
			sql
					.append(" join (select m.* from( select t.*,row_number() over(partition by t.pri_productid order by t.ifgoogle desc) as rn from product_list t where t.ifdelete = 0 and t.ifvalid=1 ) m where m.rn=1) n on pmaster.pri_productid = n.pri_productid ");
			sql.append(" where pmaster.classcode like '102%'  ");
			//sql.append(" and plist.ifgoogle = 1 ");
			sql.append(" and pmaster.ifindex = 0 ");
			sql.append(" and pmaster.ifdelete = 1  ");
			sql.append(" and pmaster.comp_id = 1 ");

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
			sql.append(" select pmaster.pri_productid,  ");
			sql.append(" pmaster.classcode, ");
			sql.append(" pmaster.productclass, ");
			sql.append(" pmaster.brand, ");
//			sql.append(" (select dict.valuename  ");
//			sql.append(" from dict_basetype_value dict ");
//			sql.append(" where dict.valueid = pmaster.brand) brand_name, ");
			
			sql.append(" (select dicts.parentid from dict_physicsclass dicts where dicts.classcode=pmaster.classcode) brand_code, ");//产品分类code
			
			sql.append("   (    ");
			sql.append(" select dict.classname          ");
			sql.append(" from dict_physicsclass dict    ");
			sql.append(" where dict.classcode in        ");
			sql.append(" (select dicts.parentid from dict_physicsclass dicts where dicts.classcode=pmaster.classcode)   ");//产品分类名称
			sql.append("   )   brand_name,              ");
			sql.append(" pmaster.productmodel, ");
			sql.append(" pmaster.product_para, ");
			sql.append(" pmaster.productname, ");
			sql.append(" pmaster.describe,");
			sql.append(" pmaster.promotion, ");
			sql.append(" pmaster.sale_count, ");
			sql.append(" pmaster.market_time, ");
			sql.append(" pmaster.rebate, ");
			sql.append(" pmaster.keywords, ");
			sql.append(" pmaster.net_standard, ");
			sql.append(" n.rela_color, ");
			sql.append(" pmaster.ifuse, ");
			sql.append(" pmaster.defect, ");
			sql.append(" pmaster.merit,  ");
			sql.append(" pmaster.net_standard net_name,  ");
//			sql.append(" (select dict.valuename ");
//			sql.append(" from dict_basetype_value dict ");
//			sql
//					.append(" where dict.valueid = pmaster.net_standard) net_name, ");
			sql.append(" pmaster.camera_pixel, ");
			sql.append(" pmaster.screen_size, ");
			sql.append(" pmaster.shape, ");
			sql.append(" pmaster.crowd, ");
			sql.append(" pmaster.ifdirect, ");
			sql.append(" pmaster.weightindex, ");
			sql.append(" pmaster.ifrecommend, ");
			sql.append(" pmaster.func, ");
			sql.append(" (select dict.valuename ");
			sql.append(" from dict_basetype_value dict ");
			sql.append(" where dict.valueid = pmaster.shape) shape_name,");
			sql.append(" n.productid, ");
			sql.append(" n.price_market, ");
//			sql
//					.append(" (select t.productid from product_list t where t.ifgoogle = 1 and t.pri_productid = pmaster.pri_productid) productid, ");
//			sql
//					.append(" (select tt.price_market from product_list tt where tt.ifgoogle = 1 and tt.pri_productid = pmaster.pri_productid) price_market, ");
			sql
					.append(" (select min(t.price_net) from product_list t where pmaster.pri_productid=t.pri_productid and t.ifdelete = 0 and t.ifvalid = 1 ) minNetPrice, ");
			sql
					.append(" (select max(t.price_net) from product_list t where pmaster.pri_productid=t.pri_productid and t.ifdelete = 0 and t.ifvalid = 1 ) maxNetPrice, ");
			sql
					.append(" (select count(*) from user_message m where m.productid = pmaster.pri_productid and m.message_type = 1 and m.ifdisp = 1) recommendCount, ");
			sql
					.append(" (select count(*) from user_message m where m.productid = pmaster.pri_productid and m.message_type = 2 and m.ifdisp = 1) askCount,  ");
			sql.append(" f_get_product_pic(productid, 102) photo_name ");
			sql.append(" from product_master pmaster ");
			sql
					.append(" join (select m.* from( select t.*,row_number() over(partition by t.pri_productid order by t.ifgoogle desc) as rn from product_list t where t.ifdelete = 0 and t.ifvalid=1  ) m where m.rn=1) n on pmaster.pri_productid = n.pri_productid ");
			sql.append(" where pmaster.classcode like '102%' ");
			sql.append(" and  n.ifvalid = 1  ");
			sql.append(" and n.ifgift = 0 ");
//			sql.append(" and plist.ifgoogle = 1 ");
			sql.append(" and pmaster.ifdelete = 0  ");
			sql.append(" and pmaster.pri_productid = ?  "); 
			sql.append(" and pmaster.comp_id = 1 ");

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
	 * 
	 * @param beginNum
	 * @param endNum
	 * @return
	 */
	public List<Document> getAllPhoneDatas(int beginNum, int endNum) {
		List<Document> listData = new ArrayList<Document>();
		if (endNum >= beginNum && endNum > 0) {
			StringBuilder sql = new StringBuilder("");
			sql.append(" select pmaster.pri_productid,  ");
			sql.append(" pmaster.classcode, ");
			sql.append(" pmaster.productclass, ");
			sql.append(" pmaster.brand, ");
//			sql.append(" (select dict.valuename  ");
//			sql.append(" from dict_basetype_value dict ");
//			sql.append(" where dict.valueid = pmaster.brand) brand_name, ");

			sql.append(" (select dicts.parentid from dict_physicsclass dicts where dicts.classcode=pmaster.classcode) brand_code, ");//产品分类code
			
			sql.append("   (    ");
			sql.append(" select dict.classname          ");
			sql.append(" from dict_physicsclass dict    ");
			sql.append(" where dict.classcode in        ");
			sql.append(" (select dicts.parentid from dict_physicsclass dicts where dicts.classcode=pmaster.classcode)   ");//产品分类名称
			sql.append("   )   brand_name,              ");
			
			sql.append(" pmaster.productmodel, ");
			sql.append(" pmaster.product_para, ");
			sql.append(" pmaster.productname, ");
			sql.append(" pmaster.describe,");
			sql.append(" pmaster.promotion, ");
			sql.append(" pmaster.sale_count, ");
			sql.append(" pmaster.market_time, ");
			sql.append(" pmaster.rebate, ");
			sql.append(" pmaster.keywords, ");
			sql.append(" pmaster.net_standard, ");
			sql.append(" n.rela_color, ");
			sql.append(" pmaster.ifuse, ");
			sql.append(" pmaster.defect, ");
			sql.append(" pmaster.merit,  ");
			sql.append(" pmaster.net_standard net_name,  ");
			sql.append(" pmaster.camera_pixel, ");
			sql.append(" pmaster.screen_size, ");
			sql.append(" pmaster.shape, ");
			sql.append(" pmaster.crowd, ");
			sql.append(" pmaster.ifdirect, ");
			sql.append(" pmaster.weightindex, ");
			sql.append(" pmaster.ifrecommend, ");
			sql.append(" pmaster.func, ");
			sql.append(" (select dict.valuename ");
			sql.append(" from dict_basetype_value dict ");
			sql.append(" where dict.valueid = pmaster.shape) shape_name,");
			sql.append(" n.productid, ");
			sql.append(" n.price_market, ");
//			sql
//					.append(" (select t.productid from product_list t where t.ifgoogle = 1 and t.pri_productid = pmaster.pri_productid) productid, ");
//			sql
//					.append(" (select tt.price_market from product_list tt where tt.ifgoogle = 1 and tt.pri_productid = pmaster.pri_productid) price_market, ");
			sql
					.append(" (select min(t.price_net) from product_list t where pmaster.pri_productid=t.pri_productid and t.ifdelete = 0 and t.ifvalid = 1 ) minNetPrice, ");
			sql
					.append(" (select max(t.price_net) from product_list t where pmaster.pri_productid=t.pri_productid and t.ifdelete = 0 and t.ifvalid = 1 ) maxNetPrice, ");
			sql
					.append(" (select count(*) from user_message m where m.productid = pmaster.pri_productid and m.message_type = 1 and m.ifdisp = 1) recommendCount, ");
			sql
					.append(" (select count(*) from user_message m where m.productid = pmaster.pri_productid and m.message_type = 2 and m.ifdisp = 1) askCount,  ");
			sql.append(" f_get_product_pic(productid, 102) photo_name ");
			sql.append(" from product_master pmaster ");
			sql
					.append(" join (select m.* from( select t.*,row_number() over(partition by t.pri_productid order by t.ifgoogle desc) as rn from product_list t where t.ifdelete = 0 and t.ifvalid=1 ) m where m.rn=1) n on pmaster.pri_productid = n.pri_productid ");
			sql.append(" where pmaster.classcode like '102%' ");
			sql.append(" and  n.ifvalid = 1  ");
			sql.append(" and n.ifgift = 0 ");
//			sql.append(" and plist.ifgoogle = 1 ");
			sql.append(" and pmaster.ifdelete = 0  ");
			sql.append(" and pmaster.comp_id = 1 ");

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
			sql.append(" select pmaster.pri_productid,  ");
			sql.append(" pmaster.classcode, ");
			sql.append(" pmaster.productclass, ");
			sql.append(" pmaster.brand, ");
//			sql.append(" (select dict.valuename  ");
//			sql.append(" from dict_basetype_value dict ");
//			sql.append(" where dict.valueid = pmaster.brand) brand_name, ");
			
			sql.append(" (select dicts.parentid from dict_physicsclass dicts where dicts.classcode=pmaster.classcode) brand_code, ");//产品分类code
			
			sql.append("   (    ");
			sql.append(" select dict.classname          ");
			sql.append(" from dict_physicsclass dict    ");
			sql.append(" where dict.classcode in        ");
			sql.append(" (select dicts.parentid from dict_physicsclass dicts where dicts.classcode=pmaster.classcode)   ");//产品分类名称
			sql.append("   )   brand_name,              ");
			sql.append(" pmaster.productmodel, ");
			sql.append(" pmaster.product_para, ");
			sql.append(" pmaster.productname, ");
			sql.append(" pmaster.describe,");
			sql.append(" pmaster.promotion, ");
			sql.append(" pmaster.sale_count, ");
			sql.append(" pmaster.market_time, ");
			sql.append(" pmaster.rebate, ");
			sql.append(" pmaster.keywords, ");
			sql.append(" pmaster.net_standard, ");
			sql.append(" n.rela_color, ");
			sql.append(" pmaster.ifuse, ");
			sql.append(" pmaster.defect, ");
			sql.append(" pmaster.merit,  ");
			sql.append(" pmaster.net_standard net_name,  ");
			sql.append(" pmaster.camera_pixel, ");
			sql.append(" pmaster.screen_size, ");
			sql.append(" pmaster.shape, ");
			sql.append(" pmaster.crowd, ");
			sql.append(" pmaster.ifdirect, ");
			sql.append(" pmaster.weightindex, ");
			sql.append(" pmaster.ifrecommend, ");
			sql.append(" pmaster.func, ");
			sql.append(" (select dict.valuename ");
			sql.append(" from dict_basetype_value dict ");
			sql.append(" where dict.valueid = pmaster.shape) shape_name,");
			sql.append(" n.productid, ");
			sql.append(" n.price_market, ");
//			sql
//					.append(" (select t.productid from product_list t where t.ifgoogle = 1 and t.pri_productid = pmaster.pri_productid) productid, ");
//			sql
//					.append(" (select tt.price_market from product_list tt where tt.ifgoogle = 1 and tt.pri_productid = pmaster.pri_productid) price_market, ");
			sql
					.append(" (select min(t.price_net) from product_list t where pmaster.pri_productid=t.pri_productid and t.ifdelete = 0 and t.ifvalid = 1 ) minNetPrice, ");
			sql
					.append(" (select max(t.price_net) from product_list t where pmaster.pri_productid=t.pri_productid and t.ifdelete = 0 and t.ifvalid = 1 ) maxNetPrice, ");
			sql
					.append(" (select count(*) from user_message m where m.productid = pmaster.pri_productid and m.message_type = 1 and m.ifdisp = 1) recommendCount, ");
			sql
					.append(" (select count(*) from user_message m where m.productid = pmaster.pri_productid and m.message_type = 2 and m.ifdisp = 1) askCount,  ");
			sql.append(" f_get_product_pic(productid, 102) photo_name ");
			sql.append(" from product_master pmaster ");
			sql
					.append(" join (select m.* from( select t.*,row_number() over(partition by t.pri_productid order by t.ifgoogle desc) as rn from product_list t where t.ifdelete = 0 and t.ifvalid=1 ) m where m.rn=1) n on pmaster.pri_productid = n.pri_productid ");
			sql.append(" where pmaster.classcode like '102%' ");
			sql.append(" and  n.ifvalid = 1  ");
			sql.append(" and n.ifgift = 0 ");
//			sql.append(" and plist.ifgoogle = 1 ");
			sql.append(" and pmaster.ifdelete = 0  ");
			sql.append(" and pmaster.ifindex = 0 ");
			sql.append(" and pmaster.comp_id = 1 ");

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
		IDAO dao = this.getDao();
		if (entry != null) {

			// 获取主商品ID
			String pri_productid = StringUtil
					.escapeNullObject(RowSetTypeConverter.getLong(
							entry.get("pri_productid")).toString());
			// 通过主商品ID得到"主商品售销状态"和"颜色列表"
			String sql2 = "select * from product_list t where t.pri_productid = ? and t.ifdelete = 0 and t.ifvalid = 1";
			Object[] args = { pri_productid };
			int[] argTypes = { java.sql.Types.INTEGER };
			List data2 = dao.getQuery(sql2.toString(), args, argTypes);
			StringBuffer color = new StringBuffer(); // 保存有货的颜色
			StringBuffer colorNo = new StringBuffer(); // 保存无货的颜色
			if (data2 != null && data2.size() > 0) {
				// 得到颜色和对应的ifuse
				for (Object d : data2) {
					Map entry2 = (Map) d;
					if (entry2 != null) {
						Integer ifuse = RowSetTypeConverter.getInteger(entry2
								.get("ifuse"));
						if (ifuse == 1 || ifuse == 3) {
							String color_dic = StringUtil
									.escapeNullObject(RowSetTypeConverter
											.getString(entry2.get("rela_color")));
							color.append(color_dic + ",");
							// 得到字典表中的对应颜色
//							String sql3 = "select dict.valuename from dict_basetype_value dict where dict.valueid = ?";
//							Object[] args_color = { color_dic };
//							int[] argTypes_color = { java.sql.Types.VARCHAR };
//							List data3 = dao.getQuery(sql3.toString(),
//									args_color, argTypes_color);
//							if (data3 != null && data3.size() > 0) {
//								for (int i = 0; i < data3.size(); i++)
//									color.append(data3.get(i));
//							}
						}
						else {
							String color_dic = StringUtil
									.escapeNullObject(RowSetTypeConverter
												.getString(entry2.get("rela_color")));
							colorNo.append(color_dic + ",");
						}
					}
				}
			}

			String colorStr = color.toString();
			int len = colorStr.length();
			if( len > 0 ) {
				if( colorStr.substring(len - 1, len).equals(",") )
					colorStr = colorStr.substring(0, colorStr.length() - 1);
					
			}
			
			String colorNoStr = colorNo.toString();
			int lenN = colorNoStr.length();
			if( lenN > 0 ) {
				if( colorNoStr.substring(lenN - 1, lenN).equals(",") )
					colorNoStr = colorNoStr.substring(0, colorNoStr.length() - 1);
					
			}
			
			String classcode = StringUtil
					.escapeNullObject(RowSetTypeConverter.getString(entry
							.get("classcode")));
			String productclass = RowSetTypeConverter.getInteger(entry
					.get("productclass")).toString();
					
			String productModel = StringUtil
					.escapeNullObject(RowSetTypeConverter.getString(entry
							.get("productmodel")));
			String product_para = StringUtil.html2Text(StringUtil
					.escapeNullObject(RowSetTypeConverter.getString(entry
							.get("product_para"))));
			String productname = StringUtil
					.escapeNullObject(RowSetTypeConverter.getString(entry
							.get("productname")));
			String describe = StringUtil.html2Text(StringUtil
					.escapeNullObject(RowSetTypeConverter.getString(entry
							.get("describe"))));

			String promotion = StringUtil.escapeNullObject(RowSetTypeConverter
					.getString(entry.get("promotion")));
			Integer sale_count = RowSetTypeConverter.getInteger(entry
					.get("sale_count"));
			String market_time = StringUtil
					.escapeNullObject(RowSetTypeConverter.getString(entry
							.get("market_time")));
			String rebate = StringUtil.escapeNullObject(RowSetTypeConverter
					.getString(entry.get("rebate"))); // 优惠政策
			String keywords = StringUtil.escapeNullObject(RowSetTypeConverter
					.getString(entry.get("keywords")));
			int net = RowSetTypeConverter
					.getInteger(entry.get("net_name"));
			String net_name = "";
			if( ((int)(Math.pow(2, 6)) & net ) >= 1 ) {
				net_name = "GSM";
			}
			if( ((int)(Math.pow(2, 5)) & net ) >= 1 ) {
				net_name = "CDMA";
			}
			if( ((int)(Math.pow(2, 4)) & net ) >= 1 ) {
				net_name = "双卡双待";
			}
			if( ((int)(Math.pow(2, 3)) & net ) >= 1 ) {
				net_name = "双模双待";
			}
			if( ((int)(Math.pow(2, 2)) & net ) >= 1 ) {
				net_name = "TDCDMA";
			}
			if( ((int)(Math.pow(2, 1)) & net ) >= 1 ) {
				net_name = "WCDMA";
			}
			if( ((int)(Math.pow(2, 0)) & net ) >= 1 ) {
				net_name = "CDMA2000";
			}
			
			int crowd = RowSetTypeConverter
					.getInteger(entry.get("crowd"));
			
			String crowd_name = "";
			if( ((int)(Math.pow(2, 4)) & crowd ) >= 1 ) {
				crowd_name = "老板";
			}
			if( ((int)(Math.pow(2, 3)) & crowd ) >= 1 ) {
				crowd_name = "白领商务";
			}
			if( ((int)(Math.pow(2, 2)) & crowd ) >= 1 ) {
				crowd_name = "时尚达人";
			}
			if( ((int)(Math.pow(2, 1)) & crowd ) >= 1 ) {
				crowd_name = "学生";
			}
			if( ((int)(Math.pow(2, 0)) & crowd ) >= 1 ) {
				crowd_name = "老年人";
			}
			
			
			Integer camera_pixel = RowSetTypeConverter.getInteger(entry
					.get("camera_pixel"));

			if (camera_pixel == null) {
				camera_pixel = 0;
			}

			Double screen_size = RowSetTypeConverter.getDouble(entry
					.get("screen_size"));
			if (screen_size == null) {
				screen_size = 0.0;
			}
			
			String shape = StringUtil.escapeNullObject(RowSetTypeConverter
					.getString(entry.get("shape_name")));

			String productid = StringUtil.escapeNullObject(RowSetTypeConverter
					.getLong(entry.get("productid")).toString()); // 从表google收录ID

			Double price_market = RowSetTypeConverter.getDouble(entry
					.get("price_market"));
			Double priceNetMin = RowSetTypeConverter.getDouble(entry
					.get("minNetPrice"));
			Double priceNetMax = RowSetTypeConverter.getDouble(entry
					.get("maxNetPrice"));

			Integer ifuse = RowSetTypeConverter.getInteger(entry.get("ifuse"));
			Integer recommendCount = RowSetTypeConverter.getInteger(entry
							.get("recommendCount"));
			Integer askCount = RowSetTypeConverter
					.getInteger(entry.get("askCount"));

			// Date up_time = RowSetTypeConverter.getDate(entry.get("up_time"));
			// Date down_time =
			// RowSetTypeConverter.getDate(entry.get("down_time"));
			//			
			String photo_name = StringUtil
			 		.escapeNullObject(RowSetTypeConverter.getString(entry
			 			.get("photo_name")));
			String brand_code = StringUtil.escapeNullObject(
						RowSetTypeConverter.getString(entry.get("brand_code")));
			String brand_name = StringUtil.escapeNullObject(
					RowSetTypeConverter.getString(entry.get("brand_name")));
			Integer brandv = RowSetTypeConverter.getInteger(entry.get("brand"));
			
			String defect = StringUtil.escapeNullObject(
					RowSetTypeConverter.getString(entry.get("defect")));
			String merit = StringUtil.escapeNullObject(
					RowSetTypeConverter.getString(entry.get("merit")));
			String ifdirect = RowSetTypeConverter.getInteger(entry.get("ifdirect")).toString();
			String weightIndex = RowSetTypeConverter.getInteger(entry.get("weightindex")).toString();
			String ifrecommend = RowSetTypeConverter.getInteger(entry.get("ifrecommend")).toString();
			int func = RowSetTypeConverter.getInteger(entry
					.get("func"));
			// String os_name = StringUtil
			// .escapeNullObject(RowSetTypeConverter
			// .getString(entry.get("os_name")));
			//
			// String shap_name = StringUtil
			// .escapeNullObject(RowSetTypeConverter.getString(entry
			// .get("shap_name")));
			// if( shap_name != null && !shap_name.equals("") ) {
			// if( !shap_name.equals("滑盖") && !shap_name.equals("翻盖") &&
			// !shap_name.equals("直板") )
			// shap_name = "其它";
			// }

			// StringBuffer phoneFun = new StringBuffer("");
			//
			// String handwrite_fun = StringUtil
			// .escapeNullObject(RowSetTypeConverter.getString(entry
			// .get("handwrite_fun")));
			// String thin = StringUtil.escapeNullObject(RowSetTypeConverter
			// .getString(entry.get("thin")));
			// String femail_fun =
			// StringUtil.escapeNullObject(RowSetTypeConverter
			// .getString(entry.get("femail_fun")));
			// String bluetooth_fun = StringUtil
			// .escapeNullObject(RowSetTypeConverter.getString(entry
			// .get("bluetooth_fun")));
			// String frared_fun =
			// StringUtil.escapeNullObject(RowSetTypeConverter
			// .getString(entry.get("frared_fun")));
			// String expansion_card_fun = StringUtil
			// .escapeNullObject(RowSetTypeConverter.getString(entry
			// .get("expansion_card_fun")));
			// String mp3_fun = StringUtil.escapeNullObject(RowSetTypeConverter
			// .getString(entry.get("mp3_fun")));
			// //视频播放
			// String video = StringUtil
			// .escapeNullObject(RowSetTypeConverter.getString(entry
			// .get("video")));
			// if( video.length() > 0 && !video.contains("不支持") &&
			// !video.contains("无") ) {
			// video = "视频播放";
			// }
			// else {
			// video = "";
			// }
			// String video_capture_fun = StringUtil
			// .escapeNullObject(RowSetTypeConverter.getString(entry
			// .get("video_capture_fun")));
			// String gps_fun = StringUtil.escapeNullObject(RowSetTypeConverter
			// .getString(entry.get("gps_fun")));
			// String email_fun =
			// StringUtil.escapeNullObject(RowSetTypeConverter
			// .getString(entry.get("email_fun")));
			// String audio_fun =
			// StringUtil.escapeNullObject(RowSetTypeConverter
			// .getString(entry.get("audio_fun")));
			// String wap_fun = StringUtil.escapeNullObject(RowSetTypeConverter
			// .getString(entry.get("wap_fun")));
			//			
			//			
			//			
			//			
			//			
			// String ring = StringUtil.escapeNullObject(RowSetTypeConverter
			// .getString(entry.get("ring")));
			//			
			// if( ring.contains("和弦铃声") ) {
			// ring = ring.replaceAll("和弦铃声", "和弦");
			// }
			// ring = ring.replaceAll("mp3", "p3");
			// ring = ring.replaceAll("MP3", "p3");
			//
			// phoneFun.append(handwrite_fun);
			// phoneFun.append(" ");
			// phoneFun.append(thin);
			// phoneFun.append(" ");
			// phoneFun.append(femail_fun);
			// phoneFun.append(" ");
			// phoneFun.append(bluetooth_fun);
			// phoneFun.append(" ");
			// phoneFun.append(frared_fun);
			// phoneFun.append(" ");
			// if(StringUtils.isNotEmpty(expansion_card_fun) &&
			// !StringUtils.equals(expansion_card_fun.trim(), "不支持扩展")) {
			// phoneFun.append(expansion_card_fun);
			// }
			// phoneFun.append(" ");
			// phoneFun.append(mp3_fun);
			// phoneFun.append(" ");
			// phoneFun.append(video);
			// phoneFun.append(" ");
			// phoneFun.append(video_capture_fun);
			// phoneFun.append(" ");
			// phoneFun.append(gps_fun);
			// phoneFun.append(" ");
			// phoneFun.append(email_fun);
			// phoneFun.append(" ");
			// phoneFun.append(audio_fun);
			// phoneFun.append(" ");
			// phoneFun.append(wap_fun);
			// phoneFun.append(" ");
			// if(StringUtils.isNotEmpty(os_name) && price_net.intValue() >=
			// 1500 && !StringUtils.equals(os_name.trim(), "无操作系统")
			// && !StringUtils.contains(os_name.trim(), "Series 40")){
			// phoneFun.append("智能系统");
			// }
			// phoneFun.append(" ");
			// phoneFun.append(ring);

			// String color = StringUtil.escapeNullObject(RowSetTypeConverter
			// .getString(entry.get("color"))).trim();
			// if( !color.equals("白色") && !color.equals("粉色") &&
			// !color.equals("红色") && !color.equals("蓝色")
			// && !color.equals("金色") && !color.equals("银色") &&
			// !color.equals("黑色") && !color.equals("紫色") ) {
			// color = "其它";
			// }


//			return PhoneDocument.getDocument(productid, productname, describe, promotion, 
//					price_market.toString(), price_net, sale_count.toString(), up_time, down_time, photo_name,
//					brand_name, net_name, os_name, shap_name, screen_size,
//					camera_pixel, market_time, phoneFun.toString().trim(),
//					productModel, keywords, color,
//					brandv.intValue(), ifuse.intValue());
			return PhoneDocument.getDocument_new(pri_productid, classcode, productclass, brandv.intValue(), brand_code, brand_name, productModel, product_para,
					productname, describe, promotion, sale_count, market_time, rebate, keywords, net_name, camera_pixel, 
					screen_size, productid, price_market, priceNetMin, priceNetMax, colorStr, colorNoStr,
					ifuse.intValue(), recommendCount.intValue(), askCount.intValue(), photo_name, shape, defect, merit, crowd_name, 
					ifdirect, weightIndex,ifrecommend,  func);
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
			sql.append(" update  product_master t set t.ifindex = 1 ");
			sql.append(" where t.pri_productid = ? ");
			sql.append(" and t.classcode like '102%' ");
			sql.append(" and t.comp_id = 1  ");
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
		sql.append(" update  product_master t set t.ifindex = 1 ");
		sql.append(" where t.ifindex = 0 and t.classcode like '102%' ");
		sql.append(" and t.comp_id = 1  ");
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
		sql.append(" update  product_master t set t.ifindex = 1 ");
		sql.append(" where t.ifdelete = 0 and  t.ifindex = 0 ");
		sql.append(" and t.classcode like '102%'");
		sql.append(" and t.comp_id = 1  ");

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
		sql.append(" update  product_master t set t.ifindex = 1 ");
		sql.append(" where t.ifdelete = 1 and  t.ifindex = 0 ");
		sql.append(" and t.classcode like '102%'");
		sql.append(" and t.comp_id = 1  ");
		IDAO dao = this.getDao();

		return dao.executeUpdate(sql.toString());
	}

	public static void main(String arg[]) {
		PhoneDataService peijianDataService = new PhoneDataService();
		// System.out.println( peijianDataService.reCompileView() );
		int dd = peijianDataService.getAllPhoneNums();

		int dddw = peijianDataService.getDeletedPhoneNums();

		List<Document> ddd = peijianDataService.getAllPhoneDatas(1, 10);
	}
}