package com.moobao.service.netcomputer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.lucene.document.Document;

import com.apply.b2b.cms.base.IDAO;
import com.apply.b2b.cms.util.RowSetTypeConverter;
import com.apply.b2b.cms.util.StringUtil;
import com.moobao.indexser.document.NetComputerDocument;
import com.moobao.service.DataBaseService;

/**
 * 
 * @author wind
 * 
 */
public class NetComputerDataService extends DataBaseService {

	/**
	 * 获得上架销售上网本和上网卡的数量
	 * 
	 * @return
	 */
	public int getAllNetComputerNums() {
		StringBuilder sql = new StringBuilder("");
		sql.append(" select count(1) ");                                                                        
		sql.append(" from product_master pmaster ");
		sql.append(" join (select m.* from( select t.*,row_number() over(partition by t.pri_productid order by t.ifgoogle desc) as rn from product_list t ) m where m.rn=1) n on pmaster.pri_productid = n.pri_productid ");
		sql.append(" where (pmaster.classcode like '104%' or pmaster.classcode like '105%') ");
		sql.append(" and  n.ifvalid = 1 ");
		sql.append(" and  pmaster.ifdelete = 0  ");
		sql.append(" and pmaster.comp_id = 1 ");
		IDAO dao = this.getDao();
		return dao.getQueryInt(sql.toString());
	}

	/**
	 * 获得上架销售上网本和上网卡的数据(视图)
	 * 
	 * @return
	 */
	public List<Document> getAllNetComputerDatas(int beginNum, int endNum) {
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
		sql.append("  from V_NOTEBOOK_INDEX t      ");
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
					Document doc = buildNetComputerIndexDocument(entry);
					if (doc != null) {
						listData.add(doc);
					}
				}
			}
		}
		return listData;
	}

	/**
	 * 获得上架销售上网本和上网卡的数据(表)
	 * 
	 * @return
	 */
	public List<Document> getAllNetComputerDatas_new(int beginNum, int endNum) {
		List<Document> listData = new ArrayList<Document>();

		StringBuilder sql = new StringBuilder("");
		sql.append(" select pmaster.pri_productid,  ");
		sql.append(" pmaster.classcode, ");
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
		sql.append(" pmaster.productclass, ");
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
//		sql
//				.append(" (select t.productid from product_list t where t.ifgoogle = 1 and t.pri_productid = pmaster.pri_productid) productid, ");
//		sql
//				.append(" (select tt.price_market from product_list tt where tt.ifgoogle = 1 and tt.pri_productid = pmaster.pri_productid) price_market, ");
		sql
				.append(" (select min(t.price_net) from product_list t where pmaster.pri_productid=t.pri_productid and t.ifdelete = 0 and t.ifvalid = 1 ) minNetPrice, ");
		sql
				.append(" (select max(t.price_net) from product_list t where pmaster.pri_productid=t.pri_productid and t.ifdelete = 0 and t.ifvalid = 1 ) maxNetPrice, ");
		sql
				.append(" (select count(*) from user_message m where m.productid = pmaster.pri_productid and m.message_type = 1 and m.ifdisp = 1) recommendCount, ");
		sql
				.append(" (select count(*) from user_message m where m.productid = pmaster.pri_productid and m.message_type = 2 and m.ifdisp = 1) askCount,  ");
		sql.append(" decode(pmaster.productclass,104,f_get_product_pic(productid, 104),f_get_product_pic(productid, 105)) photo_name ");
		sql.append(" from product_master pmaster ");
		sql
				.append(" join (select m.* from( select t.*,row_number() over(partition by t.pri_productid order by t.ifgoogle desc) as rn from product_list t where t.ifdelete = 0 and t.ifvalid=1 ) m where m.rn=1) n on pmaster.pri_productid = n.pri_productid ");
		sql.append(" where (pmaster.classcode like '104%' or pmaster.classcode like '105%')  ");
		sql.append(" and  n.ifvalid = 1  ");
		sql.append(" and n.ifgift = 0 ");
//		sql.append(" and plist.ifgoogle = 1 ");
		sql.append(" and pmaster.ifdelete = 0  ");
		sql.append(" and pmaster.comp_id = 1 ");

		IDAO dao = this.getDao();

		List data = dao.getCountQuery(sql.toString(), endNum, beginNum);

		if (data != null && data.size() > 0) {
			for (Object obj : data) {
				Map entry = (Map) obj;
				if (entry != null) {
					Document doc = buildNetComputerIndexDocument(entry);
					if (doc != null) {
						listData.add(doc);
					}
				}
			}
		}
		return listData;
	}
	
	/**
	 * 获得上架销售未索引的上网本和上网卡的数量
	 * 
	 * @return
	 */
	public int getUnindexedNetComputerNums() {
		StringBuilder sql = new StringBuilder("");
		sql.append(" select count(1) ");                                                                        
		sql.append(" from product_master pmaster ");
		sql.append(" join (select m.* from( select t.*,row_number() over(partition by t.pri_productid order by t.ifgoogle desc) as rn from product_list t ) m where m.rn=1) n on pmaster.pri_productid = n.pri_productid ");
		sql.append(" where (pmaster.classcode like '104%' or pmaster.classcode like '105%') ");
		sql.append(" and n.ifvalid = 1 ");
		sql.append(" and pmaster.ifdelete = 0  ");
		sql.append(" and pmaster.ifindex = 0 ");
		sql.append(" and pmaster.comp_id = 1 ");
		IDAO dao = this.getDao();
		return dao.getQueryInt(sql.toString());
	}

	/**
	 * 获得上架销售未索引的上网本和上网卡的数据(视图)
	 * 
	 * @return
	 */
	public List<Document> getUnindexedNetComputerDatas(int beginNum, int endNum) {
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
		sql.append("  from V_NOTEBOOK_INDEX t      ");
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
					Document doc = buildNetComputerIndexDocument(entry);
					if (doc != null) {
						listData.add(doc);
					}
				}
			}
		}
		return listData;
	}
	
	/**
	 * 获得上架销售未索引的上网本和上网卡的数据(表)
	 * 
	 * @return
	 */
	public List<Document> getUnindexedNetComputerDatas_new(int beginNum, int endNum) {
		List<Document> listData = new ArrayList<Document>();

		StringBuilder sql = new StringBuilder("");
		sql.append(" select pmaster.pri_productid,  ");
		sql.append(" pmaster.classcode, ");
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
		sql.append(" pmaster.productclass, ");
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
//		sql
//				.append(" (select t.productid from product_list t where t.ifgoogle = 1 and t.pri_productid = pmaster.pri_productid) productid, ");
//		sql
//				.append(" (select tt.price_market from product_list tt where tt.ifgoogle = 1 and tt.pri_productid = pmaster.pri_productid) price_market, ");
		sql
				.append(" (select min(t.price_net) from product_list t where pmaster.pri_productid=t.pri_productid and t.ifdelete = 0 and t.ifvalid = 1 ) minNetPrice, ");
		sql
				.append(" (select max(t.price_net) from product_list t where pmaster.pri_productid=t.pri_productid and t.ifdelete = 0 and t.ifvalid = 1 ) maxNetPrice, ");
		sql
				.append(" (select count(*) from user_message m where m.productid = pmaster.pri_productid and m.message_type = 1 and m.ifdisp = 1) recommendCount, ");
		sql
				.append(" (select count(*) from user_message m where m.productid = pmaster.pri_productid and m.message_type = 2 and m.ifdisp = 1) askCount,  ");
		sql.append(" decode(pmaster.productclass,104,f_get_product_pic(productid, 104),f_get_product_pic(productid, 105)) photo_name ");
		sql.append(" from product_master pmaster ");
		sql
				.append(" join (select m.* from( select t.*,row_number() over(partition by t.pri_productid order by t.ifgoogle desc) as rn from product_list t where t.ifdelete = 0 and t.ifvalid=1 ) m where m.rn=1) n on pmaster.pri_productid = n.pri_productid ");
		sql.append(" where (pmaster.classcode like '104%' or pmaster.classcode like '105%') ");
		sql.append(" and  n.ifvalid = 1  ");
		sql.append(" and n.ifgift = 0 ");
//		sql.append(" and plist.ifgoogle = 1 ");
		sql.append(" and pmaster.ifdelete = 0  ");
		sql.append(" and pmaster.ifindex = 0 ");
		sql.append(" and pmaster.comp_id = 1 ");

		IDAO dao = this.getDao();

		List data = dao.getCountQuery(sql.toString(), endNum, beginNum);

		if (data != null && data.size() > 0) {
			for (Object obj : data) {
				Map entry = (Map) obj;
				if (entry != null) {
					Document doc = buildNetComputerIndexDocument(entry);
					if (doc != null) {
						listData.add(doc);
					}
				}
			}
		}
		return listData;
	}

	/**
	 * 获得已删除的上网本和上网卡的数量
	 * 
	 * @return
	 */
	public int getDeletedNetComputerNums() {
		StringBuilder sql = new StringBuilder("");
		sql.append(" select count(1) ");                                                                        
		sql.append(" from product_master pmaster ");
		sql.append(" where (pmaster.classcode like '104%' or pmaster.classcode like '105%') ");
		sql.append(" and pmaster.ifindex = 0 ");
		sql.append(" and pmaster.ifdelete = 1  ");
		sql.append(" and pmaster.comp_id = 1 ");

		IDAO dao = this.getDao();
		return dao.getQueryInt(sql.toString());
	}

	/**
	 * 获得已删除的上网本和上网卡的数据(视图)
	 * 
	 * @return
	 */
	public List<Document> getDeletedNetComputerDatas(int beginNum, int endNum) {
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
		sql.append("  from V_NOTEBOOK_INDEX t      ");
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
					Document doc = buildNetComputerIndexDocument(entry);
					if (doc != null) {
						listData.add(doc);
					}
				}
			}
		}
		return listData;
	}
	
	/**
	 * 获得已删除的上网本和上网卡的数据(表)
	 * 
	 * @return
	 */
	public List<Document> getDeletedNetComputerDatas_new(int beginNum, int endNum) {
		List<Document> listData = new ArrayList<Document>();

		StringBuilder sql = new StringBuilder("");
		sql.append(" select pmaster.pri_productid,  ");
		sql.append(" pmaster.classcode, ");
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
		sql.append(" pmaster.productclass, ");
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
//		sql
//				.append(" (select t.productid from product_list t where t.ifgoogle = 1 and t.pri_productid = pmaster.pri_productid) productid, ");
//		sql
//				.append(" (select tt.price_market from product_list tt where tt.ifgoogle = 1 and tt.pri_productid = pmaster.pri_productid) price_market, ");
		sql
				.append(" (select min(t.price_net) from product_list t where pmaster.pri_productid=t.pri_productid and t.ifdelete = 0 and t.ifvalid = 1 ) minNetPrice, ");
		sql
				.append(" (select max(t.price_net) from product_list t where pmaster.pri_productid=t.pri_productid and t.ifdelete = 0 and t.ifvalid = 1 ) maxNetPrice, ");
		sql
				.append(" (select count(*) from user_message m where m.productid = pmaster.pri_productid and m.message_type = 1 and m.ifdisp = 1) recommendCount, ");
		sql
				.append(" (select count(*) from user_message m where m.productid = pmaster.pri_productid and m.message_type = 2 and m.ifdisp = 1) askCount,  ");
		sql.append(" decode(pmaster.productclass,104,f_get_product_pic(productid, 104),f_get_product_pic(productid, 105)) photo_name ");
		sql.append(" from product_master pmaster ");
		sql
				.append(" join (select m.* from( select t.*,row_number() over(partition by t.pri_productid order by t.ifgoogle desc) as rn from product_list t where t.ifdelete = 0 and t.ifvalid=1 ) m where m.rn=1) n on pmaster.pri_productid = n.pri_productid ");
		sql.append(" where (pmaster.classcode like '104%' or pmaster.classcode like '105%')  ");
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
					Document doc = buildNetComputerIndexDocument(entry);
					if (doc != null) {
						listData.add(doc);
					}
				}
			}
		}
		return listData;
	}
	
	/**
	 * 获得已下架的上网本和上网卡的数量
	 * 
	 * @return
	 */
	public int getUnSaleNetComputerNums() {
		StringBuilder sql = new StringBuilder("");
		sql.append(" select count(1)  ");
		sql.append(" from v_notebook_index t ");
		sql.append("  where (t.down_time < sysdate and t.comp_id = 1) or ");
		sql.append("  (t.ifuse = 0 and t.comp_id = 1) ");
		IDAO dao = this.getDao();
		return dao.getQueryInt(sql.toString());
	}
	
	/**
	 * 获得已下架的上网本和上网卡的数据(视图)
	 * 
	 * @return
	 */
	public List<Document> getUnSaleNetComputerDatas(int beginNum, int endNum) {
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
		//sql.append("       MEMO,                  ");
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
		sql.append("  from v_notebook_index t      ");
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
		//sql.append("       MEMO,                  ");
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
		sql.append("  from v_notebook_index tt      ");
		sql.append("  where tt.ifuse = 0 ");
		sql.append("  and tt.comp_id = 1 ");

		IDAO dao = this.getDao();

		List data = dao.getCountQuery(sql.toString(), endNum, beginNum);

		if (data != null && data.size() > 0) {
			for (Object obj : data) {
				Map entry = (Map) obj;
				if (entry != null) {
					Document doc = buildNetComputerIndexDocument(entry);
					if (doc != null) {
						listData.add(doc);
					}
				}
			}
		}
		return listData;
	}
	
	/**
	 * 获得已下架的上网本和上网卡的数据(表)
	 * 
	 * @return
	 */
	public List<Document> getUnSaleNetComputerDatas_new(int beginNum, int endNum) {
		List<Document> listData = new ArrayList<Document>();

		StringBuilder sql = new StringBuilder("");
		sql.append(" select t.productid, ");
		sql.append(" t.productno, ");
		sql.append(" t.productname, ");
		sql.append(" t.promotion, ");
		sql.append(" t.brand, ");
		sql.append(" (select d.valuename ");
		sql.append(" from dict_basetype_value d ");
		sql.append(" where d.valueid = t.brand) brand_name, ");
		sql.append(" t.price_net, ");
		sql.append(" t.price_market, ");
		sql.append(" t.price_set, ");
		sql.append(" t.ifset, ");
		sql.append(" t.ifuse, ");
		sql.append(" t.ifvalid, ");
		sql.append(" t.ifscore, ");
		sql.append(" t.up_time, ");
		sql.append(" t.down_time, ");
		sql.append(" t.keywords, ");
		sql.append(" t.memo, ");
		sql.append(" t.describe, ");
		sql.append(" t.create_dt, ");
		sql.append(" t.ifindex, ");
		sql.append(" t.price_standby, ");
		sql.append(" t.valid_time, ");
		sql.append(" t.ifdelete, ");
		sql.append(" t.productmodel, ");
		sql.append(" t.sale_count, ");
		sql.append(" f_get_product_pic(t.productid, 2) photo_name, ");
		sql.append(" a.market_time ");
		sql.append(" from product_list t,product_fitting_attr a ");
		sql.append(" where (t.productclass = 104 or t.productclass = 105) and t.productid = a.productid and t.down_time < sysdate and t.comp_id = 1 ");
		sql.append(" or ");
		sql.append(" (t.productclass = 104 or t.productclass = 105) and t.productid = a.productid and t.ifuse = 0 and t.comp_id = 1");
		IDAO dao = this.getDao();

		List data = dao.getCountQuery(sql.toString(), endNum, beginNum);

		if (data != null && data.size() > 0) {
			for (Object obj : data) {
				Map entry = (Map) obj;
				if (entry != null) {
					Document doc = buildNetComputerIndexDocument(entry);
					if (doc != null) {
						listData.add(doc);
					}
				}
			}
		}
		return listData;
	}

	/**
	 * 获得上架销售上网本和上网卡的数据(视图)
	 * 
	 * @return
	 */
	public Document getAllNetComputerDatas(int peiJianid) {
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
			sql.append("  from v_notebook_index t      ");
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
					Document doc = buildNetComputerIndexDocument(entry);
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
	 * 获得上架销售上网本和上网卡的数据(表)
	 * 
	 * @return
	 */
	public Document getAllNetComputerDatas_new(int peiJianid) {
		if (peiJianid > 0) {
			StringBuilder sql = new StringBuilder("");
			sql.append(" select pmaster.pri_productid,  ");
			sql.append(" pmaster.classcode, ");
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
			sql.append(" pmaster.productclass, ");
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
			sql.append(" decode(pmaster.productclass,104,f_get_product_pic(productid, 104),f_get_product_pic(productid, 105)) photo_name ");
			sql.append(" from product_master pmaster ");
			sql
					.append(" join (select m.* from( select t.*,row_number() over(partition by t.pri_productid order by t.ifgoogle desc) as rn from product_list t where t.ifdelete = 0 and t.ifvalid=1 ) m where m.rn=1) n on pmaster.pri_productid = n.pri_productid ");
			sql.append(" where (pmaster.classcode like '104%' or pmaster.classcode like '105%') ");
			sql.append(" and  n.ifvalid = 1  ");
			sql.append(" and n.ifgift = 0 ");
//			sql.append(" and plist.ifgoogle = 1 ");
			sql.append(" and pmaster.ifdelete = 0  ");
			sql.append(" and pmaster.pri_productid = ?  "); 
			sql.append(" and pmaster.comp_id = 1 ");
			
			IDAO dao = this.getDao();
			Object[] args = { peiJianid };
			int[] argTypes = { java.sql.Types.INTEGER };
			
			List data = dao.getQuery(sql.toString(), args, argTypes);
			
			if (data != null && data.size() > 0) {
				Object obj = data.get(0);
				Map entry = (Map) obj;
				if (entry != null) {
					Document doc = buildNetComputerIndexDocument(entry);
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
	 * 构建一个NetComputer document
	 * 
	 * @param entry
	 * @return
	 */
	private Document buildNetComputerIndexDocument(Map entry) {
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
					}
				}
			}

			String colorStr = color.toString();
			int len = colorStr.length();
			if( len > 0 ) {
				if( colorStr.substring(len - 1, len).equals(",") )
					colorStr = colorStr.substring(0, colorStr.length() - 1);
					
			}
			
			String classcode = StringUtil
					.escapeNullObject(RowSetTypeConverter.getString(entry
							.get("classcode")));
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
			String net_name = StringUtil.escapeNullObject(RowSetTypeConverter
					.getString(entry.get("net_name")));
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
			String brand_name = StringUtil.escapeNullObject(
					RowSetTypeConverter.getString(entry.get("brand_name")))
					.toLowerCase();
			Integer brandv = RowSetTypeConverter.getInteger(entry.get("brand"));
			Integer productclass = RowSetTypeConverter.getInteger(entry
					.get("productclass"));
			
			return NetComputerDocument.getDocument(pri_productid, classcode, brandv.intValue(), brand_name, productModel, product_para,
					productname, describe, promotion, sale_count, market_time, rebate, keywords, net_name, camera_pixel, 
					screen_size, productid, price_market, priceNetMin, priceNetMax, 
					colorStr, ifuse.intValue(), recommendCount.intValue(), askCount.intValue(), photo_name, shape, productclass);
		} else {
			return null;
		}
	}
	
	
	/**
	 * 更新指定上网本或上网卡为已被索引状态
	 * 
	 * @param peiJanid
	 * @return
	 */
	public int setANetComputerIndexed(int peiJanid) {
		if (peiJanid > 0) {
			StringBuilder sql = new StringBuilder();
			sql
					.append(" update product_master pl set pl.ifindex = 1 where pl.pri_productid = ? ");
			sql
					.append("   and pl.comp_id = 1 ");
			IDAO dao = this.getDao();
			Object[] args = { peiJanid };
			int[] argTypes = { java.sql.Types.INTEGER };
			
			dao.executeUpdate(sql.toString(), args, argTypes);
		}
		return 0;
	}

	
	/**
	 * 更新所有的上网本或上网卡为已被索引状态
	 * 
	 * @return
	 */
	public int setAlldNetComputerIndexed() {
		StringBuilder sql = new StringBuilder();
		sql.append(" update  product_master t set t.ifindex = 1 ");
		sql.append(" where   t.ifindex = 0 and (t.classcode like '104%' or t.classcode like '105%') ");
		sql.append(" and t.comp_id = 1 ");
		IDAO dao = this.getDao();
		return dao.executeUpdate(sql.toString());
	}
	
	/**
	 * 更新未索引的上网本或上网卡为已被索引状态
	 * 
	 * @return
	 */
	public int setUnIndexedNetComputerIndexed() {
		StringBuilder sql = new StringBuilder();
		sql.append(" update  product_master t set t.ifindex = 1 ");
		
//		sql.append(" where t.up_time<sysdate ");
//		sql.append(" and t.down_time>sysdate ");
		sql.append(" where t.ifvalid=1 ");
		sql.append(" and t.ifdelete = 0 and  t.ifindex = 0 and (t.classcode like '104%' or t.classcode like '105%') ");
		sql.append(" and t.comp_id = 1 ");
		
		IDAO dao = this.getDao();
		return dao.executeUpdate(sql.toString());
	}
	
	/**
	 * 更新已删除的上网本或上网卡为已被索引状态
	 * 
	 * @return
	 */
	public int setDeletedNetComputerIndexed() {
		StringBuilder sql = new StringBuilder();
		sql
				.append(" update  product_master t set t.ifindex = 1 ");
		
//		sql.append(" where t.up_time<sysdate ");
//		sql.append(" and t.down_time>sysdate ");
		sql.append(" and t.ifvalid=1 ");
		sql.append(" and t.ifdelete = 1 and  t.ifindex = 0 and (t.classcode like '104%' or t.classcode like '105%') ");
		sql.append(" and t.comp_id = 1 ");
		
		IDAO dao = this.getDao();
		
		return dao.executeUpdate(sql.toString());
	}
	
	public static void main(String arg[]) {
		NetComputerDataService peijianDataService = new NetComputerDataService();
		int dd = peijianDataService.getAllNetComputerNums();
	}
}
