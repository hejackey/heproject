
/**
 * 从数据库得到"手机品种"和"型号"�
 * @author liuxueyong
 */
package com.moobao.searchlist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.apply.b2b.cms.base.BaseDAO;

public class GetFieldFromDb {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String valueName = "";
	//���е�Fiels定义一个Set对象,存放所有字段内容.
	private Set set1 = new HashSet();
	private Set set2 = new HashSet();

	/**
	 * @param sql
	 * @param field �ֶ���
	 * @return Set
	 * @throws SQLException
	 */
	public Set getField( String sql, String field ) {
		BaseDAO dao = new BaseDAO();
		try {
			conn = dao.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				 valueName = rs.getString( field );
				 if( !(valueName == null) ) {
					 valueName.trim();
					 set2.add( valueName );
				 }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				conn = null;
			}
		}
		return set2;
	}
	
	/**
	 * @param sql
	 * @param fields
	 * @return
	 */
	public Set getField( String sql, List<String> fields ) {
		BaseDAO dao = new BaseDAO();
		try {
			conn = dao.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				for( int i = 0; i < fields.size(); i ++ ) {
					valueName = rs.getString( fields.get(i) );
					 if( !(valueName == null) && valueName.length() > 0 ) {
						 valueName.trim();
						 set1.add( valueName );
					 }
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				conn = null;
			}
		}
		return set1;
	}
}
