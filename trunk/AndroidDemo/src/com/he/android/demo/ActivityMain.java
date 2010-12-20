package com.he.android.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ActivityMain extends Activity {
	OnClickListener listen0;
	OnClickListener listen1;
	OnClickListener listen2;
	OnClickListener listen3;
	Button button0;
	Button button1;
	Button button2;
	Button button3;
	
	Button menuBt1;
	Button menuBt2;
	
	public static final int item0 = Menu.FIRST;
	public static final int item1= Menu.FIRST+1;
	public static final int item2= Menu.FIRST+2;
	public static final int item3= Menu.FIRST+3;
	public static final int item4= Menu.FIRST+4;
	protected static final int REQUEST_CODE = 0;
	
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		
		listen0= new OnClickListener(){
			public void onClick(View v){
				Intent intent0= new Intent(ActivityMain.this,ActivityFrameLayout.class);
				setTitle("FrameLayout");
				startActivity(intent0);
			}
		};
		listen1= new OnClickListener(){
			public void onClick(View v){
				Intent intent1= new Intent(ActivityMain.this,ActivityRelativeLayout.class);
				setTitle("FrameLayout");
				startActivity(intent1);
			}
		};
		listen2= new OnClickListener(){
			public void onClick(View v){
				Intent intent2= new Intent(ActivityMain.this,ActivityLayout.class);
				setTitle("this is activity layout");
				startActivity(intent2);
			}
		};
		listen3= new OnClickListener(){
			public void onClick(View v){
				Intent intent3= new Intent(ActivityMain.this,ActivityTableLayout.class);
				setTitle("this is table layout");
				startActivity(intent3);
			}
		};
		
		setContentView(R.layout.main);

		Button but4 = (Button)findViewById(R.id.button4);
		but4.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				setTitle("这是button测试");
				TextView tv1= (TextView)findViewById(R.id.but4val);
				CharSequence cs = tv1.getText();
				
				tv1.setText("原来值是："+cs+",新值：这是button4测试的值");
			}
		});
		
		Button but5 = (Button)findViewById(R.id.button5);
		but5.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(ActivityMain.this, EditeTextActivity.class);
				startActivity(intent);
			}
		});
		
		Button but6 = (Button)findViewById(R.id.button6);
		but6.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(ActivityMain.this, CheckBoxActivity.class);
				startActivity(intent);
			}
		});
		
		Button but7 = (Button)findViewById(R.id.button7);
		but7.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(ActivityMain.this, RadioGroupActivity.class);
				startActivity(intent);
			}
		});
		
		Button but8 = (Button)findViewById(R.id.button8);
		but8.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(ActivityMain.this, SpinnerActivity.class);
				startActivity(intent);
			}
		});
		
		Button but9 = (Button)findViewById(R.id.button9);
		but9.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(ActivityMain.this, AutoCompleteActivity.class);
				startActivity(intent);
			}
		});
		
		Button but10 = (Button)findViewById(R.id.button10);
		but10.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(ActivityMain.this, DatePickerActivity.class);
				startActivity(intent);
			}
		});
		
		Button but11 = (Button)findViewById(R.id.button11);
		but11.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(ActivityMain.this, TimePickerActivity.class);
				startActivity(intent);
			}
		});
		
		Button but12 = (Button)findViewById(R.id.button12);
		but12.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(ActivityMain.this, ProgressBarActivity.class);
				startActivity(intent);
			}
		});
		
		Button but13 = (Button)findViewById(R.id.button13);
		but13.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(ActivityMain.this, RateBarActivity.class);
				startActivity(intent);
			}
		});

		Button but14 = (Button)findViewById(R.id.button14);
		but14.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(ActivityMain.this, TabDemoActivity.class);
				intent.putExtra("param", "我也可以传参数");
				startActivity(intent);
			}
		});
		
		Button but15 = (Button)findViewById(R.id.button15);
		but15.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(ActivityMain.this, GridViewActivity.class);
				startActivity(intent);
			}
		});
		
		Button but16 = (Button)findViewById(R.id.button16);
		but16.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(ActivityMain.this, ImageSwitchActivity.class);
				startActivity(intent);
			}
		});
		
		button0= (Button) findViewById(R.id.button0);
		button0.setOnClickListener(listen0);
		button1= (Button) findViewById(R.id.button1);
		button1.setOnClickListener(listen1);
		button2= (Button) findViewById(R.id.button2);
		button2.setOnClickListener(listen2);
		button3= (Button) findViewById(R.id.button3);
		button3.setOnClickListener(listen3);
		
		
		//////////////////menu测试///////////////////////////////
		menuBt1 = (Button)findViewById(R.id.menubt1);
		menuBt2 = (Button)findViewById(R.id.menubt2);
		menuBt1.setVisibility(View.INVISIBLE);
		menuBt2.setVisibility(View.INVISIBLE);
		
		//////////////////intent测试/////////////////////////////
		Button intentBt1 = (Button)findViewById(R.id.intentbt1);
		intentBt1.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(ActivityMain.this,IntentParaActivity.class);
				intent.putExtra("actm", "我是来自activity main的参数");
				intent.putExtra("actm1", "2号参数");
				startActivityForResult(intent,REQUEST_CODE);
			}
		});
		
		Button intentBt2 = (Button)findViewById(R.id.intentbt2);
		intentBt2.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(ActivityMain.this,IntentActivity.class);
				startActivity(intent);
			}
		});
		
		///////////////////ListView//////////////////////////////////
		Button listVBt1 = (Button)findViewById(R.id.listVBt1);
		listVBt1.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(ActivityMain.this,ListViewActivity1.class);
				startActivity(intent);
			}
		});
		
		Button listVBt2 = (Button)findViewById(R.id.listVBt2);
		listVBt2.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(ActivityMain.this,ListViewActivity2.class);
				startActivity(intent);
			}
		});
		
		Button listVBt3 = (Button)findViewById(R.id.listVBt3);
		listVBt3.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(ActivityMain.this,ListViewActivity3.class);
				startActivity(intent);
			}
		});
	}
	
	public boolean onCreateOptionsMenu(Menu menu){
		super.onCreateOptionsMenu(menu);
		
		menu.add(Menu.NONE, item0, Menu.NONE, "显示按钮1").setIcon(R.drawable.icon);
		menu.add(Menu.NONE, item1, Menu.NONE, "显示按钮2");
		menu.add(Menu.NONE, item2, Menu.NONE, "显示按钮3");
		menu.add(Menu.NONE, item3, Menu.NONE, "显示按钮4");
		menu.add(Menu.NONE, item4, Menu.NONE, "显示按钮5");
		//menu.findItem(item1);
		
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
		case item0:
			showMenuBut1();
			break;
		case item1:
			showMenuBut2();
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	public void showMenuBut1(){
		setTitle("显示菜单1");
		menuBt1.setVisibility(View.VISIBLE);
		menuBt2.setVisibility(View.INVISIBLE);
	}
	
	public void showMenuBut2(){
		setTitle("显示菜单2");
		menuBt2.setVisibility(View.VISIBLE);
		menuBt1.setVisibility(View.INVISIBLE);
	}
	
	//IntentParaActivity点击按钮回调的函数
	public void onActivityResult(int reqCode,int resCode,Intent mIntent){
		String param="";
		if(reqCode == REQUEST_CODE){
			if(resCode == RESULT_CANCELED){
				setTitle("取消");
			}
			else if(resCode == RESULT_OK){
				Bundle extra = mIntent.getExtras();
				if(extra != null){
					param = extra.getString("intentParm");
				}
				setTitle(param);
			}
		}
	}
}
