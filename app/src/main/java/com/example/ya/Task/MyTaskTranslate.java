package com.example.ya.Task;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Сергей on 26.04.2017.
 */
public class MyTaskTranslate extends AsyncTask<String, Void, String> {
    private final static String KEY = "&key=trnsl.1.1.20170425T181936Z.b193666944ee4eaa.9594841bf13a2777a5f254b1c6cacf4482c34595";
    private final static String URLGETLANG = "https://translate.yandex.net/api/v1.5/tr.json/translate?lang=";


    @Override
    protected String doInBackground(String... params) {
        String pairLanguages = params[0] + "-" + params[1];
        List<NameValuePair> nameValuePairList = new ArrayList<>();
        if(params[2].trim().equals("")){
            return null;
        }
        nameValuePairList.add(new BasicNameValuePair("text", params[2]));
        StringBuilder outTranslate = null;
        String out=null;
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(URLGETLANG + pairLanguages + KEY);
        //httpPost.addHeader("Accept", "application/json");
        //   httpPost.addHeader("Content-Type: application/x-www-form-urlencoded", "charset=utf-8");
        //   httpPost.addHeader("Accept-Charset: windows-1251,utf-8");
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairList, HTTP.UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            InputStream is = httpEntity.getContent();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is), 8);
            outTranslate = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                outTranslate.append(line);
            }
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            JSONObject jsonObject = new JSONObject(String.valueOf(outTranslate));
            out = jsonObject.getString("text");

                out =out.substring(2, out.length() - 2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return out;
    }
}
