package com.saminthebox.info.network;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.saminthebox.info.constant.STATS;

public class GetNetData {
	public static JSONObject getResult(String urlString) {

		HttpURLConnection urlConnection = null;
		JSONObject outdata = new JSONObject();
		HashMap map = new HashMap();

		try {
			URL url = new URL(urlString);

			urlConnection = (HttpURLConnection)url.openConnection();

			int httpCode = urlConnection.getResponseCode();
			
			if (httpCode == STATS.HTTP_OK) {
				InputStream in = new BufferedInputStream(urlConnection.getInputStream());

				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				StringBuilder out = new StringBuilder();
				String line;
				
				while((line = reader.readLine()) != null){
					out.append(line);
				}
				
				String result = out.toString();
				reader.close();

				System.out.println(result);

				JSONObject data = new JSONObject(result);

				map.put("http_code", httpCode);
				map.put("data", data);
                outdata = new JSONObject(map);
            } else {
            	map.put("http_code", httpCode);
                outdata = new JSONObject(map);	 					 
            }	
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}finally {
			urlConnection.disconnect();
		}
		
		return outdata;
	}
}
