package com.saminthebox.info.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.saminthebox.info.database.model.Price;
import com.saminthebox.info.main.TradeChartActivity;
import com.saminthebox.info.R;

public class PriceAdapter extends BaseAdapter {

    private Context context;
	private ArrayList<Price> prices;
	
	public PriceAdapter(Context context, ArrayList<Price> prices) {
		this.context = context;
	    this.prices = prices;
	}
	
    @Override
    public int getCount() {
        return prices.size();
    } 

    @Override
    public Price getItem(int position) {	
        return prices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
	    View priceView = LayoutInflater.from(parent.getContext()).inflate(R.layout.price_item, parent, false);

        TextView titleText = priceView.findViewById(R.id.title);
        TextView valueText = priceView.findViewById(R.id.value);
        TextView trendText = priceView.findViewById(R.id.trend);

        Price price = prices.get(position);
        String title = price.getTitle();
        String value = price.getValue();
        String trend = price.getTrend();
        String trendSign = price.getTrendSign();

        titleText.setText(title);
        valueText.setText(value);
        trendText.setText(trend);

        if(trendSign.equals("p")) {
            trendText.setBackgroundColor(Color.parseColor("#50c382"));
        } else {
            trendText.setBackgroundColor(Color.parseColor("#ef7955"));
        }

        priceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TradeChartActivity.class);
                context.startActivity(intent);
            }
        });

        return priceView;
    }
} 


