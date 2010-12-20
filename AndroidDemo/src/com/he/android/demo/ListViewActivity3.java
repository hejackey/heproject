package com.he.android.demo;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewActivity3 extends ListActivity {
	private String[] data={"第一","第二"};
	
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		setContentView(R.layout.list_item3);
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,//简单list
				data));
	}
	
	protected void onListItemClick(ListView listView, View v, int position, long id) {
    	super.onListItemClick(listView, v, position, id);
    	setTitle(listView.getItemAtPosition(position).toString());
	}
	
}
