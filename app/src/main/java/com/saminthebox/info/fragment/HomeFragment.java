package com.saminthebox.info.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.saminthebox.info.customview.TopNewsWidget;
import com.saminthebox.info.customview.VideoNewsWidget;
import com.saminthebox.info.R;

public class HomeFragment extends Fragment {

    private ScrollView scrollView;
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
		topNewsWidget = rootView.findViewById(R.id.top_news);
		videoNewsWidget = rootView.findViewById(R.id.video_news);

		topNewsWidget.loadData();
		videoNewsWidget.loadData();

        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
			@Override
			public void onScrollChanged() {
				if(scrollView.getChildAt(0).getBottom() == (scrollView.getHeight() + scrollView.getScrollY())) {
					Toast.makeText(getActivity(), "scroll to bottom", Toast.LENGTH_SHORT).show();
				} 
			}
		});

        pullRefreshLayout = rootView.findViewById(R.id.pull_refresh);

        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                
            }
        });

		//pullRefreshLayout.setRefreshing(false);
		
        return rootView;
    }
}
