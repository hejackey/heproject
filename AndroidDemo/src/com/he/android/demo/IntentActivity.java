package com.he.android.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class IntentActivity extends Activity {
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		
		setContentView(R.layout.intentactivity);
		Button bt1 = (Button)findViewById(R.id.bt1);
		bt1.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				finish();
			}
		});
	}
}
