package com.saminthebox.info.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baoyz.widget.PullRefreshLayout;
import com.saminthebox.info.customview.TopNewsBlock;
import com.saminthebox.info.R;

public class HomeFragment extends Fragment {
	
    private TopNewsBlock topNewsBlock;
    private PullRefreshLayout pullRefreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, null);

        topNewsBlock = rootView.findViewById(R.id.top_news);

        pullRefreshLayout = rootView.findViewById(R.id.pull_refresh);

        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                topNewsBlock.loadData();
            }
        });

		//pullRefreshLayout.setRefreshing(false);
		
        topNewsBlock.loadData();
		
        return rootView;
    }
}
