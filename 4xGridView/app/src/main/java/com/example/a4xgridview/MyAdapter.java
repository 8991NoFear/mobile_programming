package com.example.a4xgridview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    ArrayList<MyItemData> data;

    public MyAdapter(ArrayList<MyItemData> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        Context context = parent.getContext();
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        }
        TextView figureText = (TextView) itemView.findViewById(R.id.figureText);
        figureText.setText(data.get(position).getTitle());
        ImageView imageView = (ImageView) itemView.findViewById(R.id.figure);
        try {
            Uri uri = data.get(position).getFigureUri();
            InputStream is = context.getContentResolver().openInputStream(uri);
            Bitmap bm = BitmapFactory.decodeStream(is);
            imageView.setImageBitmap(bm);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return itemView;
    }
}
