package com.sohu.spaces.solr.dao;

import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import com.sohu.spaces.solr.model.VideoInfo;

/**
 * UGC视频全量索引数据提取
 * @author xiaolianghe
 *
 */
public class VideoInfoDaoImpl {
    ComboPooledDataSource dataSource;
    private static final String JDBC_URL="jdbc:mysql://videos.read.db.spaces.sohu:3306/videodb?useUnicode=true&amp;characterEncoding=UTF-8" +
            "&amp;autoReconnect=true&amp;zeroDateTimeBehavior=convertToNull";
    private static final String DRIVE_CLASS="com.mysql.jdbc.Driver";
    private static final String DB_USER="video_user";
    private static final String DB_PWD="177sohuvideo!";
    private static Logger log = LoggerFactory.getLogger(VideoInfoDaoImpl.class);
    
    /**
     * 构造函数，初始化dataSource
     */
    public VideoInfoDaoImpl(){
        dataSource = new ComboPooledDataSource(); 
        try {
            dataSource.setDriverClass(DRIVE_CLASS);
            dataSource.setJdbcUrl(JDBC_URL);
            dataSource.setUser(DB_USER);
            dataSource.setPassword(DB_PWD);
            log.info("VideoInfoDaoImpl init datasource succes");
        } catch (PropertyVetoException e) {
            dataSource = null;
            log.error("VideoInfoDaoImpl init datasource error,"+e.getMessage());
        }
      
    }
    
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
        String sql = getDynamicSql(1,0,0);
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
    public List<VideoInfo> getVideoInfo(Date sdate,Date edate,int start,int limit) throws SQLException{
        Long lsdate = sdate.getTime();
        Long ledate = edate.getTime();
        String sql = getDynamicSql(0,start,limit);
        log.info("getVideoInfo sql =====>"+sql);
        
        Object[] params={lsdate,ledate};
        
        QueryRunner runner = new QueryRunner(dataSource);
        
        return runner.query(sql, getBeanListHandler(), params);
    }
    
    private BeanListHandler<VideoInfo> getBeanListHandler(){
        return new BeanListHandler<VideoInfo>(VideoInfo.class);
    }
    
    /**
     * 封装查询视频sql语句
     * @param flag  0、集合查询 1、统计查询 
     * @param page  起始记录
     * @param pageSize  每页记录总数
     * @return  拼接后的sql语句
     */
    private String getDynamicSql(int flag,int page,int pageSize){
        StringBuffer sb = new StringBuffer();
        if(flag == 0)
            sb.append("select id,title,tag,categoriesid as categoriesId,introduction,status,videolength as videoLength,videosize as videoSize," +
                    "uploadtime as uploadTime,uploadip as uploadIp,cuscoverurl as cusCoverURL,cutcoverurl as cutCoverURL,userid as userId," +
                    "lastmodified as lastModified,videotype as videoType,uploadfrom as uploadFrom,plevel,ver_type as verType,play_limit as playLimit," +
                    "cate_code as cateCode from videoinfo  where 1=1 ");
        else 
            sb.append("select count(1) from videoinfo where 1=1 ");
      
        sb.append(" and lastmodified >=? ");
        sb.append(" and lastmodified <= ?");
        sb.append("  order by lastmodified desc");
        
        if(flag == 0){
            sb.append(" limit ");
            sb.append((page-1)*pageSize);
            sb.append(",");
            sb.append(pageSize);
        }
        return sb.toString();
    }
}
