package com.moobao.peijianBase.indexManager;

import com.moobao.config.PropertyConfiguration;
import com.moobao.indexlogs.IndexLogs;
import com.moobao.peijianBase.indexTask.PeiJianBaseDeleteIndexTask;
import com.moobao.peijianBase.indexTask.PeiJianBaseIndexBackUpTask;
import com.moobao.peijianBase.indexTask.PeiJianBaseOptimizeIndexTask;
import com.moobao.peijianBase.indexTask.PeiJianBaseUpdateIndexTask;
import com.moobao.restartIndex.HttpClientRestart;

/**
 * 调用修改任务,进行配件普通索引的修改,
 * 包括(添加, 更新, 删除, 备份等)
 * @author liuxueyong
 */
public class PeiJianBaseIndexModify {
	
	public void indexModify() {
		//获取配件索引存放的位置.
		String indexPath = PropertyConfiguration.getPeiJianBaseIndexPath();
		String newPath = PropertyConfiguration.getPeiJianBaseBackUpIndexPath();
		
		PeiJianBaseDeleteIndexTask del = new PeiJianBaseDeleteIndexTask();
		PeiJianBaseUpdateIndexTask update = new PeiJianBaseUpdateIndexTask();
		PeiJianBaseIndexBackUpTask backUp = new PeiJianBaseIndexBackUpTask();
		
		//更新
		try {
			update.indexUpdate();
			IndexLogs.setLogs("peiJianBase index is updated!", this.getClass().getName());
		}
		catch( Exception e ) {
			IndexLogs.setLogs( "更新配件普通索引文件异常!", this.getClass().getName() );
		}
		//删除
		try {
			del.deleteIndex();
			IndexLogs.setLogs("peiJianBase index of overdue is deleted!", this.getClass().getName());
		}
		catch( Exception e ) {
			IndexLogs.setLogs( "删除过期的配件普通索引文件异常!", this.getClass().getName() );
		}		
		//优化 备份
		try {
			PeiJianBaseOptimizeIndexTask.optimizeIndex();
			backUp.copyIndex(indexPath, newPath);
			IndexLogs.setLogs("peiJianBase index is Optimize and Backup!", this.getClass().getName());
		}
		catch( Exception e ) {
			System.out.println( "配件普通索引优化或备份失败!" );
		}
		
		//重新加载配件索引.
		try {
			HttpClientRestart.getPostMethod( "RESTART_PEIJIANBASE_INDEX" );
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
		PeiJianBaseIndexModify peiM = new PeiJianBaseIndexModify();
		peiM.indexModify();
	}
}
