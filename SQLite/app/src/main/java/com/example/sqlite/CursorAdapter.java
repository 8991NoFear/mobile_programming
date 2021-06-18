package com.example.sqlite;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CursorAdapter extends BaseAdapter {
    Cursor cs = null;

    public CursorAdapter(Cursor cs) {
        this.cs = cs;
    }

    @Override
    public int getCount() {
        return cs.getCount();
    }

    @Override
    public Object getItem(int position) {
        cs.moveToPosition(position);
        return cs;
    }

    @Override
    public long getItemId(int position) {
        cs.moveToPosition(position);
        return cs.getInt(0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        try {
            if (view == null) {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.amigo_layout, null);
            }
            TextView textRectID = (TextView) view.findViewById(R.id.text_rectID);
            TextView textName = (TextView) view.findViewById(R.id.text_name);
            TextView textPhone = (TextView) view.findViewById(R.id.text_phone);

            cs.moveToPosition(position);
            textRectID.setText(String.valueOf(cs.getInt(0)));
            textName.setText(cs.getString(1));
            textPhone.setText(cs.getString(2));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return view;
    }
}
