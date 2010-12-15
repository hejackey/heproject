package com.he.android.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class ProgressBarActivity extends Activity {
	
	/*
	 * 如何让进度条动态增加？？
	 * public void onStart(){
		ProgressBar pb= (ProgressBar)findViewById(R.id.ProgressBar02);
		int start=20;
		for(int i=0; i<5; i++){
			start+=20;
			pb.setProgress(start);
			pb.setSecondaryProgress(start);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}*/
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		setContentView(R.layout.progressbar);
	}
}
