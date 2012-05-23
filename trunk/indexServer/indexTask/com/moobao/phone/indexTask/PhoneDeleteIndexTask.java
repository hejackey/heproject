package com.moobao.phone.indexTask;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;

import com.moobao.config.PropertyConfiguration;
import com.moobao.indexser.PhoneIndexser;
import com.moobao.service.phone.PhoneDataService;

/**
 * 手机索引文件的删除. 包括(未索引,已删除;已下架).
 * 
 * @author liuxueyong
 */
public class PhoneDeleteIndexTask {
	// 获取手机索引存放的位置.
	private static final String indexPath = PropertyConfiguration
			.getPhoneIndexPath();
	private static final int MAX_UPDATE_DOCS = 5000;
	/**
	 * 删除一个Document列表. (未索引,已删除的)和(已下架).
	 * @param list
	 * @throws IOException
	 * @return null
	 */
	public void deleteIndex() {

		// 初始化一个IndexReader.
		IndexReader reader = null;
		// 初始化一个PhoneIndexser对象.
		PhoneIndexser phoneIndexser = null;
		// 初始化一个PhoneDataService对象
		PhoneDataService phoneDataService = new PhoneDataService();

		// 获得已删除了的手机数量
		int num = phoneDataService.getDeletedPhoneNums();
		List<Document> listdoc = null;
		
		// 获得已下架了的手机数量
//		int num1 = phoneDataService.getUnSalePhoneNums();
//		List<Document> listdoc1 = null;
		if( num == 0 ) {
			System.out.println( "没有已删除的手机数据!" );
		}
		else {
			try {
				reader = IndexReader.open(indexPath);
				//每次得到MAX_UPDATE_DOCS万条.
				for (int i = 0; i < num; i = i + MAX_UPDATE_DOCS) {
					listdoc = phoneDataService.getDeletedPhoneDatas(i, i + MAX_UPDATE_DOCS);
					
					if( listdoc.size() > 0 && listdoc != null ) {
					    for (int j = 0; j < listdoc.size(); j++) {
						    phoneIndexser = new PhoneIndexser(reader);
						    phoneIndexser.indexDel(listdoc);
					    }
					}
				}
		    } catch (IOException e) {
			    e.printStackTrace();
			    System.err.println("删除ifdelete批量文档失败!");
		    } finally {
			    try {
				    reader.close();
			    } catch (IOException e) {
				    e.printStackTrace();
			    }
		    }
		}
		
		
//		if( num1 == 0 ) {
//			System.out.println( "没有已下架了的手机数据!" );
//		}
//		else {
//			try {
//				reader = IndexReader.open(indexPath);
//				//每次得到MAX_UPDATE_DOCS条.
//				for (int i = 0; i < num1; i = i + MAX_UPDATE_DOCS) {
//					listdoc1 = phoneDataService.getUnSalePhonePhoneDatas(i,
//							i + MAX_UPDATE_DOCS);
//					if( listdoc1.size() > 0 && listdoc1 != null ) {
//					    for (int j = 0; j < listdoc1.size(); j++) {
//						    phoneIndexser = new PhoneIndexser(reader);
//						    phoneIndexser.indexDel(listdoc1);
//					    }
//					}
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//				System.err.println("删除已下架文档失败!");
//			} finally {
//				try {
//					reader.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
		
//		if( num != 0 || num1 != 0 )
		if( num != 0 )
			// 修改标志位.
			phoneDataService.setDeletedPhoneIndexed();
	}

	/**
	 * 删除一个指定的Document
	 * 
	 * @param keyid
	 * @exception IOException
	 * @return null
	 */
	public void deleteIndex(int pid) {
		PhoneIndexser phoneIndexser = null;
		IndexReader reader = null;
		try {
			reader = IndexReader.open(indexPath);
			phoneIndexser = new PhoneIndexser(reader);
			phoneIndexser.indexDel(pid + "");
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
		//修改标志位
		PhoneDataService phoneDataService = new PhoneDataService();
		phoneDataService.setAPhoneIndexed(pid);
	 }
	 public static void main( String[] args ) {
	     PhoneDeleteIndexTask ts = new PhoneDeleteIndexTask();
	     ts.deleteIndex();
	 }
}
