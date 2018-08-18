package com.saminthebox.info.network;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.saminthebox.info.constant.STATS;

public class PostNetData {
    public static JSONObject getResult(String url, ArrayList<BasicNameValuePair> postdata) {
        JSONObject outdata = new JSONObject();
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url);

        try {
            httppost.setEntity(new UrlEncodedFormEntity(postdata));
            HttpResponse response = httpclient.execute(httppost);
            			
			int httpCode = response.getStatusLine().getStatusCode();	
            HashMap map = new HashMap();	
						
			if (httpCode == STATS.HTTP_OK) {
                String result = EntityUtils.toString(response.getEntity(), "UTF-8");
				JSONObject data = new JSONObject(result);
				
				map.put("http_code", httpCode);
				map.put("data", data);
                outdata = new JSONObject(map);	
			
			} else {
            	map.put("http_code", httpCode);
                outdata = new JSONObject(map);	 			 
            } 
        } catch (ClientProtocolException e) {
			e.printStackTrace();	
        } catch (IOException e) {
			e.printStackTrace();	
        } catch(JSONException e) {
			e.printStackTrace();	
		}
        
        return outdata;
    }
	
	public static JSONObject getResult2(String urlString, ArrayList<BasicNameValuePair> postdata) {
		HttpURLConnection urlConnection = null;
		JSONObject outdata = new JSONObject();
		HashMap map = new HashMap();

		try {
			URL url = new URL(urlString);
			urlConnection = (HttpURLConnection)url.openConnection();
			
			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod("POST");
			
			//String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
			String urlParameter = getQuery(postdata);
			
			DataOutputStream dataOutputStream = new DataOutputStream(urlConnection.getOutputStream());
			dataOutputStream.writeBytes(urlParameter);
			dataOutputStream.flush();
			dataOutputStream.close();

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
        } catch (ClientProtocolException e) {
			e.printStackTrace();	
        } catch (IOException e) {
			e.printStackTrace();	
        } catch(JSONException e) {
			e.printStackTrace();	
		}  finally {
			urlConnection.disconnect();
		}
        
        return outdata;
    }
	
	private static String getQuery(ArrayList<BasicNameValuePair> params) throws UnsupportedEncodingException
	{
		StringBuilder result = new StringBuilder();
		boolean first = true;

		for (NameValuePair pair : params)
		{
			if (first)
				first = false;
			else
				result.append("&");

			result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
		}

		return result.toString();
	}
	
}
