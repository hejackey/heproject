package com.he.android.demo;

import com.he.android.adapter.ImageAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

public class GridViewActivity extends Activity {
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		setContentView(R.layout.gridview);
		GridView gridview = (GridView)findViewById(R.id.gridview);
		gridview.setAdapter(new ImageAdapter(this));
	}
}
