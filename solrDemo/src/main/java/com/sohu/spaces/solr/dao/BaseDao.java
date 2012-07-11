package com.sohu.spaces.solr.dao;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.mchange.v2.c3p0.ComboPooledDataSource;


public abstract class BaseDao {
    public static ComboPooledDataSource dataSource = DataSourceFactory.getDataSouce();
    public static QueryRunner runner = new QueryRunner(dataSource);
    
    /**
     * 查询列表结果封装list
     * @return
     */
    protected abstract BeanListHandler<? extends Object> getBeanListHandler();
    
    /**
     * 查询对象结果封装object
     * @return
     */
    protected abstract BeanHandler<? extends Object> getBeanObjectHandler();
    
    /**
     * 封装查询sql语句
     * @param id 查询表主键id
     * @param flag  0、集合查询 1、统计查询 
     * @param page  起始记录
     * @param pageSize  每页记录总数
     * @return  拼接后的sql语句
     */
    public abstract String getDynamicSql(Long id,int flag,int page,int pageSize);
   
}
