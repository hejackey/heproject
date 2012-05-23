package com.moobao.netComputer.indexTask;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;

import com.moobao.config.PropertyConfiguration;
import com.moobao.indexser.NetComputerIndexser;
import com.moobao.indexser.PeiJianIndexser;
import com.moobao.service.netcomputer.NetComputerDataService;
import com.moobao.service.peijian.PeijianDataService;

/**
 * 上网本索引文件的删除. 包括(未索引,已删除;已下架).
 * 
 * @author liuxueyong
 */
public class NetComputerDeleteIndexTask {
	// 获取上网本索引存放的位置.
	private static final String indexPath = PropertyConfiguration
			.getNetComputerIndexPath();

	private static final int MAX_UPDATE_DOCS = 5000;
	/**
	 * 删除一个Document列表(未索引,已删除的)和(已下架). 
	 * @param list
	 * @throws IOException
	 * @return null
	 */
	public void deleteIndex() {
		NetComputerDataService netComputerDataService = new NetComputerDataService();
		// 获得已删除了的上网本数量
		int num = netComputerDataService.getDeletedNetComputerNums();
		List<Document> listdoc = null;

		IndexReader reader = null;
		// 初始化一个PeiJianIndexser对象.
		NetComputerIndexser netComputerIndexser = null;

		if( num == 0 ) {
			System.out.println( "没有已删除的上网本!" );
		}
		else {
			try {
				reader = IndexReader.open(indexPath);
				netComputerIndexser = new NetComputerIndexser(reader);
				// 第次得到2万条.
				for (int i = 0; i < num; i = i + MAX_UPDATE_DOCS) {
					listdoc = netComputerDataService.getDeletedNetComputerDatas_new(i,
							i + MAX_UPDATE_DOCS);
					if( listdoc.size() > 0 && listdoc != null ) {
					    for (int j = 0; j < listdoc.size(); j++) {
					    	netComputerIndexser = new NetComputerIndexser(reader);
					    	netComputerIndexser.indexDel(listdoc);
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
		
//		int num1 = netComputerDataService.getUnSaleNetComputerNums();
//		List<Document> listdoc1 = null;
//		if( num1 == 0 ) {
//			System.out.println( "没有已下架的上网本!" );
//		}
//		else {
//			try {
//				reader = IndexReader.open(indexPath);
//				netComputerIndexser = new NetComputerIndexser(reader);			
//				// 第次得到2万条.
//				for (int i = 0; i < num1; i = i + MAX_UPDATE_DOCS) {
//					listdoc1 = netComputerDataService.getUnSaleNetComputerDatas_new(i,
//							i + MAX_UPDATE_DOCS);
//					if( listdoc1.size() > 0 && listdoc1 != null ) {
//					    for (int j = 0; j < listdoc1.size(); j++) {
//					    	netComputerIndexser = new NetComputerIndexser(reader);
//					    	netComputerIndexser.indexDel(listdoc1);
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
			netComputerDataService.setDeletedNetComputerIndexed();
	}

	/**
	 * 删除一个指定的Document
	 * @param keyid
	 * @exception IOException
	 * @return null
	 */
	public void deleteIndex(int keyid) {
		IndexReader reader = null;
		NetComputerIndexser netComputerIndexser = null;
		try {
			reader = IndexReader.open(indexPath);
			netComputerIndexser = new NetComputerIndexser(reader);
			netComputerIndexser.indexDel(keyid + "");
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
		NetComputerDataService netDataService = new NetComputerDataService();
		netDataService.setANetComputerIndexed(keyid);
	}
	public static void main( String[] args ) {
		NetComputerDeleteIndexTask ts = new NetComputerDeleteIndexTask();
		ts.deleteIndex();
	}
}
