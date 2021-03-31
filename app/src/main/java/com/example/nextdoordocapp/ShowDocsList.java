package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ShowDocsList extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    List<ListData> mListData= new ArrayList<>();
    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_docs_list);

        databaseHelper = new DatabaseHelper(this);


        mRecyclerView = findViewById(R.id.recyclerViewPat);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(ShowDocsList.this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(ShowDocsList.this,
                DividerItemDecoration.VERTICAL));

        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        ListData mList;


        String doctorFName;
        String doctorLName;
        String doctorAdd;

        Intent docIntent = getIntent();
        Bundle extraInfo = docIntent.getExtras();
        String addressDoc = extraInfo.getString("Address");
        String firstThreeCharsPostalCode = extraInfo.getString("postalCode");
        int patientId = extraInfo.getInt("PatientId");

        Log.d("PAtienID", String.valueOf(patientId));
        Log.d("address",addressDoc);
        Log.d("Postal",firstThreeCharsPostalCode );
/*
        SharedPreferences preferences = getSharedPreferences("myPref",MODE_PRIVATE);
*/
        SharedPreferences preferences = getSharedPreferences("MyPref",MODE_PRIVATE);


        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("patientId",patientId);
        editor.commit();

        Cursor DoctorByAddCursor = databaseHelper.getDoctorByAddress(addressDoc);
        Cursor DoctorBtPostalCursor = databaseHelper.getDoctorByPostalCode(firstThreeCharsPostalCode);
        if(DoctorByAddCursor.getCount()>0){
            while (DoctorByAddCursor.moveToNext()){
                doctorFName = DoctorByAddCursor.getString(0);
                doctorLName = DoctorByAddCursor.getString(1);
                doctorAdd = DoctorByAddCursor.getString(2);

                mList = new ListData(doctorFName, doctorLName,
                        doctorAdd);

                mListData.add(mList);
            }
        }
        else if(DoctorBtPostalCursor.getCount()>0){
            while (DoctorBtPostalCursor.moveToNext()){
                doctorFName = DoctorBtPostalCursor.getString(0);
                doctorLName = DoctorBtPostalCursor.getString(1);
                doctorAdd = DoctorBtPostalCursor.getString(2);
                mList = new ListData(doctorFName, doctorLName,
                        doctorAdd);

                mListData.add(mList);
            }
        }

        DocListAdapter mListAapter = new DocListAdapter(ShowDocsList.this, mListData);
        mRecyclerView.setAdapter(mListAapter);




    }


}