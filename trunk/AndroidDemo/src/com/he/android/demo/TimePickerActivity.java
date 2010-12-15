package com.he.android.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TimePicker;

public class TimePickerActivity extends Activity {
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		setContentView(R.layout.timepicker);
		
		TimePicker tp = (TimePicker)findViewById(R.id.TimePicker01);
		tp.setIs24HourView(true);
	}
}
