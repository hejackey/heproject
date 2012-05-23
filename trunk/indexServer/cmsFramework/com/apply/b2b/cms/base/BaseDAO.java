package com.apply.b2b.cms.base;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.apply.b2b.cms.util.DBPoolUtil;

/**
 * 
 * @author luoweifeng
 * 
 */

public class BaseDAO implements IDAO {
	protected final Logger log = Logger.getLogger(this.getClass());
	
	private final JdbcTemplate jdbcTemplate = new JdbcTemplate(DBPoolUtil
			.getInstance("moobao").getDataSource());//外网打包时用
	        //.getEnCryptInstance().getDataSource());//定时任务JAR包时用
	        //.getInstance().getDataSource());//本机时用
	
	/**
	 * 子类调用父类的这个方法getConnection()，即可获得数据库的一个连掄1�7
	 * 
	 * @return Conection conn对象
	 * @throws SQLException
	 */
	
	public Connection getConnection() {
		return DataSourceUtils.getConnection(getJdbcTemplate().getDataSource());
	}
	
	private JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	public void setFetchSize(int fetchSize) {
		getJdbcTemplate().setFetchSize(fetchSize);
	}
	
	public void setIgnoreWarnings(boolean ignoreWarnings) {
		getJdbcTemplate().setIgnoreWarnings(ignoreWarnings);
	}
	
	public void setMaxRows(int maxRows) {
		getJdbcTemplate().setMaxRows(maxRows);
	}
	
	/* (non-Javadoc)
	 * @see com.apply.b2b.cms.base.IDAO#executeUpdate(java.lang.String)
	 */
	
	public int executeUpdate(String sql) {
		return this.getJdbcTemplate().update(sql);
	}
	
	public int executeUpdate(String sql, Object[] args, int[] argTypes) {
		return this.getJdbcTemplate().update(sql, args, argTypes);
	}
	
	/* (non-Javadoc)
	 * @see com.apply.b2b.cms.base.IDAO#getCount(java.lang.String)
	 */
	
	public long getCount(String sql) {
	    if(sql != null && !sql.trim().equals("")) {
	    	StringBuffer sqlCountQuery = new StringBuffer();
	    	sqlCountQuery.append(" select count(1) from ( ");
	    	sqlCountQuery.append(sql);
	    	sqlCountQuery.append(" ) count_tmep ");
	    	
	    	return this.getQueryLong(sqlCountQuery.toString());
	    	
	    }else{
	    	return 0;
	    }
	}
	
	/* (non-Javadoc)
	 * @see com.apply.b2b.cms.base.IDAO#getQuery(java.lang.String)
	 */
	
	public List getQuery(String sql) {
		if (sql != null && !sql.trim().equals("")) {
			try {
				return getJdbcTemplate().queryForList(sql);
			} catch (DataAccessException dae) {
				log.error(this.getClass().getName()
						+ "  Method:getQuery(String sql) " + "Error message: "
						+ dae.getMessage() + " sql:" + sql);
				return null;
			}
		} else {
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.apply.b2b.cms.base.IDAO#getQuery(java.lang.String, java.lang.Object[])
	 */
	
	public List getQuery(String sql, Object[] args) {
		if (sql != null && !sql.trim().equals("")) {
			try {
				return getJdbcTemplate().queryForList(sql, args);
			} catch (DataAccessException dae) {
				log.error(this.getClass().getName()
						+ "  Method:getQuery(String sql, Object[] args) "
						+ "Error message: " + dae.getMessage() + " sql:" + sql);
				return null;
			}
		} else {
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.apply.b2b.cms.base.IDAO#getQuery(java.lang.String, java.lang.Object[], int[])
	 */
	public List getQuery(String sql, Object[] args, int[] argTypes) {
		if (sql != null && !sql.trim().equals("")) {
			try {
				return getJdbcTemplate().queryForList(sql, args, argTypes);
			} catch (DataAccessException dae) {
				log
						.error(this.getClass().getName()
								+ "  Method:getQuery(String sql, Object[] args, int[] argTypes) "
								+ "Error message: " + dae.getMessage()
								+ " sql:" + sql);
				return null;
			}
		} else {
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.apply.b2b.cms.base.IDAO#getRowSetQuery(java.lang.String)
	 */
	public SqlRowSet getRowSetQuery(String sql) {
		if (sql != null && !sql.trim().equals("")) {
			try {
				return getJdbcTemplate().queryForRowSet(sql);
			} catch (DataAccessException dae) {
				log.error(this.getClass().getName()
						+ "  Method:getQuery(String sql) " + "Error message: "
						+ dae.getMessage() + " sql:" + sql);
				return null;
			}
		} else {
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.apply.b2b.cms.base.IDAO#getRowSetQuery(java.lang.String, java.lang.Object[])
	 */
	public SqlRowSet getRowSetQuery(String sql, Object[] args) {
		if (sql != null && !sql.trim().equals("")) {
			try {
				return getJdbcTemplate().queryForRowSet(sql, args);
			} catch (DataAccessException dae) {
				log.error(this.getClass().getName()
						+ "  Method:getRowSetQuery(String sql, Object[] args) "
						+ "Error message: " + dae.getMessage() + " sql:" + sql);
				return null;
			}
		} else {
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.apply.b2b.cms.base.IDAO#getRowSetQuery(java.lang.String, java.lang.Object[], int[])
	 */
	public SqlRowSet getRowSetQuery(String sql, Object[] args, int[] argTypes) {
		if (sql != null && !sql.trim().equals("")) {
			try {
				return getJdbcTemplate().queryForRowSet(sql, args, argTypes);
			} catch (DataAccessException dae) {
				log
						.error(this.getClass().getName()
								+ "  Method:getRowSetQuery(String sql, Object[] args, int[] argTypes) "
								+ "Error message: " + dae.getMessage()
								+ " sql:" + sql);
				return null;
			}
		} else {
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.apply.b2b.cms.base.IDAO#getQueryFirst(java.lang.String)
	 */
	public Map getQueryFirst(String sql) {
		List rows = getQuery(sql);
		if (rows != null && rows.size() > 0) {
			Iterator it = rows.iterator();
			if (it.hasNext()) {
				Map aRow = (Map) it.next();
				return aRow;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.apply.b2b.cms.base.IDAO#getQueryFirst(java.lang.String, java.lang.Object[])
	 */
	public Map getQueryFirst(String sql, Object[] args) {
		List rows = getQuery(sql, args);
		if (rows != null && rows.size() > 0) {
			Iterator it = rows.iterator();
			if (it.hasNext()) {
				Map aRow = (Map) it.next();
				return aRow;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.apply.b2b.cms.base.IDAO#getQueryFirst(java.lang.String, java.lang.Object[], int[])
	 */
	public Map getQueryFirst(String sql, Object[] args, int[] argTypes) {
		List rows = getQuery(sql, args, argTypes);
		if (rows != null && rows.size() > 0) {
			Iterator it = rows.iterator();
			if (it.hasNext()) {
				Map aRow = (Map) it.next();
				return aRow;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.apply.b2b.cms.base.IDAO#getQueryInt(java.lang.String)
	 */
	public int getQueryInt(String sql) {
		if (sql != null && !sql.trim().equals("")) {
			try {
				return getJdbcTemplate().queryForInt(sql);
			} catch (IncorrectResultSizeDataAccessException irse) {
				log
						.error(this.getClass().getName()
								+ "  Method:getQueryInt(String sql) "
								+ "Error message: " + irse.getMessage()
								+ " sql:" + sql);
				return 0;
			} catch (DataAccessException dae) {
				log.error(this.getClass().getName()
						+ "  Method:getQueryInt(String sql) "
						+ "Error message: " + dae.getMessage() + " sql:" + sql);
				return 0;
			}
		} else {
			return 0;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.apply.b2b.cms.base.IDAO#getQueryInt(java.lang.String, java.lang.Object[])
	 */
	public int getQueryInt(String sql, Object[] args) {
		if (sql != null && !sql.trim().equals("")) {
			try {
				return getJdbcTemplate().queryForInt(sql, args);
			} catch (IncorrectResultSizeDataAccessException irse) {
				log
						.error(this.getClass().getName()
								+ "  Method:getQueryInt(String sql, Object[] args) "
								+ "Error message: " + irse.getMessage()
								+ " sql:" + sql);
				return 0;
			} catch (DataAccessException dae) {
				log.error(this.getClass().getName()
						+ "  Method:getQueryInt(String sql, Object[] args) "
						+ "Error message: " + dae.getMessage() + " sql:" + sql);
				return 0;
			}
		} else {
			return 0;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.apply.b2b.cms.base.IDAO#getQueryInt(java.lang.String, java.lang.Object[], int[])
	 */
	public int getQueryInt(String sql, Object[] args, int[] argTypes) {
		if (sql != null && !sql.trim().equals("")) {
			try {
				return getJdbcTemplate().queryForInt(sql, args, argTypes);
			} catch (IncorrectResultSizeDataAccessException irse) {
				log
						.error(this.getClass().getName()
								+ "  Method:getQueryIntgetQueryInt(String sql, Object[] args, int[] argTypes) "
								+ "Error message: " + irse.getMessage()
								+ " sql:" + sql);
				return 0;
			} catch (DataAccessException dae) {
				log
						.error(this.getClass().getName()
								+ "  Method:getQueryInt(String sql, Object[] args, int[] argTypes) "
								+ " Error message: " + dae.getMessage()
								+ " sql:" + sql);
				return 0;
			}
		} else {
			return 0;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.apply.b2b.cms.base.IDAO#getQueryLong(java.lang.String)
	 */
	public long getQueryLong(String sql) {
		if (sql != null && !sql.trim().equals("")) {
			try {
				return getJdbcTemplate().queryForLong(sql);
			} catch (IncorrectResultSizeDataAccessException irse) {
				log.error(this.getClass().getName()
						+ "  Method:getQueryLong(String sql) "
						+ " Error message: " + irse.getMessage() + " sql:"
						+ sql);
				return 0;
			} catch (DataAccessException dae) {
				log
						.error(this.getClass().getName()
								+ "  Method:getQueryLong(String sql) "
								+ " Error message: " + dae.getMessage()
								+ " sql:" + sql);
				return 0;
			}
		} else {
			return 0;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.apply.b2b.cms.base.IDAO#getQueryLong(java.lang.String, java.lang.Object[])
	 */
	public long getQueryLong(String sql, Object[] args) {
		if (sql != null && !sql.trim().equals("")) {
			try {
				return getJdbcTemplate().queryForLong(sql, args);
			} catch (IncorrectResultSizeDataAccessException irse) {
				log.error(this.getClass().getName()
						+ "  Method:getQueryLong(String sql, Object[] args) "
						+ " Error message: " + irse.getMessage() + " sql:"
						+ sql);
				return 0;
			} catch (DataAccessException dae) {
				log
						.error(this.getClass().getName()
								+ "  Method:getQueryLong(String sql, Object[] args) "
								+ " Error message: " + dae.getMessage()
								+ " sql:" + sql);
				return 0;
			}
		} else {
			return 0;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.apply.b2b.cms.base.IDAO#getQueryLong(java.lang.String, java.lang.Object[], int[])
	 */
	public long getQueryLong(String sql, Object[] args, int[] argTypes) {
		if (sql != null && !sql.trim().equals("")) {
			try {
				return getJdbcTemplate().queryForLong(sql, args, argTypes);
			} catch (IncorrectResultSizeDataAccessException irse) {
				log.error(this.getClass().getName()
						+ "  Method:getQueryLong(String sql, Object[] args) "
						+ " Error message: " + irse.getMessage() + " sql:"
						+ sql);
				return 0;
			} catch (DataAccessException dae) {
				log
						.error(this.getClass().getName()
								+ "  Method:getQueryLong(String sql, Object[] args) "
								+ " Error message: " + dae.getMessage()
								+ " sql:" + sql);
				return 0;
			}
		} else {
			return 0;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.apply.b2b.cms.base.IDAO#getTopCountQuery(java.lang.String, long)
	 */
	
	public List getTopCountQuery(String sql, long count) {
		if (sql != null && !sql.equals("")) {
			StringBuffer sqlCountQuery = new StringBuffer();
			sqlCountQuery.append("select * from ( ");
			sqlCountQuery.append(sql);
			sqlCountQuery.append(" ) ");
			sqlCountQuery.append(" where rownum <= ? ");
			ArrayList args = new ArrayList();
			args.add(count);
			int[] argTypes = new int[] { java.sql.Types.BIGINT };
			return getJdbcTemplate().queryForList(sqlCountQuery.toString(),
					args.toArray(), argTypes);
		} else {
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.apply.b2b.cms.base.IDAO#getTopCountQuery(java.lang.String, java.lang.Object[], long)
	 */
	public List getTopCountQuery(String sql, Object[] args, long count) {
		if (sql != null && !sql.equals("")) {
			StringBuffer sqlCountQuery = new StringBuffer();
			sqlCountQuery.append("select * from ( ");
			sqlCountQuery.append(sql);
			sqlCountQuery.append(" ) ");
			sqlCountQuery.append(" where rownum <= ? ");

			ArrayList arg1s = new ArrayList();
			if (args != null && args.length > 0) {
				arg1s.addAll(Arrays.asList(args));
				arg1s.add(count);
				return getJdbcTemplate().queryForList(sqlCountQuery.toString(),
						arg1s.toArray());
			} else {
				arg1s.add(count);
				int[] argTypes = new int[] { java.sql.Types.BIGINT };
				return getJdbcTemplate().queryForList(sqlCountQuery.toString(),
						arg1s.toArray(), argTypes);
			}
		} else {
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.apply.b2b.cms.base.IDAO#getTopCountQuery(java.lang.String, java.lang.Object[], int[], long)
	 */
	public List getTopCountQuery(String sql, Object[] args, int[] argTypes,
			long count) {
		if (sql != null && !sql.equals("")) {
			StringBuffer sqlCountQuery = new StringBuffer();
			sqlCountQuery.append("select * from ( ");
			sqlCountQuery.append(sql);
			sqlCountQuery.append(" ) ");
			sqlCountQuery.append(" where rownum <= ? ");

			ArrayList arg1s = new ArrayList();
			if (args != null && args.length > 0) {
				arg1s.addAll(Arrays.asList(args));
				arg1s.add(count);
			} else {
				arg1s.add(count);
			}

			int[] argTypes1 = null;
			if (argTypes != null && argTypes.length > 0) {
				int[] tempIntArgTypes = new int[argTypes.length + 1];
				System.arraycopy(argTypes, 0, tempIntArgTypes, 0,
						argTypes.length);
				System.arraycopy(new int[] { java.sql.Types.BIGINT }, 0,
						tempIntArgTypes, argTypes.length, 1);
				argTypes1 = tempIntArgTypes;
			} else {
				argTypes1 = new int[] { java.sql.Types.BIGINT };
			}

			return getJdbcTemplate().queryForList(sqlCountQuery.toString(),
					arg1s.toArray(), argTypes1);
		} else {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.apply.b2b.cms.base.IDAO#getCountQuery(java.lang.String, long, long)
	 */
	public List getCountQuery(String sql, long upNum, long lowNum) {
		if (sql != null && !sql.equals("")) {
			StringBuffer sqlCountQuery = new StringBuffer();
			sqlCountQuery.append("select * from ( ");
			sqlCountQuery.append("select rownum rn,temp.* from ( ");
			sqlCountQuery.append(sql);
			sqlCountQuery.append(" ) temp ");
			sqlCountQuery.append(" where rownum <= ? ");
			sqlCountQuery.append(" ) ");
			sqlCountQuery.append(" where rn >= ? ");

			//System.out.println( sqlCountQuery );
			ArrayList args = new ArrayList();
			args.add(upNum);
			args.add(lowNum);

			int[] argTypes = new int[] { java.sql.Types.BIGINT,
					java.sql.Types.BIGINT };
			
			List rows = getQuery(sqlCountQuery.toString(), args.toArray(),
					argTypes);
			return rows;
		} else {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.apply.b2b.cms.base.IDAO#getCountQuery(java.lang.String, java.lang.Object[], long, long)
	 */
	public List getCountQuery(String sql, Object[] args, long upNum, long lowNum) {
		if (sql != null && !sql.equals("")) {
			StringBuffer sqlCountQuery = new StringBuffer();
			sqlCountQuery.append("select * from ( ");
			sqlCountQuery.append("select rownum rn,temp.* from ( ");
			sqlCountQuery.append(sql);
			sqlCountQuery.append(" ) temp ");
			sqlCountQuery.append(" where rownum <= ? ");
			sqlCountQuery.append(" ) ");
			sqlCountQuery.append(" where rn >= ? ");
			ArrayList arg1s = new ArrayList();
			if (args != null && args.length > 0) {
				arg1s.addAll(Arrays.asList(args));
				arg1s.add(upNum);
				arg1s.add(lowNum);
				return getJdbcTemplate().queryForList(sqlCountQuery.toString(),
						arg1s.toArray());
			} else {
				arg1s.add(upNum);
				arg1s.add(lowNum);
				int[] argTypes = new int[] { java.sql.Types.BIGINT,
						java.sql.Types.BIGINT };
				return getQuery(sqlCountQuery.toString(), arg1s.toArray(),
						argTypes);
			}
		} else {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.apply.b2b.cms.base.IDAO#getCountQuery(java.lang.String, java.lang.Object[], int[], long, long)
	 */
	public List getCountQuery(String sql, Object[] args, int[] argTypes,
			long upNum, long lowNum) {
		if (sql != null && !sql.equals("")) {
			StringBuffer sqlCountQuery = new StringBuffer();
			sqlCountQuery.append("select * from ( ");
			sqlCountQuery.append("select rownum rn,temp.* from ( ");
			sqlCountQuery.append(sql);
			sqlCountQuery.append(" ) temp ");
			sqlCountQuery.append(" where rownum <= ? ");
			sqlCountQuery.append(" ) ");
			sqlCountQuery.append(" where rn >= ? ");

			ArrayList arg1s = new ArrayList();
			if (args != null && args.length > 0) {
				arg1s.addAll(Arrays.asList(args));
				arg1s.add(upNum);
				arg1s.add(lowNum);
			} else {
				arg1s.add(upNum);
				arg1s.add(lowNum);
			}
			int[] argTypes1 = null;

			if (argTypes != null && argTypes.length > 0) {
				int[] tempIntArgTypes = new int[argTypes.length + 2];
				System.arraycopy(argTypes, 0, tempIntArgTypes, 0,
						argTypes.length);
				System.arraycopy(new int[] { java.sql.Types.BIGINT,
						java.sql.Types.BIGINT }, 0, tempIntArgTypes,
						argTypes.length, 2);
				argTypes1 = tempIntArgTypes;
			} else {
				argTypes1 = new int[] { java.sql.Types.BIGINT,
						java.sql.Types.BIGINT };
			}
			return getQuery(sqlCountQuery.toString(), arg1s.toArray(),
					argTypes1);
		} else {
			return null;
		}
	}

	private SqlRowSet getRowSetCountQuery(String sql, long upNum, long lowNum) {
		if (sql != null && !sql.trim().equals("")) {
			StringBuffer sqlCountQuery = new StringBuffer();
			sqlCountQuery.append("select * from ( ");
			sqlCountQuery.append("select rownum rn,temp.* from ( ");
			sqlCountQuery.append(sql);
			sqlCountQuery.append(" ) temp ");
			sqlCountQuery.append(" where rownum <= ? ");
			sqlCountQuery.append(" ) ");
			sqlCountQuery.append(" where rn >= ? ");

			ArrayList args = new ArrayList();
			args.add(upNum);
			args.add(lowNum);

			int[] argTypes = new int[] { java.sql.Types.BIGINT,
					java.sql.Types.BIGINT };

			return getRowSetQuery(sqlCountQuery.toString(), args.toArray(),
					argTypes);
		} else {
			return null;
		}
	}
	
	private SqlRowSet getRowSetCountQuery(String sql, Object[] args,
			long upNum, long lowNum) {
		if (sql != null && !sql.trim().equals("")) {
			try {

				StringBuffer sqlCountQuery = new StringBuffer();
				sqlCountQuery.append("select * from ( ");
				sqlCountQuery.append("select rownum rn,temp.* from ( ");
				sqlCountQuery.append(sql);
				sqlCountQuery.append(" ) temp ");
				sqlCountQuery.append(" where rownum <= ? ");
				sqlCountQuery.append(" ) ");
				sqlCountQuery.append(" where rn >= ? ");
				ArrayList arg1s = new ArrayList();
				if (args != null && args.length > 0) {
					arg1s.addAll(Arrays.asList(args));
					arg1s.add(upNum);
					arg1s.add(lowNum);
					return getJdbcTemplate().queryForRowSet(
							sqlCountQuery.toString(), arg1s.toArray());
				} else {
					arg1s.add(upNum);
					arg1s.add(lowNum);
					int[] argTypes = new int[] { java.sql.Types.BIGINT,
							java.sql.Types.BIGINT };
					return getRowSetQuery(sqlCountQuery.toString(), arg1s
							.toArray(), argTypes);
				}

			} catch (DataAccessException dae) {
				log
						.error(this.getClass().getName()
								+ "  Method:getRowSetCountQuery(String sql, Object[] args, long upNum, long lowNum) "
								+ "Error message: " + dae.getMessage()
								+ " sql:" + sql);
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param sql
	 * @param args
	 * @param argTypes
	 * @param upNum
	 * @param lowNum
	 * @return
	 */
	private SqlRowSet getRowSetCountQuery(String sql, Object[] args,
			int[] argTypes, long upNum, long lowNum) {
		if (sql != null && !sql.trim().equals("")) {
			StringBuffer sqlCountQuery = new StringBuffer();
			sqlCountQuery.append("select * from ( ");
			sqlCountQuery.append("select rownum rn,temp.* from ( ");
			sqlCountQuery.append(sql);
			sqlCountQuery.append(" ) temp ");
			sqlCountQuery.append(" where rownum <= ? ");
			sqlCountQuery.append(" ) ");
			sqlCountQuery.append(" where rn >= ? ");

			ArrayList arg1s = new ArrayList();
			if (args != null && args.length > 0) {
				arg1s.addAll(Arrays.asList(args));
				arg1s.add(upNum);
				arg1s.add(lowNum);
			} else {
				arg1s.add(upNum);
				arg1s.add(lowNum);
			}
			int[] argTypes1 = null;

			if (argTypes != null && argTypes.length > 0) {
				int[] tempIntArgTypes = new int[argTypes.length + 2];
				System.arraycopy(argTypes, 0, tempIntArgTypes, 0,
						argTypes.length);
				System.arraycopy(new int[] { java.sql.Types.BIGINT,
						java.sql.Types.BIGINT }, 0, tempIntArgTypes,
						argTypes.length, 2);
				argTypes1 = tempIntArgTypes;
			} else {
				argTypes1 = new int[] { java.sql.Types.BIGINT,
						java.sql.Types.BIGINT };
			}
			return getRowSetQuery(sqlCountQuery.toString(), arg1s.toArray(),
					argTypes1);
		} else {
			return null;
		}
	}


}