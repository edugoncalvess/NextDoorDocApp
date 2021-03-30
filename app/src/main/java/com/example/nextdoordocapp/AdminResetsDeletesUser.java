package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminResetsDeletesUser extends AppCompatActivity {
    String sEmail;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_deletes_user);

        Button btnReset = findViewById(R.id.btnR);
        Button btnDelete = findViewById(R.id.btnDeleteAtAdminDelete);
        EditText email = findViewById(R.id.admSearchEmail);
        databaseHelper = new DatabaseHelper(this);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sEmail = email.getText().toString();
                boolean valEmailPassword = databaseHelper.valAdminEmailPassword(sEmail);
                StringBuilder role = new StringBuilder();
                if (valEmailPassword) {
//                    Toast.makeText(LoginActivityPg1.this, "Login successful", Toast.LENGTH_LONG).show();
                    Cursor cursor = databaseHelper.roleAdminLoginTableExists(sEmail);
                    if (cursor.getCount() > 0) {
                        while (cursor.moveToNext()) {
                            role.append(cursor.getString(0));
                        }
                    }
                    boolean r = databaseHelper.resetPasswordLogin(sEmail, "NextDoorDoc");
                    boolean resMain = databaseHelper.resetPasswordLogin(sEmail, "NextDoorDoc");
                    if (r && resMain) {
                        Toast.makeText(AdminResetsDeletesUser.this, "Password Reset Successfully", Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(AdminResetsDeletesUser.this, "Reset Failed", Toast.LENGTH_SHORT).show();

                }else
                    Toast.makeText(AdminResetsDeletesUser.this, "Email Id doesn't exists", Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sEmail = email.getText().toString();
            }
        });
    }
}