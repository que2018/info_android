package com.saminthebox.info.customview;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.saminthebox.info.constant.ADDR;
import com.saminthebox.info.network.GetNetData;
import com.saminthebox.info.R;

public class VideoNewsWidget extends RelativeLayout {

    private TextView titleView;
    private VideoView videoView;
	
    public VideoNewsWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater inflater = LayoutInflater.from(context);
		View rootView = inflater.inflate(R.layout.widget_video_news, this);

        titleView = rootView.findViewById(R.id.title);
        videoView = rootView.findViewById(R.id.video);
    }
	
	public void loadData() {
		VideoNewsTask videoNewsTask = new VideoNewsTask();
        videoNewsTask.execute();
	}
	
	class VideoNewsTask extends AsyncTask<Void, Void, Void> {
        private JSONObject outdata;

        @Override
        protected Void doInBackground(Void... params) {
            outdata = GetNetData.getResult(ADDR.TOP_VIDEO);

            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            try {
                JSONObject dataJson = (JSONObject)outdata.get("data");

                JSONObject topVideoJson = (JSONObject)dataJson.get("top_video");
                String title = topVideoJson.getString("title");
                String videoUrl = topVideoJson.getString("video_url");

                titleView.setText(title);
                Uri uri = Uri.parse(videoUrl);
                videoView.setVideoURI(uri);
                //videoView.start();

            } catch(JSONException e) {
                e.printStackTrace();
            }
        }
    }
}