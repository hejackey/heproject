package com.moobao.phone.indexManager;

import com.moobao.config.PropertyConfiguration;
import com.moobao.indexlogs.IndexLogs;
import com.moobao.phone.indexTask.PhoneDeleteIndexTask;
import com.moobao.phone.indexTask.PhoneIndexBackUpTask;
import com.moobao.phone.indexTask.PhoneOptimizeIndexTask;
import com.moobao.phone.indexTask.PhoneUpdateIndexTask;
import com.moobao.restartIndex.HttpClientRestart;

/**
 * 调用修改任务,进行手机索引的修改,
 * 包括(添加, 更新, 删除, 备份等)
 * @author liuxueyong
 */
public class PhoneIndexModify {
	 
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
		String indexPath = PropertyConfiguration.getPhoneIndexPath();
		String newPath = PropertyConfiguration.getPhoneBackUpIndexPath();
		
		PhoneDeleteIndexTask del = new PhoneDeleteIndexTask();
		PhoneUpdateIndexTask update = new PhoneUpdateIndexTask();
		PhoneIndexBackUpTask backUp = new PhoneIndexBackUpTask();
		//更新
		try {
			update.indexUpdate();
			IndexLogs.setLogs("phone index is updated!", this.getClass().getName());
		}
		catch( Exception e ) {
			IndexLogs.setLogs( e.getMessage(), this.getClass().getName() );
			IndexLogs.setLogs( "更新手机索引文件异常!", this.getClass().getName() );
		}
		//删除
		try {
			del.deleteIndex();
			IndexLogs.setLogs("phone index of overdue is deleted!", this.getClass().getName());
		}
		catch( Exception e ) {
			IndexLogs.setLogs( "删除过期的手机索引文件异常!", this.getClass().getName() );
		}
		//优化 备份
		try {
			PhoneOptimizeIndexTask.optimizeIndex();
			backUp.copyIndex(indexPath, newPath);
			IndexLogs.setLogs("phone index is Optimize and Backup!", this.getClass().getName());
		}
		catch( Exception e ) {
			IndexLogs.setLogs( "手机索引优化或备份失败!", this.getClass().getName() );
		}
		
		//重新加载手机索引.
		try {
			HttpClientRestart.getPostMethod( "RESTART_PHONE_INDEX" );
			IndexLogs.setLogs("phone index is restarted!", this.getClass().getName());
		}
		catch( Exception e ) {
			IndexLogs.setLogs( "重启手机索引异常!", this.getClass().getName() );
		}
		
		//重新加载手机数据字典数量(搜索提示).
		try {
			HttpClientRestart.updateDic( "UPDATE_DATA_DIC" );
			HttpClientRestart.reloadDic( "RELOAD_PHONE_DIC" );
			IndexLogs.setLogs("Phone_dic is reload!", this.getClass().getName());
		}
		catch( Exception e ) {
			IndexLogs.setLogs( "加载手机搜索提示数量异常!", this.getClass().getName() );
		}
	}
	
	public static void main( String[] args ) {
		PhoneIndexModify pm = new PhoneIndexModify();
		pm.indexModify();
	}
}
