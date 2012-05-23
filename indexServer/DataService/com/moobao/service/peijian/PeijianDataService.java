package com.moobao.service.peijian;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.lucene.document.Document;

import com.apply.b2b.cms.base.IDAO;
import com.apply.b2b.cms.util.RowSetTypeConverter;
import com.apply.b2b.cms.util.StringUtil;
import com.moobao.indexser.document.PeiJianDocument;
import com.moobao.service.DataBaseService;

/**
 * 
 * @author wind
 * 
 */
public class PeijianDataService extends DataBaseService {

	/**
	 * 获得上架销售配件的数量
	 * 
	 * @return
	 */
	public int getAllPeiJianNums() {
		StringBuilder sql = new StringBuilder("");

		sql.append(" select count(1) ");
		sql.append(" from product_master pmaster ");
		sql
				.append(" join pri_product_relation rela on pmaster.pri_productid = rela.slave_productid ");
		sql
				.append(" join product_list plist on pmaster.pri_productid = plist.pri_productid ");
		sql
				.append(" where pmaster.classcode like '103%' and rela.pri_productid in (select pm.pri_productid from product_master pm where pm.classcode like '102%') ");
		sql.append(" and plist.ifvalid = 1 ");
		sql.append(" and  pmaster.ifdelete = 0  ");
		sql.append(" and pmaster.comp_id = 1 ");
		IDAO dao = this.getDao();
		return dao.getQueryInt(sql.toString());
	}

	/**
	 * 获得上架销售配件的数据(视图)
	 * 
	 * @return
	 */
	public List<Document> getAllPeiJianDatas(int beginNum, int endNum) {
		List<Document> listData = new ArrayList<Document>();

		StringBuilder sql = new StringBuilder("");
		sql.append("select PRODUCTID,             ");
		sql.append("       PRODUCTNO,             ");
		sql.append("       PRODUCTNAME,           ");
		sql.append("       PROMOTION,             ");
		sql.append("       BRAND,                 ");
		sql.append("       BRAND_NAME,            ");
		sql.append("       PRICE_NET,             ");
		sql.append("       PRICE_MARKET,          ");
		sql.append("       PRICE_SET,             ");
		sql.append("       IFSET,                 ");
		sql.append("       IFUSE,                 ");
		sql.append("       IFVALID,               ");
		sql.append("       IFSCORE,               ");
		sql.append("       UP_TIME,               ");
		sql.append("       DOWN_TIME,             ");
		sql.append("       KEYWORDS,              ");
		sql.append("       MEMO,                  ");
		sql.append("       DESCRIBE,              ");
		sql.append("       CREATE_DT,             ");
		sql.append("       IFINDEX,               ");
		sql.append("       PRICE_STANDBY,         ");
		sql.append("       VALID_TIME,            ");
		sql.append("       IFDELETE,              ");
		sql.append("       PRODUCTMODEL,          ");
		sql.append("       SALE_COUNT,            ");
		sql.append("       PHOTO_NAME,            ");
		sql.append("       MARKET_TIME            ");
		sql.append("  from v_fitting_index t      ");
		sql.append(" where t.up_time < sysdate    ");
		sql.append("   and t.down_time > sysdate  ");
		sql.append("   and t.ifvalid = 1          ");
		sql.append("   and t.ifuse <> 0            ");
		sql.append("   and t.ifdelete = 0         ");
		sql.append("   and t.comp_id = 1          ");

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
		return listData;
	}

	/**
	 * 获得上架销售配件的数据(表)
	 * 
	 * @return
	 */
	public List<Document> getAllPeiJianDatas_new(int beginNum, int endNum) {
		List<Document> listData = new ArrayList<Document>();

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
		sql.append(" pmaster.ifuse, ");
		sql.append(" (select dict.valuename ");
		sql.append(" from dict_basetype_value dict ");
		sql.append(" where dict.valueid = pmaster.net_standard) net_name, ");
		sql.append(" pmaster.camera_pixel, ");
		sql.append(" pmaster.screen_size, ");
		sql.append(" pmaster.shape, ");
		sql.append(" (select dict.valuename ");
		sql.append(" from dict_basetype_value dict ");
		sql.append(" where dict.valueid = pmaster.shape) shape_name,");
		sql.append(" plist.productid, ");
		sql.append(" plist.price_market, ");
		sql
				.append(" (select min(t.price_net) from product_list t where pmaster.pri_productid=t.pri_productid and t.ifdelete = 0 and t.ifvalid = 1 ) minNetPrice, ");
		sql
				.append(" (select max(t.price_net) from product_list t where pmaster.pri_productid=t.pri_productid and t.ifdelete = 0 and t.ifvalid = 1 ) maxNetPrice, ");
		sql
				.append(" (select count(*) from user_message m where m.productid = pmaster.pri_productid and m.message_type = 1 and m.ifdisp = 1) recommendCount, ");
		sql
				.append(" (select count(*) from user_message m where m.productid = pmaster.pri_productid and m.message_type = 2 and m.ifdisp = 1) askCount,  ");
		sql.append(" f_get_product_pic(productid, 103) photo_name, ");

		sql
				.append(" (select dictphy.parentid from dict_physicsclass dictphy where dictphy.classcode = pmaster.classcode) parentid, ");
		sql
				.append(" (select pm2.brand from product_master pm2 where pm2.pri_productid = rela.pri_productid) brand_rela, ");
		sql
				.append(" (select pm2.productmodel from product_master pm2 where pm2.pri_productid = rela.pri_productid) brand_model ");
		sql.append(" from product_master pmaster ");
		sql
				.append(" join pri_product_relation rela on pmaster.pri_productid = rela.slave_productid ");
		sql
				.append(" join product_list plist on pmaster.pri_productid = plist.pri_productid ");
		sql
				.append(" where pmaster.classcode like '103%' and rela.pri_productid in (select pm.pri_productid from product_master pm where pm.classcode like '102%')   ");
		sql.append(" and  plist.ifvalid = 1  ");
		sql.append(" and plist.ifgift = 0 ");
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
		return listData;
	}

	/**
	 * 获得上架销售未索引的配件的数量
	 * 
	 * @return
	 */
	public int getUnindexedPeiJianNums() {
		StringBuilder sql = new StringBuilder("");
		sql.append(" select count(1) ");
		sql.append(" from product_master pmaster ");
		sql
				.append(" join pri_product_relation rela on pmaster.pri_productid = rela.slave_productid ");
		sql
				.append(" join product_list plist on pmaster.pri_productid = plist.pri_productid ");
		sql
				.append(" where pmaster.classcode like '103%' and rela.pri_productid in (select pm.pri_productid from product_master pm where pm.classcode like '102%') ");
		sql.append(" and plist.ifvalid = 1 ");
		sql.append(" and pmaster.ifindex = 0  ");
		sql.append(" and pmaster.ifdelete = 0  ");
		sql.append(" and pmaster.comp_id = 1 ");
		IDAO dao = this.getDao();
		return dao.getQueryInt(sql.toString());
	}

	/**
	 * 获得上架销售未索引的配件的数据(视图)
	 * 
	 * @return
	 */
	public List<Document> getUnindexedPeiJianDatas(int beginNum, int endNum) {
		List<Document> listData = new ArrayList<Document>();

		StringBuilder sql = new StringBuilder("");
		sql.append("select PRODUCTID,             ");
		sql.append("       PRODUCTNO,             ");
		sql.append("       PRODUCTNAME,           ");
		sql.append("       PROMOTION,             ");
		sql.append("       BRAND,                 ");
		sql.append("       BRAND_NAME,            ");
		sql.append("       PRICE_NET,             ");
		sql.append("       PRICE_MARKET,          ");
		sql.append("       PRICE_SET,             ");
		sql.append("       IFSET,                 ");
		sql.append("       IFUSE,                 ");
		sql.append("       IFVALID,               ");
		sql.append("       IFSCORE,               ");
		sql.append("       UP_TIME,               ");
		sql.append("       DOWN_TIME,             ");
		sql.append("       KEYWORDS,              ");
		sql.append("       MEMO,                  ");
		sql.append("       DESCRIBE,              ");
		sql.append("       CREATE_DT,             ");
		sql.append("       IFINDEX,               ");
		sql.append("       PRICE_STANDBY,         ");
		sql.append("       VALID_TIME,            ");
		sql.append("       IFDELETE,              ");
		sql.append("       PRODUCTMODEL,          ");
		sql.append("       SALE_COUNT,            ");
		sql.append("       PHOTO_NAME,            ");
		sql.append("       MARKET_TIME            ");
		sql.append("  from v_fitting_index t      ");
		sql.append(" where t.up_time < sysdate    ");
		sql.append("   and t.down_time > sysdate  ");
		sql.append("   and t.ifvalid = 1          ");
		sql.append("   and t.ifuse <> 0            ");
		sql.append("   and t.ifdelete = 0  and  t.ifindex = 0     ");
		sql.append("   and t.comp_id = 1          ");

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
		return listData;
	}

	/**
	 * 获得上架销售未索引的配件的数据(表)
	 * 
	 * @return
	 */
	public List<Document> getUnindexedPeiJianDatas_new(int beginNum, int endNum) {
		List<Document> listData = new ArrayList<Document>();

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
		sql.append(" pmaster.ifuse, ");
		sql.append(" (select dict.valuename ");
		sql.append(" from dict_basetype_value dict ");
		sql.append(" where dict.valueid = pmaster.net_standard) net_name, ");
		sql.append(" pmaster.camera_pixel, ");
		sql.append(" pmaster.screen_size, ");
		sql.append(" pmaster.shape, ");
		sql.append(" (select dict.valuename ");
		sql.append(" from dict_basetype_value dict ");
		sql.append(" where dict.valueid = pmaster.shape) shape_name,");
		sql.append(" plist.productid, ");
		sql.append(" plist.price_market, ");
		sql
				.append(" (select min(t.price_net) from product_list t where pmaster.pri_productid=t.pri_productid and t.ifdelete = 0 and t.ifvalid = 1 ) minNetPrice, ");
		sql
				.append(" (select max(t.price_net) from product_list t where pmaster.pri_productid=t.pri_productid and t.ifdelete = 0 and t.ifvalid = 1 ) maxNetPrice, ");
		sql
				.append(" (select count(*) from user_message m where m.productid = pmaster.pri_productid and m.message_type = 1 and m.ifdisp = 1) recommendCount, ");
		sql
				.append(" (select count(*) from user_message m where m.productid = pmaster.pri_productid and m.message_type = 2 and m.ifdisp = 1) askCount,  ");
		sql.append(" f_get_product_pic(productid, 103) photo_name, ");

		sql.append(" rela.pri_productid, ");
		sql
				.append(" (select dictphy.parentid from dict_physicsclass dictphy where dictphy.classcode = pmaster.classcode) parentid, ");
		sql
				.append(" (select pm2.brand from product_master pm2 where pm2.pri_productid = rela.pri_productid) brand_rela, ");
		sql
				.append(" (select pm2.productmodel from product_master pm2 where pm2.pri_productid = rela.pri_productid) brand_model ");
		sql.append(" from product_master pmaster ");
		sql
				.append(" join pri_product_relation rela on pmaster.pri_productid = rela.slave_productid ");
		sql
				.append(" join product_list plist on pmaster.pri_productid = plist.pri_productid ");
		sql
				.append(" where pmaster.classcode like '103%' and rela.pri_productid in (select pm.pri_productid from product_master pm where pm.classcode like '102%')   ");

		sql.append("   and pmaster.ifvalid = 1          ");
		sql
				.append("   and pmaster.ifdelete = 0  and  pmaster.ifindex = 0     ");
		sql.append("   and pmaster.comp_id = 1 ");

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
		return listData;
	}

	/**
	 * 获得已删除的配件的数量
	 * 
	 * @return
	 */
	public int getDeletedPeiJianNums() {
		StringBuilder sql = new StringBuilder("");
		sql.append(" select count(1) ");
		sql.append(" from product_master pmaster ");
		sql
				.append(" join pri_product_relation rela on pmaster.pri_productid = rela.slave_productid ");
		sql
				.append(" join product_list plist on pmaster.pri_productid = plist.pri_productid ");
		sql
				.append(" where pmaster.classcode like '103%' and rela.pri_productid in (select pm.pri_productid from product_master pm where pm.classcode like '102%') ");
		sql.append("  and pmaster.ifdelete = 1 and pmaster.ifindex = 0  ");
		sql.append("  and pmaster.comp_id = 1 ");

		IDAO dao = this.getDao();
		return dao.getQueryInt(sql.toString());
	}

	/**
	 * 获得已删除的配件的数据(视图)
	 * 
	 * @return
	 */
	public List<Document> getDeletedPeiJianDatas(int beginNum, int endNum) {
		List<Document> listData = new ArrayList<Document>();

		StringBuilder sql = new StringBuilder("");
		sql.append("select PRODUCTID,             ");
		sql.append("       PRODUCTNO,             ");
		sql.append("       PRODUCTNAME,           ");
		sql.append("       PROMOTION,             ");
		sql.append("       BRAND,                 ");
		sql.append("       BRAND_NAME,            ");
		sql.append("       PRICE_NET,             ");
		sql.append("       PRICE_MARKET,          ");
		sql.append("       PRICE_SET,             ");
		sql.append("       IFSET,                 ");
		sql.append("       IFUSE,                 ");
		sql.append("       IFVALID,               ");
		sql.append("       IFSCORE,               ");
		sql.append("       UP_TIME,               ");
		sql.append("       DOWN_TIME,             ");
		sql.append("       KEYWORDS,              ");
		sql.append("       MEMO,                  ");
		sql.append("       DESCRIBE,              ");
		sql.append("       CREATE_DT,             ");
		sql.append("       IFINDEX,               ");
		sql.append("       PRICE_STANDBY,         ");
		sql.append("       VALID_TIME,            ");
		sql.append("       IFDELETE,              ");
		sql.append("       PRODUCTMODEL,          ");
		sql.append("       SALE_COUNT,            ");
		sql.append("       PHOTO_NAME,            ");
		sql.append("       MARKET_TIME            ");
		sql.append("  from v_fitting_index t      ");
		sql.append("  where t.up_time < sysdate   ");
		sql.append("  and t.down_time > sysdate   ");
		sql.append("  and t.ifdelete = 1 and t.ifindex = 0  ");
		sql.append("  and t.comp_id = 1           ");

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
		return listData;
	}

	/**
	 * 获得已删除的配件的数据(表)
	 * 
	 * @return
	 */
	public List<Document> getDeletedPeiJianDatas_new(int beginNum, int endNum) {
		List<Document> listData = new ArrayList<Document>();

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
		sql.append(" pmaster.ifuse, ");
		sql.append(" (select dict.valuename ");
		sql.append(" from dict_basetype_value dict ");
		sql.append(" where dict.valueid = pmaster.net_standard) net_name, ");
		sql.append(" pmaster.camera_pixel, ");
		sql.append(" pmaster.screen_size, ");
		sql.append(" pmaster.shape, ");
		sql.append(" (select dict.valuename ");
		sql.append(" from dict_basetype_value dict ");
		sql.append(" where dict.valueid = pmaster.shape) shape_name,");
		sql.append(" plist.productid, ");
		sql.append(" plist.price_market, ");
		sql
				.append(" (select min(t.price_net) from product_list t where pmaster.pri_productid=t.pri_productid and t.ifdelete = 0 and t.ifvalid = 1 ) minNetPrice, ");
		sql
				.append(" (select max(t.price_net) from product_list t where pmaster.pri_productid=t.pri_productid and t.ifdelete = 0 and t.ifvalid = 1 ) maxNetPrice, ");
		sql
				.append(" (select count(*) from user_message m where m.productid = pmaster.pri_productid and m.message_type = 1 and m.ifdisp = 1) recommendCount, ");
		sql
				.append(" (select count(*) from user_message m where m.productid = pmaster.pri_productid and m.message_type = 2 and m.ifdisp = 1) askCount,  ");
		sql.append(" f_get_product_pic(productid, 103) photo_name, ");

		sql.append(" rela.pri_productid, ");
		sql
				.append(" (select dictphy.parentid from dict_physicsclass dictphy where dictphy.classcode = pmaster.classcode) parentid, ");
		sql
				.append(" (select pm2.brand from product_master pm2 where pm2.pri_productid = rela.pri_productid) brand_rela, ");
		sql
				.append(" (select pm2.productmodel from product_master pm2 where pm2.pri_productid = rela.pri_productid) brand_model ");
		sql.append(" from product_master pmaster ");
		sql
				.append(" join pri_product_relation rela on pmaster.pri_productid = rela.slave_productid ");
		sql
				.append(" join product_list plist on pmaster.pri_productid = plist.pri_productid ");
		sql
				.append(" where pmaster.classcode like '103%' and rela.pri_productid in (select pm.pri_productid from product_master pm where pm.classcode like '102%')   ");

		sql.append("  and pmaster.ifdelete = 1 and pmaster.ifindex = 0  ");
		sql.append("  and pmaster.comp_id = 1 ");

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
		return listData;
	}

	/**
	 * 获得已下架的配件的数量
	 * 
	 * @return
	 */
	public int getUnSalePeiJianNums() {
		StringBuilder sql = new StringBuilder("");
		sql.append(" select count(1) ");
		sql.append(" from product_master pmaster ");
		sql
				.append(" join pri_product_relation rela on pmaster.pri_productid = rela.slave_productid ");
		sql
				.append(" join product_list plist on pmaster.pri_productid = plist.pri_productid ");
		sql
				.append(" where pmaster.classcode like '103%' and rela.pri_productid in (select pm.pri_productid from product_master pm where pm.classcode like '102%') ");
		sql
				.append("  where (plist.down_time < sysdate and plist.comp_id = 1) or ");
		sql.append("  (plist.ifuse = 0 and plist.comp_id = 1) ");
		IDAO dao = this.getDao();
		return dao.getQueryInt(sql.toString());
	}

	/**
	 * 获得已下架的配件的数据(视图)
	 * 
	 * @return
	 */
	public List<Document> getUnSalePeiJianDatas(int beginNum, int endNum) {
		List<Document> listData = new ArrayList<Document>();

		StringBuilder sql = new StringBuilder("");
		sql.append("select PRODUCTID,             ");
		sql.append("       PRODUCTNO,             ");
		sql.append("       PRODUCTNAME,           ");
		sql.append("       PROMOTION,             ");
		sql.append("       BRAND,                 ");
		sql.append("       BRAND_NAME,            ");
		sql.append("       PRICE_NET,             ");
		sql.append("       PRICE_MARKET,          ");
		sql.append("       PRICE_SET,             ");
		sql.append("       IFSET,                 ");
		sql.append("       IFUSE,                 ");
		sql.append("       IFVALID,               ");
		sql.append("       IFSCORE,               ");
		sql.append("       UP_TIME,               ");
		sql.append("       DOWN_TIME,             ");
		sql.append("       KEYWORDS,              ");
		// sql.append(" MEMO, ");
		sql.append("       DESCRIBE,              ");
		sql.append("       CREATE_DT,             ");
		sql.append("       IFINDEX,               ");
		sql.append("       PRICE_STANDBY,         ");
		sql.append("       VALID_TIME,            ");
		sql.append("       IFDELETE,              ");
		sql.append("       PRODUCTMODEL,          ");
		sql.append("       SALE_COUNT,            ");
		sql.append("       PHOTO_NAME,            ");
		sql.append("       MARKET_TIME            ");
		sql.append("  from v_fitting_index t      ");
		sql.append("  where t.down_time < sysdate ");
		sql.append("  UNION ");
		sql.append("select PRODUCTID,             ");
		sql.append("       PRODUCTNO,             ");
		sql.append("       PRODUCTNAME,           ");
		sql.append("       PROMOTION,             ");
		sql.append("       BRAND,                 ");
		sql.append("       BRAND_NAME,            ");
		sql.append("       PRICE_NET,             ");
		sql.append("       PRICE_MARKET,          ");
		sql.append("       PRICE_SET,             ");
		sql.append("       IFSET,                 ");
		sql.append("       IFUSE,                 ");
		sql.append("       IFVALID,               ");
		sql.append("       IFSCORE,               ");
		sql.append("       UP_TIME,               ");
		sql.append("       DOWN_TIME,             ");
		sql.append("       KEYWORDS,              ");
		// sql.append(" MEMO, ");
		sql.append("       DESCRIBE,              ");
		sql.append("       CREATE_DT,             ");
		sql.append("       IFINDEX,               ");
		sql.append("       PRICE_STANDBY,         ");
		sql.append("       VALID_TIME,            ");
		sql.append("       IFDELETE,              ");
		sql.append("       PRODUCTMODEL,          ");
		sql.append("       SALE_COUNT,            ");
		sql.append("       PHOTO_NAME,            ");
		sql.append("       MARKET_TIME            ");
		sql.append("  from v_fitting_index tt      ");
		sql.append("  where tt.ifuse = 0 ");
		sql.append("  and tt.comp_id = 1 ");

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
		return listData;
	}

	/**
	 * 获得已下架的配件的数据(表)
	 * 
	 * @return
	 */
	public List<Document> getUnSalePeiJianDatas_new(int beginNum, int endNum) {
		List<Document> listData = new ArrayList<Document>();

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
		sql.append(" pmaster.ifuse, ");
		sql.append(" (select dict.valuename ");
		sql.append(" from dict_basetype_value dict ");
		sql.append(" where dict.valueid = pmaster.net_standard) net_name, ");
		sql.append(" pmaster.camera_pixel, ");
		sql.append(" pmaster.screen_size, ");
		sql.append(" pmaster.shape, ");
		sql.append(" (select dict.valuename ");
		sql.append(" from dict_basetype_value dict ");
		sql.append(" where dict.valueid = pmaster.shape) shape_name,");
		sql.append(" plist.productid, ");
		sql.append(" plist.price_market, ");
		sql
				.append(" (select min(t.price_net) from product_list t where pmaster.pri_productid=t.pri_productid and t.ifdelete = 0 and t.ifvalid = 1 ) minNetPrice, ");
		sql
				.append(" (select max(t.price_net) from product_list t where pmaster.pri_productid=t.pri_productid and t.ifdelete = 0 and t.ifvalid = 1 ) maxNetPrice, ");
		sql
				.append(" (select count(*) from user_message m where m.productid = pmaster.pri_productid and m.message_type = 1 and m.ifdisp = 1) recommendCount, ");
		sql
				.append(" (select count(*) from user_message m where m.productid = pmaster.pri_productid and m.message_type = 2 and m.ifdisp = 1) askCount,  ");
		sql.append(" f_get_product_pic(productid, 103) photo_name, ");

		sql.append(" rela.pri_productid, ");
		sql
				.append(" (select dictphy.parentid from dict_physicsclass dictphy where dictphy.classcode = pmaster.classcode) parentid, ");
		sql
				.append(" (select pm2.brand from product_master pm2 where pm2.pri_productid = rela.pri_productid) brand_rela, ");
		sql
				.append(" (select pm2.productmodel from product_master pm2 where pm2.pri_productid = rela.pri_productid) brand_model ");
		sql.append(" from product_master pmaster ");
		sql
				.append(" join pri_product_relation rela on pmaster.pri_productid = rela.slave_productid ");
		sql
				.append(" join product_list plist on pmaster.pri_productid = plist.pri_productid ");
		sql
				.append(" where pmaster.classcode like '103%' and rela.pri_productid in (select pm.pri_productid from product_master pm where pm.classcode like '102%')   ");

		sql.append(" where plist.down_time < sysdate and plist.comp_id = 1 ");
		sql.append(" or ");
		sql.append(" plist.ifuse = 0 and pmaster.comp_id = 1");
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
		return listData;
	}

	/**
	 * 获得上架销售配件的数据(视图)
	 * 
	 * @return
	 */
	public Document getAllPeiJianDatas(int peiJianid) {
		if (peiJianid > 0) {
			StringBuilder sql = new StringBuilder("");
			sql.append("select PRODUCTID,             ");
			sql.append("       PRODUCTNO,             ");
			sql.append("       PRODUCTNAME,           ");
			sql.append("       PROMOTION,             ");
			sql.append("       BRAND,                 ");
			sql.append("       BRAND_NAME,            ");
			sql.append("       PRICE_NET,             ");
			sql.append("       PRICE_MARKET,          ");
			sql.append("       PRICE_SET,             ");
			sql.append("       IFSET,                 ");
			sql.append("       IFUSE,                 ");
			sql.append("       IFVALID,               ");
			sql.append("       IFSCORE,               ");
			sql.append("       UP_TIME,               ");
			sql.append("       DOWN_TIME,             ");
			sql.append("       KEYWORDS,              ");
			sql.append("       MEMO,                  ");
			sql.append("       DESCRIBE,              ");
			sql.append("       CREATE_DT,             ");
			sql.append("       IFINDEX,               ");
			sql.append("       PRICE_STANDBY,         ");
			sql.append("       VALID_TIME,            ");
			sql.append("       IFDELETE,              ");
			sql.append("       PRODUCTMODEL,          ");
			sql.append("       SALE_COUNT,            ");
			sql.append("       PHOTO_NAME,            ");
			sql.append("       MARKET_TIME            ");
			sql.append("  from v_fitting_index t      ");
			sql.append(" where t.up_time < sysdate    ");
			sql.append("   and t.down_time > sysdate  ");
			sql.append("   and t.ifvalid = 1          ");
			sql.append("   and t.ifuse <> 0            ");
			sql.append("   and t.ifdelete = 0 and t.productid = ? ");
			sql.append("   and t.comp_id = 1          ");
			IDAO dao = this.getDao();
			Object[] args = { peiJianid };
			int[] argTypes = { java.sql.Types.INTEGER };

			List data = dao.getQuery(sql.toString(), args, argTypes);

			if (data != null && data.size() > 0) {
				Object obj = data.get(0);
				Map entry = (Map) obj;
				if (entry != null) {
					Document doc = buildPeiJianIndexDocument(entry);
					if (doc != null) {
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
		} else {
			return null;
		}
	}

	/**
	 * 获得上架销售配件的数据(表)
	 * 
	 * @return
	 */
	public Document getAllPeiJianDatas_new(int peiJianid) {
		if (peiJianid > 0) {
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
			sql.append(" plist.productid, ");
			sql.append(" plist.price_market, ");
			sql
					.append(" (select min(t.price_net) from product_list t where pmaster.pri_productid=t.pri_productid and t.ifdelete = 0 and t.ifvalid = 1 ) minNetPrice, ");
			sql
					.append(" (select max(t.price_net) from product_list t where pmaster.pri_productid=t.pri_productid and t.ifdelete = 0 and t.ifvalid = 1 ) maxNetPrice, ");
			sql
					.append(" (select count(*) from user_message m where m.productid = pmaster.pri_productid and m.message_type = 1 and m.ifdisp = 1) recommendCount, ");
			sql
					.append(" (select count(*) from user_message m where m.productid = pmaster.pri_productid and m.message_type = 2 and m.ifdisp = 1) askCount,  ");
			sql.append(" f_get_product_pic(productid, 103) photo_name, ");

			sql.append(" rela.pri_productid, ");
			sql
					.append(" (select dictphy.parentid from dict_physicsclass dictphy where dictphy.classcode = pmaster.classcode) parentid, ");
			sql
					.append(" (select pm2.brand from product_master pm2 where pm2.pri_productid = rela.pri_productid) brand_rela, ");
			sql
					.append(" (select pm2.productmodel from product_master pm2 where pm2.pri_productid = rela.pri_productid) brand_model ");
			sql.append(" from product_master pmaster ");
			sql
					.append(" join pri_product_relation rela on pmaster.pri_productid = rela.slave_productid ");
			sql
					.append(" join product_list plist on pmaster.pri_productid = plist.pri_productid ");
			sql
					.append(" where pmaster.classcode like '103%' and rela.pri_productid in (select pm.pri_productid from product_master pm where pm.classcode like '102%')   ");

			sql.append("   and pmaster.ifvalid = 1          ");
			sql
					.append("   and pmaster.ifdelete = 0 and pmaster.pri_productid = ? ");
			sql.append("   and pmaster.comp_id = 1 ");

			IDAO dao = this.getDao();
			Object[] args = { peiJianid };
			int[] argTypes = { java.sql.Types.INTEGER };

			List data = dao.getQuery(sql.toString(), args, argTypes);

			if (data != null && data.size() > 0) {
				Object obj = data.get(0);
				Map entry = (Map) obj;
				if (entry != null) {
					Document doc = buildPeiJianIndexDocument(entry);
					if (doc != null) {
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
		} else {
			return null;
		}
	}

	/**
	 * 构建一个Peijian document
	 * 
	 * @param entry
	 * @return
	 */
	private Document buildPeiJianIndexDocument(Map entry) {
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
			Integer brandv = RowSetTypeConverter.getInteger(entry
					.get("brand_rela"));
			// 配件分类(配件的父分类)
			String parentid = StringUtil.escapeNullObject(RowSetTypeConverter
					.getString(entry.get("parentid")));
			// 配件关联的手机型号
			String brandModel = StringUtil.escapeNullObject(RowSetTypeConverter
					.getString(entry.get("brand_model")));

			return PeiJianDocument.getDocument(pri_productid, classcode, productclass, brand_name, product_para,
					productname, describe, promotion, sale_count, market_time, rebate, keywords, productid, price_market, 
					priceNetMin, priceNetMax, ifuse.intValue(), recommendCount.intValue(), askCount.intValue(), photo_name,
					parentid, brandv, brandModel);
		} else {
			return null;
		}
	}

	/**
	 * 更新指定配件为已被索引状态
	 * 
	 * @param peiJanid
	 * @return
	 */
	public int setAPeijianIndexed(int peiJanid) {
		if (peiJanid > 0) {
			StringBuilder sql = new StringBuilder();
			sql
					.append(" update product_master pl set pl.ifindex = 1 where pl.productid = ? ");
			sql.append("   and pl.comp_id = 1 ");
			IDAO dao = this.getDao();
			Object[] args = { peiJanid };
			int[] argTypes = { java.sql.Types.INTEGER };

			dao.executeUpdate(sql.toString(), args, argTypes);
		}
		return 0;
	}

	/**
	 * 更新所有的配件为已被索引状态
	 * 
	 * @return
	 */
	public int setAlldPeiJianIndexed() {
		StringBuilder sql = new StringBuilder();
		sql.append(" update  product_master t set t.ifindex = 1 ");
		sql.append(" where   t.ifindex = 0 and t.classcode like '103%' ");
		sql.append(" and t.comp_id = 1 ");
		IDAO dao = this.getDao();
		return dao.executeUpdate(sql.toString());
	}

	/**
	 * 更新未索引的配件为已被索引状态
	 * 
	 * @return
	 */
	public int setUnIndexedPeiJianIndexed() {
		StringBuilder sql = new StringBuilder();
		sql.append(" update  product_master t set t.ifindex = 1 ");

		// sql.append(" where t.up_time<sysdate ");
		// sql.append(" and t.down_time>sysdate ");
		sql.append(" where t.ifvalid=1 ");
		sql
				.append(" and t.ifdelete = 0 and  t.ifindex = 0 and t.classcode like '103%' ");
		sql.append(" and t.comp_id = 1 ");

		IDAO dao = this.getDao();
		return dao.executeUpdate(sql.toString());
	}

	/**
	 * 更新已删除的配件为已被索引状态
	 * 
	 * @return
	 */
	public int setDeletedPeijianIndexed() {
		StringBuilder sql = new StringBuilder();
		sql.append(" update  product_master t set t.ifindex = 1 ");

		// sql.append(" where t.up_time<sysdate ");
		// sql.append(" and t.down_time>sysdate ");
		sql.append(" and t.ifvalid=1 ");
		sql
				.append(" t.ifdelete = 1 and  t.ifindex = 0 and t.classcode like '103%' ");
		sql.append(" and t.comp_id = 1 ");

		IDAO dao = this.getDao();

		return dao.executeUpdate(sql.toString());
	}

	public static void main(String arg[]) {
		PeijianDataService peijianDataService = new PeijianDataService();
		int dd = peijianDataService.getAllPeiJianNums();
	}
}
