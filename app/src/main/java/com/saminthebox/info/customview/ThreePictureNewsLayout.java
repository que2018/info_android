package com.saminthebox.info.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.saminthebox.info.helper.ImageTask;
import com.saminthebox.info.R;

public class ThreePictureNewsLayout extends RelativeLayout {

	private TextView titleView;
	private ImageView imageView1;
	private ImageView imageView2;
	private ImageView imageView3;

    public ThreePictureNewsLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater inflater = LayoutInflater.from(context);
		View rootView = inflater.inflate(R.layout.widget_three_picture_news, this);
		
		titleView = rootView.findViewById(R.id.title);
		imageView1 = rootView.findViewById(R.id.image1);
		imageView2 = rootView.findViewById(R.id.image2);
		imageView3 = rootView.findViewById(R.id.image3);
    }

    public void setTitle(String title) {
    	titleView.setText(title);
	}

	public void setImage1(String url) {
    	ImageTask imageTask = new ImageTask(url, imageView1);
		imageTask.execute();
	}
	
	public void setImage2(String url) {
    	ImageTask imageTask = new ImageTask(url, imageView2);
		imageTask.execute();
	}
	
	public void setImage3(String url) {
    	ImageTask imageTask = new ImageTask(url, imageView3);
		imageTask.execute();
	}
}