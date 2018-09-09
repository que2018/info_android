package com.saminthebox.info.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.saminthebox.info.customview.TopNewsWidget;
import com.saminthebox.info.customview.VideoNewsWidget;
import com.saminthebox.info.R;

import java.util.HashMap;

public class HomeFragment extends Fragment {


    private int pointer = 1;
    private HashMap<Integer, View> hmap;
    public boolean[] isLoading = {false};

    private ScrollView scrollView;

    private LinearLayout widgetLlistView;

    private TopNewsWidget topNewsWidget;
    private VideoNewsWidget videoNewsWidget;
    private PullRefreshLayout pullRefreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, null);

        scrollView = rootView.findViewById(R.id.scroll);

        widgetLlistView = rootView.findViewById(R.id.widget_list);


        topNewsWidget = rootView.findViewById(R.id.top_news);
		videoNewsWidget = rootView.findViewById(R.id.video_news);

		topNewsWidget.loadData();
		videoNewsWidget.loadData();

        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
			@Override
			public void onScrollChanged() {
				if(scrollView.getChildAt(0).getBottom() == (scrollView.getHeight() + scrollView.getScrollY())) {
				    if(!isLoading[0]) {
                        Toast.makeText(getActivity(), "scroll to bottom", Toast.LENGTH_SHORT).show();

                        //View view = hmap.get(pointer);
                        //view.loadData();
                        //widgetLlistView.addView(view);

                        //pointer++;
                    }
                }
			}
		});

        pullRefreshLayout = rootView.findViewById(R.id.pull_refresh);

        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                
            }
        });

		hmap = new HashMap<Integer, View>();

        //hmap.put(1, new TopNewsWidget(getActivity(), isLoading));
        //hmap.put(2, new TopNewsWidget(getActivity(), isLoading));
        //hmap.put(3, new TopNewsWidget(getActivity(), isLoading));
        //hmap.put(4, new TopNewsWidget(getActivity(), isLoading));

		//pullRefreshLayout.setRefreshing(false);
		
        return rootView;
    }
}
