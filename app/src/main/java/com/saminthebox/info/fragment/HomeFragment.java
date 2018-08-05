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
import android.widget.ListView;
import android.widget.ScrollView;

import com.baoyz.widget.PullRefreshLayout;

import com.saminthebox.info.R;
import com.saminthebox.info.adapter.TopNewsAdapter;
import com.saminthebox.info.constant.ADDR;
import com.saminthebox.info.customview.ThreePictureNewsLayout;
import com.saminthebox.info.database.model.News;
import com.saminthebox.info.network.GetNetData;

public class HomeFragment extends Fragment {

    private ListView topNewsList;
    private ThreePictureNewsLayout threePictureNewsLayout;
    private PullRefreshLayout pullRefreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, null);

        topNewsList = rootView.findViewById(R.id.top_news_list);
        threePictureNewsLayout = rootView.findViewById(R.id.three_picture_news);

        pullRefreshLayout = rootView.findViewById(R.id.pull_refresh);

        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                TopTask topTask = new TopTask();
                topTask.execute();
            }
        });

        TopTask topTask = new TopTask();
        topTask.execute();
		
        return rootView;
    }
	
	class TopTask extends AsyncTask<Void, Void, Void> {

        private JSONObject outdata;

        @Override
        protected Void doInBackground(Void... params) {
            outdata = GetNetData.getResult(ADDR.TOP_NEWS);
            
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            try {
                JSONObject dataJson = (JSONObject)outdata.get("data");
                JSONObject topJson = (JSONObject)dataJson.get("top");

                JSONArray topNewsesJson = topJson.getJSONArray("newses");

                ArrayList<News> newses = new ArrayList<News>();

                for (int i = 0; i < topNewsesJson.length(); i++) {
                    JSONObject newsJson = (JSONObject)topNewsesJson.get(i);

                    String title = newsJson.getString("title");
                    String imageUrl = newsJson.getString("image_url");

                    News news = new News();
                    news.setTitle(title);
                    news.setImageUrl(imageUrl);

                    newses.add(news);
                }

                TopNewsAdapter topNewsAdapter = new TopNewsAdapter(getActivity(), newses);
                topNewsList.setAdapter(topNewsAdapter);

                JSONObject threePictureNewsJson = (JSONObject)topJson.get("three_picture_news");
                String title = threePictureNewsJson.getString("title");
                String image1Url = threePictureNewsJson.getString("image1_url");
                String image2Url = threePictureNewsJson.getString("image2_url");
                String image3Url = threePictureNewsJson.getString("image3_url");

                threePictureNewsLayout.setTitle(title);
                threePictureNewsLayout.setImage1(image1Url);
                threePictureNewsLayout.setImage2(image2Url);
                threePictureNewsLayout.setImage3(image3Url);

                pullRefreshLayout.setRefreshing(false);

            } catch(JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
