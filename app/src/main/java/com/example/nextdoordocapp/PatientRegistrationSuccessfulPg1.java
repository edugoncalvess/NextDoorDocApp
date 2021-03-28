package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PatientRegistrationSuccessfulPg1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_registration_successful_pg1);

        String welcomeMessage;

        TextView txtPatRegSuccess = findViewById(R.id.txtPatRegSuccess);
        Button btnRgstPgCBackToLogin = findViewById(R.id.btnRgstPgCBackToLogin);


        welcomeMessage = "Welcome. Your profile was successful registered";

        txtPatRegSuccess.setText(welcomeMessage);

        btnRgstPgCBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientRegistrationSuccessfulPg1.this,LoginActivityPg1.class));
            }
        });

    }
}