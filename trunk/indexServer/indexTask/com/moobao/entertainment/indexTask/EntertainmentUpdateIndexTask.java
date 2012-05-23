package com.moobao.entertainment.indexTask;

import java.io.IOException;
import java.util.List;

import jeasy.analysis.MMAnalyzer;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;

import com.moobao.analisis.AnalysisFactory;
import com.moobao.config.PropertyConfiguration;
import com.moobao.indexProcessOptimize.SetOptimize;
import com.moobao.indexser.EntertainmentIndexser;
import com.moobao.service.entertainment.EntertainmentDataService;

/**
 * 娱乐中心索引文件的更新(status=1,ifindex=0)
 * 
 * @author liuxueyong
 */
public class EntertainmentUpdateIndexTask {
	// 获取娱乐中心索引存放的位置.
	private String indexPath = PropertyConfiguration.getEntertainmentIndexPath();
	// 初始化一个IndexWriter.
	private IndexWriter writer = null;
	// 初始化一个IndexReader.
	private IndexReader reader = null;

	// 获取到分词器.
	private static final MMAnalyzer analyser = AnalysisFactory
			.getIndexingAnalisis();

	// 初始化一个EntertainmentDataService对象.
	EntertainmentDataService entDataService = null;
	private static final int MAX_UPDATE_DOCS = 10000;
	
	/**
	 * 更新一个Document
	 * @param doc
	 * @exception IOException
	 * @return null
	 */
	public synchronized boolean indexUpdate(int entId) {

		entDataService = new EntertainmentDataService();
		Document doc = entDataService.getAEntertaimentData_new(entId);
		boolean flag = false;
		
		try {
			// 删除Document
			reader = IndexReader.open(indexPath);
			Term term = new Term("entID", entId + "");
			reader.deleteDocuments(term);
			flag = true;
		} catch (IOException ex) {
			ex.printStackTrace();
			System.err.println("删除Document失败!");
			flag = false;
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		if (doc != null) {
			try {
				// 重新添加
				writer = new IndexWriter(indexPath, analyser, false);
				writer.addDocument(doc);
				writer.optimize();
				writer.close();
				flag = true;
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("更新文档失败!");
				flag = false;
			} finally {
				try {
					writer.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			// 修改标志位.
			entDataService.setAEntertainmentIndexed(entId);
		}
		else {
			System.out.println( "没有此Id!" );
		}
		
		return flag;
	}

	/**
	 * 更新一个Document链表 如果索引文件中有这些Document,先删后加入.
	 * @param listdoc
	 * @exception IOException
	 * @return null
	 */
	public synchronized void indexUpdate() {

		// 获得status=1,ifindex=0的娱乐信息数量Num
		entDataService = new EntertainmentDataService();
		int Num = entDataService.getAddEntertainmentNums();
		System.out.println( "娱乐中心更新的数量:" + Num );
		if( Num == 0 )
			return ;

		List<Document> listdoc = null;
		try {
			// 删除Document列表

			reader = IndexReader.open(indexPath);
			// 初始化一个EntertainmentIndexser对象.
			EntertainmentIndexser entIndexser = new EntertainmentIndexser(reader);
			for (int i = 0; i < Num; i = i + MAX_UPDATE_DOCS) {
				// 每次返回2万个Document.
				listdoc = entDataService.getAddEntertainmentDatas_new(i, i
						+ MAX_UPDATE_DOCS);
				if( listdoc.size() > 0 && listdoc != null ) 
				// 删除列表.
					entIndexser.indexDel(listdoc);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("删除批量文档失败!");
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		try {
			writer = new IndexWriter(indexPath, analyser, false);
			writer = SetOptimize.setParameter( writer );                 //建索引过程优化.
			
			// 初始化一个PhoneIndexser对象.
			EntertainmentIndexser entIndexser = new EntertainmentIndexser(writer);
			// 重新添加Document列表
			for (int i = 0; i < Num; i = i + MAX_UPDATE_DOCS) {
				// 每次返回2万个Document.
				listdoc = entDataService.getAddEntertainmentDatas_new(i, i
						+ MAX_UPDATE_DOCS);
				if( listdoc.size() > 0 && listdoc != null ) 
				// 增加列表.
					entIndexser.indexADD(listdoc);
			}
			writer.optimize();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("更新批量文档失败!");
		} finally {
			try {
				writer.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		// 更新未索引的标志位.
		entDataService.setUnIndexEntertainmentIndexed();
	}

	public static void main(String[] args) {
		EntertainmentUpdateIndexTask ts = new EntertainmentUpdateIndexTask();
		ts.indexUpdate();
	}
}
