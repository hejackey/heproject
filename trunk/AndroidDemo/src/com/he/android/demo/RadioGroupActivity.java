package com.he.android.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class RadioGroupActivity extends Activity {
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		setContentView(R.layout.radiogroup);
		
		Button but = (Button)findViewById(R.id.clean);
		but.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				RadioGroup rg = (RadioGroup)findViewById(R.id.menu);
				RadioButton rb = (RadioButton)findViewById(rg.getCheckedRadioButtonId());
				setTitle("选中的值："+rb.getText());
				rg.clearCheck();
			}
		});
	}
}
