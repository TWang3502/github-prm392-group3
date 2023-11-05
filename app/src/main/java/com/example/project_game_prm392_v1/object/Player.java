package com.example.project_game_prm392_v1.object;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class Player {

    private String nameData = "appData";
    public int coin;
    public int quizth;


    public void saveInfo(Context ct){
        SharedPreferences settings = ct.getSharedPreferences(nameData, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("coin", coin);
        editor.putInt("quizth", quizth);
        editor.commit();
    }

    public void getInfo(Context ct) {
        SharedPreferences settings = ct.getSharedPreferences(nameData, 0);
        coin = settings.getInt("coin", 20);
        quizth = settings.getInt("quizth", -1);
        Log.e("TQ", String.valueOf(quizth));
    }
}
