package com.he.android.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ActivityMain extends Activity {
	OnClickListener listen0;
	OnClickListener listen1;
	OnClickListener listen2;
	OnClickListener listen3;
	Button button0;
	Button button1;
	Button button2;
	Button button3;
	
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		
		listen0= new OnClickListener(){
			public void onClick(View v){
				Intent intent0= new Intent(ActivityMain.this,ActivityFrameLayout.class);
				setTitle("FrameLayout");
				startActivity(intent0);
			}
		};
		listen1= new OnClickListener(){
			public void onClick(View v){
				Intent intent1= new Intent(ActivityMain.this,ActivityRelativeLayout.class);
				setTitle("FrameLayout");
				startActivity(intent1);
			}
		};
		listen2= new OnClickListener(){
			public void onClick(View v){
				Intent intent2= new Intent(ActivityMain.this,ActivityLayout.class);
				setTitle("this is activity layout");
				startActivity(intent2);
			}
		};
		listen3= new OnClickListener(){
			public void onClick(View v){
				Intent intent3= new Intent(ActivityMain.this,ActivityTableLayout.class);
				setTitle("this is table layout");
				startActivity(intent3);
			}
		};
		
		setContentView(R.layout.main);

		Button but4 = (Button)findViewById(R.id.button4);
		but4.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				setTitle("这是button测试");
				TextView tv1= (TextView)findViewById(R.id.but4val);
				CharSequence cs = tv1.getText();
				
				tv1.setText("原来值是："+cs+",新值：这是button4测试的值");
			}
		});
		
		Button but5 = (Button)findViewById(R.id.button5);
		but5.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				
				Intent intent = new Intent(ActivityMain.this, EditeTextActivity.class);
				startActivity(intent);
			}
		});
		
		Button but6 = (Button)findViewById(R.id.button6);
		but6.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				
				Intent intent = new Intent(ActivityMain.this, CheckBoxActivity.class);
				startActivity(intent);
			}
		});
		
		button0= (Button) findViewById(R.id.button0);
		button0.setOnClickListener(listen0);
		button1= (Button) findViewById(R.id.button1);
		button1.setOnClickListener(listen1);
		button2= (Button) findViewById(R.id.button2);
		button2.setOnClickListener(listen2);
		button3= (Button) findViewById(R.id.button3);
		button3.setOnClickListener(listen3);
	}
}
