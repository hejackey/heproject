package com.he.android.demo;

import android.app.Activity;
import android.content.Context;
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
				setTitle("段时间学习toast");
				showToast(Toast.LENGTH_SHORT);
			}
		});
		
		Button toast2 = (Button)findViewById(R.id.toast2);
		toast2.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				setTitle("长时间学习notification");
				showToast(Toast.LENGTH_LONG);
			}
		});
	}
	
	protected void showToast(int type){
		View view = inflateView(R.layout.toastv);
		
		TextView tv = (TextView)view.findViewById(R.id.toasttv1);
		tv.setText("lkjsdfadslsjdf测试toast");
		Toast toast = new Toast(this);
		toast.setView(view);
		toast.setDuration(type);
		toast.show();
		
	}
	
	private View inflateView(int resource){
		LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		return vi.inflate(resource, null);
	}
}
