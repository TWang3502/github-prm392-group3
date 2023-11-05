package com.example.project_game_prm392_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.project_game_prm392_v1.adapter.SolutionAdapter;
import com.example.project_game_prm392_v1.model.PlayGameModels;
import com.example.project_game_prm392_v1.object.Quiz;

import java.util.ArrayList;
import java.util.Random;

public class PlayActivity extends AppCompatActivity {
    int timeSuggestion = 0;
    PlayGameModels models;
    Quiz quiz;
    private String solution;
    ArrayList<String> arrAnswer;
    GridView gdvAnswer;
    ArrayList<String> arrSolution;
    GridView gdvSolution;
    ImageView img_quiz;
    TextView tv_playerCoin;
    TextView tv_namequiz;

    MediaPlayer win_sound;

    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        init();
        mapping();
        setOnClick();

        showQuiz();
    }

    private void mapping() {
        gdvAnswer = findViewById(R.id.gdvAnswer);
        gdvSolution = findViewById(R.id.gdvSolution);
        img_quiz = findViewById(R.id.img_quiz);
        tv_playerCoin = findViewById(R.id.tv_playerCoin);
        tv_namequiz = findViewById(R.id.tv_namequiz);
        win_sound = MediaPlayer.create(this,R.raw.googjob);
    }

    private void init() {
        models = new PlayGameModels(this);
        arrAnswer = new ArrayList<>();
        arrSolution = new ArrayList<>();


    }

    private void showQuiz() {
        timeSuggestion = 0;
        quiz = models.getQuiz();
        solution = quiz.getSolution();
        separateData();
        showAnswer();
        showSolution();

        tv_namequiz.setText(quiz.name);
        Glide.with(this)
                .load(quiz.image)
                .into(img_quiz);
        models.getInfo();
        tv_playerCoin.setText(String.valueOf(models.player.coin) + " $");
    }

    private void showAnswer() {
        gdvAnswer.setNumColumns(arrAnswer.size());
        gdvAnswer.setAdapter(new SolutionAdapter(this, 0, arrAnswer));
    }

    private void showSolution() {
        gdvSolution.setNumColumns(arrSolution.size() / 2);
        gdvSolution.setAdapter(new SolutionAdapter(this, 0, arrSolution));
    }

    private void setOnClick() {
        gdvSolution.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String s = (String) adapterView.getItemAtPosition(position);
                if (s.length() != 0 && index < arrAnswer.size()) {
                    for (int i = 0; i < arrAnswer.size(); i++) {
                        if (arrAnswer.get(i).length() == 0) {
                            index = i;
                            break;
                        }
                    }
                    arrSolution.set(position, "");
                    arrAnswer.set(index, s);
                    index++;
                    showAnswer();
                    showSolution();
                    checkWin();
                }
            }
        });

        gdvAnswer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String s = (String) adapterView.getItemAtPosition(position);
                if (s.length() != 0) {
                    index = position;
                    arrAnswer.set(position, "");
                    for (int i = 0; i < arrSolution.size(); i++) {
                        if (arrSolution.get(i).length() == 0) {
                            arrSolution.set(i, s);
                            break;
                        }
                    }
                    showAnswer();
                    showSolution();
                    checkWin();
                }
            }
        });
    }

    private void separateData() {
        index = 0;
        arrAnswer.clear();
        arrSolution.clear();
        Random r = new Random();
        for (int i = 0; i < solution.length(); i++) {
            arrAnswer.add("");
            String s = "" + (char) (r.nextInt(26) + 65);
            arrSolution.add(s);
            String s1 = "" + (char) (r.nextInt(26) + 65);
            arrSolution.add(s1);
        }
        for (int i = 0; i < solution.length(); i++) {
            String s = "" + solution.charAt(i);
            arrSolution.set(i, s.toUpperCase());
        }
        for (int i = 0; i < arrSolution.size(); i++) {
            String s = arrSolution.get(i);
            int vt = r.nextInt(arrSolution.size());
            arrSolution.set(i, arrSolution.get(vt));
            arrSolution.set(vt, s);
        }
    }

    private void checkWin() {
        String s = "";
        for (String s1 : arrAnswer) {
            s = s + s1;
        }

        s = s.toUpperCase();
        if (s.equals(solution.toUpperCase())) {
//            Toast.makeText(this, "Chuc mung ban da tra loi dung cau hoi nay!", Toast.LENGTH_SHORT).show();
            final Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_pass_quiz);

            Window window = dialog.getWindow();
            if (window == null) {
                return;
            }

            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            WindowManager.LayoutParams windowAttributes = window.getAttributes();
            windowAttributes.gravity = Gravity.CENTER;
            window.setAttributes(windowAttributes);

            win_sound.start();

            dialog.show();

            models.getInfo();
            models.player.coin = models.player.coin + 10;
            models.saveInfo();
            showQuiz();
        }
    }

    public void openSuggestion(View view) {
        if (timeSuggestion >= solution.length()) {
            return;
        }
        timeSuggestion++;
        models.getInfo();
        if (models.player.coin < 5) {
            Toast.makeText(this, "Ban khong du tien de mo goi y", Toast.LENGTH_SHORT).show();
            return;
        }

        String suggestion = "";
        char[] arrSolution = solution.toCharArray();
        for (int i = 0; i < timeSuggestion; i++) {
            if (i == 0) {
                suggestion += "Ky tu dau tien la: " + solution.toUpperCase().charAt(i) + "\n";
            } else {
                suggestion += "Ky tu thu " + (i+1) + " la: " + solution.toUpperCase().charAt(i) + "\n";
            }
        }

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Goi y:");
        alertDialog.setMessage(suggestion);
        alertDialog.show();

        models.getInfo();
        models.player.coin = models.player.coin - 5;
        models.saveInfo();
        tv_playerCoin.setText(String.valueOf(models.player.coin) + " $");

    }


    public void changeQuestion(View view) {
        models.getInfo();
        if (models.player.coin < 10) {
            Toast.makeText(this, "Ban khong du tien de doi cau hoi", Toast.LENGTH_SHORT).show();
            return;
        }
        models.player.coin = models.player.coin - 10;
        models.saveInfo();
        tv_playerCoin.setText(String.valueOf(models.player.coin) + " $");
        Toast.makeText(this, "Ban da doi cau hoi thanh cong", Toast.LENGTH_SHORT).show();
        showQuiz();
    }
}