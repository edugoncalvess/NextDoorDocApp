package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdmDocInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_doc_registration);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        EditText txtDocFName = findViewById(R.id.txtRgstDocFirstName);
        EditText txtDocLName = findViewById(R.id.txtRgstDocFamilyName);
        EditText txtDocEmail = findViewById(R.id.txtRgstDocEmail);
        EditText txtDocPassword = findViewById(R.id.txtRgstDocPassword);
        EditText txtDocPhone = findViewById(R.id.txtRgstDocPhone);
        EditText txtDocAddress = findViewById(R.id.txtRgstDocAddress);
        EditText txtDocCity = findViewById(R.id.txtRgstDocCity);
        EditText txtDocPCode = findViewById(R.id.txtRgstDocPCode);
        String role = "doctor";

        Button createDocProf = findViewById(R.id.btnRgstCasCreate);

        createDocProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dEmail, dFName, dLName, dPassword, dPCode, dPhone, dAddress, dCity;

                dEmail = txtDocEmail.getText().toString();
                dFName = txtDocFName.getText().toString();
                dLName = txtDocLName.getText().toString();
                dPassword = txtDocPassword.getText().toString();
                dPCode = txtDocPCode.getText().toString();
                dPhone = txtDocPhone.getText().toString();
                dAddress = txtDocAddress.getText().toString();
                dCity = txtDocCity.getText().toString();

                Boolean chkEmail = databaseHelper.valEmail(dEmail);
                if (chkEmail == true) {
                    Boolean i = databaseHelper.insert(dEmail, dPassword,role);
                    if (i == true) {
                        databaseHelper.addDoctorRecords(dEmail, dFName, dLName,
                                dPassword, dPCode, dPhone, dAddress, dCity);
                        Toast.makeText(AdmDocInformation.this, "Profile Created", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(AdmDocInformation.this,MainActivity.class));
                    }
                }else {
                    Toast.makeText(AdmDocInformation.this, "Email ID already exist in the database", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}