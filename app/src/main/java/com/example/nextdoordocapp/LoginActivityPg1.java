package com.example.nextdoordocapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
                StringBuilder id = new StringBuilder();
                StringBuilder name =new StringBuilder();
                boolean valEmailPassword = databaseHelper.valEmailPassword(emailID, pass);
                StringBuilder role = new StringBuilder();
                if (valEmailPassword) {

//                    Toast.makeText(LoginActivityPg1.this, "Login successful", Toast.LENGTH_LONG).show();
                    Cursor cursor = databaseHelper.roleLoginTableExists(emailID, pass);
                    if (cursor.getCount() > 0) {
                        while (cursor.moveToNext())
                            role.append(cursor.getString(0));

                    }

                    Cursor cID;
                    Cursor cName;
                    if ("patient".equals(role.toString())) {
                        //gets id from patient table
                        cID = databaseHelper.getIDPatient(emailID, pass);
                        cName = databaseHelper.getNamePatient(emailID, pass);
                        if (cID.getCount() > 0 && cName.getCount() > 0) {
                            while (cID.moveToNext()) {
                                id.append(cID.getString(0));
                            }
                            while(cName.moveToNext()){
                                name.append(cName.getString(0));
                            }
                            //creates new intent based on the patient id received
                            Toast.makeText(LoginActivityPg1.this, "Hello  "+name+ "!", Toast.LENGTH_LONG).show();
                            Intent loginNavigation = new Intent(LoginActivityPg1.this, PatientMainActions.class);
                            loginNavigation.putExtra("patientId", id.toString());
                            startActivity(loginNavigation);
                        }
                    } else if ("doctor".equals(role.toString())) {

//                        gets the doctor id
                        cID = databaseHelper.getIDDoctor(emailID, pass);
                        cName = databaseHelper.getNameDoctor(emailID, pass);
                        if (cID.getCount() > 0 && cName.getCount() > 0) {
                            while (cID.moveToNext()) {
                                id.append(cID.getString(0));
                            }
                            while(cName.moveToNext()){
                                name.append(cName.getString(0));
                            }
                            //creates new intent based on the doctor id received
                            Toast.makeText(LoginActivityPg1.this, "Hello  "+name+ "!", Toast.LENGTH_LONG).show();
                            Intent loginNavigation = new Intent(LoginActivityPg1.this, DoctorHelloPg1.class);
                            loginNavigation.putExtra("docID", id.toString());
                            startActivity(loginNavigation);
                        }
                    } else if ("cashier".equals(role.toString())) {
//                        gets the cashier id
                        cID = databaseHelper.getIDCashier(emailID, pass);
                        cName = databaseHelper.getNameCashier(emailID, pass);
                        if (cID.getCount() > 0 && cName.getCount() > 0) {
                            while (cID.moveToNext()) {
                                id.append(cID.getString(0));
                            }
                            while(cName.moveToNext()){
                                name.append(cName.getString(0));
                            }
                            //creates new intent based on the cashier id received
                            Toast.makeText(LoginActivityPg1.this, "Hello  "+name+ "!", Toast.LENGTH_LONG).show();
                            Intent loginNavigation = new Intent(LoginActivityPg1.this, CashierHomePg1.class);
                            loginNavigation.putExtra("casID", id.toString());
                            startActivity(loginNavigation);
                        }
                    } else if ("admin".equals(role.toString())) {
//                        gets the Admin id
                        cID = databaseHelper.getIDAdmin(emailID, pass);
                        cName = databaseHelper.getNameAdmin(emailID, pass);
                        if (cID.getCount() > 0 && cName.getCount() > 0) {
                            while (cID.moveToNext()) {
                                id.append(cID.getString(0));
                            }
                            while(cName.moveToNext()){
                                name.append(cName.getString(0));
                            }
                            //creates new intent based on the admin id received
                            Toast.makeText(LoginActivityPg1.this, "Hello  "+name+ "!", Toast.LENGTH_LONG).show();
                            Intent loginNavigation = new Intent(LoginActivityPg1.this, AdmWelcomePage.class);
                            loginNavigation.putExtra("casID", id.toString());
                            startActivity(loginNavigation);
                        }
                    } else
                        Toast.makeText(LoginActivityPg1.this, "Role doesn't exists", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(LoginActivityPg1.this, "Wrong EmailId or password or user doesn't exist ", Toast.LENGTH_SHORT).show();
                }


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

