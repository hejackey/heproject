package com.moobao.netComputer.indexTask;

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
import com.moobao.indexser.NetComputerIndexser;
import com.moobao.indexser.PeiJianIndexser;
import com.moobao.service.netcomputer.NetComputerDataService;

/**
 * 上网本索引文件的更新. 包括(上架,未索引要update)
 * 
 * @author liuxueyong
 */
public class NetComputerUpdateIndexTask {

	private static final int MAX_UPDATE_DOCS = 10000;
	// 获取上网本索引存放的位置.
	private static final String indexPath = PropertyConfiguration
			.getNetComputerIndexPath();
	// 获取到分词器.
	private static final MMAnalyzer analyser = AnalysisFactory
			.getIndexingAnalisis();
	
	
	/**
	 * 更新一个Document
	 * @param doc
	 * @exception IOException
	 * @return null
	 */
	public synchronized boolean indexUpdate(int pid) {

		IndexReader reader = null;
		IndexWriter writer = null;
		NetComputerDataService netComputerDataService = new NetComputerDataService();
		Document doc = netComputerDataService.getAllNetComputerDatas_new(pid);
		boolean flag = false;
		
		try {
			// 删除Document
			reader = IndexReader.open(indexPath);
			Term term = new Term("netComputerPrimaryId", pid + "");
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
			netComputerDataService.setANetComputerIndexed(pid);
		}
		else {
			System.out.println( "没有此Id!" );
		}
		
		return flag;
	}
	
	
	

	/**
	 * 更新一个Document链表
	 * @param listdoc
	 * @exception IOException
	 * @return null
	 */
	public synchronized void indexUpdate() {
		// 获得上架销售而未索引的配件数量Num
		NetComputerDataService netComputerDataService = null;
		netComputerDataService = new NetComputerDataService();
		int Num = netComputerDataService.getUnindexedNetComputerNums();

		System.out.println( "上网本要更新的数量:" + Num );
		if( Num == 0 )
			return ;
		List<Document> listdoc = null;
		IndexReader reader = null;
		// 删除Document列表
		try {
			reader = IndexReader.open(indexPath);
			NetComputerIndexser netComputerIndexser = new NetComputerIndexser(reader);

			for (int i = 0; i < Num; i = i + MAX_UPDATE_DOCS) {
				listdoc = netComputerDataService.getUnindexedNetComputerDatas_new(i, i
						+ MAX_UPDATE_DOCS);
				if( listdoc.size() > 0 && listdoc != null )
					netComputerIndexser.indexDel(listdoc);
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

		IndexWriter writer = null;
		try {
			writer = new IndexWriter(indexPath, analyser, false);
			writer = SetOptimize.setParameter( writer );                 //建索引过程优化.
			
			NetComputerIndexser netComputerIndexser = new NetComputerIndexser(writer);
			// 重新添加Document列表
			for (int i = 0; i < Num; i = i + MAX_UPDATE_DOCS) {
				// 每次返回2万个Document.
				listdoc = netComputerDataService.getUnindexedNetComputerDatas_new(i, i
						+ MAX_UPDATE_DOCS);
				if( listdoc.size() > 0 && listdoc != null )
				// 增加一个列表.
					netComputerIndexser.indexADD(listdoc);
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
		// 更新上架未索引的标志位.
		netComputerDataService.setUnIndexedNetComputerIndexed();
	}
	
	public static void main(String[] args) {
		NetComputerUpdateIndexTask ts = new NetComputerUpdateIndexTask();
		ts.indexUpdate();
	}
}
