package com.he.android.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

public class CheckBoxActivity extends Activity {
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		setContentView(R.layout.checkbox);
		
		Button bt = (Button)findViewById(R.id.getCbVal);
		
		bt.setOnClickListener(new OnClickListener(){
			String val="";
			
			public void onClick(View v){
				CheckBox color1 = (CheckBox)findViewById(R.id.color1);
				CheckBox color2 = (CheckBox)findViewById(R.id.color2);
				CheckBox color3 = (CheckBox)findViewById(R.id.color3);
				
				if(color1.isChecked()){
					val = val+","+color1.getText();
				}
				else if(color2.isChecked()){
					val = val+","+color2.getText();
				}
				else if(color3.isChecked()){
					val = val+","+color3.getText();
				}
				else{
					val = val+",未选中任何值";
				}
				
				setTitle(val);
			}
			
		});
	}
}
