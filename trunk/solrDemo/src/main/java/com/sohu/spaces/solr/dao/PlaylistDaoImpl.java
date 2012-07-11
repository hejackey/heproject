package com.sohu.spaces.solr.dao;

import org.apache.commons.dbutils.handlers.BeanHandler;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.sohu.spaces.solr.model.Playlist;

public class PlaylistDaoImpl extends AbstractPlayListDao {

    @Override
    protected BeanListHandler<? extends Object> getBeanListHandler() {
        return new BeanListHandler<Playlist>(Playlist.class);
    }

    @Override
    protected BeanHandler<? extends Object> getBeanObjectHandler() {
        return new BeanHandler<Playlist>(Playlist.class); 
    }

    @Override
    public String getDynamicSql(Long id, int flag, int page, int pageSize) {
        StringBuffer sb = new StringBuffer();
        if(flag == 0)
            sb.append("select id,userid as userId,title,coverurl as coverUrl,status,tag,description,createtime as createTime,lastmodified as lastModified," +
            		"videocount as videoCount,cate_code as cateCode,createfrom as createFrom,create_ip as createIp from playlist  where 1=1 ");
        else 
            sb.append("select count(1) from playlist where 1=1 ");
      
        if (id != null) {//有id条件时只查询id，否则按创建日期范围查询
            sb.append(" and id = ?");
        } else {
            sb.append(" and createtime >=? ");
            sb.append(" and createtime <= ?");
            sb.append("  order by createtime desc");
            
            if(flag == 0){
                sb.append(" limit ");
                sb.append((page-1)*pageSize);
                sb.append(",");
                sb.append(pageSize);
            }
        }
       
        return sb.toString();
    }
    
    public String getDynamicSqlCreatetimeNull(Long id, int flag, int page, int pageSize) {
        StringBuffer sb = new StringBuffer();
        if(flag == 0)
            sb.append("select id,userid as userId,title,coverurl as coverUrl,status,tag,description,createtime as createTime,lastmodified as lastModified," +
                    "videocount as videoCount,cate_code as cateCode,createfrom as createFrom,create_ip as createIp from playlist  where createtime=? ");
        else 
            sb.append("select count(1) from playlist where createtime=? ");
      
        if(flag == 0){
            sb.append(" limit ");
            sb.append((page-1)*pageSize);
            sb.append(",");
            sb.append(pageSize);
        }
       
        return sb.toString();
    }
}
