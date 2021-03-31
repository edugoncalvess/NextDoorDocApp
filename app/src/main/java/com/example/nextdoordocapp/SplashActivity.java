package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        /* NOTE: The picture used in the Splash Activity is free and the source is cited below:
        * https://unsplash.com/photos/NFvdKIhxYlU
        * */
DatabaseHelper db= new DatabaseHelper(this);
boolean l = db.adAdminLogin();

        TimerTask task = new TimerTask(){

            @Override
            public void run() {
                finish();
                startActivity(new Intent(SplashActivity.this,LoginActivityPg1.class));
            }
        } ;

        Timer opening = new Timer();
        opening.schedule(task,5000);
    }
}