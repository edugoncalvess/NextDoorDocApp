package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AdminRegistrationPage extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_registration_page);
        databaseHelper = new DatabaseHelper(this);
        EditText inputAdmFName = findViewById(R.id.inputAdmFName);
        EditText inputAdmLName = findViewById(R.id.inputAdmLName);
        EditText inputAdmCellNum = findViewById(R.id.inputAdmCellNum);
        EditText inputAdmAddress = findViewById(R.id.inputAdmAddress);
        EditText inputAdmEmail = findViewById(R.id.inputAdmEmail);
        EditText inputAdmPassword = findViewById(R.id.txtRgstPgPassword2);
        Spinner spnAdmRoleSel = findViewById(R.id.spnAdmRoleSel);

        Button btnAdmNext = findViewById(R.id.btnAdmNext);
        Button btnAdmClear = findViewById(R.id.btnAdmClear);

        btnAdmClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputAdmFName.setText("");
                inputAdmLName.setText("");
                inputAdmCellNum.setText("");
                inputAdmAddress.setText("");
                inputAdmEmail.setText("");
                inputAdmPassword.setText("");
                spnAdmRoleSel.setSelection(0);
            }
        });

        btnAdmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//convertign to the next page checking for null values and registering the user from Admin side
//                if (inputAdmFName.equals("") || inputAdmLName.equals("") || inputAdmCellNum.equals("") ||
//                        inputAdmAddress.equals("") || inputAdmFName.equals("") || inputAdmEmail.equals("") || inputAdmPassword.equals("")) {

                String emailId = inputAdmEmail.getText().toString();
                String password = inputAdmPassword.getText().toString();

                Boolean chkEmail = databaseHelper.valEmail(emailId);
                if (chkEmail == true) {
                    Boolean i = databaseHelper.insert(emailId, password);
                    if (i == true) {
                        Toast.makeText(AdminRegistrationPage.this, "Registration was successful",
                                Toast.LENGTH_LONG).show();

                        if (spnAdmRoleSel.getSelectedItem().toString().equals("Patient")) {
                            startActivity(new Intent(AdminRegistrationPage.this, AdmPatInformation.class));

                        } else if (spnAdmRoleSel.getSelectedItem().toString().equals("Doctor")) {
                            startActivity(new Intent(AdminRegistrationPage.this, AdmDocInformation.class));

                        }
                        if (spnAdmRoleSel.getSelectedItem().toString().equals("Cashier")) {
                            startActivity(new Intent(AdminRegistrationPage.this, AdmCasInformation.class));
                        }
                        if (spnAdmRoleSel.getSelectedItem().toString().equals("Admin")) {
                            startActivity(new Intent(AdminRegistrationPage.this, AdmInfoRegistration.class));

                        }

                    } else {
                        Toast.makeText(AdminRegistrationPage.this, "Email ID already exists", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });
    }
}
