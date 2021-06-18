package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db = null;
    ListView listView = null;

    /*
     * mo db trong onCreate()
     * dong db trong onStop()
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_view);

        // mo csdl
        String filePath = getFilesDir() + "/mydb"; // luu o /data/data/[package]/files
        db = SQLiteDatabase.openDatabase(filePath, null, SQLiteDatabase.CREATE_IF_NECESSARY);

        // tao bang
        db.beginTransaction();
        try {
            // execSQL khong tra ve ket qua thuc hien --> khong biet duoc3. thanh cong hay that bai
            db.execSQL("CREATE TABLE tblAMIGO (" +
                    "recID integer primary key AUTOINCREMENT," +
                    "name text," +
                    "phone text);");
            db.setTransactionSuccessful();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    protected void onStop() {
        db.close();
        super.onStop();
    }

    public void insert(View v) {
        // C1: sd execSQL()
//        db.beginTransaction();
//        try {
//            db.execSQL("INSERT INTO tblAMIGO(name, phone) values ('AAA', '555-1111');");
//            db.execSQL("INSERT INTO tblAMIGO(name, phone) values ('BBB', '555-2222');");
//            db.execSQL("INSERT INTO tblAMIGO(name, phone) values ('CCC', '555-3333');");
//            db.setTransactionSuccessful();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            db.endTransaction();
//        }

        // C2 sd insert()
        db.beginTransaction();
        try {
            ContentValues cv = new ContentValues();
            cv.put("name", "ABC");
            cv.put("phone", "555-1010");
            long res = db.insert("tblAMIGO", null, cv); // tra ve khoa chinh
            Log.v("SQLite", "#1 insert() result: " + res);

            cv.put("name", "DEF");
            cv.put("phone", "555-2020");
            res = db.insert("tblAMIGO", null, cv);
            Log.v("SQLite", "#2 insert() result: " + res);

            cv.clear();
            res = db.insert("tblAMIGO", null, cv);
            Log.v("SQLite", "#3 insert() result: " + res);

            res = db.insert("tblAMIGO", "name", cv);
            Log.v("SQLite", "#4 insert() result: " + res);

            db.setTransactionSuccessful();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    public void update(View v) {
        // C1: sd execSQL()
//        db.beginTransaction();
//        try {
//            db.execSQL("UPDATE tblAMIGO set name='ABC' where phone='555-1111';");
//            db.setTransactionSuccessful();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            db.endTransaction();
//        }

        // C2: sd update()
        db.beginTransaction();
        try {
            ContentValues cv = new ContentValues();
            cv.put("name", "Maria");
            int res = db.update("tblAMIGO", cv, "recID > 10 and recID < 100", null);
            Log.v("SQLite", "#1 insert() result: " + res);

            db.setTransactionSuccessful();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    public void delete(View v) {
        // C1: sd execSQL()
//        db.beginTransaction();
//        try {
//            db.execSQL("DELETE FROM tblAMIGO");
//            db.setTransactionSuccessful();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            db.endTransaction();
//        }

        // C2: sd delete()
        db.beginTransaction();
        try {
            int res = db.delete("tblAMIGO", "recID > 10 and recID < 100", null);
            Log.v("SQLite", "#1 delete() result: " + res);
            db.setTransactionSuccessful();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    public void select(View v) {
        // RAW query
        Cursor cs = db.rawQuery("SELECT * FROM tblAMIGO", null);
        Log.v("SQLite", "# total records: " + cs.getCount());

        // SIMPLE query
//        String[] columns = {"rectID", "name", "phone"};
//        Cursor cs = db.query("tblAMIGO", columns, null, null, null, null, null);

//        cs.moveToPosition(-1);
//        while (cs.moveToNext()) {
//            int rectID = cs.getInt(0);
//            String name = cs.getString(1);
//            String phone = cs.getString(cs.getColumnIndex("phone"));
//            Log.v("SQLite", "recID=" + rectID + " & name=" + name + " & phone=" + phone);
//        }

        CursorAdapter adapter = new CursorAdapter(cs);
        listView.setAdapter(adapter);

        Intent it = new Intent();
        it.put
    }
}