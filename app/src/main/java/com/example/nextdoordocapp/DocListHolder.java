package com.example.nextdoordocapp;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DocListHolder extends RecyclerView.ViewHolder {
    TextView mFname;
    TextView mLname;
    TextView mAddress;
    RelativeLayout mdocListLayout;

    DocListHolder(View itemView) {
        super(itemView);

        mFname = itemView.findViewById(R.id.rcdlfirstName);
        mLname = itemView.findViewById(R.id.rcdllastName);
        mAddress = itemView.findViewById(R.id.rcdlAddress);
        mdocListLayout = itemView.findViewById(R.id.docListLayout);
    }

}
