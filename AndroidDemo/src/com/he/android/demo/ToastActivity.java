package com.he.android.demo;


import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ToastActivity extends Activity {
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		setContentView(R.layout.toast);
		
		Button toast1 = (Button)findViewById(R.id.toast1);
		toast1.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				setTitle("��ʱ��ѧϰtoast");
				showToast(Toast.LENGTH_SHORT);
				showNotification();
			}
		});
		
		Button toast2 = (Button)findViewById(R.id.toast2);
		toast2.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				setTitle("��ʱ��ѧϰnotification");
				showToast(Toast.LENGTH_LONG);
			}
		});
	}
	
	protected void showToast(int type){
		View view = inflateView(R.layout.toastv);
		
		TextView tv = (TextView)view.findViewById(R.id.toasttv1);
		tv.setText("lkjsdfadslsjdf����toast");
		Toast toast = new Toast(this);
		toast.setView(view);
		toast.setDuration(type);
		toast.show();
		
	}
	
	private View inflateView(int resource){
		LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		return vi.inflate(resource, null);
	}
	
	//�Զ�����notificaiton
	protected void showNotification() {

		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		CharSequence title = "�Զ�����notificaiton";
		CharSequence contents = "eoeandroid.com";

		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, ActivityMain.class), 0);

		Notification notification = new Notification(R.drawable.default_icon,
				title, System.currentTimeMillis());

		notification.setLatestEventInfo(this, title, contents, contentIntent);

		// 100ms�ӳٺ���250ms��ֹͣ100ms����500ms
		notification.vibrate = new long[] { 100, 250, 100, 500 };

		notificationManager.notify(R.layout.toast, notification);
	}
}
