package com.jackey.android.rss.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Toast;

public class ConnectValidActivity extends Activity {
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		boolean res = CheckNetwork();
		if(res){
			Intent intent = new Intent(ConnectValidActivity.this,StartActivity.class);
			startActivity(intent);
		}
	}
	

    private boolean CheckNetwork() {
        boolean flag = false;
        ConnectivityManager cwjManager = (ConnectivityManager) getSystemService(ConnectValidActivity.this.CONNECTIVITY_SERVICE);
        if (cwjManager.getActiveNetworkInfo() != null)
            flag = cwjManager.getActiveNetworkInfo().isAvailable();
        if (!flag) {
            Builder b = new AlertDialog.Builder(this).setTitle("没有可用的网络").setMessage("请开启GPRS或WIFI网络连接");
            b.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    Intent mIntent = new Intent("/");
                    ComponentName comp = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
                    mIntent.setComponent(comp);
                    mIntent.setAction("<span class='hilite'>android</span>.intent.action.VIEW");
                    startActivity(mIntent);
                }
            }).setNeutralButton("取消", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    dialog.cancel();
                }
            }).create();
            b.show();
        }
        else{
        	Toast.makeText(ConnectValidActivity.this, "网络畅通，请稍候...", Toast.LENGTH_SHORT).show();
        }
 
        return flag;
    }
}
