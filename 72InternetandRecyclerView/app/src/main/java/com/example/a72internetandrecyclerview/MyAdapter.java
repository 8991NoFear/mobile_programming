package com.example.a72internetandrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.LinkedList;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    Context context = null;
    LinkedList<MyItemData> data = null;

    public MyAdapter(LinkedList<MyItemData> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.my_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MyItemData itemData = data.get(position);
        holder.getHeader().setText(itemData.getTitle());
        Picasso.get().load(itemData.getThumb()).into(holder.getThumb());
        holder.getSummary().setText(itemData.getSummary());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
