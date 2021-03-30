package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PatientCheckNewMessage extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_check_new_message);

        Intent findDoctorPg1Intent = getIntent();
        int patientId = getIntent().getIntExtra("patientId",0);

        databaseHelper = new DatabaseHelper(this);

        TextView viewMessage = findViewById(R.id.txtViewNEwMEssagePatFromDoc);
        Button btnHome = findViewById(R.id.btnBackHomeToPatient);


        Cursor c = databaseHelper.viewNewMessageforPatient();
        StringBuilder str = new StringBuilder();
        if (c.getCount() > 0) {
            while (c.moveToNext()) {
                str.append(c.getString(0));
                str.append("\n");

            }
            viewMessage.setText(str);
        }

            btnHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(PatientCheckNewMessage.this, PatientMainActions.class));
                }
            });
        }

/* Cursor c = databaseHelper.viewData();
                StringBuilder str = new StringBuilder();
                if(c.getCount()>0){
                    while (c.moveToNext()){
                        str.append(("ID : ") + c.getString(0));
                        str.append(("Name " + c.getString(1)));
                        str.append((" :" + c.getString(2)));
                        str.append((" Course " + c.getString(3)));
                        str.append("\n");

                    }
                    output.setText(str);
                }
            }*/

}