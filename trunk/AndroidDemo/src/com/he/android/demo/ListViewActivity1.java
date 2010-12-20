package com.he.android.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewActivity1 extends Activity {
	private final String[] data={"��һ��:98","�ڶ���:94","������:90","������:85","������:80"};
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		
		ListView lv = new ListView(this);
		lv.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,//��list
				//android.R.layout.simple_list_item_single_choice,
				//android.R.layout.simple_list_item_2,//ʧ��
				data));
		
		//lv.setItemsCanFocus(true);
        //lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        
		setContentView(lv);
	}
}
