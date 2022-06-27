package com.example.my;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {
    Handler handler;
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        try{
            this.getSupportActionBar().hide();
        }
        catch(NullPointerException e){}

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreen.this,MainActivity.class);
                startActivity(intent);

            }
        }, 3000);

        


    }
}