package com.example.gelie.cameraapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.example.gelie.cameraapp.Services.StorageService;

import java.io.File;
import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    GridView mGridView;
    ArrayList<String> arrayOfFilePaths;
    File[] listOfFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        arrayOfFilePaths = new ArrayList<>();
        getFromSdCard();

        mGridView = findViewById(R.id.grid_gallery);
        mGridView.setAdapter(new ImageAdapter(this, arrayOfFilePaths));
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