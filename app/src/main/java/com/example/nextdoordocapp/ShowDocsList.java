package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

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

/* Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            mFname.setText(mBundle.getString("icon"));
            mLname.setText(mBundle.getString("sender"));
            mAddress.setText(mBundle.getString("title"));*/
        databaseHelper = new DatabaseHelper(this);


        mRecyclerView = findViewById(R.id.recyclerViewPat);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(ShowDocsList.this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(ShowDocsList.this,
                DividerItemDecoration.VERTICAL));

        mRecyclerView.setLayoutManager(mLinearLayoutManager);


        Intent intentDocList = getIntent();
        String docFName = String.valueOf(getIntent().getIntExtra("docFirstName",0));
        String docLName = String.valueOf(getIntent().getIntExtra("docLastName",0));
        String docAddress = String.valueOf(getIntent().getIntExtra("docAddress",0));
/*   databaseHelper.viewNewMessageDoc();
        Cursor c = databaseHelper.viewNewMessageDoc();
        if(c.getCount()>0){
            while (c.moveToNext()){
                String sender = c.getString(0);
                String title = c.getString(1);
                String Details = c.getString(2);
                String time = c.getString(3);

                mEmail = new EmailData("Patient: " + sender, "Message/Question",
                        Details,
                        time);
            }
            mEmailData.add(mEmail);
        }*/


        ListData mList= new ListData("Sam", "Gholi",
                "Coquitlam");

        mListData.add(mList);

        mList = new ListData(docFName, docLName,
                docAddress);
        mListData.add(mList);

        mList = new ListData("Sam", "Gholi",
                "Coquitlam");

        mListData.add(mList);

        mList = new ListData("Sam", "Gholi",
                "Coquitlam");

        mListData.add(mList);

        mList = new ListData("Sam", "Gholi",
                "Coquitlam");

        mListData.add(mList);

        mList = new ListData("Sam", "Gholi",
                "Coquitlam");

        mListData.add(mList);
        mList = new ListData("Sam", "Gholi",
                "Coquitlam");

        mListData.add(mList);


        DocListAdapter mListAapter = new DocListAdapter(ShowDocsList.this, mListData);
        mRecyclerView.setAdapter(mListAapter);
    }

}