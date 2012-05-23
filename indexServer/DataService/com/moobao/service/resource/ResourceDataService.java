package com.moobao.service.resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.lucene.document.Document;

import com.apply.b2b.cms.base.IDAO;
import com.apply.b2b.cms.util.RowSetTypeConverter;
import com.apply.b2b.cms.util.StringUtil;
import com.moobao.indexser.document.ResourceDocument;
import com.moobao.service.DataBaseService;

/**
 * 
 * @author wind
 * 
 */
public class ResourceDataService extends DataBaseService {
	/**
	 * 获得资讯数量
	 * 
	 * @return
	 */
	public int getAllResourceNums() {
		StringBuilder sql = new StringBuilder("");

		sql.append("select count(1) ");
		sql.append("  from article_list t ");
		sql.append(" where t.ifuse = 1 ");
		sql.append("	   and t.ifdelete = 0 ");

		IDAO dao = this.getDao();
		return dao.getQueryInt(sql.toString());
	}

	private int getArticleClassID(int id) {
		if (id == 1) {
			return 111001;
		} else if (id == 2) {
			return 111002;
		} else if (id == 3) {
			return 111003;
		} else if (id == 4) {
			return 111004;
		} else {
			return -1;
		}
	}

	/**
	 * 获得分类资讯数量 1: 新闻 2:评测 3:导购 4:行情
	 * 
	 * @return
	 */
	public int getResourceNumsByClassID(int cateID) {
		if (cateID > 0) {
			StringBuilder sql = new StringBuilder("");
			int classid = getArticleClassID(cateID);
			if (classid == -1) {
				return 0;
			} else {
				sql.append("select count(1) ");
				sql.append("  from article_list t ");
				sql.append(" where t.ifuse = 1 ");
				sql.append("	   and t.ifdelete = 0 and t.valueid = ? ");

				Object[] args = { classid };
				int[] argTypes = { java.sql.Types.INTEGER };

				IDAO dao = this.getDao();
				return dao.getQueryInt(sql.toString(), args, argTypes);
			}
		} else {
			return 0;
		}
	}

	/**
	 * 获得分类资讯数据 1: 新闻 2:评测 3:导购 4:行情(视图)
	 * 
	 * @param cateID
	 * @param beginNum
	 * @param endNum
	 * @return
	 */

	public List<Document> getResourceDatasByClassID(int cateID, int beginNum,
			int endNum) {
		List<Document> listData = new ArrayList<Document>();
		int classid = getArticleClassID(cateID);
		if (classid == -1) {
			return listData;
		} else {
			StringBuilder sql = new StringBuilder("");

			sql.append("select articleid,         ");
			sql.append("       brand,             ");
			sql.append("       brand_name,        ");
			//sql.append("       content,           ");
			sql.append("       create_dt,         ");
			sql.append("       describe,          ");
			sql.append("       digest,            ");
			sql.append("       ifdelete,          ");
			sql.append("       ifindex,           ");
			sql.append("       ifuse,             ");
			sql.append("       keywords,          ");
			sql.append("       labels,            ");
			sql.append("       photo_name,        ");
			sql.append("       picurl,            ");
			sql.append("       publish_time,      ");
			sql.append("       sourcenet,         ");
			sql.append("       sourceurl,         ");
			sql.append("       title,             ");
			sql.append("       valueid            ");
			sql.append("  from v_article_index t  ");
			sql.append(" where t.ifuse = 1        ");
			sql.append("   and t.ifdelete = 0  and t.valueid = ?    ");

			Object[] args = { classid };
			int[] argTypes = { java.sql.Types.INTEGER };
			IDAO dao = this.getDao();

			List data = dao.getCountQuery(sql.toString(), args, argTypes,
					endNum, beginNum);

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
			return listData;
		}
	}
	
	/**
	 * 获得分类资讯数据 1: 新闻 2:评测 3:导购 4:行情(表)
	 * 
	 * @param cateID
	 * @param beginNum
	 * @param endNum
	 * @return
	 */

	public List<Document> getResourceDatasByClassID_new(int cateID, int beginNum,
			int endNum) {
		List<Document> listData = new ArrayList<Document>();
		int classid = getArticleClassID(cateID);
		if (classid == -1) {
			return listData;
		} else {
			StringBuilder sql = new StringBuilder("");

			sql.append(" select t.articleid, ");
			sql.append(" t.valueid, ");
			sql.append(" t.brand, ");
			sql.append(" (select d.valuename from dict_basetype_value d where d.valueid = t.brand) brand_name, ");
			sql.append(" t.title, ");
			sql.append(" t.digest, ");
			sql.append(" t.describe, ");
			sql.append(" t.keywords, ");
			sql.append(" t.labels, ");
			//sql.append(" t.content, ");
			sql.append(" t.sourceurl, ");
			sql.append(" t.sourcenet, ");
			sql.append(" t.picurl, ");
			sql.append(" t.publish_time, ");
			sql.append(" t.create_dt, ");
			sql.append(" f_get_product_pic(t.articleid, 3) photo_name , ");
			sql.append(" t.ifuse, ");
			sql.append(" t.ifdelete, ");
			sql.append(" t.ifindex ");
			sql.append(" from article_list t ");
			sql.append(" where t.ifuse = 1        ");
			sql.append("   and t.ifdelete = 0  and t.valueid = ?    ");

			Object[] args = { classid };
			int[] argTypes = { java.sql.Types.INTEGER };
			IDAO dao = this.getDao();

			List data = dao.getCountQuery(sql.toString(), args, argTypes,
					endNum, beginNum);

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
			return listData;
		}
	}

	/**
	 * 获得资讯数据(视图)
	 * 
	 * @param beginNum
	 * @param endNum
	 * @return
	 */
	public List<Document> getAllResourceDatas(int beginNum, int endNum) {
		List<Document> listData = new ArrayList<Document>();

		StringBuilder sql = new StringBuilder("");

		sql.append("select articleid,         ");
		sql.append("       brand,             ");
		sql.append("       brand_name,        ");
		sql.append("       content,           ");
		sql.append("       create_dt,         ");
		sql.append("       describe,          ");
		sql.append("       digest,            ");
		sql.append("       ifdelete,          ");
		sql.append("       ifindex,           ");
		sql.append("       ifuse,             ");
		sql.append("       keywords,          ");
		sql.append("       labels,            ");
		sql.append("       photo_name,        ");
		sql.append("       picurl,            ");
		sql.append("       publish_time,      ");
		sql.append("       sourcenet,         ");
		sql.append("       sourceurl,         ");
		sql.append("       title,             ");
		sql.append("       valueid            ");
		sql.append("  from v_article_index t  ");
		sql.append(" where t.ifuse = 1        ");
		sql.append("   and t.ifdelete = 0     ");

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
		return listData;
	}
	
	/**
	 * 获得资讯数据(表)
	 * 
	 * @param beginNum
	 * @param endNum
	 * @return
	 */
	public List<Document> getAllResourceDatas_new(int beginNum, int endNum) {
		List<Document> listData = new ArrayList<Document>();

		StringBuilder sql = new StringBuilder("");

		sql.append(" select t.articleid, ");
		sql.append(" t.valueid, ");
		sql.append(" t.brand, ");
		sql.append(" (select d.valuename from dict_basetype_value d where d.valueid = t.brand) brand_name, ");
		sql.append(" t.title, ");
		sql.append(" t.digest, ");
		sql.append(" t.describe, ");
		sql.append(" t.keywords, ");
		sql.append(" t.labels, ");
		sql.append(" t.content, ");
		sql.append(" t.sourceurl, ");
		sql.append(" t.sourcenet, ");
		sql.append(" t.picurl, ");
		sql.append(" t.publish_time, ");
		sql.append(" t.create_dt, ");
		sql.append(" f_get_product_pic(t.articleid, 3) photo_name , ");
		sql.append(" t.ifuse, ");
		sql.append(" t.ifdelete, ");
		sql.append(" t.ifindex ");
		sql.append(" from article_list t ");
		sql.append(" where t.ifuse = 1        ");
		sql.append("   and t.ifdelete = 0     ");

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
		return listData;
	}

	/**
	 * 获得未索引资讯数量
	 * 
	 * @return
	 */
	public int getUnIndexedResourceNums() {
		StringBuilder sql = new StringBuilder("");

		sql.append("select count(1) ");
		sql.append("  from article_list t ");
		sql.append(" where t.ifuse = 1 ");
		sql.append(" and t.ifindex = 0	and t.ifdelete = 0 ");

		IDAO dao = this.getDao();
		return dao.getQueryInt(sql.toString());
	}

	/**
	 * 获得未索引资讯数据(视图)
	 * 
	 * @param beginNum
	 * @param endNum
	 * @return
	 */

	public List<Document> getUnIndexedResourceDatas(int beginNum, int endNum) {
		List<Document> listData = new ArrayList<Document>();

		StringBuilder sql = new StringBuilder("");

		sql.append("select articleid,         ");
		sql.append("       brand,             ");
		sql.append("       brand_name,        ");
		sql.append("       content,           ");
		sql.append("       create_dt,         ");
		sql.append("       describe,          ");
		sql.append("       digest,            ");
		sql.append("       ifdelete,          ");
		sql.append("       ifindex,           ");
		sql.append("       ifuse,             ");
		sql.append("       keywords,          ");
		sql.append("       labels,            ");
		sql.append("       photo_name,        ");
		sql.append("       picurl,            ");
		sql.append("       publish_time,      ");
		sql.append("       sourcenet,         ");
		sql.append("       sourceurl,         ");
		sql.append("       title,             ");
		sql.append("       valueid            ");
		sql.append("  from v_article_index t  ");
		sql.append(" where t.ifuse = 1        ");
		sql.append("   and t.ifdelete = 0  and t.ifindex = 0  ");

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
		return listData;
	}

	/**
	 * 获得未索引资讯数据(表)
	 * 
	 * @param beginNum
	 * @param endNum
	 * @return
	 */

	public List<Document> getUnIndexedResourceDatas_new(int beginNum, int endNum) {
		List<Document> listData = new ArrayList<Document>();

		StringBuilder sql = new StringBuilder("");

		sql.append(" select t.articleid, ");
		sql.append(" t.valueid, ");
		sql.append(" t.brand, ");
		sql.append(" (select d.valuename from dict_basetype_value d where d.valueid = t.brand) brand_name, ");
		sql.append(" t.title, ");
		sql.append(" t.digest, ");
		sql.append(" t.describe, ");
		sql.append(" t.keywords, ");
		sql.append(" t.labels, ");
		sql.append(" t.content, ");
		sql.append(" t.sourceurl, ");
		sql.append(" t.sourcenet, ");
		sql.append(" t.picurl, ");
		sql.append(" t.publish_time, ");
		sql.append(" t.create_dt, ");
		sql.append(" f_get_product_pic(t.articleid, 3) photo_name , ");
		sql.append(" t.ifuse, ");
		sql.append(" t.ifdelete, ");
		sql.append(" t.ifindex ");
		sql.append(" from article_list t ");
		sql.append(" where t.ifuse = 1        ");
		sql.append("   and t.ifdelete = 0  and t.ifindex = 0  ");

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
		return listData;
	}
	
	/**
	 * 获得已删除资讯数量
	 * 
	 * @return
	 */
	public int getDeletedResourceNums() {
		StringBuilder sql = new StringBuilder("");
		sql.append(" select count(1) ");
		sql.append(" from article_list t ");
		sql.append(" where (t.ifuse = 0 ");
		sql.append(" or t.ifdelete = 1) and t.ifindex = 0 ");

		IDAO dao = this.getDao();
		return dao.getQueryInt(sql.toString());
	}

	/**
	 * 获得已删除资讯的数据(视图)
	 * 
	 * @param beginNum
	 * @param endNum
	 * @return
	 */

	public List<Document> getDeletedResourceDatas(int beginNum, int endNum) {
		List<Document> listData = new ArrayList<Document>();

		StringBuilder sql = new StringBuilder("");

		sql.append("select articleid,         ");
		sql.append("       brand,             ");
		sql.append("       brand_name,        ");
		sql.append("       content,           ");
		sql.append("       create_dt,         ");
		sql.append("       describe,          ");
		sql.append("       digest,            ");
		sql.append("       ifdelete,          ");
		sql.append("       ifindex,           ");
		sql.append("       ifuse,             ");
		sql.append("       keywords,          ");
		sql.append("       labels,            ");
		sql.append("       photo_name,        ");
		sql.append("       picurl,            ");
		sql.append("       publish_time,      ");
		sql.append("       sourcenet,         ");
		sql.append("       sourceurl,         ");
		sql.append("       title,             ");
		sql.append("       valueid            ");
		sql.append("  from v_article_index t  ");
		sql.append(" where (t.ifuse = 0  ");
		sql.append(" or t.ifdelete = 1) and t.ifindex = 0 ");

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
		return listData;
	}
	
	/**
	 * 获得已删除资讯的数据(表)
	 * 
	 * @param beginNum
	 * @param endNum
	 * @return
	 */

	public List<Document> getDeletedResourceDatas_new(int beginNum, int endNum) {
		List<Document> listData = new ArrayList<Document>();

		StringBuilder sql = new StringBuilder("");

		sql.append(" select t.articleid, ");
		sql.append(" t.valueid, ");
		sql.append(" t.brand, ");
		sql.append(" (select d.valuename from dict_basetype_value d where d.valueid = t.brand) brand_name, ");
		sql.append(" t.title, ");
		sql.append(" t.digest, ");
		sql.append(" t.describe, ");
		sql.append(" t.keywords, ");
		sql.append(" t.labels, ");
		sql.append(" t.content, ");
		sql.append(" t.sourceurl, ");
		sql.append(" t.sourcenet, ");
		sql.append(" t.picurl, ");
		sql.append(" t.publish_time, ");
		sql.append(" t.create_dt, ");
		sql.append(" f_get_product_pic(t.articleid, 3) photo_name , ");
		sql.append(" t.ifuse, ");
		sql.append(" t.ifdelete, ");
		sql.append(" t.ifindex ");
		sql.append(" from article_list t ");
		sql.append(" where (t.ifuse = 0  ");
		sql.append(" or t.ifdelete = 1) and t.ifindex = 0 ");

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
		return listData;
	}

	/**
	 * 获得指定资讯的信息(视图)
	 * 
	 * @param resourceId
	 * @return
	 */
	public Document getAResourceDatas(int resourceId) {

		if (resourceId > 0) {
			StringBuilder sql = new StringBuilder("");
			sql.append("select articleid,         ");
			sql.append("       brand,             ");
			sql.append("       brand_name,        ");
			sql.append("       content,           ");
			sql.append("       create_dt,         ");
			sql.append("       describe,          ");
			sql.append("       digest,            ");
			sql.append("       ifdelete,          ");
			sql.append("       ifindex,           ");
			sql.append("       ifuse,             ");
			sql.append("       keywords,          ");
			sql.append("       labels,            ");
			sql.append("       photo_name,        ");
			sql.append("       picurl,            ");
			sql.append("       publish_time,      ");
			sql.append("       sourcenet,         ");
			sql.append("       sourceurl,         ");
			sql.append("       title,             ");
			sql.append("       valueid            ");
			sql.append("  from v_article_index t  ");
			sql.append(" where articleid = ?  ");

			IDAO dao = this.getDao();

			Object[] args = { resourceId };
			int[] argTypes = { java.sql.Types.INTEGER };

			List data = dao.getQuery(sql.toString(), args, argTypes);

			if (data != null && data.size() > 0) {
				Object obj = data.get(0);
				Map entry = (Map) obj;
				if (entry != null) {
					Document doc = buildPhoneIndexDocument(entry);
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
	 * 获得指定资讯的信息(表)
	 * 
	 * @param resourceId
	 * @return
	 */
	public Document getAResourceDatas_new(int resourceId) {

		if (resourceId > 0) {
			StringBuilder sql = new StringBuilder("");
			sql.append(" select t.articleid, ");
			sql.append(" t.valueid, ");
			sql.append(" t.brand, ");
			sql.append(" (select d.valuename from dict_basetype_value d where d.valueid = t.brand) brand_name, ");
			sql.append(" t.title, ");
			sql.append(" t.digest, ");
			sql.append(" t.describe, ");
			sql.append(" t.keywords, ");
			sql.append(" t.labels, ");
			sql.append(" t.content, ");
			sql.append(" t.sourceurl, ");
			sql.append(" t.sourcenet, ");
			sql.append(" t.picurl, ");
			sql.append(" t.publish_time, ");
			sql.append(" t.create_dt, ");
			sql.append(" f_get_product_pic(t.articleid, 3) photo_name , ");
			sql.append(" t.ifuse, ");
			sql.append(" t.ifdelete, ");
			sql.append(" t.ifindex ");
			sql.append(" from article_list t ");
			sql.append(" where t.ifdelete = 0 and articleid = ?  ");

			IDAO dao = this.getDao();

			Object[] args = { resourceId };
			int[] argTypes = { java.sql.Types.INTEGER };

			List data = dao.getQuery(sql.toString(), args, argTypes);

			if (data != null && data.size() > 0) {
				Object obj = data.get(0);
				Map entry = (Map) obj;
				if (entry != null) {
					Document doc = buildPhoneIndexDocument(entry);
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

	private Document buildPhoneIndexDocument(Map entry) {
		if (entry != null) {
			String articleid = StringUtil.escapeNullObject(RowSetTypeConverter
					.getInteger(entry.get("articleid")).toString());
			Date create_dt = RowSetTypeConverter
					.getDate(entry.get("create_dt"));
			// String brand = StringUtil.escapeNullObject(RowSetTypeConverter
			// .getString(entry.get("brand")));
			// String brand_name =
			// StringUtil.escapeNullObject(RowSetTypeConverter
			// .getString(entry.get("brand_name")));
			//		
			// String content = StringUtil.escapeNullObject(RowSetTypeConverter
			// .getString(entry.get("content")));
			//		
			// String describe = StringUtil.escapeNullObject(RowSetTypeConverter
			// .getString(entry.get("describe")));

			String digest = StringUtil.escapeNullObject(RowSetTypeConverter
					.getString(entry.get("digest")));

			String keywords = StringUtil.escapeNullObject(RowSetTypeConverter
					.getString(entry.get("keywords")));

			String labels = StringUtil.escapeNullObject(RowSetTypeConverter
					.getString(entry.get("labels")));
			String photo_name = StringUtil.escapeNullObject(RowSetTypeConverter
					.getString(entry.get("photo_name")));
			String picurl = StringUtil.escapeNullObject(RowSetTypeConverter
					.getString(entry.get("picurl")));

			Date publish_time = RowSetTypeConverter.getDate(entry
					.get("publish_time"));

			String sourcenet = StringUtil.escapeNullObject(RowSetTypeConverter
					.getString(entry.get("sourcenet")));
			String sourceurl = StringUtil.escapeNullObject(RowSetTypeConverter
					.getString(entry.get("sourceurl")));

			String title = StringUtil.escapeNullObject(RowSetTypeConverter
					.getString(entry.get("title")));

			Integer valueid = RowSetTypeConverter.getInteger(entry
					.get("valueid"));
			String valueidStr = "";
			if (valueid != null) {
				valueidStr = StringUtil.escapeNullObject(valueid.toString());
			}

			return ResourceDocument.getDocument(articleid, valueidStr, title,
					create_dt, keywords, labels, sourceurl, sourcenet, digest,
					publish_time, picurl, photo_name);
		} else {
			return null;
		}
	}

	/**
	 * 更新指定资讯为已被索引状态
	 * 
	 * @param peiJanid
	 * @return
	 */
	public int setAResourceIndexed(int resourceId) {
		if (resourceId > 0) {
			StringBuilder sql = new StringBuilder();

			sql
					.append(" update article_list al set al.ifindex = 1 where al.articleid = ? ");
			IDAO dao = this.getDao();
			Object[] args = { resourceId };
			int[] argTypes = { java.sql.Types.INTEGER };

			dao.executeUpdate(sql.toString(), args, argTypes);
		}
		return 0;
	}

	/**
	 * 更新指定分类的资讯为已被索引状态 1: 新闻 2:评测 3:导购 4:行情
	 * 
	 * @return
	 */
	public int setResourceIndexedByClassID(int cateID) {

		int classid = getArticleClassID(cateID);
		if (classid == -1) {
			return 0;
		} else {
			StringBuilder sql = new StringBuilder();
			sql.append(" update  article_list t set t.ifindex = 1 ");
			sql.append(" where t.ifindex = 0 and t.valueid = ?    ");
			
			Object[] args = { classid };
			int[] argTypes = { java.sql.Types.INTEGER };
			
			IDAO dao = this.getDao();
			return dao.executeUpdate(sql.toString(), args, argTypes);
		}
	}

	/**
	 * 更新所有的资讯为已被索引状态
	 * 
	 * @return
	 */
	public int setAllResourceIndexed() {
		StringBuilder sql = new StringBuilder();
		sql.append(" update  article_list t set t.ifindex = 1 ");
		sql.append(" where t.ifindex = 0	");

		IDAO dao = this.getDao();
		return dao.executeUpdate(sql.toString());
	}

	/**
	 * 更新未索引的资讯为已被索引状态
	 * 
	 * @return
	 */
	public int setUnIndexedResourceIndexed() {
		StringBuilder sql = new StringBuilder();
		sql.append(" update  article_list t set t.ifindex = 1 ");
		sql.append(" where t.ifuse = 1 ");
		sql.append(" and t.ifindex = 0	and t.ifdelete = 0 ");

		IDAO dao = this.getDao();
		return dao.executeUpdate(sql.toString());
	}

	/**
	 * 更新已删除的资讯为已被索引状态
	 * 
	 * @return
	 */
	public int setDeletedResourceIndexed() {
		StringBuilder sql = new StringBuilder();
		sql.append(" update  product_list t set t.ifindex = 1 ");
		sql.append(" where (t.ifuse = 0  ");
		sql.append(" or t.ifdelete = 1) and t.ifindex = 0 ");

		IDAO dao = this.getDao();

		return dao.executeUpdate(sql.toString());
	}
}