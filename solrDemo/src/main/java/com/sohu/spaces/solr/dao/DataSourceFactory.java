package com.sohu.spaces.solr.dao;

import java.beans.PropertyVetoException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import com.sohu.spaces.solr.util.PropertiesUtil;

public class DataSourceFactory {
    private static Logger log = LoggerFactory.getLogger(DataSourceFactory.class);
    private static final String DB_CONFIG="db_config.properties";
    private static final String JDBC_URL=PropertiesUtil.getValueFromPropertyByKey(DB_CONFIG,"JDBC_URL");
    private static final String DRIVE_CLASS=PropertiesUtil.getValueFromPropertyByKey(DB_CONFIG,"DRIVE_CLASS");
    private static final String DB_USER=PropertiesUtil.getValueFromPropertyByKey(DB_CONFIG,"DB_USER");
    private static final String DB_PWD=PropertiesUtil.getValueFromPropertyByKey(DB_CONFIG,"DB_PWD");
    
    private static ComboPooledDataSource dataSource;
    
    public static synchronized ComboPooledDataSource getDataSouce(){
        if ( dataSource != null) {
            return dataSource;
        } else {
            dataSource = new ComboPooledDataSource(); 
            try {
                dataSource.setDriverClass(DRIVE_CLASS);
                dataSource.setJdbcUrl(JDBC_URL);
                dataSource.setUser(DB_USER);
                dataSource.setPassword(DB_PWD);
                log.info("VideoInfoDaoImpl init datasource succes");
                
                return dataSource;
            } catch (PropertyVetoException e) {
                log.error("VideoInfoDaoImpl init datasource error,"+e.getMessage());
                
                return null;
            }
             
        }
    }
}
