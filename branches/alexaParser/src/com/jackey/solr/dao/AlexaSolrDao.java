package com.jackey.solr.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jackey.parser.bean.AlexBean;
import com.jackey.parser.dao.DbAccessBase;
import com.jackey.parser.util.SqlStorer;

public class AlexaSolrDao extends DbAccessBase {

	public AlexaSolrDao(Connection conn) {
		super(conn);
	}

	/**
	 * 分页获取记录数
	 * @param bean
	 * @return
	 * @throws SQLException
	 */
	public List<AlexBean> getAlexas(AlexBean bean) throws SQLException{
		String[] data = new String [2];
		data[0] = String.valueOf(bean.getStartRow());
		data[1] = String.valueOf(bean.getEndRow());
		
		ResultSet rs = super.executeQuery(SqlStorer.SQL_WEBSORTINFO_SELECTAll, data);
		List<AlexBean> beanList = new ArrayList<AlexBean>();
		
		while(rs.next()){
			AlexBean alexBean = new AlexBean();
			
			alexBean.setTitle(rs.getString("title"));
			alexBean.setDomain(rs.getString("domainname"));
			alexBean.setDesc(rs.getString("description"));
			alexBean.setCount(rs.getInt("sort"));
			alexBean.setSorttype(rs.getInt("sorttype"));
			alexBean.setWebtype(rs.getString("webtype"));
			
			beanList.add(alexBean);
		}
		return beanList;
	}
}
