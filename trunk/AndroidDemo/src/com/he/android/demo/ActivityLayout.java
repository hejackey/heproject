package com.he.android.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ActivityLayout extends Activity {
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		
		LinearLayout layoutMain = new LinearLayout(this);
		layoutMain.setOrientation(LinearLayout.HORIZONTAL);
		setContentView(layoutMain);
		
		LayoutInflater inflate = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		RelativeLayout layoutLeft = (RelativeLayout) inflate.inflate(
				R.layout.left, null);
		RelativeLayout layoutRight = (RelativeLayout) inflate.inflate(
				R.layout.active_relative_layout, null);

		RelativeLayout.LayoutParams relParam = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		layoutMain.addView(layoutLeft, 100, 100);
		layoutMain.addView(layoutRight, relParam);
	}
}
