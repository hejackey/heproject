package com.he.android.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LayOutDemo extends Activity {
	OnClickListener listener1 = null;
	OnClickListener listener2 = null;


	Button button1;
	Button button2;



	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		listener1 = new OnClickListener() {
			public void onClick(View v) {
				setTitle("button1 ����󲻿ɼ�");
				button1.setVisibility(View.INVISIBLE);
				button2.setVisibility(View.VISIBLE);
			}
		};
		listener2 = new OnClickListener() {
			public void onClick(View v) {
				setTitle("button2 ����󲻿ɼ�");
				button2.setVisibility(View.INVISIBLE);
				button1.setVisibility(View.VISIBLE);
			}
		};
		setContentView(R.layout.main);
		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(listener1);
		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(listener2);
	} 
}
