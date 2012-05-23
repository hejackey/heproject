package com.moobao.resource.indexTask;

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
import com.moobao.indexser.ResourceIndexser;
import com.moobao.service.resource.ResourceDataService;

/**
 * 资讯索引文件的创建.
 * 
 * @author liuxueyong
 */
public class ResourceCreateIndexTask {

	// 获取资讯索引存放的位置.
	private static final String indexPath = PropertyConfiguration
			.getResourceIndexPath();
	// 初始化一个ResourceIndexser对象.
	ResourceIndexser resourceIndexser = null;
	// 获取到分词器.
	private static final MMAnalyzer analyser = AnalysisFactory.getIndexingAnalisis();
	private static final int MAX_UPDATE_DOCS = 2000;
	/**
	 * 删除索引文件后,创建新的索引文件.
	 * 
	 * @param Document
	 * @exception IOException
	 * @return null
	 */
	public void createIndex() {
		// 初始化一个IndexWriter.
		IndexWriter writer = null;
		ResourceDataService resourceDataService = new ResourceDataService();
		try {
			writer = new IndexWriter(indexPath, analyser, true);
			writer.setUseCompoundFile(true);                             // 采用复合索引. 
			writer = SetOptimize.setParameter( writer );                 //建索引过程优化.
			
			resourceIndexser = new ResourceIndexser(writer);
			int upNum = 0;
			Date startTime = new Date();
			for( int i = 1; i < 5; i ++ ) {
				// 获得上架销售的资讯数量upNum
				upNum = resourceDataService.getResourceNumsByClassID(i);
				System.out.println( "满足条件的Document数量:" + upNum );
				
				List<Document> listdoc = null;
				for (int j = 0; j < upNum; j = j + MAX_UPDATE_DOCS) {
					// 第次追加2万个Document.
					listdoc = resourceDataService.getResourceDatasByClassID_new(i, j, j + MAX_UPDATE_DOCS);
					if( listdoc.size() > 0 && listdoc != null )
					    resourceIndexser.indexADD(listdoc);
				}
			}
			
			
			Date endTime = new Date();
			long Time = endTime.getTime() - startTime.getTime(); //建索引时间.
			System.out.println( "消耗时间:" + GetSeconds.convertSeconds(Time) + "s" );
			
			// 更新数据库的标志位
			// 更新所有上资讯为已被索引状态
			resourceDataService.setUnIndexedResourceIndexed();
			//日志
			IndexLogs.setLogs("resource index is reCreated!", this.getClass().getName());
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
	}
	public static void main( String[] args ) {
		ResourceCreateIndexTask t = new ResourceCreateIndexTask();
		t.createIndex();
	}
}
