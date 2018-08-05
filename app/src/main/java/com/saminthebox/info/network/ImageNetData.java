package com.saminthebox.info.network;

import java.net.URL;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageNetData {
	public static Bitmap getResult(String imgUrl) {
		Bitmap bitmap = null;

		try {
			URL url = new URL(imgUrl);
			HttpGet httpRequest = null;
			httpRequest = new HttpGet(url.toURI());

			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response = httpclient.execute(httpRequest);

			HttpEntity entity = response.getEntity();
			BufferedHttpEntity b_entity = new BufferedHttpEntity(entity);
			InputStream input = b_entity.getContent();

			bitmap = BitmapFactory.decodeStream(input);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return bitmap;
	}
}
