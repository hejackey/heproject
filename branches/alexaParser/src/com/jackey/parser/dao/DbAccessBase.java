package com.jackey.parser.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>
 * Title: SDK For ZLWT Wap
 * </p>
 * <p>
 * Description: 数据库访问类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * <p>
 * Company: zlwt
 * </p>
 * 
 * @author 林华�?
 * @version 1.0
 */

public class DbAccessBase {
	protected Connection conn = null;

	protected PreparedStatement pstmt = null;

	protected Statement stmt = null;

	protected static final Log log = LogFactory.getLog(DbAccessBase.class);

	public DbAccessBase(Connection conn) {
		this.conn = conn;
	}

	/**
	 * 调用数据库存储过�?
	 * 
	 * @param sql
	 *            String
	 * @throws SQLException
	 * @return CallableStatement
	 */
	public CallableStatement call(String sql) throws SQLException {
		CallableStatement call = null;
		try {
			call = conn.prepareCall(sql);
		} catch (Exception e) {
			log.error("-----" + e.toString());
			e.printStackTrace();
		}
		return call;
	}

	/**
	 * 数据库查询方�?
	 * 
	 * @param sql
	 *            String
	 * @throws SQLException
	 * @return ResultSet
	 */
	public ResultSet executeQuery(String sql, String[] paras)
			throws SQLException {
		ResultSet rs = null;
		//System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);

			for (int i = 0; i < paras.length; i++) {
				pstmt.setString(i + 1, paras[i]);
				//System.out.println(paras[i]);
			}

			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			log.error("-----" + e.toString());
			e.printStackTrace();
			throw e;
		}
		return rs;
	}

	/**
	 * 数据库更新方�?
	 * 
	 * @param sql
	 *            String
	 * @throws SQLException
	 * @return int
	 */
	public int update(String sql, String[] paras) throws SQLException {
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < paras.length; i++) {
				// System.out.println("i"+i+":"+paras[i]);
				pstmt.setString(i + 1, paras[i]);
			}
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			log.error("-----" + e.toString());
			e.printStackTrace();
			throw e;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					throw e;
				}
				pstmt = null;
			}
		}
		return result;
	}

	/**
	 * 数据库更新方�?
	 * 
	 * @param sql
	 *            String
	 * @throws SQLException
	 */
	public void updateExecute(String sql, String[] paras) throws SQLException {
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < paras.length; i++) {
				pstmt.setString(i + 1, paras[i]);
			}

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			log.error("-----" + e.toString());
			e.printStackTrace();
			throw e;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					throw e;
				}
				pstmt = null;
			}
		}
	}

	/**
	 * 数据库查询方�?
	 * 
	 * @param sql
	 *            String
	 * @throws SQLException
	 * @return ResultSet
	 */
	public ResultSet executeQuery(String sql) throws SQLException {
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// log.error(this.getClass().getName() +" : Mothed =
			// ExcuteQuery(String sql)==>"+ e.getMessage());
			log.error("-----" + e.toString());
			e.printStackTrace();
			throw e;
		}
		return rs;
	}

	/**
	 * 数据库更新方�?
	 * 
	 * @param sql
	 *            String
	 * @throws SQLException
	 * @return int
	 */
	public int update(String sql) throws SQLException {
		int result = 0;
		try {
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// log.error(this.getClass().getName() +" : Mothed = Update(String
			// sql)==>"+ e.getMessage());
			log.error("-----" + e.toString());
			e.printStackTrace();
			throw e;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					throw e;
				}
				stmt = null;
			}
		}
		return result;
	}

	/**
	 * 数据库更新方�?
	 * 
	 * @param sql
	 *            String
	 * @throws SQLException
	 */
	public void updateExecute(String sql) throws SQLException {
		int result = 0;
		try {
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// log.error(this.getClass().getName() +" : Mothed =
			// UpdateExcute(String sql)==>"+ e.getMessage());
			log.error("-----" + e.toString());
			e.printStackTrace();
			throw e;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					throw e;
					// log.error(this.getClass().getName() +" : Mothed =
					// UpdateExcute(String sql)==>"+ e.getMessage());
				}
				stmt = null;
			}
		}
	}
}
