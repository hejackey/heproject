package com.jackey.android.diary.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.jackey.android.diary.dao.DataBaseHelper;

public class DiaryListActivity extends ListActivity {
    /** Called when the activity is first created. */
	private static DataBaseHelper dbHelper;
	private Cursor cursor; 
    public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		
		setTitle("���м��±�");
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
		tv.setText("���'menu'��ʼ�����־");
		
			/*ListView lv = new ListView(this);
			cursor.moveToFirst();
			String[] data = new String[count];
			
			for(int i=0;i<count;i++){
				data[i]=cursor.getString(0)+"   "+cursor.getString(2);
				cursor.moveToNext();
			}
			
			lv.setAdapter(new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1,//��list
					data));

			setContentView(lv);*/
			
		startManagingCursor(cursor);
		String[] from = new String[] { dbHelper.TITLE,dbHelper.CREATETIME};
		int[] to = new int[] { R.id.tv01, R.id.tv02 };
		SimpleCursorAdapter diarys = new SimpleCursorAdapter(this,
				R.layout.list, cursor, from, to);
		setListAdapter(diarys);
    }
    
    private static final int item0=0;
    private static final int item1=1;
    public boolean onCreateOptionsMenu(Menu menu){
		super.onCreateOptionsMenu(menu);
		
		//menu.add(Menu.NONE, item0, Menu.NONE, "�����־").setIcon(R.drawable.icon);
		menu.add(Menu.NONE, item0, Menu.NONE, "�����־");
		menu.add(Menu.NONE, item1, Menu.NONE, "ɾ����־");
		
		return true;
	}  
    
    /*public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
		case item0:
			showInsertDiary();
			break;
		case item1:
			showDelDiary();
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}*/

    public boolean onMenuItemSelected (int featureId, MenuItem item){
    	switch (item.getItemId()){
		case item0:
			showInsertDiary();
			return true;
		case item1:
			showDelDiary();
			return true;
		}
		
		return super.onOptionsItemSelected(item);
    }

    
    public void showInsertDiary(){
    	Intent intent = new Intent(DiaryListActivity.this,AddDiaryActivity.class);
    	startActivityForResult(intent, 0);
    }
    
    public void showDelDiary(){
    	long id = getListView().getSelectedItemId();
    	dbHelper.delDiary(dbHelper, id);
    	refreshDiaryList();
    }
    
    @Override
	// ��Ҫ��position��id����һ���ܺõ�����
	// positionָ���ǵ�������ViewItem�ڵ�ǰListView�е�λ��
	// ÿһ����ViewItem�󶨵����ݣ��϶�����һ��id��ͨ�����id�����ҵ��������ݡ�
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
    
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		refreshDiaryList();
	}
}