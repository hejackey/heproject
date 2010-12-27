package com.jackey.android.dial.activity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.jackey.android.dial.model.StudentModel;
import com.jackey.android.dial.util.GsonUtil;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Contacts.Phones;
import android.provider.Contacts.PhonesColumns;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class QuickDialActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final AutoCompleteTextView actv = (AutoCompleteTextView)findViewById(R.id.qkey);
        actv.setOnKeyListener(new OnKeyListener(){

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				String qkey = actv.getText().toString();
	        	Log.i("qkey===",qkey);
	        	
	        	ContentResolver cr = actv.getContext().getContentResolver();
	        	Cursor cursor = cr.query(Phones.CONTENT_URI, null, 
	        			PhonesColumns.NUMBER +" like '%"+qkey+"%' ", null, null);
	        	startManagingCursor(cursor);
	        	Log.i("count==", String.valueOf(cursor.getCount()));
	        	
	        	SimpleCursorAdapter adapter = new SimpleCursorAdapter(actv.getContext(),android.R.layout.simple_list_item_2,
	        			cursor,new String[]{Phones.NUMBER},
	        			new int[]{android.R.id.text1});
	    		
	    		actv.setAdapter(adapter);
	    		return false;
			}
        	
        });
        actv.addTextChangedListener(new TextWatcher(){
			@Override
			public void afterTextChanged(Editable s) {
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String qkey = actv.getText().toString();
	        	Log.i("qkey===",qkey);
	        	
	        	ContentResolver cr = actv.getContext().getContentResolver();
	        	Cursor cursor = cr.query(Phones.CONTENT_URI, null, 
	        			PhonesColumns.NUMBER +" like '%"+qkey+"%' ", null, null);
	        	startManagingCursor(cursor);
	        	Log.i("count==", String.valueOf(cursor.getCount()));
	        	
	        	SimpleCursorAdapter adapter = new SimpleCursorAdapter(actv.getContext(),android.R.layout.simple_list_item_2,
	        			cursor,new String[]{Phones.NAME,Phones.NUMBER},
	        			new int[]{android.R.id.text1,android.R.id.text2});
	    		
	    		actv.setAdapter(adapter);
			}
        	
        });
        
        Button query = (Button)findViewById(R.id.query);
        query.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				HttpPost request = new HttpPost(SERVER_URL);
				
				
				try {
					List<NameValuePair> params = new ArrayList<NameValuePair>();
					params.add(new BasicNameValuePair("id","1"));
					params.add(new BasicNameValuePair("type","json"));
					request.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
					
					/*
					 *该种请求方式，服务端获取不到id参数 
					HttpParams params = new BasicHttpParams();
					params.setParameter("id", "200");
					request.setParams(params);
					request.setHeader("Content-Type","application/x-www-form-urlencoded; charset=utf-8");*/
					
					HttpResponse res = new DefaultHttpClient().execute(request);
					
					if(res.getStatusLine().getStatusCode()!=404){
						
						String result = EntityUtils.toString(res.getEntity());
						
						Log.d("service result====",result);
						StudentModel student = GsonUtil.fromGson(result, StudentModel.class);
						/*Log.d("student name is",student.getName());
						Log.d("student sex is",student.getSex());
						Log.d("student age is",String.valueOf(student.getAge()));*/
						Toast.makeText(QuickDialActivity.this, "student name is "+student.getName()
								+",student sex is "+ student.getSex()
								+",studnet age is "+ student.getAge(), Toast.LENGTH_SHORT).show();
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
        	
        });
    }
    private static final String SERVER_URL="http://10.0.2.2:8080/BaseServiceApi/testApi.do";
    
}