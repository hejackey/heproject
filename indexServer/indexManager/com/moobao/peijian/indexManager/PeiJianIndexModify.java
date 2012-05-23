package com.moobao.peijian.indexManager;

import com.moobao.config.PropertyConfiguration;
import com.moobao.indexlogs.IndexLogs;
import com.moobao.peijian.indexTask.PeiJianDeleteIndexTask;
import com.moobao.peijian.indexTask.PeiJianIndexBackUpTask;
import com.moobao.peijian.indexTask.PeiJianOptimizeIndexTask;
import com.moobao.peijian.indexTask.PeiJianUpdateIndexTask;
import com.moobao.restartIndex.HttpClientRestart;

/**
 * 调用修改任务,进行配件索引的修改,
 * 包括(添加, 更新, 删除, 备份等)
 * @author liuxueyong
 */
public class PeiJianIndexModify {
	
	public void indexModify() {
		//获取配件索引存放的位置.
		String indexPath = PropertyConfiguration.getPeiJianIndexPath();
		String newPath = PropertyConfiguration.getPeiJianBackUpIndexPath();
		
		PeiJianDeleteIndexTask del = new PeiJianDeleteIndexTask();
		PeiJianUpdateIndexTask update = new PeiJianUpdateIndexTask();
		PeiJianIndexBackUpTask backUp = new PeiJianIndexBackUpTask();
		
		//更新
		try {
			update.indexUpdate();
			IndexLogs.setLogs("peiJian index is updated!", this.getClass().getName());
		}
		catch( Exception e ) {
			IndexLogs.setLogs( "更新配件索引文件异常!", this.getClass().getName() );
		}
		//删除
		try {
			del.deleteIndex();
			IndexLogs.setLogs("peiJian index of overdue is deleted!", this.getClass().getName());
		}
		catch( Exception e ) {
			IndexLogs.setLogs( "删除过期的配件索引文件异常!", this.getClass().getName() );
		}		
		//优化 备份
		try {
			PeiJianOptimizeIndexTask.optimizeIndex();
			backUp.copyIndex(indexPath, newPath);
			IndexLogs.setLogs("peiJian index is Optimize and Backup!", this.getClass().getName());
		}
		catch( Exception e ) {
			System.out.println( "配件索引优化或备份失败!" );
		}
		
		//重新加载配件索引.
		try {
			HttpClientRestart.getPostMethod( "RESTART_PEIJIAN_INDEX" );
			IndexLogs.setLogs("peiJian index is restarted!", this.getClass().getName());
		}
		catch( Exception e ) {
			System.out.println( "重启配件索引异常!" );
		}
		
		//重新加载配件数据字典数量(搜索提示).
		try {
			HttpClientRestart.reloadDic( "RELOAD_PEIJIAN_INDEX" );
			IndexLogs.setLogs("PeiJian_dic is reload!", this.getClass().getName());
		}
		catch( Exception e ) {
			IndexLogs.setLogs( "加载配件搜索提示数量异常!", this.getClass().getName() );
		}
		
	}
	
	public static void main( String[] args ) {
		PeiJianIndexModify peiM = new PeiJianIndexModify();
		peiM.indexModify();
	}
}
