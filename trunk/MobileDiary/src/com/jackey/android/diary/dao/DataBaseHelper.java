package com.jackey.android.diary.dao;


import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.jackey.android.diary.model.DiaryModel;
import com.jackey.android.diary.util.DateUtil;

public class DataBaseHelper extends SQLiteOpenHelper {
	public String TITLE="title";
    public String CONTENT="content";
    public String CREATETIME="create_time";
    public String ID="_id";
    private static String DBNAME="stu_android";
    private static int DB_V=1;
    private static String TABLENAME="diary";
    private static DataBaseHelper dbHelper;
    private Context ct;
    
    public DataBaseHelper(Context context) {
		super(context, DBNAME, null, DB_V);
		ct = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table "+TABLENAME+"("+ID+" integer primary key autoincrement,"+TITLE+" text not null,"
		+CONTENT+" text not null,"+CREATETIME+" date not null)";
		Log.i("sqlite create table", sql);
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

	public DataBaseHelper open(){
		dbHelper = new DataBaseHelper(ct);
		return dbHelper;
	}
	
	public Cursor queryDiary(DataBaseHelper dbHelper){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		/*return db.query("diary", null, null, null, null, null, null);*/
		return db.query("diary", new String[] { ID, TITLE,
				CONTENT, CREATETIME }, null, null, null, null, null);
	}
	
	public void insertDiary(DataBaseHelper dbHelper,DiaryModel model){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		String sql = "insert into "+TABLENAME+" ("+TITLE+","+CONTENT+","+CREATETIME
			+") values('"+model.getTitle()+"','"+model.getContent()+"','"
			+DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm")+"')";
		
		db.execSQL(sql);
	}
	
	public void updateDiary(DataBaseHelper dbHelper,DiaryModel model){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(TITLE, model.getTitle());
		cv.put(CONTENT, model.getContent());
		cv.put(CREATETIME, DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm"));
		
		db.update(TABLENAME, cv, ID+"="+model.getId(), null);
		/*String sql = "insert into "+TABLENAME+" ("+TITLE+","+CONTENT+","+CREATETIME
			+") values('"+model.getTitle()+"','"+model.getContent()+"','"
			+DateUtil.formatDate(new Date(), "yyyy-MM-dd")+"')";
		
		db.execSQL(sql);*/
	}
	
	public void delTable(DataBaseHelper dbHelper){
		String sql = "drop table diary";
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.execSQL(sql);
	}
	
}
