package com.jackey.android.rss.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailContentActivity extends Activity {
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.detail);
		String content = null;
		Intent startingIntent = getIntent();

		if (startingIntent != null) {
			Bundle bundle = startingIntent
					.getBundleExtra("android.intent.extra.rssItem");
			if (bundle == null) {
				content = "������˼���������";
			} else {
				content = bundle.getString("title") + "\n\n"
						+ bundle.getString("pubdate") + "\n\n"
						+ bundle.getString("description").replace('\n', ' ')
						+ "\n\n��ϸ��Ϣ�����������ַ:\n" + bundle.getString("link");
			}
		} else {
			content = "������˼���������";

		}

		TextView textView = (TextView) findViewById(R.id.content);
		textView.setText(content);

		Button backbutton = (Button) findViewById(R.id.back);

		backbutton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}
}
