package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class listDocDetail extends AppCompatActivity {
    TextView mFname;
    TextView mLname;
    TextView mAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_doc_detail);


        mFname = findViewById(R.id.rcdlfirstName);
        mLname = findViewById(R.id.rcdllastName);
        mAddress = findViewById(R.id.rcdlAddress);

        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            mFname.setText(mBundle.getString("icon"));
            mLname.setText(mBundle.getString("sender"));
            mAddress.setText(mBundle.getString("title"));
        }
    }
}