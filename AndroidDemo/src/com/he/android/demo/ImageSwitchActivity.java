package com.he.android.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;


public class ImageSwitchActivity extends Activity implements
AdapterView.OnItemSelectedListener, ViewSwitcher.ViewFactory{
	private ImageSwitcher mSwitch;
	
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.image_switch);
		setTitle("ImageShowActivity");
		
		mSwitch = (ImageSwitcher)findViewById(R.id.switcher);
		try{
			mSwitch.setFactory(this);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		mSwitch.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
		mSwitch.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
		
		Gallery gallery = (Gallery)findViewById(R.id.gallery);
		gallery.setAdapter(new ImageAdapter(this));
		gallery.setOnItemSelectedListener(this);
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		mSwitch.setImageResource(mImageIds[position]);
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		
	}

	@Override
	public View makeView() {
		ImageView iv = new ImageView(this);
		iv.setBackgroundColor(0xFF000000);
		iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
		iv.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		
		return iv;
	}
	
	public class ImageAdapter extends BaseAdapter {
        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView i = new ImageView(mContext);

            i.setImageResource(mThumbIds[position]);
            i.setAdjustViewBounds(true);
            i.setLayoutParams(new Gallery.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            i.setBackgroundResource(R.drawable.picture_frame);
            return i;
        }

        private Context mContext;

    }
 
	private Integer[] mThumbIds = {
            R.drawable.sample_thumb_0, R.drawable.sample_thumb_1,
            R.drawable.sample_thumb_2, R.drawable.sample_thumb_3,
            R.drawable.sample_thumb_4, R.drawable.sample_thumb_5,
            R.drawable.sample_thumb_6, R.drawable.sample_thumb_7};

    private Integer[] mImageIds = {
            R.drawable.sample_0, R.drawable.sample_1, R.drawable.sample_2,
            R.drawable.sample_3, R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7};
}
