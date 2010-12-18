package com.he.android.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.GridView;
import com.he.android.demo.R;

public class ImageAdapter extends BaseAdapter {
	private Context mContext;
	
	private Integer[] imageArray={
		R.drawable.grid_view_01,R.drawable.grid_view_02,
		R.drawable.grid_view_03,R.drawable.grid_view_04,
		R.drawable.grid_view_05,R.drawable.grid_view_06,
		R.drawable.grid_view_07,R.drawable.grid_view_08,
		R.drawable.grid_view_09
	};
	
	public ImageAdapter(Context c){
		this.mContext = c;
	}
	
	@Override
	public int getCount() {
		return imageArray.length;
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if(convertView == null){
			imageView = new ImageView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams(85,85));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(8,8,8,8);
		}
		else{
			imageView = (ImageView)convertView;
		}
		imageView.setImageResource(imageArray[position]);
		return imageView;
	}

}
