package com.saminthebox.info.customview;

import java.util.HashMap;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

public class LabelRelativeLayout extends RelativeLayout {

    private LayoutInflater layoutInflater;

    public LabelRelativeLayout(Context context) {
        super(context);
		layoutInflater = LayoutInflater.from(context);
    }
	
    public LabelRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		layoutInflater = LayoutInflater.from(context);
    }
	
    public LabelRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		layoutInflater = LayoutInflater.from(context);
    }
}