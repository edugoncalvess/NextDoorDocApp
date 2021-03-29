package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdmCasInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_cas_information);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        EditText txtCasFName = findViewById(R.id.txtRgstCasFirstName);
        EditText txtCasLName = findViewById(R.id.txtRgstCasFamilyName);
        EditText txtCasEmail = findViewById(R.id.txtRgstCasEmail);
        EditText txtCasPassword = findViewById(R.id.txtRgstCasPassword);
        EditText txtCasPhone = findViewById(R.id.txtRgstCasPhone);
        EditText txtCasAddress = findViewById(R.id.txtRgstCasAddress);
        EditText txtCasSIN = findViewById(R.id.txtRgstCasSIN);
        EditText txtCasDOB = findViewById(R.id.txtRgstCasDOB);
        String role = "cashier";

        Button createCasProf = findViewById(R.id.btnRgstCasCreate);

        createCasProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cEmail, cFName, cLName, cPassword, cSIN, cPhone, cAddress, cDOB;

                cEmail = txtCasEmail.getText().toString();
                cFName = txtCasFName.getText().toString();
                cLName = txtCasLName.getText().toString();
                cPassword = txtCasPassword.getText().toString();
                cSIN = txtCasSIN.getText().toString();
                cPhone = txtCasPhone.getText().toString();
                cAddress = txtCasAddress.getText().toString();
                cDOB = txtCasDOB.getText().toString();

                Boolean chkEmail = databaseHelper.valEmail(cEmail);
                if (chkEmail == true) {
                    Boolean i = databaseHelper.insert(cEmail, cPassword,role);
                    if (i == true) {
                        databaseHelper.addCashierRecords(cEmail, cFName, cLName,
                                cPassword, cSIN, cPhone, cAddress, cDOB);
                        Toast.makeText(AdmCasInformation.this, "Profile Created", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(AdmCasInformation.this,MainActivity.class));
                    }
                }else {
                    Toast.makeText(AdmCasInformation.this, "Email ID already exist in the database", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}