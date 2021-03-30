package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ShowDocsList extends AppCompatActivity {
    List<ListData> mListData= new ArrayList<>();
    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_docs_list);


        mRecyclerView = findViewById(R.id.recyclerViewPat);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(ShowDocsList.this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(ShowDocsList.this,
                DividerItemDecoration.VERTICAL));

        mRecyclerView.setLayoutManager(mLinearLayoutManager);




        ListData mList= new ListData("Sam", "Gholi",
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
        mList = new ListData("Sam", "Gholi",
                "Coquitlam");

        mListData.add(mList);


        DocListAdapter mListAapter = new DocListAdapter(ShowDocsList.this, mListData);
        mRecyclerView.setAdapter(mListAapter);
    }

}