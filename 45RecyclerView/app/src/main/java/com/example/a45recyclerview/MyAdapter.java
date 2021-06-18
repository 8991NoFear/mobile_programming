package com.example.a45recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.zip.Inflater;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    LayoutInflater inflater = null;
    LinkedList<String> data = null;

    public MyAdapter(Context context, LinkedList<String> data) {
        inflater = LayoutInflater.from(context); // bo chen de lien ket du lieu voi xml
        this.data = data;
    }

    // TODO: xac dinh file xml nao de hien thi 1 muc
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(itemView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.itemText.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
