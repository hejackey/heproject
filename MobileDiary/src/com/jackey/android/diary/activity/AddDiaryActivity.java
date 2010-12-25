package com.jackey.android.diary.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.jackey.android.diary.dao.DataBaseHelper;
import com.jackey.android.diary.model.DiaryModel;

public class AddDiaryActivity extends Activity {
	private static DataBaseHelper dbHelper;
	private EditText et1;
	private EditText et2;
	
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		setContentView(R.layout.add);
		dbHelper=new DataBaseHelper(this);
		
		et1 = (EditText)findViewById(R.id.et_title);
		et2 = (EditText)findViewById(R.id.et_content);
		
		Bundle bd = getIntent().getExtras();
		if(bd!=null){
			
			et1.setText(bd.getString("title"));
			et2.setText(bd.getString("content"));
			DiaryModel model = new DiaryModel();
			model.setId(bd.getInt("_id"));
			
			updateDiary(model);
		}
		else{
			insertDiary();
		}
	}
	
	public void insertDiary(){
		Button bt = (Button)findViewById(R.id.add_bt);
		bt.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				DiaryModel model = new DiaryModel();
				model.setTitle(et1.getText().toString());
				model.setContent(et2.getText().toString());
				
				dbHelper.insertDiary(dbHelper, model);
				
				setResult(0);
				finish();
			}
		});
	}
	
	public void updateDiary(final DiaryModel model){
		Button bt = (Button)findViewById(R.id.add_bt);
		bt.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				model.setTitle(et1.getText().toString());
				model.setContent(et2.getText().toString());
				
				dbHelper.updateDiary(dbHelper, model);
				
				setResult(0);
				finish();
			}
		});
	}
}
