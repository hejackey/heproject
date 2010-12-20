package com.he.android.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class IntentParaActivity extends Activity {
	private String param;
	private String param1;
	
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		setContentView(R.layout.intentpara);
		TextView tv = (TextView)findViewById(R.id.tv01);
		
		Bundle extra = getIntent().getExtras();
		if(extra != null){
			param = extra.getString("actm");
			param1 = extra.getString("actm1");
		}
		tv.setText(tv.getText()+",activityMain传的参数值:"+param+"===2号=="+param1);
		
		Button bt1 = (Button)findViewById(R.id.intentparambt1);
		bt1.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Bundle bd = new Bundle();
				bd.putString("intentParm", "来自IntentParam参数");
				Intent mIntent = new Intent();
				mIntent.putExtras(bd);
				setResult(RESULT_OK,mIntent);
				finish();
				
			}
		});
	}
}
