package com.saminthebox.info.helper;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.saminthebox.info.network.ImageNetData;

public class ImageTask extends AsyncTask<Void, Void, Void> {

	private String url; 
	private ImageView imageView;
	private Bitmap bitmap;

	public ImageTask(String url, ImageView imageView) {
		this.url = url;
		this.imageView = imageView;
	}
	
	@Override
	protected Void doInBackground(Void... params) {
		bitmap = ImageNetData.getResult(url);

		return null;
	}

	@Override
	protected void onPostExecute(Void v) {
		imageView.setImageBitmap(bitmap);
	}
}