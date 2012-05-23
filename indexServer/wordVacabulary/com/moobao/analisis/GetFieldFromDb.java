
/**
 * 从数据库得到"手机品种"和"型号"�
 * @author liuxueyong
 */
package com.moobao.analisis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.apply.b2b.cms.base.BaseDAO;

public class GetFieldFromDb {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String valueName = "";
	//���е�Fiels定义一个Set对象,存放所有字段内容.
	private Set set = new HashSet();

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
					 set.add( valueName );
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
		return set;
	}
}
