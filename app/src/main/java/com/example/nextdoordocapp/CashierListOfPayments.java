package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CashierListOfPayments extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashier_list_of_payments);

        databaseHelper = new DatabaseHelper(this);

        Button btnCheckPendingPaymts = findViewById(R.id.btnCheckPendingPaymts);
        TextView txtListOfPendingPmts = findViewById(R.id.txtListOfPendingPmts);
        Button btnBackHomeFromPmtList = findViewById(R.id.btnBackHomeFromPmtList);






        btnCheckPendingPaymts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor c = databaseHelper.listAllPendingPayments();
                StringBuilder strMsg = new StringBuilder();
                if(c.getCount() > 0) {
                    //strMsg.append("PaymentID; PatientID; Patient Name; PaymentAmout");
                    strMsg.append("PmtID; "); strMsg.append("\t");
                    strMsg.append("PatientID; "); strMsg.append("\t");
                    strMsg.append("Name; "); strMsg.append("\t");
                    strMsg.append("PaymentAmout");
                    strMsg.append("\n");
                    while (c.moveToNext()) {
                        ///strMsg.append("PmtID: " + c.getString(0));
                        strMsg.append(c.getString(0) + ";  "); strMsg.append("\t"); // PaymentID
                        strMsg.append(c.getString(1) + ";  "); strMsg.append("\t"); // Patient ID
                        strMsg.append(c.getString(2) + " "); // patientFirst Name
                        strMsg.append(c.getString(3) + ";  "); strMsg.append("\t"); // patient Last Name
                        strMsg.append("$" + c.getString(4));  // payment amount
                        strMsg.append("\n");

                        // select pmt.PaymentID, pat.PatientID, pat.patFName, pat.LName, pmt.PaymentAmount
                        // from Payment pmt INNER JOIN patient pat
                        // ON pmt.PatientID = pat.PatientID
                        // WHERE pmt.PaymentStatus = 'Pending';



                    }
                    txtListOfPendingPmts.setText(strMsg);
                }
                else if(c.getCount() == 0)  {
                    strMsg.append("There are no pending payments"); strMsg.append("\t");
                    Log.d("PaymentCursorCount", "Query retrieving pending payments returned with 0 entries " + c.getCount());
                    txtListOfPendingPmts.setText(strMsg);
                }

                c.close();;

            }
        });


        btnBackHomeFromPmtList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CashierListOfPayments.this,CashierHomePg1.class));
            }
        });

    }
}