package com.example.nextdoordocapp;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminUpdatingPatient extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    Button btnUpdateDetails;
    //    Button btnUpdateDetails;
//    EditText txtEmailUpdate;

    String emailUpdateSearch;

    EditText patientFirstName;
    EditText patientLastName;
    //    EditText patientEmail;
    EditText patientPassword;
    EditText patientPhone;
    EditText patientDOB;
    EditText patientHeight;
    EditText patientWeight;
    EditText patientAllergies;
    EditText patientDisease;
    EditText patientMedicine;
    EditText patientGender;
    EditText patientInsuranceNumber;
    EditText patientStreet;
    EditText patientCity;
    EditText patientState;
    EditText patientCountry;
    EditText patientPostalCode;
    AdminUpdatesDetails adminPatientUpdate = new AdminUpdatesDetails();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_updating_patient);


        btnUpdateDetails = findViewById(R.id.btnUpdatePatient);
//        txtEmailUpdate = findViewById(R.id.txtEmailUpdate);

        emailUpdateSearch = adminPatientUpdate.emailUpdateSearch;

        patientFirstName = findViewById(R.id.txtUpdatePgFirstName);
        patientLastName = findViewById(R.id.txtUpdatePgFamilyName);
//        patientEmail = findViewById(R.id.txtUpdatePgEmail);
        patientPassword = findViewById(R.id.txtUpdatePgPassword);
        patientPhone = findViewById(R.id.txtUpdatePgPhone);
        patientDOB = findViewById(R.id.txtUpdatePgDOB);
        patientHeight = findViewById(R.id.txtUpdatePgHeight);
        patientWeight = findViewById(R.id.txtUpdatePgWeight);
        patientAllergies = findViewById(R.id.txtUpdatePgAllergies);
        patientDisease = findViewById(R.id.txtUpdatePgDisease);
        patientMedicine = findViewById(R.id.txtUpdatePgMedicine);
        patientInsuranceNumber = findViewById(R.id.txtUpdatePgInsuranceNumber);
        patientStreet = findViewById(R.id.txtUpdatePgStreet);
        patientCity = findViewById(R.id.txtUpdatePgCity);
        patientState = findViewById(R.id.txtUpdatePgState);
        patientCountry = findViewById(R.id.txtUpdatePgCountry);
        patientPostalCode = findViewById(R.id.txtUpdatePgPCode);

        btnUpdateDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminUpdatingPatient.this, "accepted", Toast.LENGTH_SHORT).show();
//                updateInformation(v);
            }


        });
    }

    public void updateInformation(View view) {
        databaseHelper = new DatabaseHelper(getApplicationContext());
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        String pFName, pLName, pBD, pGender,
                pHeight, pWeight, pPhone, pCountry, pState,
                pCity, pStreet, pPostalCode, pPassword,
                pInsuranceNumber, pDiseaseName, pAllergyName,
                pMedicineName;
        String old_email = adminPatientUpdate.emailUpdateSearch;

        pFName = patientFirstName.getText().toString();
        pLName = patientLastName.getText().toString();
        pBD = patientDOB.getText().toString();
        pGender = patientGender.getText().toString();
        pHeight = patientHeight.getText().toString();
        pWeight = patientWeight.getText().toString();
        pPhone = patientPhone.getText().toString();
        pCountry = patientCountry.getText().toString();
        pState = patientState.getText().toString();
        pCity = patientCity.getText().toString();
        pStreet = patientStreet.getText().toString();
        pPostalCode = patientPostalCode.getText().toString();
        pPassword = patientPassword.getText().toString();
        pInsuranceNumber = patientInsuranceNumber.getText().toString();
        pDiseaseName = patientDisease.getText().toString();
        pAllergyName = patientAllergies.getText().toString();
        pMedicineName = patientMedicine.getText().toString();

        databaseHelper.updatePatientInformation(old_email, pFName, pLName, pBD, pGender,
                pHeight, pWeight, pPhone, pCountry, pState,
                pCity, pStreet, pPostalCode, pPassword,
                pInsuranceNumber, pDiseaseName, pAllergyName,
                pMedicineName);
    }
}
