package com.he.android.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

public class CheckBoxActivity extends Activity {
	CheckBox color1 =null;
	CheckBox color2 =null;
	CheckBox color3 =null;
	
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		setContentView(R.layout.checkbox);
		
		Button bt = (Button)findViewById(R.id.getCbVal);

		 color1 = (CheckBox)findViewById(R.id.color1);
		 color2 = (CheckBox)findViewById(R.id.color2);
		 color3 = (CheckBox)findViewById(R.id.color3);
		 
		 color3.setOnClickListener(new OnClickListener(){
			 public void onClick(View v){
				 if(color3.isChecked())
					 setTitle("绑定checkbox3的值："+color3.getText());
				 else
					 setTitle("color3取消");
			 }
		 });
		
		bt.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				String val="";
				if(color1.isChecked()){
					val = val+","+color1.getText();
				}
				if(color2.isChecked()){
					val = val+","+color2.getText();
				}
				if(color3.isChecked()){
					val = val+","+color3.getText();
				}
				
				
				setTitle("选中值："+val);
			}
			
		});
	}
}
