package com.he.android.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewActivity1 extends Activity {
	private final String[] data={"第一名:98","第二名:94","第三名:90","第四名:85","第五名:80"};
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		
		ListView lv = new ListView(this);
		lv.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,//简单list
				//android.R.layout.simple_list_item_single_choice,
				//android.R.layout.simple_list_item_2,//失败
				data));
		
		//lv.setItemsCanFocus(true);
        //lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        
		setContentView(lv);
	}
}
