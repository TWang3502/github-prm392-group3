package com.example.prj2048;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Vaogame extends AppCompatActivity {
    Button play;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinhdau);
        play = findViewById(R.id.button);
        Button btnCenter = findViewById(R.id.btn_huong);
        btnCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHuongDan(Gravity.CENTER);

            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play_method();
            }
        });

    }
    private void play_method(){
        Intent i = new Intent(Vaogame.this, MainActivity.class);
        startActivity(i);
    }
    private void showHuongDan(int gravity){
         final Dialog dialog =new Dialog(this);
         dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
         dialog.setContentView(R.layout.layout_dialog);
         Window window=dialog.getWindow();
         if(window==null){
             return;
         }
         window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
         window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

         WindowManager.LayoutParams windowattibute=window.getAttributes();
         windowattibute.gravity= gravity;
         window.setAttributes(windowattibute);

         Button btnDaHieu=dialog.findViewById(R.id.btn_dahieu);
         btnDaHieu.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 dialog.dismiss();
             }
         });
         dialog.show();
    }

}

