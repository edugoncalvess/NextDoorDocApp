package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class PatientRegistrationPg1 extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    String patientGender = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_registration_pg1);

        databaseHelper = new DatabaseHelper(this);


        EditText patientFirstName = findViewById(R.id.txtRgstPgFirstName);
        EditText patientLastName = findViewById(R.id.txtRgstPgFamilyName);
        EditText patientEmail = findViewById(R.id.txtRgstPgEmail);
        EditText patientPassword = findViewById(R.id.txtRgstPgPassword);
        EditText patientPhone = findViewById(R.id.txtRgstPgPhone);
        EditText patientDOB = findViewById(R.id.txtRgstPgDOB);
        EditText patientHeight = findViewById(R.id.txtRgstPgHeight);
        EditText patientWeight = findViewById(R.id.txtRgstPgWeight);
        EditText patientAllergies = findViewById(R.id.txtRgstPgAllergies);
        EditText patientDisease = findViewById(R.id.txtRgstPgDisease);
        EditText patientMedicine = findViewById(R.id.txtRgstPgMedicine);
        EditText patientInsuranceNumber = findViewById(R.id.txtRgstPgInsuranceNumber);
        EditText patientStreet = findViewById(R.id.txtRgstPgStreet);
        EditText patientCity = findViewById(R.id.txtRgstPgCity);
        EditText patientState = findViewById(R.id.txtRgstPgState);
        EditText patientCountry = findViewById(R.id.txtRgstPgCountry);
        EditText patientPostalCode = findViewById(R.id.txtRgstPgPCode);
        String role = "patient";

//        Spinner patientGenderSelection = (Spinner) findViewById(R.id.sprGender);
        Spinner patientGenderSelection = findViewById(R.id.sprUpdateGender);

        Button btnRegisterPatient = findViewById(R.id.btnRgstPgCreatePatient);


        btnRegisterPatient.setOnClickListener(new View.OnClickListener() {
            boolean isInserted;

            @Override
            public void onClick(View v) {
                int spinner_pos = patientGenderSelection.getSelectedItemPosition();
                Log.d("SnrPos", "The selected position was " + spinner_pos);

                patientGender = patientGenderSelection.getSelectedItem().toString();

                Log.d("SpinnerGenderSelection", patientGenderSelection.getSelectedItem().toString());


                String emailId = patientEmail.getText().toString();
                String password = patientPassword.getText().toString();

                Boolean chkEmail = databaseHelper.valEmail(emailId);
                if (chkEmail == true) {
                    Boolean i = databaseHelper.insert(emailId, password, role);
                    if (i == true) {
                        String patInsNum = patientInsuranceNumber.getText().toString();
                        if (patInsNum.isEmpty())
                            patientInsuranceNumber.setText("NULL");

                        isInserted = databaseHelper.addRecordPatient(patientEmail.getText().toString(),
                                patientFirstName.getText().toString(),
                                patientLastName.getText().toString(),
                                patientDOB.getText().toString(),
                                patientGender,
                                patientHeight.getText().toString(),
                                patientWeight.getText().toString(),
                                patientPhone.getText().toString(),
                                patientCountry.getText().toString(),
                                patientState.getText().toString(),
                                patientCity.getText().toString(),
                                patientStreet.getText().toString(),
                                patientPostalCode.getText().toString(),
                                patientPassword.getText().toString(),
                                patientInsuranceNumber.getText().toString(),
                                patientDisease.getText().toString(),
                                patientAllergies.getText().toString(),
                                patientMedicine.getText().toString());
                    }
                }

                if (isInserted) {
                    //Toast.makeText(PatientRegistrationPg1.this,"Data added",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(PatientRegistrationPg1.this, PatientRegistrationSuccessfulPg1.class));
                } else {
                    Toast.makeText(PatientRegistrationPg1.this, "Data not added. Please check the data.", Toast.LENGTH_LONG).show();
                }


            }

        });


    }
}