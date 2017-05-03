package com.example.ya.Task;

import android.os.AsyncTask;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class MyTask extends AsyncTask<Void,Void,Map<String,String>> {
    private final static String KEY = "trnsl.1.1.20170425T181936Z.b193666944ee4eaa.9594841bf13a2777a5f254b1c6cacf4482c34595";
    private final static String URLGETLANG = "https://translate.yandex.net/api/v1.5/tr.json/getLangs?ui=en&key=";

    @Override
    protected Map<String,String> doInBackground(Void... params) {
        JSONObject jsonObject=null;
        Map<String,String> map=null;
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(URLGETLANG+KEY);
        httpGet.addHeader("Accept","application/json");
        httpGet.addHeader("Content-Type: text/html", "charset=utf-8");
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            InputStream is = httpEntity.getContent();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            is.close();
            String json = sb.toString();
            try {
                jsonObject = new JSONObject(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
           jsonObject  =jsonObject.getJSONObject("langs");
            JSONArray jsonNames=jsonObject.names();
             map = new HashMap<>();
            System.err.println(jsonNames.length());
            for (int i=0;i<jsonNames.length();i++) {

                map.put(jsonObject.get(jsonNames.get(i).toString()).toString(),jsonNames.get(i).toString());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return map;
    }
}
