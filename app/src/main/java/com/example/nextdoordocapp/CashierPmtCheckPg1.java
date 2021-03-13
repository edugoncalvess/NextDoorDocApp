package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class CashierPmtCheckPg1 extends AppCompatActivity {

    // REMOVE THIS FOLLOWING VARIABLE AFTERWARDS
    String messageToBeShown; //  Just to show something on the screen while we don't have the SQL statements functioning
    String User_ID;
    float PAmount;
    String PStatus;
    String PMethod;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashier_pmt_check_pg1);
        final String TABLENAME_PMT = "Payment";  //     REVIEW THE TABLE NAME
        // HARDCODE JUST FOR TESTING:
        PMethod = "MSP";
        PMethod = "Covered";
        PAmount = 80.00f;

        DecimalFormat format = new DecimalFormat(("####.##"));
        Button btnCheckPmtStatus = findViewById(R.id.btnCheckPmtStatus);
        Button btnGoToPmtActv = findViewById(R.id.btnGoToPmtActv);
        btnGoToPmtActv.setVisibility(View.INVISIBLE);

        btnCheckPmtStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView txtPmtBalanceAmount = findViewById(R.id.txtPmtBalanceAmount);
                TextView txtPaymentStatus = findViewById(R.id.txtPaymentStatus);

                if (PMethod.equals("MSP")) {
                    txtPmtBalanceAmount.setText("$ " + format.format(PAmount));
                    txtPaymentStatus.setText("MSP Insured");
                }
                else {
                   if (PAmount == 0){
                       txtPmtBalanceAmount.setText("$ " + format.format(PAmount));
                       PStatus = "Payment done";
                       txtPaymentStatus.setText(PStatus);
                   }
                   else {
                       txtPmtBalanceAmount.setText("$ " + format.format(PAmount));
                       PStatus = "Pending";
                       txtPaymentStatus.setText(PStatus);
                       btnGoToPmtActv.setVisibility(View.VISIBLE);
                   }
                }

                // messageToBeShown = "SELECT " + PAmount + ", " + PStatus  + ", " + PMethod + " from " + TABLENAME_PMT + "WHERE UserID = " + User_ID;
            }

        });

        btnGoToPmtActv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toPaymentAcvt = new Intent(CashierPmtCheckPg1.this,CashierRegisterPmtPg1.class);
                Bundle extraInfo = new Bundle();
                extraInfo.putString("transferedPatientId",User_ID);
                extraInfo.putFloat("OwedValue",PAmount);
                toPaymentAcvt.putExtras(extraInfo);
                // toPaymentAcvt.putExtra("OwedValue",PAmount);
                // toPaymentAcvt.putExtra("transferedPatientId",User_ID);
                startActivity(toPaymentAcvt);

            }
        });



    }
}