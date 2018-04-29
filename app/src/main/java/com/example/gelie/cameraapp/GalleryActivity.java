package com.example.gelie.cameraapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.gelie.cameraapp.Services.StorageService;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class GalleryActivity extends AppCompatActivity {

    GridView mGridView;
    ArrayList<String> arrayOfFilePaths;
    File[] listOfFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY| View.SYSTEM_UI_FLAG_FULLSCREEN);

        arrayOfFilePaths = new ArrayList<>();
        getFromSdCard();

        mGridView = findViewById(R.id.grid_gallery);
        mGridView.setAdapter(new ImageAdapter(this, arrayOfFilePaths));

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String filePath = arrayOfFilePaths.get(position);
                Intent myIntent = new Intent(getApplicationContext(),ImageActivity.class);
                myIntent.putExtra("filePath", filePath);
                startActivity(myIntent);
            }
        });
    }

    private void getFromSdCard() {
        File file = StorageService.getCameraAppDir();

        if (file.isDirectory()) {
            listOfFiles = file.listFiles();

            for (File aFile : listOfFiles) {
                arrayOfFilePaths.add(aFile.getAbsolutePath());
            }
        }
    }
}