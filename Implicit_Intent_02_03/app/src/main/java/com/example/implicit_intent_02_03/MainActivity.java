package com.example.implicit_intent_02_03;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private final int ACTIVITY_REQUEST_CHOOSE_IMAGE = 1;
    private Uri uri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // xu ly intent tu app khac
        Intent it = getIntent();
        ImageView imgView = (ImageView) findViewById(R.id.imgView);
        if (it.getAction() == Intent.ACTION_SEND) {
            uri = it.getParcelableExtra(Intent.EXTRA_STREAM);
            InputStream is = null;
            try {
                is = getContentResolver().openInputStream(uri);
                Bitmap bm = BitmapFactory.decodeStream(is);
                imgView.setImageBitmap(bm);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void chooseImage(View v) {
        Intent it = new Intent(Intent.ACTION_GET_CONTENT);
        it.setType("image/*");
        if (it.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(it, ACTIVITY_REQUEST_CHOOSE_IMAGE);
        }
    }

    public void shareImage(View v) {
        if (uri != null) {
            Intent it = new Intent();
            it.setAction(Intent.ACTION_SEND);
            it.setType("image/*");
            it.putExtra(Intent.EXTRA_STREAM, uri);
            startActivity(it);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ACTIVITY_REQUEST_CHOOSE_IMAGE) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    ImageView imgView = findViewById(R.id.imgView);
                    uri = data.getData();
                    try {
                        InputStream is = getContentResolver().openInputStream(uri);
                        Bitmap bm = BitmapFactory.decodeStream(is);
                        imgView.setImageBitmap(bm);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Toast.makeText(this, "hello world", Toast.LENGTH_SHORT).show();;
    }
}