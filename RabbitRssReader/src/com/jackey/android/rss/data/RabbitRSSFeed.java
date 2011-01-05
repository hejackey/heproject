package com.jackey.android.rss.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RabbitRSSFeed {
	private String title = null;
	private String pubdate = null;
	private int itemcount = 0;
	private List<RabbitRSSItem> itemlist;
	
	
	 public RabbitRSSFeed()
	{
		itemlist = new ArrayList<RabbitRSSItem>(); 
	}
	public int addItem(RabbitRSSItem item)
	{
		itemlist.add(item);
		itemcount++;
		return itemcount;
	}
	public RabbitRSSItem getItem(int location)
	{
		return itemlist.get(location);
	}
	public List<RabbitRSSItem> getAllItems()
	{
		return itemlist;
	}
	public List<Map<String, Object>> getAllItemsForListView(){
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		int size = itemlist.size();
		for(int i=0;i<size;i++){
			HashMap<String, Object>	item = new HashMap<String, Object>();
			item.put(RabbitRSSItem.TITLE, itemlist.get(i).getTitle());
			item.put(RabbitRSSItem.PUBDATE, itemlist.get(i).getPubDate());
			data.add(item);
		}
		return data;
	}
	int getItemCount()
	{
		return itemcount;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public void setPubDate(String pubdate)
	{
		this.pubdate = pubdate;
	}
	public String getTitle()
	{
		return title;
	}
	public String getPubDate()
	{
		return pubdate;
	}
}
