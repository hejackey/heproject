package com.sohu.spaces.solr.dao;

import org.apache.commons.dbutils.handlers.BeanHandler;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.sohu.spaces.solr.model.VideoInfo;

/**
 * UGC视频全量索引数据提取
 * @author xiaolianghe
 *
 */
public class VideoInfoDaoImpl extends AbstractVideoInfoDao {
    private static VideoInfoDaoImpl instance = null;
    
    private VideoInfoDaoImpl(){
        
    }
    
    public synchronized static VideoInfoDaoImpl getInstantce(){
        if (instance != null) {
            return instance;
        } else {
            return new VideoInfoDaoImpl(); 
        }
    }
    
    /**
     * 查询列表结果封装list
     * @return
     */
    protected BeanListHandler<VideoInfo> getBeanListHandler(){
        return new BeanListHandler<VideoInfo>(VideoInfo.class);
    }
    
    /**
     * 查询对象结果封装object
     * @return
     */
    protected BeanHandler<VideoInfo> getBeanObjectHandler(){
        return new BeanHandler<VideoInfo>(VideoInfo.class); 
    }
    
    /**
     * 封装查询视频sql语句
     * @param vid 视频id
     * @param flag  0、集合查询 1、统计查询 
     * @param page  起始记录
     * @param pageSize  每页记录总数
     * @return  拼接后的sql语句
     */
    public String getDynamicSql(Long vid,int flag,int page,int pageSize){
        StringBuffer sb = new StringBuffer();
        if(flag == 0)
            sb.append("select id,title,tag,categoriesid as categoriesId,introduction,status,videolength as videoLength,videosize as videoSize," +
                    "uploadtime as uploadTime,uploadip as uploadIp,cuscoverurl as cusCoverURL,cutcoverurl as cutCoverURL,userid as userId," +
                    "lastmodified as lastModified,videotype as videoType,uploadfrom as uploadFrom,plevel,ver_type as verType,play_limit as playLimit," +
                    "cate_code as cateCode from videoinfo  where 1=1 ");
        else 
            sb.append("select count(1) from videoinfo where 1=1 ");
      
        if (vid != null) {//有id条件时只查询id，否则按最后修改日期范围查询
            sb.append(" and id = ?");
        } else {
            sb.append(" and lastmodified >=? ");
            sb.append(" and lastmodified <= ?");
            sb.append("  order by lastmodified desc");
            
            if(flag == 0){
                sb.append(" limit ");
                sb.append((page-1)*pageSize);
                sb.append(",");
                sb.append(pageSize);
            }
        }
       
        return sb.toString();
    }
}
