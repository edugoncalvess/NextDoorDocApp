package com.example.nextdoordocapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminUpdatingPatient extends AppCompatActivity {
    String email, fName, lName, password, phone, dateOB,
            height, weight, allergies, disease, medicine, insNum,
            address, city, state, country, pCode, gender;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_updating_patient);

        databaseHelper = new DatabaseHelper(this);
        Button btnUpdate = findViewById(R.id.btnUpdatePatient);
        EditText txtEmail = findViewById(R.id.txtOldEmail);
        EditText txtFName = findViewById(R.id.txtUpdatePgFirstName);
        EditText txtLName = findViewById(R.id.txtUpdatePgFamilyName);
        EditText txtPassword = findViewById(R.id.txtUpdatePgPassword);
        EditText txtPhone = findViewById(R.id.txtUpdatePgPhone);
        EditText txtDOB = findViewById(R.id.txtUpdatePgDOB);
        EditText txtHt = findViewById(R.id.txtUpdatePgHeight);
        EditText txtWt = findViewById(R.id.txtUpdatePgWeight);
        Spinner spnGender = findViewById(R.id.sprUpdateGender);
        EditText txtAllergies = findViewById(R.id.txtUpdatePgAllergies);
        EditText txtDiseases = findViewById(R.id.txtUpdatePgDisease);
        EditText txtMedicine = findViewById(R.id.txtUpdatePgMedicine);
        EditText txtInsurance = findViewById(R.id.txtUpdatePgInsuranceNumber);
        EditText txtAddress = findViewById(R.id.txtUpdatePgStreet);
        EditText txtCity = findViewById(R.id.txtUpdatePgCity);
        EditText txtState = findViewById(R.id.txtUpdatePgState);
        EditText txtCountry = findViewById(R.id.txtUpdatePgCountry);
        EditText txtPCode = findViewById(R.id.txtUpdatePgPCode);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = txtEmail.getText().toString();
                fName = txtFName.getText().toString();
                lName = txtLName.getText().toString();
                password = txtPassword.getText().toString();
                phone = txtPhone.getText().toString();
                dateOB = txtDOB.getText().toString();
                height = txtHt.getText().toString();
                weight = txtWt.getText().toString();
                allergies = txtAllergies.getText().toString();
                disease = txtDiseases.getText().toString();
                medicine = txtMedicine.getText().toString();
                insNum = txtInsurance.getText().toString();
                address = txtAddress.getText().toString();
                city = txtCity.getText().toString();
                state = txtState.getText().toString();
                country = txtCountry.getText().toString();
                pCode = txtPCode.getText().toString();
                gender = spnGender.getSelectedItem().toString();

                boolean login = databaseHelper.updatePasswordLogin(email,password);
                boolean update = databaseHelper.updatePatientInformation(email, fName, lName, dateOB,
                        gender, height, weight, phone, country, state, city, address, pCode, password, insNum, disease, allergies, medicine);
                if (update && login) {
//                    Toast.makeText(updatePat.this, n + "  " + e + "  " + l + "updated", Toast.LENGTH_SHORT).show();
                    Toast.makeText(AdminUpdatingPatient.this, "Updated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AdminUpdatingPatient.this, AdmWelcomePage.class));
                } else {
                    Toast.makeText(AdminUpdatingPatient.this, "rejected", Toast.LENGTH_SHORT).show();
//
                }
            }


        });

    }

}