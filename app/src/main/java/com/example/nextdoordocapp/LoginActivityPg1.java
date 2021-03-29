package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivityPg1 extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this);

        TextView txtLoginUsername = findViewById(R.id.txtLoginUsername);
        TextView txtLoginPassword = findViewById(R.id.txtLoginPassword);
        TextView txtLoginActvSignUp = findViewById(R.id.txtLoginActvSignUp);
        Button btnLoginActLogin = findViewById(R.id.btnLoginActLogin);
        Button btnContactOurSupport = findViewById(R.id.btnContactOurSupport);


        btnLoginActLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailID = txtLoginUsername.getText().toString();
                String pass = txtLoginPassword.getText().toString();
//                StringBuilder id;
                Boolean valEmailPassword = databaseHelper.valEmailPassword(emailID, pass);
                StringBuilder role = new StringBuilder();
                if (valEmailPassword) {

//                    Toast.makeText(LoginActivityPg1.this, "Login successful", Toast.LENGTH_LONG).show();
                    Cursor cursor = databaseHelper.roleLoginTableExists(emailID, pass);
                    if (cursor.getCount() > 0) {
                        while (cursor.moveToNext())
                            role.append(cursor.getString(0));

                    }
                    Toast.makeText(LoginActivityPg1.this, role, Toast.LENGTH_SHORT).show();
                    Cursor cID;
                    if ("patient".equals(role.toString())) {
                        Toast.makeText(LoginActivityPg1.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        StringBuilder id = new StringBuilder();
                        cID = databaseHelper.getIDPatient(emailID, pass);
                        if (cID.getCount() > 0)
                            while (cID.moveToNext()) {

                                id.append(cID.getString(0));

                            }


                        Toast.makeText(LoginActivityPg1.this, id, Toast.LENGTH_SHORT).show();
                        Intent loginNavigation = new Intent(LoginActivityPg1.this, PatientMainActions.class);
                        loginNavigation.putExtra("patientId", id.toString());
                        startActivity(loginNavigation);
                    }
                } else if ("doctor".equals(role.toString())) {
                    Toast.makeText(LoginActivityPg1.this, "Hi D", Toast.LENGTH_SHORT).show();
                } else if ("cashier".equals(role.toString())) {
                    Toast.makeText(LoginActivityPg1.this, "Hi C", Toast.LENGTH_SHORT).show();
                } else if ("admin".equals(role.toString())) {
                    Toast.makeText(LoginActivityPg1.this, "Hi A", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(LoginActivityPg1.this, "Hi null", Toast.LENGTH_SHORT).show();


            }


        });

        txtLoginActvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to PatientRegistrationPg1
                startActivity(new Intent(LoginActivityPg1.this, PatientRegistrationPg1.class));
            }
        });

        btnContactOurSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivityPg1.this, ContactSupportPg1.class));
            }
        });


    }
}

