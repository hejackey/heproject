package com.he.android.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EditeTextActivity extends Activity {
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		setContentView(R.layout.edittext);
		
		Button bt = (Button)findViewById(R.id.but5);
		bt.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				EditText et = (EditText)findViewById(R.id.but5et);
				setTitle("测试edittext，取值为："+et.getText());
				et.setText("点击后的新值");
			}
		});
	}
}
