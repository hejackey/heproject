
package com.moobao.entertainment.indexManager;

import com.moobao.config.PropertyConfiguration;
import com.moobao.entertainment.indexTask.EntertainmentDeleteIndexTask;
import com.moobao.entertainment.indexTask.EntertainmentIndexBackUpTask;
import com.moobao.entertainment.indexTask.EntertainmentOptimizeIndexTask;
import com.moobao.entertainment.indexTask.EntertainmentUpdateIndexTask;
import com.moobao.indexlogs.IndexLogs;
import com.moobao.restartIndex.HttpClientRestart;

/**
 * 调用修改任务,进行娱乐中心索引的修改,
 * 包括(添加, 更新, 删除, 备份等)
 * @author liuxueyong
 */
public class EntertainmentIndexModify {
	
	public void indexModify() {
		//获取娱乐中心索引存放的位置.
		String indexPath = PropertyConfiguration.getEntertainmentIndexPath();
		String newPath = PropertyConfiguration.getEntertainmentBackUpIndexPath();
		
		EntertainmentDeleteIndexTask del = new  EntertainmentDeleteIndexTask();
		EntertainmentUpdateIndexTask update = new EntertainmentUpdateIndexTask();
		EntertainmentIndexBackUpTask backUp = new EntertainmentIndexBackUpTask();
		
		try {
			update.indexUpdate();
			IndexLogs.setLogs("Entertainment index is updated!", this.getClass().getName());
		}
		catch( Exception e ) {
			IndexLogs.setLogs( "更新娱乐中心索引文件异常!", this.getClass().getName() );
		}
		
		try {
			del.deleteIndex();
			IndexLogs.setLogs("Entertainment index of overdue is deleted!", this.getClass().getName());
		}
        catch( Exception e ) {
        	IndexLogs.setLogs( "删除过期的娱乐中心索引文件异常!", this.getClass().getName() );
		}
        
		try {
			EntertainmentOptimizeIndexTask.optimizeIndex();
			backUp.copyIndex(indexPath, newPath);
			IndexLogs.setLogs("Entertainment index is Optimize and Backup!", this.getClass().getName());
		}
		catch( Exception e ) {
			System.out.println( "娱乐中心索引优化或备份失败!" );
		}
		
		//重新加载娱乐中心索引.
		try {
			HttpClientRestart.getPostMethod( "RESTART_ENTERTAINMENT_INDEX" );
			IndexLogs.setLogs("entertainment index is restarted!", this.getClass().getName());
		}
		catch( Exception e ) {
			System.out.println( "重启娱乐中心索引异常!" );
		}
	}
	
	public static void main( String[] args ) {
		EntertainmentIndexModify em = new EntertainmentIndexModify();
		em.indexModify();
	}
}
