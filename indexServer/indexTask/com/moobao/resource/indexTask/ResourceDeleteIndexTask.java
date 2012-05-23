package com.moobao.resource.indexTask;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;

import com.moobao.config.PropertyConfiguration;
import com.moobao.indexser.PeiJianIndexser;
import com.moobao.indexser.ResourceIndexser;
import com.moobao.service.peijian.PeijianDataService;
import com.moobao.service.resource.ResourceDataService;

/**
 * 资讯索引文件的删除.
 * @author liuxueyong
 */
public class ResourceDeleteIndexTask {
	//获取资讯索引存放的位置.
	private static final String indexPath = PropertyConfiguration.getResourceIndexPath();
	//初始化一个ResourceIndexser对象.
	ResourceIndexser resourceIndexser = null;
	private static final int MAX_UPDATE_DOCS = 5000;
	
	/**
	 * 删除一个Document列表.
	 * @param list
	 * @throws IOException
	 * @return null
	 */
	public void deleteIndex() {
		
		ResourceDataService resourceDataService = new ResourceDataService();
		// 获得已删除了的资讯数量
		int num = resourceDataService.getDeletedResourceNums();
		System.out.println( "资讯要删除的数据:" + num );
		if( num == 0 )
			return ;
		List<Document> listdoc = null;

		IndexReader reader = null;
		// 初始化一个PeiJianIndexser对象.
		ResourceIndexser resourceIndexser = null;

		try {
			reader = IndexReader.open(indexPath);
			resourceIndexser = new ResourceIndexser(reader);
			// 第次得到2万条.
			for (int i = 0; i < num; i = i + MAX_UPDATE_DOCS) {
				listdoc = resourceDataService.getDeletedResourceDatas_new(i,
						i + MAX_UPDATE_DOCS);
				if( listdoc.size() > 0 && listdoc != null ) {
				    for (int j = 0; j < listdoc.size(); j++) {
					    resourceIndexser = new ResourceIndexser(reader);
					    resourceIndexser.indexDel(listdoc);
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
		resourceDataService.setDeletedResourceIndexed();
		
	}
	
	/**
	 * 删除一个指定的Document
	 * @param keyid
	 * @exception IOException
	 * @return null
	 */
	public void deleteIndex( int keyid ) {
		
		IndexReader reader = null;
		ResourceIndexser resourceIndexser = null;
		try {
			reader = IndexReader.open(indexPath);
			resourceIndexser = new ResourceIndexser(reader);
			resourceIndexser.indexDel(keyid + "");
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
		//修改标志位.
		ResourceDataService resourceDataService = new ResourceDataService();
		resourceDataService.setAResourceIndexed(keyid);
	}
	public static void main( String[] args ) {
		ResourceDeleteIndexTask t = new ResourceDeleteIndexTask();
		t.deleteIndex();
	}
}
