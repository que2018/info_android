package com.saminthebox.info.fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ListView;
import android.widget.ScrollView;

import com.saminthebox.info.R;
import com.saminthebox.info.adapter.TopNewsAdapter;
import com.saminthebox.info.constant.ADDR;
import com.saminthebox.info.database.model.News;
import com.saminthebox.info.network.GetNetData;
import com.yalantis.phoenix.PullToRefreshView;

public class HomeFragment extends Fragment {

    private ScrollView scrollWrapView;
    private ListView topNewsList;
    private PullToRefreshView pullToRefreshView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, null);

        scrollWrapView = rootView.findViewById(R.id.scroll_wrap);
        topNewsList = rootView.findViewById(R.id.top_news_list);

        pullToRefreshView = rootView.findViewById(R.id.pull_to_refresh);
		pullToRefreshView.setRefreshing(false);

       /*  pullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullToRefreshView.setRefreshing(false);
                    }
                }, 1000);
            }
        }); */

        scrollWrapView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int scrollY = scrollWrapView.getScrollY();
                int scrollX = scrollWrapView.getScrollX();

                Log.d("home framgnet", "it is scrolling ....");
            }
        });

		TopNewsTask topNewsTask = new TopNewsTask();
        topNewsTask.execute();
		
        return rootView;
    }
	
	class TopNewsTask extends AsyncTask<Void, Void, Void> {

        private JSONObject outdata;

        @Override
        protected Void doInBackground(Void... params) {
            outdata = GetNetData.getResult(ADDR.TOP_NEWS);

            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            try {
                JSONObject data = (JSONObject)outdata.get("data");

                JSONArray topNewsesJson = data.getJSONArray("top_newses");

                ArrayList<News> newses = new ArrayList<News>();

                for (int i = 0; i < topNewsesJson.length(); i++) {
                    JSONObject newsJson = (JSONObject)topNewsesJson.get(i);

                    String title = newsJson.getString("title");
                    String imageUrl = newsJson.getString("image_url");
                    String description = newsJson.getString("description");

                    News news = new News();
                    news.setTitle(title);
                    news.setImageUrl(imageUrl);
                    news.setDescription(description);

                    newses.add(news);
                }

                TopNewsAdapter topNewsAdapter = new TopNewsAdapter(getActivity(), newses);
                topNewsList.setAdapter(topNewsAdapter);

            } catch(JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
