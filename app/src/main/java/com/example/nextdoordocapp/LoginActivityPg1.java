package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivityPg1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        TextView txtLoginUsername = findViewById(R.id.txtLoginUsername);
        TextView txtLoginPassword = findViewById(R.id.txtLoginPassword);
        TextView txtLoginActvSignUp = findViewById(R.id.txtLoginActvSignUp);
        Button btnLoginActLogin = findViewById(R.id.btnLoginActLogin);
        Button btnContactOurSupport = findViewById(R.id.btnContactOurSupport);


        btnLoginActLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                /*
                if(!txtLoginUsername.equals("")){
                    if (!txtLoginPassword.equals("")){
                        // check if the login and password match.
                        // If so, check Patient, Doctor, Admin, and Cashier tables and "intend" to their respective homepage


                        // check if the password matches

                    }
                    else {
                        Toast.makeText(LoginActivityPg1.this,"Please insert Password",Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(LoginActivityPg1.this,"Please insert Username",Toast.LENGTH_LONG).show();
                } */
            }
        });

        txtLoginActvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to PatientRegistrationPg1
                startActivity(new Intent(LoginActivityPg1.this,PatientRegistrationPg1.class));
            }
        });

        btnContactOurSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivityPg1.this,ContactSupportPg1.class));
            }
        });














    }
}