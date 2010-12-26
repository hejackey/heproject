package com.jackey.android.diary.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import com.jackey.android.diary.dao.DataBaseHelper;

public class DiaryListActivity extends ListActivity {
    /** Called when the activity is first created. */
	private static DataBaseHelper dbHelper;
	private Cursor cursor; 
    public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		
		setTitle("兔仔记事本");
		dbHelper = new DataBaseHelper(DiaryListActivity.this);
		/*SQLiteDatabase db = dbHelper.getWritableDatabase();
		dbHelper.onCreate(db);*/
		///dbHelper.delTable(dbHelper);
		refreshDiaryList();
		
	}
    
    private void refreshDiaryList(){
    	cursor = dbHelper.queryDiary(dbHelper);
		
		setContentView(R.layout.main);
		TextView tv = (TextView)findViewById(android.R.id.empty);
		tv.setText("点击'menu'开始添加日志");
		
		//显示列表内容	
		startManagingCursor(cursor);
		String[] from = new String[] { dbHelper.TITLE,dbHelper.CREATETIME};
		int[] to = new int[] { R.id.tv01, R.id.tv02 };
		SimpleCursorAdapter diarys = new SimpleCursorAdapter(this,
				R.layout.list, cursor, from, to);
		setListAdapter(diarys);
		
		registerForContextMenu(getListView());
		//为列表绑定长击事件
		/*getListView().setOnItemLongClickListener(new OnItemLongClickListener(){
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.i("长按点击的id==", String.valueOf(id));
				showDialog(1);
				Cursor c = cursor;
				c.moveToPosition(position);
				Intent i = new Intent(parent.getContext(), FlowOperateActivity.class);
				i.putExtra(dbHelper.ID, (int)id);
				i.putExtra(dbHelper.TITLE, c.getString(c.getColumnIndexOrThrow(dbHelper.TITLE)));
				i.putExtra(dbHelper.CONTENT, c.getString(c.getColumnIndexOrThrow(dbHelper.CONTENT)));
				startActivityForResult(i, 0);
				
				return false;
			}
		});*/
    }
    
    //创建菜单
    private static final int item0=0;
    private static final int item1=1;
    private static final int item2=2;
    public boolean onCreateOptionsMenu(Menu menu){
		super.onCreateOptionsMenu(menu);
		
		menu.add(Menu.NONE, item0, Menu.NONE, "添加日志");
		menu.add(Menu.NONE, item1, Menu.NONE, "删除日志");
		
		return true;
	}  
    
    //监听菜单单击事件
    /*public boolean onOptionsItemSelected (int featureId, MenuItem item){
    	switch (item.getItemId()){
	    	case item0:
	    		showInsertDiary();
	    		return true;
	    	case item1:
	    		showDelDiary();
	    		return true;
	    	default:
	    		return super.onOptionsItemSelected(item);
    	}
    }*/
    public boolean onOptionsItemSelected(MenuItem item){
    	switch (item.getItemId()){
    	case item0:
    		showInsertDiary();
    		return true;
    	case item1:
    		showDelDiary(0);
    		return true;
    	default:
    		return super.onOptionsItemSelected(item);
	}
	}

    public void onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
    	super.onCreateContextMenu(menu, v, menuInfo);
    	menu.setHeaderIcon(android.R.drawable.ic_dialog_info);
    	menu.setHeaderTitle("操作选项");
    	menu.add(Menu.NONE, item0, Menu.NONE, "添加");
    	menu.add(Menu.NONE, item1, Menu.NONE, "删除");
    	menu.add(Menu.NONE, item2, Menu.NONE, "编辑");
    }
    
    //
    public boolean onContextItemSelected(MenuItem item) {
    	AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
    	Log.i("info id===", String.valueOf(info.id));
    	switch (item.getItemId()){
			case item0:
				showInsertDiary();
				return true;
			case item1:
				showDelDiary(info.id);
				return true;
			case item2:{
				Cursor c = cursor;
				c.moveToPosition(info.position);
				Intent i = new Intent(this, AddDiaryActivity.class);
				i.putExtra(dbHelper.ID, (int)info.id);
				i.putExtra(dbHelper.TITLE, c.getString(c.getColumnIndexOrThrow(dbHelper.TITLE)));
				i.putExtra(dbHelper.CONTENT, c.getString(c.getColumnIndexOrThrow(dbHelper.CONTENT)));
				startActivityForResult(i, 0);
			
				return true;
			}
			default:
				return super.onContextItemSelected(item);
		}
	}
    //显示添加日志activity
    public void showInsertDiary(){
    	Intent intent = new Intent(DiaryListActivity.this,AddDiaryActivity.class);
    	startActivityForResult(intent, 0);
    }
    
    //删除一条日志
    public void showDelDiary(long selId){
    	long id = 0;
    	if(selId == 0)
    		id = getListView().getSelectedItemId();
    	else
    		id = selId;
    	
    	dbHelper.delDiary(dbHelper, id);
    	refreshDiaryList();
    }
    
    @Override
	// 需要对position和id进行一个很好的区分
	// position指的是点击的这个ViewItem在当前ListView中的位置
	// 每一个和ViewItem绑定的数据，肯定都有一个id，通过这个id可以找到那条数据。
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Cursor c = cursor;
		c.moveToPosition(position);
		Intent i = new Intent(this, AddDiaryActivity.class);
		i.putExtra(dbHelper.ID, (int)id);
		i.putExtra(dbHelper.TITLE, c.getString(c.getColumnIndexOrThrow(dbHelper.TITLE)));
		i.putExtra(dbHelper.CONTENT, c.getString(c.getColumnIndexOrThrow(dbHelper.CONTENT)));
		startActivityForResult(i, 0);
	}
    
    //下一个activity回调
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		refreshDiaryList();
	}
	
	//显示dialog回调函数
	protected Dialog onCreateDialog(int id){
		switch (id){
			case 1:
			return builderDialog1(DiaryListActivity.this);
		}
		return null;
	}
	
	public Dialog builderDialog1(Context context){
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setIcon(android.R.drawable.ic_menu_directions);
		builder.setTitle("可选操作");
		Log.i("selectedid===", String.valueOf(getListView().getSelectedItemId()));
		/*List<Map<String,String>> listMap = new ArrayList<Map<String,String>>();
		Map<String,String> map = new HashMap<String,String>();
		map.put("operate", "添加");
		listMap.add(map);
		Map<String,String> map1 = new HashMap<String,String>();
		map1.put("operate", "编辑");
		listMap.add(map1);
		Map<String,String> map2 = new HashMap<String,String>();
		map2.put("operate", "删除");
		listMap.add(map2);
		
		SimpleAdapter sAdapter = new SimpleAdapter(context, listMap, 
				android.R.layout.simple_list_item_1, new String[] {"operate"}, 
				new int[] {android.R.id.text1});
		
		ListView lv = new ListView(this);
		lv.setAdapter(sAdapter);

		lv.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.i("dialog which=", String.valueOf(position));
				switch (position){
				case 0:
					showInsertDiary();
					break;
				case 1:{
					//parent.get
					onListItemClick(getListView(),view,position,id);
					break;
				}
				case 2:
					break;
				}
			}
		});
		
		builder.setView(lv);*/
		final CharSequence[] items = {"添加", "编辑", "删除"};

		builder.setItems(items, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int item) {
		        Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
		    }
		});

		return builder.create();
	}
}