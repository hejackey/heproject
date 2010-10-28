package com.jackey.parser.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jackey.parser.bean.AlexBean;
import com.jackey.parser.util.SqlStorer;

public class AlexaParserDao extends DbAccessBase {

	public AlexaParserDao(Connection conn) {
		super(conn);
	}

	/**
	 * 插入
	 * @param bean
	 * @return
	 * @throws SQLException
	 */
	public int insert(AlexBean bean) throws SQLException {	
		String[] data=new String[6];
		data[0]=bean.getTitle();
		data[1]=bean.getDomain().toLowerCase();
		data[2]=String.valueOf(bean.getCount());
		data[3]=bean.getDesc();
		data[4]=String.valueOf(bean.getSorttype());
		data[5]=bean.getWebtype();
		
		return super.update(SqlStorer.SQL_WEBSORTINFO_INSERT,data);
	}
	
	/**
	 * 查询
	 * @param bean
	 * @return
	 * @throws SQLException
	 */
	public int query(AlexBean bean) throws SQLException {	
		String[] data=new String[2];
		data[0]=bean.getDomain().toLowerCase();
		
		data[1]=String.valueOf(bean.getSorttype());
		
		ResultSet rs = super.executeQuery(SqlStorer.SQL_WEBSORTINFO_SELECT,data);
		System.out.println(rs.getRow());
		if(rs!=null&&rs.next()){
			return rs.getInt("id");
		}
		
		return 0;
	}
	
	/**
	 * 更新
	 * @param bean
	 * @return
	 * @throws SQLException
	 */
	public int update(AlexBean bean) throws SQLException {	
		String[] data=new String[6];
		data[0]=bean.getTitle();
		data[1]=bean.getDomain();
		data[2]=String.valueOf(bean.getCount());
		data[3]=bean.getDesc();
		data[4]=String.valueOf(bean.getSorttype());
		data[5]=String.valueOf(bean.getId());
		
		return super.update(SqlStorer.SQL_WEBSORTINFO_UPDATE,data);
	}
}
