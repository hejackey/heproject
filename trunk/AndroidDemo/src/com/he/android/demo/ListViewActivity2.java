package com.he.android.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class ListViewActivity2 extends Activity {
	private ArrayList<Map<String,String>> al = new ArrayList<Map<String,String>>();
	
	public void preData(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("name", "张三");
		map.put("sex","男");
		al.add(map);
		
		map = new HashMap<String,String>();
		map.put("name", "李四");
		map.put("sex","女");
		al.add(map);
		
		map = new HashMap<String,String>();
		map.put("name", "王二");
		map.put("sex","男");
		al.add(map);
	}
	
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		
		preData();
		ListView lv = new ListView(this);
		//自定义布局
		/*lv.setAdapter(new SimpleAdapter(this,al,R.layout.list_item,
				new String[]{"name","sex"},
				new int[]{R.id.listvtv2,R.id.listvtv1}));*/
		
		//系统自带布局
		lv.setAdapter(new SimpleAdapter(this,al,android.R.layout.simple_list_item_2,
				new String[]{"name","sex"},
				new int[]{android.R.id.text1,android.R.id.text2}));
		
		OnItemClickListener listener = new OnItemClickListener(){
			public void onItemClick(AdapterView<?> parent,View view,
					int position,long id){
				
				Map<String ,String > map = (Map<String,String>)parent.getItemAtPosition(position);
				
				setTitle("选中名称:"+map.get("name")+",性别:"+map.get("sex"));
			}
		};
		lv.setOnItemClickListener(listener);
		
		OnItemSelectedListener selListen = new OnItemSelectedListener(){
			public void onItemSelected(AdapterView<?> parent,View view,
					int position,long id){
				setTitle(parent.getItemAtPosition(position).toString());
			}
			
			public void onNothingSelected(AdapterView<?> parent){
				setTitle("");
			}
		};
		lv.setOnItemSelectedListener(selListen);
		
		setContentView(lv);
	}
}
