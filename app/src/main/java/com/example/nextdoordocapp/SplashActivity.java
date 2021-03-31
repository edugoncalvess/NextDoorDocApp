package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    int i ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);


        /* NOTE: The picture used in the Splash Activity is free and the source is cited below:
         * https://unsplash.com/photos/NFvdKIhxYlU
         * */


        if (i < 1) {

            DatabaseHelper db = new DatabaseHelper(this);
            boolean l = db.adAdminLogin();
            boolean r = db.adAdminTable();

            if (l & r)
                Log.d("inserted", "yes");
            else
                Log.d("inserted", "no");

        }
        i++;
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                finish();
                startActivity(new Intent(SplashActivity.this, LoginActivityPg1.class));
            }
        };

        Timer opening = new Timer();
        opening.schedule(task, 5000);
    }
}