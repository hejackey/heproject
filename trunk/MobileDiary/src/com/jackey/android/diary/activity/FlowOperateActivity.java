package com.jackey.android.diary.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class FlowOperateActivity extends Activity {
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		
		setTitle(android.R.drawable.ic_dialog_info+"可选操作");
		List<Map<String,String>> listMap = new ArrayList<Map<String,String>>();
		Map<String,String> map = new HashMap<String,String>();
		map.put("operate", "添加");
		listMap.add(map);
		Map<String,String> map1 = new HashMap<String,String>();
		map1.put("operate", "编辑");
		listMap.add(map1);
		Map<String,String> map2 = new HashMap<String,String>();
		map2.put("operate", "删除");
		listMap.add(map2);
		
		SimpleAdapter sAdapter = new SimpleAdapter(FlowOperateActivity.this, listMap, 
				android.R.layout.simple_list_item_1, new String[] {"operate"}, 
				new int[] {android.R.id.text1});
		
		ListView lv = new ListView(this);
		lv.setAdapter(sAdapter);
		
		setContentView(lv);
	}
}
