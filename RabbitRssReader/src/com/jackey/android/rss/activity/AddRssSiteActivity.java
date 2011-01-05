package com.jackey.android.rss.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddRssSiteActivity extends Activity {
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		setContentView(R.layout.add);
		
		Button bt = (Button)findViewById(R.id.bt01);
		bt.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Bundle bd = new Bundle();
				bd.putString("title", ((EditText)findViewById(R.id.et01)).getText().toString());
				bd.putString("url", ((EditText)findViewById(R.id.et02)).getText().toString());
				Intent mIntent = new Intent();
				mIntent.putExtras(bd);
				setResult(RESULT_OK,mIntent);
				finish();
			}
		});
	}
}
