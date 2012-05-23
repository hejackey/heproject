package com.moobao.entertainment.indexTask;

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
import com.moobao.indexser.EntertainmentIndexser;
import com.moobao.service.entertainment.EntertainmentDataService;

/**
 * 娱乐中心索引文件的创建. 对上架销售的手机全建索引文件(相对于数据库).
 * @author liuxueyong
 */
public class EntertainmentCreateIndexTask {

	// 获取娱乐中心索引存放的位置.
	private static final String indexPath = PropertyConfiguration.getEntertainmentIndexPath();
	private static final int MAX_UPDATE_DOCS = 3000;
	// 获取到分词器.
	private static final MMAnalyzer analyser = AnalysisFactory.getIndexingAnalisis();
	
	/**
	 * 删除索引文件后,创建新的索引文件.
	 * @param Document
	 * @exception IOException
	 * @return null
	 */
	public void createIndex() {

		// 初始化一个EntertainmentIndexser对象.
		EntertainmentIndexser enterIndexser = null;
		// 初始化一个IndexWriter.
		IndexWriter writer = null;
		// 得到一个EntertainmentDataService对象.
		EntertainmentDataService enterDataService = new EntertainmentDataService();
		try {			
			writer = new IndexWriter(indexPath, analyser, true);
			writer.setUseCompoundFile(true);                             // 采用复合索引.
			writer = SetOptimize.setParameter( writer );                 //建索引过程优化.
			
			enterIndexser = new EntertainmentIndexser(writer);
			// 获得所有娱乐中心数量upNum
			int upNum = enterDataService.getAllEntertainmentNums();
			System.out.println( "满足条件的Document数量:" +  upNum );
			if( upNum != 0 ) {
				Date startTime = new Date();
				
				List<Document> listdoc = null;
				for (int i = 0; i < upNum; i = i + MAX_UPDATE_DOCS) {
					// 第次追加2万个Document.
					listdoc = enterDataService.getAllEntertainmentDatas_new(i, i
							+ MAX_UPDATE_DOCS);
					if( listdoc.size() > 0 && listdoc != null )
						enterIndexser.indexADD(listdoc);
				}
				
				Date endTime = new Date();
				long Time = endTime.getTime() - startTime.getTime(); //建索引时间.
				System.out.println( "消耗时间:" + GetSeconds.convertSeconds(Time) + "s" );
				
				// 更新数据库的标志位
				// 更新所有娱乐信息为已被索引状态
				enterDataService.setAllEntertainmentIndexed();
				//日志
				IndexLogs.setLogs("entertainment index is reCreated!", this.getClass().getName());
			}
			else{
				System.out.println("没有记录, 因此没创建娱乐中心索引!");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("生成索引文件失败!");
		} finally {
			try {
				writer.optimize();
				writer.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main( String[] args ) {
		EntertainmentCreateIndexTask task = new EntertainmentCreateIndexTask();
		task.createIndex();
	}
}
