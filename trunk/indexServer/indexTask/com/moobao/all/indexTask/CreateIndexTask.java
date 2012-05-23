package com.moobao.all.indexTask;

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
import com.moobao.indexser.AllIndexser;
import com.moobao.restartIndex.HttpClientRestart;
import com.moobao.service.all.AllDataService;

/**
 * 所有索引文件的创建(包括手机,配件...). 对上架销售的手机全建索引文件(相对于数据库).
 * 
 * @author liuxueyong
 */
public class CreateIndexTask {

	// 获取全部索引存放的位置.
	private static final String indexPath = PropertyConfiguration
			.getAllIndexPath();
	private static final int MAX_UPDATE_DOCS = 2000;
	// 获取到分词器.
	private static final MMAnalyzer analyser = AnalysisFactory.getIndexingAnalisis();
	
	/**
	 * 删除索引文件后,创建新的索引文件.
	 * @param Document
	 * @exception IOException
	 * @return null
	 */
	public void createIndex() {

		// 初始化一个PhoneIndexser对象.
		AllIndexser indexser = null;
		// 初始化一个IndexWriter.
		IndexWriter writer = null;
		// 得到一个phoneDataService对象.
		AllDataService dataService = new AllDataService();
		try {
			
			writer = new IndexWriter(indexPath, analyser, true);
			writer.setUseCompoundFile(true);                             // 采用复合索引.
			writer = SetOptimize.setParameter( writer );                 //建索引过程优化.
			
			indexser = new AllIndexser(writer);
			// 获得上架销售的手机数量upNum
			int upNum = dataService.getAllPhoneNums();
			System.out.println( "满足条件的Document数量:" +  upNum );
			
			if( upNum != 0 ) {
				Date startTime = new Date();
				
				List<Document> listdoc = null;
				for (int i = 0; i < upNum; i = i + MAX_UPDATE_DOCS) {
					// 第次追加2万个Document.
					listdoc = dataService.getAllPhoneDatas(i, i
							+ MAX_UPDATE_DOCS);
					if( listdoc.size() > 0 && listdoc != null )
						indexser.indexADD(listdoc);
				}
				
				Date endTime = new Date();
				long Time = endTime.getTime() - startTime.getTime(); //建索引时间.
				System.out.println( "消耗时间:" + GetSeconds.convertSeconds(Time) + "s" );
				
				// 更新数据库的标志位
				// 更新所有上架销售手机为已被索引状态
				dataService.setUnIndexedPhoneIndexed();
				
				//日志
				IndexLogs.setLogs("phone index is reCreated!", this.getClass().getName());
			}	
			else{
				System.out.println("没有记录, 因此没创建手机索引!");
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
		
		//重新加载手机索引.
		try {
			HttpClientRestart.getPostMethod( "RESTART_ALL_INDEX" );
			IndexLogs.setLogs("all product index is restarted!", this.getClass().getName());
		}
		catch( Exception e ) {
			IndexLogs.setLogs( "重启全部索引异常!", this.getClass().getName() );
		}
	}
	
	public static void main( String[] args ) {
		CreateIndexTask task = new CreateIndexTask();
		task.createIndex();
	}
}
