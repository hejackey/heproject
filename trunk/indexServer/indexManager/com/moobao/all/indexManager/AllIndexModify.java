package com.moobao.all.indexManager;

import com.moobao.all.indexTask.DeleteIndexTask;
import com.moobao.all.indexTask.IndexBackUpTask;
import com.moobao.all.indexTask.OptimizeIndexTask;
import com.moobao.all.indexTask.UpdateIndexTask;
import com.moobao.config.PropertyConfiguration;
import com.moobao.indexlogs.IndexLogs;
import com.moobao.restartIndex.HttpClientRestart;

/**
 * 调用修改任务,进行索引的修改,
 * 包括(添加, 更新, 删除, 备份等)
 * @author liuxueyong
 */
public class AllIndexModify {
	 
	public void indexModify() {
		
		//重新编译手机view
//		OracleTest newjdbc = new OracleTest();
//		boolean flag = newjdbc.executeQuery("alter view V_MOBILE_INDEX compile");
//		System.out.println( flag );
//		newjdbc.close();
		
//		try {
//			PhoneDataService phoneD = new PhoneDataService();
//			boolean flag = phoneD.reCompileView();
//			System.out.println( flag );
//		}
//		catch( Exception e ) {
//			e.printStackTrace();
//		}
		
		//获取手机索引存放的位置.
		String indexPath = PropertyConfiguration.getAllIndexPath();
		String newPath = PropertyConfiguration.getAllBackUpIndexPath();
		
		DeleteIndexTask del = new DeleteIndexTask();
		UpdateIndexTask update = new UpdateIndexTask();
		IndexBackUpTask backUp = new IndexBackUpTask();
		//更新
		try {
			update.indexUpdate();
			IndexLogs.setLogs("all product index is updated!", this.getClass().getName());
		}
		catch( Exception e ) {
			IndexLogs.setLogs( e.getMessage(), this.getClass().getName() );
			IndexLogs.setLogs( "更新全部索引文件异常!", this.getClass().getName() );
		}
		//删除
		try {
			del.deleteIndex();
			IndexLogs.setLogs("all product index of overdue is deleted!", this.getClass().getName());
		}
		catch( Exception e ) {
			IndexLogs.setLogs( "删除过期的索引文件异常!", this.getClass().getName() );
		}
		//优化 备份
		try {
			OptimizeIndexTask.optimizeIndex();
			backUp.copyIndex(indexPath, newPath);
			IndexLogs.setLogs("all product index is Optimize and Backup!", this.getClass().getName());
		}
		catch( Exception e ) {
			IndexLogs.setLogs( "索引优化或备份失败!", this.getClass().getName() );
		}
		
		//重新加载全部索引.
		try {
			HttpClientRestart.getPostMethod( "RESTART_ALL_INDEX" );
			IndexLogs.setLogs("all product index is restarted!", this.getClass().getName());
		}
		catch( Exception e ) {
			IndexLogs.setLogs( "重启索引异常!", this.getClass().getName() );
		}
	}
	
	public static void main( String[] args ) {
		AllIndexModify pm = new AllIndexModify();
		pm.indexModify();
	}
}
