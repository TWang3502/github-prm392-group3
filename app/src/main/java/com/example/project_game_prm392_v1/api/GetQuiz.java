package com.example.project_game_prm392_v1.api;

import android.os.AsyncTask;

import com.example.project_game_prm392_v1.DATA;
import com.example.project_game_prm392_v1.object.Quiz;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class GetQuiz extends AsyncTask<Void, Void, Void> {
    String data;

    public GetQuiz(){

    }
    //http://192.168.0.2/duoihinhbatchu/GetQuiz.php

    //http://192.168.137.1/duoihinhbatchu/GetQuiz.php
    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://192.168.1.15/duoihinhbatchu/GetQuiz.php")
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            ResponseBody responseBody = response.body();
            data = responseBody.string();
        }catch (IOException e){
            data = null;
            e.printStackTrace();
        }
        return null;

    }

    @Override
    protected void onPostExecute(Void unused) {
        if(data != null){
            try {
                DATA.getData().arrQuiz.clear();
                JSONArray array = new JSONArray(data);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject o = array.getJSONObject(i);
                    Quiz quiz = new Quiz();
                    quiz.image = o.getString("image");
                    quiz.name = o.getString("name");
                    quiz.solution = o.getString("solution");
                    DATA.getData().arrQuiz.add(quiz);
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }else {

        }
    }

}
