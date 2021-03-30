package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AdminUpdatingDoctor extends AppCompatActivity {
    String email, fName, lName, password, phone, address, city, pCode;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_updating_doctor);

        databaseHelper = new DatabaseHelper(this);
        Button btnUpdate = findViewById(R.id.btnUpdateDoctor);
        EditText txtEmail = findViewById(R.id.txtUpdateDocEmail);
        EditText txtFName = findViewById(R.id.txtUpdateDocFirstName);
        EditText txtLName = findViewById(R.id.txtUpdateDocFamilyName);
        EditText txtPassword = findViewById(R.id.txtUpdateDocPassword);
        EditText txtPhone = findViewById(R.id.txtUpdateDocPhone);
        EditText txtAddress = findViewById(R.id.txtUpdateDocAddress);
        EditText txtCity = findViewById(R.id.txtUpdateDocCity);
        EditText txtPCode = findViewById(R.id.txtUpdateDocPCode);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = txtEmail.getText().toString();
                fName = txtFName.getText().toString();
                lName = txtLName.getText().toString();
                password = txtPassword.getText().toString();
                phone = txtPhone.getText().toString();
                address = txtAddress.getText().toString();
                city = txtCity.getText().toString();
                pCode = txtPCode.getText().toString();

                boolean login = databaseHelper.updatePasswordLogin(email,password);
                boolean update = databaseHelper.updateDoctorInformation(email, fName, lName,password,pCode,phone,address,city);
                if (update && login) {
//                    Toast.makeText(updatePat.this, n + "  " + e + "  " + l + "updated", Toast.LENGTH_SHORT).show();
                    Toast.makeText(AdminUpdatingDoctor.this, "Updated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AdminUpdatingDoctor.this, AdmWelcomePage.class));
                } else {
                    Toast.makeText(AdminUpdatingDoctor.this, "rejected", Toast.LENGTH_SHORT).show();
//
                }

            }
        });
    }
}