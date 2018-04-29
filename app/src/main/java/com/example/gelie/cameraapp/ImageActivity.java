package com.example.gelie.cameraapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ImageActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY| View.SYSTEM_UI_FLAG_FULLSCREEN| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        String filePath = getIntent().getStringExtra("filePath");
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);

        if (bitmap != null) {
            imageView = findViewById(R.id.image_display);
            imageView.setImageBitmap(bitmap);
        }
    }
}
