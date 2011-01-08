package com.he.android.demo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.OnTabChangeListener;

public class TabDemoActivity extends TabActivity {
	private String param;
	private TabHost tab; 
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		tab = getTabHost();
		LayoutInflater.from(this).inflate(R.layout.tab,
				tab.getTabContentView(), true);
		
		ListView itemlist = (ListView)findViewById(R.id.itemlist);
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		HashMap<String, Object>	item = new HashMap<String, Object>();
		item.put("title", "test");
		item.put("pubdate","1928");
		data.add(item);
		HashMap<String, Object>	item1 = new HashMap<String, Object>();
		item1.put("title", "testb23");
		item1.put("pubdate","1654");
		data.add(item1);
		
		SimpleAdapter adapter = new SimpleAdapter(this, data,
	       		 android.R.layout.simple_list_item_2, new String[] { "title","pubdate" },
	       		 new int[] { android.R.id.text1 , android.R.id.text2});
	    itemlist.setAdapter(adapter);
	    
	    tab.addTab(tab.newTabSpec("tab1").setIndicator("tab1")
				.setContent(R.id.itemlist));
		tab.addTab(tab.newTabSpec("tab2").setIndicator("tab2")
				.setContent(R.id.view2));
		tab.addTab(tab.newTabSpec("tab3").setIndicator("tab3")
				.setContent(R.id.view3));
		
		tab.setOnTabChangedListener(new OnTabChangeListener(){

			@Override
			public void onTabChanged(String tabId) {
				if("tab2".equals(tabId)){
					TextView tx = (TextView)findViewById(R.id.view2);
					tx.setText("我正被选中");
				   /* tab.addTab(tab.newTabSpec("tab3").setIndicator("tab2")
							.setContent(R.id.view2));
				    tab.*/
					
				}
			}
			
		});
		
	}
}
