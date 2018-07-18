package com.saminthebox.info.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.saminthebox.info.database.model.News;
import com.saminthebox.info.R;

public class TopNewsAdapter extends BaseAdapter {

    private Context context;
	private ArrayList<News> newses;
	
	public TopNewsAdapter(Context context, ArrayList<News> newses) {
		this.context = context;
	    this.newses = newses;
	}
	
    @Override
    public int getCount() {
        return newses.size();
    } 

    @Override
    public News getItem(int position) {
        return newses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
	    View news1View = LayoutInflater.from(parent.getContext()).inflate(R.layout.news1_item, parent, false);

        TextView titleText = news1View.findViewById(R.id.title);
        ImageView imgageView = news1View.findViewById(R.id.image);
		TextView descriptionView = news1View.findViewById(R.id.description);
		
		News news = newses.get(position);

		String title = news.getTitle();
        String description = news.getDescription();

        titleText.setText(title);
        //descriptionView.setText(description);
		
        return news1View;
    }
} 


