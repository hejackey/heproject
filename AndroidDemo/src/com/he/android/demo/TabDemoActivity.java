package com.he.android.demo;


import android.app.TabActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

public class TabDemoActivity extends TabActivity {
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		TabHost tab = getTabHost();
		
		LayoutInflater.from(this).inflate(R.layout.tab,
				tab.getTabContentView(), true);
		tab.addTab(tab.newTabSpec("tab1").setIndicator("tab1")
				.setContent(R.id.view1));
		tab.addTab(tab.newTabSpec("tab3").setIndicator("tab2")
				.setContent(R.id.view2));
		tab.addTab(tab.newTabSpec("tab3").setIndicator("tab3")
				.setContent(R.id.view3));
	}
}
