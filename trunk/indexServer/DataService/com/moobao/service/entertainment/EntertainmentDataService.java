package com.moobao.service.entertainment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.lucene.document.Document;

import com.apply.b2b.cms.base.IDAO;
import com.apply.b2b.cms.util.RowSetTypeConverter;
import com.apply.b2b.cms.util.StringUtil;
import com.moobao.indexser.document.EntertainmentDocument;
import com.moobao.service.DataBaseService;

public class EntertainmentDataService extends DataBaseService {

	/**
	 * 获得所有状态为1的"娱乐中心"的数量
	 * 
	 * @return
	 */
	public int getAllEntertainmentNums() {
		StringBuilder sql = new StringBuilder("");
		sql.append(" select count(1) ");
		sql.append(" from sp_resource ent ");
		sql.append(" where ");
		sql.append(" ent.status = 1 ");
		IDAO dao = this.getDao();
		return dao.getQueryInt(sql.toString());
	}
	
	/**
	 * 获得所有状态为1的"娱乐中心"信息(视图)
	 * @param beginNum
	 * @param endNum
	 * @return
	 */
	public List<Document> getAllEntertainmentDatas(int beginNum, int endNum) {
		List<Document> listData = new ArrayList<Document>();
		if (endNum >= beginNum && endNum > 0) {
			StringBuilder sql = new StringBuilder("");
			sql.append("select id, ");
			sql.append("res_no, ");
			sql.append("res_name, ");
			sql.append("cp_id, ");
			sql.append("description, ");
			sql.append("cat_id, ");
			sql.append("slave_id, ");
			sql.append("valuename, ");
			sql.append("tag, ");
			sql.append("back_tag, ");
			sql.append("down_count, ");
			sql.append("author, ");
			sql.append("res_path, ");
			sql.append("pic_path, ");
			sql.append("main_pic, ");
			sql.append("min_pic, ");
			sql.append("status, ");
			sql.append("create_dt, ");
			sql.append("last_opertime ");
			sql.append(" from v_entertainment_index ent ");
			sql.append(" where ent.status = 1 ");
			
			IDAO dao = this.getDao();
			
			List data = dao.getCountQuery(sql.toString(), endNum, beginNum);			
			if (data != null && data.size() > 0) {
				for (Object obj : data) {
					Map entry = (Map) obj;
					if (entry != null) {
						Document doc = buildEntertainmentIndexDocument(entry);
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
	 * 获得所有状态为1的"娱乐中心"信息(表)
	 * @param beginNum
	 * @param endNum
	 * @return
	 */
	public List<Document> getAllEntertainmentDatas_new(int beginNum, int endNum) {
		List<Document> listData = new ArrayList<Document>();
		if (endNum >= beginNum && endNum > 0) {
			StringBuilder sql = new StringBuilder("");
			sql.append(" select sr.id, sr.res_no, sr.res_name, ");
			sql.append(" sr.cp_id, sr.description, sr.cat_id, ");
			sql.append(" sr.slave_id,dbv.valuename, sr.tag, ");
			sql.append(" sr.back_tag, sr.down_count, sr.author, ");
			sql.append(" sr.res_path, sr.pic_path, sr.main_pic, ");
			sql.append(" sr.min_pic, sr.status, sr.create_dt, ");
			sql.append(" sr.last_opertime, ");
			sql.append(" sr.ifindex ");
			sql.append(" from sp_resource sr join dict_basetype_value dbv ");
			sql.append(" on sr.slave_id=dbv.valueid ");
			sql.append(" where sr.status = 1 ");
			
			IDAO dao = this.getDao();
			
			List data = dao.getCountQuery(sql.toString(), endNum, beginNum);			
			if (data != null && data.size() > 0) {
				for (Object obj : data) {
					Map entry = (Map) obj;
					if (entry != null) {
						Document doc = buildEntertainmentIndexDocument(entry);
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
	 * 获得status=1, ifindex=0的"娱乐中心"的数量(增量更新)
	 * 
	 * @return
	 */
	public int getAddEntertainmentNums() {
		StringBuilder sql = new StringBuilder("");
		sql.append(" select count(1) ");
		sql.append(" from sp_resource ent ");
		sql.append(" where ");
		sql.append(" ent.status = 1 and ent.ifindex = 0");
		IDAO dao = this.getDao();
		return dao.getQueryInt(sql.toString());
	}
	
	/**
	 * 获得status=1, ifindex=0的"娱乐中心"信息(视图)
	 * @param beginNum
	 * @param endNum
	 * @return
	 */
	public List<Document> getAddEntertainmentDatas(int beginNum, int endNum) {
		List<Document> listData = new ArrayList<Document>();
		if (endNum >= beginNum && endNum > 0) {
			StringBuilder sql = new StringBuilder("");
			sql.append("select id, ");
			sql.append("res_no, ");
			sql.append("res_name, ");
			sql.append("cp_id, ");
			sql.append("description, ");
			sql.append("cat_id, ");
			sql.append("slave_id, ");
			sql.append("valuename, ");
			sql.append("tag, ");
			sql.append("back_tag, ");
			sql.append("down_count, ");
			sql.append("author, ");
			sql.append("res_path, ");
			sql.append("pic_path, ");
			sql.append("main_pic, ");
			sql.append("min_pic, ");
			sql.append("status, ");
			sql.append("create_dt, ");
			sql.append("last_opertime ");
			sql.append(" from v_entertainment_index ent ");
			sql.append(" where ent.status = 1 and ent.ifindex = 0");
			
			IDAO dao = this.getDao();
			
			List data = dao.getCountQuery(sql.toString(), endNum, beginNum);
			
			if (data != null && data.size() > 0) {
				for (Object obj : data) {
					Map entry = (Map) obj;
					if (entry != null) {
						Document doc = buildEntertainmentIndexDocument(entry);
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
	 * 获得status=1, ifindex=0的"娱乐中心"信息(表)
	 * @param beginNum
	 * @param endNum
	 * @return
	 */
	public List<Document> getAddEntertainmentDatas_new(int beginNum, int endNum) {
		List<Document> listData = new ArrayList<Document>();
		if (endNum >= beginNum && endNum > 0) {
			StringBuilder sql = new StringBuilder("");
			sql.append(" select sr.id, sr.res_no, sr.res_name, ");
			sql.append(" sr.cp_id, sr.description, sr.cat_id, ");
			sql.append(" sr.slave_id,dbv.valuename, sr.tag, ");
			sql.append(" sr.back_tag, sr.down_count, sr.author, ");
			sql.append(" sr.res_path, sr.pic_path, sr.main_pic, ");
			sql.append(" sr.min_pic, sr.status, sr.create_dt, ");
			sql.append(" sr.last_opertime, ");
			sql.append(" sr.ifindex ");
			sql.append(" from sp_resource sr join dict_basetype_value dbv ");
			sql.append(" on sr.slave_id=dbv.valueid ");
			sql.append(" where sr.status = 1 and sr.ifindex = 0");
			
			IDAO dao = this.getDao();
			
			List data = dao.getCountQuery(sql.toString(), endNum, beginNum);
			
			if (data != null && data.size() > 0) {
				for (Object obj : data) {
					Map entry = (Map) obj;
					if (entry != null) {
						Document doc = buildEntertainmentIndexDocument(entry);
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
	 * 获得status=0的"娱乐中心"的数量(删除索引)
	 * 
	 * @return
	 */
	public int getDelEntertainmentNums() {
		StringBuilder sql = new StringBuilder("");
		sql.append(" select count(1) ");
		sql.append(" from sp_resource ent ");
		sql.append(" where ");
		sql.append(" ent.status = 0 ");
		IDAO dao = this.getDao();
		return dao.getQueryInt(sql.toString());
	}
	
	/**
	 * 获得status=0的"娱乐中心"信息(视图)
	 * @param beginNum
	 * @param endNum
	 * @return
	 */
	public List<Document> getDelEntertainmentDatas(int beginNum, int endNum) {
		List<Document> listData = new ArrayList<Document>();
		if (endNum >= beginNum && endNum > 0) {
			StringBuilder sql = new StringBuilder("");
			sql.append("select id, ");
			sql.append("res_no, ");
			sql.append("res_name, ");
			sql.append("cp_id, ");
			sql.append("description, ");
			sql.append("cat_id, ");
			sql.append("slave_id, ");
			sql.append("valuename, ");
			sql.append("tag, ");
			sql.append("back_tag, ");
			sql.append("down_count, ");
			sql.append("author, ");
			sql.append("res_path, ");
			sql.append("pic_path, ");
			sql.append("main_pic, ");
			sql.append("min_pic, ");
			sql.append("status, ");
			sql.append("create_dt, ");
			sql.append("last_opertime ");
			sql.append(" from v_entertainment_index ent ");
			sql.append(" where ent.status = 0 ");
			
			IDAO dao = this.getDao();
			
			List data = dao.getCountQuery(sql.toString(), endNum, beginNum);
			
			if (data != null && data.size() > 0) {
				for (Object obj : data) {
					Map entry = (Map) obj;
					if (entry != null) {
						Document doc = buildEntertainmentIndexDocument(entry);
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
	 * 获得status=0的"娱乐中心"信息(表)
	 * @param beginNum
	 * @param endNum
	 * @return
	 */
	public List<Document> getDelEntertainmentDatas_new(int beginNum, int endNum) {
		List<Document> listData = new ArrayList<Document>();
		if (endNum >= beginNum && endNum > 0) {
			StringBuilder sql = new StringBuilder("");
			sql.append(" select sr.id, sr.res_no, sr.res_name, ");
			sql.append(" sr.cp_id, sr.description, sr.cat_id, ");
			sql.append(" sr.slave_id,dbv.valuename, sr.tag, ");
			sql.append(" sr.back_tag, sr.down_count, sr.author, ");
			sql.append(" sr.res_path, sr.pic_path, sr.main_pic, ");
			sql.append(" sr.min_pic, sr.status, sr.create_dt, ");
			sql.append(" sr.last_opertime, ");
			sql.append(" sr.ifindex ");
			sql.append(" from sp_resource sr join dict_basetype_value dbv ");
			sql.append(" on sr.slave_id=dbv.valueid ");
			sql.append(" where sr.status = 0 ");
			
			IDAO dao = this.getDao();
			
			List data = dao.getCountQuery(sql.toString(), endNum, beginNum);
			
			if (data != null && data.size() > 0) {
				for (Object obj : data) {
					Map entry = (Map) obj;
					if (entry != null) {
						Document doc = buildEntertainmentIndexDocument(entry);
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
	 * 获得指定的娱乐信息(视图)
	 * @param resId
	 * @return
	 */
	public Document getAEntertaimentData(int resId) {
		if (resId > 0) {
			StringBuilder sql = new StringBuilder("");
			sql.append("select id, ");
			sql.append("res_no, ");
			sql.append("res_name, ");
			sql.append("cp_id, ");
			sql.append("description, ");
			sql.append("cat_id, ");
			sql.append("slave_id, ");
			sql.append("valuename, ");
			sql.append("tag, ");
			sql.append("back_tag, ");
			sql.append("down_count, ");
			sql.append("author, ");
			sql.append("res_path, ");
			sql.append("pic_path, ");
			sql.append("main_pic, ");
			sql.append("min_pic, ");
			sql.append("status, ");
			sql.append("create_dt, ");
			sql.append("last_opertime ");
			sql.append(" from v_entertainment_index ent ");
			sql.append(" where ent.status = 1 and ent.id = ?");

			IDAO dao = this.getDao();
			Object[] args = { resId };
			int[] argTypes = { java.sql.Types.INTEGER };

			List data = dao.getQuery(sql.toString(), args, argTypes);

			if (data != null && data.size() > 0) {
				Object obj = data.get(0);
				Map entry = (Map) obj;
				if (entry != null) {
					Document doc = buildEntertainmentIndexDocument(entry);
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
	 * 获得指定的娱乐信息(表)
	 * @param resId
	 * @return
	 */
	public Document getAEntertaimentData_new(int resId) {
		if (resId > 0) {
			StringBuilder sql = new StringBuilder("");
			sql.append(" select sr.id, sr.res_no, sr.res_name, ");
			sql.append(" sr.cp_id, sr.description, sr.cat_id, ");
			sql.append(" sr.slave_id,dbv.valuename, sr.tag, ");
			sql.append(" sr.back_tag, sr.down_count, sr.author, ");
			sql.append(" sr.res_path, sr.pic_path, sr.main_pic, ");
			sql.append(" sr.min_pic, sr.status, sr.create_dt, ");
			sql.append(" sr.last_opertime, ");
			sql.append(" sr.ifindex ");
			sql.append(" from sp_resource sr join dict_basetype_value dbv ");
			sql.append(" on sr.slave_id=dbv.valueid ");
			sql.append(" where sr.status = 1 and sr.id = ?");

			IDAO dao = this.getDao();
			Object[] args = { resId };
			int[] argTypes = { java.sql.Types.INTEGER };

			List data = dao.getQuery(sql.toString(), args, argTypes);

			if (data != null && data.size() > 0) {
				Object obj = data.get(0);
				Map entry = (Map) obj;
				if (entry != null) {
					Document doc = buildEntertainmentIndexDocument(entry);
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
	 * 组合指定娱乐信息所搭配的手机型号.
	 * @param resId
	 * @return
	 */
	public List<String> getEntertaimentModel(int resId) {
		
		List<String> list = new ArrayList<String>();
		List<String> listBrand = new ArrayList<String>();   //品牌ID
		List<String> listCnBrand = new ArrayList<String>(); //品牌的汉字形式。
		StringBuffer model = new StringBuffer();
		StringBuffer brand = new StringBuffer();
		StringBuffer cn_brand = new StringBuffer();
		
		if (resId > 0) {
			StringBuilder sql = new StringBuilder("");
			sql.append("select sr.id, ");
			sql.append("seri.mobile_model, ");
			sql.append("seri.brand, ");
			sql.append("seri.cn_brand ");
			
			sql.append("from( select res.id, res.cat_id, restt.file_name  ");
			sql.append("from  sp_resource res ");
			sql.append("join  sp_res_file restt ");
			sql.append("on    res.id = restt.res_id ) sr ");
			sql.append("join   sp_mobile_series seri ");
			sql.append("on     sr.cat_id = seri.cat_id and ");
			sql.append("sr.file_name = seri.file_name where sr.id = ?");

			IDAO dao = this.getDao();
			Object[] args = { resId };
			int[] argTypes = { java.sql.Types.INTEGER };

			List data = dao.getQuery(sql.toString(), args, argTypes);

			
			if (data != null && data.size() > 0) {
				for (Object obj : data) {
					Map entry = (Map) obj;
					if (entry != null) {
						model.append( entry.get( "mobile_model" ) + ", " );
						//brand.append( entry.get( "brand" ) + ", " );
						//cn_brand.append( entry.get( "cn_brand" ) + ", " );
						listBrand.add( entry.get( "brand" ).toString() );
						listCnBrand.add( entry.get( "cn_brand" ).toString() );
					}
				}
				
				//将listBrand与listCnBrand去重.
				String b1 = "", b2 = "";
				if( listBrand != null && listBrand.size() > 0 ) {
					for( int i = 0; i < listBrand.size() - 1; i ++ ) {
						b1 = listBrand.get( i );
						for( int j = i + 1; j < listBrand.size(); j ++  ) {
							b2 = listBrand.get( j );
							if( b1.equals( b2 ) ) {
								listBrand.remove( j );
							    listCnBrand.remove( j );
							    j --;
							}
						}
					}
					
					//组合成一个字符串.
					for( int v = 0; v < listBrand.size(); v ++  ) {
						brand.append( listBrand.get( v ) + ", " );
						cn_brand.append( listCnBrand.get( v ) + ", " );
					}
				}
				
				list.add( model.toString() );
				list.add( brand.toString() );
				list.add( cn_brand.toString() );
			}
		}
		return list;
	}
	
	
	
	
	/**
	 * 构建一个Entertainment document
	 * 
	 * @param entry
	 * @return
	 */
	private Document buildEntertainmentIndexDocument(Map entry) {
		
		String model = "", brand = "", cn_brand = "";
		if (entry != null) {

			Integer entID = RowSetTypeConverter.getInteger(
					entry.get("id"));
			
			//提取出相对应的手机型号.
			List<String> listData = getEntertaimentModel( entID );
			if( listData != null && listData.size() > 0 ) {
				model = listData.get( 0 );
				brand = listData.get( 1 );
				cn_brand = listData.get( 2 );
			}
			
			String entResNo = StringUtil
			.escapeNullObject(RowSetTypeConverter.getString(entry
					.get("res_no")));
			String entResName = StringUtil
			.escapeNullObject(RowSetTypeConverter.getString(entry
					.get("res_name")));
			Integer entCpID = RowSetTypeConverter.getInteger(entry
					.get("cp_id"));
			
			String description = StringUtil
			.escapeNullObject(RowSetTypeConverter.getString(entry
					.get("description")));
			Integer entCatID = RowSetTypeConverter.getInteger(entry
					.get("cat_id"));
			Integer entSlaveID = RowSetTypeConverter.getInteger(entry
					.get("slave_id"));
			
			String entSlaveName = StringUtil
			.escapeNullObject(RowSetTypeConverter.getString(entry
					.get("valuename")));

			String entTag = StringUtil
			.escapeNullObject(RowSetTypeConverter.getString(entry
					.get("tag")));
			String entBackTag = StringUtil
			.escapeNullObject(RowSetTypeConverter.getString(entry
					.get("back_tag")));
			Integer entDownCount = RowSetTypeConverter.getInteger(
					entry.get("down_count"));
			String entAuthor = StringUtil
			.escapeNullObject(RowSetTypeConverter.getString(entry
					.get("author")));
			String entRes_path = StringUtil
			.escapeNullObject(RowSetTypeConverter.getString(entry
					.get("res_path")));
			String entPicPath = StringUtil
			.escapeNullObject(RowSetTypeConverter.getString(entry
					.get("pic_path")));
			String entMainPic = StringUtil
			.escapeNullObject(RowSetTypeConverter.getString(entry
					.get("main_pic")));
			String entMinPic = StringUtil
			.escapeNullObject(RowSetTypeConverter.getString(entry
					.get("min_pic")));
			Integer entStatus = RowSetTypeConverter.getInteger(
					entry.get("status"));
			
			Date createTime = RowSetTypeConverter.getDate(entry.get("create_dt"));
			Date lastOperatorTime = RowSetTypeConverter.getDate(entry.get("last_opertime"));


			//处理型号
			String modelData = StringUtil
			.escapeNullObject(RowSetTypeConverter.getString(model));
			
			String brand1 = StringUtil
			.escapeNullObject(RowSetTypeConverter.getString(brand));
			
			String brand2 = StringUtil
			.escapeNullObject(RowSetTypeConverter.getString(cn_brand));
			
			
			
			return EntertainmentDocument.getDocument(entID, entResNo, entResName,
					entCpID, description, entCatID, entSlaveID, 
					entSlaveName, entTag, entBackTag, entDownCount,
					entAuthor, entRes_path, entPicPath, entMainPic, entMinPic,
					entStatus, createTime, lastOperatorTime, modelData, brand1, brand2);
		} else {
			return null;
		}
	}
	
	/**
	 * 更新指定娱乐信息为已被索引状态
	 * 
	 * @param entId
	 * @return Integer
	 */
	public int setAEntertainmentIndexed(int entId) {
		if (entId > 0) {
			StringBuilder sql = new StringBuilder();
			sql
					.append(" update sp_resource re set re.ifindex = 1 where re.id = ? ");
			IDAO dao = this.getDao();
			Object[] args = { entId };
			int[] argTypes = { java.sql.Types.INTEGER };
			
			dao.executeUpdate(sql.toString(), args, argTypes);
		}
		return 0;
	}

	
	/**
	 * 更新所有娱乐信息为已被索引状态
	 * @return Integer
	 */
	public int setAllEntertainmentIndexed() {
		StringBuilder sql = new StringBuilder();
		sql.append(" update  sp_resource re set re.ifindex = 1 ");
		sql.append(" where re.ifindex = 0 ");
		IDAO dao = this.getDao();
		return dao.executeUpdate(sql.toString());
	}
	
	/**
	 * 更新未索引娱乐信息为已被索引状态(增量)
	 * @return Integer
	 */
	public int setUnIndexEntertainmentIndexed() {
		StringBuilder sql = new StringBuilder();
		sql.append(" update sp_resource re set re.ifindex = 1 ");
		sql.append(" where re.status = 1 and re.ifindex = 0 ");
		IDAO dao = this.getDao();
		return dao.executeUpdate(sql.toString());
	}
	
	/**
	 * 更新已删除的娱乐信息为已被索引状态
	 * @return Integer
	 */
	public int setDelEntertainmentIndexed() {
		StringBuilder sql = new StringBuilder();
		sql.append(" update sp_resource re set re.ifindex = 1 ");
		sql.append(" where re.status = 0 ");
		IDAO dao = this.getDao();
		return dao.executeUpdate(sql.toString());
	}
	
	public static void main( String[] a ) {
		EntertainmentDataService ent = new EntertainmentDataService();
		System.out.println( ent.getAllEntertainmentNums() );
		List<Document> list = new ArrayList<Document>();
		list = ent.getAllEntertainmentDatas(1, 10);
	}
}
