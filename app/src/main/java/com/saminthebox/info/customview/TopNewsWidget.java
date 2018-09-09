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

public class TopNewsWidget extends RelativeLayout {

    boolean[] isLoading;

    private TextView mTitle1View;
    private TextView mTitle2View;
    private TextView mTitle3View;
    private TextView nTitleView;
    private ImageView mImageView1;
    private ImageView mImageView2;
    private ImageView mImageView3;
	private ImageView nImageView1;
	private ImageView nImageView2;
	private ImageView nImageView3;

    public TopNewsWidget(Context context, AttributeSet attrs) {
        super(context, attrs);

		//this.isLoading = isLoading;

		LayoutInflater inflater = LayoutInflater.from(context);
		View rootView = inflater.inflate(R.layout.widget_top_news, this);

        mTitle1View = rootView.findViewById(R.id.mtitle1);
        mTitle2View = rootView.findViewById(R.id.mtitle2);
        mTitle3View = rootView.findViewById(R.id.mtitle3);
        nTitleView = rootView.findViewById(R.id.ntitle);

        mImageView1 = rootView.findViewById(R.id.mimage1);
        mImageView2 = rootView.findViewById(R.id.mimage2);
        mImageView3 = rootView.findViewById(R.id.mimage3);
        nImageView1 = rootView.findViewById(R.id.nimage1);
        nImageView2 = rootView.findViewById(R.id.nimage2);
        nImageView3 = rootView.findViewById(R.id.nimage3);
    }
	
	public void loadData() {
		TopNewsTask topNewsTask = new TopNewsTask();
        topNewsTask.execute();
	}
	
	class TopNewsTask extends AsyncTask<Void, Void, Void> {
        private JSONObject outdata;

        @Override
        protected Void doInBackground(Void... params) {
            //outdata = GetNetData.getResult(ADDR.TOP_NEWS);

            outdata = GetNetData.getResult(ADDR.TOP_NEWS);

            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            try {
                JSONObject dataJson = (JSONObject)outdata.get("data");
                JSONObject topJson = (JSONObject)dataJson.get("top");

                //top news1
                JSONObject topNews1 = (JSONObject)topJson.get("top_news1");
                String mTitle1 = topNews1.getString("title");
                String mImage1Url = topNews1.getString("image_url");

                mTitle1View.setText(mTitle1);

                ImageTask mImageTask1 = new ImageTask(mImage1Url, mImageView1);
                mImageTask1.execute();

                //top news2
                JSONObject topNews2 = (JSONObject)topJson.get("top_news2");
                String mTitle2 = topNews2.getString("title");
                String mImage2Url = topNews2.getString("image_url");

                mTitle2View.setText(mTitle2);

                ImageTask mImageTask2 = new ImageTask(mImage2Url, mImageView2);
                mImageTask2.execute();

                //top news3
                JSONObject topNews3 = (JSONObject)topJson.get("top_news3");
                String mTitle3 = topNews3.getString("title");
                String mImage3Url = topNews3.getString("image_url");

                mTitle3View.setText(mTitle3);

                ImageTask mImageTask3 = new ImageTask(mImage3Url, mImageView3);
                mImageTask3.execute();

                //three images news
                JSONObject threePictureNewsJson = (JSONObject)topJson.get("three_picture_news");
				
                String ntitle = threePictureNewsJson.getString("title");
                String nImage1Url = threePictureNewsJson.getString("image1_url");
                String nImage2Url = threePictureNewsJson.getString("image2_url");
                String nImage31Url = threePictureNewsJson.getString("image3_url");

                nTitleView.setText(ntitle);
             
				ImageTask nImageTask1 = new ImageTask(nImage1Url, nImageView1);
                nImageTask1.execute();

				ImageTask nImageTask2 = new ImageTask(nImage2Url, nImageView2);
                nImageTask2.execute();
		
				ImageTask nImageTask3 = new ImageTask(nImage31Url, nImageView3);
                nImageTask3.execute();

            } catch(JSONException e) {
                e.printStackTrace();
            }
        }
    }
}