package com.example.a72internetandrecyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    private TextView header = null;
    private ImageView thumb = null;
    private TextView summary = null;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        header = (TextView) itemView.findViewById(R.id.header);
        thumb = (ImageView) itemView.findViewById(R.id.thumb);
        summary = (TextView) itemView.findViewById(R.id.summary);
    }

    public TextView getHeader() {
        return header;
    }

    public ImageView getThumb() {
        return thumb;
    }

    public TextView getSummary() {
        return summary;
    }
}
