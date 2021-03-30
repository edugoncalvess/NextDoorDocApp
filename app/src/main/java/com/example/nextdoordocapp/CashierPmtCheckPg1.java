package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class CashierPmtCheckPg1 extends AppCompatActivity {

    // REMOVE THIS FOLLOWING VARIABLE AFTERWARDS
    String messageToBeShown; //  Just to show something on the screen while we don't have the SQL statements functioning
    int patientID = 0;
    int paymentId;
    double PAmount;
    String PStatus;
    String PMethod;


    DatabaseHelper databaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashier_pmt_check_pg1);

        databaseHelper = new DatabaseHelper(this);


        EditText txtPatientID = findViewById(R.id.txtPatientID);



        TextView txtPmtBalanceAmount = findViewById(R.id.txtPmtBalanceAmount);
        TextView txtPaymentStatus = findViewById(R.id.txtPaymentStatus);

        DecimalFormat format = new DecimalFormat(("####.##"));
        Button btnCheckPmtStatus = findViewById(R.id.btnCheckPmtStatus);
        Button btnGoToPmtActv = findViewById(R.id.btnGoToPmtActv);
        btnGoToPmtActv.setVisibility(View.INVISIBLE);

        btnCheckPmtStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patientID = Integer.parseInt(txtPatientID.getText().toString());

                // Call this method only for test, to manually create a pending payment so we have one register at least
                 databaseHelper.addRecordPaymentTest();




                Cursor c = databaseHelper.checkPaymentByPatientId(patientID);

                if(c.getCount() > 0) {
                    while (c.moveToNext()) {
                        paymentId = Integer.parseInt(c.getString(0).toString());
                        // String PDate = c.getString(2); // Unnecessary
                        // String PTime = c.getString(3); // Unnecessary
                        PAmount = Double.parseDouble(c.getString(4).toString());
                        PMethod = c.getString(5);
                        PStatus = c.getString(6);







                    }
                    //Log.d("DBGETPAYMT", paymentId.toString());
                }
                else if(c.getCount() < 1) {
                    Toast.makeText(CashierPmtCheckPg1.this, "There is no pending payment for this id", Toast.LENGTH_SHORT).show();

                    PMethod = "ERROR";
                    PAmount = 0;
                }
                c.close();;


                /*
                // Possible values from the Payment Table ( TABLE4_NAME )
                T4COL_5     T4COL_6     T4COL_7
                Amount      Method      PmtStatus
                -------     ----------  -------------
                0.00	    MSP     	Insured
                != 0        Cash        Pending
                                        Paid
                            Card    	Pending
                                        Paid
                            Deposit     Pending
                                        Paid
                */


                if (PMethod.equals("MSP")) {
                    //PAmount = 0.00f;
                    txtPmtBalanceAmount.setText("$ " + format.format(PAmount));
                    txtPaymentStatus.setText("MSP Insured");
                    btnGoToPmtActv.setVisibility(View.INVISIBLE);
                }
                else if(PMethod.equals("Paid")) {
                    PAmount = 0;
                    txtPaymentStatus.setText("The are no pending payments fo this patient");
                    btnGoToPmtActv.setVisibility(View.INVISIBLE);
                }
                else if(PMethod.equals("ERROR")) {
                    PAmount = 0;
                    txtPaymentStatus.setText("The are no pending payments fo this patient");
                    btnGoToPmtActv.setVisibility(View.INVISIBLE);
                }
                else {
                    if (PAmount == 0){
                        txtPmtBalanceAmount.setText("$ " + format.format(PAmount));
                        //PStatus = "Payment done";
                        txtPaymentStatus.setText("Payment with ID " + paymentId + " is " + PStatus);
                    }
                    else {
                        txtPmtBalanceAmount.setText("$ " + format.format(PAmount));
                        // PStatus = "Pending";
                        txtPaymentStatus.setText("Payment with ID " + paymentId + " is " + PStatus);
                        // txtPaymentStatus.setText(PStatus);
                        btnGoToPmtActv.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        btnGoToPmtActv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toPaymentAcvt = new Intent(CashierPmtCheckPg1.this,CashierRegisterPmtPg1.class);
                Bundle extraInfo = new Bundle();
                extraInfo.putInt("PaymentID", paymentId);
                extraInfo.putInt("User_ID", patientID);
                extraInfo.putDouble("OwedValue",PAmount);
                toPaymentAcvt.putExtras(extraInfo);
                startActivity(toPaymentAcvt);

            }
        });



    }
}