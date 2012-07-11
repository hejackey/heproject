package com.sohu.spaces.solr.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.common.SolrInputDocument;

import com.sohu.spaces.solr.client.SolrIndexClient;
import com.sohu.spaces.solr.dao.PlaylistDaoImpl;
import com.sohu.spaces.solr.model.Playlist;
import com.sohu.spaces.solr.util.ConstantUtil;
import com.sohu.spaces.solr.util.DateUtil;
import com.sohu.spaces.solr.util.VideoIndexUtil;

/**
 * ugc专辑全量建索引
 * @author xiaolianghe
 *
 */
public class PlaylistIndexService {
    private static EmbeddedSolrServer plServer = SolrIndexClient.getEmbeddedSolrServer(ConstantUtil.PL_EMBED_SOLR_HOME, ConstantUtil.PL_EMBED_SOLR_CORE_NAME);
    private static final String PL_SOLR_SERVER_URL = ConstantUtil.PL__SOLR_SERVER_URL;
    private static final PlaylistDaoImpl dao = new PlaylistDaoImpl();
    
    public void allPlaylistIndex(){
        try {
            String sedate = ConstantUtil.PL_ALL_INDEX_START_DATE;
            Date dsdate =DateUtil.formatStr2Date(sedate, "yyyy-MM-dd");
            Date dedate = DateUtil.formatStr2Date(ConstantUtil.PL_ALL_INDEX_END_DATE, "yyyy-MM-dd");
            
            int count = 0;
            int page = 0;
            Date curSDate = null;
            Date curEDate = null;
            
            while(true){
                //退出
                if (DateUtil.compDateTime(dedate,dsdate )){
                    break;
                }
                
                curSDate = DateUtil.formatStr2Date( DateUtil.formatDate2Str(dedate, "yyyy-MM-dd") + " 00:00:00" ,"yyyy-MM-dd HH:mm:ss");
                curEDate = DateUtil.formatStr2Date( DateUtil.formatDate2Str(dedate, "yyyy-MM-dd") + " 23:59:59" ,"yyyy-MM-dd HH:mm:ss");
                try {
                    count = dao.countPlayList(0,curSDate, curEDate);
                    
                    if (count > 0) {
                        page = VideoIndexUtil.getPage(count);
                        
                        for (int i = 1; i <= page; i++) {
                            List<Playlist> playlistList = dao.getPlaylist(0, curSDate,  curEDate, i, ConstantUtil.INDEX_PAGE_SIZE);
                            
                            if (playlistList != null && playlistList.size() > 0) {
                                
                                try {
                                    Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
                                    docs = VideoIndexUtil.putPlaylistToDoc(playlistList,docs);
                                    
                                    SolrIndexClient.singleIndexAdd(PL_SOLR_SERVER_URL, docs, plServer);
                                    SolrIndexClient.singleIndexCommit(PL_SOLR_SERVER_URL, docs, plServer);
                                } catch (SolrServerException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    
                    dedate = DateUtil.addMinDay(dedate, -1);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    
    public void allPlaylistIndexCreatetimeNull(){
        int count = 0 ;
        int page = 0;
        try {
            count = dao.countPlayList(1,new Date(),new Date());
            
            if (count > 0) {
                page = VideoIndexUtil.getPage(count);
                
                for (int i = 1; i <= page; i++) {
                    List<Playlist> playlistList = dao.getPlaylist(1,new Date(),new Date(),  i, ConstantUtil.INDEX_PAGE_SIZE);
                    
                    if (playlistList != null && playlistList.size() > 0) {
                        
                        try {
                            Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
                            docs = VideoIndexUtil.putPlaylistToDoc(playlistList,docs);
                            
                            SolrIndexClient.singleIndexAdd(PL_SOLR_SERVER_URL, docs, plServer);
                            SolrIndexClient.singleIndexCommit(PL_SOLR_SERVER_URL, docs, plServer);
                        } catch (SolrServerException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        PlaylistIndexService service = new PlaylistIndexService();
        service.allPlaylistIndex();
        service.allPlaylistIndexCreatetimeNull();
        System.exit(0);
    }
}
