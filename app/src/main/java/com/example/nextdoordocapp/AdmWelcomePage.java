package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AdmWelcomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_welc_page);

        Button btnAdmMaintenance = (Button) findViewById(R.id.btnAdmMaintenance);
        Button btnAdmUpdateDetails= findViewById(R.id.btnAdmUpdateDetails);;
        Button btnAdmReg = (Button) findViewById(R.id.btnAdmReg);
        FloatingActionButton logout = findViewById(R.id.logoutAdmin);
        btnAdmReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdmWelcomePage.this,AdminRegistrationModule.class));

            }
        });


        btnAdmUpdateDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdmWelcomePage.this, AdminUpdatesFPage.class));

            }
        });

        btnAdmMaintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
startActivity(new Intent(AdmWelcomePage.this,AdmApplicationMaintenance.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdmWelcomePage.this,SplashActivity.class));
            }
        });


    }
}