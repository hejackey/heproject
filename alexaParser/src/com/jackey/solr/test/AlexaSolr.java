package com.jackey.solr.test;

import java.sql.SQLException;

import com.jackey.parser.bean.AlexBean;
import com.jackey.parser.dao.DbConnection;
import com.jackey.solr.dao.AlexaSolrDao;

public class AlexaSolr {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AlexaSolrDao dao = new AlexaSolrDao(DbConnection.getDBConnection());
			AlexBean bean = new AlexBean(2,10);
			System.out.println(dao.getAlexas(bean).size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
