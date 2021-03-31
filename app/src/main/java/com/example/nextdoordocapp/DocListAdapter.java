package com.example.nextdoordocapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DocListAdapter extends RecyclerView.Adapter<DocListHolder> {
    private List<ListData> mListData  ;
    private Context mContext;


    public DocListAdapter(Context mContext, List<ListData> mListData ) {
        this.mListData = mListData;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public DocListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyelerview_doclist_item,
                parent, false);
        return new DocListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DocListHolder holder, int position) {
        holder.mFname.setText(mListData.get(position).getmFname());
        holder.mLname.setText(mListData.get(position).getmLname());
        holder.mAddress.setText(mListData.get(position).getmAddress());

        holder.mdocListLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, PatientFindDoctorPg2.class);
                mIntent.putExtra("firstName", holder.mFname.getText().toString());
                mIntent.putExtra("LastName", holder.mLname.getText().toString());
                mIntent.putExtra("Address", holder.mAddress.getText().toString());
                mContext.startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }
}
