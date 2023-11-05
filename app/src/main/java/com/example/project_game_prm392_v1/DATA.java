package com.example.project_game_prm392_v1;

import com.example.project_game_prm392_v1.object.Quiz;

import java.util.ArrayList;

public class DATA {
    public static DATA data;

    static {
        data = new DATA();
    }

    public static DATA getData() {
        return data;
    }

    public ArrayList<Quiz> arrQuiz = new ArrayList<>();


}
