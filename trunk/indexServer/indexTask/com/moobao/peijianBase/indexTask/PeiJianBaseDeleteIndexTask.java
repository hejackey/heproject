package com.moobao.peijianBase.indexTask;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;

import com.moobao.config.PropertyConfiguration;
import com.moobao.indexser.PeiJianIndexser;
import com.moobao.service.peijian.PeijianBaseDataService;
import com.moobao.service.peijian.PeijianDataService;

/**
 * 配件索引文件的删除. 包括(未索引,已删除;已下架).
 * 
 * @author liuxueyong
 */
public class PeiJianBaseDeleteIndexTask {
	// 获取配件索引存放的位置.
	private static final String indexPath = PropertyConfiguration
			.getPeiJianBaseIndexPath();

	private static final int MAX_UPDATE_DOCS = 5000;
	/**
	 * 删除一个Document列表(未索引,已删除的)和(已下架). 
	 * @param list
	 * @throws IOException
	 * @return null
	 */
	public void deleteIndex() {
		PeijianBaseDataService peiJianDataService = new PeijianBaseDataService();
		// 获得已删除了的配件数量
		int num = peiJianDataService.getDeletedPeiJianNums();
		List<Document> listdoc = null;

		IndexReader reader = null;
		// 初始化一个PeiJianIndexser对象.
		PeiJianIndexser peiJianIndexser = null;

		if( num == 0 ) {
			System.out.println( "没有已删除的配件!" );
		}
		else {
			try {
				reader = IndexReader.open(indexPath);
				peiJianIndexser = new PeiJianIndexser(reader);
				// 第次得到2万条.
				for (int i = 0; i < num; i = i + MAX_UPDATE_DOCS) {
					listdoc = peiJianDataService.getDeletedPeiJianDatas(i,
							i + MAX_UPDATE_DOCS);
					if( listdoc.size() > 0 && listdoc != null ) {
					    for (int j = 0; j < listdoc.size(); j++) {
						    peiJianIndexser = new PeiJianIndexser(reader);
						    peiJianIndexser.indexDel(listdoc);
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
		}
		
//		int num1 = peiJianDataService.getUnSalePeiJianNums();
//		List<Document> listdoc1 = null;
//		if( num1 == 0 ) {
//			System.out.println( "没有已下架的配件!" );
//		}
//		else {
//			try {
//				reader = IndexReader.open(indexPath);
//				peiJianIndexser = new PeiJianIndexser(reader);			
//				// 第次得到2万条.
//				for (int i = 0; i < num1; i = i + MAX_UPDATE_DOCS) {
//					listdoc1 = peiJianDataService.getUnSalePeiJianDatas_new(i,
//							i + MAX_UPDATE_DOCS);
//					if( listdoc1.size() > 0 && listdoc1 != null ) {
//					    for (int j = 0; j < listdoc1.size(); j++) {
//						    peiJianIndexser = new PeiJianIndexser(reader);
//						    peiJianIndexser.indexDel(listdoc1);
//					    }
//					}
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//				System.err.println("删除批量文档失败!");
//			} finally {
//				try {
//					reader.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
		
		if( num != 0 )
			// 修改标志位.
			peiJianDataService.setDeletedPeiJianIndexed();
	}

	/**
	 * 删除一个指定的Document
	 * @param keyid
	 * @exception IOException
	 * @return null
	 */
	public void deleteIndex(int keyid) {
		IndexReader reader = null;
		PeiJianIndexser peiJianIndexser = null;
		try {
			reader = IndexReader.open(indexPath);
			peiJianIndexser = new PeiJianIndexser(reader);
			peiJianIndexser.indexDel(keyid + "");
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
		
		// 修改标志位.
		PeijianBaseDataService peiJianDataService = new PeijianBaseDataService();
		peiJianDataService.setAPeiJianIndexed(keyid);
	}
	public static void main( String[] args ) {
		PeiJianBaseDeleteIndexTask ts = new PeiJianBaseDeleteIndexTask();
		ts.deleteIndex();
	}
}
