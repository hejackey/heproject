package com.sohu.spaces.solr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sohu.spaces.solr.model.Playlist;

public abstract class AbstractPlayListDao extends BaseDao {
    private static Logger log = LoggerFactory.getLogger(AbstractPlayListDao.class);
    
    /**
     * 查询符合条件的专辑总数
     * @param flag 0 正常数据处理 1处理createtime为0的数据
     * @param sdate 起始日期
     * @param edate 截止日期
    * @return  专辑总数
     * @throws SQLException
     */
    public int countPlayList(int flag,Date sdate, Date edate) throws SQLException{
        Long lsdate = sdate.getTime();
        Long ledate = edate.getTime();
        String sql = null;
        Object[] params= null;
        
        if(flag == 0){
            sql = getDynamicSql(null,1,0,0);
            params=new Object[]{lsdate,ledate};
        } else {
            sql = getDynamicSqlCreatetimeNull(null,1,0,0);
            params=new Object[]{0};
        }
        log.info("countPlayList sql =====>"+sql);
        
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
     * 专辑列表查询
     * @param flag TODO
     * @param sdate 起始日期
     * @param edate 截止日期
     * @param start 起始记录数
     * @param limit 每页记录总数
     * @return  专辑集合
     * @throws SQLException
     */
    @SuppressWarnings("unchecked")
    public List<Playlist> getPlaylist(int flag,Date sdate,Date edate,int start, int limit) throws SQLException{
        Long lsdate = sdate.getTime();
        Long ledate = edate.getTime();
        String sql = null;
        Object[] params=null;
        
        if(flag == 0){
            sql = getDynamicSql(null,0,start,limit);
            params=new Object[]{lsdate,ledate};
        } else {
            sql = getDynamicSqlCreatetimeNull(null,0,start,limit);
            params=new Object[]{0};
        }
       
        log.info("getPlaylist sql =====>"+sql);
        
        return (List<Playlist>) runner.query(sql, getBeanListHandler(), params);
    }
    
    /**
     * 根据专辑id获取专辑信息
     * @param vid
     * @return
     * @throws SQLException
     */
    public Playlist getPlaylistById(Long vid) throws SQLException{
        String sql = getDynamicSql(vid,0,0,0);
        log.info("getPlaylistById sql =====>"+sql);
        
        Object[] params={vid};
        
        return (Playlist) runner.query(sql, getBeanObjectHandler(), params);
    }
    
    public abstract String getDynamicSqlCreatetimeNull(Long id, int flag, int page, int pageSize) ;
}
