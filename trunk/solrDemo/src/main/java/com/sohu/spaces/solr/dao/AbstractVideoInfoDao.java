package com.sohu.spaces.solr.dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import com.sohu.spaces.solr.model.VideoInfo;

/**
 * 操作视频表dao抽象类
 * @author xiaolianghe
 *
 */
public abstract class AbstractVideoInfoDao extends BaseDao{
    private static Logger log = LoggerFactory.getLogger(AbstractVideoInfoDao.class);
    private static ComboPooledDataSource dataSource = DataSourceFactory.getDataSouce();
    
    /**
     * 查询符合条件的记录总数
    * @param sdate 起始日期
     * @param edate 截止日期
     * @return  记录总数
     * @throws SQLException
     */
    public int countVideoInfo(Date sdate,Date edate) throws SQLException{
        Long lsdate = sdate.getTime();
        Long ledate = edate.getTime();
        String sql = getDynamicSqlByDate(lsdate,ledate,null,1,0,0);//getDynamicSql(null,1,0,0);
        log.info("countVideoInfo sql =====>"+sql);
        
        Object[] params={lsdate,ledate};
        
        QueryRunner runner = new QueryRunner(dataSource);
        Integer count = runner.query(sql, new ResultSetHandler<Integer>(){
            public Integer handle(final ResultSet rs) throws SQLException {
                log.info("query size=====>"+rs.getFetchSize());
                if(rs.next()){
                    return rs.getInt(1); 
                }
                return 0;
            }
        },params);
        
        return count;
    }
    
    /**
     * 视频列表查询
     * @param sdate 起始日期
     * @param edate 截止日期
     * @param start 起始记录数
     * @param limit 每页记录总数
     * @return  视频集合
     * @throws SQLException
     */
    @SuppressWarnings("unchecked")
    public List<VideoInfo> getVideoInfo(Date sdate,Date edate,int start,int limit) throws SQLException{
        Long lsdate = sdate.getTime();
        Long ledate = edate.getTime();
        String sql = getDynamicSqlByDate(lsdate,ledate,null,0,start,limit);//getDynamicSql(null,0,start,limit);
        log.info("getVideoInfo sql =====>"+sql);
        
        Object[] params={lsdate,ledate};
        
        QueryRunner runner = new QueryRunner(dataSource);
        
        return (List<VideoInfo>) runner.query(sql, getBeanListHandler(), params);
    }
    
    /**
     * 根据视频id获取视频信息
     * @param vid
     * @return
     * @throws SQLException
     */
    public VideoInfo getVideoInfoById(Long vid) throws SQLException{
        String sql = getDynamicSql(vid,0,0,0);
        log.info("getVideoInfoById sql =====>"+sql);
        
        Object[] params={vid};
        
        QueryRunner runner = new QueryRunner(dataSource);
        
        return (VideoInfo) runner.query(sql, getBeanObjectHandler(), params);
    }
    
    /**
     * 根据id范围获取视频信息
     * @param start
     * @param limit
     * @return
     * @throws SQLException
     */
    @SuppressWarnings("unchecked")
    public List<VideoInfo> getVideoInfoByIdRange(int start,int limit) throws SQLException{
        String sql = getDynamicSqlByIdRange(start,limit);
        log.info("getVideoInfoByIdRange sql =====>"+sql);
        
        QueryRunner runner = new QueryRunner(dataSource);
        
        return (List<VideoInfo>) runner.query(sql, getBeanListHandler());
    }
    
    /**
     * 查询符合条件的记录总数
    * @param sdate 起始日期
     * @param edate 截止日期
     * @return  记录总数
     * @throws SQLException
     */
    public int countVideoInfo() throws SQLException{
        String sql = getCountVideoSql();
        log.info("countVideoInfo sql =====>"+sql);
        
        Object[] params={};
        
        QueryRunner runner = new QueryRunner(dataSource);
        Integer count = runner.query(sql, new ResultSetHandler<Integer>(){
            public Integer handle(final ResultSet rs) throws SQLException {
                log.info("query size=====>"+rs.getFetchSize());
                if(rs.next()){
                    return rs.getInt(1); 
                }
                return 0;
            }
        },params);
        
        return count;
    }
    
    
    /**
     * 根据id范围拼接sql
     * @param start 页码
     * @param limit 每页大小
     * @return
     * @throws SQLException
     */
    public abstract String getDynamicSqlByIdRange(int page,int pageSize); 
    
    /**
     * 拼装视频表总记录数sql
     * @return
     */
    public abstract String getCountVideoSql();
    
    /**
     * 根据日期查询记录
     * @param starDate	开始日期
     * @param endDate	截止日期
     * @param vid	视频id
     * @param flag	
     * @param page
     * @param pageSize
     * @return
     */
    public abstract String getDynamicSqlByDate(Long starDate,Long endDate,Long vid,int flag,int page,int pageSize);
}
