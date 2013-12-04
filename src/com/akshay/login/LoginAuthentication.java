package com.akshay.login;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;


import android.os.AsyncTask;
import android.util.Log;

public class LoginAuthentication extends AsyncTask<String, Void, JSONObject>{

	static final String DEBUG_TAG = "LoginAuthentication"; 
	LoginScreenActivity act;
	String email;
	String password;
	
	public LoginAuthentication(String emailId, String pass, LoginScreenActivity lsa) {
		email = emailId;
		password = pass;
		act = lsa;
	}
	
	@Override
	protected JSONObject doInBackground(String... params) {
		InputStream inputStream = null;
		JSONObject jsonObject = null;
		String jsonString = "";
		try
		{
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(params[0]);
	        List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>(2);
	        // you can add number of fields in the numvalue pair
	        // depending upon your api
	        // like username, passowrd, authID etc
	        nameValuePairs.add(new BasicNameValuePair("username", email));
	        nameValuePairs.add(new BasicNameValuePair("password", password));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			inputStream = entity.getContent();
		}
		catch(Exception e)
		{
			Log.e(DEBUG_TAG, "Connection Error " + e.toString());
		}
    	//Convert Response
    	try
    	{
	        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"),8);
	        String stringBuilder = "";
	        String line = null;
	        while ((line = reader.readLine()) != null) 
	        {
	        	stringBuilder += line;
	        }
	        inputStream.close();
	        if(!stringBuilder.toString().equalsIgnoreCase("null\t"))
	        {
	        	jsonString = stringBuilder.toString();
	        }
    	}
    	catch(Exception e)
    	{
    		Log.e(DEBUG_TAG, "Converting Error " + e.toString());
    	}
		//Parse the String to JSON Object
		try
		{
			jsonObject = new JSONObject(jsonString.substring(jsonString.indexOf("{"), jsonString.lastIndexOf("}") + 1));
		}
		catch(JSONException e)
		{
			Log.e(DEBUG_TAG, "Error parsing data " + e.toString());
		}
		return jsonObject;
	}
	
	@Override
    protected void onPostExecute(JSONObject jsonObject) 
    {
		super.onPostExecute(jsonObject);
		Log.e(DEBUG_TAG, "json obj " + jsonObject);
		act.doHandleJson(jsonObject);
    }

}
