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
		
		//从第二个字符开始匹配，能否输入一个就匹配？
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line,s_array);
		AutoCompleteTextView actv = (AutoCompleteTextView)findViewById(R.id.autocomplete);
		actv.setAdapter(aa);
	}
}
