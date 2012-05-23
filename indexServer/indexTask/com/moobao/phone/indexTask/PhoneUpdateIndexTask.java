package com.moobao.phone.indexTask;

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
import com.moobao.indexlogs.IndexLogs;
import com.moobao.indexser.PhoneIndexser;
import com.moobao.service.phone.PhoneDataService;

/**
 * 手机索引文件的更新. 包括(上架,未索引要update)
 * 
 * @author liuxueyong
 */
public class PhoneUpdateIndexTask {
	// 获取手机索引存放的位置.
	private String indexPath = PropertyConfiguration.getPhoneIndexPath();
	// 初始化一个IndexWriter.
	private IndexWriter writer = null;
	// 初始化一个IndexReader.
	private IndexReader reader = null;

	// 获取到分词器.
	private static final MMAnalyzer analyser = AnalysisFactory
			.getIndexingAnalisis();

	// 初始化一个PhoneDataService对象.
	PhoneDataService phoneDataService = null;
	private static final int MAX_UPDATE_DOCS = 10000;

	/**
	 * 向索引文件增加单个Document
	 * 
	 * @param doc
	 * @exception IOException
	 * @return null
	 */
	// public void addIndex( Document doc ) {
	//	
	// try {
	// writer = new IndexWriter( indexPath, analyser, false );
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// phoneIndexser = new PhoneIndexser( writer );
	// phoneIndexser.indexADD( doc );
	//			
	// try {
	// writer.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// System.err.println( "增加单个Document失败!" );
	// }
	//	
	// }
	/**
	 * 向索引文件批量增加Document
	 * 
	 * @param doc
	 * @exception IOException
	 * @return null
	 */
	// public void addIndex( List<Document> listdoc ) {
	//		
	// try {
	// writer = new IndexWriter( indexPath, analyser, false );
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// phoneIndexser = new PhoneIndexser( writer );
	// phoneIndexser.indexADD( listdoc );
	//			
	// try {
	// writer.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// System.err.println( "批量增加Document失败!" );
	// }
	//	
	// }
	/**
	 * 更新一个Document
	 * @param doc
	 * @exception IOException
	 * @return null
	 */
	public synchronized boolean indexUpdate(int pid) {

		phoneDataService = new PhoneDataService();
		Document doc = phoneDataService.getAPhoneData(pid);
		boolean flag = false;
		
		try {
			// 删除Document
			reader = IndexReader.open(indexPath);
			Term term = new Term("phonePrimaryId", pid + "");
			reader.deleteDocuments(term);
			flag = true;
		} catch (IOException ex) {
			ex.printStackTrace();
			flag = false;
			System.err.println("删除Document失败!");
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		if( flag ) {
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
				phoneDataService.setAPhoneIndexed(pid);
			}
			else {
				System.out.println( "没有此Id!" );
			}
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

		// 获得上架销售而未索引的手机数量Num
		phoneDataService = new PhoneDataService();
		int Num = phoneDataService.getUnIndexedPhoneNums();
		boolean flag = false;
		System.out.println("phone要更新的数量:" + Num);
		IndexLogs.setLogs("phone要更新的数量: " + Num, this.getClass().getName());
		if( Num == 0 ) {
			return;
		}
		List<Document> listdoc = null;
		try {
			// 删除Document列表

			reader = IndexReader.open(indexPath);
			// 初始化一个PhoneIndexser对象.
			PhoneIndexser phoneIndexser = new PhoneIndexser(reader);
			for (int i = 0; i < Num; i = i + MAX_UPDATE_DOCS) {
				// 每次返回2万个Document.
				listdoc = phoneDataService.getUnindexedPhoneDatas(i, i
						+ MAX_UPDATE_DOCS);
				if( listdoc.size() > 0 && listdoc != null ) {
					// 删除列表.
				    phoneIndexser.indexDel(listdoc);
					IndexLogs.setLogs("删除List列表: " + i, this.getClass().getName());
				}
			}
			flag = true;
		} catch (IOException e) {
			e.printStackTrace();
			IndexLogs.setLogs("phone delete of update is exception!", this.getClass().getName());
			System.err.println("删除批量文档失败!");
			flag = false;
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		if( flag ) {
			try {
				writer = new IndexWriter(indexPath, analyser, false);
				writer = SetOptimize.setParameter( writer );                 //建索引过程优化.
				
				// 初始化一个PhoneIndexser对象.
				PhoneIndexser phoneIndexser = new PhoneIndexser(writer);
				// 重新添加Document列表
				for (int i = 0; i < Num; i = i + MAX_UPDATE_DOCS) {
					// 每次返回2万个Document.
					listdoc = phoneDataService.getUnindexedPhoneDatas(i, i
							+ MAX_UPDATE_DOCS);
					if( listdoc.size() > 0 && listdoc != null ) {
						// 增加列表.
					    phoneIndexser.indexADD(listdoc);
						IndexLogs.setLogs("添加List列表: " + i, this.getClass().getName());
					}
				}
				// 更新上架未索引的标志位.
				phoneDataService.setUnIndexedPhoneIndexed();
				writer.optimize();
			} catch (IOException e) {
				e.printStackTrace();
				IndexLogs.setLogs("phone add of update is exception!", this.getClass().getName());
				System.err.println("更新批量文档失败!");
			} finally {
				try {
					writer.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		PhoneUpdateIndexTask ts = new PhoneUpdateIndexTask();
		ts.indexUpdate();
	}
}
