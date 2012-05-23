package com.moobao.resource.indexManager;

import com.moobao.config.PropertyConfiguration;
import com.moobao.indexlogs.IndexLogs;
import com.moobao.resource.indexTask.ResourceDeleteIndexTask;
import com.moobao.resource.indexTask.ResourceIndexBackUpTask;
import com.moobao.resource.indexTask.ResourceOptimizeIndexTask;
import com.moobao.resource.indexTask.ResourceUpdateIndexTask;
import com.moobao.restartIndex.HttpClientRestart;

/**
 * 调用修改任务,进行资讯索引的修改,
 * 包括(添加, 更新, 删除, 备份等)
 * @author liuxueyong
 */
public class ResourceIndexModify {
	 
	public void indexModify() {
		//获取资讯索引存放的位置.
		String indexPath = PropertyConfiguration.getResourceIndexPath();
		String newPath = PropertyConfiguration.getResourceBackUpIndexPath();
		
		ResourceDeleteIndexTask del = new  ResourceDeleteIndexTask();
		ResourceUpdateIndexTask update = new ResourceUpdateIndexTask();
		ResourceIndexBackUpTask backUp = new ResourceIndexBackUpTask();
		
		//更新
		try {
			update.indexUpdate();
			IndexLogs.setLogs("Resource index is updated!", this.getClass().getName());
		}
		catch( Exception e ) {
			IndexLogs.setLogs( "更新资讯索引文件异常!", this.getClass().getName() );
		}
		//删除
		try {
			del.deleteIndex();
			IndexLogs.setLogs("Resource index of overdue is deleted!", this.getClass().getName());
		}
		catch( Exception e ) {
			IndexLogs.setLogs( "删除过期的资讯索引文件异常!", this.getClass().getName() );
		}		
		//优化 备份
		try {
			ResourceOptimizeIndexTask.optimizeIndex();
			backUp.copyIndex(indexPath, newPath);
			IndexLogs.setLogs("Resource index is Optimize and Backup!", this.getClass().getName());
		}
		catch( Exception e ) {
			System.out.println( "资讯索引优化或备份失败!" );
		}		
		//重新加载资讯索引.
		try {
			HttpClientRestart.getPostMethod( "RESTART_RESOURCE_INDEX" );
			IndexLogs.setLogs("Resource index is restarted!", this.getClass().getName());
		}
		catch( Exception e ) {
			System.out.println( "重启资讯索引异常!" );
		}
	}
	
	public static void main( String[] args ) {
		ResourceIndexModify rm = new ResourceIndexModify();
		rm.indexModify();
	}
}
