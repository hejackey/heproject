package com.jackey.android.rss.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class ConnectValidReceive extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		ConnectivityManager conManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo actInfo = conManager.getActiveNetworkInfo();
		NetworkInfo mobInfo = conManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if(actInfo != null){
			Log.i("newwork info is ", "ok");
			Toast.makeText(context, "ÍøÂç³©Í¨", Toast.LENGTH_SHORT).show();
		}
		
		if(mobInfo != null){
			Log.i("mob newwork info is ", "ok");
			Toast.makeText(context, "ÍøÂç³©Í¨", Toast.LENGTH_SHORT);
		}
		
		Log.i("mob newwork info is ", "failure");
		Toast.makeText(context, "Çë²é¿´ÍøÂçÉèÖÃ", Toast.LENGTH_SHORT);
	}

}
