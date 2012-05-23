package com.apply.b2b.cms.base;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public interface IDAO {

	
	public Connection getConnection() ;
	
	/**
	 * 数据操作的方法
	 * 
	 * @param sql
	 * @return update操作
	 */

	public abstract int executeUpdate(String sql);
	public abstract int executeUpdate(String sql , Object[] args, int[] argTypes);

	/**
	 * 返回结果集的行数，sql语句中要包含"COUNT_NUM"
	 * 
	 * @param sql
	 * @return long
	 */

	public abstract long getCount(String sql);

	/**
	 * 根据一条SQL语句，查询到包含所有结果的map List集合
	 * 
	 * @param sql
	 * @return List集合
	 */

	public abstract List getQuery(String sql);

	/**
	 * 根据一条SQL语句，查询到包含所有结果的map List集合
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */

	public abstract List getQuery(String sql, Object[] args);

	public abstract List getQuery(String sql, Object[] args, int[] argTypes);

	/**
	 * 根据一条SQL语句，查询到包含所有结果SqlRowSet
	 * 
	 * @param sql
	 * @return
	 */
	public abstract SqlRowSet getRowSetQuery(String sql);

	public abstract SqlRowSet getRowSetQuery(String sql, Object[] args);

	public abstract SqlRowSet getRowSetQuery(String sql, Object[] args,
			int[] argTypes);

	/**
	 * 根据一条SQL语句，只返回查询到的第一条结果
	 * 
	 * @param sql
	 * @return Map对象
	 */
	public abstract Map getQueryFirst(String sql);

	public abstract Map getQueryFirst(String sql, Object[] args);

	public abstract Map getQueryFirst(String sql, Object[] args, int[] argTypes);

	/**
	 * 返回sql语句中，的第一个字段的值
	 * 
	 * @param sql
	 * @return int
	 */
	public abstract int getQueryInt(String sql);

	public abstract int getQueryInt(String sql, Object[] args);

	public abstract int getQueryInt(String sql, Object[] args, int[] argTypes);

	/**
	 * 返回sql语句中，的第一个字段的值
	 * 
	 * @param sql
	 * @return long
	 */
	public abstract long getQueryLong(String sql);

	public abstract long getQueryLong(String sql, Object[] args);

	public abstract long getQueryLong(String sql, Object[] args, int[] argTypes);

	/**
	 * 返回sql结果集中的，count行之前的结果集
	 * 
	 * @param sql
	 * @param count
	 * @return List结果集
	 */
	
	public abstract List getTopCountQuery(String sql, long count);
	
	public abstract List getTopCountQuery(String sql, Object[] args, long count);
	
	public abstract List getTopCountQuery(String sql, Object[] args,
			int[] argTypes, long count);
	
	/**
	 * 返回sql结果集中，upNum和lowNum 之间的结果集
	 * 
	 * @param sql
	 * @param upNum
	 *            较大的行数
	 * @param lowNum
	 *            较小的行数
	 * @return List结果集
	 */
	public abstract List getCountQuery(String sql, long upNum, long lowNum);
	
	public abstract List getCountQuery(String sql, Object[] args, long upNum,
			long lowNum);
	
	public abstract List getCountQuery(String sql, Object[] args,
			int[] argTypes, long upNum, long lowNum);
	
}