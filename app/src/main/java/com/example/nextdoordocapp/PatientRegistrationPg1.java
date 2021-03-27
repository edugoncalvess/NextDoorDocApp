package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PatientRegistrationPg1 extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_registration_pg1);

        EditText txtRgstPgEmail = findViewById(R.id.txtRgstPgEmail);
        EditText txtRgstPgPassword = findViewById(R.id.txtRgstPgPassword);

        Button btnRgstPgCreateProfile = findViewById(R.id.btnRgstPgCreateProfile);
        databaseHelper = new DatabaseHelper(this);
        btnRgstPgCreateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//              Creating SignUp Modules

                String email, password;
                email = txtRgstPgEmail.getText().toString();
                password = txtRgstPgPassword.getText().toString();

                if (email.equals("") || password.equals("")) {
                    Toast.makeText(PatientRegistrationPg1.this, "Necessary fields are empty",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Boolean chkEmail = databaseHelper.valEmail(email);
                    if (chkEmail == true) {
                        Boolean i = databaseHelper.insert(email, password);
                        if (i == true) {
                            Toast.makeText(PatientRegistrationPg1.this, "Registration was successful",
                                    Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(PatientRegistrationPg1.this, "Email ID exists in the database",
                                Toast.LENGTH_LONG).show();
                    }

                }


            }
        });

    }
}