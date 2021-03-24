package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnPatAllPages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPatAllPages = findViewById(R.id.btnPatAllPages);
        btnPatAllPages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PatientMainActions.class));
            }
        });

        Button btnDocAllPages = findViewById(R.id.btnDocAllPages);
        btnDocAllPages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,DoctorHelloPg1.class));
            }
        });

        Button btnAdmWel    = findViewById(R.id.btnAdminAllPages);
        btnAdmWel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AdmWelcPage.class));
            }
        });

        Button btnCashierPages = findViewById(R.id.btnCashierPages);
        btnCashierPages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CashierHomePg1.class));
            }
        });

        Button btnLoginPage = findViewById(R.id.btnLoginPage);
        btnLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoginActivityPg1.class));
            }
        });

        Button btnSplashPage = findViewById(R.id.btnSplashPage);
        btnSplashPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SplashActivity.class));
            }
        });


    }
}