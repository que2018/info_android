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
import com.saminthebox.info.customview.TopNewsBlock;
import com.saminthebox.info.R;

public class HomeFragment extends Fragment {

    private ScrollView scrollView;
    private TopNewsBlock topNewsBlock;
    private TopNewsBlock topNews1Block;
    private TopNewsBlock topNews2Block;
    private TopNewsBlock topNews3Block;
    private PullRefreshLayout pullRefreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, null);

        scrollView = rootView.findViewById(R.id.scroll);

        scrollView.getViewTreeObserver()
                .addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                    @Override
                    public void onScrollChanged() {
                        if (scrollView.getChildAt(0).getBottom()
                                == (scrollView.getHeight() + scrollView.getScrollY())) {
                            Toast.makeText(getActivity(), "scroll to bottom",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            //scroll view is not at bottom
                        }
                    }
                });


        topNewsBlock = rootView.findViewById(R.id.top_news);
        topNews1Block = rootView.findViewById(R.id.top_news1);
        topNews2Block = rootView.findViewById(R.id.top_news2);
        topNews3Block = rootView.findViewById(R.id.top_news3);


        pullRefreshLayout = rootView.findViewById(R.id.pull_refresh);

        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                topNewsBlock.loadData();
            }
        });

		//pullRefreshLayout.setRefreshing(false);
		
        topNewsBlock.loadData();
        topNews1Block.loadData();
        topNews2Block.loadData();
        topNews3Block.loadData();
		
        return rootView;
    }
}
