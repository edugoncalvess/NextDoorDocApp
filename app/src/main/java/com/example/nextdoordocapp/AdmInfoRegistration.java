package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdmInfoRegistration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_info_registration);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        EditText txtAdmFName = findViewById(R.id.txtRgstAdmFirstName);
        EditText txtAdmLName = findViewById(R.id.txtRgstAdmFamilyName);
        EditText txtAdmEmail = findViewById(R.id.txtRgstAdmEmail);
        EditText txtAdmPassword = findViewById(R.id.txtRgstAdmPassword);
        EditText txtAdmPhone = findViewById(R.id.txtRgstAdmPhone);
        EditText txtAdmAddress = findViewById(R.id.txtRgstAdmAddress);
        EditText txtAdmSIN = findViewById(R.id.txtRgstAdmSIN);
        EditText txtAdmDOB = findViewById(R.id.txtRgstAdmDOB);
        String role = "admin";

        Button createAdmProf = findViewById(R.id.btnRgstAdmCreate);

        createAdmProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aEmail, aFName, aLName, aPassword, aSIN, aPhone, aAddress, aDOB;

                aEmail = txtAdmEmail.getText().toString();
                aFName = txtAdmFName.getText().toString();
                aLName = txtAdmLName.getText().toString();
                aPassword = txtAdmPassword.getText().toString();
                aSIN = txtAdmSIN.getText().toString();
                aPhone = txtAdmPhone.getText().toString();
                aAddress = txtAdmAddress.getText().toString();
                aDOB = txtAdmDOB.getText().toString();

                Boolean chkEmail = databaseHelper.valEmail(aEmail);
                if (chkEmail == true) {
                    Boolean i = databaseHelper.insert(aEmail, aPassword,role);
                    if (i == true) {
                        databaseHelper.addAdminRecords(aEmail, aFName, aLName,
                                aPassword, aSIN, aPhone, aAddress, aDOB);
                        Toast.makeText(AdmInfoRegistration.this, "Profile Created", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(AdmInfoRegistration.this, MainActivity.class));
                    }
                } else {
                    Toast.makeText(AdmInfoRegistration.this, "Email ID already exist in the database", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
