package com.saminthebox.info.customview;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.saminthebox.info.constant.ADDR;
import com.saminthebox.info.helper.ImageTask;
import com.saminthebox.info.network.GetNetData;
import com.saminthebox.info.R;

public class VideoNewsWidget extends RelativeLayout {

    private TextView titleView;
    private videoView videoView;
	
    public TopNewsBlock(Context context, AttributeSet attrs) {
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
            outdata = GetNetData.getResult(ADDR.TOP_NEWS);

            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            try {
                JSONObject dataJson = (JSONObject)outdata.get("data");
                
            } catch(JSONException e) {
                e.printStackTrace();
            }
        }
    }
}