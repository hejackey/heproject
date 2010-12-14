package com.he.android.demo;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SpinnerActivity extends Activity {
	private static String[] country={"北京","上海","香港","澳门"};
	
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		setContentView(R.layout.spinner);
		
		ArrayList<String> s_array = new ArrayList<String>();
		for(String str : country){
			s_array.add(str);
		}
		//数组中载入下拉数据
		Spinner spinner = (Spinner)findViewById(R.id.spinner);
		ArrayAdapter<String> aa = new ArrayAdapter<String>(
				this,android.R.layout.simple_spinner_item,s_array);
		
		aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(aa);
		
		//从xml文件中载入下拉数据
		Spinner spinner1 = (Spinner)findViewById(R.id.spinner1);
		ArrayAdapter<CharSequence> aa1 = ArrayAdapter.createFromResource(this,
				R.array.countries, android.R.layout.simple_spinner_item);
		
		aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner1.setAdapter(aa1);
	}
}
