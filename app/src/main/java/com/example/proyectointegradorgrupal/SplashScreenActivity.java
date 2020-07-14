package com.example.proyectointegradorgrupal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.Toast;

import com.example.proyectointegradorgrupal.controller.LyricsController;
import com.example.proyectointegradorgrupal.model.Body;
import com.example.proyectointegradorgrupal.model.Example;
import com.example.proyectointegradorgrupal.model.Message;
import com.example.proyectointegradorgrupal.util.ResultListener;
import com.example.proyectointegradorgrupal.view.MainActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private LyricsController lyricsController;
    private Example example;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);

/*        LyricsController lyricsController = new LyricsController();
        lyricsController.getLyrics("Californication", "Red Hot Chilli Peppers", new ResultListener<Example>() {
            @Override
            public void onFinish(Example result) {
               

            }
        });*/
    }


}





