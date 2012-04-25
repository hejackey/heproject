package com.zhuozhuo.mis.common;

import java.sql.Connection;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class BaseDao extends SqlMapClientDaoSupport {
	protected  Connection  getConnection() {
		return DataSourceUtils.getConnection(getDataSource());
	}
}
