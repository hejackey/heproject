package com.moobao.service.peijian;

/**
 * 配件的基本搜索
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.lucene.document.Document;

import com.apply.b2b.cms.base.IDAO;
import com.apply.b2b.cms.util.RowSetTypeConverter;
import com.apply.b2b.cms.util.StringUtil;
import com.moobao.indexser.document.PeiJianDocument;
import com.moobao.service.DataBaseService;

public class PeijianBaseDataService extends DataBaseService {

	/**
	 * 重新编译配件视图
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
	// System.out.println( "重新编译配件视图异常!" );
	// }
	// return f;
	// }

	/**
	 * 获得已下架的配件数量
	 * 
	 * @return
	 */
	public int getUnSalePeiJianNums() {
		StringBuilder sql = new StringBuilder("");
		sql.append(" select count(1) ");
		sql.append(" from v_fitting_index t ");
		sql.append(" where ");
		sql.append(" (t.down_time < sysdate and t.comp_id = 1) or ");
		sql.append(" (t.ifuse = 0 and t.comp_id = 1) ");
		IDAO dao = this.getDao();
		return dao.getQueryInt(sql.toString());
	}

	/**
	 * 获得已下架的配件信息
	 * 
	 * @param beginNum
	 * @param endNum
	 * @return
	 */

	
	

	/**
	 * 获得上架销售的配件数量
	 * 
	 * @return
	 */
	public int getAllPeiJianNums() {
		StringBuilder sql = new StringBuilder("");
		sql.append(" select count(1) ");                                                                        
		sql.append(" from product_master pmaster ");
		sql.append(" join (select m.* from( select t.*,row_number() over(partition by t.pri_productid order by t.ifgoogle desc) as rn from product_list t ) m where m.rn=1) n on pmaster.pri_productid = n.pri_productid ");
		sql.append(" where pmaster.classcode  like '103%' ");
		sql.append(" and  n.ifvalid = 1 and n.ifgift = 0 ");
		sql.append(" and  pmaster.ifdelete = 0  ");
		sql.append(" and pmaster.comp_id = 1 ");
		IDAO dao = this.getDao();
		return dao.getQueryInt(sql.toString());
	}

	/**
	 * 获得没有被索引的配件数量
	 * 
	 * @return
	 */
	public int getUnIndexedPeiJianNums() {
		StringBuilder sql = new StringBuilder("");	
		sql.append(" select count(1) ");                                                                        
		sql.append(" from product_master pmaster ");
		sql.append(" join (select m.* from( select t.*,row_number() over(partition by t.pri_productid order by t.ifgoogle desc) as rn from product_list t ) m where m.rn=1) n on pmaster.pri_productid = n.pri_productid ");
		sql.append(" where pmaster.classcode like '103%' ");
		sql.append(" and n.ifvalid = 1 and n.ifgift = 0 ");
		sql.append(" and pmaster.ifdelete = 0  ");
		sql.append(" and pmaster.ifindex = 0 ");
		sql.append(" and pmaster.comp_id = 1 ");

		IDAO dao = this.getDao();
		System.out.println(dao.getQueryInt(sql.toString()));
		return dao.getQueryInt(sql.toString());
	}

	/**
	 * 获得已删除了的配件数量
	 * 
	 * @return
	 */
	public int getDeletedPeiJianNums() {
		StringBuilder sql = new StringBuilder("");		
		sql.append(" select count(1) ");                                                                        
		sql.append(" from product_master pmaster ");
		sql.append(" where pmaster.classcode like '103%' ");
		sql.append(" and pmaster.ifindex = 0 ");
		sql.append(" and pmaster.ifdelete = 1  ");
		sql.append(" and pmaster.comp_id = 1 ");

		IDAO dao = this.getDao();
		return dao.getQueryInt(sql.toString());
	}

	/**
	 * 获得未被索引且已被删除了的配件信息
	 * 
	 * @param beginNum
	 * @param endNum
	 * @return
	 */
	public List<Document> getDeletedPeiJianDatas(int beginNum, int endNum) {
		List<Document> listData = new ArrayList<Document>();
		if (endNum >= beginNum && endNum > 0) {
			StringBuilder sql = new StringBuilder("");
			sql.append(" select pmaster.pri_productid,  ");
			sql.append(" pmaster.classcode, ");
			sql.append(" pmaster.productclass, ");
			sql.append(" pmaster.brand, ");
			sql.append(" (select dict.valuename  ");
			sql.append(" from dict_basetype_value dict ");
			sql.append(" where dict.valueid = pmaster.brand) brand_name, ");
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
			sql.append(" (select dict.valuename ");
			sql.append(" from dict_basetype_value dict ");
			sql
					.append(" where dict.valueid = pmaster.net_standard) net_name, ");
			sql.append(" pmaster.camera_pixel, ");
			sql.append(" pmaster.screen_size, ");
			sql.append(" pmaster.shape, ");
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
					.append(" (select min(t.price_net) from product_list t where pmaster.pri_productid=t.pri_productid and t.ifdelete = 0 and t.ifvalid = 1) minNetPrice, ");
			sql
					.append(" (select max(t.price_net) from product_list t where pmaster.pri_productid=t.pri_productid and t.ifdelete = 0 and t.ifvalid = 1 ) maxNetPrice, ");
			sql
					.append(" (select count(*) from user_message m where m.productid = pmaster.pri_productid and m.message_type = 1 and m.ifdisp = 1) recommendCount, ");
			sql
					.append(" (select count(*) from user_message m where m.productid = pmaster.pri_productid and m.message_type = 2 and m.ifdisp = 1) askCount,  ");
			sql.append(" f_get_product_pic(productid, 103) photo_name ");
			sql.append(" from product_master pmaster ");
			sql
					.append(" join (select m.* from( select t.*,row_number() over(partition by t.pri_productid order by t.ifgoogle desc) as rn from product_list t where t.ifdelete = 0 and t.ifvalid=1 ) m where m.rn=1) n on pmaster.pri_productid = n.pri_productid ");
			sql.append(" where pmaster.classcode like '103%'  ");
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
						Document doc = buildPeiJianIndexDocument(entry);
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
	 * 获得指定的配件信息
	 * 
	 * @return
	 */
	public Document getAPeiJianData(int PeiJianid) {
		if (PeiJianid > 0) {
			StringBuilder sql = new StringBuilder("");
			sql.append(" select pmaster.pri_productid,  ");
			sql.append(" pmaster.classcode, ");
			sql.append(" pmaster.productclass, ");
			sql.append(" pmaster.brand, ");
			sql.append(" (select dict.valuename  ");
			sql.append(" from dict_basetype_value dict ");
			sql.append(" where dict.valueid = pmaster.brand) brand_name, ");
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
			sql.append(" (select dict.valuename ");
			sql.append(" from dict_basetype_value dict ");
			sql
					.append(" where dict.valueid = pmaster.net_standard) net_name, ");
			sql.append(" pmaster.camera_pixel, ");
			sql.append(" pmaster.screen_size, ");
			sql.append(" pmaster.shape, ");
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
			sql.append(" f_get_product_pic(productid, 103) photo_name ");
			sql.append(" from product_master pmaster ");
			sql
					.append(" join (select m.* from( select t.*,row_number() over(partition by t.pri_productid order by t.ifgoogle desc) as rn from product_list t where t.ifdelete = 0 and t.ifvalid=1 ) m where m.rn=1) n on pmaster.pri_productid = n.pri_productid ");
			sql.append(" where pmaster.classcode like '103%' ");
			sql.append(" and  n.ifvalid = 1  ");
			sql.append(" and n.ifgift = 0 ");
//			sql.append(" and plist.ifgoogle = 1 ");
			sql.append(" and pmaster.ifdelete = 0  ");
			sql.append(" and pmaster.pri_productid = ?  "); 
			sql.append(" and pmaster.comp_id = 1 ");

			IDAO dao = this.getDao();
			Object[] args = { PeiJianid };
			int[] argTypes = { java.sql.Types.INTEGER };

			List data = dao.getQuery(sql.toString(), args, argTypes);

			if (data != null && data.size() > 0) {
				Object obj = data.get(0);
				Map entry = (Map) obj;
				if (entry != null) {
					Document doc = buildPeiJianIndexDocument(entry);
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
	 * 获得上架销售的配件信息
	 * 
	 * @param beginNum
	 * @param endNum
	 * @return
	 */
	public List<Document> getAllPeiJianDatas(int beginNum, int endNum) {
		List<Document> listData = new ArrayList<Document>();
		if (endNum >= beginNum && endNum > 0) {
			StringBuilder sql = new StringBuilder("");
			sql.append(" select pmaster.pri_productid,  ");
			sql.append(" pmaster.classcode, ");
			sql.append(" pmaster.productclass, ");
			sql.append(" pmaster.brand, ");
			sql.append(" (select dict.valuename  ");
			sql.append(" from dict_basetype_value dict ");
			sql.append(" where dict.valueid = pmaster.brand) brand_name, ");
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
			sql.append(" (select dict.valuename ");
			sql.append(" from dict_basetype_value dict ");
			sql
					.append(" where dict.valueid = pmaster.net_standard) net_name, ");
			sql.append(" pmaster.camera_pixel, ");
			sql.append(" pmaster.screen_size, ");
			sql.append(" pmaster.shape, ");
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
			sql.append(" f_get_product_pic(productid, 103) photo_name ");
			sql.append(" from product_master pmaster ");
			sql
					.append(" join (select m.* from( select t.*,row_number() over(partition by t.pri_productid order by t.ifgoogle desc) as rn from product_list t where t.ifdelete = 0 and t.ifvalid=1 ) m where m.rn=1) n on pmaster.pri_productid = n.pri_productid ");
			sql.append(" where pmaster.classcode like '103%' ");
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
						Document doc = buildPeiJianIndexDocument(entry);
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
	 * 获得上架销售未被索引的配件信息
	 * 
	 * @param beginNum
	 * @param endNum
	 * @return
	 */
	public List<Document> getUnindexedPeiJianDatas(int beginNum, int endNum) {
		List<Document> listData = new ArrayList<Document>();
		if (endNum >= beginNum && endNum > 0) {
			StringBuilder sql = new StringBuilder("");
			sql.append(" select pmaster.pri_productid,  ");
			sql.append(" pmaster.classcode, ");
			sql.append(" pmaster.productclass, ");
			sql.append(" pmaster.brand, ");
			sql.append(" (select dict.valuename  ");
			sql.append(" from dict_basetype_value dict ");
			sql.append(" where dict.valueid = pmaster.brand) brand_name, ");
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
			sql.append(" (select dict.valuename ");
			sql.append(" from dict_basetype_value dict ");
			sql
					.append(" where dict.valueid = pmaster.net_standard) net_name, ");
			sql.append(" pmaster.camera_pixel, ");
			sql.append(" pmaster.screen_size, ");
			sql.append(" pmaster.shape, ");
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
			sql.append(" f_get_product_pic(productid, 103) photo_name ");
			sql.append(" from product_master pmaster ");
			sql
					.append(" join (select m.* from( select t.*,row_number() over(partition by t.pri_productid order by t.ifgoogle desc) as rn from product_list t where t.ifdelete = 0 and t.ifvalid=1 ) m where m.rn=1) n on pmaster.pri_productid = n.pri_productid ");
			sql.append(" where pmaster.classcode like '103%' ");
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
						Document doc = buildPeiJianIndexDocument(entry);
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
	 * 构建一个PeiJian document
	 * 
	 * @param entry
	 * @return
	 */
	private Document buildPeiJianIndexDocument(Map entry) {
		IDAO dao = this.getDao();
		if (entry != null) {

			// 获取主商品ID
			String pri_productid = StringUtil
					.escapeNullObject(RowSetTypeConverter.getLong(
							entry.get("pri_productid")).toString());
			String classcode = StringUtil.escapeNullObject(RowSetTypeConverter
					.getString(entry.get("classcode")));
			String productclass = RowSetTypeConverter
					.getInteger(entry.get("productclass")).toString();
//			String productModel = StringUtil
//					.escapeNullObject(RowSetTypeConverter.getString(entry
//							.get("productmodel")));
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

//			String shape = StringUtil.escapeNullObject(RowSetTypeConverter
//					.getString(entry.get("shape_name")));

			String productid = StringUtil.escapeNullObject(RowSetTypeConverter
					.getLong(entry.get("productid")).toString());

			Double price_market = RowSetTypeConverter.getDouble(entry
					.get("price_market"));
			Double priceNetMin = RowSetTypeConverter.getDouble(entry
					.get("minNetPrice"));
			Double priceNetMax = RowSetTypeConverter.getDouble(entry
					.get("maxNetPrice"));

			Integer ifuse = RowSetTypeConverter.getInteger(entry.get("ifuse"));
			Integer recommendCount = RowSetTypeConverter.getInteger(entry
					.get("recommendCount"));
			Integer askCount = RowSetTypeConverter.getInteger(entry
					.get("askCount"));

			String photo_name = StringUtil.escapeNullObject(RowSetTypeConverter
					.getString(entry.get("photo_name")));
			//配件品牌
			 String brand_name = StringUtil.escapeNullObject(
					 RowSetTypeConverter.getString(entry.get("brand_name")))
					 .toLowerCase();
			// 配件关联的手机品牌
//			Integer brandv = RowSetTypeConverter.getInteger(entry
//					.get("brand_rela"));
//			// 配件分类(配件的父分类)
//			String parentid = StringUtil.escapeNullObject(RowSetTypeConverter
//					.getString(entry.get("parentid")));
//			// 配件关联的手机型号
//			String brandModel = StringUtil.escapeNullObject(RowSetTypeConverter
//					.getString(entry.get("brand_model")));

			return PeiJianDocument.getDocument(pri_productid, classcode, productclass, brand_name, product_para,
					productname, describe, promotion, sale_count, market_time, rebate, keywords, productid, price_market, 
					priceNetMin, priceNetMax, ifuse.intValue(), recommendCount.intValue(), askCount.intValue(), photo_name,
					"", 0, "");
		} else {
			return null;
		}
	}

	/**
	 * 更新指定配件为已被索引状态
	 * 
	 * @param PeiJianid
	 * @return
	 */
	public int setAPeiJianIndexed(int PeiJianid) {
		if (PeiJianid > 0) {
			StringBuilder sql = new StringBuilder();
			sql.append(" update  product_master t set t.ifindex = 1 ");
			sql.append(" where t.pri_productid = ? ");
			sql.append(" and t.classcode like '103%' ");
			sql.append(" and t.comp_id = 1  ");
			IDAO dao = this.getDao();
			Object[] args = { PeiJianid };
			int[] argTypes = { java.sql.Types.INTEGER };

			dao.executeUpdate(sql.toString(), args, argTypes);
		}
		return 0;
	}

	/**
	 * 更新所有配件为已被索引状态
	 * 
	 * @param PeiJianid
	 * @return
	 */
	public int setAllPeiJianIndexed() {
		StringBuilder sql = new StringBuilder();
		sql.append(" update  product_master t set t.ifindex = 1 ");
		sql.append(" where t.ifindex = 0 and t.classcode like '103%' ");
		sql.append(" and t.comp_id = 1  ");
		IDAO dao = this.getDao();
		return dao.executeUpdate(sql.toString());
	}

	/**
	 * 更新未索引的配件为已被索引状态
	 * 
	 * @param PeiJianid
	 * @return
	 */
	public int setUnIndexedPeiJianIndexed() {
		StringBuilder sql = new StringBuilder();
		sql.append(" update  product_master t set t.ifindex = 1 ");
		sql.append(" where t.ifdelete = 0 and  t.ifindex = 0 ");
		sql.append(" and t.classcode like '103%'");
		sql.append(" and t.comp_id = 1  ");

		IDAO dao = this.getDao();
		return dao.executeUpdate(sql.toString());
	}

	/**
	 * 更新已删除的配件为已被索引状态
	 * 
	 * @param PeiJianid
	 * @return
	 */
	public int setDeletedPeiJianIndexed() {
		StringBuilder sql = new StringBuilder();
		sql.append(" update  product_master t set t.ifindex = 1 ");
		sql.append(" where t.ifdelete = 1 and  t.ifindex = 0 ");
		sql.append(" and t.classcode like '103%'");
		sql.append(" and t.comp_id = 1  ");
		IDAO dao = this.getDao();

		return dao.executeUpdate(sql.toString());
	}

	public static void main(String arg[]) {
		PeijianBaseDataService peijianDataService = new PeijianBaseDataService();
		// System.out.println( peijianDataService.reCompileView() );
		int dd = peijianDataService.getAllPeiJianNums();

		int dddw = peijianDataService.getDeletedPeiJianNums();

		List<Document> ddd = peijianDataService.getAllPeiJianDatas(1, 10);
	}
}