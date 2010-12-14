package com.he.android.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class AutoCompleteActivity extends Activity {
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		setContentView(R.layout.autocomplete);
		
		String[] s_array = {"application","apple","apad","book","cake","cut"};
		
		//�ӵڶ����ַ���ʼƥ�䣬�ܷ�����һ����ƥ�䣿
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line,s_array);
		AutoCompleteTextView actv = (AutoCompleteTextView)findViewById(R.id.autocomplete);
		actv.setAdapter(aa);
	}
}
