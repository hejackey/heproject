package com.jackey.store;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StoreMainActivity extends Activity {
	private static final String UM_KEY="username";
	private static final String PWD_KEY="password";
	private static final String SETTING_INFOS="SETTING_Infos";
	private EditText et1;
	private EditText et2;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ////////////////////简单信息map结构存储//////////////////////
        SharedPreferences sp = getSharedPreferences(SETTING_INFOS,0);
        String name  = sp.getString(UM_KEY, "");
        String pwd = sp.getString(PWD_KEY, "");
        
        et1 = (EditText)findViewById(R.id.ed_um);
        et2 = (EditText)findViewById(R.id.ed_pwd);
        
        et1.setText(name);
        et2.setText(pwd);
        
        ///////////////////当前应用下文件读写/////////////////////////
        String fileName = "tmp.txt";
        try {
        	BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(fileName)));
        	String data = null;
        	while((data = br.readLine())!=null)
        	{
        		System.out.println(data);
        	}
        	//Context.MODE_PRIVATE替换文件内容，Context.MODE_APPEND向文件追加内容
        	//FileOutputStream fos = openFileOutput(fileName,Context.MODE_PRIVATE);
			FileOutputStream fos = openFileOutput(fileName,Context.MODE_APPEND);
			byte[] buf = "新追加内容".getBytes();
			fos.write(buf);
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		////////////////////演示插入sqlit/////////////////////////////////
		dbHelper = new DatabaseHelper(this);
		Button bt = (Button)findViewById(R.id.bt01);
		bt.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				insertChapter();
			}
		});
		
		Button bt2 = (Button)findViewById(R.id.bt02);
		bt2.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				queryChapter();
			}
		});
		
		Button bt3 = (Button)findViewById(R.id.bt03);
		bt3.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				delChapter();
			}
		});
		
		Button bt4 = (Button)findViewById(R.id.bt4);
		bt4.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				delTable();
			}
		});

		
		Button bt05 = (Button)findViewById(R.id.bt05);
		bt05.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				createTable();
			}
		});

    }
    private DatabaseHelper dbHelper;
    private static final String TABLE_NAME = "chapter";
    private static final String DB_NAME = "TestSqlite";
    private static final int DB_V = 1;
    private static final String TITLE="title";
    private static final String CONTENT="content";
    
    private static class DatabaseHelper extends SQLiteOpenHelper{
    	public DatabaseHelper(Context context) {
			super(context, DB_NAME, null, DB_V);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			String sql = "create table "+TABLE_NAME+"("+TITLE+" text not null,"
				+CONTENT+" text not null)";
			Log.i("sqlite create table", sql);
			db.execSQL(sql);
		}

		@Override
		public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		}
    	
    }
    
    private void insertChapter(){
    	String sql = "insert into "+TABLE_NAME+"("+TITLE+","+CONTENT+") values"
    		+"('星期四来了','星期五不远了');";
    	SQLiteDatabase db = dbHelper.getWritableDatabase();
    	try{
    		db.execSQL(sql);
    		Log.i("插入数据：", sql);
    		setTitle("插入数据成功");
    	}catch (Exception e) {
    		Log.i("插入数据：", sql);
    		setTitle("插入数据失败");
		}
    }
    
    private void queryChapter(){
    	SQLiteDatabase db = dbHelper.getWritableDatabase();
    	String[] col={TITLE,CONTENT};
    	Cursor cursor = db.query(TABLE_NAME, col, null, null, null,
    			null, null);
    	int count = cursor.getCount();
    	cursor.moveToFirst();
    	for(int i =0;i<count;i++){
    		Toast.makeText(StoreMainActivity.this, cursor.getString(0), 
    				Toast.LENGTH_SHORT).show();
    		Log.i("title"+i, cursor.getString(0));
    		Log.i("cotent"+i, cursor.getString(1));
    		cursor.moveToNext();
    	}
    	setTitle("当前记录数是："+count);
    }
    
    public void delChapter(){
    	SQLiteDatabase db = dbHelper.getWritableDatabase();
    	int res = db.delete(TABLE_NAME, "title='星期四来了'", null);
    	if(res>0)
    		setTitle("删除记录成功");
    	else
    		setTitle("删除记录失败");
    }
    
    
    public void delTable(){
    	SQLiteDatabase db = dbHelper.getWritableDatabase();
    	db.execSQL("drop table "+TABLE_NAME);
    }
    
    public void createTable(){
    	String sql = "create table "+TABLE_NAME+"("+TITLE+" text not null,"
			+CONTENT+" text not null)";
		Log.i("sqlite create table", sql);
		
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.execSQL(sql);
    }
    
    public void onStop(){
    	super.onStop();
    	
    	SharedPreferences sp = getSharedPreferences(SETTING_INFOS,0);
    	sp.edit().putString(UM_KEY, et1.getText().toString())
    		.putString(PWD_KEY, et2.getText().toString()).commit();
    }
}