package com.jackey.android.rss.activity;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TabHost.OnTabChangeListener;

import com.jackey.android.rss.data.RabbitRSSFeed;
import com.jackey.android.rss.handle.RabbitRSSHandler;
import com.jackey.android.rss.model.RssSite;

public class StartActivity extends TabActivity implements OnItemClickListener{
    /** Called when the activity is first created. */
	private final String SOHU_URL="http://news.sohu.com/rss/scroll.xml";
	/*private final String MOBILE2_URL="http://feed.mobile20.com.cn/";
	private final String SHOWEB2_URL="http://feed.feedsky.com/showeb20";
	private final String CNBETA_URL="http://mrss.dokoda.jp/a/http/www.cnbeta.com/backend.php";*/
	private RabbitRSSFeed feed;
	private TabHost tab;
	private List<RssSite> list  = new ArrayList<RssSite>();
	private String SETTING_INFOS="rssSite_db";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean res = CheckNetwork();
		if(res){
			refreshLayout();
		}
		else{
        	Toast.makeText(StartActivity.this, "网络畅通，请稍候...", Toast.LENGTH_SHORT).show();
        }
    }
    
    private void refreshLayout(){
        tab = getTabHost();
        tab.clearAllTabs();
        LayoutInflater.from(this).inflate(R.layout.main,
				tab.getTabContentView(), true);
        
	    RssSite rss;
	    if(list.size()==0){
        	rss = new RssSite();
        	rss.setTitle("搜狐新闻");
        	rss.setUrl(SOHU_URL);
        	list.add(rss);
        }
	    
        for(int i=1;i<=list.size();i++){
        	rss = (RssSite)list.get(i-1);
	    	
        	if(i==list.size()){
	        	feed = getFeed(rss.getUrl());
	            showListView(R.id.listview1);
        	}
        	tab.addTab(tab.newTabSpec("tab"+i).setIndicator(rss.getTitle()).setContent(R.id.listview1));
        }
	    tab.setCurrentTab(list.size()-1);
       
	    tab.setOnTabChangedListener(new OnTabChangeListener(){
			@Override
			public void onTabChanged(String tabId) {
				Log.i("click tab id is ", tabId);
				RssSite rss;
				for(int i=1;i<=list.size();i++){
					if(tabId.equals("tab"+i)){
						rss = (RssSite)list.get(i-1);
						feed = getFeed(rss.getUrl());
						showListView(R.id.listview1);
					}
				}
			}
        });
    }
    private RabbitRSSFeed getFeed(String urlString)
    {
    	try
    	{
    	   URL url = new URL(urlString);

           SAXParserFactory factory = SAXParserFactory.newInstance();
           SAXParser parser = factory.newSAXParser();
           XMLReader xmlreader = parser.getXMLReader();
           
           RabbitRSSHandler rssHandler = new RabbitRSSHandler();
           xmlreader.setContentHandler(rssHandler);

           InputSource is = new InputSource(url.openStream());    
           xmlreader.parse(is);
           
  
           return rssHandler.getFeed();
    	}
    	catch (Exception ee)
    	{
    		Log.i("parser feed fail=========", ee.getMessage());
    		return null;
    	}
    }
    
    public void showListView(int vid){
    	ListView itemlist = (ListView)findViewById(vid);
    	SimpleAdapter adapter = new SimpleAdapter(this, feed.getAllItemsForListView(),
	       		 android.R.layout.simple_list_item_2, new String[] { "title","pubdate" },
	       		 new int[] { android.R.id.text1 , android.R.id.text2});
	    itemlist.setAdapter(adapter);
	    itemlist.setOnItemClickListener(this);
    }
    
    public void onItemClick(AdapterView<?> parent, View v, int position, long id)
    {
    	Intent itemintent = new Intent(this,DetailContentActivity.class);
    	
    	Bundle b = new Bundle();
    	b.putString("title", feed.getItem(position).getTitle());
    	b.putString("description", feed.getItem(position).getDescription());
    	b.putString("link", feed.getItem(position).getLink());
    	
    	b.putString("pubdate", feed.getItem(position).getPubDate());
		   	 
    	itemintent.putExtra("android.intent.extra.rssItem", b);
    	startActivityForResult(itemintent, 0);
    }
    
    private boolean CheckNetwork() {
        boolean flag = false;
        ConnectivityManager cwjManager = (ConnectivityManager) getSystemService(StartActivity.this.CONNECTIVITY_SERVICE);
        if (cwjManager.getActiveNetworkInfo() != null)
            flag = cwjManager.getActiveNetworkInfo().isAvailable();
        if (!flag) {
            Builder b = new AlertDialog.Builder(this).setTitle("没有可用的网络").setMessage("请开启GPRS或WIFI网络连接");
            b.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    Intent mIntent = new Intent("/");
                    ComponentName comp = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
                    mIntent.setComponent(comp);
                    mIntent.setAction("<span class='hilite'>android</span>.intent.action.VIEW");
                    startActivity(mIntent);
                }
            }).setNeutralButton("取消", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    dialog.cancel();
                }
            }).create();
            b.show();
        }
        
        return flag;
    }
    public boolean onCreateOptionsMenu(Menu menu){
    	if(list.size()<5){
    		super.onCreateOptionsMenu(menu);
    		menu.add(Menu.NONE, 0, Menu.NONE, "添加RSS站点");
    		menu.add(Menu.NONE, 1, Menu.NONE, "删除RSS站点");
    		return true;
    	}
    	
    	return false;
	}  
    public boolean onOptionsItemSelected(MenuItem item){
    	Log.i("list size is ",String.valueOf(list.size()));
    	if(list.size()>3){
    		Toast.makeText(StartActivity.this, "最多只能添加4个标签，请先删除一个标签", Toast.LENGTH_SHORT).show();
    		return false;
    	}
    	else{
    		switch (item.getItemId()){
	    		case 0:{
	    			Intent intent = new Intent(StartActivity.this,AddRssSiteActivity.class);
	    			startActivityForResult(intent, 0);
	    		};
	    		break;
	    		case 1:{
	    			int index = tab.getCurrentTab();
	    			if(list.size()==1){
	    				Toast.makeText(StartActivity.this, "别删了吧，最后一个了！！", Toast.LENGTH_SHORT).show();
	    	    		return false;
	    			}
	    			else{
		    			//tab.clearAllTabs();
		    			list.remove(index);
		    			refreshLayout();
	    			}
	    		};	
	    		break;
    		}
    		return true;
	   	 }
	}
    
    public void onActivityResult(int reqCode,int resCode,Intent mIntent){
    	if(resCode==RESULT_OK){
    		Bundle bd = mIntent.getExtras();
    		Toast.makeText(StartActivity.this, bd.getString("title")+","+
    				bd.getString("url"), Toast.LENGTH_SHORT).show();
    		RssSite rss = new RssSite();
    		rss.setTitle(bd.getString("title"));
    		rss.setUrl(bd.getString("url"));
    		list.add(rss);
    		
    		refreshLayout();
    	}
    } 
    
    
}