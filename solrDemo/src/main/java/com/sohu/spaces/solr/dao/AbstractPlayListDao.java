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
    * @param sdate 起始日期
     * @param edate 截止日期
     * @return  专辑总数
     * @throws SQLException
     */
    public int countPlayList(Date sdate,Date edate) throws SQLException{
        Long lsdate = sdate.getTime();
        Long ledate = edate.getTime();
        String sql = getDynamicSql(null,1,0,0);
        log.info("countPlayList sql =====>"+sql);
        
        Object[] params={lsdate,ledate};
        
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
     * @param sdate 起始日期
     * @param edate 截止日期
     * @param start 起始记录数
     * @param limit 每页记录总数
     * @return  专辑集合
     * @throws SQLException
     */
    @SuppressWarnings("unchecked")
    public List<Playlist> getPlaylist(Date sdate,Date edate,int start,int limit) throws SQLException{
        Long lsdate = sdate.getTime();
        Long ledate = edate.getTime();
        String sql = getDynamicSql(null,0,start,limit);
        log.info("getPlaylist sql =====>"+sql);
        
        Object[] params={lsdate,ledate};
        
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
}
