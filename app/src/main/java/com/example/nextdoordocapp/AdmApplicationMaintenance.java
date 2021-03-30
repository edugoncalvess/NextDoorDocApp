package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdmApplicationMaintenance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_application_maintenance);

        Button btnReset = findViewById(R.id.btnResetPassword);
//        Button btnDelete = findViewById(R.id.btnDeleteUser);
        Button btnContactSupport = findViewById(R.id.btnContactSupport);
        Button btnHome = findViewById(R.id.btnHome);


        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdmApplicationMaintenance.this, AdminResetsDeletesUser.class));
            }
        });


        btnContactSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdmApplicationMaintenance.this, ContactSupportPg1.class));
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdmApplicationMaintenance.this, AdmWelcomePage.class));
            }
        });
    }
}