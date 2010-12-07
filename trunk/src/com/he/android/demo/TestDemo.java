package com.he.android.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class TestDemo extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView tx = new TextView(this);
        tx.setBackgroundColor(333333);
        tx.setText("<a href='www.google.com'>hello world!!!!²ÙÄãÂè</a>");
        setContentView(tx);
        
    }
}