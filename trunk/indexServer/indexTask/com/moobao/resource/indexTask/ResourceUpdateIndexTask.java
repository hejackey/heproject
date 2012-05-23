package com.moobao.resource.indexTask;

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
import com.moobao.indexser.ResourceIndexser;
import com.moobao.service.resource.ResourceDataService;

/**
 * 资讯索引文件的更新.
 * @author liuxueyong
 */
public class ResourceUpdateIndexTask {
	//获取资讯索引存放的位置.
	private static final String indexPath = PropertyConfiguration.getResourceIndexPath();
	//初始化一个IndexWriter.
	private IndexWriter writer = null;
	private IndexReader reader = null;
	//初始化一个ResourceIndexser对象.
	ResourceIndexser resourceIndexser = null;
	//获取到分词器.
	private static final MMAnalyzer analyser = AnalysisFactory.getIndexingAnalisis();
	
	// 初始化一个PhoneDataService对象.
	ResourceDataService resourceDataService = null;
	private static final int MAX_UPDATE_DOCS = 10000;
	
	/**
     * 更新一个Document
     * @param doc
     * @exception IOException
     * @return null
     */
    public synchronized boolean indexUpdate(int rid) {
    	resourceDataService = new ResourceDataService();
		Document doc = resourceDataService.getAResourceDatas_new(rid);
		boolean flag = false;
		
		try {
			// 删除Document
			reader = IndexReader.open(indexPath);
			Term term = new Term("articleID", rid + "");
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
			resourceDataService.setAResourceIndexed(rid);
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
    public void indexUpdate(){
    	// 获得上未索引的资讯数量Num
    	resourceDataService = new ResourceDataService();
		int Num = resourceDataService.getUnIndexedResourceNums();
		System.out.println( "资讯要更新的数量:" + Num );
		if( Num == 0 )
			return ;
		List<Document> listdoc = null;
		try {
			// 删除Document列表

			reader = IndexReader.open(indexPath);
			// 初始化一个PhoneIndexser对象.
			ResourceIndexser resourceIndexser = new ResourceIndexser(reader);
			for (int i = 0; i < Num; i = i + MAX_UPDATE_DOCS) {
				// 每次返回2万个Document.
				listdoc = resourceDataService.getUnIndexedResourceDatas_new(i, i
						+ MAX_UPDATE_DOCS);
				if( listdoc.size() > 0 && listdoc != null )
				// 删除列表.
				    resourceIndexser.indexDel(listdoc);
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
			ResourceIndexser resourceIndexser = new ResourceIndexser(writer);
			// 重新添加Document列表
			for (int i = 0; i < Num; i = i + MAX_UPDATE_DOCS) {
				// 每次返回2万个Document.
				listdoc = resourceDataService.getUnIndexedResourceDatas_new(i, i
						+ MAX_UPDATE_DOCS);
				if( listdoc.size() > 0 && listdoc != null )
				// 增加列表.
				    resourceIndexser.indexADD(listdoc);
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
		resourceDataService.setUnIndexedResourceIndexed();
    }
    
    public static void main( String[] args ) {
    	ResourceUpdateIndexTask t= new ResourceUpdateIndexTask();
    	t.indexUpdate();
    }
}
