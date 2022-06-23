package com.example.chatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LoadingPage extends AppCompatActivity {

    private final int SPLASH_DISPLAY_TIMER = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_page);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(LoadingPage.this,IntroPage.class);
                LoadingPage.this.startActivity(mainIntent);
                LoadingPage.this.finish();
            }
        },SPLASH_DISPLAY_TIMER);
    }
}