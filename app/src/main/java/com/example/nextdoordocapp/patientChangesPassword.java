package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class patientChangesPassword extends AppCompatActivity {
    String e, p;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_changes_password);

        Button changePassword = findViewById(R.id.btnPatPassword);
        EditText emailId = findViewById(R.id.txtPassChangeEmail);
        EditText password = findViewById(R.id.txtPassChangePassword);
        databaseHelper = new DatabaseHelper(this);
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e = emailId.getText().toString();
                p = password.getText().toString();

                boolean valEmailPassword = databaseHelper.valAdminEmailPassword(e);
                StringBuilder role = new StringBuilder();
                if (valEmailPassword) {
//                    Toast.makeText(LoginActivityPg1.this, "Login successful", Toast.LENGTH_LONG).show();
                    Cursor cursor = databaseHelper.roleAdminLoginTableExists(e);
                    if (cursor.getCount() > 0) {
                        while (cursor.moveToNext()) {
                            role.append(cursor.getString(0));
                        }
                    }
                    boolean r = databaseHelper.updatePasswordLogin(e, p);
                    boolean resMain;

                    resMain = databaseHelper.resetPasswordPatient(e, p);

                    if (r && resMain) {
                        Toast.makeText(patientChangesPassword.this, "Password Changed Successfully", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(patientChangesPassword.this, "Password change Failed", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}