package com.he.android.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RatingBar;

public class RateBarActivity extends Activity {
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		setContentView(R.layout.ratebar);
		final RatingBar rb = (RatingBar)findViewById(R.id.RatingBar01);
		setTitle("星数："+rb.getNumStars()+",选中数："+rb.getProgress());
		 
		rb.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				setTitle("星数："+rb.getNumStars()+",选中数："+rb.getProgress());
			}
		});
		
	}
}
