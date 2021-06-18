package com.example.a45recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView itemText = null;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        itemText = (TextView) itemView.findViewById(R.id.itemText);
    }
}
