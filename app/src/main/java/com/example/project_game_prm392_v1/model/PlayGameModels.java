package com.example.project_game_prm392_v1.model;

import com.example.project_game_prm392_v1.DATA;
import com.example.project_game_prm392_v1.PlayActivity;
import com.example.project_game_prm392_v1.object.Player;
import com.example.project_game_prm392_v1.object.Quiz;

import java.util.ArrayList;

public class PlayGameModels {
    PlayActivity c;
    ArrayList<Quiz> arr;
    public Player player;

    public PlayGameModels(PlayActivity c) {
        this.c = c;
        player = new Player();
        createData();
        getInfo();
//        player.quizth = 0;
        player.quizth = player.quizth - 1;
        saveInfo();
    }

    private void createData() {
        arr = new ArrayList<>(DATA.getData().arrQuiz);
    }

    public Quiz getQuiz() {
        getInfo();
//        index = player.quizth;
//        index++;
//        player.quizth = index;
        player.quizth++;
        saveInfo();
//        if (index >= arr.size()) {
//            index = arr.size() - 1;
//        }
//        return arr.get(index);
        if (player.quizth >= arr.size()) {
            player.quizth = arr.size() - 1;
        }
        return arr.get(player.quizth);
    }

    public void getInfo() {
        player.getInfo(c);
    }

    public void saveInfo() {
        player.saveInfo(c);
    }

}
