package com.moobao.peijian.indexTask;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import jeasy.analysis.MMAnalyzer;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;

import com.moobao.analisis.AnalysisFactory;
import com.moobao.config.PropertyConfiguration;
import com.moobao.indexProcessOptimize.SetOptimize;
import com.moobao.indexTime.GetSeconds;
import com.moobao.indexlogs.IndexLogs;
import com.moobao.indexser.PeiJianIndexser;
import com.moobao.restartIndex.HttpClientRestart;
import com.moobao.service.peijian.PeijianDataService;

/**
 * 配件索引文件的创建. 对上架销售的配件全建索引文件(相对于数据库).
 * 
 * @author liuxueyong
 */
public class PeijianCreateIndexTask {
	// 获取配件索引存放的位置.
	private static final String indexPath = PropertyConfiguration
			.getPeiJianIndexPath();
	// 初始化一个IndexWriter.
	private IndexWriter writer = null;
	// 初始化一个PeiJianIndexser对象.
	PeiJianIndexser peiJianIndexser = null;
	// 获取到分词器.
	private static final MMAnalyzer analyser = AnalysisFactory
			.getIndexingAnalisis();
	private static final int MAX_UPDATE_DOCS = 2000;
	/**
	 * 删除索引文件后,创建新的索引文件.
	 * 
	 * @param Document
	 * @exception IOException
	 * @return null
	 */
	public void createIndex() {
		PeijianDataService peiJianDataService = new PeijianDataService();
		try {			
			writer = new IndexWriter(indexPath, analyser, true);
			writer.setUseCompoundFile(true);                             // 采用复合索引.
			writer = SetOptimize.setParameter( writer );                 //建索引过程优化.
			
			peiJianIndexser = new PeiJianIndexser(writer);
			// 获得上架销售的配件数量upNum
			int upNum = peiJianDataService.getAllPeiJianNums();
            System.out.println( "满足条件的Document数量:" + upNum );
            if( upNum != 0 ) {
            	Date startTime = new Date();
                
    			List<Document> listdoc = null;
    			for (int i = 0; i < upNum; i = i + MAX_UPDATE_DOCS) {
    				
    		        // 第次追加2万个Document.
    				listdoc = peiJianDataService.getAllPeiJianDatas_new(i, i + MAX_UPDATE_DOCS);
    				if( listdoc.size() > 0 && listdoc != null )  
    				    peiJianIndexser.indexADD(listdoc);
    			}
    			
    			Date endTime = new Date();
    			long Time = endTime.getTime() - startTime.getTime(); //建索引时间.
    			System.out.println( "消耗时间:" + GetSeconds.convertSeconds(Time) + "s" );
    			
    			// 更新数据库的标志位
    			// 更新所有上架销售配件为已被索引状态
    			peiJianDataService.setUnIndexedPeiJianIndexed();
    			
    			//日志
    			IndexLogs.setLogs("peiJian index is reCreated!", this.getClass().getName());
            }
            else{
				System.out.println("没有记录, 因此没创建配件索引!");
			}
            
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("生成索引文件失败!");
		} finally {
			try {
				writer.optimize();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//重新加载配件索引.
		try {
			HttpClientRestart.getPostMethod( "RESTART_PEIJIAN_INDEX" );
			IndexLogs.setLogs("peiJian index is restarted!", this.getClass().getName());
		}
		catch( Exception e ) {
			System.out.println( "重启配件索引异常!" );
		}
	}

	public static void main(String[] args) {
		PeijianCreateIndexTask ts = new PeijianCreateIndexTask();
		ts.createIndex();
	}
}
