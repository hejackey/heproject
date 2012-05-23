package com.moobao.entertainment.indexTask;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;

import com.moobao.config.PropertyConfiguration;
import com.moobao.indexser.EntertainmentIndexser;
import com.moobao.service.entertainment.EntertainmentDataService;

/**
 * 娱乐信息索引文件的删除(status=0).
 * 
 * @author liuxueyong
 */
public class EntertainmentDeleteIndexTask {
	// 获取娱乐中心索引存放的位置.
	private static final String indexPath = PropertyConfiguration.getEntertainmentIndexPath();
	private static final int MAX_UPDATE_DOCS = 5000;
	/**
	 * 删除一个Document列表. (status为0).
	 * @param list
	 * @throws IOException
	 * @return null
	 */
	public void deleteIndex() {

		// 初始化一个IndexReader.
		IndexReader reader = null;
		// 初始化一个PhoneIndexser对象.
		EntertainmentIndexser entIndexser = null;
		// 初始化一个PhoneDataService对象
		EntertainmentDataService entDataService = new EntertainmentDataService();

		// 获得已status=0的娱乐信息的数量
		int num = entDataService.getDelEntertainmentNums();
		System.out.println( "娱乐中心要删除记录:" + num );
		if( num == 0 )
			return ;
		
		List<Document> listdoc = null;

		try {
			reader = IndexReader.open(indexPath);
			//每次得到MAX_UPDATE_DOCS万条.
			for (int i = 0; i < num; i = i + MAX_UPDATE_DOCS) {
				listdoc = entDataService.getDelEntertainmentDatas_new(i, i + MAX_UPDATE_DOCS);
				
				if( listdoc.size() > 0 && listdoc != null ) {
				    for (int j = 0; j < listdoc.size(); j++) {
				    	entIndexser = new EntertainmentIndexser(reader);
				    	entIndexser.indexDel(listdoc);
				    }
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("删除批量文档失败!");
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 修改标志位.
		entDataService.setDelEntertainmentIndexed();

	}

	/**
	 * 删除一个指定的Document
	 * 
	 * @param entId
	 * @exception IOException
	 * @return null
	 */
	public void deleteIndex(int entId) {
		EntertainmentIndexser entIndexser = null;
		IndexReader reader = null;
		try {
			reader = IndexReader.open(indexPath);
			entIndexser = new EntertainmentIndexser(reader);
			entIndexser.indexDel(entId + "");
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("删除文档失败!");
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 修改标志位
		EntertainmentDataService entDataService = new EntertainmentDataService();
		entDataService.setAEntertainmentIndexed(entId);
		
	 }
	 public static void main( String[] args ) {
	     EntertainmentDeleteIndexTask ts = new EntertainmentDeleteIndexTask();
	     ts.deleteIndex();
	 }
}
